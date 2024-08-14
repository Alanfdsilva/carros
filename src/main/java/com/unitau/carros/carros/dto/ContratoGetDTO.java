package com.unitau.carros.carros.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.unitau.carros.carros.model.Contrato;

public class ContratoGetDTO {
	private Long contratoId ;
    private Integer contratoStatus;
	private Date dataInicio;
	private Date dataFinal;
	private double preco;
    private Date createdAt;
    private Date updatedAt;
	Long pessoaId;
	String pessoaNome;
	String pessoaCpf;
	Long carroId;
	String carroModelo;
	String carroPlaca;

	public ContratoGetDTO() {
	}

	public ContratoGetDTO(Contrato contrato) {
		this.contratoId = contrato.getId();
		this.contratoStatus = contrato.getContratoStatus();
        this.dataInicio = contrato.getDataInicio();
        this.dataFinal = contrato.getDataFinal();
        this.preco = contrato.getPreco();
        this.createdAt = contrato.getCreatedAt();
        this.updatedAt = contrato.getUpdatedAt();
		this.pessoaId = contrato.getPessoa().getId();
		this.pessoaNome = contrato.getPessoa().getPessoaNome();
		this.pessoaCpf = contrato.getPessoa().getPessoaCpf();
		this.carroId = contrato.getCarro().getId();
		this.carroModelo = contrato.getCarro().getModelo();
		this.carroPlaca = contrato.getCarro().getPlaca();
	}

	public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    public Long getContratoId() {
        return contratoId;
    }

    public void setContratoId(Long contratoId) {
        this.contratoId = contratoId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getContratoStatus() {
        return contratoStatus;
    }

    public void setContratoStatus(Integer contratoStatus) {
        this.contratoStatus = contratoStatus;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public String getPessoaCpf() {
        return pessoaCpf;
    }

    public void setPessoaCpf(String pessoaCpf) {
        this.pessoaCpf = pessoaCpf;
    }


    public String getCarroModelo() {
        return carroModelo;
    }

    public void setCarroModelo(String carroModelo) {
        this.carroModelo = carroModelo;
    }

    public String getCarroPlaca() {
        return carroPlaca;
    }

    public void setCarroPlaca(String carroPlaca) {
        this.carroPlaca = carroPlaca;
    }

	public static List<ContratoGetDTO> convert(List<Contrato> lista) {
		return lista.stream().map(ContratoGetDTO::new).collect(Collectors.toList());
	}
}