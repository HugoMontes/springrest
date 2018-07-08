package com.educomser.springrest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.educomser.springrest.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// Inyectar la clase usuarioService
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	// Implementar los metodos haciendo clic derecho >
	// Source > Override/Implement Methods... y seleccionar
	// - configure(AuthenticationManagerBuilder)
	// - configure(HttpSecurity)
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Opcionalmente se puede encriptar el password
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
		//auth.userDetailsService(usuarioService);
	}

	// Configuracion para las peticiones
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/login").permitAll() // Permite acceso a /login a cualquiera
			.anyRequest().authenticated()      // Cualquier otra peticion requiera autenticacion
			.and()
			// Las peticiones /login pasan previamente por este filtro
			.addFilterBefore(new LoginFilter("/login", authenticationManager()), 
					UsernamePasswordAuthenticationFilter.class)
			// Las demas peticiones pasan por este filtro para validar token
			.addFilterBefore(new JwtFilter(),
					UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint());
	}
	
}
