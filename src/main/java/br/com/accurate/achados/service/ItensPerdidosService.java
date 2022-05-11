package br.com.accurate.achados.service;

import br.com.accurate.achados.model.entity.itensPerdidos;
import br.com.accurate.achados.model.enums.StatusItensPerdidos;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ItensPerdidosService {

        itensPerdidos salvar(itensPerdidos item);

          itensPerdidos atualizar(itensPerdidos item);

         void deletar(itensPerdidos item);

         Optional<itensPerdidos> obterPorId(Long id);

        void atualizarStatus(itensPerdidos item, StatusItensPerdidos status);

        void validar(itensPerdidos item);

    }

