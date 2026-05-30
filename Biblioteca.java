import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {

    private static final String ARQ_LIVROS   = "livros.dat";
    private static final String ARQ_USUARIOS = "usuarios.dat";

    private List<Livro>      livros;
    private List<Usuario>    usuarios;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Biblioteca() {
        livros   = Arquivo.carregar(ARQ_LIVROS);
        usuarios = Arquivo.carregar(ARQ_USUARIOS);
    }

    public void salvar() {
        Arquivo.salvar(livros,   ARQ_LIVROS);
        Arquivo.salvar(usuarios, ARQ_USUARIOS);
    }

    // ══ LIVROS ═════════════════════════════════════════════

    public void cadastrarLivro(String titulo, String autor, int ano) {
        livros.add(new Livro(titulo, autor, ano));
        salvar();
        System.out.println("✔  Livro \"" + titulo + "\" cadastrado!");
    }

    public void listarLivros() {
        if (livros.isEmpty()) { System.out.println("  Nenhum livro cadastrado."); return; }
        livros.stream().sorted().forEach(System.out::println);
    }

    public void buscarPorTitulo(String titulo) {
        livros.stream()
            .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .forEach(System.out::println);
    }

    public void ordenarPorAno() {
        livros.stream()
            .sorted(Comparator.comparingInt(Livro::getAno))
            .forEach(System.out::println);
    }

    public void listarPorAutor(String autor) {
        livros.stream()
            .filter(l -> l.getAutor().equalsIgnoreCase(autor))
            .forEach(System.out::println);
    }

    public void agruparPorAutor() {
        Map<String, List<Livro>> agrupados = livros.stream()
            .collect(Collectors.groupingBy(Livro::getAutor));
        agrupados.forEach((autor, lista) -> {
            System.out.println("  Autor: " + autor);
            lista.forEach(l -> System.out.println("    - " + l.getTitulo()));
        });
    }

    // ══ USUÁRIOS ═══════════════════════════════════════════

    public void cadastrarUsuario(String nome, String email) {
        usuarios.add(new Usuario(nome, email));
        salvar();
        System.out.println("✔  Usuário \"" + nome + "\" cadastrado!");
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) { System.out.println("  Nenhum usuário cadastrado."); return; }
        usuarios.forEach(System.out::println);
    }

    public void buscarPorEmail(String email) {
        usuarios.stream()
            .filter(u -> u.getEmail().equalsIgnoreCase(email))
            .map(u -> "  Encontrado: " + u.getNome() + " | " + u.getEmail())
            .forEach(System.out::println);
    }

    // ══ EMPRÉSTIMOS ════════════════════════════════════════

    public void realizarEmprestimo(String titulo, String nomeUsuario)
            throws LivroIndisponivelException {

        Optional<Livro> livroOpt = livros.stream()
            .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
            .findFirst();

        Optional<Usuario> usuarioOpt = usuarios.stream()
            .filter(u -> u.getNome().equalsIgnoreCase(nomeUsuario))
            .findFirst();

        if (livroOpt.isEmpty())    { System.out.println("⚠  Livro não encontrado!");   return; }
        if (usuarioOpt.isEmpty())  { System.out.println("⚠  Usuário não encontrado!"); return; }

        Livro livro = livroOpt.get();
        if (!livro.isDisponivel()) throw new LivroIndisponivelException(livro.getTitulo());

        livro.setDisponivel(false);
        emprestimos.add(new Emprestimo(livro, usuarioOpt.get()));
        salvar();
        System.out.println("✔  Empréstimo realizado: \"" + titulo + "\" → " + nomeUsuario);
    }

    public void devolverLivro(String titulo) {
        Optional<Emprestimo> emp = emprestimos.stream()
            .filter(e -> e.getLivro().getTitulo().equalsIgnoreCase(titulo))
            .findFirst();

        emp.ifPresentOrElse(e -> {
            e.getLivro().setDisponivel(true);
            emprestimos.remove(e);
            salvar();
            System.out.println("✔  Livro \"" + titulo + "\" devolvido!");
        }, () -> System.out.println("⚠  Empréstimo não encontrado!"));
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) { System.out.println("  Nenhum empréstimo ativo."); return; }
        emprestimos.forEach(System.out::println);
    }
}
