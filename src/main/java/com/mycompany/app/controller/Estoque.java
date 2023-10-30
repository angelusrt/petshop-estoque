package com.mycompany.app.controller;

import com.mycompany.app.model.Categoria;
import com.mycompany.app.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Estoque extends Conector{
    Scanner scanner = new Scanner(System.in);

    public void adicionarProduto() {
        try {
            PreparedStatement addProd = this.conn.prepareStatement(
                "insert into produtos (nome, quant, tipo, forn, preco) values (?, ?, ?, ?, ?)");

            System.out.print("Nome: ");
            addProd.setString(1, scanner.nextLine());
            System.out.print("Quantidade: ");
            addProd.setInt(2, Integer.parseInt(scanner.nextLine()));
            System.out.print("Tipo (REMEDIO, RACAO, BRINQUEDOS, MISC): ");
            addProd.setInt(3, Categoria.valueOf(scanner.nextLine()));
            System.out.print("Fornecedor: ");
            addProd.setString(4, scanner.nextLine());
            System.out.print("Preço: ");
            addProd.setFloat(5, Float.parseFloat((scanner.nextLine())));

            addProd.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Produto adicionado com sucesso.");
    }

    public void editarProduto(int id) {
        try {
            PreparedStatement editProd = this.conn.prepareStatement(
                "update produtos set ? where id = ?");
            editProd.setInt(2, id);

            System.out.println("Escolha uma opção:");
            System.out.println("0 - Editar nome");
            System.out.println("1 - Editar quantidade");
            System.out.println("2 - Editar tipo");
            System.out.println("3 - Editar fornecedor");
            System.out.println("4 - Editar preco");
            System.out.print("Opção: ");
            int escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 0:
                    System.out.print("Nome: ");
                    editProd.setString(0, "nome="+scanner.nextLine());
                    break;
                case 1:
                    System.out.print("Quantidade: ");
                    editProd.setString(0, "quant="+Integer.parseInt(scanner.nextLine()));
                    break;
                case 2:
                    System.out.print("Tipo (REMEDIO, RACAO, BRINQUEDOS, MISC): ");
                    editProd.setString(0, "tipo="+Categoria.valueOf(scanner.nextLine()));
                    break;
                case 3:
                    System.out.print("Fornecedor: ");
                    editProd.setString(0, "forn="+scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Preço: ");
                    editProd.setString(0, "preco="+scanner.nextFloat());
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro de Conexão.");
        }
    }

    public void verProduto(int id) {
        try {
            PreparedStatement verProd = this.conn.prepareStatement("select produtos where id = ?");
            verProd.setInt(1, id);
            ResultSet prod = verProd.executeQuery();

            System.out.printf("Produto: \n\t%s\n\tQuantidade: %d,\n\tTipo: %s,\n\tFornecedor: %s,\n\tPreço: %.2f \n",
                    prod.nome, prod.quant, prod.tipo, prod.forn, prod.preco);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

    /*
    public void deletarProduto(int id) {
        try {
            Produto produtoEncontrado = produtos.get(id);
            produtos.remove(produtoEncontrado);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void verEstoque() {
        for (Produto produto : produtos) {
            System.out.println("produto: " + produto.toString());
        }
    }
    */
}
