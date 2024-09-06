package ru.itis.animerec.animeimport.jsonparser;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.itis.animerec.animeimport.jsonmodel.Creator;
import ru.itis.animerec.animeimport.jsonmodel.Genre;
import ru.itis.animerec.animeimport.jsonmodel.Studio;
import ru.itis.animerec.entity.CreatorEntity;
import ru.itis.animerec.entity.GenreEntity;
import ru.itis.animerec.entity.StudioEntity;

@Mapper(componentModel = "spring")
public interface AnimeJsonMapper {

    @Mapping(source = "title", target = "title")
    CreatorEntity toCreatorEntity(Creator creator);

    @Mapping(source = "title", target = "title")
    GenreEntity toGenreEntity(Genre genre);

    @Mapping(source = "title", target = "title")
    StudioEntity toStudioEntity(Studio studio);
}