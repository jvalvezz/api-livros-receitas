package com.sistema.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.os.model.Cozinheiro;

@Repository
public interface CozinheiroRepository
        extends JpaRepository<Cozinheiro, Long> {
                boolean existsByCpf(String cpf);
}
