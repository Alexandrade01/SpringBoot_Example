package academy.devdojo.Myspringboot2essentials.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.Myspringboot2essentials.domain.Anime;

@Service
public class AnimeService {
	
	private final List<Anime> animes = new ArrayList<>
	(
		List.of(new Anime(1L, "Dragon Ball Z"), new Anime(2L, "Berserk"), new Anime(3L,"Boruto"))	
	);
	
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

	public Anime save(Anime animeBody) {
		//setando o id automaticamente do 4 ao 100.000
		animeBody.setId(ThreadLocalRandom.current().nextLong(4, 100000));
		animes.add(animeBody);
		return animeBody;
	}
	
	public void delete(long id) {
		Anime animeReturn = findById(id);
		animes.remove(animeReturn);
		
	}

	public void replace(Anime animeBody) {
		
		animes.removeIf(anime -> anime.getId().equals(animeBody.getId()));
		
		animes.add(animeBody);
	}
}