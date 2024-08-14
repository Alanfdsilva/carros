package com.unitau.carros.carros.dto;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.model.Contrato;

public class CarroGetDTO {
	private Long carroId;
    private String modelo;
	private String placa;
	private String tipo;
	private Integer anoFabricacao;
    private Date createdAt;
    private Date updatedAt;
	Long pessoaId;
	String pessoaNome;
	Long marcaId;
	String marcaNome;

    private List<ContratoGetDTO> contratos;

	public CarroGetDTO() {
	}

	public CarroGetDTO(Carro carro) {
		this.carroId = carro.getId();
		this.modelo = carro.getModelo();
        this.placa = carro.getPlaca();
        this.tipo = carro.getTipo();
        this.anoFabricacao = carro.getAnoFabricacao();
        this.createdAt = carro.getCreatedAt();
        this.updatedAt = carro.getUpdatedAt();
		this.pessoaId = carro.getPessoa().getId();
		this.pessoaNome = carro.getPessoa().getPessoaNome();
		this.marcaId = carro.getMarca().getId();
		this.marcaNome = carro.getMarca().getNome();

        List<Contrato> contratos = carro.getContratos();
		if (contratos!=null) {
			this.contratos = ContratoGetDTO.convert(contratos);
		}
	}

	public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
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

	public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getMarcaNome() {
        return marcaNome;
    }

    public void setMarcaNome(String marcaNome) {
        this.marcaNome = marcaNome;
    }
	public List<ContratoGetDTO> getContratos() {
		return contratos;
	}

	public static List<CarroGetDTO> convert(List<Carro> lista) {
		return lista.stream().map(CarroGetDTO::new).collect(Collectors.toList());
	}
}