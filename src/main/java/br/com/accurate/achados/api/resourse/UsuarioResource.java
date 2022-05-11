package br.com.accurate.achados.api.resourse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.accurate.achados.api.dto.UsuarioDTO;
import br.com.accurate.achados.model.entity.Usuario;
import br.com.accurate.achados.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor

public class UsuarioResource {
	private UsuarioService service;

	
	public class ErroAutenticacao extends RuntimeException {

		public ErroAutenticacao(String mensagem) {
			super(mensagem);	
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {

		String Email = dto.getEmail();
		String Senha = dto.getSenha();
		
		try {
			  Usuario usuarioAutenticado = service.autenticar(Email,Senha);
			  return new ResponseEntity(usuarioAutenticado,HttpStatus.CREATED);
		  } catch(ErroAutenticacao e) {
			  return ResponseEntity.badRequest().body(e.getMessage());

		  }

	}
	
	public class RegraNegocioException extends RuntimeException {
		public RegraNegocioException(String msg) {
			super(msg);
		}
		
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = Usuario.builder().Nome(dto.getNome()).Email(dto.getEmail()).Senha(dto.getSenha()).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}