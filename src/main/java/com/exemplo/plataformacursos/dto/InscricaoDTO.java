package com.exemplo.plataformacursos.dto;

import java.time.LocalDate;

// DTO para representar os dados de Inscrição
public class InscricaoDTO {

    private Long id;
    private Long estudanteId;
    private String estudanteNome;
    private Long cursoId;
    private String cursoNome;
    private LocalDate dataInscricao;

    // Construtor
    public InscricaoDTO(Long id, Long estudanteId, String estudanteNome, Long cursoId, String cursoNome, LocalDate dataInscricao) {
        this.id = id;
        this.estudanteId = estudanteId;
        this.estudanteNome = estudanteNome;
        this.cursoId = cursoId;
        this.cursoNome = cursoNome;
        this.dataInscricao = dataInscricao;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudanteId() {
        return estudanteId;
    }

    public void setAlunoId(Long estudanteId) {
        this.estudanteId = estudanteId;
    }

    public String getEstudanteNome() {
        return estudanteNome;
    }

    public void setAlunoNome(String estudanteNome) {
        this.estudanteNome = estudanteNome;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
