package com.miguel.api_prevencao_golpes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabela_usuarios")
public class Usuario {

    // Criarei os primeiros atributos do projeto, no momento serão apenas esses os mais importante, o MVP seria esse:
    // ---
    // Primeiramente, criaremos em ID, caso a professora ou tiver tempo para aprender o UUID seja uma ideia implementada no futuro.
    // Dúvida: Long ou long? Como havia sentido, criar inicialmente como Long para não precisar no construtor inserir o ID e ser feito depois em um método incremental.
    // Para colocar como padrão, colocarei o ID na primeira linha dos atributos por ser Identificador único.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;
    // Futuramente faremos algum meio de ocultar a senha para não estar exposta no meu banco de dados.
    @Column(nullable = false, unique = false)
    private String senha;
    // Por enquanto, acredito que não usaremos tão cedo o Email.
    @Column(nullable = false, unique = true)
    private String email;
    // Inicialmente, colocarei por permitir nulo, pois eu não sei se usarei o CPF para algo)
    // Por padrão, permite nulo, mas deixarei para poder inverter, caso ache melhor.
    @Column(nullable = true, unique = true)
    private String cpf;

    // Criarei um construtor vazio e um construtor com atributos(Mockados?)
    // Esse serve para a Spring
    public Usuario() {
    }

    // Esse serve para testar e fazer o Postman, por exemplo.
        // Observa que eu criei sem o ID por ser incremental pelo Atomic em UsuarioService.
    public Usuario(String nomeUsuario, String senha, String email, String cpf) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
