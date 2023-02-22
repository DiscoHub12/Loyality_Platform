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
    public boolean inviaSmsGenerale(String sms, Set<Cliente> clienti){
        if(Objects.equals(sms,""))
            throw new IllegalArgumentException("no message written");
        boolean smsAll=true;
        SMS sms1=creaSMS(sms);
        for(int i=1; i<=clienti.size();i++){
            boolean smsInviato=false;
            for(Cliente cliente : clienti){
                if(cliente.getIdCliente() == i){
                    dbms.addSMS(cliente.getIdCliente(),sms1);
                    smsInviato = true;
                    break;
                }
            }
            if(!smsInviato){
                smsAll=false;
            }
        }
        return smsAll;
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
        SMS messaggio = new SMS(smsConfigurato);
        this.dbms.addSMS(idCliente,messaggio);
    }



    /**
     * This method allows you to send a configured text message to a set of customers.
     *
     */
    public boolean inviaSMSPreconfiguratoGenerale(Set<Cliente> clienti,ConfigurazioneSMS smsConfigurato){
        if(Objects.isNull(smsConfigurato))
            throw new IllegalArgumentException("Error in sms");
        boolean smsAll=true;
        SMS sms1= new SMS(smsConfigurato);
        for(int i=1; i<=clienti.size();i++){
            boolean smsInviato=false;
            for(Cliente cliente : clienti){
                if(cliente.getIdCliente() == i){
                    dbms.addSMS(cliente.getIdCliente(),sms1);
                    smsInviato = true;
                    break;
                }
            }
            if(!smsInviato){
                smsAll=false;
            }
        }
        return smsAll;
    }
    public void aggiungiConfigurazioneSMS(int idAzienda, String testo){
        if(idAzienda<1)
            throw new IllegalArgumentException("Company id not correct");
        if(Objects.equals(testo,""))
            throw new IllegalArgumentException("you have not written the text for the message");
        ConfigurazioneSMS configurazioneSMS = creaConfigurazioneSMS(testo);
        SMS newSMS = new SMS(configurazioneSMS);
        this.dbms.addConfigurazioneSMS(idAzienda,newSMS);
    }
    public void aggiornaConfigurazioneSMS(int idAzienda,int idConfigurazione,String testo){
        if(idAzienda<1 || idConfigurazione<1)
            throw new IllegalArgumentException("Company id or sms id are not correct");
        if(Objects.equals(testo,""))
            throw new IllegalArgumentException("you have not written the text for the message");
        SMS newSMS = creaSMS(testo);
        this.dbms.updateConfigurazioneSMS(idAzienda,idConfigurazione,newSMS);
    }
    public void rimuoviConfigurazioenSMS(int idAzienda, int idConfigurazione){
        if(idAzienda<1 || idConfigurazione<1)
            throw new IllegalArgumentException("Company id or sms id are not correct");
        this.dbms.removeConfigurazioneSMS(idAzienda, idConfigurazione);
    }
}
