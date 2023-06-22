package br.edu.atitus.pooavancado.CadUsuario.services;

import br.edu.atitus.pooavancado.CadUsuario.entities.GenericEntity;
import br.edu.atitus.pooavancado.CadUsuario.repositories.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<TEntidade extends GenericEntity> {

	GenericRepository<TEntidade> getRepository();

	default void validadeSave(TEntidade objeto) throws Exception {
		if(objeto.getNome() == null || objeto.getNome().isEmpty())
			throw new Exception("É necessário informar um nome válido");
		if(getRepository().existsByNomeAndIdNot(objeto.getNome(), objeto.getId()))
			throw new Exception("Já existe cadastro com este nome");
	}

	default TEntidade save(TEntidade objeto) throws Exception{
		this.validadeSave(objeto);
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
