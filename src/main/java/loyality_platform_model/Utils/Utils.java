package loyality_platform_model.Utils;

import loyality_platform_model.Handler.*;

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
}
