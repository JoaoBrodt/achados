package br.com.accurate.achados.api.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder

public class ItensPerdidosDTO {

    private Long Id;
    private String Descricao;
    private Integer Data;
    private Integer Hora;
    private String Local;
    private String status;

}


