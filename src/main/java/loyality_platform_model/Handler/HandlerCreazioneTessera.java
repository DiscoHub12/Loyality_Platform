package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Cliente;

import java.util.Objects;

public class HandlerCreazioneTessera {

    private final DBMSController dbmsController;



    public HandlerCreazioneTessera(){
   this.dbmsController=DBMSController.getInstance();

    }

    public boolean creaTessera(Cliente cliente){
        Objects.requireNonNull(cliente);
        //Todo implementare
        return false;
    }
    public void inviaTessera(){
        //TODO implementare
    }
}
