package com.educomser.springrest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.educomser.springrest.service.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
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
		// Especificar el tipo de cifrado para el password
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configuracion para las peticiones
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/login").permitAll() // Permite acceso a /login a cualquiera
			.antMatchers("/error").permitAll() // Permite acceso a /error a cualquiera
			.anyRequest().authenticated()      // Cualquier otra peticion requiere autenticacion
			.and()
				// Las peticiones /login pasan previamente por este filtro
				.addFilterBefore(new LoginFilter("/login", authenticationManager()), 
						UsernamePasswordAuthenticationFilter.class)
				// Las demas peticiones pasan por este filtro para validar token
				.addFilterBefore(new JwtFilter(),
						UsernamePasswordAuthenticationFilter.class)
				// Excepciones/validaciones de usuario no autorizado
				.exceptionHandling().authenticationEntryPoint(new BasicAuthenticationEntryPoint())
			.and()
				// Desactiva login por formulario
	           	.formLogin().disable();
	}
	
}
