package com.unitau.carros.carros.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unitau.carros.carros.dto.ContratoEstatisticaDTO;
import com.unitau.carros.carros.dto.ContratoPostDTO;
import com.unitau.carros.carros.dto.ContratoPutDTO;
import com.unitau.carros.carros.model.Contrato;
import com.unitau.carros.carros.service.ContratoService;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public Contrato inserirNovoContrato(@RequestBody ContratoPostDTO novoContrato) {
        return contratoService.inserirNovoContrato(novoContrato);
    }

    @GetMapping
    public List<Contrato> listarContratos() {
        return contratoService.listarContratos();
    }

    @GetMapping("/{contratoId}")
    public Contrato buscarContratoPorId(@PathVariable Long contratoId) {
        return contratoService.buscarContratoPorId(contratoId);
    }

    @GetMapping("/estatisticas")
    public List<ContratoEstatisticaDTO> estatisticas() {
        return contratoService.estatisticas();
    }

    @PutMapping("/{contratoId}")
    public Contrato atualizarContrato(@PathVariable Long contratoId, @RequestBody ContratoPutDTO contratoAtualizado) {
        return contratoService.atualizarContrato(contratoId, contratoAtualizado);
    }

    @PutMapping("/desabilita/{contratoId}")
    public void desabilitaContrato(@PathVariable Long contratoId) {
        contratoService.desabilitaContrato(contratoId);
    }
}


