
package com.unitau.carros.carros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unitau.carros.carros.dto.PessoaPostDTO;
import com.unitau.carros.carros.dto.PessoaPutDTO;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.service.PessoaService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pessoas") // Mapeia todas as solicitações a partir de "/pessoas"
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Endpoint para criar uma nova pessoa
    @PostMapping
    public Pessoa criarPessoa(@RequestBody PessoaPostDTO novaPessoa) {
        return pessoaService.inserirNovaPessoa(novaPessoa);
    }

    // Endpoint para buscar todas as pessoas
    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    // Endpoint para buscar uma pessoa por ID
    @GetMapping("/{pessoaId}")
    public Pessoa buscarPessoaPorId(@PathVariable Long pessoaId) {
        return pessoaService.buscarPessoaPorId(pessoaId);
    }

    // Endpoint para atualizar uma pessoa por ID
    @PutMapping("/{pessoaId}")
    public Pessoa atualizarPessoa(@PathVariable Long pessoaId, @RequestBody PessoaPutDTO camposAtualizados) {
        return pessoaService.atualizarPessoa(pessoaId, camposAtualizados);
    }

    // Endpoint para excluir uma pessoa por ID
    @DeleteMapping("/{pessoaId}")
    public void excluirPessoa(@PathVariable Long pessoaId) {
        pessoaService.excluirPessoa(pessoaId);
    }
}