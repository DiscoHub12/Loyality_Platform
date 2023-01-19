package loyality_platform_model.Activity;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.*;

import java.util.Objects;

public class AttivitaTitolare {

    public final DBMSController dbmsController;

    public final GestorePuntoVendita gestorePuntoVendita;

    public AttivitaTitolare(GestorePuntoVendita gestorePuntoVendita) {
        this.dbmsController = DBMSController.getInstance();
        this.gestorePuntoVendita= gestorePuntoVendita;
    }

    public GestorePuntoVendita getGestorePuntoVendita() {
        return gestorePuntoVendita;
    }

    public void creaUtente(String nome, String cognome, String email, boolean restrizioni) {
        if(Objects.equals(nome, ""))
            throw new IllegalArgumentException("Illegal name for user");
        if(Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal surname for user");
        if(Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal email for user");
        Dipendente dipendente= new  Dipendente(nome, cognome, email, restrizioni);
        this.getGestorePuntoVendita().getAzienda().addDipendente(dipendente);
        //Aggiungi a db
    }


    //Da rivedere
    public void modificaUtente(Dipendente dipendente, String email, boolean restrizioni){
        Objects.requireNonNull(dipendente);
        for (Dipendente dipendente1 : this.getGestorePuntoVendita().getAzienda().getDipendenti()){
            if(dipendente.equals(dipendente1)){
                dipendente.setEmail(email);
                dipendente.setRestrizioni(this.getGestorePuntoVendita(), restrizioni);
            }
        }
    }

    public void rimuoviUtente(Dipendente dipendente){
        Objects.requireNonNull(dipendente);
        for(Dipendente dipendente1 : this.getGestorePuntoVendita().getAzienda().getDipendenti()) {
            if(dipendente.equals(dipendente1)){
                this.getGestorePuntoVendita().getAzienda().rimuoviDipendente(dipendente);
                //Rimuovi da db
            }
        }
    }

    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        this.getGestorePuntoVendita().getAzienda().addProgrammaFedelta(programmaFedelta);
        //Aggiungi a db
    }

    public void rimuoviProgramma (ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        for(ProgrammaFedelta programmaFedelta1 : this.getGestorePuntoVendita().getAzienda().getProgrammiAttivi()){
            if(programmaFedelta.equals(programmaFedelta1)){
                this.getGestorePuntoVendita().getAzienda().rimuoviProgrammaFedelta(programmaFedelta);
                //Rimuovi da db
            }
        }
    }

    //TODO implementare modifica programma fedeltà
    //TODO implementare catalogo premi
    //TODO implementare invita esercenti

    public void addAbbonamento(Abbonamento abbonamento){
        Objects.requireNonNull(abbonamento);
        this.getGestorePuntoVendita().getAzienda().setAbbonamento(abbonamento);
    }

    public void addPacchettoSms(PacchettoSMS pacchettoSms){
        Objects.requireNonNull(pacchettoSms);
        this.getGestorePuntoVendita().getAzienda().setPacchettoSms(pacchettoSms);
    }



}
