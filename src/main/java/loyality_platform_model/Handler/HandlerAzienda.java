package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.Dipendente;
import loyality_platform_model.Models.ProgrammaFedelta;

import java.util.Objects;
import java.util.Set;

public class HandlerAzienda {

    private final DBMSController dbmsController;

    private final Azienda azienda;

    private static HandlerAzienda instance;

    public HandlerAzienda(Azienda azienda) {
        Objects.requireNonNull(azienda);
        this.dbmsController = DBMSController.getInstance();
        this.azienda = azienda;
    }

    public static HandlerAzienda getInstance() {
        if(instance == null){
            instance = new HandlerAzienda(getInstance().azienda);
        }
        return instance;
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
        return "Dipendente non trovato";
    }

    public Set<ProgrammaFedelta> getProgrammiAttivi(){
        return this.getAzienda().getProgrammiAttivi();
    }

    public String getDetailsProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        if(this.getAzienda().getProgrammiAttivi().contains(programmaFedelta)){
            return programmaFedelta.toString();
        }
        return "Programma fedeltà non trovato";
    }

    public Set<Cliente> getClienti(){
        return this.getAzienda().getClienti();
    }

    public String getDetailsCliente(Cliente cliente){
        Objects.requireNonNull(cliente);
        if(this.getAzienda().getClienti().contains(cliente)){
            return cliente.toString();
        }
        return "Cliente non trovato";
    }

    public Azienda getAzienda(){
        return azienda;
    }

    public String getDetailsAzienda() {
        return this.getAzienda().toString();
    }
}
