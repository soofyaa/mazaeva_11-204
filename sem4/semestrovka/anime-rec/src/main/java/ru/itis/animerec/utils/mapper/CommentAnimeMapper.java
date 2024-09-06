package ru.itis.animerec.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.animerec.dto.CommentDTO;
import ru.itis.animerec.entity.CommentAnimeEntity;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface CommentAnimeMapper {
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "userAvatarBase64", expression = "java(convertToBase64(comment.getUser().getAvatar()))")
    CommentDTO toDTO(CommentAnimeEntity comment);

    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "user.avatar", expression = "java(convertToByteArray(commentDTO.getUserAvatarBase64()))")
    CommentAnimeEntity toEntity(CommentDTO commentDTO);

    default String convertToBase64(byte[] poster) {
        if (poster == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(poster);
    }

    default byte[] convertToByteArray(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(base64String);
    }
}
