package academy.devdojo.Myspringboot2essentials.service;

import java.util.List;

import org.springframework.stereotype.Service;

import academy.devdojo.Myspringboot2essentials.domain.Anime;

@Service
public class AnimeService {
    // private final AnimeRepository animeRepository;
    public List<Anime> listAll() {
        return List.of(new Anime(1L, "Boku No Hero"), new Anime(2L, "Berserk"));
    }
}