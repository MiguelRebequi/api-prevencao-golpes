package com.miguel.api_prevencao_golpes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguel.api_prevencao_golpes.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    
}
