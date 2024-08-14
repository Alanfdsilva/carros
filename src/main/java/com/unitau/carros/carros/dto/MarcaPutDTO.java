package com.unitau.carros.carros.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.unitau.carros.carros.model.Marca;

public class MarcaPutDTO {
	@NotBlank
	private String nome;


	public String getNome() {
		return nome;
	}

	public Marca update() {
		Marca ret = new Marca();
		ret.setNome(this.nome);
        ret.setUpdatedAt(new Date());
		return ret;
	}
}