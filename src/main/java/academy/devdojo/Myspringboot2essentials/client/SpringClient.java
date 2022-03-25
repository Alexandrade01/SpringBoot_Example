package academy.devdojo.Myspringboot2essentials.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {

	public static void main(String[] args) {

		/*
		 * ResponseEntity<Anime> entity = new
		 * RestTemplate().getForEntity("http://localhost:8090/animes/4", Anime.class);
		 * log.info(entity); ResponseEntity<Anime> entityByIdName = new
		 * RestTemplate().getForEntity("http://localhost:8090/animes/{id}", Anime.class,
		 * 2); log.info(entityByIdName);
		 * 
		 * Anime object = new
		 * RestTemplate().getForObject("http://localhost:8090/animes/{id}", Anime.class,
		 * 2); log.info(object);
		 * 
		 * 
		 * Anime[] animesArray = new
		 * RestTemplate().getForObject("http://localhost:8090/animes/all/",
		 * Anime[].class); log.info(Arrays.toString(animesArray));
		 */

		// Utiliza o exchange quando precisamos de um retorno mais parametrizado
		// conforme o projeto

		ResponseEntity<List<Anime>> exchangeResponse = new RestTemplate().exchange("http://localhost:8090/animes/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<>() {
				});
		log.info(exchangeResponse.getBody());

		// Post utilizando postForObject
		// Anime MortalKombat = Anime.builder().name("Mortal Kombat").build();
		// Anime ReturnMortalKombat = new
		// RestTemplate().postForObject("http://localhost:8090/animes/", MortalKombat,
		// Anime.class);
		// log.info("saved anime {}", ReturnMortalKombat);

		Anime Onimusha = Anime.builder().name("Onimusha").build();

		ResponseEntity<Anime> responseOnimusha = new RestTemplate().exchange("http://localhost:8090/animes/",
				HttpMethod.POST, new HttpEntity<>(Onimusha, createJsonHeader()), Anime.class);
		
		log.info("saved anime {}", responseOnimusha);
	}

	// quando criamos um httpEntity<> precisamos de um header e um body, aqui
	// criamos um header automatico
	private static HttpHeaders createJsonHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

}
