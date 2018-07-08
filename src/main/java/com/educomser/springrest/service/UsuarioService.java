package com.educomser.springrest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.educomser.springrest.entity.Usuario;
import com.educomser.springrest.repository.UsuarioRepository;

// La clase UsuarioService es usado por las configuraciones de Spring
// por lo tanto se debe implementar la interface UserDetailsService
@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository repository;

	// Metodo abstracto que carga un usuario a partir del nombre de usuario
	// verifica si el usuario existe en la base de datos
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repository.findByUsername(username);
		if (user != null) {
			return new User(user.getUsername(), user.getPassword(), user.isActivo(), user.isActivo(), user.isActivo(),
					user.isActivo(), buildGranted(user.getRol()));
		}
		throw new UsernameNotFoundException(username);
	}

	// Metodo que verifica a que rol pertenece un usuario
	public List<GrantedAuthority> buildGranted(byte rol) {
		String[] roles = { "LECTOR", "USUARIO", "ADMINISTRADOR" };
		List<GrantedAuthority> auths = new ArrayList<>();
		for (int i = 0; i < rol; i++) {
			auths.add(new SimpleGrantedAuthority(roles[i]));
		}
		return auths;
	}

}
