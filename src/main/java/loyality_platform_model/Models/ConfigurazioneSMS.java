package loyality_platform_model.Models;

/**
 * Class that represents the structure of an SMS
 */
public class ConfigurazioneSMS {


    private static int contatoreConfigurazione=0;

    /**
     * attribute representing the id of the configuration
     */
    private final int idConfigurazione;

    /**
     * attribute representing the text written in an SMS
     */
    private String testoConfigurato;


    /**
     * Constructor that creates the configuration of an SMS
     * @param testoConfigurato text configuration
     */
    public ConfigurazioneSMS(String testoConfigurato){
        this.idConfigurazione=++contatoreConfigurazione;
        this.testoConfigurato=testoConfigurato;

    }
    public int getIdConfigurazione(){
        return this.idConfigurazione;
    }

    public String getTestoConfigurato(){
        return this.testoConfigurato;
    }

    public void setTestoConfigurato(String t){
        this.testoConfigurato=t;
    }

    @Override
    public String toString() {
        return "\t-DETAILS CONFIGURAZIONE-" +
                "\nId configurazione: " + this.idConfigurazione +
                "\ntestoConfigurato: " + this.testoConfigurato;
    }
}

