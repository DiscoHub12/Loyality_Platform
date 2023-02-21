package loyality_platform_model.Models;

import java.util.Objects;

/**
 * This class represents a generic user before registering and logging in.
 */
public class UtenteGenerico {

    private final int idUtente;

    private static int contatoreUtente = 0;

    private final String nome;

    private final String cognome;

    private String email;

    private String password;


    public UtenteGenerico(String nome, String cognome, String email, String password) {
        this.idUtente = ++contatoreUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    /**
     * This method returns the username.
     * @return the username.
     */
    public String getNome() {
        return nome;
    }

    /**
     * This method returns the user's last name.
     * @return the user's last name.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * This method returns the user email.
     * @return the user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the user email.
     * @param email new user email.
     */
    public void setEmail(String email){
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal email for user.");
        this.email = email;
    }

    /**
     * This method returns the user password.
     * @return the user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the user password.
     * @param password new user password.
     */
    public void setPassword(String password){
        if (Objects.equals(password, ""))
            throw new IllegalArgumentException("Illegal password for user.");
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteGenerico that = (UtenteGenerico) o;
        return idUtente == that.idUtente && Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtente, nome, cognome, email, password);
    }
}
