package academy.devdojo.Myspringboot2essentials.request;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimePutRequestBodyDTO {
	
	private Long id;
	// nao permite uma atualizacao vazia ou nula
	@NotEmpty(message = "value null not permited")
	private String name;
}
