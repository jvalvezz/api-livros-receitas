package com.sistema.os.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livros_receita")
public class LivroReceita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "livroReceita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receitas;

    // Construtor padrão (vazio) obrigatório pelo JPA
    public LivroReceita() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
}