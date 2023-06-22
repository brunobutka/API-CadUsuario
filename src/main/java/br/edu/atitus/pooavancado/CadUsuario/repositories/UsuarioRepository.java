package br.edu.atitus.pooavancado.CadUsuario.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.atitus.pooavancado.CadUsuario.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario>{
	
	@Query(value = "update tb_usuario set status = NOT status where id = :id", nativeQuery = true)
	@Modifying
	void alteraStatus(@Param(value = "id") long id);

	boolean existsByEmailAndIdNot(String email, long id);

	Optional<Usuario> findByEmail(String email);
}
