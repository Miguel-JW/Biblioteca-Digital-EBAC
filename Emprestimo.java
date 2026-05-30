import java.io.Serializable;

public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Livro   livro;
    private Usuario usuario;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro   = livro;
        this.usuario = usuario;
    }

    public Livro   getLivro()   { return livro; }
    public Usuario getUsuario() { return usuario; }

    @Override
    public String toString() {
        return String.format("  Livro: %-30s | Usuário: %s", livro.getTitulo(), usuario.getNome());
    }
}
