package br.com.wesleyschneider.springbootdebezium.model.new_database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_email")
public class UsuarioEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_email", unique = true)
    private int cdEmail;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;
}
