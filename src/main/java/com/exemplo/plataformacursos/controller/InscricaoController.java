package com.exemplo.plataformacursos.controller;

import com.exemplo.plataformacursos.dto.EstudanteDTO;
import com.exemplo.plataformacursos.dto.CursoDTO;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.model.Curso;
import com.exemplo.plataformacursos.model.Inscricao;
import com.exemplo.plataformacursos.dto.InscricaoPopulator;
import com.exemplo.plataformacursos.repository.EstudanteRepository;
import com.exemplo.plataformacursos.repository.CursoRepository;
import com.exemplo.plataformacursos.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    // Endpoint para cadastrar estudantes
    @PostMapping("/estudante")
    public EstudanteDTO cadastrarAluno(@RequestBody EstudanteDTO estudanteDTO) {
        Estudante estudante = new Estudante();
        estudante.setNome(estudanteDTO.getNome());
        estudante.setEmail(estudanteDTO.getEmail());
        estudanteRepository.save(estudante);
        return InscricaoPopulator.convertToEstudanteDTO(estudante);
    }

    // Endpoint para cadastrar curso
    @PostMapping("/curso")
    public CursoDTO cadastrarCurso(@RequestBody CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        curso.setDescricao(cursoDTO.getDescricao());
        cursoRepository.save(curso);
        return InscricaoPopulator.convertToCursoDTO(curso);
    }

    // Endpoint para inscrever aluno em um curso
    @PostMapping("/inscricao")
    public void inscreverAluno(@RequestParam Long estudanteId, @RequestParam Long cursoId) {
        Estudante estudante = estudanteRepository.findById(estudanteId).orElseThrow();
        Curso curso = cursoRepository.findById(cursoId).orElseThrow();

        Inscricao inscricao = new Inscricao();
        inscricao.setEstudante(estudante);
        inscricao.setCurso(curso);
        inscricaoRepository.save(inscricao);
    }

    // Endpoint para listar cursos em que um aluno está inscrito
    @GetMapping("/aluno/{id}/cursos")
    public List<CursoDTO> listarCursosPorAluno(@PathVariable Long id) {
        List<Inscricao> inscricoes = inscricaoRepository.findByEstudanteId(id);
        return inscricoes.stream()
                .map(inscricao -> InscricaoPopulator.convertToCursoDTO(inscricao.getCurso()))
                .collect(Collectors.toList());
    }

    // Endpoint para listar alunos inscritos em um curso
    @GetMapping("/curso/{id}/estudante")
    public List<EstudanteDTO> listarEstudantePorCurso(@PathVariable Long id) {
        List<Inscricao> inscricoes = inscricaoRepository.findByCursoId(id);
        return inscricoes.stream()
                .map(inscricao -> InscricaoPopulator.convertToEstudanteDTO(inscricao.getEstudante()))
                .collect(Collectors.toList());
    }
} 
   


