package academy.devdojo.Myspringboot2essentials.util;

import academy.devdojo.Myspringboot2essentials.request.AnimePutRequestBodyDTO;

public class AnimePutRequestBodyDTOCreator {

	public static AnimePutRequestBodyDTO animePostRequestBody() {

		return AnimePutRequestBodyDTO.builder()
				.name(AnimeCreator.createAnimeToBeSaved()
				.getName())
				.id(AnimeCreator.createAnimeToBeSaved()
				.getId())
				.build();
	}
}
