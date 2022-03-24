package academy.devdojo.Myspringboot2essentials.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.exception.BadRequestException;
import academy.devdojo.Myspringboot2essentials.mapper.AnimeMapper;
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
				.orElseThrow(() -> new BadRequestException("Anime not found!"));
	}
	
	/*@Transactional(rollbackFor = Exception.class)
	public Anime save(AnimePostRequestBodyDTO animePostRequestBodyDTO) throws Exception {
		Anime anime = Anime.builder().name(animePostRequestBodyDTO.getName()).build();
		animeRepository.save(anime);
		if(true) {
			throw new Exception("Exception Test!");
		}
		return anime;
	} */
	
	@Transactional
	public Anime save(AnimePostRequestBodyDTO animePostRequestBodyDTO) {
		Anime anime = animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBodyDTO));
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
	
	public List<Anime> findByName(String name) {
		
		return animeRepository.findByName(name);
	}
}