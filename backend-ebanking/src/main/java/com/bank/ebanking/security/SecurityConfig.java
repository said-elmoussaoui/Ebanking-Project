package com.bank.ebanking.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.bank.ebanking.service.UserService;
import com.bank.ebanking.model.*;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/rendezVous/*").hasAnyRole("Agent","Client")
		.antMatchers("/admin/*").hasRole("Admin")
		.antMatchers("/client/*").hasAnyRole("Client","Agent","Admin")
		.antMatchers("/agent/**").hasAnyRole("Agent","Admin")
		.antMatchers("/compte/*").hasAnyRole("Client","Agent","Admin")
		.antMatchers("/operation/*").hasAnyRole("Client","Agent")
		.antMatchers("/virement/*").hasRole("Client")
		.antMatchers("/agence/*").permitAll()
		.antMatchers("/utilisateur/**").hasAnyRole("Client","Agent","Admin")
		.antMatchers("/agent/updateAgenceOfAgent/**").permitAll()
		.anyRequest().authenticated().and().httpBasic();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
	 List<Utilisateur> users = userService.getAll();
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		for(Utilisateur user : users) {
	          UserDetails theUser = User.withUsername(user.getUsername())
                    .password(getPasswordEncoder().encode(user.getPassword())).roles(user.getRole()).build();
	        userDetailsManager.createUser(theUser);
	        System.out.println(user.getPassword());
		}
		return userDetailsManager;
    }

}