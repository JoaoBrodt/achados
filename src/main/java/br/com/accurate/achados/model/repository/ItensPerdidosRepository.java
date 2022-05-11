package br.com.accurate.achados.model.repository;
import java.util.Optional;

import br.com.accurate.achados.model.entity.itensPerdidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface ItensPerdidosRepository extends JpaRepository<itensPerdidos, Long> {

    @Query(value = "select u from itensPerdidos u where u.Id= :id")
    Optional<itensPerdidos> findById(Long id);

}
