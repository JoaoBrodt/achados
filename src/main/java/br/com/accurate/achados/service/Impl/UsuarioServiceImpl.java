package br.com.accurate.achados.service.Impl;

import java.util.Optional;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.accurate.achados.model.entity.Usuario;
import br.com.accurate.achados.model.repository.UsuarioRepository;
import br.com.accurate.achados.service.UsuarioService;


public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private Usuario usuario;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	public class ErroAutenticacao extends RuntimeException {

		public ErroAutenticacao(String mensagem) {
			super(mensagem);
		}
	}

 @Override
 @Transactional
	public Usuario autenticar(String Email, String Senha) {

		Optional<Usuario> usuario = repository.findByEmail(Email);


		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado!");
		}

		if(!usuario.get().getSenha().equals(Senha) ){
			throw new ErroAutenticacao("Senha inválida!");
		}
		return usuario.get();
	}
	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	public class RegraNegocioException extends RuntimeException {
		public RegraNegocioException(String msg) {
			super(msg);
		}
	}
	
	@Override
	@Transactional
	@Autowired
	public void validarEmail(String Email) {
		boolean existe = repository.findByEmail(Email).isPresent();
		if (existe) {
			throw new RegraNegocioException("Já existe um usuario cadastrado com este email");
		}
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}




	

}