package com.sistema.os.controller;

import com.sistema.os.controller.dto.ReceitaRequest;
import com.sistema.os.model.Cozinheiro;
import com.sistema.os.model.LivroReceita;
import com.sistema.os.model.Receita;
import com.sistema.os.service.ReceitaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public List<Receita> listar() {
        return receitaService.listarReceitas();
    }

    @GetMapping("/{id}")
    public Receita buscar(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receita cadastrar(@RequestBody ReceitaRequest dados) {
        return receitaService.salvar(criarReceita(dados));
    }

    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id, @RequestBody ReceitaRequest dados) {
        return receitaService.atualizar(id, criarReceita(dados));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        receitaService.excluir(id);
    }

    private Receita criarReceita(ReceitaRequest dados) {
        Receita receita = new Receita();
        receita.setNome(dados.nome());

        Cozinheiro cozinheiro = new Cozinheiro();
        cozinheiro.setId(dados.cozinheiroId());
        receita.setCozinheiro(cozinheiro);

        LivroReceita livroReceita = new LivroReceita();
        livroReceita.setId(dados.livroReceitaId());
        receita.setLivroReceita(livroReceita);

        return receita;
    }
}
