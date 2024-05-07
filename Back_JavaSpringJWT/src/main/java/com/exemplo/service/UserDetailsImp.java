package com.exemplo.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.exemplo.model.User;


public class UserDetailsImp implements UserDetails {
  private static final long serialVersionUID = 1L;

  
  private final User user;

  public UserDetailsImp(User user) {
    this.user = user;
  }
  
  public String getNome() {
	  return user.getNome();
  }
  
  public int getId() {
		return user.getId();
	}
  
   public String getEmail() {
		return user.getEmail();
	}
  
  @Override
  public String getUsername() {
    return user.getUsername();
  }

  
  @Override
  public String getPassword() {
    return user.getPassword();
  }

  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	  List<GrantedAuthority> authorities = user.getRoles().stream()
		        .map(role -> new SimpleGrantedAuthority(role))
		        .collect(Collectors.toList());
	  return authorities;
  }

  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  
  @Override
  public boolean isEnabled() {
    return true;
  }

}
