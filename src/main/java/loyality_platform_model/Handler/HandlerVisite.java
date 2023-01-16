package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;
import loyality_platform_model.Models.Visita;

import java.util.Date;
import java.util.Objects;

//Todo finire.
public class HandlerVisite {

    public DBMSController dbmsController;

    public HandlerVisite(){
        this.dbmsController = DBMSController.getInstance();
    }

    public Visita creaVisita(String luogo, Date data, String ora){
        Objects.requireNonNull(data);
        if(Objects.equals(luogo, "") || Objects.equals(ora, ""))
            throw new IllegalArgumentException("Illegal place or hour for the Visit.");
        return new Visita(luogo, data, ora);
    }

    public void aggiungiVisita(Cliente cliente, Visita visita){
        //Todo implementare, manca Cliente
    }

    public void modificaVisita(Visita visita, String luogo, Date data, String ora){
        Objects.requireNonNull(data);
        if(Objects.equals(luogo, "") || Objects.equals(ora, ""))
            throw new IllegalArgumentException("Illegal place or hour for the Visit.");
        visita.setLuogo(luogo);
        visita.setData(data);
        visita.setOra(ora);
    }

    public void rimuoviVisita(Visita visita, Cliente cliente){
        //Todo implementare, manca Cliente
    }

    public void inviaSMS(HandlerMessaggi gestoreMessaggi, Cliente cliente, SMS sms){
        //Todo implementare, manca Cliente, HandlerMessaggi.
    }

}
