package com.exemplo.model;

import java.util.Set;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import javax.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;
	@Column(name="username")
	private String username;
	private String password;
	private Set<String> roles;
	
	public String toString() {
		
		return id+" "+username+" "+password+" "+roles;
	}
	
	
	public void setNome(String nome) {
		this.nome=nome;		
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}	
}
