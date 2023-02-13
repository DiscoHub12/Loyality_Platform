package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;
import loyality_platform_model.Models.Visita;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents a customer visit manager.
 * Able to perform certain functions, such as creating a visit,
 * associating a visit to a particular customer and so on.
 */
public class HandlerVisite {

    /**
     * This attribute rapresent the database.
     */
    public DBMS dbms;

    /**
     * Constructor that allows you to create a HandlerVisite
     * object taking the instance of the DataBase for data persistence.
     */
    public HandlerVisite() {
        this.dbms = DBMS.getInstance();
    }

    /**
     * method that returns the details of the visit
     */
    public void getDetailsVisita() {
        //TODO implemntare
    }

    public DBMS getDbms() {
        return dbms;
    }
    /**
     * method that returns the details of the visit
     */
    public String getDetailsVisita(int idVisita){
        if (idVisita < 1)
            throw new IllegalArgumentException("Id not correct");
        for (Visita visita : this.getDbms().getVisite()) {
            if (visita.getIdVisita() == idVisita) {
                return visita.toString();
            }
            throw new IllegalArgumentException("visit not exists.");
        }
        return null;
    }

    /**
     * Method that allows you to create a visit by passing the place,
     * date and time as parameters.
     *
     * @param luogo the Place of the Visit.
     * @param data  the Date of the Visit.
     * @return the new Visit created.
     * @throws NullPointerException     if the <data> is null.
     * @throws IllegalArgumentException if the location or time is incorrect.
     */

    public Visita creaVisita(String luogo, Date data) {
        Objects.requireNonNull(data);
        if (Objects.equals(luogo, ""))
            throw new IllegalArgumentException("Illegal place or hour for the Visit.");
        return new Visita(luogo, data);
    }

    /**
     * Method that allows you to add a visit to a particular Customer (registered).
     *
     * @param idCliente the Costumer (registrated)
     *
     * @throws NullPointerException     if the <cliente> or <visita> is null.
     * @throws IllegalArgumentException if the Visit is already present into the Costumer's profile.
     */

    public void aggiungiVisita(int idCliente, Date data, String luogo) {
        Objects.requireNonNull(data);
        if (Objects.equals(luogo, "") || idCliente < 1)
            throw new IllegalArgumentException("Id or place are not correct");
        for(Cliente cliente : this.getDbms().getClientiIscritti()){
            if(cliente.getIdCliente()==idCliente){
                Visita visita = creaVisita(luogo, data);
                for (Visita visitaCliente : this.getDbms().getVisiteCliente().get(cliente)) {
                    if (visitaCliente.equals(visita))
                        throw new IllegalArgumentException("Visit already presents in Costumers profile.");
                    else
                        this.dbms.addVisita(cliente, visita);
                }
            }
        }
    }

    public void aggiungiVisitaGenerale(Set<Integer> idClienti, Date data, String luogo){
        //TODO implementare
    }

    /**
     * Method that allows you to modify a specific visit for a specific Costumers.
     * @param luogo   the new Place of the Visit.
     * @param data    the new Date of the Visit.
     * @throws NullPointerException     if the <cliente> or <visita> or <data> is null.
     * @throws IllegalArgumentException if the location or time is incorrect or the visit don't exist.
     *                                  <<<<<<< HEAD
     */
    public void modificaVisita(int idCliente, int idVisita,Date data, String luogo) {
        Objects.requireNonNull(data);
        if (Objects.equals(luogo, "") || idCliente < 1 || idVisita < 1)
            throw new IllegalArgumentException("Custormer id, visit id or place are not correct");
        for (Cliente cliente : this.getDbms().getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.getDbms().getVisiteCliente().get(cliente)) {
                    if(visita.getIdVisita()==idVisita)
                        this.dbms.updateVisita(idCliente, idVisita, luogo, data);
                    else
                        throw new IllegalArgumentException("visit don't exist.");
                }
            }
        }
    }

    /**
     * Method that allows you to remove a specific Visit for a specific Costumers.
     *
     * @param visita  the Visit to remove.
     * @throws NullPointerException     if the <cliente> or <visita> is null.
     * @throws IllegalArgumentException if the Costumers don't have this Visit.
     */

    public void rimuoviVisita(int idCliente, Visita visita) {
        Objects.requireNonNull(visita);
        if (idCliente < 1)
            throw new IllegalArgumentException("Customer Id not correct");
        for (Cliente cliente : this.getDbms().getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita1 : this.dbms.getVisiteCliente().get(cliente)) {
                    if (visita.equals(visita1)) {
                        this.dbms.removeVisita(visita, idCliente);
                    }
                }
            }
        }
    }

    public void rimuoviVisitaGenerale() {
        //TODO implementare
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
        //gestoreMessaggi.inviaSMS(sms, cliente);
    }

}