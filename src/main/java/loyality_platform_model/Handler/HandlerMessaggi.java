package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.ConfigurazioneSMS;
import loyality_platform_model.Models.SMS;
import java.util.Objects;
import java.util.Set;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 * Classes representing a capable message manager
 * to send text messages to a single customer or to a group of customers.
 */
public class HandlerMessaggi {

    //TODO rivedere
    //TODO implementare db

    private final DBMS dbms;

    public HandlerMessaggi() {
        this.dbms = DBMS.getInstance();
    }

    public DBMS getDbmsController() {
        return dbms;
    }

    /**
     * This method allows you to create a new SMS.
     * @param testo sms text.
     * @param oraInvio sms ora.
     * @return new sms.
     */
    public SMS creaSMS(String testo, String oraInvio){
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms.");
        if (Objects.equals(oraInvio, ""))
            throw new IllegalArgumentException("Illegal ora for sms.");
       return new SMS(testo, oraInvio);
    }

    /**
     * This method allows you to send a text message to a customer.
     * @param sms sms to send.
     * @param cliente customer who must receive the SMS.
     */
    public void inviaSMS(SMS sms, Cliente cliente){
        Objects.requireNonNull(sms);
        Objects.requireNonNull(cliente);
        SMS sms1 = creaSMS(sms.getTesto(), sms.getOraInvio());
        HandlerCliente.getInstance(cliente).getSMSCliente().add(sms1);
    }

    /**
     * This method allows you to send a text message to a set of customers.
     * @param sms sms to send.
     * @param clienti set of customers who must receive the SMS.
     */
    public void inviaSmsGenerale(SMS sms, Set<Cliente> clienti){
        Objects.requireNonNull(sms);
        Objects.requireNonNull(clienti);
        SMS sms1 = creaSMS(sms.getTesto(), sms.getOraInvio());
        for(Cliente cliente : clienti){
            HandlerCliente.getInstance(cliente).getSMSCliente().add(sms1);
        }
    }

    /**
     * This method allows you to send a configured text message to a customer.
     * @param sms sms to send.
     * @param configurazioneSMS configured text message.
     * @param cliente customer who must receive the SMS.
     */
    public void inviaSMSConfigurato(SMS sms, ConfigurazioneSMS configurazioneSMS, Cliente cliente){
        Objects.requireNonNull(sms);
        Objects.requireNonNull(configurazioneSMS);
        Objects.requireNonNull(cliente);
        sms.setConfigurato(true);
        sms.setMessaggioConfigurato(configurazioneSMS);
        SMS sms1 = creaSMS(sms.getTesto(), sms.getOraInvio());
        HandlerCliente.getInstance(cliente).getSMSCliente().add(sms1);
    }

    /**
     * This method allows you to send a configured text message to a set of customers.
     * @param sms sms to send.
     * @param configurazioneSMS configured text message.
     * @param clienti set of customers who must receive the SMS.
     */
    public void inviaSMSGeneraleConfigurato(SMS sms, ConfigurazioneSMS configurazioneSMS, Set<Cliente> clienti){
        Objects.requireNonNull(sms);
        Objects.requireNonNull(clienti);
        Objects.requireNonNull(configurazioneSMS);
        sms.setMessaggioConfigurato(configurazioneSMS);
        sms.setConfigurato(true);
        SMS sms1 = creaSMS(sms.getTesto(), sms.getOraInvio());
        for(Cliente cliente : clienti){
            HandlerCliente.getInstance(cliente).getSMSCliente().add(sms1);
        }
    }
}
