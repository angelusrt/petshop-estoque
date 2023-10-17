package petshop_estoque;

import petshop_estoque.controller.Estoque;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            Estoque estoque = new Estoque();
            boolean loop = true;
            while (loop) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Adicionar produto");
                System.out.println("2 - Editar produto");
                System.out.println("3 - Ver produto");
                System.out.println("4 - Deletar produto");
                System.out.println("5 - Ver estoque");
                System.out.println("6 - Sair");

                int escolha = in.nextInt();
                switch (escolha) {
                    case 1:
                        estoque.adicionarProduto();
                        break;
                    case 2:
                        System.out.print("id: ");
                        var id = in.nextInt() - 1;
                        estoque.editarProduto(id);
                        break;
                    case 3:
                        System.out.print("id: ");
                        id = in.nextInt() - 1;
                        estoque.verProduto(id);
                        break;
                    case 4:
                        System.out.print("id: ");
                        id = in.nextInt() - 1;
                        estoque.deletarProduto(id);
                        break;
                    case 5:
                        estoque.verEstoque();
                        break;
                    case 6:
                        loop = false;
                        in.close();
                        break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
