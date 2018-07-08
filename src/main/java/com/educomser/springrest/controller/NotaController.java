package com.educomser.springrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educomser.springrest.entity.Nota;
import com.educomser.springrest.model.NotaModel;
import com.educomser.springrest.service.NotaService;

@RestController
@RequestMapping("/nota")
public class NotaController {

	@Autowired
	@Qualifier("notaService")
	NotaService service;

	@PostMapping("/agregar")
	public boolean agregarNota(@RequestBody @Valid Nota nota) {
		return service.crear(nota);
	}
	
	@PutMapping("/actualizar")
	public boolean actualizarNota(@RequestBody @Valid Nota nota) {
		return service.actualizar(nota);
	}
	
	@DeleteMapping("/eliminar/{id}/{nombre}")
	public boolean eliminarrNota(@PathVariable("id") long id, @PathVariable("nombre") String nombre) {
		return service.borrar(nombre, id);
	}
	
	@GetMapping("/obtener")
	public List<NotaModel> obtenerNotas(Pageable pageable){
		return service.obtenerPorPaginacion(pageable);
	}
}
