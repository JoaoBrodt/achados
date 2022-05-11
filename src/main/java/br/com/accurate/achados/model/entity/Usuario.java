package br.com.accurate.achados.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table( name = "Usuario" )
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "Nome")
	private String Nome;

	@Column(name = "Email")
	private String Email;
	
	@Column(name = "Senha")
	private String Senha;
}