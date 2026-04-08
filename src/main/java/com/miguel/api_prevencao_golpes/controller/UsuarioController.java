package com.miguel.api_prevencao_golpes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.api_prevencao_golpes.model.Usuario;
import com.miguel.api_prevencao_golpes.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Métodos de requisições
    // Em ordem: Get, Get(Por id), Post, Put, Delete
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Usuario> getUsuariosById(@PathVariable Long id) {
        // Uma Classe Optional usuario atribuida pelo metodo de findUsuarioByID(Evitando a possibilidade de vir um nulo)
        Optional<Usuario> usuario = usuarioService.findUsuarioById(id);
        // Verifica se usuário tem os dados, se tiver entrega um OK, se não envia um erro 404
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Uma requisição post com RequestBody(Um Json) do Objeto Usuário
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Usuario> updateUsuarioById(@PathVariable Long id, @RequestBody Usuario dadosNovos) {
        Usuario usuarioAtualizado = usuarioService.updateUsuarioById(id, dadosNovos);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id) {
        usuarioService.disableUsuarioById(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reactivateUsuarioById(@PathVariable Long id) {
        usuarioService.enableUsuarioByID(id);
        return ResponseEntity.noContent().build();
    }
}
