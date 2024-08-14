package com.unitau.carros.carros.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unitau.carros.carros.model.Pessoa;

public class PessoaPutDTO {
	@NotBlank
	private String pessoaNome;

    @NotBlank
	private String pessoaCpf;

    @NotBlank
	private String pessoaTelefone;


	public String getPessoaNome() {
		return pessoaNome;
	}

    public String getPessoaCpf() {
		return pessoaCpf;
	}

    public String getPessoaTelefone() {
		return pessoaTelefone;
	}

	public void update(Pessoa item) {
		item.setPessoaNome(this.pessoaNome);
        item.setPessoaCpf(this.pessoaCpf);
        item.setPessoaTelefone(this.pessoaTelefone);
        item.setUpdatedAt(new Date());
	}
}