package com.unitau.carros.carros.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.carros.carros.dto.ContratoEstatisticaDTO;
import com.unitau.carros.carros.dto.ContratoPostDTO;
import com.unitau.carros.carros.dto.ContratoPutDTO;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Contrato;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.repository.CarroRepository;
import com.unitau.carros.carros.repository.ContratoRepository;
import com.unitau.carros.carros.repository.PessoaRepository;
import com.unitau.carros.carros.validation.BadRequestException;
import com.unitau.carros.carros.validation.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CarroRepository carroRepository;

    public Contrato inserirNovoContrato(ContratoPostDTO novoContrato) {
        Contrato contrato = novoContrato.convert(pessoaRepository, carroRepository);
        Pessoa pessoa = contrato.getPessoa();
        Carro carro = contrato.getCarro();

        if (pessoa == null)  {
            throw new NotFoundException("Pessoa não encontrada na base de dados");
        }

        if(carro == null) {
            throw new NotFoundException("Carro não encontrada na base de dados");
        }

        if (carroRepository.findByPessoa_idAndId(pessoa.getId(), carro.getId()).isPresent()) {
            throw new BadRequestException("Não é possível criar o contrato, a pessoa já é o dono do carro");
        }

        return contratoRepository.save(contrato);
    }

    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    public Contrato buscarContratoPorId(Long contratoId) {
        return contratoRepository.findById(contratoId)
                .orElseThrow(() -> new NotFoundException("Contrato não encontrado com o ID: " + contratoId));
    }

    public Contrato atualizarContrato(Long contratoId, ContratoPutDTO contratoAtualizado) {
        Optional<Contrato> contratoExistente = contratoRepository.findById(contratoId);
    
        if (!contratoExistente.isPresent()) {
            throw new NotFoundException("Contrato não encontrado na base de dados");
        }
    
        Contrato contrato = contratoExistente.get();
    
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(contratoAtualizado.getPessoaId());
    
        if (!pessoaOptional.isPresent()) {
            throw new NotFoundException("Pessoa não encontrada na base de dados");
        }
    
        Optional<Carro> carroOptional = carroRepository.findById(contratoAtualizado.getCarroId());
    
        if (!carroOptional.isPresent()) {
            throw new NotFoundException("Carro não encontrado na base de dados");
        }
    
        Pessoa pessoa = pessoaOptional.get();
        Carro carro = carroOptional.get();
    
        if (carro.getPessoa().getId().equals(pessoa.getId())) {
            throw new BadRequestException("Não é possível atualizar o contrato, a pessoa é a dona do carro");
        }
    
        contratoAtualizado.update(pessoaRepository, carroRepository, contrato);
        return contratoRepository.save(contrato);
    }

    public List<ContratoEstatisticaDTO> estatisticas() {
        return contratoRepository.obterEstatisticasContrato();
    }
    

    public void desabilitaContrato(Long contratoId) {
        if (contratoRepository.existsById(contratoId)) {

            contratoRepository.desabilitaContrato(contratoId);
        } else {
            throw new NotFoundException("Contrato não encontrado com o ID: " + contratoId);
        }
    }
}
