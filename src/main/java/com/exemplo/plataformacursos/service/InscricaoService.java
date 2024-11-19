package com.exemplo.plataformacursos.service;

import com.exemplo.plataformacursos.model.Inscricao;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.model.Curso;
import com.exemplo.plataformacursos.dto.CursoDTO;
import com.exemplo.plataformacursos.dto.EstudanteDTO;
import com.exemplo.plataformacursos.dto.InscricaoDTO;
import com.exemplo.plataformacursos.dto.InscricaoPopulator;
import com.exemplo.plataformacursos.repository.InscricaoRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EstudanteService estudanteService;

    @Autowired
    private CursoService cursoService;

    // Método para inscrever um aluno em um curso
    public InscricaoDTO inscreverEstudantesEmCurso(Long estudanteId, Long cursoId) {
        Estudante estudante = estudanteService.buscarPorId(estudanteId);
        Curso curso = cursoService.buscarPorId(cursoId);

        // Criar a inscrição
        Inscricao inscricao = new Inscricao();
        inscricao.setEstudante(estudante);
        inscricao.setCurso(curso);
        inscricao.setDataInscricao(java.time.LocalDate.now());

        // Persistir a inscrição
        inscricao = inscricaoRepository.save(inscricao);

        // Converter a entidade Inscricao para InscricaoDTO
        return InscricaoPopulator.toDTO(inscricao);
    }

    // Método para buscar todos os cursos de um estudante
    public List<CursoDTO> listarCursosPorEstudante(Long estudanteId) {
        Estudante estudante = estudanteService.buscarPorId(estudanteId);

        // Obter as inscrições para o estudante
        List<Inscricao> inscricoes = inscricaoRepository.findByEstudante(estudante);

        // Converter para DTOs de Cursos
        List<CursoDTO> cursosDTO = inscricoes.stream()
                .map(inscricao -> new CursoDTO(inscricao.getCurso()))
                .collect(Collectors.toList());

        return cursosDTO;
    }
    public List<EstudanteDTO> listarEstudantePorCurso(Long cursoId) {
        // Verifique se o curso existe (pode ser necessário um CursoRepository)
        List<Inscricao> inscricoes = inscricaoRepository.findByCursoId(cursoId);

        // Converter as inscrições para DTOs
        List<EstudanteDTO> estudanteDTOs = inscricoes.stream()
                .map(inscricao -> new EstudanteDTO(inscricao.getEstudante()))
                .collect(Collectors.toList());
           
          return estudanteDTOs;
    }

    // Método para listar todos os estudantes inscritos em um curso
    public List<EstudanteDTO> listarEstudanteDeCurso(Long cursoId) {
        Curso curso = cursoService.buscarPorId(cursoId);

        // Obter as inscrições para o curso
        List<Inscricao> inscricoes = inscricaoRepository.findByCurso(curso);

        // Converter para DTOs de Estutantes
        List<EstudanteDTO> estudanteDTO = inscricoes.stream()
                .map(inscricao -> new EstudanteDTO(inscricao.getEstudante()))
                .collect(Collectors.toList());

        return estudanteDTO;
    }
}
