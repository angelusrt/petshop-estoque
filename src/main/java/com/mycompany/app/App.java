package com.mycompany.app;

import java.util.Scanner;

import com.mycompany.app.controller.GerenciadorDeEstoque;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            boolean loopCondition = true;
            while (loopCondition) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Adicionar produto");
                System.out.println("2 - Editar produto");
                System.out.println("3 - Ver produto");
                System.out.println("4 - Deletar produto");
                System.out.println("5 - Ver estoque");
                System.out.println("6 - Sair");

                try {
                    Scanner in = new Scanner(System.in);

                    var gerenciadorDeEstoque = new GerenciadorDeEstoque();

                    int change = in.nextInt();
                    switch (change) {
                        case 1:
                            gerenciadorDeEstoque.addProduto();
                            break;
                        case 2:
                            System.out.print("id: ");
                            gerenciadorDeEstoque.editarProduto(in.nextInt());
                            break;
                        case 3:
                            System.out.print("id: ");
                            gerenciadorDeEstoque.verProduto(in.nextInt());
                            break;
                        case 4:
                            System.out.print("id: ");
                            gerenciadorDeEstoque.deletarProduto(in.nextInt());
                            break;
                        case 5:
                            gerenciadorDeEstoque.verEstoque();
                            break;
                        case 6:
                            loopCondition = false;
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("Escolha uma opção válida");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
