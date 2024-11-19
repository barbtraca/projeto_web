package com.exemplo.plataformacursos.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.exemplo.plataformacursos.model.Curso;
import com.exemplo.plataformacursos.model.Estudante;
import com.exemplo.plataformacursos.model.Inscricao;
import com.exemplo.plataformacursos.model.HibernateUtil;

import java.time.LocalDate;

public class Teste {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Criar um novo curso
            Curso curso = new Curso();
            curso.setNome("Java Avançado");
            curso.setDescricao("Curso para aprender conceitos avançados de Java.");
            
            // Criar um novo estudante
            Estudante estudante = new Estudante();
            estudante.setNome("João Pereira");
            estudante.setEmail("joao.pereira@email.com");

            // Criar uma nova inscrição
            Inscricao inscricao = new Inscricao();
            inscricao.setCurso(curso);
            inscricao.setEstudante(estudante);
            inscricao.setDataInscricao(LocalDate.now());

            // Persistir os dados
            session.save(curso);
            session.save(estudante);
            session.save(inscricao);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        HibernateUtil.shutdown();
    }
}
