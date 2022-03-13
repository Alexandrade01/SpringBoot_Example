package academy.devdojo.Myspringboot2essentials.repository;

import java.util.List;

import academy.devdojo.Myspringboot2essentials.domain.Anime;

public interface AnimeRepository {
    List<Anime> listAll();
}