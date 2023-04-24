package br.com.wesleyschneider.springbootdebezium.repository;

import br.com.wesleyschneider.springbootdebezium.model.new_database.UsuarioEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioEmailRepository extends JpaRepository<UsuarioEmail, Integer> {
}
