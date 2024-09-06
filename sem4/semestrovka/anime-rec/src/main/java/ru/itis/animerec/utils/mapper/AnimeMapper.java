package ru.itis.animerec.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.itis.animerec.dto.AnimeDTO;
import ru.itis.animerec.entity.AnimeEntity;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

    AnimeDTO toDto(AnimeEntity anime);
}
