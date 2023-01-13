package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Cliente;

import java.util.List;

//Todo finire.
public class HandlerPunti {

    public DBMSController dbmsController;

    public HandlerPunti(){
        this.dbmsController = DBMSController.getInstance();
    }

    public void aggiungiPunti(Cliente cliente, int numeroPunti){
        //Todo implementare, manca Cliente e Tessera
    }

    public void rimuoviPunti(Cliente cliente, int numeroPunti){
        //Todo implementare, manca Cliente e Tessera
    }

    public void aggiungiPuntiGenerale(List<Cliente> clienti, int numeroPunti){
        //Todo implementare, manca Cliente e Tessera
    }

    public void rimuoviPuntiGenerale(List<Cliente> clienti, int numeroPunti){
        //Todo implementare, manca Cliente e Tessera
    }

}
