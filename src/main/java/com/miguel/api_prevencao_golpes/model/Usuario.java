package com.miguel.api_prevencao_golpes.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    // Troquei o nome do atributo para o padrão do Banco de Dados, não pode ser nulo e o limie de caracteres é 100. (Deixei o limite maior que o conhecido no mundo)
    @Column(name = "nome_usuario", nullable = false, length = 100)
    private String nomeUsuario;

    // Futuramente faremos algum meio de ocultar a senha para não estar exposta no meu banco de dados.
    // não pode ser nulo, único e o limie de caracteres é 255. (Como futuramente, a ideia seria inserir uma criptografia, apenas reforcei o limite comum de um dado na tabela) 
    @Column(nullable = false, unique = false, length = 255)
    private String senha;

    // Por enquanto, acredito que não usaremos tão cedo o Email.
    // não pode ser nulo, único e o limie de caracteres é 150.
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Inicialmente, colocarei por permitir nulo, pois eu não sei se usarei o CPF para algo)
    // não pode ser nulo, único e o limie de caracteres é 11.
    // Obs: Por padrão, permite nulo, mas deixarei para poder inverter, caso ache melhor.
    @Column(nullable = true, unique = true, length = 11)
    private String cpf;

    // Estado de ativo ou desativo para os métodos de CRUD, ou seja, não irei deletar os dados apenas desativa-los.
        // Especifiquei que não pode haver tipo Nulo
    @Column(nullable = false)
    private boolean ativo = true;

    // Mapei qual o outro atributo da outra classe pego.
    // Tudo feito a classe Usuário será feito a classe cartão
    // Se um cartão estiver sem dono, será excluído.
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes = new ArrayList<>();

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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Cartao> getCartoes(){
        return cartoes;
    }

    public void adicionarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
        cartao.setUsuario(this); // Garantindo que todo cartão tem um dono especificado.
    }

    public void removerCartao(Cartao cartao) {
        this.cartoes.remove(cartao);
        cartao.setUsuario(null); // Remove o vínculo com o antigo dono.
    }

}
