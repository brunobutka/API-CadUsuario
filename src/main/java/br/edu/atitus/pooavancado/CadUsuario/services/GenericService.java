package br.edu.atitus.pooavancado.CadUsuario.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.pooavancado.CadUsuario.entities.GenericEntity;
import br.edu.atitus.pooavancado.CadUsuario.entities.Usuario;
import br.edu.atitus.pooavancado.CadUsuario.repositories.GenericRepository;

public interface GenericService<TEntidade extends GenericEntity, TRepository extends GenericRepository<TEntidade>> {

	TRepository getRepository();
	
	default TEntidade save(TEntidade objeto) throws Exception{
		if(getRepository().existsByNomeAndIdNot(objeto.getNome(), objeto.getId()))
			throw new Exception("Já existe cadastro com este nome");
		
		return this.getRepository().save(objeto);
	}
	
	default TEntidade findById(Long id) throws Exception{
		var objeto = this.getRepository().findById(id);
		
		if(objeto.isEmpty())
			throw new Exception("Não existe cadastro com o ID: " + id);
		
		return objeto.get();
	}
	
	default Page<TEntidade> findByNome(String nome, Pageable pageable) throws Exception{
		return this.getRepository().findByNomeContainingIgnoreCase(nome, pageable);
	}
	
	default void deleteById(Long id) throws Exception{
		if(!getRepository().existsById(id))
			throw new Exception("Não existe cadastro com o ID: " + id);
			
		getRepository().deleteById(id);
	}
	
}
