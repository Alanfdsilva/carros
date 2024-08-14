package com.unitau.carros.carros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unitau.carros.carros.dto.MarcaGetDTO;
import com.unitau.carros.carros.dto.MarcaPostDTO;
import com.unitau.carros.carros.dto.MarcaPutDTO;
import com.unitau.carros.carros.model.Marca;
import com.unitau.carros.carros.service.MarcaService;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @PostMapping
    public Marca criarMarca(@RequestBody MarcaPostDTO novaMarca) {
        return marcaService.inserirNovaMarca(novaMarca);
    }

    @GetMapping
    public List<Marca> listarMarcas() {
        return marcaService.listarMarcas();
    }

    @GetMapping("/{marcaId}")
    public Marca buscarMarcaPorId(@PathVariable Long marcaId) {
        return marcaService.buscarMarcaPorId(marcaId);
    }

    @PutMapping("/{marcaId}")
    public Marca atualizarMarca(@PathVariable Long marcaId, @RequestBody MarcaPutDTO marcaAtualizada) {
        return marcaService.atualizarMarca(marcaId, marcaAtualizada);
    }

    @DeleteMapping("/{marcaId}")
    public void excluirMarca(@PathVariable Long marcaId) {
        marcaService.excluirMarca(marcaId);
    }
}

