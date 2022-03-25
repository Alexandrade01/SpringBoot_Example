package academy.devdojo.Myspringboot2essentials.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {

	public static void main(String[] args) {

		ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8090/animes/4", Anime.class);
		log.info(entity);
		ResponseEntity<Anime> entityByIdName = new RestTemplate().getForEntity("http://localhost:8090/animes/{id}",
				Anime.class, 2);
		log.info(entityByIdName);

		Anime object = new RestTemplate().getForObject("http://localhost:8090/animes/{id}", Anime.class, 2);
		log.info(object);
	}

}
