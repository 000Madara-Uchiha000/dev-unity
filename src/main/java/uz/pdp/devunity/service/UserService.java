package uz.pdp.devunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.devunity.dto.EditUserDto;
import uz.pdp.devunity.entity.Photo;
import uz.pdp.devunity.entity.User;
import uz.pdp.devunity.repo.PhotoRepository;
import uz.pdp.devunity.repo.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public void editUser(EditUserDto editUserDto) {
        Optional<User> opt = userRepository.findById(editUserDto.getId());
        if (opt.isPresent()) {
            User user = opt.get();
            setEditUserDto(editUserDto,user);
        }else {
            throw new RuntimeException("User not found");
        }
    }
    public void setEditUserDto(EditUserDto editUserDto, User user){

        user.setEmail(editUserDto.getEmail());
        user.setPassword(editUserDto.getPassword());
        if (user.getBio().getPhoto()!=null) {
            photoRepository.delete(user.getBio().getPhoto());
        }

        Photo ph = photoRepository.save(
                new Photo(editUserDto.getPhoto()));
        user.getBio().setPhoto(ph);
        user.getBio().setFirstname(editUserDto.getFirstName());
        user.getBio().setLastname(editUserDto.getLastName());
        user.getBio().setBio(editUserDto.getBio());
        userRepository.save(user);
    }
}
