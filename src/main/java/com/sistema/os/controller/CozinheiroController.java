package com.sistema.os.controller;

import com.sistema.os.model.Cozinheiro;
import com.sistema.os.service.CozinheiroService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/cozinheiros")
public class CozinheiroController {
    private final CozinheiroService cozinheiroService;

    public CozinheiroController(CozinheiroService cozinheiroService) {
        this.cozinheiroService = cozinheiroService;
    }

    // GET /cozinheiros
    @GetMapping
    public List<Cozinheiro> listar() {
        return cozinheiroService.listarTodos();
    }

    // GET /cozinheiros/1
    @GetMapping("/{id}")
    public Cozinheiro buscar(@PathVariable Long id) {
        return cozinheiroService.buscarPorId(id);
    }

    // POST /cozinheiros
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinheiro cadastrar(@RequestBody Cozinheiro cozinheiro) {
        return cozinheiroService.salvar(cozinheiro);
    }

    // PUT /cozinheiros/1
    @PutMapping("/{id}")
    public Cozinheiro atualizar(@PathVariable Long id, @RequestBody Cozinheiro cozinheiro) {
        return cozinheiroService.atualizar(id, cozinheiro);
    }

    // DELETE /cozinheiros/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        cozinheiroService.excluir(id);
    }
}
