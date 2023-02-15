package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.SMS;
import loyality_platform_model.Models.Visita;

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

    public DBMS getDbms() {
        return dbms;
    }
    /**
     * method that returns the details of the visit
     */
    public String getDetailsVisita(int idCliente,int idVisita){
        if (idVisita < 1)
            throw new IllegalArgumentException("Id not correct");
        for(Cliente cliente : this.getDbms().getClientiIscritti()){
            if(cliente.getIdCliente()==idCliente){
            for (Visita visita : this.getDbms().getVisiteCliente().get(cliente)) {
                if (visita.getIdVisita() == idVisita) {
                    return visita.toString();

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

    public Visita creaVisita(String luogo, String data) {
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

    public boolean aggiungiVisita(int idCliente, String data, String luogo) {
       if(Objects.equals(luogo, "") || (Objects.equals(data,"")))
           throw new IllegalArgumentException("place or data are empty");
        if  (idCliente < 1)
            throw new IllegalArgumentException("Id not correct");
                Visita visita = creaVisita(luogo, data);
                this.dbms.addVisita(cliente, visita);
                return true;
    }

    public void aggiungiVisitaGenerale(Set<Cliente> clienti, String data, String luogo){
        if(Objects.equals(luogo, "") || (Objects.equals(data,"")))
            throw new IllegalArgumentException("place or data are empty");
        if  (idCliente < 1)
            throw new IllegalArgumentException("Id not correct");
        for(Cliente cliente: clienti) {
            Visita visita = creaVisita(luogo, data);
            aggiungiVisita(cliente.getIdCliente(),visita);
        }
    }

    /**
     * Method that allows you to modify a specific visit for a specific Costumers.
     * @param luogo   the new Place of the Visit.
     * @param data    the new Date of the Visit.
     * @throws NullPointerException     if the <cliente> or <visita> or <data> is null.
     * @throws IllegalArgumentException if the location or time is incorrect or the visit don't exist.
     *                                  <<<<<<< HEAD
     */
    public void modificaVisita(int idCliente, int idVisita,String data, String luogo,boolean completata) {
        if((Objects.equals(luogo, "") || (Objects.equals(data,""))))
            throw new IllegalArgumentException("place or data are empty");
        if (idCliente < 1 || idVisita < 1)
            throw new IllegalArgumentException("Custormer id or visit id are not correct");
        Visita visitaUpdated=new Visita(luogo,data);
        visitaUpdated.setCompletata(completata);
        this.dbms.updateVisita(cliente.getIdCliente(), idVisita, visitaUpdated);

    }

    /**
     * Method that allows you to remove a specific Visit for a specific Costumers.
     * @throws NullPointerException     if the <cliente> or <visita> is null.
     * @throws IllegalArgumentException if the Costumers don't have this Visit.
     */

    public void rimuoviVisita(int idVisita, int idCliente) {
        if (idCliente < 1 || idVisita<1)
            throw new IllegalArgumentException("Customer Id or visit id are not correct");
        this.dbms.removeVisita(idCliente, visita);

    }

    public void rimuoviVisitaGenerale() {
        //TODO implementare
    }
}