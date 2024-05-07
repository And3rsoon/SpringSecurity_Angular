package com.exemplo.model.DTOmodels;

import java.util.Set;

public class buscardto {
	
	private int id;
	private String username;
	private Set<String >roles;
    private String email;
	
	public buscardto(int id,String username, Set<String> roles,String email){
		this.id=id;
		this.username=username;
		this.roles=roles;
		this.email=email;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
