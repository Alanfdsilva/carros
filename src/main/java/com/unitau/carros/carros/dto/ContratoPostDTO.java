package com.unitau.carros.carros.dto;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.unitau.carros.carros.anotations.PessoaFK;
import com.unitau.carros.carros.anotations.CarroFK;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Contrato;
import com.unitau.carros.carros.repository.PessoaRepository;
import com.unitau.carros.carros.repository.CarroRepository;

public class ContratoPostDTO {
	@PessoaFK
	Long pessoaId;
	@CarroFK
	Long carroId;

    @NotNull
	@PastOrPresent
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "America/Sao_Paulo")	
	private Date dataInicio;

    @NotNull
	@PastOrPresent
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "America/Sao_Paulo")	
	private Date dataFinal;

    @Positive
	private Double preco;

    @NotNull
    private Integer contratoStatus;


	public Long getPessoaId() {
        return pessoaId;
    }

	public Long getCarroId() {
        return carroId;
    }

	public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getContratoStatus() {
        return contratoStatus;
    }

	public Contrato convert(PessoaRepository pessoaRepository, CarroRepository carroRepository) {
	    Contrato ret = new Contrato();
		ret.setDataInicio(this.dataInicio);
		ret.setDataFinal(this.dataFinal);
        ret.setPreco(this.preco);
        ret.setContratoStatus(this.contratoStatus);
        ret.setCreatedAt(new Date());
        ret.setUpdatedAt(new Date());
        Optional<Pessoa> pessoaSearch = pessoaRepository.findById(this.pessoaId);
        if (pessoaSearch.isPresent()) {
        	Pessoa pessoa = pessoaSearch.get();
        	ret.setPessoa(pessoa);
        }
        Optional<Carro> carroSearch = carroRepository.findById(this.carroId);
        if (carroSearch.isPresent()) {
        	Carro carro = carroSearch.get();
        	ret.setCarro(carro);
        }
		return ret;
	}
}