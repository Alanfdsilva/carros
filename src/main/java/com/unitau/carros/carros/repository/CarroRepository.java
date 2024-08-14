package com.unitau.carros.carros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.carros.carros.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro> findByPessoa_idAndId(Long pessoa_id, Long carro_id);
    Optional<Carro> findByPlaca(String placa);
    List<Carro> findByPessoa_id(Long pessoa_id);
    List<Carro> findByMarca_id(Long marca_id);
}
