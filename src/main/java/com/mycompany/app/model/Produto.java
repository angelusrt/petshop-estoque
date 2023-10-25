package com.mycompany.app.model;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private Categoria tipo;
    private String fornecedor;
    private float preco;
    private static int posicao = 0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getTipo() {
        return tipo;
    }

    public void setTipo(Categoria tipo) {
        this.tipo = tipo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Produto(String nome, int quant, Categoria tipo, String fornecedor, float preco) {
        this.nome = nome;
        this.quantidade = quant;
        this.tipo = tipo;
        this.fornecedor = fornecedor;
        this.preco = preco;
        posicao++;
        this.id = posicao;
    }

    @Override
    public String toString() {
        return "[id: " + this.id + " nome: " + this.nome + ", tipo: " + this.tipo + " Fornecedor: " + this.fornecedor
                + ", preço: " + this.preco + " ]";
    }
}
