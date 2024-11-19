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

import com.exemplo.plataformacursos.dto.CursoDTO;
import com.exemplo.plataformacursos.model.Curso;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.model.Inscricao;
import com.exemplo.plataformacursos.service.EstudanteService;
import com.exemplo.plataformacursos.service.InscricaoService;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @Autowired
    private InscricaoService inscricaoService;

    // Endpoint para cadastrar um novo aluno
    @PostMapping
    public ResponseEntity<Estudante> cadastrarEstudante(@RequestBody Estudante estudante) {
        Estudante novoEstudante = estudanteService.salvarEstudante(estudante);
        return ResponseEntity.ok(novoEstudante);
    }

    // Endpoint para listar todos os cursos em que um aluno está inscrito
    @GetMapping("/{estudanteId}/cursos")
    public ResponseEntity<List<CursoDTO>> listarCursosDoEstudante(@PathVariable Long estudanteId) {
        List<CursoDTO> cursosDTO = inscricaoService.listarCursosPorEstudante(estudanteId);
        return ResponseEntity.ok(cursosDTO);
    }
}
