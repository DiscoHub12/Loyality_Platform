package loyality_platform_model.Activity;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.*;
import java.util.Objects;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 *  Class that represents the activities that the store manager is able to carry out.
 *  Indeed, within it, we find a whole series of methods that allow the store manager to add,
 *  remove or modify an employee and a loyalty program and other actions.
 *
 */
public class AttivitaTitolare {

    public final DBMSController dbmsController;

    public final Azienda azienda;

    public AttivitaTitolare(Azienda azienda) {
        this.dbmsController = DBMSController.getInstance();
        this.azienda= azienda;
    }

    /**
     * This method returns the company related to the store manager.
     * @return the company.
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     * This method allows you to create a new employee for the company.
     * @param nome the name of the new employee.
     * @param cognome the last name of the new employee.
     * @param email the email of the new employee.
     * @param restrizioni the restrictions of the new employee.
     */
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

    /**
     * This method allows you to change information relating to a
     * particular employee within the company in question.
     * @param dipendente employee considered.
     * @param email the new email of employee.
     * @param restrizioni the new restrictions of employee.
     */
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

    /**
     * This method allows you to remove a particular employee within the company.
     * @param dipendente employee to remove.
     */
    public void rimuoviUtente(Dipendente dipendente){
        Objects.requireNonNull(dipendente);
        this.getAzienda().rimuoviDipendente(dipendente);
        this.dbmsController.removeDipendenteAzienda(this.getAzienda(), dipendente);
    }

    /**
     * This method allows you to add a new loyalty program for the company,
     * choosing from those made available by the platform.
     * @param programmaFedelta loyalty program to add.
     */
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        //this.dbmsController.getProgrammiDisponibili();
        this.getAzienda().addProgrammaFedelta(programmaFedelta);
        this.dbmsController.addProgrammaAzienda(this.getAzienda(), programmaFedelta);
    }

    /**
     * This method allows you to go and modify an active loyalty program in the company.
     * @param programmaFedelta loyalty program to edit.
     */
    public void modificaProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        //TODO implementare
    }

    /**
     * This method allows you to remove a particular loyalty program for my company.
     * @param programmaFedelta loyalty program to be removed.
     */
    public void rimuoviProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        this.getAzienda().rimuoviProgrammaFedelta(programmaFedelta);
        this.dbmsController.removeProgrammaAzienda(this.getAzienda(), programmaFedelta);
        }

    /**
     * This method allows you to go and edit my company's loyalty space.
     */
    public void modificaSpazioFedelta(){
        //TODO implementare
     }


    /**
     * This method allows you to purchase a subscription within the platform.
     * @param abbonamento purchased subscription.
     */
    //TODO finire db
    public void addAbbonamento(Abbonamento abbonamento){
        Objects.requireNonNull(abbonamento);
        this.getAzienda().setAbbonamento(abbonamento);
    }

    /**
     * This method allows you to purchase an SMS package within the platform.
     * @param pacchettoSms sms package to buy.
     */
    //TODO finire db
    public void addPacchettoSms(PacchettoSMS pacchettoSms){
        Objects.requireNonNull(pacchettoSms);
        this.getAzienda().setPacchettoSms(pacchettoSms);
    }

    //TODO implementare compila e modifica catalogo premi
    //TODO implementare invita esercenti

}
