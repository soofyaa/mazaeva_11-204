package ru.itis.animerec.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.form.SignUpForm;

public interface UserService {
    boolean signUp(SignUpForm form);
    UserDTO getUserById(Long id);

    UserDTO getCurrentUserDto();

    boolean updateUserName(String newName);

    void updateUserAvatar(MultipartFile avatarFile);

    void deleteUserById(Long id);

    void blockUserById(Long id);

    boolean isAdmin();

    boolean isCurrentUser(Long id);

    String getNameById(Long id);
}