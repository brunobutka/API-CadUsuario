package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.atitus.pooavancado.CadUsuario.entities.Usuario;
import br.edu.atitus.pooavancado.CadUsuario.repositories.UsuarioRepository;
import br.edu.atitus.pooavancado.CadUsuario.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UsuarioRepository getRepository() {
		return usuarioRepository;
	}

	@Override
	@Transactional
	public void alteraStatus(long id) throws Exception {
		if(!usuarioRepository.existsById(id))
			throw new Exception("Não existe usuário com o ID: " + id);
			
		usuarioRepository.alteraStatus(id);
	}	
	
}
