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

import com.miguel.api_prevencao_golpes.model.Cartao;
import com.miguel.api_prevencao_golpes.repository.CartaoRepository;
import com.miguel.api_prevencao_golpes.service.CartaoService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    // Métodos de requisições
    // Em ordem: Get(Por id), Get(Listar todos por ID), Post, Put, Delete
    // Método listar por id
    @GetMapping("/{cartaoId}")
    public ResponseEntity<Cartao> getCartaoById(@PathVariable Long usuarioId, @PathVariable Long cartaoId){
        Optional<Cartao> cartao = cartaoService.findCartaoById(cartaoId);
        return cartao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        
    }
    // Método listar todos
    @GetMapping
    public ResponseEntity<List<Cartao>> getAllCartoesByUsuarioId(@PathVariable Long usuarioId) {
        List<Cartao> cartoes = cartaoService.findAllById(usuarioId);
        return ResponseEntity.ok(cartoes);
    }

    // Método de criar um Cartão
    @PostMapping
    public ResponseEntity<Cartao> createCartao(@PathVariable Long usuarioId, @RequestBody Cartao cartaoNovo) {
        Cartao cartaoSalvo = cartaoService.saveCartao(usuarioId, cartaoNovo);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoSalvo);
    }

    

    // Método de Atualizar um Cartão
    @PutMapping("/{cartaoId}")
    public ResponseEntity<Cartao> updateCartaoById(@PathVariable Long usuarioId, @PathVariable Long cartaoId, @RequestBody Cartao dadosNovos) {
        Cartao cartaoAtualizado = cartaoService.updateCartaoById(cartaoId, dadosNovos);

        return ResponseEntity.ok(cartaoAtualizado);
    }

    // Método de "Deletar"(Soft Delete) um Cartão
    @DeleteMapping("/{cartaoId}")
    public ResponseEntity<Void> deleteCartaoById(@PathVariable Long usuarioId, @PathVariable Long cartaoId) {
        cartaoService.disableCartaoById(cartaoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cartaoId}/reativar")
    public ResponseEntity<Void> reactiveCartaoById(@PathVariable Long usuarioId, @PathVariable Long cartaoId) {
        cartaoService.enableCartaoById(cartaoId);
        return ResponseEntity.noContent().build();
    }
}
