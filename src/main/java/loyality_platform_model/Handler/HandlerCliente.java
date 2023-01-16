package loyality_platform_model.Handler;

import loyality_platform_model.Activity.AttivitaProfiloCliente;
import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.*;

import java.util.List;
import java.util.Objects;

//Todo finire.
public class HandlerCliente {

    private final DBMSController dbmsController;

    private Cliente clienteIdentificato;

    private AttivitaProfiloCliente attivitaProfiloCliente;

    public HandlerCliente(){
        this.dbmsController = DBMSController.getInstance();
        this.attivitaProfiloCliente = null;
    }

    public AttivitaProfiloCliente identificaCliente(String nome, String cognome){
        //Todo implementare, manca DBMSController.
        return null;
    }

    public Tessera getTesseraCliente(){
        //Todo implementare, manca Cliente.
        return null;
    }

    public List<ProgrammaFedelta> getProgrammiFedeltaCliente(){
        //Todo implementare, manca Cliente.
        return null;
    }

    public List<Visita> getVisite(){
        //Todo implementare, manca Cliente.
        return null;
    }

    public List<SMS> getSMSCliente(){
        //Todo implementare, manca Cliente.
        return null;
    }

    public String getDetailsCliente(){
        //Todo implementare, manca getDetails (toString) sul Cliente.
        return "";
    }

    public void esciProfiloCliente(){
        this.clienteIdentificato = null;
        this.attivitaProfiloCliente = null;
    }
}
