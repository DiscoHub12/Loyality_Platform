package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.ConfigurazioneSMS;
import loyality_platform_model.Models.SMS;

import java.util.Objects;
import java.util.Set;

/***
 * Classes representing a capable message manager
 * to send text messages to a single customer or to a group of customers.
 */

public class HandlerMessaggi {

    private final DBMS dbms;

    public HandlerMessaggi() {
        this.dbms = DBMS.getInstance();
    }

    public DBMS getDbmsController() {
        return dbms;
    }
    public Set<SMS> getSMSPreconfigurati(int idAzienda){
        if (idAzienda<=0)
            throw new IllegalArgumentException("Illegal id for the company.");
        return this.dbms.getSMSPreconfigurati(idAzienda);
            }


    public ConfigurazioneSMS getSMSPreconfigurato(int idAzienda,int idSMS){
        if (idAzienda<=0 || idSMS<=0)
            throw new IllegalArgumentException("Illegal id for the company and sms.");
        return this.dbms.getSMSPreconfigurato(idAzienda,idSMS);
    }

    /**
     * This method allows you to create a new SMS.
     *
     * @param testo    sms text.
     * @return new sms.
     */
    public SMS creaSMS(String testo){
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms.");
        return new SMS(testo);
    }
    public SMS creaSMSPreconfigurato(ConfigurazioneSMS sms){
        if(Objects.isNull(sms))
            throw new IllegalArgumentException(("Empty message"));
        return new SMS(sms);
    }
    public ConfigurazioneSMS creaConfigurazioneSMS(String testo){
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms.");
        return new ConfigurazioneSMS(testo);
    }

    /**
     * This method allows you to send a text message to a customer.
     *
     * @param sms     sms to send.
     * @param idCliente customer who must receive the SMS.
     */
    public void inviaSMS(int idCliente, String sms) {
        if(Objects.equals(sms,""))
            throw new IllegalArgumentException("no message written");
        if(idCliente<1)
            throw new IllegalArgumentException("Customer id not correct");
                this.dbms.addSMS(idCliente,creaSMS(sms));
                }

    /**
     * This method allows you to send a text message to a set of customers.
     *
     * @param sms     sms to send.
     * @param clienti set of customers who must receive the SMS.
     */


    /**
     *
     * @param sms
     * @param clienti
     */
    public void inviaSmsGenerale(String sms, Set<Cliente> clienti){
        if(Objects.isNull(sms))
            throw new IllegalArgumentException("Error in sms");
        for(Cliente cliente : clienti){
            this.dbms.addSMS(cliente.getIdCliente(),creaSMS(sms));
        }
    }

    /**
     * This method allows you to send a configured text message to a customer.
     *
     */
    public void inviaSMSPreConfigurato(int idCliente, ConfigurazioneSMS smsConfigurato){
        if(Objects.isNull(smsConfigurato))
            throw new IllegalArgumentException("unable to send empty messages");
        if(idCliente<1)
            throw new IllegalArgumentException("Customer id not correct");
        SMS messaggio = creaSMSPreconfigurato(smsConfigurato);
        this.dbms.addSMS(idCliente,messaggio);
    }



    /**
     * This method allows you to send a configured text message to a set of customers.
     *
     */
    public void inviaSMSPreconfiguratoGenerale(Set<Cliente> clienti,ConfigurazioneSMS smsConfigurato){
        if(Objects.isNull(smsConfigurato))
            throw new IllegalArgumentException("Error in sms");
        for(Cliente cliente : clienti){
            SMS messaggio= creaSMSPreconfigurato(smsConfigurato);
            this.dbms.addSMS(cliente.getIdCliente(),messaggio);
        }
    }
}
