package com.exemplo.plataformacursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.plataformacursos.dto.EstudanteDTO;
import com.exemplo.plataformacursos.model.Curso;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.service.CursoService;
import com.exemplo.plataformacursos.service.InscricaoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private InscricaoService inscricaoService;

    // Endpoint para cadastrar um novo curso
    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvarCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }

    // Endpoint para listar todos os alunos inscritos em um curso específico
    @GetMapping("/{cursoId}/esudante")
    public ResponseEntity<List<EstudanteDTO>> listarEstudanteDoCurso(@PathVariable Long cursoId) {
        List<EstudanteDTO> estudanteDTO = inscricaoService.listarEstudantePorCurso(cursoId);
        return ResponseEntity.ok(estudanteDTO);
    }
}
