package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

import java.util.Objects;
import java.util.Set;


/**
 * Class that represent a Costumer manager capable
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
     * Constructor who creates a Client manager.
     * Recall the Db instance to retrieve permanent data.
     */
    public HandlerCliente() {
        this.dbms = DBMS.getInstance();
    }

    /**
     * This method allow to create a new Customer account into the
     * Platform.
     *
     * @param nome     the name of the Customer.
     * @param cognome  the surname of the Customer.
     * @param telefono the telephone number of the Customer.
     * @param email    the email of the Customer.
     * @param password the password of the Customer.
     * @return true if the Customer is created successfully, false otherwise.
     * @throws IllegalArgumentException if the filed is not valids.
     */
    public boolean addCliente(String nome, String cognome, String telefono, String email, String password) {
        if (Objects.equals(nome, "") || Objects.equals(cognome, "") || Objects.equals(telefono, "") || Objects.equals(email, "") || Objects.equals(password, "")) {
            throw new IllegalArgumentException("Invalid Fields. The field can't be empty.");
        }
        for (Cliente cliente : this.dbms.getClientiIscritti()) {
            if (Objects.equals(cliente.getNome(), nome) && Objects.equals(cliente.getCognome(), cognome) && Objects.equals(cliente.getEmail(), email))
                return false;
        }
        Cliente newCustomer = new Cliente(nome, cognome, telefono, email, password);
        return this.dbms.addCliente(newCustomer);

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
        return this.dbms.identificaCliente(nome, cognome);
    }

    /**
     * This method allows you to identify a Customer
     * who will be searched within the platform by the card code if he has
     * one.
     *
     * @param idTessera the card code about the Costumer to identify.
     * @return a Costumer object if exists, null otherwise.
     * @throws IllegalArgumentException if the idTessera is not valid.
     */
    public Cliente identificaClienteTessera(int idTessera) {
        if (idTessera <= 0)
            throw new IllegalArgumentException("Invalid id for the Card.");
        return this.dbms.identificaClienteTessera(idTessera);
    }

    /**
     * This method allows you to identify a Customer
     * who will be searched within the platform by id.
     *
     * @param idCliente the id about the Costumer to identify;
     * @return a Costumer object if exists, null otherwise.
     * @throws IllegalArgumentException if the idCliente is not valid.
     */
    public Cliente identificaClienteCodice(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Invalid id for the Customer.");
        return this.dbms.identificaClienteCodice(idCliente);
    }

    /**
     * This method allows you to take a Card of
     * a particular Costumer, to be identified through
     * his id.
     *
     * @param idCliente the id about the Costumer.
     * @return the Card if the Costumer has one, null otherwise.
     * @throws IllegalArgumentException if the idCliente is not valid.
     */
    public Tessera getTesseraCliente(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer.");
        return this.dbms.getTesseraCliente(idCliente);
    }

    /**
     * This method allows you to take all messages received
     * from a particular Costumer if exists, to be identified by its id.
     *
     * @param idCliente the id about the Costumer.
     * @return a Set of SMS received from Costumer.
     * @throws IllegalArgumentException if the id for Customer is not valid.
     */
    public Set<SMS> getSMSCliente(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Invalid id for the Customer.");
        return this.dbms.getSMSClienteById(idCliente);
    }

    /**
     * This method allows you to take all the Visits
     * of a particular Customer if exists, to be identified
     * through his id.
     *
     * @param idCliente the id about the Costumer.
     * @return a Set of Visits about the Costumer.
     * @throws IllegalArgumentException if the idCliente is not valid.
     */
    public Set<Visita> getVisiteCliente(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Invalid id for the Customer.");
        return this.dbms.getVisiteClienteById(idCliente);
    }


    /**
     * This method allows you to take all the
     * Rewards received from a particular Customer if exists,
     * to be identified by his id.
     *
     * @param idCliente the id about the Customer.
     * @return a Set of rewards received froma  particular Customer.
     * @throws IllegalArgumentException if the idCliente is not valid.
     */
    public Set<Premio> getPremiCliente(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Invalid id for the Customer.");
        return this.dbms.getPremiClienteById(idCliente);
    }

    /**
     * This method allows you to take all the avaible
     * Coupons received from a specific Customer, if exists,
     * identified by id.
     *
     * @param idCliente the id about the Customer.
     * @return a Set of Coupons held by the Costumer, if any exists.
     * @throws IllegalArgumentException if the idCliente is not valid.
     */
    public Set<Coupon> getCouponCliente(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Invalid id for the Customer.");
        return this.dbms.getCouponClienteById(idCliente);
    }

    /**
     * This method allows you to validate a Purchase of a
     * particular Customer registered on the Platform and on the
     * company's, current, loyalty program.
     *
     * @param idAzienda      the name about the Customer.
     * @param idTessera      the surname about the Customer.
     * @param importoSpeso   the amount spent
     * @param coupon         the Coupon, if the Customer has one.
     * @param gestoreTessera the handler to add the points to the Customer's Card.
     * @param gestorePremi   the handler reward that allows to manage the Customer's Coupons and other.
     * @throws IllegalArgumentException if the idTessera is not valid.
     */
    public void convalidaAcquisto(int idAzienda, int idTessera, double importoSpeso, Coupon coupon, HandlerTessera gestoreTessera, HandlerPremi gestorePremi) {
        if (idTessera <= 0)
            throw new IllegalArgumentException("Illegal number of Customer card.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Tessera tessera : this.dbms.getTessereClienti()) {
                    if (tessera.getIdTessera() == idTessera) {
                        for (Cliente cliente : this.dbms.getClientiIscritti()) {
                            if (tessera.getIdCliente() == cliente.getIdCliente()) {
                                if (coupon == null) {
                                    gestoreTessera.addPuntiAcquisto(importoSpeso, tessera, azienda);
                                } else {
                                    if (importoSpeso >= coupon.getValoreSconto()) {
                                        importoSpeso -= coupon.getValoreSconto();
                                        gestorePremi.deleteCouponCliente(cliente.getIdCliente(), coupon.getIdCoupon());
                                        gestoreTessera.addPuntiAcquisto(importoSpeso, tessera, azienda);
                                    } else gestoreTessera.addPuntiAcquisto(importoSpeso, tessera, azienda);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}