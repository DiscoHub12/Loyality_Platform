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
    public Set<Visita> getVisiteCliente(int idCliente){
        if(idCliente<1)
            throw new IllegalArgumentException("Illegal id");
        return this.dbms.getVisiteClienteById(idCliente);
    }
    public Visita getVisitaById(int idCliente, int idVisita) {
        if (idCliente <= 0 || idVisita <= 0)
            throw new IllegalArgumentException("Invalid id for Customer or Visit.");
        return this.dbms.getVisitaById(idCliente, idVisita);
    }
    /**
     * method that returns the details of the visit
     */
    public String getDetailsVisita(int idCliente,int idVisita) {
        if (idVisita < 1)
            throw new IllegalArgumentException("Id not correct");
        for (Cliente cliente : this.getDbms().getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.getDbms().getVisiteCliente().get(cliente)) {
                    if (visita.getIdVisita() == idVisita) {
                        return visita.toString();
                    }
                }
            }
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

    public Visita creaVisita(String luogo, String data) {
        if (Objects.equals(luogo, "") || Objects.equals(data,""))
            throw new IllegalArgumentException("Illegal place or data for the Visit.");
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
        Visita visitaNew = creaVisita(luogo, data);
        return this.dbms.addVisita(idCliente, visitaNew);
    }

    public boolean aggiungiVisitaGenerale(Set<Cliente> clienti, String data, String luogo){
        if(Objects.equals(luogo, "") || (Objects.equals(data,"")))
            throw new IllegalArgumentException("place or data are empty");
        boolean tutteVisiteAggiunte=true;
        Visita visita = creaVisita(luogo, data);
        for(int i=1; i<=clienti.size();i++){
            boolean visitaAggiunta=false;
            for(Cliente cliente : clienti){
                if(cliente.getIdCliente() == i){
                    dbms.addVisita(cliente.getIdCliente(),visita);
                    visitaAggiunta = true;
                    break;
                }
            }
            if(!visitaAggiunta){
                tutteVisiteAggiunte=false;
            }
        }
        return tutteVisiteAggiunte;
    }

    /**
     * Method that allows you to modify a specific visit for a specific Costumers.
     * @param luogo   the new Place of the Visit.
     * @param data    the new Date of the Visit.
     * @throws NullPointerException     if the <cliente> or <visita> or <data> is null.
     * @throws IllegalArgumentException if the location or time is incorrect or the visit don't exist.
     *                                  <<<<<<< HEAD
     */
    public boolean modificaVisita(int idCliente, int idVisita,String data, String luogo,boolean completata) {
        if((Objects.equals(luogo, "") || (Objects.equals(data,""))))
            throw new IllegalArgumentException("place or data are empty");
        if (idCliente < 1 || idVisita < 1)
            throw new IllegalArgumentException("Custormer id or visit id are not correct");
        Visita visitaUpdated=new Visita(luogo,data);
        visitaUpdated.setCompletata(completata);
        this.dbms.updateVisita(idCliente, idVisita, visitaUpdated);
        return true;
    }

    /**
     * Method that allows you to remove a specific Visit for a specific Costumers.
     * @throws NullPointerException     if the <cliente> or <visita> is null.
     * @throws IllegalArgumentException if the Costumers don't have this Visit.
     */

    public boolean rimuoviVisita(int idCliente, int idVisita) {
        if (idCliente < 1 || idVisita<1)
            throw new IllegalArgumentException("Customer Id or visit id are not correct");
        return this.dbms.removeVisita(idCliente, idVisita);
    }

    public boolean rimuoviVisitaGenerale(Set<Cliente> clienti, Visita visita) {
        if(Objects.isNull(visita))
            throw new IllegalArgumentException("null visit");
        boolean tutteVisiteEliminate=true;
        for(int i=1; i<=clienti.size();i++){
            boolean visitaEliminata=false;
            for(Cliente cliente : clienti){
                if(cliente.getIdCliente() == i){
                    dbms.removeVisita(cliente.getIdCliente(),visita.getIdVisita());
                    visitaEliminata = true;
                    break;
                }
            }
            if(!visitaEliminata){
                tutteVisiteEliminate=false;
            }
        }
        return tutteVisiteEliminate;
    }
}