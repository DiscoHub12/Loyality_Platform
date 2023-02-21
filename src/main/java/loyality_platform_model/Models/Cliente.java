package loyality_platform_model.Models;


import java.util.Objects;

/**
 * the class represents a customer within the platform.
 * A customer is registered with his classic data
 * (such as name, surname, telephone and email),
 * and can subscribe to loyalty programs, book and cancel visits,
 * search for shops and write reviews.
 */
public class Cliente extends UtenteGenerico {

    private static int contatoreClienti = 0;


    private final int idCliente;


    private String telefono;


    private Tessera tessera;


    /**
     * Constructor allows you to create a new Client within the platform.
     *
     * @param nome     the name about this Costumer.
     * @param cognome  the surname about this Customer.
     * @param telefono the phone number about this Customer.
     * @param email    the email about this Customer.
     * @param password the password about this Costumer.
     * @throws IllegalArgumentException if the name or the surname about this Employee is invalid.
     */
    public Cliente(String nome, String cognome, String telefono, String email, String password) {
        super(nome, cognome, email, password);
        if (Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal name or surname about this Employee.");
        this.idCliente = ++contatoreClienti;
        this.setTelefono(telefono);
        this.tessera = null;
    }

    /**
     * This method returns the costumer id.
     * @return the costumer id.
     */
    public int getIdCliente() {
        return idCliente;
    }


    /**
     * This method returns the costumer phone.
     * @return the costumer phone.
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * This set method, set the telephone
     * number about this costumer.
     *
     * @param telefono the new telephone number
     * @throws IllegalArgumentException if the telephone number is not correct.
     */
    public void setTelefono(String telefono) {
        if (Objects.equals(telefono, ""))
            throw new IllegalArgumentException("Illegal telephone number.");
        this.telefono = telefono;
    }


    /**
     * This method returns the costumer card.
     * @return the costumer card.
     */
    public Tessera getTessera() {
        return this.tessera;
    }

    /**
     * This method allows you to associate a new
     * Card to the Customer, if he does not have one.
     *
     * @param tessera the new Card required by the Customer.
     * @throws NullPointerException     if tessera is null.
     * @throws IllegalArgumentException if the Customer already have Card.
     */
    public void setTessera(Tessera tessera) {
        Objects.requireNonNull(tessera);
        if (this.tessera != null)
            throw new IllegalArgumentException("Customer already have a Card.");
        this.tessera = tessera;
    }


    /**
     * This private method returns the costumer card id.
     * @return the costumer card id.
     */
    private String getIdTessera() {
        String tmp;
        if (this.tessera != null) {
            tmp = "\nID CARD : " + this.tessera.getIdTessera();
        } else tmp = "Customer don't have Card.";
        return tmp;
    }

    /**
     * Equals method of the costumer class, simply
     * compare if the passed object is equivalent to this Costumer,
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
            return this.getIdCliente() == tmp.getIdCliente() && Objects.equals(this.getNome(), tmp.getNome());
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
    }
}