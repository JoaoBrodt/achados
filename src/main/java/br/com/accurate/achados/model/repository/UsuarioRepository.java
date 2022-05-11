package br.com.accurate.achados.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.accurate.achados.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select u from Usuario u where u.Email= :email")
	Optional<Usuario> findByEmail(String email);
}