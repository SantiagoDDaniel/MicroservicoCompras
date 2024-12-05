package com.wine.repository;

import com.wine.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.compras WHERE c.cpf = :cpf")
    Optional<ClienteEntity> findByCpf(@Param("cpf") String cpf);
}
