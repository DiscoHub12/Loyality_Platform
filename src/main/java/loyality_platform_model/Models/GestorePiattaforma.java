package loyality_platform_model.Models;

public class GestorePiattaforma {

    private static int id;

    private final String nome, cognome;

    private String email;

    public GestorePiattaforma(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
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
        return "GestorePiattaforma{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
