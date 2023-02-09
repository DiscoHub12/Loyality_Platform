package loyality_platform_model.Models;

import java.util.Date;

/**
 * Class that represents the structure of an SMS
 */
public class ConfigurazioneSMS {
    private static int idConfigurazione;
    /**
     * attribute representing the text written in an SMS
     */
    private String testoConfigurato;
    /**
     * Constructor that creates the configuration of an SMS
     * @param testoConfigurato text configuration
     */
    public ConfigurazioneSMS(String testoConfigurato){
        idConfigurazione++;
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
        return "ConfigurazioneSMS{" +
                "testoConfigurato='" + this.testoConfigurato + '\'' +
                '}';
    }
}

