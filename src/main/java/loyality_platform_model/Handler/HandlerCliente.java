package loyality_platform_model.Handler;

import loyality_platform_model.Activity.AttivitaProfiloCliente;
import loyality_platform_model.DBController.DBMSController;
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
    private final DBMSController dbmsController;

    /**
     * Once a Client is identified, he is associated
     * with this temporary variable, able to access his information.
     */
    private Cliente clienteIdentificato;

    /**
     * Costructor who creates a Client manager.
     * Recall the Db instance to retrieve permanent data.
     */
    public HandlerCliente() {
        this.dbmsController = DBMSController.getInstance();
    }

    /**
     * Method that tries to identify the customer,
     * passing name and surname, if the customer is found,
     * it is identified, and we will associate it with a temporary variable,
     * to carry out our operations.
     * @param nome the Name of the Costumer.
     * @param cognome the Surname of the Costumer.
     * @throws IllegalArgumentException if the Name or Surname is not valid or if the Costumer
     * with this Name and Surname is already identified.
     */
    public AttivitaProfiloCliente identificaCliente(String nome, String cognome) {
        AttivitaProfiloCliente activity = null;
        if (Objects.equals(nome, "") || Objects.equals(cognome, ""))
            throw new IllegalArgumentException("Illegal Name or Surname to identify the Customer.");
        for (Cliente cliente : this.dbmsController.getClientiIscritti()) {
            if (Objects.equals(cliente.getNome(), nome) && Objects.equals(cliente.getCognome(), cognome)) {
                if (cliente.isIdentificato())
                    throw new IllegalArgumentException("Costumer already identify.");
            } else{
                this.clienteIdentificato = cliente;
                this.clienteIdentificato.setIdentificato(true);
                cliente.setIdentificato(true);
                activity = new AttivitaProfiloCliente(cliente);
            }
        }
        if(activity == null){
            throw new IllegalArgumentException("Costumer is not identify.");
        }
        return activity;
    }

    /**
     * Method that returns the card of an identified Customer, if he has it.
     * @return the Caard of an identified Costumer.
     * @throws IllegalArgumentException if the Costumer where you are trying to get the information
     * is not identified.
     */
    public Tessera getTesseraCliente() {
        if (!isIdentificato(this.clienteIdentificato))
            throw new IllegalArgumentException("Costumer is not identified.");
        return this.clienteIdentificato.getTessera();
    }

    /**
     * Method that returns the list of loyalty Programs
     * to which a particular identified customer is subscribed.
     * @return the Set of Loyality Programs.
     * @throws IllegalArgumentException if the Costumer where you are trying to get the information
     * is not identified.
     */
    public Set<ProgrammaFedelta> getProgrammiFedeltaCliente() {
        if (!isIdentificato(this.clienteIdentificato))
            throw new IllegalArgumentException("Costumer is not identified.");
        return this.clienteIdentificato.getTessera().getProgrammiFedelta();
    }

    /**
     * Method that returns the list of Visits made by a
     * particular identified Customer.
     * @return the Set of Visits from identified Costumer.
     * @throws IllegalArgumentException If the customer where you are trying to get the
     *                                  information is not identified.
     */
    public Set<Visita> getVisite() {
        if (!isIdentificato(this.clienteIdentificato))
            throw new IllegalArgumentException("Costumer is not identified.");
        return this.clienteIdentificato.getVisite();
    }

    /**
     * Method that returns the list of SMS received from a
     * particular identified Customer.
     *
     * @return the Set of SMS received from identified Costumer.
     * @throws IllegalArgumentException If the customer where you are trying to get the
     *                                  information is not identified.
     */
    public Set<SMS> getSMSCliente() {
        if (!isIdentificato(this.clienteIdentificato))
            throw new IllegalArgumentException("Costumer is not identified.");
        return this.clienteIdentificato.getSmsCliente();
    }

    /**
     * Method that returns all the details of a particular
     * identified Customer.
     *
     * @return details of the identified Costumer.
     * @throws IllegalArgumentException If the customer where you are trying to get the
     *                                  information is not identified.
     */
    public String getDetailsCliente() {
        if (!isIdentificato(this.clienteIdentificato))
            throw new IllegalArgumentException("Costumer is not identified.");
        return this.clienteIdentificato.toString();
    }

    /**
     * Method that will exit to the Costumer's profile.
     * So the identified Costumer's temporary variable
     * will return to null, and the Client is no longer
     * identified.
     */
    public void esciProfiloCliente() {
        for (Cliente cliente : this.dbmsController.getClientiIscritti()) {
            if (cliente == this.clienteIdentificato) {
                cliente.setIdentificato(false);
            }
        }
        this.clienteIdentificato.setIdentificato(false);
        this.clienteIdentificato = null;
    }

    /**
     * Private method that avoids code duplication,
     * able to return true if the client is still identified,
     * false otherwise.
     *
     * @param cliente The Costumer to check its status.
     * @return true if the Costumer is identified, false otherwise.
     */
    private boolean isIdentificato(Cliente cliente) {
        return cliente.isIdentificato();
    }
}
