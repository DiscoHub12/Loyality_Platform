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

    /**
     * Constructor who creates an SMS manager.
     * Recall the Db instance to retrieve permanent data.
     */
    public HandlerMessaggi() {
        this.dbms = DBMS.getInstance();
    }

    /**
     * This method allows you to take a set of configuration sms of
     * a particular Company, to be identified through
     * his id.
     *
     * @param idAzienda the id about the Company.
     * @return the set of sms if the Company has any, null otherwise.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public Set<SMS> getSMSPreconfigurati(int idAzienda) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Illegal id for the company.");
        return this.dbms.getSMSPreconfigurati(idAzienda);
    }

    /**
     * This method returns a sms configuration if exists.
     *
     * @param idAzienda the id about the Company.
     * @param idSMS     the id about the Sms.
     * @return a sms configuration if exists, null otherwise.
     */
    public ConfigurazioneSMS getSMSPreconfigurato(int idAzienda, int idSMS) {
        if (idAzienda <= 0 || idSMS <= 0)
            throw new IllegalArgumentException("Illegal id for the company and sms.");
        return this.dbms.getSMSPreconfigurato(idAzienda, idSMS);
    }

    /**
     * This method allows you to create a new SMS.
     *
     * @param testo sms text.
     * @return new sms.
     */
    public SMS creaSMS(String testo) {
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms.");
        return new SMS(testo);
    }

    /**
     * This method allows you to create a new sms configuration.
     *
     * @param testo sms configuration text.
     * @return new sms configuration.
     */
    public ConfigurazioneSMS creaConfigurazioneSMS(String testo) {
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("Illegal text for sms configuration.");
        return new ConfigurazioneSMS(testo);
    }

    /**
     * This method allows you to send a text message to a customer.
     *
     * @param sms       sms to send.
     * @param idCliente customer who must receive the SMS.
     * @return <code>true</code> if the sms is sent, <code>false</code> otherwise.
     */
    public boolean inviaSMS(int idCliente, String sms) {
        if (Objects.equals(sms, ""))
            throw new IllegalArgumentException("No message written");
        if (idCliente < 1)
            throw new IllegalArgumentException("Customer id not correct");
        return this.dbms.addSMS(idCliente, creaSMS(sms));
    }


    /**
     * This method allows you to send a text message to a set of customer.
     *
     * @param sms       sms to send.
     * @param clienti customers who must receive the SMS.
     * @return <code>true</code> if the sms is sent, <code>false</code> otherwise.
     */
    public boolean inviaSmsGenerale(String sms, Set<Cliente> clienti) {
        if (Objects.equals(sms, ""))
            throw new IllegalArgumentException("no message written");
        boolean smsAll = true;
        SMS sms1 = creaSMS(sms);
        for (int i = 1; i <= clienti.size(); i++) {
            boolean smsInviato = false;
            for (Cliente cliente : clienti) {
                if (cliente.getIdCliente() == i) {
                    dbms.addSMS(cliente.getIdCliente(), sms1);
                    smsInviato = true;
                    break;
                }
            }
            if (!smsInviato) {
                smsAll = false;
            }
        }
        return smsAll;
    }

    /**
     * This method allows you to send a sms configuration to a customer.
     *
     * @param smsConfigurato       sms to send.
     * @param idCliente customer who must receive the SMS.
     * @return <code>true</code> if the sms is sent, <code>false</code> otherwise.
     */
    public boolean inviaSMSPreConfigurato(int idCliente, ConfigurazioneSMS smsConfigurato) {
        if (Objects.isNull(smsConfigurato))
            throw new IllegalArgumentException("unable to send empty messages");
        if (idCliente < 1)
            throw new IllegalArgumentException("Customer id not correct");
        SMS messaggio = new SMS(smsConfigurato);
        return this.dbms.addSMS(idCliente, messaggio);
    }


    /**
     * This method allows you to send a sms configuration to set of customer.
     *
     * @param smsConfigurato       sms to send.
     * @param clienti customers who must receive the SMS.
     * @return <code>true</code> if the sms is sent, <code>false</code> otherwise.
     */
    public boolean inviaSMSPreconfiguratoGenerale(Set<Cliente> clienti, ConfigurazioneSMS smsConfigurato) {
        if (Objects.isNull(smsConfigurato))
            throw new IllegalArgumentException("Error in sms");
        boolean smsAll = true;
        SMS sms1 = new SMS(smsConfigurato);
        for (int i = 1; i <= clienti.size(); i++) {
            boolean smsInviato = false;
            for (Cliente cliente : clienti) {
                if (cliente.getIdCliente() == i) {
                    dbms.addSMS(cliente.getIdCliente(), sms1);
                    smsInviato = true;
                    break;
                }
            }
            if (!smsInviato) {
                smsAll = false;
            }
        }
        return smsAll;
    }

    /**
     * This method adds a new sms configuration.
     * @param idAzienda the id about the Company.
     * @param testo the text of the sms configuration.
     * @return <code>true</code> if the sms configuration is added, <code>false</code> otherwise.
     */
    public boolean aggiungiConfigurazioneSMS(int idAzienda, String testo) {
        if (idAzienda < 1)
            throw new IllegalArgumentException("Company id not correct");
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("you have not written the text for the message");
        ConfigurazioneSMS configurazioneSMS = creaConfigurazioneSMS(testo);
        SMS newSMS = new SMS(configurazioneSMS);
        return this.dbms.addConfigurazioneSMS(idAzienda, newSMS);
    }

    /**
     * This method updates a new sms configuration.
     * @param idAzienda the id about the Company.
     * @param idConfigurazione the id of the sms configuration.
     * @param testo the text of the sms configuration.
     * @return <code>true</code> if the sms configuration is updated, <code>false</code> otherwise.
     */
    public boolean aggiornaConfigurazioneSMS(int idAzienda, int idConfigurazione, String testo) {
        if (idAzienda < 1 || idConfigurazione < 1)
            throw new IllegalArgumentException("Company id or sms id are not correct");
        if (Objects.equals(testo, ""))
            throw new IllegalArgumentException("you have not written the text for the message");
        SMS newSMS = creaSMS(testo);
        return this.dbms.updateConfigurazioneSMS(idAzienda, idConfigurazione, newSMS);
    }

    /**
     * This method removes a sms configuration.
     * @param idAzienda the id about the Company.
     * @param idConfigurazione the id of the sms configuration to remove.
     * @return <code>true</code> if the sms configuration is removed, <code>false</code> otherwise.
     */
    public boolean rimuoviConfigurazioenSMS(int idAzienda, int idConfigurazione) {
        if (idAzienda < 1 || idConfigurazione < 1)
            throw new IllegalArgumentException("Company id or sms id are not correct");
        return this.dbms.removeConfigurazioneSMS(idAzienda, idConfigurazione);
    }
}
