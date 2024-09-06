package ru.itis.animerec.utils.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itis.animerec.dto.UserDTO;
import ru.itis.animerec.entity.UserEntity;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatarBase64", expression = "java(convertToBase64(userEntity.getAvatar()))")
    UserDTO toDTO(UserEntity userEntity);

    @Mapping(target = "avatar", expression = "java(convertToBytes(userDTO.getAvatarBase64()))")
    UserEntity toEntity(UserDTO userDTO);

    default String convertToBase64(byte[] avatar) {
        if (avatar == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(avatar);
    }

    default byte[] convertToBytes(String avatarBase64) {
        if (avatarBase64 == null || avatarBase64.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(avatarBase64);
    }

}
