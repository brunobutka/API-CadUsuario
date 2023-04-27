package br.edu.atitus.pooavancado.CadUsuario.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.atitus.pooavancado.CadUsuario.entities.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	Page<Departamento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	boolean existsByNomeAndIdNot(String nome, long id);

}
