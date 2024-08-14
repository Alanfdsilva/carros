package com.unitau.carros.carros.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.unitau.carros.carros.model.Marca;
public class MarcaGetDTO {
	private Long marcaId;
	private String nome;
    private Date createdAt;
    private Date updatedAt;
	
	public MarcaGetDTO() {	
	}
	
	public MarcaGetDTO(Marca marca) {
		this.marcaId = marca.getId();
		this.nome = marca.getNome();
        this.createdAt = marca.getCreatedAt();
        this.updatedAt = marca.getUpdatedAt();
	}
	
	public Long getMarcaId() {
        return marcaId;
    }

	public String getNome() {
		return nome;
	}

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
	
	public static List<MarcaGetDTO> convert(List<Marca> lista) {
		return lista.stream().map(MarcaGetDTO::new).collect(Collectors.toList());
	}
}