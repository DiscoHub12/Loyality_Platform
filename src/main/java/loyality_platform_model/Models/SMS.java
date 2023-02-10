package loyality_platform_model.Models;

import java.util.Date;
import java.util.Objects;

/**
 * This class represents an SMS that may or may not have a predefined configuration.
 */
public class SMS {
    private static int contatoreSMS=0;
    private int idSMS;
    private String testo;

    private Date dataInvio;

    private ConfigurazioneSMS configurazione;

    public SMS(String testo, Date dataInvio) {
        if (Objects.equals(testo, "")){
            throw new IllegalArgumentException("Illegal text for sms.");}
        this.idSMS=++contatoreSMS;
        this.testo = testo;
        this.dataInvio = dataInvio;

    }
    public SMS(ConfigurazioneSMS configurazione, Date dataInvio){
        this.idSMS=++contatoreSMS;
        this.testo= configurazione.getTestoConfigurato();
        this.dataInvio=dataInvio;
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
     * This method returns the time the SMS was sent.
     * @return the time the SMS was sent.
     */
    public Date getDataInvio(){
        return this.dataInvio;
    }
    public ConfigurazioneSMS getConfigurazione(){
        return this.configurazione;
    }
    @Override
    public String toString() {
        return "SMS{" +
                "testo='" + this.testo + '\'' +
                ", oraInvio='" + this.dataInvio + '\'' +
                '}';
    }
}
