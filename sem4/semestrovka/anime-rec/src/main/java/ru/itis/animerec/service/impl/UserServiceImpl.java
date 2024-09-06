package ru.itis.animerec.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.form.SignUpForm;
import ru.itis.animerec.repository.UserRepository;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.utils.mapper.UserMapper;
import ru.itis.animerec.utils.validator.EmailValidator;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public boolean signUp(SignUpForm form) {
        log.info("Signing up user with email: {}", form.getEmail());

        String email = form.getEmail();
        String username = form.getUsername();

        if (!EmailValidator.isValidEmail(email) ||
                userRepository.existsByEmail(email) ||
                userRepository.existsByUsername(username)) {
            return false;
        }

        UserEntity user = UserEntity.builder()
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .username(form.getUsername())
                .role(UserEntity.Role.USER)
                .state(UserEntity.State.ACTIVE)
                .build();

        userRepository.save(user);
        log.info("User signed up successfully");
        return true;
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        UserDTO userDTO = userMapper.toDTO(userEntity);
        log.info("User fetched: {}", userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getCurrentUserDto() {
        log.info("Fetching current user");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        UserDTO userDTO = userMapper.toDTO(user);
        log.info("Current user fetched: {}", userDTO);
        return userDTO;
    }

    @Override
    public boolean updateUserName(String newName) {
        log.info("Updating username to: {}", newName);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();

        if (userRepository.existsByUsername(newName)) {
            return false;
        }

        user.setUsername(newName);
        userRepository.save(user);
        log.info("Username updated successfully");
        return true;
    }

    @Override
    @SneakyThrows
    public void updateUserAvatar(MultipartFile avatarFile) {
        log.info("Updating user avatar");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        byte[] avatarBytes = avatarFile.getBytes();
        user.setAvatar(avatarBytes);
        userRepository.save(user);
        log.info("User avatar updated successfully");
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
        log.info("User deleted successfully");
    }

    @Override
    @Transactional
    public void blockUserById(Long id) {
        log.info("Blocking user by id: {}", id);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        user.setState(UserEntity.State.BANNED);
        userRepository.save(user);
        log.info("User blocked successfully");
    }

    @Override
    public boolean isAdmin() {
        log.info("Checking if current user is admin");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        boolean isAdmin = user.isAdmin();
        log.info("Current user is admin: {}", isAdmin);
        return isAdmin;
    }

    @Override
    public boolean isCurrentUser(Long id) {
        log.info("Checking if user with id {} is current user", id);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        boolean isCurrentUser = Objects.equals(user.getId(), id);
        log.info("User with id {} is current user: {}", id, isCurrentUser);
        return isCurrentUser;
    }

    @Override
    public String getNameById(Long id) {
        log.info("Fetching username by id: {}", id);
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        String username = user.getUsername();
        log.info("Username fetched: {}", username);
        return username;
    }
}