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
     */
    public Cliente identificaClienteTessera(int idTessera) {
        return getClienteByCardId(idTessera);
    }

    /**
     * This method allows you to identify a Customer
     * who will be searched within the platform by id.
     *
     * @param idCliente the id about the Costumer to identify;
     * @return a Costumer object if exists, null otherwise.
     */
    public Cliente identificaClienteCodice(int idCliente) {
        return getClienteById(idCliente);
    }

    /**
     * This method allows you to take a Card of
     * a particular Costumer, to be identified through
     * his id.
     *
     * @param idCliente the id about the Costumer.
     * @return the Card if the Costumer has one, null otherwise.
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
     *
     * @param idCliente the id about the Costumer.
     * @return a list of SMS received from Costumer.
     */
    public Set<SMS> getSMSCliente(int idCliente) {
        Cliente cliente = getClienteById(idCliente);
        if (cliente != null) {
            if (!this.dbms.getSMSCliente().get(cliente).isEmpty())
                return this.dbms.getSMSCliente().get(cliente);
        }
        return null;
    }

    /**
     * This method allows you to take all the Visits
     * of a particular Customer if exists, to be identified
     * through his id.
     *
     * @param idCliente the id about the Costumer.
     * @return a list of Visits about the Costumer.
     */
    public Set<Visita> getVisiteCliente(int idCliente) {
        Cliente cliente = getClienteById(idCliente);
        if (cliente != null) {
            if (!this.dbms.getVisiteCliente().get(cliente).isEmpty())
                return this.dbms.getVisiteCliente().get(cliente);
        }
        return null;
    }


    /**
     * This method allows you to take all the
     * Rewards received from a particular Customer if exists,
     * to be identified by his id.
     *
     * @param idCliente the id about the Customer.
     * @return a list of rewards received froma  particular Customer.
     */
    public Set<Premio> getPremiCliente(int idCliente) {
        Cliente cliente = getClienteById(idCliente);
        if (cliente != null) {
            if (!this.dbms.getPremiCliente().get(cliente).isEmpty())
                return this.dbms.getPremiCliente().get(cliente);
        }
        return null;
    }

    /**
     * This method allows you to take all the avaible
     * Coupons received from a specific Customer, if exists,
     * identified by id.
     *
     * @param idCliente the id about the Customer.
     * @return a list of Coupons held by the Costumer, if any exists.
     */
    public Set<Coupon> getCouponCliente(int idCliente) {
        Cliente cliente = getClienteById(idCliente);
        if (cliente != null) {
            if (!this.dbms.getCouponCliente().get(cliente).isEmpty())
                return this.dbms.getCouponCliente().get(cliente);
        }
        return null;
    }

    /**
     * This method allows you to validate a Purchase of a
     * particular Customer registered on the Platform and on the
     * company's, current, loyalty program.
     *
     * @param nome    the name about the Customer.
     * @param cognome the surname about the Customer.
     * @throws IllegalArgumentException if the Name or Surname about Customer is not correct.
     */
    public int convalidaAcquisto(int idAzienda, String nome, String cognome, double importoSpeso, Coupon coupon) {
        double importo = 0;
        if (Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal Name or Surname for the Customer.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                Cliente identificato = this.identificaCliente(nome, cognome);
                Tessera tesseraCliente = this.getTesseraCliente(identificato.getIdCliente());
                if (tesseraCliente != null) {
                    if (coupon != null) {
                        if (importoSpeso >= coupon.getValoreSconto()) {
                            importo = importoSpeso - coupon.getValoreSconto();

                            //Todo richiama HandlerTessera passando l'importo.
                            //Todo richiama HandlerPremi per togliere il Coupon del Cliente al DB.
                            return 1;
                        } else {
                            importo = importoSpeso;
                            //Todo richiama HandlerTessera passando l'importo.
                            //Todo richiama HandlerPremi per togliere il Coupon del Cliente al DB.
                            return 1;
                        }
                    } else {
                        importo = importoSpeso;
                        //Todo richiama HandlerTessera passando l'importo.
                        //Todo richiama HandlerPremi per togliere il Coupon del Cliente al DB.
                        return 1;
                    }
                }
                return 0;
            }
        }
        return -1;
    }

    /**
     * This method allows you to validate a Purchase of a
     * particular Customer registered on the Platform and on the
     * company's, current, loyalty program.
     *
     * @param idTessera the name about the Customer.
     * @throws IllegalArgumentException if the idTessera is not correct.
     */
    public void convalidaAcquisto(int idAzienda, int idTessera, double importoSpeso, Coupon coupon) {
        if (idTessera <= 0)
            throw new IllegalArgumentException("Illegal number of Customer card.");
        for (Tessera tessera : this.dbms.getTessereClienti()) {
            if (tessera.getIdTessera() == idTessera) {
                for (Cliente cliente : this.dbms.getClientiIscritti()) {
                    if (tessera.getIdCliente() == cliente.getIdCliente()) {
                        convalidaAcquisto(idAzienda, cliente.getNome(), cliente.getCognome(), importoSpeso, coupon);
                    }
                }
            }
        }
    }

    /**
     * This private method allows to simplify the code,
     * and avoid duplicate code. It has the purpose of contacting the
     * DataBase to identify a certain Client from its unique ID.
     * If the customer is identified, the Customer is returned, otherwise a null value is returned.
     *
     * @param idCliente the id unique for the Customer to identify.
     * @return the Customer if exist and if it registered into the platform, null value otherwise.
     */
    private Cliente getClienteById(int idCliente) {
        if (idCliente <= 0)
            throw new IllegalArgumentException("Illegal id for the Customer.");
        for (Cliente cliente : this.dbms.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente)
                return cliente;
        }
        return null;
    }

    /**
     * This private method allows to simplify the code,
     * and avoid duplicate code. It has the purpose of contacting the
     * DataBase to identify a certain Client from its unique id Card, if exist and if it possesses.
     * If the customer is identified, the Customer is returned, otherwise a null value is returned.
     *
     * @param idTessera the id unique for the Customer to identify.
     * @return the Customer if exist and if it registered into the platform, null value otherwise.
     */
    private Cliente getClienteByCardId(int idTessera) {
        if (idTessera <= 0)
            throw new IllegalArgumentException("Illegal id Card for identify the Customer.");
        for (Tessera tessera : this.dbms.getTessereClienti()) {
            if (tessera.getIdTessera() == idTessera) {
                for (Cliente cliente : this.dbms.getClientiIscritti()) {
                    if (tessera.getIdCliente() == cliente.getIdCliente())
                        return cliente;
                }
            }
        }
        return null;
    }
}