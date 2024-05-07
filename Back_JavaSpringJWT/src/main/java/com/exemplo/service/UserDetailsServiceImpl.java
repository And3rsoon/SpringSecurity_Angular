package com.exemplo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemplo.model.User;
import com.exemplo.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
	@Autowired
	private UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
	  
	User user=userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
	return new UserDetailsImp(user);
  }

}
