package academy.devdojo.Myspringboot2essentials.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.Myspringboot2essentials.domain.Anime;

@Service
public class AnimeService {
	private final List<Anime> animes = List.of(new Anime(1L, "Dragon Ball Z"), new Anime(2L, "Berserk"), new Anime(3L,"Boruto"));
    // private final AnimeRepository animeRepository;
    public List<Anime> listAll() {
        return animes;
    }

	public Anime findById(long id) {
		
		return animes.stream()
				.filter(anime -> anime.getId().equals(id))
				.findFirst()
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Anime not found!"));
	}
}