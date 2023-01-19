package loyality_platform_model.Activity;

import loyality_platform_model.Handler.HandlerCliente;
import loyality_platform_model.Handler.HandlerCreazioneTessera;
import loyality_platform_model.Handler.HandlerMessaggi;
import loyality_platform_model.Handler.HandlerPunti;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents the activities that an Employee is able to perform.
 * Indeed, within it, we find a whole series of methods that allow a particular
 * Employee to perform.
 */
public class AttivitaDipendente {

    /**
     * This attribute represents the object that allows you to
     * perform operations in a profile of a specific identified customer.
     */
    private AttivitaProfiloCliente attivitaProfiloCliente;

    public AttivitaDipendente() {
        this.attivitaProfiloCliente = null;
    }

    /**
     * This method allows you to create a card for a customer who does not yet have one.
     *
     * @param gestoreCreazioneTessera the class Manager responsible for creating cards for Costumers.
     * @param cliente                 the Costumers who requested the Card.
     * @throws NullPointerException if the <cliente> or <gestoreCreazioneTessera> is null.
     */
    public void creaTessera(HandlerCreazioneTessera gestoreCreazioneTessera, Cliente cliente) {
        Objects.requireNonNull(gestoreCreazioneTessera);
        Objects.requireNonNull(cliente);
        gestoreCreazioneTessera.creaTessera(cliente);
    }

    /**
     * This method allows you to validate a purchase.
     */
    public void convalidaAcquisto() {
        //Todo implementare, ancora da fare su Visual Paradigm.
    }

    /**
     * This method allows a Client to be identified on the platform.
     * In doing so, through if it is possible to obtain information or perform certain
     * actions within its profile.
     *
     * @param gestoreCliente the class that allows to identify the Costumer and get the information.
     * @param nome           the Name of the Costumer we want to identify.
     * @param cognome        the Surname of the Costumer we want to identify.
     * @throws NullPointerException if the <gestoreCliente> is null.
     */
    public void identificaCliente(HandlerCliente gestoreCliente, String nome, String cognome) {
        Objects.requireNonNull(gestoreCliente);
        this.attivitaProfiloCliente = gestoreCliente.identificaCliente(nome, cognome);
    }

    /**
     * This method allows you to send a specific SMS to customers,
     * obviously it is not your responsibility, in fact the Message Manager will carry out the action.
     *
     * @param gestoreMessaggi the class that is responsible for sending Messages to Costumers.
     * @param clienti         the List of the Costumers to whom we want to send the message.
     * @param sms             the message to send.
     * @throws NullPointerException if the <gestoreMessaggi> or <clienti> or <sms> are null.
     */
    public void inviaSMSGenerali(HandlerMessaggi gestoreMessaggi, List<Cliente> clienti, SMS sms) {
        Objects.requireNonNull(gestoreMessaggi);
        Objects.requireNonNull(clienti);
        Objects.requireNonNull(sms);
        //Todo implementare, manca HandlerMessaggi
    }

    /**
     * This method allows you to add points to a customer list by passing a number of points.
     *
     * @param gestorePunti the class that is responsible for sending points to Costumers card.
     * @param clienti      the List of Costumers to whom we wanto to send points.
     * @param numeroPunti  the number of points to add.
     * @throws NullPointerException     if <gestorePunti> or <clienti> is null.
     * @throws IllegalArgumentException if the number of points are less thant or equal to 0.
     */
    public void aggiungiPuntiGenerali(HandlerPunti gestorePunti, List<Cliente> clienti, int numeroPunti) {
        Objects.requireNonNull(gestorePunti);
        Objects.requireNonNull(clienti);
        if (numeroPunti <= 0)
            throw new IllegalArgumentException("Illegal number of points.");
        gestorePunti.aggiungiPuntiGenerale(clienti, numeroPunti);
    }
}
