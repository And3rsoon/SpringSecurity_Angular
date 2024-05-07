package com.exemplo.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.exemplo.jwt.JwtUtils;
import com.exemplo.model.User;
import com.exemplo.model.DTOmodels.Userdto;
import com.exemplo.model.DTOmodels.Userinserirdto;
import com.exemplo.model.DTOmodels.buscardto;
import com.exemplo.repository.UserRepository;
import com.exemplo.service.UserDetailsImp;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserControler {
	
	
	@Autowired
	 AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsImp userDetailsImp;
	
	@Autowired
	UserRepository userrepository;
	
	UserControler(UserDetailsImp user){
		this.userDetailsImp=user;
	}
	
	@Autowired
	JwtUtils jwtutils;	
	
	@GetMapping("/autenticar")
	public ResponseEntity<String> autenticar(HttpServletRequest request){

			String headerAuth= request.getHeader(HttpHeaders.AUTHORIZATION);
			System.out.println(headerAuth);
			if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
				String token=headerAuth.substring(7);
				if (token != null && !jwtutils.validate(token)) {
					String jwt = "Bearer "+token;
					return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(null);
				}
				else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
	}
	
	
	@PostMapping(value="/login")
	public ResponseEntity<String> login(@RequestBody  Userdto userdto){
		String user=userdto.getUsername();
		String password=userdto.getPassword();
		Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user,password));
		SecurityContextHolder.getContext().setAuthentication(auth);
		HttpHeaders header=new HttpHeaders();
		String jwt ="Bearer "+jwtutils.generateToken(auth);
		header.add(HttpHeaders.AUTHORIZATION, jwt);
		header.add(HttpHeaders.CONTENT_TYPE,"application/json");
	    return ResponseEntity.ok().headers(header).body("{\"status\":\"autorizado\"}");
	}
	
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<String> inserir(@RequestBody  Userinserirdto user){
		int id=user.getId();
		String nome=user.sgetNome();
	    String username=user.getUsername();
	    String password=passwordEncoder.encode(user.getPassword());
	    String email=user.getEmail();
	    System.out.println(nome+" "+username+" "+id+" "+password+" "+email);
	    Set<String> roles=new HashSet<String>();
	    roles.add("admin");
		User useer=new User();
		useer.setNome(nome);
		useer.setUsername(username);
		useer.setEmail(email);
		useer.setPassword(password);
		useer.setRoles(roles);
		useer.setId(id);
		userrepository.save(useer);	
		return ResponseEntity.ok(useer.toString());
	}
}
