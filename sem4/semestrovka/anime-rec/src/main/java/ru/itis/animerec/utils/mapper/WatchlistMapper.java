package ru.itis.animerec.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.itis.animerec.dto.WatchlistDTO;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.entity.WatchlistEntity;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {

    @Mapping(target = "posterBase64", expression = "java(convertToBase64(watchlistEntity.getPoster()))")
    @Mapping(target = "userId", expression = "java(watchlistEntity.getUser().getId())")
    WatchlistDTO toDto(WatchlistEntity watchlistEntity);

    @Mapping(target = "poster", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "userIdToUserEntity")
    WatchlistEntity toEntity(WatchlistDTO watchlistDTO);

    default String convertToBase64(byte[] poster) {
        return Base64.getEncoder().encodeToString(poster);
    }

    @Named("userIdToUserEntity")
    default UserEntity userIdToUserEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return userEntity;
    }
}


