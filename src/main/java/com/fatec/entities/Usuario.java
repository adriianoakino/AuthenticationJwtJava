package com.fatec.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fatec.dto.UsuarioDto;
import com.fatec.enums.TipoPerfil;

/**
 * 
 * @author aasantos9
 * classe responsável por criar uma entity no banco de dados
 * 
 * A anotação @Entity é responsavel pro informar ao spring que essa classe é uma entidade
 * A anotação @Table é responsável por criar a tabela dentro do schema do banco de dados 
 *
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -8438693398479231121L;
	
	/**
	 * A anotação @Id é responsável por informar que esse atributo é uma chave primária
	 * A anotação @GeneratedValue é responsável por como essa chave primária irá se comportar,
	 * strategy = GenerationType.AUTO é apenas para dizer que a sequencia de inserção é automática e sequencial
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * A anotação @Column é como será definido as propriedades da coluna da tabela criada,
	 * name = nome da tabela
	 * nullable = se pode ser ou não inserido campos vazios
	 * length = tamanho maximo de caracteres
	 * unique = se os valores dentro dessa tabela não possuem repetidos
	 * 
	 */
	@Column(name = "username", nullable = false, length = 25, unique = true)
	private String username;
	
	
	/**
	 * A anotação @Column é como será definido as propriedades da coluna da tabela criada,
	 * name = nome da tabela
	 * nullable = se pode ser ou não inserido campos vazios
	 * 
	 */
	@Column(name = "senha", nullable = false)
	private String senha;
	
	/**
	 * A anotação @Column é como será definido as propriedades da coluna da tabela criada,
	 * name = nome da tabela
	 * nullable = se pode ser ou não inserido campos vazios
	 * 
	 */
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	/**
	 * A anotação @Column é como será definido as propriedades da coluna da tabela criada,
	 * name = nome da tabela
	 * nullable = se pode ser ou não inserido campos vazios
	 * 
	 */
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private TipoPerfil perfil;
	
	public Usuario() {}

	public Usuario(UsuarioDto usuarioDto) {
		super();
		this.id = usuarioDto.getId();
		this.username = usuarioDto.getUsername();
		this.senha = usuarioDto.getSenha();
		this.dataCriacao = usuarioDto.getDataCriacao();
		this.dataAtualizacao = usuarioDto.getDataAtualizacao();
		this.perfil = usuarioDto.getPerfil();
	}


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
	
	public TipoPerfil getPerfil() {
		return perfil;
	}


	public void setPerfil(TipoPerfil perfil) {
		this.perfil = perfil;
	}


	/**
	 * Este método é responsável de antes de fazer algum update no banco de dados
	 * ele coloca a data que esta alteração está sendo feita
	 * 
	 * A anotação @PreUpdate é responsável de antes de atualizar faça algo.
	 * 
	 */
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
	
	
	/**
	 * Este método é responsável de antes de fazer algum insert no banco de dados
	 * ele coloca a data que está sendo criado.
	 * 
	 * A anotação @PrePersist é responsável de antes de inserir faça algo.
	 */
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

    
    /**
     *  O toString() irá nos ajudar a visualizar melhor as informações em cada atributo
     *  ou inves de ficar chamando cada um por vez.
     */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", senha=" + senha + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + "]";
	}
    
    
}
