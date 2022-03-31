package academy.devdojo.Myspringboot2essentials.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.util.AnimeCreator;

@DataJpaTest
@DisplayName("Testes para AnimeRepository")
public class AnimeRepositoryTest {
	
	@Autowired
	private AnimeRepository animeRepository;

	@Test
	@DisplayName("Salvar crates quando o anime for criado com sucesso")
	void save_PersistAnime_WhenSuccessful() {
		// criamos um objeto anime teste
		Anime animeSavetoTest = AnimeCreator.createAnimeToBeSaved();

		// inserirmos no save do repositorio esse novo objeto
		Anime animeSave = this.animeRepository.save(animeSavetoTest);

		// com o resultado de retorno fazemos alguns testes
		Assertions.assertThat(animeSave).isNotNull();

		Assertions.assertThat(animeSave.getId()).isNotNull();

		Assertions.assertThat(animeSave.getName()).isEqualTo(animeSavetoTest.getName());
	}

	@Test
	@DisplayName("Salvar Updates quando o anime for atualizado com sucesso")
	void save_UpdateAnime_WhenSuccessful() {
		// criamos um objeto anime teste
		Anime animeSavetoTest = AnimeCreator.createAnimeToBeSaved();

		// inserirmos no save do repositorio esse novo objeto
		Anime animeSave = this.animeRepository.save(animeSavetoTest);

		animeSave.setName("Test Update");

		Anime animeUpdate = this.animeRepository.save(animeSave);

		// com o resultado de retorno fazemos alguns testes
		Assertions.assertThat(animeUpdate).isNotNull();

		Assertions.assertThat(animeUpdate.getId()).isNotNull();

		Assertions.assertThat(animeUpdate.getName()).isEqualTo(animeSavetoTest.getName());
	}

	@Test
	@DisplayName("Salvar Deletes quando o anime for deletado com sucesso")
	void save_DeleteAnime_WhenSuccessful() {
		// criamos um objeto anime teste
		Anime animeSavetoTest = AnimeCreator.createAnimeToBeSaved();

		// inserirmos no save do repositorio esse novo objeto
		Anime animeSave = this.animeRepository.save(animeSavetoTest);

		// deletemos esse novo anime salvo
		this.animeRepository.delete(animeSave);

		// criamos um container Optional
		Optional<Anime> animeOptional = animeRepository.findById(animeSave.getId());

		// com o resultado de retorno fazemos alguns testes
		// se o container tem algum valor o delete nao funcionou e o retorno não sera
		// nulo
		Assertions.assertThat(animeOptional.isPresent()).isFalse();
	}

	@Test
	@DisplayName("Find By Name retorna lista de animes quando volta com sucesso")
	void findByName_ReturnsListOfAnime_WhenSuccessful() {
		Anime animeToBeSaved =  AnimeCreator.createAnimeToBeSaved();

		Anime animeSaved = this.animeRepository.save(animeToBeSaved);

		String name = animeSaved.getName();

		List<Anime> animes = this.animeRepository.findByName(name);

		// teste ok quando não volta vazio
		Assertions.assertThat(animes).isNotEmpty();

		// teste ok quando contem o anime salvo previamente
		Assertions.assertThat(animes).contains(animeSaved);

	}

	@Test
	@DisplayName("Find By Name retorna vazio(empty) quando retorna com sucesso")
	void findByName_ReturnsEmptyList_WhenAnimeIsNotFound() {
		List<Anime> animes = this.animeRepository.findByName("Teste 01");

		// teste de retorno vazio
		Assertions.assertThat(animes).isEmpty();
	}

	@Test
	@DisplayName("Save throw ConstraintViolationException quando o nome é vazio")
	void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
		Anime anime = new Anime();
//	        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
//	                .isInstanceOf(ConstraintViolationException.class);
		
		//Teste de exceção
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
				.isThrownBy(() -> this.animeRepository.save(anime))
				.withMessageContaining("O nome do anime não pode ser vazio!");
	}

}
