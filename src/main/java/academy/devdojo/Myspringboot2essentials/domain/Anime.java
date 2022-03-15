package academy.devdojo.Myspringboot2essentials.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Anime {
    private Long id;
    
    //jsonproperty é uma mascara para apresentar na saida
    //@JsonProperty("Nome do anime")
    private String name;

}