package com.mycompany.app.controller;

import com.mycompany.app.model.Categoria;
import com.mycompany.app.model.Produto;
import java.util.ArrayList;
import java.util.Scanner;

public class Estoque {
    private ArrayList<Produto> produtos;

    private int pos = -1;
    Scanner scanner = new Scanner(System.in);

    public Estoque() {
        this.produtos = new ArrayList<Produto>();
    }

    public void adicionarProduto() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());
            System.out.print("Tipo (REMEDIO, RACAO, BRINQUEDOS, MISC): ");
            Categoria tipo = Categoria.valueOf(scanner.nextLine());
            System.out.print("Fornecedor: ");
            String fornecedor = scanner.nextLine();
            System.out.print("Preço: ");
            float preco = Float.parseFloat((scanner.nextLine()));
            Produto produto = new Produto(nome, quantidade, tipo, fornecedor, preco);
            this.produtos.add(produto);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Produto adicionado com sucesso.");
        pos++;
        System.out.println(produtos.get(pos));
    }

    public void editarProduto(int id) {
        try {
            Produto produto = this.produtos.get(id);
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
                    String novoNome = scanner.nextLine();
                    produto.setNome(novoNome);
                    break;

                case 1:
                    System.out.print("Quantidade: ");
                    int quant = Integer.parseInt(scanner.nextLine());
                    produto.setQuantidade(quant);
                    break;
                case 2:
                    System.out.print("Tipo (REMEDIO, RACAO, BRINQUEDOS, MISC): ");
                    Categoria tipo = Categoria.valueOf(scanner.nextLine());
                    produto.setTipo(tipo);
                    break;
                case 3:
                    System.out.print("Fornecedor: ");
                    String fornecedor = scanner.nextLine();
                    produto.setFornecedor(fornecedor);
                    break;
                case 4:
                    System.out.print("Preço: ");
                    float preco = scanner.nextFloat();
                    produto.setPreco(preco);
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void verProduto(int id) {
        try {
            Produto produto = this.produtos.get(id);
            System.out.printf("Produto: \n\t%s\n\tQuantidade: %d,\n\tTipo: %s,\n\tFornecedor: %s,\n\tPreço: %.2f \n",
                    produto.getNome(), produto.getQuantidade(), produto.getTipo(), produto.getFornecedor(), produto.getPreco());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

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
}
