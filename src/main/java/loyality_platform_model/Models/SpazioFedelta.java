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
     * This attribute rapresents the list
     * about external link for this Loyality Space.
     */
    private final List<String> linkEsterni;

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
        this.linkEsterni = new ArrayList<>();
    }

    public int getId() {
        return idSpazioFedelta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        if (Objects.equals(indirizzo, ""))
            throw new IllegalArgumentException("Invalid address.");
        this.indirizzo = indirizzo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        if (Objects.equals(numeroTelefono, ""))
            throw new IllegalArgumentException("Invalid telephone number.");
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Invalid email.");
        this.email = email;
    }

    public List<String> getLinkEsterni() {
        return this.linkEsterni;
    }

    public void aggiungiLinkEsterno(String link) {
        if (Objects.equals(link, ""))
            throw new IllegalArgumentException("Invalid link.");
        if (linkEsterni.contains(link))
            throw new IllegalArgumentException("Link already exist.");
        linkEsterni.add(link);
    }

    public void rimuoviLinkEsterno(String link) {
        if (Objects.equals(link, ""))
            throw new IllegalArgumentException("Invalid link.");
        if (!linkEsterni.contains(link))
            throw new IllegalArgumentException("This link isn't present to the list.");
        linkEsterni.remove(link);
    }

    private String toStringLinkEsterni() {
        StringBuilder tmp = new StringBuilder();
        for (String s : this.linkEsterni) {
            tmp.append("\n-").append(s);
        }
        return tmp.toString();
    }

    public String toString() {
        return "\n\tLOYALITY SPACE :" +
                "\n Name : " + this.nome +
                "\n Address : " + this.indirizzo +
                "\n Telephone Number : " + this.numeroTelefono +
                "\n Email : " + this.email +
                "\n External Links : " +
                "\n" + toStringLinkEsterni();
    }
}
