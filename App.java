package petshop_estoque;
import java.util.Scanner;
import petshop_estoque.Estoque;
import petshop_estoque.Produto;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            Estoque estoque = new Estoque();
            Produto produto = new Produto();
            boolean loop = true;
            while (loop) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Adicionar produto");
                System.out.println("2 - Editar produto");
                System.out.println("3 - Ver produto");
                System.out.println("4 - Deletar produto");
                System.out.println("5 - Ver estoque");
                System.out.println("6 - Sair");

                int op = in.nextInt();
                switch (op) {
                    case 1:
                        estoque.adicionarProduto();
                        break;
                    case 2:
                        estoque.editarProduto();
                        break;
                    case 3:
                        estoque.verProduto();
                        break;
                    case 4:
                        estoque.deletarProduto();
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
