package academy.devdojo.Myspringboot2essentials.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import academy.devdojo.Myspringboot2essentials.util;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.request.AnimePostRequestBodyDTO;
import academy.devdojo.Myspringboot2essentials.service.AnimeService;
import academy.devdojo.Myspringboot2essentials.util.AnimeCreator;
import academy.devdojo.Myspringboot2essentials.util.AnimePostRequestBodyDTOCreator;
import academy.devdojo.Myspringboot2essentials.util.DateUtil;

//implicita que usaremos o spring com o jUnit
@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

	// utiliza inject quando apenas a classe em si sera testada
	@InjectMocks
	private AnimeController animeControllerInjectMock;

	// utiliza mock quando todas as dependencias serao testadas
	@Mock
	private AnimeService animeServiceMock;

	@Mock
	private DateUtil dateUtilMock;

	// Antes de cada teste executara essa anotação
	// setamos um método que simulara todos os services como mockados
	@BeforeEach
	void setUp() {
		// criamos um page com um objeto anime
		PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
		// todo teste executara um listall independente dos argumentos e retornara a
		// variavel animePage
		BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any())).thenReturn(animePage);

		// um validador de horas para DateUtil
		String dateTime = DateUtil.dateCreator();
		BDDMockito.when(dateUtilMock.formatLocalDateTimeToDatabaseStyle(ArgumentMatchers.any())).thenReturn(dateTime);

		BDDMockito.when(animeServiceMock.listAllNonPageable()).thenReturn(List.of(AnimeCreator.createValidAnime()));

		BDDMockito.when(animeServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
				.thenReturn(AnimeCreator.createValidAnime());
		
		BDDMockito.when(animeServiceMock.findByNameFirst(ArgumentMatchers.anyString()))
		.thenReturn(AnimeCreator.createValidAnime());
		
		BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
		.thenReturn(List.of(AnimeCreator.createValidAnime()));
	}

	@Test
	@DisplayName("Testes que retorna uma list de animes no page object quando bem sucedido")
	void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
		String expectedName = AnimeCreator.createValidAnime().getName();

		// animePageReturn voltara com o animePage de BeforeEach pois sera forçado pelo
		// BeforeEach
		Page<Anime> animePageReturn = animeControllerInjectMock.list(null).getBody();

		// teste de nao nulo
		Assertions.assertThat(animePageReturn).isNotNull();
		// teste de nao vazio e tem pelo menos 1
		Assertions.assertThat(animePageReturn.toList()).isNotEmpty().hasSize(1);
		// teste de nome forçando que tenha o mesmo nome que o esperado
		Assertions.assertThat(animePageReturn.toList().get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("Testes todos os animes quando bem sucedido")
	void listAllNonPageable_ReturnsListOfAnimesnonPageable_WhenSuccessful() {
		String expectedName = AnimeCreator.createValidAnime().getName();

		List<Anime> animeList = animeControllerInjectMock.listAllNonPageable().getBody();

		Assertions.assertThat(animeList).isNotNull();

		Assertions.assertThat(animeList).isNotEmpty();

		Assertions.assertThat(animeList).hasSize(1);

		// teste de nome forçando que tenha o mesmo nome que o esperado
		Assertions.assertThat(animeList.get(0).getName()).isEqualTo(expectedName);
	}

	@Test
	@DisplayName("Teste que retorna um anime via findById quando bem sucedido")
	void findById_ReturnsAnime_WhenSuccessful() {

		Anime expectedAnime = AnimeCreator.createValidAnime();
		Anime animeReturn = animeControllerInjectMock.findById(expectedAnime.getId()).getBody();

		Assertions.assertThat(animeReturn).isNotNull();

		Assertions.assertThat(animeReturn.getName()).isEqualTo(expectedAnime.getName());
	}

	@Test
	@DisplayName("Teste que retorna um anime via findByName quando bem sucedido")
	void findByName_ReturnsAnime_WhenSuccessful() {

		Anime expectedAnime = AnimeCreator.createValidAnime();
		Anime animeReturn = animeControllerInjectMock.findByName(expectedAnime.getName()).getBody();

		Assertions.assertThat(animeReturn).isNotNull();

		Assertions.assertThat(animeReturn.getName()).isEqualTo(expectedAnime.getName());
	}

	@Test
	@DisplayName("Teste que retorna uma lista de anime via find quando bem sucedido")
	void find_ReturnsAnime_WhenSuccessful() {

		String animeName = AnimeCreator.createValidAnime().getName();
		List<Anime> animeReturn = animeControllerInjectMock.find(animeName).getBody();

		Assertions.assertThat(animeReturn).isNotNull();
		
		Assertions.assertThat(animeReturn).isNotEmpty();
		
		Assertions.assertThat(animeReturn).hasSize(1);

		Assertions.assertThat(animeReturn.get(0).getName()).isEqualTo(animeName);
	}
	
	@Test
	@DisplayName("Teste que retorna um Anime salvo quando bem sucedido")
	void save_ReturnsAnime_WhenSuccessful() {

		AnimePostRequestBodyDTO animeRequest = AnimePostRequestBodyDTOCreator.animePostRequestBody();
		Anime animeReturn = animeControllerInjectMock.save(animeRequest)
													.getBody();

		Assertions.assertThat(animeReturn).isNotNull();

		Assertions.assertThat(animeReturn.getName()).isEqualTo(animeRequest.getName());
	
	}

}
