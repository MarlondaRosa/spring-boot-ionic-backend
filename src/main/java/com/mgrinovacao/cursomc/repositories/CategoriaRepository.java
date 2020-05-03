package com.mgrinovacao.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgrinovacao.cursomc.domain.CategoriaDomain;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaDomain, Integer> {
	
	

}
