package academy.devdojo.Myspringboot2essentials.client;

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

		// ResponseEntity<Anime> entity = new
		// RestTemplate().getForEntity("http://localhost:8090/animes/4", Anime.class);
		// log.info(entity);
		// ResponseEntity<Anime> entityByIdName = new
		// RestTemplate().getForEntity("http://localhost:8090/animes/{id}",
		// Anime.class, 2);
		// log.info(entityByIdName);
		//
		// Anime object = new
		// RestTemplate().getForObject("http://localhost:8090/animes/{id}", Anime.class,
		// 2);
		// log.info(object);

		// Anime[] animesArray = new
		// RestTemplate().getForObject("http://localhost:8090/animes/all/",
		// Anime[].class);
		// log.info(Arrays.toString(animesArray));

		// Utiliza o exchange quando precisamos de um retorno mais parametrizado
		// conforme o projeto

		/*
		 * ResponseEntity<List<Anime>> exchangeResponse = new
		 * RestTemplate().exchange("http://localhost:8090/animes/all", HttpMethod.GET,
		 * null, new ParameterizedTypeReference<>() { });
		 * log.info(exchangeResponse.getBody());
		 */

		// POST

		// Post utilizando postForObject
		// Anime MortalKombat = Anime.builder().name("Mortal Kombat").build();
		// Anime ReturnMortalKombat = new
		// RestTemplate().postForObject("http://localhost:8090/animes/", MortalKombat,
		// Anime.class);
		// log.info("saved anime {}", ReturnMortalKombat);

		/*
		 * Anime Onimusha = Anime.builder().name("Onimusha").build();
		 * 
		 * ResponseEntity<Anime> responseOnimusha = new
		 * RestTemplate().exchange("http://localhost:8090/animes/", HttpMethod.POST, new
		 * HttpEntity<>(Onimusha, createJsonHeader()), Anime.class);
		 * 
		 * log.info("saved anime {}", responseOnimusha);
		 */

		// PUT

		// fazendo update de onimusha

		//ResponseEntity<Anime> entityOnimusha = new RestTemplate()
		//		.getForEntity("http://localhost:8090/animes/find/{name}", Anime.class, "Onimusha");
        //
		//log.info(entityOnimusha.getBody());
        //
		//Anime newAnime = entityOnimusha.getBody();
		//newAnime.setName("Onimusha Demons 3");
        //
		//ResponseEntity<Void> updateAnime = new RestTemplate().exchange("http://localhost:8090/animes/", HttpMethod.PUT,
		//		new HttpEntity<>(newAnime, createJsonHeader()), Void.class);
		//log.info(updateAnime);
        //
		// DELETE
		
		ResponseEntity<Anime> entityOnimusha = new RestTemplate()
		.getForEntity("http://localhost:8090/animes/find/{name}", Anime.class, "Onimusha Demons 3");
		
		ResponseEntity<Void> onimusha3Deleted = new RestTemplate().exchange("http://localhost:8090/animes/{Id}",
				HttpMethod.DELETE, null, Void.class, entityOnimusha.getBody().getId());

		//log.info(onimusha3Deleted);

	}

	// quando criamos um httpEntity<> precisamos de um header e um body, aqui
	// criamos um header automatico
	private static HttpHeaders createJsonHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

}
