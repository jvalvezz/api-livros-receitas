package com.sistema.os.controller.dto;

public record ReceitaRequest(
        String nome,
        Long cozinheiroId,
        Long livroReceitaId) {
}
