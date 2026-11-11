package com.sistema.os.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "receitas")
public class Receita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cozinheiro_id", nullable = false)
    @JsonIgnore // Evita loop infinito na hora de transformar o objeto em Json
    private Cozinheiro cozinheiro;

    @ManyToOne
    @JoinColumn(name = "livro_receita_id", nullable = false)
    @JsonIgnore // Evita loop infinito na hora de transformar o objeto em Json
    private LivroReceita livroReceita;

    // Construtor padrão (vazio) obrigatório pelo JPA
    public Receita() {
    }

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

    public Cozinheiro getCozinheiro() {
        return cozinheiro;
    }

    public void setCozinheiro(Cozinheiro cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public LivroReceita getLivroReceita() {
        return livroReceita;
    }

    public void setLivroReceita(LivroReceita livroReceita) {
        this.livroReceita = livroReceita;
    }
}