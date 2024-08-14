// Pessoa.java (Classe de modelo)
package com.unitau.carros.carros.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pessoaNome;
    private String pessoaCpf;
    private String pessoaTelefone;
    private Date createdAt;
    private Date updatedAt;

    @JsonManagedReference
	@OneToMany(mappedBy="pessoa")
	private List<Contrato> contratos;

    @JsonManagedReference
	@OneToMany(mappedBy="pessoa")
	private List<Carro> carros;

    // Getter e Setter para pessoaId
    public Long getId() {
        return id;
    }

    public void setId(Long pessoaId) {
        this.id = pessoaId;
    }

    // Getter e Setter para pessoaNome
    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    // Getter e Setter para pessoaCpf
    public String getPessoaCpf() {
        return pessoaCpf;
    }

    public void setPessoaCpf(String pessoaCpf) {
        this.pessoaCpf = pessoaCpf;
    }

    // Getter e Setter para pessoaTelefone
    public String getPessoaTelefone() {
        return pessoaTelefone;
    }

    public void setPessoaTelefone(String pessoaTelefone) {
        this.pessoaTelefone = pessoaTelefone;
    }

    // Getter e Setter para createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter e Setter para updatedAt
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Contrato> getContratos() {
		return contratos;
	}

    public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

    public List<Carro> getCarros() {
		return carros;
	}

    public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
}
