package br.com.accurate.achados.api.resourse;


import br.com.accurate.achados.api.dto.AtualizaStatusDTO;
import br.com.accurate.achados.model.enums.StatusItensPerdidos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.accurate.achados.api.dto.ItensPerdidosDTO;
import br.com.accurate.achados.model.entity.itensPerdidos;
import br.com.accurate.achados.service.ItensPerdidosService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/itensperdidos")
@RequiredArgsConstructor

public class ItensPerdidosResource {
    private final ItensPerdidosService service;

    public class RegraNegocioException extends RuntimeException {

        public RegraNegocioException(String msg) {
            super(msg);
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody ItensPerdidosDTO dto) {
        try {
           itensPerdidos item = converter(dto);
            item = service.salvar(item);
            return new ResponseEntity(item, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar( @PathVariable("id") Long id, @RequestBody ItensPerdidosDTO dto) {
        return service.obterPorId(id).map(entity -> {
            try {
                itensPerdidos item = converter(dto);
                item.setId(entity.getId());
                service.atualizar(item);
                return ResponseEntity.ok(item);
            }catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() ->
                new ResponseEntity("Lancamento não encontrado na base de dados!.",HttpStatus.BAD_REQUEST));
    }

    @PutMapping("{id}/atualizarstatus")
    public ResponseEntity atualizarStatus( @PathVariable("id") Long id, @RequestBody AtualizaStatusDTO dto) {
        return service.obterPorId(id).map(entity -> {
            StatusItensPerdidos statusSelecionado =  StatusItensPerdidos.valueOf(dto.getStatus());

            if (statusSelecionado == null) {
                return ResponseEntity.badRequest().body("Não foi possivel atualizar o lancamento! Envie um status valido!");
            }
            try {
                entity.setStatus(statusSelecionado);
                service.atualizar(entity);
                return ResponseEntity.ok(entity);
            }catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet( () ->
                new ResponseEntity("Item não encontrado na base de dados!.",HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return service.obterPorId(id).map(entidade ->{
            service.deletar(entidade);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet( () ->
                new ResponseEntity("Item não encontrado!", HttpStatus.BAD_REQUEST));
    }



    public itensPerdidos converter(ItensPerdidosDTO dto) {
        itensPerdidos item = new itensPerdidos();
        item.setId(dto.getId());
        item.setDescricao(dto.getDescricao());
        item.setData(dto.getData());
        item.setHora(dto.getHora());
        item.setLocal(dto.getLocal());

        if (dto.getStatus() != null) {
            item.setStatus(StatusItensPerdidos.valueOf(dto.getStatus()));
        }

        return item;
    }

}