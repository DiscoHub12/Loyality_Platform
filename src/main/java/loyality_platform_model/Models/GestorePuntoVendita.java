package loyality_platform_model.Models;

public class GestorePuntoVendita {

    private static int id;

    private final String nome, cognome;

    private String email;

    public GestorePuntoVendita(String nome, String cognome, String email) {
        id++;
        this.nome = nome;
        this.cognome = cognome;
        this.setEmail(email);
    }

    public static int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GestorePuntoVendita{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
