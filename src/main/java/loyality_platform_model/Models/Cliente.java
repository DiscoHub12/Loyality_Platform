package loyality_platform_model.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private static int id;
    /**
     * This attribute rapresents the
     * the customer's name.
     */
    private final String nome;
    /**
     * This attribute rapresents the
     * the customer's surname.
     */
    private final String cognome;
    /**
     * This attribute rapresents the
     * the customer's phone number.
     */
    private String telefono;
    /**
     * This attribute rapresents the
     * the customer's email address.
     */
    private String mail;
    /**
     * This attribute indicates whether a visit has been booked or not
     */
    private boolean visitaPrenotata;
    /**
     * This attribute contains the customer card
     */
    private final Tessera tessera;
    /**
     * This attribute indicates whether the customer has been identified
     */
    private boolean identificato;

    /**
     * This attribute indicates a set of visits
     */
    private final Set<Visita> visite;

    private final Set<SMS> smsCliente;


    /**
     * constructor allows you to create a new client within the platform.
     *
     * @param nome     the customer's name.
     * @param cognome  the surname about this customer.
     * @param telefono the phone number about this customer.
     * @param mail     the Email about this customer.
     */
    public Cliente(String nome, String cognome, String telefono, String mail, Tessera tessera) {
        id++;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.mail = mail;
        this.tessera = tessera;
        this.visitaPrenotata = false;
        this.identificato = false;
        this.visite = new HashSet<>();
        this.smsCliente = new HashSet<>();

    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public boolean isIdentificato() {
        return identificato;
    }

    public void setIdentificato(boolean identificato) {
        this.identificato = identificato;
    }

    public static int getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isVisitaPrenotata() {
        return visitaPrenotata;
    }

    public void setVisitaPrenotata(boolean visitaPrenotata) {
        this.visitaPrenotata = visitaPrenotata;
    }

    public Set<Visita> getVisite() {
        return visite;
    }

    public Set<SMS> getSmsCliente() {
        return this.smsCliente;
    }


    public void iscriviProgrammaFedelta() {
        //TODO implementare
    }

    public void cercaNegozio(String nome) {
        //TODO implementare
    }

    public void prenotaVisita(Visita visita) {
        this.visite.add(visita);
        visitaPrenotata = true;
    }

    public void addVisita(Visita visita) {
        Objects.requireNonNull(visita);
        this.visite.add(visita);
    }

    public void removeVisita(Visita visita) {
        this.visite.remove(visita);
        visitaPrenotata = false;
    }

    public Tessera getTessera() {
        return this.tessera;
    }

    public String getDetailsTessera() {
        return this.getTessera().toString();
    }

    public String getDetailsProgrammaFedelta() {
        //TODO implementare
        return null;
    }

    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome=" + nome +
                ", cognome=" + cognome +
                ", telefono=" + telefono +
                ", mail=" + mail +
                ", visite prenotate=" + toStringVisite() +
                ", tessera=" + tessera +
                '}';
    }

    private String toStringVisite() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Visita visita : this.getVisite()) {
            stringBuilder.append("\n-").append(visita);
        }
        return stringBuilder.toString();
    }
}
