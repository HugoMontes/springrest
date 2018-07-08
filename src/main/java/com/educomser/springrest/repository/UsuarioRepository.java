package com.educomser.springrest.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educomser.springrest.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {
	
	public abstract Usuario findByUsername(String username);
}
