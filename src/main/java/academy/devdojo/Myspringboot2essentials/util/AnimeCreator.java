package academy.devdojo.Myspringboot2essentials.util;

import academy.devdojo.Myspringboot2essentials.domain.Anime;

public class AnimeCreator {

	public static Anime createAnimeToBeSaved() {

		return Anime.builder().name("TEST ANIME").build();
	}

	public static Anime createValidAnime() {

		return Anime.builder().id(1L).name("TEST ANIME").build();
	}

	public static Anime createValidUpdateAnime() {

		return Anime.builder().id(1L).name("TEST ANIME 02").build();
	}
}
