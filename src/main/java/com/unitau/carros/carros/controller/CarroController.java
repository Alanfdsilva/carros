package com.unitau.carros.carros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.unitau.carros.carros.dto.CarroGetDTO;
import com.unitau.carros.carros.dto.CarroPostDTO;
import com.unitau.carros.carros.dto.CarroPutDTO;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.service.CarroService;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @PostMapping
    public Carro criarCarro(@RequestBody CarroPostDTO novoCarro) {
        return carroService.inserirNovoCarro(novoCarro);
    }

    @GetMapping
    public List<CarroGetDTO> listarCarros() {
        return carroService.listarCarros();
    }

    @GetMapping("/{carroId}")
    public Carro buscarCarroPorId(@PathVariable Long carroId) {
        return carroService.buscarCarroPorId(carroId);
    }

    @PutMapping("/{carroId}")
    public Carro atualizarCarro(@PathVariable Long carroId, @RequestBody CarroPutDTO carroAtualizado) {
        return carroService.atualizarCarro(carroId, carroAtualizado);
    }

    @DeleteMapping("/{carroId}")
    public void excluirCarro(@PathVariable Long carroId) {
        carroService.excluirCarro(carroId);
    }
}

