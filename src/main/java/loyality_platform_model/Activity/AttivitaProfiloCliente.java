package loyality_platform_model.Activity;

import loyality_platform_model.Handler.HandlerMessaggi;
import loyality_platform_model.Handler.HandlerPunti;
import loyality_platform_model.Handler.HandlerVisite;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;
import loyality_platform_model.Models.Visita;

import java.util.Objects;

//Todo finire.
public class AttivitaProfiloCliente {

    public Cliente clienteIdentificato;

    public AttivitaProfiloCliente(Cliente clienteIdentificato){
        Objects.requireNonNull(clienteIdentificato);
    }

    public void aggiungiPunti(HandlerPunti gestorePunti, int numeroPunti){
        //Todo implementare, manca HandlerPunti.
    }

    public void rimuoviPunti(HandlerPunti gestorePunti, int numeroPunti){
        //Todo implementare, manca HandlerPunti
    }

    public void aggiungiCodiceSconto(){
        //Todo, ancora non visto su VP.
    }

    public void inviaSMS(HandlerMessaggi gestoreMessaggi, SMS sms){
        //Todo implementare, manca HandlerMessaggi
    }

    public void aggiungiVisita(HandlerVisite gestoreVisite, Visita visita){
        //Todo implementare, manca HandlerVisite
    }

    public void modificaVisita(HandlerVisite gestoreVisite, Visita visita){
        //Todo implementare, manca HandlerVisite
    }

    public void rimuoviVisita(HandlerVisite gestoreVisite, Visita visita){
        //Todo implementare, manca HandlerVisite
    }
}
