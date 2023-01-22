package loyality_platform_model.Models;

import java.util.Objects;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 * This class represents an SMS that may or may not have a predefined configuration.
 */
public class SMS {

    private String testo;

    private final String oraInvio;

    private boolean configurato = false;

    public SMS(String testo, String oraInvio, boolean configurato) {
        this.setTesto(testo);
        if (Objects.equals(oraInvio, ""))
            throw new IllegalArgumentException("Illegal ora for sms.");
        this.oraInvio = oraInvio;
        this.setConfigurato(configurato);
    }

    /**
     * This method returns the text of the SMS.
     * @return the text of the SMS.
     */
    public String getTesto() {
        return testo;
    }

    /**
     * This method sets the text of the SMS.
     * @param testo the text of the SMS.
     */
    public void setTesto(String testo) {
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms.");
        this.testo = testo;
    }

    /**
     * This method returns the time the SMS was sent.
     * @return the time the SMS was sent.
     */
    public String getOraInvio() {
        return oraInvio;
    }

    /**
     * This method returns <code>true</code> if SMS is configured,
     * <code>false</code> otherwise.
     * @return <code>true</code> if SMS is configured, <code>false</code> otherwise.
     */
    public boolean isConfigurato() {
        return configurato;
    }

    /**
     * This method sets whether the sms is configured or not.
     */
    public void setConfigurato(boolean configurato) {
        this.configurato = configurato;
    }

    /**
     * This method sets up the SMS configuration.
     * @param configurazioneSMS the SMS configuration.
     */
    public void setMessaggioConfigurato(ConfigurazioneSMS configurazioneSMS){
        Objects.requireNonNull(configurazioneSMS);
        this.setTesto(configurazioneSMS.getTestoConfigurato());
    }

    @Override
    public String toString() {
        return "SMS{" +
                "testo='" + testo + '\'' +
                ", oraInvio='" + oraInvio + '\'' +
                '}';
    }
}
