import java.util.Scanner;

public class Menu {

    private final Biblioteca biblioteca = new Biblioteca();
    private final Scanner    scanner    = new Scanner(System.in);

    public void iniciar() {
        boasVindas();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt();
            try {
                switch (opcao) {
                    case 1  -> cadastrarLivro();
                    case 2  -> biblioteca.listarLivros();
                    case 3  -> buscarLivro();
                    case 4  -> biblioteca.ordenarPorAno();
                    case 5  -> listarPorAutor();
                    case 6  -> biblioteca.agruparPorAutor();
                    case 7  -> cadastrarUsuario();
                    case 8  -> biblioteca.listarUsuarios();
                    case 9  -> buscarEmail();
                    case 10 -> realizarEmprestimo();
                    case 11 -> devolverLivro();
                    case 12 -> biblioteca.listarEmprestimos();
                    case 0  -> System.out.println("\n  Até logo! 📚");
                    default -> System.out.println("⚠  Opção inválida!");
                }
            } catch (LivroIndisponivelException e) {
                System.out.println("⚠  " + e.getMessage());
            } finally {
                System.out.println();
            }
        } while (opcao != 0);
        scanner.close();
    }

    void boasVindas() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("       📚  Biblioteca Digital               ");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    void exibirMenu() {
        System.out.println("── Livros ────────────────────────────────");
        System.out.println("  1  - Cadastrar livro");
        System.out.println("  2  - Listar livros (por título)");
        System.out.println("  3  - Buscar livro por título");
        System.out.println("  4  - Ordenar livros por ano");
        System.out.println("  5  - Listar livros por autor");
        System.out.println("  6  - Agrupar livros por autor");
        System.out.println("── Usuários ──────────────────────────────");
        System.out.println("  7  - Cadastrar usuário");
        System.out.println("  8  - Listar usuários");
        System.out.println("  9  - Buscar usuário por email");
        System.out.println("── Empréstimos ───────────────────────────");
        System.out.println("  10 - Realizar empréstimo");
        System.out.println("  11 - Devolver livro");
        System.out.println("  12 - Listar empréstimos ativos");
        System.out.println("  0  - Sair");
        System.out.print("  Opção: ");
    }

    void cadastrarLivro() {
        scanner.nextLine();
        System.out.print("  Título: ");  String titulo = scanner.nextLine();
        System.out.print("  Autor: ");   String autor  = scanner.nextLine();
        System.out.print("  Ano: ");     int ano       = lerInt();
        biblioteca.cadastrarLivro(titulo, autor, ano);
    }

    void buscarLivro() {
        scanner.nextLine();
        System.out.print("  Título (ou parte): ");
        biblioteca.buscarPorTitulo(scanner.nextLine());
    }

    void listarPorAutor() {
        scanner.nextLine();
        System.out.print("  Autor: ");
        biblioteca.listarPorAutor(scanner.nextLine());
    }

    void cadastrarUsuario() {
        scanner.nextLine();
        System.out.print("  Nome: ");   String nome  = scanner.nextLine();
        System.out.print("  Email: ");  String email = scanner.nextLine();
        biblioteca.cadastrarUsuario(nome, email);
    }

    void buscarEmail() {
        scanner.nextLine();
        System.out.print("  Email: ");
        biblioteca.buscarPorEmail(scanner.nextLine());
    }

    void realizarEmprestimo() throws LivroIndisponivelException {
        scanner.nextLine();
        System.out.print("  Título do livro: ");  String titulo = scanner.nextLine();
        System.out.print("  Nome do usuário: ");  String nome   = scanner.nextLine();
        biblioteca.realizarEmprestimo(titulo, nome);
    }

    void devolverLivro() {
        scanner.nextLine();
        System.out.print("  Título do livro: ");
        biblioteca.devolverLivro(scanner.nextLine());
    }

    int lerInt() {
        while (!scanner.hasNextInt()) { System.out.print("⚠  Número válido: "); scanner.next(); }
        return scanner.nextInt();
    }
}
