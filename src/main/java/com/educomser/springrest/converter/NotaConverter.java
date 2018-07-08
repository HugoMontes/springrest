package com.educomser.springrest.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.educomser.springrest.model.NotaModel;
import com.educomser.springrest.entity.Nota;

// Funcion para convertir un listado de Entidades a Modelos
@Component
public class NotaConverter {
	public List<NotaModel> convertirLista(List<Nota> notas){
		List<NotaModel> notaModels=new ArrayList<>();
		for(Nota nota: notas) {
			notaModels.add(new NotaModel(nota));
		}
		return notaModels;
	}
}
