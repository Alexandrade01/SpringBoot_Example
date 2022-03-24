package academy.devdojo.Myspringboot2essentials.mapper;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.request.AnimePostRequestBodyDTO;
import academy.devdojo.Myspringboot2essentials.request.AnimePutRequestBodyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBodyDTO animePostRequestBodyDTO);

    public abstract Anime toAnime(AnimePutRequestBodyDTO animePutRequestBodyDTO);
}