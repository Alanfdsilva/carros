package com.unitau.carros.carros.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unitau.carros.carros.dto.ContratoEstatisticaDTO;
import com.unitau.carros.carros.model.Contrato;


public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    @Query(value = "SELECT carro.modelo, COUNT(*) AS quantidade, AVG(contrato.preco) AS media " +
        "FROM Carro, Contrato " +
        "WHERE contrato.carro_id = carro.id " +
        "GROUP BY carro.modelo " +
        "ORDER BY carro.modelo", nativeQuery = true)
    List<ContratoEstatisticaDTO> obterEstatisticasContrato();
    List<Contrato> findByPessoa_id(Long pessoa_id);
    List<Contrato> findByCarro_id(Long carro_id);
    @Transactional
    @Modifying
    @Query("UPDATE Contrato c SET c.contratoStatus = 0 WHERE c.id = :contratoId")
    void desabilitaContrato(@Param("contratoId") Long contratoId);
}
