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

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.service.AnimeService;
import academy.devdojo.Myspringboot2essentials.util.AnimeCreator;
import academy.devdojo.Myspringboot2essentials.util.DateUtil;


//implicita que usaremos o spring com o jUnit
@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {
	
	//utiliza inject quando apenas a classe em si sera testada
	@InjectMocks
	private AnimeController animeControllerInjectMock;
	
	//utiliza mock quando todas as dependencias serao testadas
	@Mock
	private AnimeService animeServiceMock;
	
	@Mock
	private DateUtil dateUtilMock;
	
	//Antes de cada teste executara essa anotação
	@BeforeEach
	void setUp() {
		//criamos um page com um objeto anime
		PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
		// todo teste executara um listall independente dos argumentos e retornara a viariavel animePage 
		BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any())).thenReturn(animePage);
		
		//um validador de horas
		String dateTime = DateUtil.dateCreator();
		BDDMockito.when(dateUtilMock.formatLocalDateTimeToDatabaseStyle(ArgumentMatchers.any())).thenReturn(dateTime);
		
	}
	
	 @Test
	    @DisplayName("Lista retorna uma lista de anime dentro do objeto de pagina quando se tem sucesso")
	    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){
	        String expectedName = AnimeCreator.createValidAnime().getName();
	        
	        //animePageReturn voltara com o animePage de BeforeEach pois sera forçado pelo BeforeEach
	        Page<Anime> animePageReturn = animeControllerInjectMock.list(null).getBody();
	        
	        //teste de nao nulo
	        Assertions.assertThat(animePageReturn).isNotNull();
	        // teste de nao vazio e tem pelo menos 1
	        Assertions.assertThat(animePageReturn.toList())
	                .isNotEmpty()
	                .hasSize(1);
	        //teste de nome forçando que tenha o mesmo nome que o esperado
	        Assertions.assertThat(animePageReturn.toList().get(0).getName()).isEqualTo(expectedName);
	    }
	
	
}
