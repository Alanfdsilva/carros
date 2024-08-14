package com.unitau.carros.carros.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unitau.carros.carros.model.Marca;


public class MarcaPostDTO {
	@NotBlank
	private String nome;


	public String getNome() {
		return nome;
	}

	public Marca convert() {
		Marca ret = new Marca();
		ret.setNome(this.nome);
        ret.setCreatedAt(new Date());
        ret.setUpdatedAt(new Date());
		return ret;
	}
}