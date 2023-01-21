package loyality_platform_model.Models;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 * Class representing the manager of this platform.
 * The manager is able to run a series
 * of activities, such as the creation of loyalty programs,
 * subscriptions and SMS packages for companies that decide to register on the platform.
 */
public class GestorePiattaforma {

    private static int idGestorePiattaforma;

    private final String nome, cognome;

    private String email;

    public GestorePiattaforma(String nome, String cognome, String email) {
        idGestorePiattaforma++;
        this.nome = nome;
        this.cognome = cognome;
        this.setEmail(email);
    }

    /**
     * This method returns the manager id
     * @return the manager id.
     */
    public static int getId() {
        return idGestorePiattaforma;
    }

    /**
     * This method returns the manager name.
     * @return the manager name.
     */
    public String getNome() {
        return nome;
    }

    /**
     * This method returns the manager last name.
     * @return the manager last name.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * This method returns the manager email.
     * @return the manager email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the new manager mail.
     * @param email new mail.
     */
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
