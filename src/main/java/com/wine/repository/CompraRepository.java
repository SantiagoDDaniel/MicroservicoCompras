package com.wine.repository;

import com.wine.model.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {
    List<CompraEntity> findByCliente_Cpf(String cpf);
}
