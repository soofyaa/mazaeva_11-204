package ru.itis.animerec.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.itis.animerec.dto.MessageDTO;
import ru.itis.animerec.entity.MessageEntity;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(target = "userAvatarBase64", expression = "java(encodeAvatar(messageEntity.getUser() != null ? messageEntity.getUser().getAvatar() : null))")
    MessageDTO toDTO(MessageEntity messageEntity);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "username", target = "user.username")
    @Mapping(target = "user.avatar", expression = "java(decodeAvatar(messageDTO.getUserAvatarBase64()))")
    MessageEntity toEntity(MessageDTO messageDTO);

    default String encodeAvatar(byte[] avatar) {
        return avatar != null ? Base64.getEncoder().encodeToString(avatar) : null;
    }

    default byte[] decodeAvatar(String avatarBase64) {
        return avatarBase64 != null ? Base64.getDecoder().decode(avatarBase64) : null;
    }
}
