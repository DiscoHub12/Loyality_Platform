package loyality_platform_model.Utils;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.*;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.Dipendente;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class, as the name represents, represents a class of "Useful" components,
 * i.e. it is composed only of Controllers and is composed of all the get
 * that allow to take all these Handlers.
 * Since Handlers are useful controllers called in different sections of the code,
 * to reduce duplicate code and not instantiate Handlers every time,
 * a class composed of only callable controllers has been created.
 */
public class Utils {

    /**
     * Handler class for Company.
     */
    private final HandlerAzienda handlerAzienda;

    /**
     * Handler class for Users.
     */
    private final HandlerCliente handlerCliente;

    /**
     * Handler class for messages.
     */
    private final HandlerMessaggi handlerMessaggi;

    /**
     * Handler class for Prizes and Reward Catalog.
     */
    private final HandlerPremi handlerPremi;

    /**
     * Handler class for Loyalty Programs.
     */
    private final HandlerProgrammaFedelta handlerProgrammaFedelta;

    /**
     * Handler class for Cards.
     */
    private final HandlerTessera handlerTessera;

    /**
     * Handler class for Visits.
     */
    private final HandlerVisite handlerVisite;


    public Utils(){
        this.handlerAzienda = new HandlerAzienda();
        this.handlerCliente = new HandlerCliente();
        this.handlerMessaggi = new HandlerMessaggi();
        this.handlerPremi = new HandlerPremi();
        this.handlerProgrammaFedelta = new HandlerProgrammaFedelta();
        this.handlerTessera = new HandlerTessera();
        this.handlerVisite = new HandlerVisite();
    }

    public HandlerAzienda getHandlerAzienda() {
        return handlerAzienda;
    }


    public HandlerCliente getHandlerCliente() {
        return handlerCliente;
    }


    public HandlerMessaggi getHandlerMessaggi() {
        return handlerMessaggi;
    }


    public HandlerPremi getHandlerPremi() {
        return handlerPremi;
    }


    public HandlerProgrammaFedelta getHandlerProgrammaFedelta() {
        return handlerProgrammaFedelta;
    }


    public HandlerTessera getHandlerTessera() {
        return handlerTessera;
    }


    public HandlerVisite getHandlerVisite() {
        return handlerVisite;
    }


    public Azienda getAziendaByLogin(String nome, String cognome){
        for(Azienda azienda : DBMS.getInstance().getAziendeIscritte()){
            if(Objects.equals(azienda.getTitolare().getNome(), nome) && Objects.equals(azienda.getTitolare().getCognome(), cognome))
                return azienda;
        }
        return null;
    }

    public Azienda getAziendaByLoginDipendente(String nome, String cognome){
        for(Map.Entry<Azienda, Set<Dipendente>> entry: DBMS.getInstance().getDipendentiAzienda().entrySet()){
            for(Dipendente dipendente : entry.getValue()){
                if(Objects.equals(dipendente.getNome(), nome) && Objects.equals(dipendente.getCognome(), cognome))
                    return entry.getKey();
            }
        }
        return null;
    }

   public Cliente getClienteByLogin(String nome, String cognome){
        for(Cliente cliente: DBMS.getInstance().getClientiIscritti()){
            if(Objects.equals(cliente.getNome(), nome) && Objects.equals(cliente.getCognome(), cognome))
                return cliente;
        }
        return null;
   }

}
