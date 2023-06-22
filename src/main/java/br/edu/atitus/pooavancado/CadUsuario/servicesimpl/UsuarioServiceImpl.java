package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import br.edu.atitus.pooavancado.CadUsuario.entities.Usuario;
import br.edu.atitus.pooavancado.CadUsuario.repositories.UsuarioRepository;
import br.edu.atitus.pooavancado.CadUsuario.services.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

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

	@Override
	public void validadeSave(Usuario objeto) throws Exception {
		UsuarioService.super.validadeSave(objeto);
		if(objeto.getEmail() == null || objeto.getEmail().isEmpty())
			throw new Exception("É necessário informar um e-mail válido");
		if(getRepository().existsByEmailAndIdNot(objeto.getEmail(), objeto.getId()))
			throw new Exception("Já existe cadastro com este e-mail");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

		return usuario;
	}
}
