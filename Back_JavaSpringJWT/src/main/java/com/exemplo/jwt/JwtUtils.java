package com.exemplo.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;



@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Autowired
  private JwtEncoder encoder;
  @Autowired
  private JwtDecoder decoder;


  public String generateToken(Authentication authentication) {
	    Instant now = Instant.now();
	    long expiry = 36000L;

	    String scope = authentication.getAuthorities().stream()
	    											  .map(GrantedAuthority::getAuthority)
	    											  .collect(Collectors.joining(" "));

	    JwtClaimsSet claims = JwtClaimsSet.builder()
	    								 .issuer("spring-security-jwt")
	    								 .issuedAt(now)
	    								 .expiresAt(now.plusSeconds(expiry))
	    								 .subject(authentication.getName())
	    								 .claim("scope", scope)
	    								 .build();

	    return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	    
	  }
 
  		public String getSubject(String jwttoken) {
	   Jwt jwt=decoder.decode(jwttoken);
	   return jwt.getClaim("sub").toString();
	  }
  
  	
  		
   public boolean validate(String jwttoken) {
	   Jwt jwt=decoder.decode(jwttoken);
	   OAuth2TokenValidator<Jwt> jwtValidator = JwtValidators.createDefault();
	   OAuth2TokenValidatorResult result = jwtValidator.validate(jwt); 
	  return result.hasErrors();
	  }
   
  
  
}