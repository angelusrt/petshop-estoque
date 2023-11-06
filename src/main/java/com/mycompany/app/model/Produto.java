package com.mycompany.app.model;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private Integer quantidade;
    private Categoria tipo;
    private String fornecedor;
    private Float preco;
    private LocalDate dataValidade;

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getTipo() {
        return tipo;
    }

    public void setTipo(int ordinal) {

        this.tipo = Categoria.values()[ordinal];
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "[id: " + this.id + " nome: " + this.nome + ", tipo: " + this.tipo + ", Fornecedor: " + this.fornecedor
                + ", preço: " + this.preco + " ]";
    }

}
