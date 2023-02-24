package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Cliente;
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


    public DBMS getDbms() {
        return dbms;
    }

    /**
     * This method returns a set of visits of a costumer.
     * @param idCliente considered costumer.
     * @return a set of visits.
     */
    public Set<Visita> getVisiteCliente(int idCliente){
        if(idCliente<1)
            throw new IllegalArgumentException("Illegal id");
        return this.dbms.getVisiteClienteById(idCliente);
    }

    /**
     * This method returns one visit of costumer.
     * @param idCliente considered costumer.
     * @param idVisita visit to return.
     * @return visit if exists, null otherwise.
     */
    public Visita getVisitaById(int idCliente, int idVisita) {
        if (idCliente <= 0 || idVisita <= 0)
            throw new IllegalArgumentException("Invalid id for Customer or Visit.");
        return this.dbms.getVisitaById(idCliente, idVisita);
    }

    /**
     * This method returns the details of the visit.
     * @param idCliente considered consumer.
     * @param idVisita id of the visit to return.
     * @return the details of the visit.
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
     * @throws IllegalArgumentException if the location or data is incorrect.
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
     * @param data data of the visit.
     * @param luogo place of the visit.
     * @return <code>true</code> if the visit is added, <code>false</code> otherwise.
     */
    public boolean aggiungiVisita(int idCliente, String data, String luogo) {
        if(Objects.equals(luogo, "") || (Objects.equals(data,"")))
            throw new IllegalArgumentException("place or data are empty");
        if  (idCliente < 1)
            throw new IllegalArgumentException("Id not correct");
        Visita visitaNew = creaVisita(luogo, data);
        return this.dbms.addVisita(idCliente, visitaNew);
    }


    /**
     * Method that allows you to add a visit to a sets of Customer (registered).
     *
     * @param clienti the set of costumer (registrated)
     * @param data data of the visit.
     * @param luogo place of the visit.
     * @return <code>true</code> if the visit is added, <code>false</code> otherwise.
     */
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
     * @param idCliente considered costumer.
     * @param idVisita considered visit.
     * @param luogo   the new Place of the Visit.
     * @param data    the new Date of the Visit.
     * @param completata <code>true</code> if the visit is complete, <code>false</code> otherwise
     * @return <code>true</code> if the visit is updated, <code>false</code> otherwise.
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
     * @param idCliente considered costumer.
     * @param idVisita visit to remove.
     * @return <code>true</code> if the visit is removed, <code>false</code> otherwise.
     */
    public boolean rimuoviVisita(int idCliente, int idVisita) {
        if (idCliente < 1 || idVisita<1)
            throw new IllegalArgumentException("Customer Id or visit id are not correct");
        return this.dbms.removeVisita(idCliente, idVisita);
    }

    /**
     * Method that allows you to remove a specific Visit for a sets of Costumers.
     * @param clienti considered costumers.
     * @param visita visit to remove.
     * @return <code>true</code> if the visit is removed, <code>false</code> otherwise.
     */
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