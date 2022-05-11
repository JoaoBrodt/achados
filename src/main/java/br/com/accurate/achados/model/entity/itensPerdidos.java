package br.com.accurate.achados.model.entity;

import br.com.accurate.achados.model.enums.StatusItensPerdidos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "itensperdidos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class itensPerdidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long Id;
	
	@Column(name = "Descricao")
	private String Descricao;
	
	@Column(name = "Local")
	private String Local;
	
	@Column(name = "Data")
	private Integer Data;
	
	@Column(name = "Hora")
	private Integer Hora;
	
	@Column(name = "Status")
	@Enumerated(value = EnumType.STRING)
	private StatusItensPerdidos Status;
	
	

	
	
}