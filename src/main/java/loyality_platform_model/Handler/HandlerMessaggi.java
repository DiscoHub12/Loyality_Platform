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
        for (Azienda azienda : this.dbms.getSMSPreconfiguratiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (!this.dbms.getSMSCliente().get(azienda).isEmpty())
                    return this.dbms.getSMSPreconfiguratiAzienda().get(azienda);
            }
        }
        return null;
    }

    public ConfigurazioneSMS getSMSPreconfigurato(int idAzienda,int idSMS){
        if (idAzienda<=0 || idSMS<=0)
            throw new IllegalArgumentException("Illegal id for the company and sms.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for(SMS sms : this.dbms.getSMSPreconfiguratiAzienda().get(azienda)){
                    if(sms.getIdSMS()==idSMS){
                        return sms.getConfigurazione();
                    }
                }
            }
        }
        return null;
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
    public void creaConfigurazioneSMS(String testo){
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms.");
        new ConfigurazioneSMS(testo);
    }

    /**
     * This method allows you to send a text message to a customer.
     *
     * @param sms     sms to send.
     * @param idCliente customer who must receive the SMS.
     */
    public void inviaSMS(int idCliente, String sms) {
        //TODO da progettare
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
    public void inviaSmsGenerale(SMS sms, Set<Cliente> clienti){
        //TODO implementare
    }

    /**
     * This method allows you to send a configured text message to a customer.
     *
     */
    public void inviaSMSPreConfigurato(){
        //TODO implementare
    }

    /**
     * This method allows you to send a configured text message to a set of customers.
     *
     */
    public void inviaSMSPreconfiguratoGenerale(){
        //TODO implementare
    }
}
