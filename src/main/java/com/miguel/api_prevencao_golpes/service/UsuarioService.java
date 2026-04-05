package com.miguel.api_prevencao_golpes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.miguel.api_prevencao_golpes.model.Usuario;

@Service
public class UsuarioService {
    // Assim como a professora inicialmente, eu farei uma lista ao invés do banco de dados temporariamente
    private static final List<Usuario> usuarios = new ArrayList<>();
    // Precisamos criar um contador de ID, a professora usou o Atomic, segundo minhas pesquisas, essa classe permite a inserção sem ter a possibilidade de dois ID's iguais, por exemplo.
    private static final AtomicLong contador = new AtomicLong();

    public List<Usuario> findAll(){
        return usuarios.stream().filter(Usuario::isAtivo).toList();
    }

    // Esse optional serve para evitar o NPE
    public Optional<Usuario> findUsuarioById(Long id){
        // Stream é uma esteira semelhante ao for imperativo, mas definitivamente mais fácil e limpo de escrever o código
        // Obs: Igualmente ou um pouco mais lento, mas ainda assim pode valer em alguns casos.
            // Esse stream filtra o usuário comparando todos os ID com o ID buscado e entrega o primeiro valor
            // Caso não encontrar, o Optional deve um Vazio sem dar erro de NPE
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
    }

    // O Método é igual ao findUsuarioByID, até usa o método para facilitar o processo.
        // Encontra o usuário, caso encontrar retorna true, caso não, retorna falso.
    public boolean disableUsuarioById(Long id){
        Optional<Usuario> usuarioEncontrado = findUsuarioById(id);
        if(usuarioEncontrado.isPresent()){
            usuarioEncontrado.get().setAtivo(false);
            return true;
        }
        return false;
    }

    public boolean enableUsuarioByID(Long id){
        Optional<Usuario> usuarioEncontrado = findUsuarioById(id);
        if(usuarioEncontrado.isPresent()){
            usuarioEncontrado.get().setAtivo(true);
            return true;
        }
        
        return false;
    }

    public Usuario saveUsuario(Usuario usuario){
        usuario.setId(contador.incrementAndGet());
        usuarios.add(usuario);
        return usuario;
    }

    public Optional<Usuario> updateUsuario(Long id, Usuario dadosNovos){
        Optional<Usuario> usuarioAntigoOpt = findUsuarioById(id);

        if(usuarioAntigoOpt.isPresent()){
            Usuario usuarioAntigo = usuarioAntigoOpt.get();

            //Os únicos campos mutáveis da minha aplicação.
            usuarioAntigo.setNomeUsuario(dadosNovos.getNomeUsuario());
            usuarioAntigo.setEmail(dadosNovos.getEmail());
            usuarioAntigo.setSenha(dadosNovos.getSenha());

            return Optional.of(usuarioAntigo);
        }

        return Optional.empty();
    }
    
}
