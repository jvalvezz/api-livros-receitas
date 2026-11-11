package com.sistema.os.service;

import com.sistema.os.model.Cozinheiro;
import com.sistema.os.model.LivroReceita;
import com.sistema.os.model.Receita;
import com.sistema.os.repository.ReceitaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final CozinheiroService cozinheiroService;
    private final LivroReceitaService livroReceitaService;

    public ReceitaService(
            ReceitaRepository receitaRepository,
            CozinheiroService cozinheiroService,
            LivroReceitaService livroReceitaService) {
        this.receitaRepository = receitaRepository;
        this.cozinheiroService = cozinheiroService;
        this.livroReceitaService = livroReceitaService;
    }

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public Receita buscarPorId(Long id) {
        return receitaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Receita não encontrada: " + id));
        
    }

    public Receita salvar(Receita receita) {
        definirRelacionamentos(receita);
        return receitaRepository.save(receita);
    }

    public Receita atualizar (Long id, Receita dados) {
        Receita receita = buscarPorId(id);
        receita.setNome(dados.getNome());
        definirRelacionamentos(dados);
        receita.setCozinheiro(dados.getCozinheiro());
        receita.setLivroReceita(dados.getLivroReceita());
        return receitaRepository.save(receita);
    }

    // criei uma função para validar se o cozinheiro e o livro que está tentando ser registrado realmente existe, aí ao tentar salvar ou atualizar ele verifica antes e da o retorno pro usuário usando um BAD_REQUEST
    private void definirRelacionamentos(Receita receita) {
        if (receita.getCozinheiro() == null || receita.getCozinheiro().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O id do cozinheiro é obrigatório");
        }

        if (receita.getLivroReceita() == null || receita.getLivroReceita().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O id do livro de receitas é obrigatório");
        }

        Cozinheiro cozinheiro = cozinheiroService.buscarPorId(receita.getCozinheiro().getId());
        LivroReceita livroReceita = livroReceitaService.buscarPorId(receita.getLivroReceita().getId());

        receita.setCozinheiro(cozinheiro);
        receita.setLivroReceita(livroReceita);
    }

    public void excluir(Long id) {
        Receita receita = buscarPorId(id);
        receitaRepository.delete(receita);
    }
}

