package loyality_platform_model.Models;


import java.util.Objects;

/**
 * the class represents a customer within the platform.
 * A customer is registered with his classic data
 * (such as name, surname, telephone and email),
 * and can subscribe to loyalty programs, book and cancel visits,
 * search for shops and write reviews.
 */
public class Cliente {

    /**
     * This attribute rapresents the
     * unique id for this customer.
     */
    private static int idCliente;

    /**
     * This attribute rapresents the
     * customer's name.
     */
    private final String nome;

    /**
     * This attribute rapresents the
     * customer's surname.
     */
    private final String cognome;

    /**
     * This attribute rapresents the
     * customer's email address.
     */
    private String email;

    /**
     * This attribute rapresents the
     * customer's phone number.
     */
    private String telefono;

    /**
     * This attribute contains the customer card
     */
    private final Tessera tessera;

    /**
     * constructor allows you to create a new Client within the platform.
     *
     * @param nome     the name about this Costumer.
     * @param cognome  the surname about this Customer.
     * @param telefono the phone number about this Customer.
     * @param email    the email about this Customer.
     * @throws IllegalArgumentException if the name or the surname about this Employee is invalid.
     */
    public Cliente(String nome, String cognome, String telefono, String email) {
        if (Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal name or surname about this Employee.");
        idCliente++;
        this.nome = nome;
        this.cognome = cognome;
        this.setTelefono(telefono);
        this.setEmail(email);
        this.tessera = null;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public String getTelefono() {
        return this.telefono;
    }

    /**
     * This set method, set the telephone
     * number about this Employee (Cliente).
     *
     * @param telefono the new telephone number
     * @throws IllegalArgumentException if the telephone number is not correct.
     */
    public void setTelefono(String telefono) {
        if (Objects.equals(telefono, ""))
            throw new IllegalArgumentException("Illegal telephone number.");
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    /**
     * This set method, set the email
     * about this Employee (Cliente).
     *
     * @param email the new email about this Employee (Cliente)
     * @throws IllegalArgumentException if the email is not correct.
     */
    public void setEmail(String email) {
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal telephone number.");
        this.email = email;
    }

    public Tessera getTessera() {
        return this.tessera;
    }

    private String getIdTessera() {
        //Todo ricordarsi il metodo getIdTessera della tessera.
        return "\nId Tessera : ";
    }

    /**
     * Equals method of the Cliente class, simply
     * compare if the passed object is equivalent to this Costumer (Cliente),
     * by checking the id, and the name.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof Cliente tmp) {
            if (this.getIdCliente() == tmp.getIdCliente() && this.getNome() == tmp.getNome())
                return true;
        }
        return false;
    }

    public String toString() {
        return "\t-DETAILS CLIENTE-" +
                "\nId Cliente : " + idCliente +
                "\nNome : " + this.getNome() +
                "\nCognome : " + this.getCognome() +
                "\nEmail : " + this.getEmail() +
                "\nTelefono : " + this.getTelefono() +
                getIdTessera();
        //Todo mettere in questo campo l'id della tessera con il metodo
    }
}
