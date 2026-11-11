package com.sistema.os.service;

import com.sistema.os.model.Cozinheiro;
import com.sistema.os.repository.CozinheiroRepository;
import com.sistema.os.repository.ReceitaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class CozinheiroService {
    private final CozinheiroRepository cozinheiroRepository;
    private final ReceitaRepository receitaRepository;

    public CozinheiroService(CozinheiroRepository cozinheiroRepository, ReceitaRepository receitaRepository) {
        this.cozinheiroRepository = cozinheiroRepository;
        this.receitaRepository = receitaRepository;
    }

    public List<Cozinheiro> listarTodos() {
        return cozinheiroRepository.findAll();
    }

    public Cozinheiro buscarPorId(Long id) {
        return cozinheiroRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cozinheiro não foi encontrado: " + id));
    }

    // REGRA 1: não pode existir dois cozinheiros com o mesmo CPF
    public Cozinheiro salvar(Cozinheiro cozinheiro) {
         if (cozinheiroRepository.existsByCpf(cozinheiro.getCpf())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Cozinheiro com CPF já cadastrado");
         }
        return cozinheiroRepository.save(cozinheiro);
    }

    public Cozinheiro atualizar(Long id, Cozinheiro dados) {
        Cozinheiro cozinheiro = buscarPorId(id);
        cozinheiro.setNome(dados.getNome());
        cozinheiro.setTelefone(dados.getTelefone());
        cozinheiro.setEmail(dados.getEmail());
        return cozinheiroRepository.save(cozinheiro);
    }

    // REGRA 2: cozinheiro que tem receitas registradas não ser excluído
    public void excluir(Long id) {
        Cozinheiro cozinheiro = buscarPorId(id);

        if (receitaRepository.existsByCozinheiroId(id)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Esse cozinheiro tem receitas vinculadas, não pode ser excluído"
            );
        }
        cozinheiroRepository.delete(cozinheiro);
    }

}
