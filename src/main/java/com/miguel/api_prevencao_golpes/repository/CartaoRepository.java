package com.miguel.api_prevencao_golpes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguel.api_prevencao_golpes.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByIdAndAtivoTrue(Long id);
    List<Cartao> findByUsuarioIdAndAtivoTrue(Long usuarioId);
}
