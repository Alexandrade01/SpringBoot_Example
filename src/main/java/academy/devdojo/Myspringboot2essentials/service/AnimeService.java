package academy.devdojo.Myspringboot2essentials.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.repository.AnimeRepository;
import academy.devdojo.Myspringboot2essentials.request.AnimePostRequestBodyDTO;
import academy.devdojo.Myspringboot2essentials.request.AnimePutRequestBodyDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {
	
	
	
	private final AnimeRepository animeRepository;
	
    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

	public Anime findByIdOrThrowBadRequestException(long id) {
		
		return animeRepository
				.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Anime not found!"));
	}

	public Anime save(AnimePostRequestBodyDTO animePostRequestBodyDTO) {
		Anime anime = Anime.builder().name(animePostRequestBodyDTO.getName()).build();
		animeRepository.save(anime);
		return anime;
	}
	
	public void delete(long id) {

		animeRepository.delete(findByIdOrThrowBadRequestException(id));
		
	}

	public void replace(AnimePutRequestBodyDTO animePutRequestBodyDTO) {
		// pegamos o id do antigo
		Anime animeSalvo = findByIdOrThrowBadRequestException(animePutRequestBodyDTO.getId());
		//com o id antigo setamos o novo nome de um modo mais seguro
		Anime newAnime = Anime.builder().name(animePutRequestBodyDTO.getName()).id(animeSalvo.getId()).build();
		
		animeRepository.save(newAnime);
	}
}