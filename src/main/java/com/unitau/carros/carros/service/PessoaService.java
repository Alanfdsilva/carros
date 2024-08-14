package com.unitau.carros.carros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.carros.carros.dto.PessoaPostDTO;
import com.unitau.carros.carros.dto.PessoaPutDTO;
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
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public Pessoa inserirNovaPessoa(PessoaPostDTO novaPessoa) {
        Optional<Pessoa> search = pessoaRepository.findByPessoaCpf(novaPessoa.getPessoaCpf());

        if(search.isPresent()) {
            throw new BadRequestException("CPF já registrado: ");
        }

        return pessoaRepository.save(novaPessoa.convert());
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Long pessoaId) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        if (pessoaOptional.isPresent()) {
            return pessoaOptional.get();
        } else {
            throw new NotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }

    public Pessoa atualizarPessoa(Long pessoaId, PessoaPutDTO pessoaAtualizada) {
        Optional<Pessoa> search = pessoaRepository.findByPessoaCpf(pessoaAtualizada.getPessoaCpf());
        if(search.isPresent() && pessoaId != search.get().getId()) {
            throw new BadRequestException("CPF já registrado: ");
        }
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoaAtualizada.update(pessoa);
            return pessoaRepository.save(pessoa);
        } else {
            throw new NotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }
    

    public void excluirPessoa(Long pessoaId) {
        if (pessoaRepository.existsById(pessoaId)) {

            List<Carro> searchCarro = carroRepository.findByPessoa_id(pessoaId);

            if(searchCarro.size() > 0) {
                throw new BadRequestException("Não é possível deletar esse usuário pois ele possui carros registrados");
            }

            List<Contrato> searchContrato = contratoRepository.findByPessoa_id(pessoaId);

            if(searchContrato.size() > 0) {
                throw new BadRequestException("Não é possível deletar esse usuário pois ele possui registros na tabela contrato");
            }

            pessoaRepository.deleteById(pessoaId);
        } else {
            throw new NotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }
}
