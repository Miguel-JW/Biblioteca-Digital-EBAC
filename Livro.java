import java.io.Serializable;

public class Livro implements Serializable, Comparable<Livro> {
    private static final long serialVersionUID = 1L;

    private String  titulo;
    private String  autor;
    private int     ano;
    private boolean disponivel;

    public Livro(String titulo, String autor, int ano) {
        this.titulo     = titulo;
        this.autor      = autor;
        this.ano        = ano;
        this.disponivel = true;
    }

    public String  getTitulo()     { return titulo; }
    public String  getAutor()      { return autor; }
    public int     getAno()        { return ano; }
    public boolean isDisponivel()  { return disponivel; }

    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public int compareTo(Livro outro) { return this.titulo.compareToIgnoreCase(outro.titulo); }

    @Override
    public String toString() {
        return String.format("  %-35s | %-20s | %d | %s",
            titulo, autor, ano, disponivel ? "Disponível ✔" : "Emprestado ✘");
    }
}
