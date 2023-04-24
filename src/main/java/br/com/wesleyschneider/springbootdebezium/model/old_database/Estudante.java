package br.com.wesleyschneider.springbootdebezium.model.old_database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estudante {
    private int cdEstudante;
    private String nome;
    private String nomeSocial;
    private String email;

    public Estudante(int cdEstudante, String nome, String nomeSocial, String email) {
        this.cdEstudante = cdEstudante;
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.email = email;
    }
}
