package com.fatec.security.dto;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
public class JwtAuthenticationDto {
	
	private String username;
	private String senha;

	public JwtAuthenticationDto() {
	}

	@NotEmpty(message = "O username não pode ser vazio.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty(message = "A senha não pode ser vazia.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [username: " + username + "  |  senha: " + senha + "]";
	}

}
