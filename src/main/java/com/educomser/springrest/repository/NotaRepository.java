package com.educomser.springrest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.educomser.springrest.entity.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Serializable>, PagingAndSortingRepository<Nota, Serializable> {
	// Busca por id
	public abstract Nota findById(long id);
	// Busca por titulo
	public abstract List<Nota> findByTitulo(String titulo);
	// Busca por nombre y titulo
	public abstract Nota findByNombreAndTitulo(String nombre, String titulo);
	// Busca por nombre y id
	public abstract Nota findByNombreAndId(String nombre, long id);
	// Devuelve datos paginados
	public abstract Page<Nota> findAll(Pageable pageable);
}
