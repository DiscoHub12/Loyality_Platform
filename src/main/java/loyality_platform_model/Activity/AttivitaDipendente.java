package loyality_platform_model.Activity;

import loyality_platform_model.Handler.HandlerCliente;
import loyality_platform_model.Handler.HandlerCreazioneTessera;
import loyality_platform_model.Handler.HandlerMessaggi;
import loyality_platform_model.Handler.HandlerPunti;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;

import java.util.List;

//Todo finire.
public class AttivitaDipendente {

    public void creaTessera(HandlerCreazioneTessera gestoreCreazioneTessera, Cliente cliente){
        //Todo implementare, manca HandlerCreazioneTessera
    }

    public void convalidaAcquisto(){
        //Todo implementare, ancora da fare su Visual Paradigm.
    }

    public void identificaCliente(HandlerCliente gestoreCliente, String nome, String cognome){
        //Todo implementare, manca HandlerCliente
    }

    public void inviaSMSGenerali(HandlerMessaggi gestoreMessaggi, List<Cliente> clienti, SMS sms){
        //Todo implementare, manca HandlerMessaggi
    }

    public void aggiungiPuntiGenerali(HandlerPunti gestorePunti, List<Cliente> clienti, int numeroPunti){
        //Todo implementare, manca HandlerPunti
    }
}
