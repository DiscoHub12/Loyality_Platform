package loyality_platform_model.Activity;

import loyality_platform_model.Handler.HandlerMessaggi;
import loyality_platform_model.Handler.HandlerPunti;
import loyality_platform_model.Handler.HandlerVisite;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;
import loyality_platform_model.Models.Visita;

import java.util.Date;
import java.util.Objects;

/**
 * IMPLEMENTED BY : Alessio Giacché.
 */


/**
 * Class representing the activities that an Employee is able to perform
 * within a profile of a particular identified Customer.
 * In fact, within it, we find a whole series of methods that allow a particular
 * Employee to carry out certain actions on an identified Customer.
 */
public class AttivitaProfiloCliente {

    /**
     * This attribute (temporary) represents the identified Customer.
     * In fact, all the operations that we are going to carry out (operations in your profile)
     * are done precisely on an identified Client.
     */
    public Cliente clienteIdentificato;

    /**
     * Constructor that allows you to create this class capable
     * of carrying out certain actions on an identified Client.
     *
     * @param clienteIdentificato the identified Costumer.
     * @throws NullPointerException if the Costumer is null.
     */
    public AttivitaProfiloCliente(Cliente clienteIdentificato) {
        Objects.requireNonNull(clienteIdentificato);
        this.clienteIdentificato = clienteIdentificato;
    }

    /**
     * Method which allows a particular number of points to be added to the identified Client.
     * Obviously the Customer must be registered on the platform, have a Fidelity Card
     * and be registered in at least 1 Loyalty Programme.
     *
     * @param gestorePunti class in charge of managing Costumers points.
     * @param numeroPunti  the number of points to add.
     * @throws NullPointerException if the <gestorePunti> is null.
     */
    public void aggiungiPunti(HandlerPunti gestorePunti, int numeroPunti) {
        Objects.requireNonNull(gestorePunti);
        gestorePunti.aggiungiPunti(this.clienteIdentificato, numeroPunti);
    }

    /**
     * Method that allows you to remove a particular number of points
     * from the identified Customer.
     * Obviously the Customer must be registered on the platform,
     * have a Fidelity Card and be registered in at least 1 Loyalty Programme.
     *
     * @param gestorePunti class in charge of managing Costumers points.
     * @param numeroPunti  the number of points to remove.
     * @throws NullPointerException if the <gestorePunti> is null.
     */
    public void rimuoviPunti(HandlerPunti gestorePunti, int numeroPunti) {
        Objects.requireNonNull(gestorePunti);
        gestorePunti.rimuoviPunti(this.clienteIdentificato, numeroPunti);
    }

    /**
     * Method that allows you to add a specific discount code
     * to a particular identified customer.
     */
    public void aggiungiCodiceSconto() {
        //Todo, ancora non visto su VP.
    }

    /**
     * Method that allows a specific SMS to be sent to the identified Customer.
     * Obviously we will need the handler that takes care of the messages.
     *
     * @param gestoreMessaggi class in charge of managing Costumers messages.
     * @param sms             the message.
     * @throws NullPointerException if the <gestoreMessaggi> or <sms> are null.
     */
    public void inviaSMS(HandlerMessaggi gestoreMessaggi, SMS sms) {
        Objects.requireNonNull(gestoreMessaggi);
        Objects.requireNonNull(sms);
        //Todo implementare, manca HandlerMessaggi
    }

    /**
     * Method that allows you to add a particular visit to the Customer's profile.
     * Obviously we will need the manager who takes care of the visits.
     *
     * @param gestoreVisite class in charge of managing Costumers visits.
     * @param visita        the visits.
     * @throws NullPointerException if the <gestoreVisite> or <visita> are null.
     */
    public void aggiungiVisita(HandlerVisite gestoreVisite, Visita visita) {
        Objects.requireNonNull(gestoreVisite);
        Objects.requireNonNull(visita);
        gestoreVisite.aggiungiVisita(this.clienteIdentificato, visita);
    }

    /**
     * Method that allows you to modify a particular visit within the Customer's profile.
     * Obviously we will need the manager who takes care of the visits.
     *
     * @param gestoreVisite class in charge of managing Costumers visits.
     * @param visita        the visits.
     * @throws NullPointerException if the <gestoreVisite> or <visita> or <data> are null.
     */
    public void modificaVisita(HandlerVisite gestoreVisite, Visita visita, String luogo, Date data, String ora) {
        Objects.requireNonNull(gestoreVisite);
        Objects.requireNonNull(visita);
        Objects.requireNonNull(data);
        gestoreVisite.modificaVisita(this.clienteIdentificato, visita, luogo, data, ora);
    }

    /**
     * Method that allows you to remove a particular visit within the customer's profile if it exists.
     * Obviously we will need the manager who takes care of the visits.
     *
     * @param gestoreVisite class in charge of managing Costumers visits.
     * @param visita        the visits.
     * @throws NullPointerException if the <gestoreVisite> or <visita> are null.
     */
    public void rimuoviVisita(HandlerVisite gestoreVisite, Visita visita) {
        Objects.requireNonNull(gestoreVisite);
        Objects.requireNonNull(visita);
        gestoreVisite.rimuoviVisita(this.clienteIdentificato, visita);
    }
}
