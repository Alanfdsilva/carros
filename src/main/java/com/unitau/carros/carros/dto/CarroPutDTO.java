package com.unitau.carros.carros.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unitau.carros.carros.model.Carro;

public class CarroPutDTO {
	@NotBlank
	private String modelo;

    @NotBlank
	private String placa;

    @NotBlank
	private String tipo;

    @Positive
	private Integer anoFabricacao;

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

	public void update(Carro item) {
		item.setModelo(this.modelo);
        item.setPlaca(this.placa);
        item.setTipo(this.tipo);
        item.setAnoFabricacao(this.anoFabricacao);
        item.setUpdatedAt(new Date());
	}
}