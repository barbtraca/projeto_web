package com.exemplo.plataformacursos.dto;

import com.exemplo.plataformacursos.model.Curso;

public class CursoDTO {
    private Long id;
    private String nome;
    private String descricao;
    
    public CursoDTO(Curso curso) {
        this.id = curso.getId();         // Mapeando os dados do objeto Curso
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
    }
    
    Curso curso = new Curso(); // Preencha os dados do curso (por exemplo, usando setters ou construtores)

    CursoDTO cursoDTO = new CursoDTO(curso); // Passando o curso como argumento

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
