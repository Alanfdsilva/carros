package com.unitau.carros.carros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.carros.carros.dto.CarroGetDTO;
import com.unitau.carros.carros.dto.CarroPostDTO;
import com.unitau.carros.carros.dto.CarroPutDTO;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Contrato;
import com.unitau.carros.carros.model.Marca;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.repository.CarroRepository;
import com.unitau.carros.carros.repository.ContratoRepository;
import com.unitau.carros.carros.repository.MarcaRepository;
import com.unitau.carros.carros.repository.PessoaRepository;
import com.unitau.carros.carros.validation.BadRequestException;
import com.unitau.carros.carros.validation.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private MarcaRepository marcaRepository;


    public Carro inserirNovoCarro(CarroPostDTO novoCarro) {
        Carro carro = novoCarro.convert(pessoaRepository, marcaRepository);

        Optional<Carro> search = carroRepository.findByPlaca(novoCarro.getPlaca());

        if(search.isPresent()) {
            throw new NotFoundException("Já possui registro dessa placa no banco de dados");
        }

        Pessoa pessoa = carro.getPessoa();
        Marca marca = carro.getMarca();

        if (pessoa == null)  {
            throw new NotFoundException("Pessoa não encontrada na base de dados");
        }

        if(marca == null) {
            throw new NotFoundException("Marca não encontrada na base de dados");
        }

        return carroRepository.save(carro);
    }

    public List<CarroGetDTO> listarCarros() {
        List<Carro> lista = carroRepository.findAll();
        return CarroGetDTO.convert(lista);
    }

    public Carro buscarCarroPorId(Long carroId) {
        Optional<Carro> carroOptional = carroRepository.findById(carroId);
        if (carroOptional.isPresent()) {
            return carroOptional.get();
        } else {
            throw new NotFoundException("Carro não encontrado com o ID: " + carroId);
        }
    }

    public Carro atualizarCarro(Long carroId, CarroPutDTO carroAtualizado) {
        Optional<Carro> carroOptional = carroRepository.findById(carroId);
        if (carroOptional.isPresent()) {
            Optional<Carro> search = carroRepository.findByPlaca(carroAtualizado.getPlaca());

            if(search.isPresent() && carroId != search.get().getId()) {
                throw new BadRequestException("Já possui registro dessa placa no banco de dados");
            }

            Carro carroExistente = carroOptional.get(); 
            carroAtualizado.update(carroExistente); 
            return carroRepository.save(carroExistente);
        }

        throw new NotFoundException("Carro não encontrado com o ID: " + carroId);
    }

    public void excluirCarro(Long carroId) {
        if (carroRepository.existsById(carroId)) {

            List<Contrato> searchContrato = contratoRepository.findByCarro_id(carroId);

            if(searchContrato.size() > 0) {
                throw new BadRequestException("Não é possível deletar esse usuário pois ele possui registros na tabela contrato");
            }

            carroRepository.deleteById(carroId);
        } else {
            throw new NotFoundException("Carro não encontrado com o ID: " + carroId);
        }
    }
}

