package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Azienda;
import java.util.Objects;

/**
 *  Classes that represent capable a company manager
 *  to retrieve all available information.
 */
public class HandlerAzienda {

    private final DBMS dbms;


    public HandlerAzienda(Azienda azienda) {
        Objects.requireNonNull(azienda);
        this.dbms = DBMS.getInstance();
    }

    //Todo implementare.
}
