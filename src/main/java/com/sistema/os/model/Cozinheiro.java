package com.sistema.os.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cozinheiros")
public class Cozinheiro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    private boolean ativo = true;

    // Construtor padrão (vazio) obrigatório pelo JPA
    public Cozinheiro() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() { // Para boolean, o padrão costuma ser 'isAtivo'
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}