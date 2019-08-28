package com.fatec.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fatec.entities.Usuario;
import com.fatec.enums.TipoPerfil;

public class JwtUserFactory {
	
	private JwtUserFactory() {}

	/**
	 * Converte e gera um JwtUser com base nos dados do usuario.
	 * 
	 * @param Usuario
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getUsername(), usuario.getSenha(),
				mapToGrantedAuthorities(usuario.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param tipoPerfil
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(TipoPerfil tipoPerfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(tipoPerfil.toString()));
		return authorities;
	}
}
