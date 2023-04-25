package br.com.wesleyschneider.springbootdebezium.service;

import br.com.wesleyschneider.springbootdebezium.model.new_database.Usuario;
import br.com.wesleyschneider.springbootdebezium.model.new_database.UsuarioEmail;
import br.com.wesleyschneider.springbootdebezium.model.old_database.Estudante;
import br.com.wesleyschneider.springbootdebezium.repository.UsuarioEmailRepository;
import br.com.wesleyschneider.springbootdebezium.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "EstudanteService")
public class EstudanteService extends BaseService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioEmailRepository usuarioEmailRepository;

    @Transactional
    public void create(Map<String, Object> payload) {
        var estudante = convertMapToEstudante(payload);

        var usuario = new Usuario();

        updateUsuario(usuario, estudante);
    }

    @Transactional
    public void update(Map<String, Object> payload) {
        var estudante = convertMapToEstudante(payload);

        var usuarioOptional = usuarioRepository.findByCdEstudante(estudante.getCdEstudante());

        if (usuarioOptional.isEmpty()) {
            return;
        }

        var usuario = usuarioOptional.orElseGet(Usuario::new);

        updateUsuario(usuario, estudante);
    }

    @Transactional
    public void delete(Map<String, Object> payload) {

    }

    private void updateUsuario(Usuario usuario, Estudante estudante) {
        usuario.setNome(estudante.getNome());
        usuario.setNomeSocial(estudante.getNomeSocial());
        usuario.setCdEstudante(estudante.getCdEstudante());

        usuarioRepository.save(usuario);

        var estudanteEmail = estudante.getEmail();
        UsuarioEmail email = new UsuarioEmail();

        var usuarioEmails = usuario.getEmails();

        if (usuarioEmails != null){
            var emailOptional = usuarioEmails.stream().filter(e -> e.getEmail().equals(estudanteEmail)).findFirst();

            if (emailOptional.isPresent()) {
                email = emailOptional.get();
            }
        }

        email.setEmail(estudanteEmail);
        email.setUsuario(usuario);

        usuarioEmailRepository.save(email);
    }

    private Estudante convertMapToEstudante(Map<String, Object> payload) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(payload, Estudante.class);
    }
}
