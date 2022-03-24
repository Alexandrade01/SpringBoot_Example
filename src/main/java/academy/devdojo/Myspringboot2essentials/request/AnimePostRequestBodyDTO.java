package academy.devdojo.Myspringboot2essentials.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnimePostRequestBodyDTO {
	@NotEmpty(message= "Não pode ser vazio!")
	private String name;
}
