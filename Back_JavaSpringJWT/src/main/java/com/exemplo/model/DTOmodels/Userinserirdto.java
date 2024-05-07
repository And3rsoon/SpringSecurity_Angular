package com.exemplo.model.DTOmodels;

import java.util.Set;

public class Userinserirdto {
	private int id;
	private String nome;
	private String email;
	private String username;
	private String password;
	private Set<String> roles;
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public String sgetNome() {
		return nome;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getId() {
		return this.id;
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
	}public String toString() {
		
		return username+" "+password+" "+email+" "+roles;
		
	}
}
