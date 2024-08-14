package com.unitau.carros.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.carros.carros.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}

