package com.exemplo.plataformacursos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.plataformacursos.model.Curso;
import com.exemplo.plataformacursos.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    
 // Método para buscar curso por ID
    public Curso buscarPorId(Long id) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        if (cursoOpt.isPresent()) {
            return cursoOpt.get();
        } else {
            throw new RuntimeException("Curso não encontrado");
        }
    }

    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
}
