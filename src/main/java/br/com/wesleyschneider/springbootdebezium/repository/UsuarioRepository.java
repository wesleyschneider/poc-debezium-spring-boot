package br.com.wesleyschneider.springbootdebezium.repository;

import br.com.wesleyschneider.springbootdebezium.model.new_database.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCdEstudante(int cdEstudante);
}
