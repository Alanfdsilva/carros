package com.unitau.carros.carros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.carros.carros.dto.MarcaPostDTO;
import com.unitau.carros.carros.dto.MarcaPutDTO;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Marca;
import com.unitau.carros.carros.repository.CarroRepository;
import com.unitau.carros.carros.repository.MarcaRepository;
import com.unitau.carros.carros.validation.BadRequestException;
import com.unitau.carros.carros.validation.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CarroRepository carroRepository;

    public Marca inserirNovaMarca(MarcaPostDTO novaMarca) {
        return marcaRepository.save(novaMarca.convert());
    }

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Marca buscarMarcaPorId(Long marcaId) {
        Optional<Marca> marcaOptional = marcaRepository.findById(marcaId);
        if (marcaOptional.isPresent()) {
            return marcaOptional.get();
        } else {
            throw new NotFoundException("Marca não encontrada com o ID: " + marcaId);
        }
    }

    public Marca atualizarMarca(Long marcaId, MarcaPutDTO marcaAtualizada) {
        Optional<Marca> marcaOptional = marcaRepository.findById(marcaId);
    
        if (marcaOptional.isPresent()) {
            Marca marcaExistente = marcaOptional.get();    
            marcaExistente.setNome(marcaAtualizada.update().getNome());
            return marcaRepository.save(marcaExistente);
        } else {
            throw new NotFoundException("Marca não encontrada com o ID: " + marcaId);
        }
    }

    public void excluirMarca(Long marcaId) {
        if (marcaRepository.existsById(marcaId)) {
            
            List<Carro> search = carroRepository.findByMarca_id(marcaId);

            if(search.size() > 0) {
                throw new BadRequestException("Essa marca não pode ser deletada, ela possui registro na tabela Carro");
            }

            marcaRepository.deleteById(marcaId);
        } else {
            throw new NotFoundException("Marca não encontrada com o ID: " + marcaId);
        }
    }
}
