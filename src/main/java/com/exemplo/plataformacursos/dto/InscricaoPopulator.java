package com.exemplo.plataformacursos.dto;

import com.exemplo.plataformacursos.dto.EstudanteDTO;
import com.exemplo.plataformacursos.dto.CursoDTO;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.model.Inscricao;
import com.exemplo.plataformacursos.model.Curso;

public class InscricaoPopulator {

    // Converter Estudante para EstudanteDTO
    public static EstudanteDTO convertToEstudanteDTO(Estudante estudante) {
        EstudanteDTO estudanteDTO = new EstudanteDTO(estudante);
        estudanteDTO.setId(estudante.getId());
        estudanteDTO.setNome(estudante.getNome());
        estudanteDTO.setEmail(estudante.getEmail());
        return estudanteDTO;
    }

    // Converter Curso para CursoDTO
    public static CursoDTO convertToCursoDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO(curso);
        cursoDTO.setId(curso.getId());
        cursoDTO.setNome(curso.getNome());
        cursoDTO.setDescricao(curso.getDescricao());
        return cursoDTO;
    }
    
    public static InscricaoDTO toDTO(Inscricao inscricao) {
        return new InscricaoDTO(
                inscricao.getId(),
                inscricao.getEstudante().getId(),
                inscricao.getEstudante().getNome(),
                inscricao.getCurso().getId(),
                inscricao.getCurso().getNome(),
                inscricao.getDataInscricao()
        );
  }
}
