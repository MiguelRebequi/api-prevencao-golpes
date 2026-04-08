package com.miguel.api_prevencao_golpes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.miguel.api_prevencao_golpes.model.Usuario;
import com.miguel.api_prevencao_golpes.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findByAtivoTrue();
    }

    // Esse optional serve para evitar o NPE
    public Optional<Usuario> findUsuarioById(Long id) {
        // Stream é uma esteira semelhante ao for imperativo, mas definitivamente mais fácil e limpo de escrever o código
        // Obs: Igualmente ou um pouco mais lento, mas ainda assim pode valer em alguns casos.
        // Esse stream filtra o usuário comparando todos os ID com o ID buscado e entrega o primeiro valor
        // Caso não encontrar, o Optional deve um Vazio sem dar erro de NPE
        return usuarioRepository.findByIdAndAtivoTrue(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    

    // O Método é igual ao findUsuarioByID, até usa o método para facilitar o processo.
    // Encontra o usuário, caso encontrar retorna true, caso não, retorna falso.
    public void disableUsuarioById(Long id) {
        Usuario usuario = findUsuarioById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public void enableUsuarioByID(Long id) {
        Usuario usuario = findUsuarioById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public Usuario updateUsuarioById(Long id, Usuario dadosNovos) {
        return findUsuarioById(id).map(usuarioExistente -> {
            usuarioExistente.setNomeUsuario(dadosNovos.getNomeUsuario());
            usuarioExistente.setEmail(dadosNovos.getEmail());
            usuarioExistente.setSenha(dadosNovos.getSenha());

            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para o ID: " + id));
    }

}
