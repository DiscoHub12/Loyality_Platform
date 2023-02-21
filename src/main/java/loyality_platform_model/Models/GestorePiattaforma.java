package loyality_platform_model.Models;

import java.util.Objects;

/**
 * Class representing the manager of this platform.
 * The manager is able to run a series
 * of activities, such as the creation of loyalty programs,
 * subscriptions and SMS packages for companies that decide to register on the platform.
 */
public class GestorePiattaforma {
    private static int contatoreGestorePiattaforma=0;

    /**
     * Attribute representing the platform manager id
     */
    private final int idGestorePiattaforma;


    /**
     * Attribute of type final representing name and surname of the platform manager
     */
    private final String nome, cognome;


    /**
     * Attribute representing the platform manager email
     */
    private String email;


    /**
     * Attribute representing the platform manager password
     */
    private String password;


    /**
     * constructor that instantiates the object of the Platform Manager class,
     * where the following parameters are passed.
     * @param nome name of platform manager.
     * @param cognome surname of platform manager.
     * @param email email of platform manager
     * @param password password of platform manager.
     */
    public GestorePiattaforma(String nome, String cognome, String email, String password) {
        if (Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal name or surname about this manager.");
        this.idGestorePiattaforma=++contatoreGestorePiattaforma;
        this.nome = nome;
        this.cognome = cognome;
        this.setEmail(email);
        this.setPassword(password);
    }

    /**
     * This method returns the manager id
     * @return the manager id.
     */
    public int getIdGestore() {
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
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal email for manager.");
        this.email = email;
    }

    /**
     * This method returns the manager password.
     * @return the manager password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the manager password.
     * @param password new manager password.
     */
    public void setPassword(String password) {
        if (Objects.equals(password, ""))
            throw new IllegalArgumentException("Illegal password for manager.");
        this.password = password;
    }

    @Override
    public String toString() {
        return "\t-DETAILS GESTORE PIATTAFORMA-" +
                "\nnome: " + getNome() +
                "\ncognome: " + getCognome() +
                "\nemail: " + getEmail();
    }
}
