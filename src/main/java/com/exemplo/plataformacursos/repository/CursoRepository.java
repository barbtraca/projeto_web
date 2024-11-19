package com.exemplo.plataformacursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.plataformacursos.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {}