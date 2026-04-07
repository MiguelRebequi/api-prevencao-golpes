package com.miguel.api_prevencao_golpes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miguel.api_prevencao_golpes.model.Usuario;
import com.miguel.api_prevencao_golpes.repository.UsuarioRepository;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> findAll(){
        return usuarioRepository.findByAtivoTrue();
    }

    // Esse optional serve para evitar o NPE
    public Optional<Usuario> findUsuarioById(Long id){
        // Stream é uma esteira semelhante ao for imperativo, mas definitivamente mais fácil e limpo de escrever o código
        // Obs: Igualmente ou um pouco mais lento, mas ainda assim pode valer em alguns casos.
            // Esse stream filtra o usuário comparando todos os ID com o ID buscado e entrega o primeiro valor
            // Caso não encontrar, o Optional deve um Vazio sem dar erro de NPE
        return usuarioRepository.findById(id);
    }

    // O Método é igual ao findUsuarioByID, até usa o método para facilitar o processo.
        // Encontra o usuário, caso encontrar retorna true, caso não, retorna falso.
    public boolean disableUsuarioById(Long id){
        return findUsuarioById(id).map(usuario -> {
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }

    public boolean enableUsuarioByID(Long id){
        return findUsuarioById(id).map(usuario -> {
            usuario.setAtivo(true);
            usuarioRepository.save(usuario);
            return true;
        }).orElse(false);
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> updateUsuario(Long id, Usuario dadosNovos){
        return findUsuarioById(id).map(usuario -> {
            usuario.setNomeUsuario(dadosNovos.getNomeUsuario());
            usuario.setEmail(dadosNovos.getEmail());
            usuario.setSenha(dadosNovos.getSenha());
            
            return usuarioRepository.save(usuario);
        });
    }
    
}
