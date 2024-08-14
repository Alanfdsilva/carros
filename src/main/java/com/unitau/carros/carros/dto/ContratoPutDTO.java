package com.unitau.carros.carros.dto;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unitau.carros.carros.anotations.CarroFK;
import com.unitau.carros.carros.anotations.PessoaFK;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Contrato;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.repository.CarroRepository;
import com.unitau.carros.carros.repository.PessoaRepository;

public class ContratoPutDTO {
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

    @NotNull
	@PastOrPresent
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "America/Sao_Paulo")	
	private Date createdAt;
    
    @Positive
	private double preco;

    @NotNull
    private Integer contratoStatus;


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

    public Long getPessoaId() {
        return pessoaId;
    }

	public Long getCarroId() {
        return carroId;
    }

    public void update(PessoaRepository pessoaRepository, CarroRepository carroRepository, Contrato item) {
        item.setDataInicio(this.dataInicio);
        item.setDataFinal(this.dataFinal);
        item.setPreco(this.preco);
        item.setContratoStatus(this.contratoStatus);

        Optional<Pessoa> pessoaSearch = pessoaRepository.findById(this.pessoaId);
        if (pessoaSearch.isPresent()) {
            Pessoa pessoa = pessoaSearch.get();
            item.setPessoa(pessoa); 
        }


        Optional<Carro> carroSearch = carroRepository.findById(this.carroId);
        if (carroSearch.isPresent()) {
            Carro carro = carroSearch.get();
            item.setCarro(carro);  
        }
        item.setUpdatedAt(new Date());
    }
}