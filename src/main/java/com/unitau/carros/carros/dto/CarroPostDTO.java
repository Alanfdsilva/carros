package com.unitau.carros.carros.dto;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.unitau.carros.carros.anotations.PessoaFK;
import com.unitau.carros.carros.anotations.MarcaFK;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.model.Marca;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.repository.PessoaRepository;
import com.unitau.carros.carros.repository.MarcaRepository;

public class CarroPostDTO {
	@PessoaFK
	Long pessoaId;
	@MarcaFK
	Long marcaId;

    @NotBlank
	private String modelo;

    @NotBlank
	private String placa;

    @NotBlank
	private String tipo;

    @Positive
	private Integer anoFabricacao;


	public Long getPessoaId() {
        return pessoaId;
    }


	public Long getMarcaId() {
        return marcaId;
    }
	
	public String getModelo(){
		return modelo;
	}

    public String getPlaca(){
		return placa;
	}

	public String getTipo(){
		return tipo;
	}

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

	public Carro convert(PessoaRepository pessoaRepository, MarcaRepository marcaRepository) {
		Carro ret = new Carro();
		ret.setModelo(this.modelo);
		ret.setPlaca(this.placa);
        ret.setTipo(this.tipo);
        ret.setAnoFabricacao(this.anoFabricacao);
        ret.setCreatedAt(new Date());
        ret.setUpdatedAt(new Date());
        Optional<Pessoa> pessoaSearch = pessoaRepository.findById(this.pessoaId);
        if (pessoaSearch.isPresent()) {
        	Pessoa pessoa = pessoaSearch.get();
        	ret.setPessoa(pessoa);
        }
        Optional<Marca> marcaSearch = marcaRepository.findById(this.marcaId);
        if (marcaSearch.isPresent()) {
        	Marca marca = marcaSearch.get();
        	ret.setMarca(marca);
        }
		return ret;
	}
}