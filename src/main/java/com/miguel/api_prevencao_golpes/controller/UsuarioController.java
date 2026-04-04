package com.miguel.api_prevencao_golpes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<Usuario> getAllUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Usuario> getUsuariosById(@PathVariable Long id) {
        // Uma Classe Optional usuario atribuida pelo metodo de findUsuarioByID(Evitando a possibilidade de vir um nulo)
        Optional<Usuario> usuario = usuarioService.findUsuariobyID(id);
        // Verifica se usuário tem os dados, se tiver entrega um OK, se não envia um erro 404
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Uma requisição post com RequestBody(Um Json) do Objeto Usuário
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

}
