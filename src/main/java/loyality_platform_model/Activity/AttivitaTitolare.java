package loyality_platform_model.Activity;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.*;

import java.util.Objects;

public class AttivitaTitolare {

    public final DBMSController dbmsController;

    public final Azienda azienda;

    public AttivitaTitolare(Azienda azienda) {
        this.dbmsController = DBMSController.getInstance();
        this.azienda= azienda;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void creaUtente(String nome, String cognome, String email, boolean restrizioni) {
        if(Objects.equals(nome, ""))
            throw new IllegalArgumentException("Illegal name for user");
        if(Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal surname for user");
        if(Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal email for user");
        Dipendente dipendente= new  Dipendente(nome, cognome, email, restrizioni);
        this.getAzienda().addDipendente(dipendente);
        this.dbmsController.addDipendenteAzienda(this.getAzienda(), dipendente);
    }


    //Da rivedere
    public void modificaUtente(Dipendente dipendente, String email, boolean restrizioni){
        Objects.requireNonNull(dipendente);
        if(Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal email for user");
        if(this.getAzienda().getDipendenti().contains(dipendente)){
            Dipendente dipendente1 = new Dipendente(dipendente.getNome(), dipendente.getCognome(), email, restrizioni);
            this.getAzienda().getDipendenti().remove(dipendente);
            this.getAzienda().getDipendenti().add(dipendente1);
            this.dbmsController.modificaDipendenteAzienda(dipendente, this.getAzienda(), dipendente1);
        }
        throw new IllegalArgumentException("User not exists");
    }

    public void rimuoviUtente(Dipendente dipendente){
        Objects.requireNonNull(dipendente);
        if(this.getAzienda().getDipendenti().contains(dipendente)){
            this.getAzienda().getDipendenti().remove(dipendente);
            this.dbmsController.removeDipendenteAzienda(this.getAzienda(), dipendente);
        }
        throw new IllegalArgumentException("User not exists");
    }

    //TODO finire
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        this.getAzienda().addProgrammaFedelta(programmaFedelta);
        //Aggiungi a db
    }

    //TODO finire
    public void rimuoviProgramma (ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        for(ProgrammaFedelta programmaFedelta1 : this.getAzienda().getProgrammiAttivi()){
            if(programmaFedelta.equals(programmaFedelta1)){
                this.getAzienda().rimuoviProgrammaFedelta(programmaFedelta);
                //Rimuovi da db
            }
        }
    }

    //TODO implementare modifica programma fedeltà
    //TODO implementare catalogo premi
    //TODO implementare invita esercenti

    //TODO finire db
    public void addAbbonamento(Abbonamento abbonamento){
        Objects.requireNonNull(abbonamento);
        this.getAzienda().setAbbonamento(abbonamento);
    }

    //TODO finire db
    public void addPacchettoSms(PacchettoSMS pacchettoSms){
        Objects.requireNonNull(pacchettoSms);
        this.getAzienda().setPacchettoSms(pacchettoSms);
    }



}
