package com.mailorderpharmacy.authservice;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.mailorderpharmacy.authservice.service.CustomerDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomerDetailsService emsuserDetailsService;
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		super.configure(auth);
		auth.userDetailsService(emsuserDetailsService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
					.antMatchers("/**")
					.permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.exceptionHandling().and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
