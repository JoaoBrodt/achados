package br.com.accurate.achados.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

	private String Nome;
	private String Email;
	private String Senha;
	
}