package com.mycompany.app.controller;

import com.mycompany.app.database.Conector;
import com.mycompany.app.model.Categoria;
import com.mycompany.app.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Estoque extends Conector {
    Scanner scanner = new Scanner(System.in);

    public void adicionarProduto() {
        try {
            PreparedStatement addProd = this.conn.prepareStatement(
                    "insert into produtos (nome, quantidade, tipo, fornecedor, preco) values (?, ?, ?, ?, ?)");

            System.out.print("Nome: ");
            addProd.setString(1, scanner.nextLine());
            System.out.print("Quantidade: ");
            addProd.setInt(2, Integer.parseInt(scanner.nextLine()));
            System.out.print("Tipo (REMEDIO, RACAO, BRINQUEDOS, MISC, HIGIENE): ");
            addProd.setInt(3, Categoria.valueOf(scanner.nextLine()).ordinal());
            System.out.print("Fornecedor: ");
            addProd.setString(4, scanner.nextLine());
            System.out.print("Preço: ");
            addProd.setFloat(5, Float.parseFloat((scanner.nextLine())));

            int novoProd = addProd.executeUpdate();
            if (novoProd == 0) {
                throw new SQLException("Ocorreu um erro ao tentar inserir o produto.");
            }

            System.out.println("Produto adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void editarProduto(int id) {
        try {

            PreparedStatement verProd = this.conn.prepareStatement("select * from produtos where id = ?");
            verProd.setInt(1, id);
            ResultSet prod = verProd.executeQuery();

            if (prod.next()) {
                var produto = new Produto();
                produto.setId(prod.getInt("id"));
                produto.setNome(prod.getString("nome"));
                produto.setQuantidade(prod.getInt("quantidade"));
                produto.setTipo(prod.getInt("tipo"));
                produto.setFornecedor(prod.getString("fornecedor"));
                produto.setPreco(prod.getFloat("preco"));

                PreparedStatement editProd = this.conn.prepareStatement(
                        "update produtos set nome = ?, quantidade = ?, tipo = ?, fornecedor = ?, preco = ? where id = ?");
                editProd.setInt(6, id);

                System.out.println("Escolha uma opção:");
                System.out.println("1 - Editar nome");
                System.out.println("2 - Editar quantidade");
                System.out.println("3 - Editar tipo");
                System.out.println("4 - Editar fornecedor");
                System.out.println("5 - Editar preco");
                System.out.print("Opção: ");
                int escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1:
                        System.out.print("Nome: ");
                        var nome = scanner.nextLine();
                        if (nome != null) {
                            produto.setNome(nome);
                        }
                        break;
                    case 2:
                        System.out.print("Quantidade: ");
                        Integer quantidade = scanner.nextInt();
                        if (quantidade != null) {
                            produto.setQuantidade(quantidade);
                        }
                        break;
                    case 3:
                        System.out.print("Tipo ( 0 - REMEDIO, 1 - RACAO, 2 - BRINQUEDOS, 3 - MISC, 4 - HIGIENE): ");
                        Integer tipo = scanner.nextInt();
                        if (tipo != null) {
                            produto.setTipo(tipo);
                        }
                        break;
                    case 4:
                        System.out.print("Fornecedor: ");
                        var fornecedor = scanner.nextLine();
                        if (fornecedor != null) {
                            produto.setFornecedor(fornecedor);
                        }
                        break;
                    case 5:
                        System.out.print("Preço: ");
                        Float preco = scanner.nextFloat();
                        if (preco != null) {
                            produto.setPreco(preco);
                        }
                        break;
                }

                editProd.setString(1, produto.getNome());
                editProd.setInt(2, produto.getQuantidade());
                editProd.setInt(3, produto.getTipo().ordinal());
                editProd.setString(4, produto.getFornecedor());
                editProd.setFloat(5, produto.getPreco());

                int prodEditado = editProd.executeUpdate();
                if (prodEditado == 0) {
                    throw new SQLException("Ocorreu um erro ao tentar editar o produto de id " + id);
                }

            } else {
                throw new SQLException("Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public void verProduto(int id) {
        try {
            PreparedStatement verProd = this.conn.prepareStatement("select * from produtos where id = ?");
            verProd.setInt(1, id);
            ResultSet prod = verProd.executeQuery();

            if (prod.next()) {
                System.out.printf(
                        "\tid: %d\n\tProduto: %s\n\tQuantidade: %d,\n\tTipo: %s,\n\tFornecedor: %s,\n\tPreço: %.2f \n",
                        prod.getInt("id"), prod.getString("nome"), prod.getInt("quantidade"), prod.getInt("tipo"),
                        prod.getString("fornecedor"), prod.getFloat("preco"));
            } else {
                throw new SQLException("Produto não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletarProduto(int id) {
        try {
            PreparedStatement verProd = this.conn.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            verProd.setInt(1, id);
            var prod = verProd.executeQuery();
            if (!prod.next()) {
                throw new SQLException("Produto não encontrado.");
            }

            PreparedStatement delProd = this.conn.prepareStatement("DELETE FROM produtos WHERE id = ?");
            delProd.setInt(1, id);
            var prodDeletado = delProd.executeUpdate();
            if (prodDeletado == 0) {
                throw new SQLException("Ocorreu um erro ao tentar deletar o produto de id " + id);
            }

            System.out.println("Produto deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void verEstoque() {
        try {
            PreparedStatement listEstoque = this.conn.prepareStatement("SELECT * FROM produtos");
            var estoque = listEstoque.executeQuery();

            while (estoque.next()) {

                Produto produto = new Produto();
                var produtos = new ArrayList<Produto>();
                produto.setId(estoque.getInt("id"));
                produto.setNome(estoque.getString("nome"));
                produto.setQuantidade(estoque.getInt("quantidade"));
                var tipo = estoque.getInt("tipo");
                produto.setTipo(estoque.getInt("tipo"));
                produto.setFornecedor(estoque.getString("fornecedor"));
                produto.setPreco(estoque.getFloat("preco"));

                produtos.add(produto);

                for (Produto prod : produtos) {
                    System.out.println(prod);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na conexão: " + e.getMessage());
        }

    }
}
