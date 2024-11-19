package com.exemplo.plataformacursos.dto;

import com.exemplo.plataformacursos.model.Estudante;

public class EstudanteDTO {
    private Long id;
    private String nome;
    private String email;
    
    // Construtor que recebe um Estudante
    public EstudanteDTO(Estudante estudante) {
        this.id = estudante.getId();
        this.nome = estudante.getNome();
        this.email = estudante.getEmail();
    }
    
    Estudante estudante = new Estudante(); // Preencha os dados do estudante (por exemplo, usando setters ou construtores)

    EstudanteDTO estudanteDTO = new EstudanteDTO(estudante); // Passando o estudante como argumento

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
