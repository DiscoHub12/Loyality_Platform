package loyality_platform_model.DBMS;

import loyality_platform_model.Models.*;
import java.util.*;

/**
 * Class representing the DataBase, capable of persisting data within
 * the Platform.
 */
public class DBMS {

    /**
     * This attribute represents the instance of the class, capable of
     * being able to invoke it without instantiating a new class and losing persistence.
     */
    private static DBMS instance;

    private final Set<Azienda> aziendeIscritte;

    private final Map<Azienda, Set<Dipendente>> dipendentiAzienda;

    private final Map<Azienda, Set<ProgrammaFedelta>> programmiAzienda;

    private final Set<Cliente> clientiIscritti;


    private final Map<Cliente, Set<SMS>>  SMSCliente;


    private Map<Cliente, Set<Coupon>>  couponCliente;


    private final Map<Cliente, Set<Premio>> premiCliente;


    private final Map<Cliente, Set<Visita>> visiteCliente;


    private final Set<Tessera> tessereClienti;

    private final Set<ProgrammaFedelta> programmiDisponibili;


    private final Set<PacchettoSMS> pacchettiSMS;


    private final Set<Abbonamento> abbonamenti;



    private DBMS() {
        this.aziendeIscritte = new HashSet<>();
        this.dipendentiAzienda = new HashMap<>();
        this.programmiAzienda = new HashMap<>();
        this.clientiIscritti = new HashSet<>();
        this.SMSCliente = new HashMap<>();
        this.couponCliente = new HashMap<>();
        this.premiCliente = new HashMap<>();
        this.visiteCliente = new HashMap<>();
        this.tessereClienti = new HashSet<>();
        this.programmiDisponibili = new HashSet<>();
        this.pacchettiSMS = new HashSet<>();
        this.abbonamenti = new HashSet<>();

    }

    public static DBMS getInstance() {
        if (instance == null) {
            instance = new DBMS();
        }
        return instance;
    }

    public Set<Azienda> getAziendeIscritte() {
        return aziendeIscritte;
    }

    public void addAzienda(Azienda azienda){
        //TODO implementare
    }

    public void updateAzienda(Azienda aziendaOld, Azienda aziendaNew){
        //TODO implementare
    }

    public void removeAzienda(Azienda azienda){
        //TODO implementare
    }


    public Map<Azienda, Set<Dipendente>> getDipendentiAzienda() {
        return dipendentiAzienda;
    }

    public void addDipendente(Azienda azienda, Dipendente dipendente){
        //TODO implementare
    }

    public void updateDipendente(Azienda azienda, Dipendente dipendenteOld, Dipendente dipendenteNew){
        //TODO implementare
    }

    public void removeDipendente(Azienda azienda, Dipendente dipendente){
        //TODO implementare
    }

    public Map<Azienda, Set<ProgrammaFedelta>> getProgrammiAzienda() {
        return programmiAzienda;
    }

    public void addProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta){
        //TODO implementare
    }

    public void updateProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta){
        //TODO implementare
    }

    public void removeProgrammaAzienda(Azienda azienda, Cliente cliente){
        //TODO implementare
    }

    public Set<Cliente> getClientiIscritti() {
        return clientiIscritti;
    }

    public void addCliente(Cliente cliente){
        //TODO implementare
    }

    public void updateCliente(Cliente clienteOld, Cliente clienteNew){
        //TODO implementare
    }

    public void removeCliente(Cliente cliente){
        //TODO implementare
    }

    public Map<Cliente, Set<SMS>> getSMSCliente() {
        return SMSCliente;
    }

    public void addSMS(Cliente cliente, SMS sms){
        //TODO implementare
    }

    public Map<Cliente, Set<Coupon>> getCouponCliente(){return this.couponCliente;}

    public void addCoupon(Cliente cliente, Coupon coupon){
        //Todo implementare
    }

    public void updateCoupon(Cliente cliente, Coupon oldCoupon, Coupon newCoupon){
        //Todo implementare
    }

    public void removeCoupon(Cliente cliente, Coupon coupon){
        //Todo implementare
    }


    public Map<Cliente, Set<Premio>> getPremiCliente() {
        return premiCliente;
    }

    public void addPremioCliente(Premio premio, Cliente cliente){
        //TODO implementare
    }

    public void updatePremioCliente(Cliente cliente, Premio premioOld, Premio premioNew){
        //TODO implementare
    }

    public void removePremioCliente(Cliente cliente, Premio premio){
        //TODO implementare
    }

    public Map<Cliente, Set<Visita>> getVisiteCliente() {
        return visiteCliente;
    }

    public void addVisita(Cliente cliente, Visita visita){
        //TODO implementare
    }

    public void updateVisita(Visita visitaOld, Visita visitaNew, Cliente cliente){
        //TODO implementare
    }

    public void removeVisita(Visita visita, Cliente cliente){
        //TODO implementare
    }

    public Set<Tessera> getTessereClienti() {
        return tessereClienti;
    }

    public void addTessera(Tessera tessera){
        Objects.requireNonNull(tessera);
        if(this.getTessereClienti().contains(tessera))
            throw new IllegalArgumentException("Tessera già registrata");
        this.getTessereClienti().add(tessera);
    }

    public void updateTessera(Tessera tesseraOld, Tessera tesseraNew){
        //TODO implementare
    }


    public Set<ProgrammaFedelta> getProgrammiDisponibili() {
        return programmiDisponibili;
    }


    public Set<PacchettoSMS> getPacchettiSMS() {
        return pacchettiSMS;
    }

    public Set<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }
}
