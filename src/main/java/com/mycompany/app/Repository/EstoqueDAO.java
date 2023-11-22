package com.mycompany.app.Repository;

import com.mycompany.app.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EstoqueDAO extends Conector {

    private final String sqlCommandInsert = "INSERT INTO produtos (nome, quantidade, tipo, fornecedor, preco) VALUES (?, ?, ?, ?, ?)";
    private final String sqlCommandSelect = "select * from produtos where id = ?";
    private final String sqlCommandUpdate = "update produtos set nome = ?, quantidade = ?, tipo = ?, fornecedor = ?, preco = ? where id = ?";
    private final String sqlCommandDelete = "DELETE FROM produtos WHERE id = ?";

    public void inserirProduto(Produto produto) {
        try {
            var queryBuilder = getInstance().prepareStatement(sqlCommandInsert);
            montarProduto(produto, queryBuilder);

            int novoProd = queryBuilder.executeUpdate();
            if (novoProd == 0) {
                throw new SQLException("Ocorreu um erro ao tentar inserir o produto.");
            }

            System.out.println("Produto adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public ResultSet obterProdutoPeloId(int id) {
        ResultSet prodReturned;
        try {
            var queryBuilder = getInstance().prepareStatement(sqlCommandSelect);
            queryBuilder.setInt(1, id);

            prodReturned = queryBuilder.executeQuery();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            prodReturned = null;
        }
        return prodReturned;
    }
    public void editarProduto(Produto produto) {
        try {
            var queryBuilder = getInstance().prepareStatement(sqlCommandUpdate);
            montarProduto(produto, queryBuilder);

            int novoProd = queryBuilder.executeUpdate();
            if (novoProd == 0) {
                throw new SQLException("Ocorreu um erro ao tentar editar o produto" + produto.getNome());
            }

            System.out.println("Produto adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletarProduto(int id) {
        try {
            var queryBuilder = getInstance().prepareStatement(sqlCommandDelete);
            queryBuilder.setInt(1, id);

            var prodDeletado = queryBuilder.executeUpdate();
            if (prodDeletado == 0) {
                throw new SQLException("Ocorreu um erro ao tentar deletar o produto de id " + id);
            }

            System.out.println("Produto deletado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Produto> verEstoque() {
        var produtos = new ArrayList<Produto>();
        try {

            var queryBuilder = getInstance().prepareStatement("SELECT * FROM produtos");
            var estoque = queryBuilder.executeQuery();

            while (estoque.next()) {
                Produto produto = new Produto();
                produto.setId(estoque.getInt("id"));
                produto.setNome(estoque.getString("nome"));
                produto.setQuantidade(estoque.getInt("quantidade"));
                produto.setTipo(estoque.getInt("tipo"));
                produto.setFornecedor(estoque.getString("fornecedor"));
                produto.setPreco(estoque.getFloat("preco"));
                produtos.add(produto);
            }

            System.out.println("Produto deletado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return produtos;
    }

    public Produto createProdutoFromResultSet(ResultSet resultSet) throws SQLException {
        var produto = new Produto();
        produto.setId(resultSet.getInt("id"));
        produto.setNome(resultSet.getString("nome"));
        produto.setQuantidade(resultSet.getInt("quantidade"));
        produto.setTipo(resultSet.getInt("tipo"));
        produto.setFornecedor(resultSet.getString("fornecedor"));
        produto.setPreco(resultSet.getFloat("preco"));

        return produto;
    }

    private void montarProduto(Produto produto, PreparedStatement queryBuilder) throws SQLException {
        queryBuilder.setString(1, produto.getNome());
        queryBuilder.setInt(2, produto.getQuantidade());
        queryBuilder.setInt(3, produto.getTipo().ordinal());
        queryBuilder.setString(4, produto.getFornecedor());
        queryBuilder.setFloat(5, produto.getPreco());
    }
}
