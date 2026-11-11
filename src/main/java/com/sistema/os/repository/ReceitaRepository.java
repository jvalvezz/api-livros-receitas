package com.sistema.os.repository;

import com.sistema.os.model.Receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReceitaRepository
        extends JpaRepository<Receita, Long> {

    // SELECT * FROM receita WHERE cozinheiro_id = ?
    List<Receita> findByCozinheiroId(Long cozinheiro_id);
    
    // SELECT * FROM receita WHERE livro_receita_id
    List<Receita> findByLivroReceitaId(Long livro_receita_id);


    boolean existsByCozinheiroId(Long cozinheiro_id); // Precisa existir um cozinheiro 
}
