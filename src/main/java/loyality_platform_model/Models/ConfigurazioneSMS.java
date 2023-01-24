package loyality_platform_model.Models;

import java.util.Date;
/**
 * IMPLEMENTED BY : Fabio Evangelista.
 */
/**
 * Class that represents the structure of an SMS
 */
public class ConfigurazioneSMS {
    /**
     * attribute representing the text written in an SMS
     */
    private String testoConfigurato;
    /**
     * attribute that indicates when an SMS was written
     */
    private Date ora;
    /**
     * Constructor that creates the configuration of an SMS
     * @param ora time the SMS was written
     * @param testoConfigurato text configuration
     */
    public ConfigurazioneSMS(String testoConfigurato, Date ora){
        this.testoConfigurato=testoConfigurato;
        this.ora=ora;
    }
    public String getTestoConfigurato(){
        return testoConfigurato;
    }
    public void setTestoConfigurato(String t){
        this.testoConfigurato=t;
    }
    public Date getOra(){
        return ora;
    }
    public void setOra(Date o){
        this.ora=o;
    }

    @Override
    public String toString() {
        return "ConfigurazioneSMS{" +
                "testoConfigurato='" + testoConfigurato + '\'' +
                ", ora=" + ora +
                '}';
    }
}

