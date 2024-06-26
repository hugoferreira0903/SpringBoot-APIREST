package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = true)
	private Long id;
	@Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
	@NotBlank(message = "O nome não pode estar nulo ou em branco")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
	private String nome;
	@Column (name = "email", nullable = false)
	@NotBlank(message = "o e-mail não pode estar nulo ou em branco")
	@Email(message = "Informe um e-mail válido")
	private String email;
	@Column (name = "senha", nullable = false)
	@NotBlank(message = "a senha não pode estar nulo ou em branco")
	private String senha;
	@Column(name = "sobre", nullable = true)
	private String sobre;
	
	
	
	public Usuario() {
		
	}
	
	
	public Long getUsuario_id() {
		return id;
	}
	
	public void setUsuario_id(Long usuario_id) {
		this.id = usuario_id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSobre() {
		return sobre;
	}
	
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	
	
	
	
}
