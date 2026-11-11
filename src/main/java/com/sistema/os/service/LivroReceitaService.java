package com.sistema.os.service;

import com.sistema.os.model.LivroReceita;
import com.sistema.os.model.Receita;
import com.sistema.os.repository.LivroReceitaRepository;
import com.sistema.os.repository.ReceitaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroReceitaService {

    private final LivroReceitaRepository livroReceitaRepository;
    private final ReceitaRepository receitaRepository;

    public LivroReceitaService(
            LivroReceitaRepository livroReceitaRepository,
            ReceitaRepository receitaRepository) {

        this.livroReceitaRepository = livroReceitaRepository;
        this.receitaRepository = receitaRepository;
    }

    public List<LivroReceita> listarTodos() {
        return livroReceitaRepository.findAll();
    }

    public LivroReceita buscarPorId(Long id) {
        return livroReceitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro de receitas não encontrado: " + id
                ));
    }

    public LivroReceita salvar(LivroReceita livroReceita) {
        return livroReceitaRepository.save(livroReceita);
    }

    public LivroReceita atualizar(Long id, LivroReceita dados) {
        LivroReceita livroReceita = buscarPorId(id);

        livroReceita.setTitulo(dados.getTitulo());
        livroReceita.setDescricao(dados.getDescricao());

        return livroReceitaRepository.save(livroReceita);
    }

    public List<Receita> listarReceitasDoLivro(Long livroId) {
        buscarPorId(livroId); // garante que o livro existe
        return receitaRepository.findByLivroReceitaId(livroId);
    }

    public void excluir(Long id) {
        LivroReceita livroReceita = buscarPorId(id);
        livroReceitaRepository.delete(livroReceita);
    }
}