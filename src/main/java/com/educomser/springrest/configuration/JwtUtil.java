package com.educomser.springrest.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static java.util.Collections.emptyList;

public class JwtUtil {

	// Metodo para crear el jwt y enviarlo al cliente en el header de la respuesta
	static void addAuthentication(HttpServletResponse response, String username) {
		String token=Jwts.builder().setSubject(username)
				// Hash con el que se firma la clave
				.signWith(SignatureAlgorithm.HS512, "s3cr3t").compact();
		// Agregar al encabezado el token
		// La palabra Bearer puede ser cualquier valor
		response.addHeader("Authorization", "Bearer "+token);
	}
	
	// Metodo para validar el token enviado por el cliente
	static Authentication getAuthentication(HttpServletRequest request) {
		// Obtener el token que viene en el encabezado de la peticion
		String token=request.getHeader("Authorization");
		// Si hay un token presente lo validamos
		if(token != null) {
			String user=Jwts.parser()
					.setSigningKey("s3cr3t")
					.parseClaimsJws(token.replace("Bearer", "")) // este metodo es el que valida
					.getBody()
					.getSubject();
			// Recordemos que para las demas peticion que no son /login
			// no requerimos una autenticacion por username y password
			// por este motivo podermo devolver un UsernamePasswordAuthenticationToken sin password
			return user!=null?new UsernamePasswordAuthenticationToken(user, null, emptyList()):null;
		}
		return null;
	}
}



