package br.com.accurate.achados.service.Impl;


import java.util.Optional;
import java.util.Objects;

import br.com.accurate.achados.model.enums.StatusItensPerdidos;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.accurate.achados.service.ItensPerdidosService;
import br.com.accurate.achados.model.entity.itensPerdidos;
import br.com.accurate.achados.model.repository.ItensPerdidosRepository;

public class ItensPerdidosServiceImpl implements ItensPerdidosService {

    @Autowired
    private ItensPerdidosRepository repository;

    @Autowired
    private itensPerdidos item;

    public ItensPerdidosServiceImpl(ItensPerdidosRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    @Transactional
    public itensPerdidos salvar(itensPerdidos Status) {
        validar(item);
       item.setStatus(StatusItensPerdidos.PERDIDO);
        return repository.save(item);
    }

    @Override
    @Transactional
    public void atualizarStatus(itensPerdidos item, StatusItensPerdidos Status) {
        item.setStatus(Status);
        atualizar(item);
    }


    @Override
    @Transactional
    public void deletar(itensPerdidos item) {
        repository.delete(item);
    }



    @Transactional
    public class RegraNegocioException extends RuntimeException {
        public RegraNegocioException(String mensagem) {
            super(mensagem);
        }
    }
    @Override
    @Transactional
    public void validar(itensPerdidos item) {

        if (item.getDescricao() == null ){
            throw new RegraNegocioException("Informe uma descrição valida!");
        }

        if (item.getData() == null ){
            throw new RegraNegocioException("Informe uma data valido!");
        }

        if (item.getLocal() == null ){
            throw new RegraNegocioException("Informe um local valido!");
        }

        if (item.getHora() == null ){
            throw new RegraNegocioException("Informe uma hora valida!");
        }

    }

    @Override
    @Transactional
    public itensPerdidos atualizar(itensPerdidos item) {
        Objects.requireNonNull(item.getId());
        validar(item);
        return repository.save(item);
    }

    @Override
    @Transactional
    public Optional<itensPerdidos> obterPorId(Long id) {
        return repository.findById(id);
    }
}
