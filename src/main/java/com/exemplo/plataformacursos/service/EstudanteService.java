package com.exemplo.plataformacursos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.repository.EstudanteRepository;

import exception.EstudanteNaoEncontradoException;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;
    
    // Método para buscar estudante por ID
    public Estudante buscarPorId(Long id) {
        // Declara e inicializa o Optional para buscar o estudante
        Optional<Estudante> estudanteOpt = estudanteRepository.findById(id);
        
        // Verifica se o estudante foi encontrado
        if (estudanteOpt.isPresent()) {
            return estudanteOpt.get(); // Retorna o estudante encontrado
        } else {
            // Lança exceção personalizada quando o estudante não for encontrado
            throw new EstudanteNaoEncontradoException("Estudante não encontrado com ID: " + id);
        }
    }
        
    public Estudante salvarEstudante(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }
}
