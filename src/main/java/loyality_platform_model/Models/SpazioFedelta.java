package loyality_platform_model.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Class that rapresent the concept of Loyalty Space.
 * This Loyality Space is an Object that is container
 * within any Company, in fact it contains Company
 * information such as the Name, the Address and
 * much other information.
 */
public class SpazioFedelta {

    /**
     * This attribute rapresents the
     * unique Id for this Loyality Space.
     */
    private static int idSpazioFedelta;

    /**
     * This attribute rapresents the
     * Name for this Loyality Space.
     */
    private  String nome;

    /**
     * This attribute rapresents the
     * address for this Loyality Space.
     */
    private String indirizzo;

    /**
     * This attribute rapresents the
     * telephone number for this Loyality Space.
     */
    private String numeroTelefono;

    /**
     * This attribute rapresents the email
     * address for this Loyality Space.
     */
    private String email;

    /**
     * Constructor that allow to create a Loyality
     * Space with the specific information.
     *
     * @param nome           the name for this Loyality Space.
     * @param indirizzo      the address for this Loyality Space.
     * @param numeroTelefono the number telephone for this Loyality Space.
     * @param email          the email address for this Loyality Space.
     * @throws IllegalArgumentException if the Name for the Loyality Space is
     *                                  wrong.
     */
    public SpazioFedelta(String nome, String indirizzo, String numeroTelefono, String email) {
        idSpazioFedelta++;
        if (Objects.equals(nome, ""))
            throw new IllegalArgumentException("Illegal name for Loyalty space.");
        this.nome = nome;
        this.setIndirizzo(indirizzo);
        this.setNumeroTelefono(numeroTelefono);
        this.setEmail(email);
    }
    /**
     * method that returns the id of the loyalty space
     */
    public int getIdSpazioFedelta() {
        return idSpazioFedelta;
    }
    /**
     * method that updates the name for the loyalty space
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * method that returns the name for the loyalty space
     */
    public String getNome() {
        return this.nome;
    }
    /**
     * method that returns the address of the loyalty space
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }
    /**
     * method that updates the loyalty space address
     */
    public void setIndirizzo(String indirizzo) {
        if (Objects.equals(indirizzo, ""))
            throw new IllegalArgumentException("Invalid address.");
        this.indirizzo = indirizzo;
    }
    /**
     * method that returns the loyalty space phone number
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    /**
     * method that update the loyalty space phone number
     */
    public void setNumeroTelefono(String numeroTelefono) {
        if (Objects.equals(numeroTelefono, ""))
            throw new IllegalArgumentException("Invalid telephone number.");
        this.numeroTelefono = numeroTelefono;
    }
    /**
     * method that returns the loyalty space email
     */
    public String getEmail() {
        return email;
    }
    /**
     * method that update the loyalty space email
     */
    public void setEmail(String email) {
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Invalid email.");
        this.email = email;
    }

    public String toString() {
        return "\n\tLOYALITY SPACE :" +
                "\n Name : " + this.nome +
                "\n Address : " + this.indirizzo +
                "\n Telephone Number : " + this.numeroTelefono +
                "\n Email : " + this.email;
    }
}
