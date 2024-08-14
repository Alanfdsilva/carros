package com.unitau.carros.carros.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Contrato;

public class PessoaGetDTO {
	private Long pessoaId;
	private String pessoaNome;
    private String pessoaCpf;
    private String pessoaTelefone;
    private Date createdAt;
    private Date updatedAt;
	
	private List<CarroGetDTO> carros;
    private List<ContratoGetDTO> contratos;
	
	public PessoaGetDTO() {	
	}
	
	public PessoaGetDTO(Pessoa pessoa) {
		this.pessoaId = pessoa.getId();
		this.pessoaNome = pessoa.getPessoaNome();
        this.pessoaCpf = pessoa.getPessoaCpf();
        this.pessoaTelefone = pessoa.getPessoaTelefone();
        this.createdAt = pessoa.getCreatedAt();
        this.updatedAt = pessoa.getUpdatedAt();
		List<Carro> carros = pessoa.getCarros();
		if (carros!=null) {
			this.carros = CarroGetDTO.convert(carros);
		}
        List<Contrato> contratos = pessoa.getContratos();
		if (contratos!=null) {
			this.contratos = ContratoGetDTO.convert(contratos);
		}
	}
	
	public Long getPessoaId() {
		return pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

    public String getPessoaCpf() {
		return pessoaCpf;
	}

    public String getPessoaTelefone() {
		return pessoaTelefone;
	}

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<CarroGetDTO> getCarros() {
		return carros;
	}
	
	public static List<PessoaGetDTO> convert(List<Pessoa> lista) {
		return lista.stream().map(PessoaGetDTO::new).collect(Collectors.toList());
	}

	public List<ContratoGetDTO> getContratos() {
		return contratos;
	}
}