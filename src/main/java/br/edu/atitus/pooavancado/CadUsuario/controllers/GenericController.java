package br.edu.atitus.pooavancado.CadUsuario.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.atitus.pooavancado.CadUsuario.entities.GenericEntity;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;

public abstract class GenericController<TEntidade extends GenericEntity> {

	abstract GenericService<TEntidade> getService();
	
	@GetMapping
	public ResponseEntity<Object> getRegistros(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable paginacao, 
											  @RequestParam(required = false) String nome, 
											  @RequestParam(required = false) String email){
		Page<TEntidade> lista;
		
		try {
			lista = getService().findByNome(nome, paginacao);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getRegistroById(@PathVariable long id){
		TEntidade objeto;
		
		try {
			objeto = this.getService().findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(objeto);
	}
	
	@PostMapping
	public ResponseEntity<Object> postRegistro(@RequestBody TEntidade objeto) {
		try {
			getService().save(objeto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putRegistro(@RequestBody TEntidade objeto, @PathVariable long id){
		objeto.setId(id);
		
		try {
			getService().save(objeto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(objeto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRegistro(@PathVariable long id){
		try {
			getService().deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
	}
	
	
}
