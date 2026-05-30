import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    // ── Salva qualquer lista serializável ──────────────────
    public static <T extends Serializable> void salvar(List<T> lista, String caminho) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("⚠  Erro ao salvar: " + e.getMessage());
        }
    }

    // ── Carrega qualquer lista serializável ────────────────
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> carregar(String caminho) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠  Erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}Biblioteca.java
