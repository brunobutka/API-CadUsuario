package br.edu.atitus.pooavancado.CadUsuario.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.entities.Produto;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;
import br.edu.atitus.pooavancado.CadUsuario.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController extends GenericController<Produto>{
		
	final private ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@Override
	GenericService<Produto> getService() {
		return produtoService;
	}
	
}
