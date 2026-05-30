import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.nome  = nome;
        this.email = email;
    }

    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("  %-20s | %s", nome, email);
    }
}
