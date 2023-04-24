package br.com.wesleyschneider.springbootdebezium.model.new_database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario", unique = true)
    private int cdUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_social")
    private String nomeSocial;

    @Column(name = "cd_estudante")
    private int cdEstudante;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioEmail> emails;
}
