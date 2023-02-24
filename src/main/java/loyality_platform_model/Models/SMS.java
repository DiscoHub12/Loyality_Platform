package loyality_platform_model.Models;

import java.util.Date;
import java.util.Objects;

/**
 * This class represents an SMS that may or may not have a predefined configuration.
 */
public class SMS {
    private static int contatoreSMS=0;

    private final int idSMS;

    private String testo;

    private Date dataInvio;

    private ConfigurazioneSMS configurazione;

    public SMS(String testo) {
        if (Objects.equals(testo, "")){
            throw new IllegalArgumentException("Illegal text for sms.");}
        this.idSMS=++contatoreSMS;
        this.testo = testo;
    }

    public SMS(ConfigurazioneSMS configurazione){
        this.idSMS=++contatoreSMS;
        this.testo= configurazione.getTestoConfigurato();
        this.configurazione = configurazione;
    }

    public int getIdSMS(){
        return this.idSMS;
    }

    /**
     * This method returns the text of the SMS.
     * @return the text of the SMS.
     */
    public String getTesto() {
        return this.testo;
    }

    /**
     * This method sets the text of the sms.
     * @param t the text of the sms.
     */
    public void setTesto(String t){
        if(Objects.equals(t, ""))
            throw new IllegalArgumentException("Empty text");
        this.testo=t;
    }

    /**
     * This method returns the time the SMS was sent.
     * @return the time the SMS was sent.
     */
    public Date getDataInvio(){
        return this.dataInvio;
    }

    /**
     * This method returns the configuration of this sms if exists.
     * @return the configuration of this sms if exists.
     */
    public ConfigurazioneSMS getConfigurazione(){
        return this.configurazione;
    }


    @Override
    public String toString() {
        return "\t-DETAILS SMS-" +
                "\nId sms: " + this.idSMS +
                "\nTesto :" + this.testo;
    }
}
