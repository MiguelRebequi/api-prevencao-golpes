package com.miguel.api_prevencao_golpes.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cartao {

    // Semelhante ao ID do usuário.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Evitaremos pegar o PAN(Aquele digitos do cartão) para meios de segurança
    // Troquei o nome do atributo para o padrão do Banco de Dados, não pode ser nulo e o limie de caracteres é 4.
        // Obs: São os últimos 4 digitos armazenado, assim como indicado.
    @Column(name = "final_numero", nullable = false, length = 4)
    private String finalNumero;
    
    // Simplesmente não pode ser nulo.
    @Column(nullable = false)
    private BigDecimal saldo; // Manteremos o tipo de BigDecimal para ter a melhor precisão possível, pois dinheiro não pode ter imprecisão!!!

    // Indiquei para o banco de dados que esse ENUM é do tipo String, salvando a palavra do ENUM.
    // Não pode ser nulo e o limie de caracteres é 20. (Coloquei o limite maior que estamos precisando por segurança e não precisa ficar trocando futuramente)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoCartao tipo;


    // Chave Estrangeira
        // Pegaremos a coluna ID da tabelaa usuário e não pode ser nulo.
    @ManyToOne // Lê-se: "Muitos Cartões pertencem a Um Usuário"
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Cartao(){
    }

    public Cartao(String finalNumero, BigDecimal saldo, TipoCartao tipo, Usuario usuario) {
        this.finalNumero = finalNumero;
        this.saldo = saldo;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinalNumero() {
        return finalNumero;
    }

    public void setFinalNumero(String finalNumero) {
        this.finalNumero = finalNumero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoCartao getTipo() {
        return tipo;
    }

    public void setTipo(TipoCartao tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
}
