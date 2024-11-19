package com.exemplo.plataformacursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.plataformacursos.model.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {}