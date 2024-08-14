// PessoaRepository.java
package com.unitau.carros.carros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unitau.carros.carros.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByPessoaCpf(String cpf);
}
