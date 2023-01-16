package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Dipendente;
import loyality_platform_model.Models.GestorePuntoVendita;


import java.util.Objects;

public class HandlerCreazioneUtente {

    private final DBMSController dbmsController;
    private Dipendente dipendente;

    public HandlerCreazioneUtente() {
        this.dbmsController = DBMSController.getInstance();
    }

    public void creaDipendente(String nome, String cognome, String email) {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(cognome);
        Objects.requireNonNull(email);
       // Dipendente dipendente = new Dipendente(nome, cognome, email);
        //this.dbmsController.addDipendente(dipendente);
        //da vedere
    }
    public void inviaUtente(Dipendente dipednente){
        //TODO implementare.. ma a che serve?

    }
}
