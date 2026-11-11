package com.sistema.os.controller;

import com.sistema.os.model.LivroReceita;
import com.sistema.os.model.Receita;
import com.sistema.os.service.LivroReceitaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/livros-receitas")
public class LivroReceitaController {
    private final LivroReceitaService livroReceitaService;

    public LivroReceitaController(LivroReceitaService livroReceitaService) {
        this.livroReceitaService = livroReceitaService;
    }

    @GetMapping
    public List<LivroReceita> listar() {
        return livroReceitaService.listarTodos();
    }

    @GetMapping("/{id}")
    public LivroReceita buscar(@PathVariable Long id) {
        return livroReceitaService.buscarPorId(id);
    }

    @GetMapping("/{id}/receitas")
    public List<Receita> listarReceitas(@PathVariable Long id) {
        return livroReceitaService.listarReceitasDoLivro(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroReceita cadastrar(@RequestBody LivroReceita livroReceita) {
        return livroReceitaService.salvar(livroReceita);
    }

    @PutMapping("/{id}")
    public LivroReceita atualizar(@PathVariable Long id, @RequestBody LivroReceita dados) {
        return livroReceitaService.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        livroReceitaService.excluir(id);
    }
}
