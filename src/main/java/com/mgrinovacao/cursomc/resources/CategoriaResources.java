package com.mgrinovacao.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mgrinovacao.cursomc.domain.CategoriaDomain;

@RestController
@RequestMapping(value="/categorias")

public class CategoriaResources {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CategoriaDomain> listar() {
		
		CategoriaDomain cat1 = new CategoriaDomain(1, "Informática");
		CategoriaDomain cat2 = new CategoriaDomain(1, "Escritório");
		
		List<CategoriaDomain> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}

}
