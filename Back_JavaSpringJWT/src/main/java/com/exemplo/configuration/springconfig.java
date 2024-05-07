package com.exemplo.configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import io.jsonwebtoken.Jwt;

import com.exemplo.jwt.JwtTokenFilter;
import com.exemplo.service.UserDetailsServiceImpl;
import com.exemplo.service.UserDetailsImp;




@Configuration
@EnableWebSecurity
public class springconfig {
	@Value("${jwt.public.key}")
	 private RSAPublicKey key;
	 @Value("${jwt.private.key}")
	 private RSAPrivateKey priv;
	
	 
	 @Bean
	  public UserDetailsImp UserDetails() {
	    return new UserDetailsImp(null);
	  }
	 
	 @Bean
	 public JwtTokenFilter jwtfilter() {
		 return new JwtTokenFilter();
	 }
	 
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}	
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }	
	
	
	@Bean
	public CorsConfigurationSource corsconf() {
		CorsConfiguration conf=new CorsConfiguration();
		conf.addAllowedMethod("GET");
		conf.addAllowedMethod("POST");
		conf.addAllowedOrigin("http://localhost:4200");
		conf.setAllowPrivateNetwork(true);
		conf.addAllowedHeader("*");
		conf.addAllowedHeader("Authorization");
		conf.addAllowedHeader("Content-Type");
		conf.addAllowedHeader("Accept");
		conf.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", conf);
		return source;
	}
	
	
	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  		http.csrf(csrf -> csrf.disable())
		  			.exceptionHandling(exc -> exc.authenticationEntryPoint(null))
		  			.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  			.authorizeHttpRequests(auth -> auth.requestMatchers("/login").permitAll()
		  												.anyRequest().permitAll())
		  			.httpBasic(Customizer.withDefaults())
		  			.oauth2ResourceServer(conf -> conf.jwt(jwt -> jwt.decoder(jwtDecoder())))
		  			.authenticationProvider(authenticationProvider())
		  			.addFilterBefore(jwtfilter(),UsernamePasswordAuthenticationFilter.class)
		  			.cors(cors->cors.configurationSource(corsconf()));
		  		return http.build();
	  }
	
	
	  @Bean
	  PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

	  @Bean
	  JwtDecoder jwtDecoder() {

		  return NimbusJwtDecoder.withPublicKey(this.key).build();
  
	  }

	  @Bean
	  JwtEncoder jwtEncoder() {
	    JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
	    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
	    return new NimbusJwtEncoder(jwks);
	  }
}
