package academy.devdojo.Myspringboot2essentials.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Anime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //jsonproperty é uma mascara para apresentar na saida
   // @JsonProperty("Nome do anime")
	@NotEmpty(message = "O nome do anime não pode ser vazio!")
    private String name;

}