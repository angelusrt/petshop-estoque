package petshop_estoque;
import java.util.Scanner;

public class Estoque {
    private ArrayList<Produto> produtos;
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
            System.out.print("Tipo: ");
            Categoria tipo = Categoria.valueOf(scanner.nextLine());
            System.out.print("Fornecedor: ");
            String fornecedor = scanner.nextLine();
            System.out.print("Preço: ");
            Float preco = Float.parseFloat((scanner.nextLine()));
            Produto produto = new Produto(nome, quantidade, tipo, fornecedor, preco);
            this.produtos.add(produto);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Yep");
        produtos.forEach(produto -> System.out.println(produto.toString()));
    }

    public void editarProduto() {
        try {
            System.out.print("id: ");
            Integer posicao = Integer.parseInt(scanner.nextLine());
            Produto produto = this.produtos.get(posicao);

            System.out.println("Escolha uma opção:");
            System.out.println("0 - Editar nome");
            System.out.println("1 - Editar quantidade");
            System.out.println("2 - Editar tipo");
            System.out.println("3 - Editar fornecedor");
            System.out.println("4 - Editar preco");
            System.out.print("Opção: ");
            Integer op = Integer.parseInt(scanner.nextLine());

            switch (op) {
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
                    System.out.print("Tipo: ");
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
                    Float preco = scanner.nextFloat();
                    produto.setPreco(preco);
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void verProduto() {
        try {
            System.out.print("id: ");
            int posicao = Integer.parseInt(scanner.nextLine());

            Produto produto = this.produtos.get(posicao);
            System.out.printf("Produto: \n\t%s\n\tQuantidade: %d,\n\tTipo: %s,\n\tFornecedor: %s,\n\tPreço: %.2f \n",
				  produto.getNome(), produto.getQuantidade(), produto.getTipo(), produto.getFornecedor(),
				  produto.getPreco());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void deletarProduto() {
        try {
            System.out.print("id: ");
            int posicao = Integer.parseInt(scanner.nextLine());
            Produto produtoEncontrado = produtos.get(posicao);
            produtos.remove(produtoEncontrado);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void verEstoque() {
        produtos.forEach(produto -> System.out.println("produto: " + produto.toString()));
    }
}
