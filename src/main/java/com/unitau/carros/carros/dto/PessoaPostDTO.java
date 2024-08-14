package com.unitau.carros.carros.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.unitau.carros.carros.model.Pessoa;

public class PessoaPostDTO {
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

	public Pessoa convert() {
		Pessoa ret = new Pessoa();
		ret.setPessoaNome(this.pessoaNome);
        ret.setPessoaCpf(this.pessoaCpf);
        ret.setPessoaTelefone(this.pessoaTelefone);
        ret.setCreatedAt(new Date());
        ret.setUpdatedAt(new Date());
		return ret;
	}
}