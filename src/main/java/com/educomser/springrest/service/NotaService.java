package com.educomser.springrest.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.educomser.springrest.converter.NotaConverter;
import com.educomser.springrest.entity.Nota;
import com.educomser.springrest.repository.NotaRepository;
import com.educomser.springrest.model.NotaModel;


@Service
public class NotaService {
	@Autowired
	@Qualifier("notaRepository")
	private NotaRepository repositorio;
	
	@Autowired
	@Qualifier("notaConverter")
	private NotaConverter convertidor;
	
	private static final Log logger=LogFactory.getLog(NotaService.class);
	
	public boolean crear(Nota nota){
		logger.info("CREANDO: "+nota);
		try {
			repositorio.save(nota);
			logger.info("NOTA CREADA");
			return true;
		}catch(Exception ex){
			logger.error("ERROR AL CREAR");
			return false;
		}
	}
	
	public boolean actualizar(Nota nota){
		try {
			repositorio.save(nota);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public boolean borrar(String nombre, long id){
		try {
			Nota nota=repositorio.findByNombreAndId(nombre, id);
			repositorio.delete(nota);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public List<NotaModel> obtener(){
		logger.info("OBTENIENDO TODAS LAS NOTAS");
		List<NotaModel> notas = convertidor.convertirLista(repositorio.findAll());
		return notas;
	}	
	
	public List<NotaModel> obtenerTitulo(String titulo){
		List<NotaModel> notas = convertidor.convertirLista(repositorio.findByTitulo(titulo));
		return notas;
	}
	
	public NotaModel obtenerPorNombreTitulo(String nombre, String titulo){
		return new NotaModel(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	
	public List<NotaModel> obtenerPorPaginacion(Pageable pageable){
		return convertidor.convertirLista(repositorio.findAll(pageable).getContent());
	}
}
