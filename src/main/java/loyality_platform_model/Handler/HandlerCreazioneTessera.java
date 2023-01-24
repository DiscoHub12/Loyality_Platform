package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.Tessera;
import java.util.Objects;
/**
 * IMPLEMENTED BY : Fabio Evangelista.
 */
/**
 * Class that represents the possibility of creating a new customer card
 * and sending it to the database
 */
public class HandlerCreazioneTessera {
    private final DBMSController dbmsController;
    public HandlerCreazioneTessera(){
        this.dbmsController=DBMSController.getInstance();
    }
    /**
     * method that creates a new card for the customer,
     * checks if the passed object is null or not.
     * Based on the result, the card is created and passed to the Database.
     * @param c customer for which to create the card
     * @return true se l'oggetto passato non era nullo
     */
    public boolean creaTessera(Cliente c){
        Objects.requireNonNull(c);
        Tessera tessera= new Tessera(c);
        this.dbmsController.addTessera(tessera,c);
        return true;
    }
}
