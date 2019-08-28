package com.fatec.dto;

import java.util.Date;

import com.fatec.enums.TipoPerfil;

public class UsuarioDto {
	
	private Long id;
	
	private String username;
	
	private String senha;
	
	private Date dataCriacao;
	
	private Date dataAtualizacao;
	
	private TipoPerfil perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public TipoPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(TipoPerfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", username=" + username + ", senha=" + senha + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + ", perfil=" + perfil + "]";
	}
	
	
	
}
