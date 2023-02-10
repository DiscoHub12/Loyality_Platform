package loyality_platform_model.Models;

import java.util.Objects;

/**
 *
 * Class representing the manager of a company.
 * The manager of the company is able to run a series
 * of activities, such as registering on the platform and purchasing a subscription,
 * purchasing SMS packages for SMS campaigns, creating new users, modifying and removing them,
 * adding loyalty programs made available by the platform and compiling them as desired.
 */
public class GestorePuntoVendita {

    private static int contatoreGestorePV = 0;

    private final int idGestorePuntoVendita;

    private final String nome, cognome;

    private String email;

    public GestorePuntoVendita(String nome, String cognome, String email) {
        this.idGestorePuntoVendita = contatoreGestorePV++;
        this.nome = nome;
        this.cognome = cognome;
        this.setEmail(email);
    }

    /**
     * This method returns the manager id
     * @return the manager id.
     */
    public int getIdGestorePuntoVendita() {
        return idGestorePuntoVendita;
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
            throw new IllegalArgumentException("Email not valid.");
        this.email = email;
    }

    @Override
    public String toString() {
        return "\t-DETAILS GESTORE PUNTO VENDITA-" +
                "\nnome: " + nome +
                "\ncognome: " + cognome +
                "\nemail: " + email;
    }
}
