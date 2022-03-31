package academy.devdojo.Myspringboot2essentials.util;

import academy.devdojo.Myspringboot2essentials.request.AnimePostRequestBodyDTO;

public class AnimePostRequestBodyDTOCreator {
	
	public static AnimePostRequestBodyDTO animePostRequestBody() {
		
		return AnimePostRequestBodyDTO.builder()
				.name(AnimeCreator.createAnimeToBeSaved().getName())
				.build();
	}
}
