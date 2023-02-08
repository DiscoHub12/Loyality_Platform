package loyality_platform_model.Handler;

import ch.qos.logback.core.net.server.Client;
import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

import java.util.Objects;
import java.util.Set;


/**
 * Class that rapresent a Costumer manager capable
 * of fetching all available information and able to identify a Client.
 * Inside, it has a temporary variable that represents any identified
 * customer, and a class (object) that represents all the activities
 * that can be done within the customer's profile.
 */
public class HandlerCliente {

    /**
     * This attribute represents the DataBase.
     */
    private final DBMS dbms;

    /**
     * Costructor who creates a Client manager.
     * Recall the Db instance to retrieve permanent data.
     */
    public HandlerCliente() {
        this.dbms = DBMS.getInstance();
    }

    /**
     * This method allows you to identify a Customer
     * who will be searched within the platform by name and surname.
     *
     * @param nome    the name about the Costumer to identify.
     * @param cognome the surname about the Costumer to identify.
     * @return a Costumer object if exists, null otherwise.
     * @throws IllegalArgumentException if the Name or Surname about the Customer is not correct.
     */
    public Cliente identificaCliente(String nome, String cognome) {
        if (Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal name or surname for identified Costumer.");
        for (Cliente cliente : this.dbms.getClientiIscritti()) {
            if (Objects.equals(cliente.getNome(), nome) && Objects.equals(cliente.getCognome(), cognome)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * This method allows you to identify a Customer
     * who will be searched within the platform by the card code if he has
     * one.
     *
     * @param idTessera the card code about the Costumer to identify.
     * @return a Costumer object if exists, null otherwise.
     * @throws IllegalArgumentException if the idTessera is not correct
     */
    public Cliente identificaClienteTessera(int idTessera) {
        if (idTessera <= 0)
            throw new IllegalArgumentException("Illegal number of Costumer's Card.");
        for (Tessera tessera : this.dbms.getTessereClienti()) {
            //Todo manca il metodo tessera getIdTessera()
        }
        return null;
    }

    /**
     * This method allows you to identify a Customer
     * who will be searched within the platform by id.
     *
     * @param idCliente the id about the Costumer to identify;
     * @return a Costumer object if exists, null otherwise.
     * @throws IllegalArgumentException if the idCliente is not correct.
     */
    public Cliente identificaClienteCodice(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Costumer.");
        for (Cliente cliente : this.dbms.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * This method allows you to take a Card of
     * a particular Costumer, to be identified through
     * his id.
     *
     * @param idCliente the id about the Costumer.
     * @return the Card if the Costumer has one, null otherwise.
     * @throws IllegalArgumentException if the idCliente is not correct.
     */
    public Tessera getTesseraCliente(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer.");
        for (Tessera tessera : this.dbms.getTessereClienti()) {
            if (tessera.getIdCliente() == idCliente) {
                return tessera;
            }
        }
        return null;
    }

    /**
     * This method allows you to take all messages received
     * from a particular Costumer if exists, to be identified by its id.
     * @param idCliente the id about the Costumer.
     * @return a list of SMS received from Costumer.
     * @throws IllegalArgumentException if the idCliente is not correct.
     */
    public Set<SMS> getSMSCliente(int idCliente) {
        if(idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer.");
        for(Cliente cliente : this.dbms.getSMSCliente().keySet()){
            if(cliente.getIdCliente() == idCliente){
                return this.dbms.getSMSCliente().get(cliente);
            }
        }
        return null;
    }

    /**
     * This method allows you to take all the Visits
     * of a particular Customer if exists, to be identified
     * through his id.
     * @param idCliente the id about the Costumer.
     * @return a list of Visits about the Costumer.
     * @throws IllegalArgumentException if the idCliente is not correct.
     *
     */
    public Set<Visita> getVisiteCliente(int idCliente) {
        if(idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer.");
        for(Cliente cliente : this.dbms.getVisiteCliente().keySet()){
            if(cliente.getIdCliente() == idCliente){
                return this.dbms.getVisiteCliente().get(cliente);
            }
        }
        return null;
    }


    /**
     * This method allows you to take all the
     * Rewards received from a particular Customer if exists,
     * to be identified by his id.
     * @param idCliente the id about the Customer.
     * @return a list of rewards received froma  particular Customer.
     * @throws IllegalArgumentException if the idCliente is not correct.
     */
    public Set<Premio> getPremiCliente(int idCliente) {
        if(idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer.");
        for(Cliente cliente : this.dbms.getPremiCliente().keySet()){
            if(cliente.getIdCliente() == idCliente){
                return this.dbms.getPremiCliente().get(cliente);
            }
        }
        return null;
    }

    /**
     * This method allows you to take all the avaible
     * Coupons received from a specific Customer, if exists,
     * identified by id.
     * @param idCliente the id about the Customer.
     * @return a list of Coupons held by the Costumer, if any exists.
     * @throws IllegalArgumentException if the idCliente is not correct.
     */
    public Set<Coupon> getCouponCliente(int idCliente) {
        if(idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer");
        for(Cliente cliente : this.dbms.getCouponCliente().keySet()){
            if(cliente.getIdCliente() == idCliente){
                return this.dbms.getCouponCliente().get(cliente);
            }
        }
        return null;
    }

    /**
     * This method allows you to validate a Purchase of a
     * particular Customer registered on the Platform and on the
     * company's, current, loyalty program.
     * @param nome the name about the Customer.
     * @param cognome the surname about the Customer.
     * @throws IllegalArgumentException if the Name or Surname about Customer is not correct.
     */
    public void convalidaAcquisto(String nome, String cognome) {
        if(Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal Name or Surname for the Customer.");
        //Todo implementare
    }

    /**
     * This method allows you to validate a Purchase of a
     * particular Customer registered on the Platform and on the
     * company's, current, loyalty program.
     * @param idTessera the name about the Customer.
     * @throws IllegalArgumentException if the idTessera is not correct.
     */
    public void convalidaAcquisto(int idTessera) {
        if(idTessera <= 0)
            throw new IllegalArgumentException("Illegal number of Customer card.");
        //Todo implementare

    }
}
