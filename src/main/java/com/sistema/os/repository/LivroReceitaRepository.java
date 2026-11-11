package com.sistema.os.repository;

import com.sistema.os.model.LivroReceita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LivroReceitaRepository
        extends JpaRepository<LivroReceita, Long> {
}
