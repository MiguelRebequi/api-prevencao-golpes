package com.miguel.api_prevencao_golpes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.miguel.api_prevencao_golpes.model.Cartao;
import com.miguel.api_prevencao_golpes.model.Usuario;
import com.miguel.api_prevencao_golpes.repository.CartaoRepository;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Cartao> findAllById(Long usuarioId) {
        return cartaoRepository.findByUsuarioIdAndAtivoTrue(usuarioId);
    }

    public Optional<Cartao> findCartaoById(Long cartaoId) {
        return cartaoRepository.findByIdAndAtivoTrue(cartaoId);
    }

    public Cartao saveCartao(Long usuarioId, Cartao cartaoNovo) {
        return usuarioService.findUsuarioById(usuarioId).map(usuarioEncontrado -> {
            usuarioEncontrado.adicionarCartao(cartaoNovo);
            return cartaoRepository.save(cartaoNovo);

        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + usuarioId));
    }

    

    public void disableCartaoById(Long cartaoId) {
        Cartao cartao = cartaoRepository.findById(cartaoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        cartao.setAtivo(false);
        cartaoRepository.save(cartao);
    }

    public void enableCartaoById(Long cartaoId) {
        Cartao cartao = cartaoRepository.findById(cartaoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        cartao.setAtivo(true);
        cartaoRepository.save(cartao);
    }

    public Cartao updateCartaoById(Long cartaoId, Cartao dadosNovos){
        return findCartaoById(cartaoId).map(cartaoExistente -> {
            cartaoExistente.setSaldo(dadosNovos.getSaldo());
            cartaoExistente.setTipo(dadosNovos.getTipo());
            return cartaoRepository.save(cartaoExistente);
        }).orElseThrow(() -> new RuntimeException("Cartão não encontrado com ID: " + cartaoId));
    }
}
