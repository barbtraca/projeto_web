package com.exemplo.plataformacursos.repository;

import com.exemplo.plataformacursos.model.Inscricao;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByEstudanteId(Long estudanteId);  // Buscar inscrições de um aluno
    List<Inscricao> findByCursoId(Long cursoId);  // Buscar inscrições de um curso
    List<Inscricao> findByEstudante(Estudante estudante); // Método para buscar inscrições de um estudante
    List<Inscricao> findByCurso(Curso curso);
}
