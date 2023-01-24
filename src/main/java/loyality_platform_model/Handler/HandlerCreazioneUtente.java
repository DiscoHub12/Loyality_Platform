package loyality_platform_model.Handler;

import loyality_platform_model.Models.*;
import loyality_platform_model.DBController.DBMSController;
import java.util.Objects;
/**
 * IMPLEMENTED BY : Fabio Evangelista.
 */
/**
 * Class that represents the possibility of creating a new user
 * and sending it to the database
 */
public class HandlerCreazioneUtente {

    private final DBMSController dbmsController;
    private Dipendente dipendente;
    public final Azienda azienda;

    public HandlerCreazioneUtente(Azienda azienda) {
        this.dbmsController = DBMSController.getInstance();
        this.azienda= azienda;
    }
    public Azienda getAzienda() {
        return azienda;
    }
    /**
     * this method creates a user,
     * check if the passed parameters are null or not.
     * Based on the result it creates the employee,
     * passes it to the Company class and to the Database
     * @param nome employee name
     * @param cognome employee surname
     * @param email employee email
     * @param restrizioni restrictions imposed on the employee
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

}
