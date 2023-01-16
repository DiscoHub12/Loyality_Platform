package loyality_platform_model.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 the class represents a customer within the platform.
 A customer is registered with his classic data
 (such as name, surname, telephone and email),
 and can subscribe to loyalty programs, book and cancel visits,
 search for shops and write reviews.
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
    private String nome;
    /**
     * This attribute rapresents the
     * the customer's surname.
     */
    private String cognome;
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
    private Tessera tessera;
    /**
     * This attribute indicates whether the customer has been identified
     */
    private boolean identificato;
    /**
     * This attribute represents a set of companies
     */
    private Set<Azienda> listaAziende;
    /**
     * This attribute indicates a list of visits
     */
    private final List<Visita> visite;
    /**
     * This attribute indicates a list of reviews
     */
    private List<String> recensioni;
    /**
     * This attribute indicates a set of sms
     */
    private Set<SMS> sms;

    /**
     * constructor allows you to create a new client within the platform.
     * @param nome    the customer's name.
     * @param cognome the surname about this customer.
     * @param telefono   the phone number about this customer.
     * @param mail   the Email about this customer.
     */
    public Cliente(String nome, String cognome, String telefono, String mail, Tessera tessera){
        id++;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.mail = mail;
        this.tessera=tessera;
        this.visitaPrenotata=false;
        this.identificato=false;
        this.visite=new ArrayList<>();

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
    public void setIdentificato(boolean identificato){
        this.identificato = identificato;
    }

    public static int getId() {
        return id;
    }

    public String getNumeroTelefono() {
        return telefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.telefono = numeroTelefono;
    }
    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public Set<SMS> getSms() {
        return sms;
    }

    public List<Visita> getVisite(){
        return visite;
    }

    public void iscriviProgrammaFedelta(){
        //TODO

    }

    public boolean cercaNegozio(String nome){
        //TODO implementare
        return false;
    }

    public void addVisita(Visita visita) {
        this.visite.add(visita);
        visitaPrenotata=true;
    }

    public void removeVisita(Visita visita) {
        this.visite.remove(visita);
        visitaPrenotata=false;
    }

    public void modificaVisita(Visita presente, Visita nuovaVisita) {
        for (Visita visita : this.visite) {
            Visita tmp = visita;
            if (presente.equals(tmp)) {
                visita = nuovaVisita;
            }
        }
    }

    public Tessera getTessera(){
        return this.tessera;
    }

    public String getDetailsTessera(Tessera t){
        return t.toString();
    }
    public String getDetailsProgrammaFedelta(){
        //da vedere
        String s="niente";
        return  s;
    }
    public String toString(){
        return "Cliente{" +
                "id=" + id +
                ", nome=" + nome +
                ", cognome=" + cognome +
                ", telefono=" + telefono +
                ", mail=" + mail +
                ", visita prenotata=" + visitaPrenotata +
                ", tessera=" + tessera +
                '}';
    }

    public void scriviRecensione(String recensione){
        this.recensioni.add(recensione);
    }

}
