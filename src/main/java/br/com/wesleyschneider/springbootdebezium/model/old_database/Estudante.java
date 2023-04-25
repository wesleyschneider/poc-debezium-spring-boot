package br.com.wesleyschneider.springbootdebezium.model.old_database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estudante {
    @JsonProperty("cd_estudante")
    private int cdEstudante;

    private String nome;

    @JsonProperty("nome_social")
    private String nomeSocial;

    private String email;
}
