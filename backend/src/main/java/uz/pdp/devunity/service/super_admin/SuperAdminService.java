package uz.pdp.devunity.service.super_admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.devunity.dto.admin.AdminDto;
import uz.pdp.devunity.dto.admin.AdminResponseProjectionDto;
import uz.pdp.devunity.entity.Admin;
import uz.pdp.devunity.entity.Role;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.entity.enums.ADMIN_ROLE;
import uz.pdp.devunity.entity.enums.ROLE_ENUM;
import uz.pdp.devunity.repo.AdminRepository;
import uz.pdp.devunity.repo.RoleRepository;
import uz.pdp.devunity.repo.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    public void addAdmin(String username, ADMIN_ROLE adminRole, String roleDescription) {
        Optional<User> opt = Optional.ofNullable(userRepository.findByUsername(username));
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
    @Transactional
    public List<AdminResponseProjectionDto> getAllAdmins() {
        var da = userRepository.findAllAdminsData();
        var list = new ArrayList<AdminResponseProjectionDto>();
        for (Object[] objects : da) {
            var dto = new AdminResponseProjectionDto((UUID) objects[0], (String) objects[1], (String) objects[2], (String) objects[3]);
            list.add(dto);
        }
        System.out.println("OK");
        for (var item : list) {
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
