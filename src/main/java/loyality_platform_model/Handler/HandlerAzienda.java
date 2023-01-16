package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Dipendente;
import loyality_platform_model.Models.ProgrammaFedelta;

import java.util.Objects;
import java.util.Set;

public class HandlerAzienda {

    private final DBMSController dbmsController;

    private final Azienda azienda;

    public HandlerAzienda(Azienda azienda) {
        Objects.requireNonNull(azienda);
        this.dbmsController = DBMSController.getInstance();
        this.azienda = azienda;
    }

    public String getSpazioFedelta(){
       return this.getAzienda().getSpazioFedelta().toString();
    }

    public Set<Dipendente> getUtenti() {
        return this.getAzienda().getDipendenti();
    }

    public String getDetailsUtente(Dipendente dipendente){
        Objects.requireNonNull(dipendente);
        if(this.getAzienda().getDipendenti().contains(dipendente))
            return dipendente.getDetails();
        return "Dipendente errato";
    }

    public Set<ProgrammaFedelta> getProgrammiAttivi(){
        return this.getAzienda().getProgrammiAttivi();
    }


    public Azienda getAzienda(){
        return azienda;
    }
}
