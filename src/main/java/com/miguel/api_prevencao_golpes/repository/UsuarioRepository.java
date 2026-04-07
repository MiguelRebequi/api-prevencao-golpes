package com.miguel.api_prevencao_golpes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguel.api_prevencao_golpes.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método especial para procurar apenas os usuários ativos no banco de dados.
    List<Usuario> findByAtivoTrue();
}
