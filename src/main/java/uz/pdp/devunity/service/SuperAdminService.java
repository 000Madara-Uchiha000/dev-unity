package uz.pdp.devunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.devunity.dto.AdminDto;
import uz.pdp.devunity.dto.AdminResponseProjectionDto;
import uz.pdp.devunity.entity.Admin;
import uz.pdp.devunity.entity.Role;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;
import uz.pdp.devunity.repo.AdminRepository;
import uz.pdp.devunity.repo.RoleRepository;
import uz.pdp.devunity.repo.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    public void addAdmin(String email, ADMIN_ROLE adminRole, String roleDescription) {
        Optional<User> opt = Optional.ofNullable(userRepository.findByEmail(email));
        if (opt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = opt.get();
        addRoleAdmin(user);
        addToAdmin(user, adminRole, roleDescription);
    }

    private void addToAdmin(User user, ADMIN_ROLE adminRole, String roleDescription) {
        Admin admin = new Admin(user, adminRole, roleDescription);
        adminRepository.save(admin);
    }

    private void addRoleAdmin(User user) {
        List<Role> roles = user.getRoles();
        boolean isAdmin = roles.stream().anyMatch(role -> {
            ROLE_ENUM roleEnum = role.getRoleEnum();
            return roleEnum.equals(ROLE_ENUM.ROLE_ADMIN);
        });
        if (!isAdmin) {
            Role role = roleRepository.findByRoleEnum(ROLE_ENUM.ROLE_ADMIN);
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Transactional
    public void extractFromAdmin(UUID userId) {
        Optional<User> opt = userRepository.findById(userId);
        if (opt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = opt.get();

        deleteRoleAdmin(user);

        deleteFromAdmin(user);

    }

    protected void deleteFromAdmin(User user) {
        adminRepository.deleteAllByUser(user);
    }

    private void deleteRoleAdmin(User user) {
        user.getRoles().removeIf(role -> role.getRoleEnum().equals(ROLE_ENUM.ROLE_ADMIN));
        userRepository.save(user);
    }

    public List<AdminResponseProjectionDto> getAllAdmins() {
        List<AdminResponseProjectionDto> list=null;
        try {
            list = userRepository.findAllAdminsData();

        }catch (Exception e) {
            e.printStackTrace();
        }
        for (AdminResponseProjectionDto item : list) {
            List<AdminDto> adminRoleList = item.getAdminDtos();
            List<AdminDto> dbData = adminRepository.findAllByUserId(item.getUserId());
            adminRoleList.addAll(dbData);
        }
        return list;
    }

    public void addAdminRole(UUID userId, ADMIN_ROLE adminRole, String roleDescription) {
        Optional<User> opt = userRepository.findById(userId);
        if (opt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = opt.get();
        addToAdmin(user, adminRole, roleDescription);
    }

    public void deleteAdminRole(UUID adminId) {
        adminRepository.deleteById(adminId);
    }

    public List<ADMIN_ROLE> getAllAdminRoles() {
        return Arrays.stream(ADMIN_ROLE.values()).toList();
    }
}
