package com.fatec.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.dto.UsuarioDto;
import com.fatec.entities.Usuario;
import com.fatec.repository.UsuarioRepository;
import com.fatec.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder encoder;

	@Override
	public Optional<Usuario> buscarPorUsername(String username) {
		return this.usuarioRepository.findByUsername(username);
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public boolean existsByUsername(String username) {
		return this.usuarioRepository.existsByUsername(username);
		
	}

	@Override
	public Usuario saveUsuario(UsuarioDto usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		Usuario user = new Usuario(usuario);
		return this.usuarioRepository.save(user);
	}

	
	
}
