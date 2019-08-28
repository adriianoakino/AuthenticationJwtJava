package com.fatec.services;

import java.util.Optional;

import com.fatec.dto.UsuarioDto;
import com.fatec.entities.Usuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorUsername(String username);
	
	Optional<Usuario> buscarPorId(Long id);
	
	boolean existsByUsername(String username);
	
	Usuario saveUsuario(UsuarioDto usuario);

}
