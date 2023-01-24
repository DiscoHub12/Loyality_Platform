package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;
import loyality_platform_model.Models.Visita;

import java.util.Date;
import java.util.Objects;

/**
 * IMPLEMENTED BY : Alessio Giacché.
 */


/**
 * Class that represents a customer visit manager.
 * Able to perform certain functions, such as creating a visit,
 * associating a visit to a particular customer and so on.
 */
public class HandlerVisite {

    /**
     * This attribute rapresent the database.
     */
    public DBMSController dbmsController;

    /**
     * Constructor that allows you to create a HandlerVisite
     * object taking the instance of the DataBase for data persistence.
     */
    public HandlerVisite() {
        this.dbmsController = DBMSController.getInstance();
    }

    /**
     * Method that allows you to create a visit by passing the place,
     * date and time as parameters.
     *
     * @param luogo the Place of the Visit.
     * @param data  the Date of the Visit.
     * @param ora   the time (hour) of the visit.
     * @return the new Visit created.
     * @throws NullPointerException     if the <data> is null.
     * @throws IllegalArgumentException if the location or time is incorrect.
     */
    public Visita creaVisita(String luogo, Date data, String ora) {
        Objects.requireNonNull(data);
        if (Objects.equals(luogo, "") || Objects.equals(ora, ""))
            throw new IllegalArgumentException("Illegal place or hour for the Visit.");
        return new Visita(luogo, data, ora);
    }

    /**
     * Method that allows you to add a visit to a particular Customer (registered).
     *
     * @param cliente the Costumer (registrated)
     * @param visita  the Visit.
     * @throws NullPointerException     if the <cliente> or <visita> is null.
     * @throws IllegalArgumentException if the Visit is already present into the Costumer's profile.
     */
    public void aggiungiVisita(Cliente cliente, Visita visita) {
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(visita);
        for (Visita visitaCliente : cliente.getVisite()) {
            if (visitaCliente.equals(visita))
                throw new IllegalArgumentException("Visit already presents in Costumers profile.");
        }
        cliente.addVisita(visita);
    }

    /**
     * Method that allows you to modify a specific visit for a specific Costumers.
     *
     * @param cliente the Costumers to modify the visit.
     * @param visita  the Visit to modify.
     * @param luogo   the new Place of the Visit.
     * @param data    the new Date of the Visit.
     * @param ora     the new hour (time) of the Visit.
     * @throws NullPointerException     if the <cliente> or <visita> or <data> is null.
     * @throws IllegalArgumentException if the location or time is incorrect or the visit don't exist.
     */
    public void modificaVisita(Cliente cliente, Visita visita, String luogo, Date data, String ora) {
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(visita);
        Objects.requireNonNull(data);
        if (Objects.equals(luogo, "") || Objects.equals(ora, ""))
            throw new IllegalArgumentException("Illegal place or hour for the Visit.");
        for (Visita visitaCliente : cliente.getVisite()) {
            if (visitaCliente.equals(visita)) {
                visitaCliente.setLuogo(luogo);
                visitaCliente.setOra(ora);
                visitaCliente.setData(data);
            }
            //Todo testare.
        }
    }

    /**
     * Method that allows you to remove a specific Visit for a specific Costumers.
     *
     * @param cliente the Costumers.
     * @param visita  the Visit to remove.
     * @throws NullPointerException     if the <cliente> or <visita> is null.
     * @throws IllegalArgumentException if the Costumers don't have this Visit.
     */
    public void rimuoviVisita(Cliente cliente, Visita visita) {
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(visita);
        if (cliente.getVisite().contains(visita)) {
            cliente.removeVisita(visita);
        } else throw new IllegalArgumentException("Visit don't exist.");
    }

    /**
     * Method that allow to send a particular SMS to a specific Costumer.
     *
     * @param gestoreMessaggi the class that is responsible for sending SMS to a Costumers.
     * @param cliente         the Costumer to send the SMS.
     * @param sms             the SMS.
     * @throws NullPointerException if the <gestoreMessaggi> or <cliente> or <sms > is null.
     */
    public void inviaSMS(HandlerMessaggi gestoreMessaggi, Cliente cliente, SMS sms) {
        Objects.requireNonNull(gestoreMessaggi);
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(sms);
        gestoreMessaggi.inviaSMS(sms, cliente);
    }

}
