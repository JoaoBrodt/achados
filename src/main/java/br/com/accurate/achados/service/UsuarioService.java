package br.com.accurate.achados.service;

import java.util.Optional;

import br.com.accurate.achados.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void  validarEmail(String email);
	
	Optional<Usuario> obterPorId(Long id);


}

