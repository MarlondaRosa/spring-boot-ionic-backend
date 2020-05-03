package com.mgrinovacao.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mgrinovacao.cursomc.domain.CategoriaDomain;
import com.mgrinovacao.cursomc.repositories.CategoriaRepository;
import com.mgrinovacao.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public CategoriaDomain buscar(Integer id) {
		 Optional<CategoriaDomain> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaDomain.class.getName()));
		} 



}
