package com.mycompany.app.controller;

import com.mycompany.app.Repository.EstoqueDAO;
import com.mycompany.app.model.Categoria;
import com.mycompany.app.model.Produto;

import java.sql.SQLException;
import java.util.Scanner;

public class GerenciadorDeEstoque {

    public Produto addProduto() {
        var prod = new Produto();

        try {
            var scanner = new Scanner(System.in);

            System.out.print("Nome: ");
            prod.setNome(scanner.nextLine());

            System.out.print("Quantidade: ");
            prod.setQuantidade(Integer.parseInt(scanner.nextLine()));

            System.out.print("Tipo (REMEDIO, RACAO, BRINQUEDOS, MISC, HIGIENE): ");
            prod.setTipo(Categoria.valueOf(scanner.nextLine().toUpperCase()).ordinal());

            System.out.print("Fornecedor: ");
            prod.setFornecedor(scanner.nextLine());

            System.out.print("Preço: ");
            prod.setPreco(Float.parseFloat((scanner.nextLine())));

            new EstoqueDAO().inserirProduto(prod);

        } catch (Exception e) {
            System.out.println("Falha ao inserir produto - Erro: " + e.getMessage());
            prod = null;
        }
        return prod;
    }

    public void editarProduto(int id) {
        try {
            var prodReturned = new EstoqueDAO().obterProdutoPeloId(id);
            if (prodReturned != null) {
                String stringInputed;
                int intInputed;
                float floatInputed;

                var prod = new EstoqueDAO().createProdutoFromResultSet(prodReturned);

                System.out.println("Nome atual: " + prod.getNome() + " - Novo Nome: ");
                stringInputed = new Scanner(System.in).nextLine();
                if (!stringInputed.isEmpty())
                    prod.setNome(stringInputed);

                System.out.println("Quantidade atual: " + prod.getQuantidade() + " - Nova Quantidade: ");
                intInputed = new Scanner(System.in).nextInt();
                if (intInputed > 0)
                    prod.setQuantidade(intInputed);

                System.out.println("Tipo atual: " + prod.getTipo() + " - Novo Tipo: ");
                intInputed = new Scanner(System.in).nextInt();
                if (intInputed > 0)
                    prod.setTipo(intInputed);

                System.out.println("Fornecedor atual: " + prod.getTipo() + " - Novo Fornecedor: ");
                stringInputed = new Scanner(System.in).nextLine();
                if (!stringInputed.isEmpty())
                    prod.setFornecedor(stringInputed);

                System.out.println("Preço atual: " + prod.getTipo() + " - Novo Preço: ");
                floatInputed = new Scanner(System.in).nextFloat();
                if (floatInputed > 0)
                    prod.setPreco(floatInputed);

                new EstoqueDAO().editarProduto(prod);
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Falha ao inserir editar - Erro: " + e.getMessage());
        }
    }

    public void verProduto(int id) {
        try {
            var prodReturned = new EstoqueDAO().obterProdutoPeloId(id);
            if (prodReturned.next()) {
                System.out.printf(
                        "\tid: %d\n\tProduto: %s\n\tQuantidade: %d,\n\tTipo: %s,\n\tFornecedor: %s,\n\tPreço: %.2f \n",
                        prodReturned.getInt("id"), prodReturned.getString("nome"), prodReturned.getInt("quantidade"), prodReturned.getInt("tipo"),
                        prodReturned.getString("fornecedor"), prodReturned.getFloat("preco"));
            } else {
                throw new SQLException("Produto não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletarProduto(int id) {
        try {
            var prodReturned = new EstoqueDAO().obterProdutoPeloId(id);
            if (!prodReturned.next()) {
                throw new SQLException("Produto não encontrado.");
            }
            new EstoqueDAO().deletarProduto(id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void verEstoque() {
        var produtos = new EstoqueDAO().verEstoque();
        for (Produto prod : produtos){
            System.out.println(prod);
        }
    }
}
