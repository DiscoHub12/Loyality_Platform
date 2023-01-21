package loyality_platform_model.DBController;

import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.Dipendente;
import java.util.*;

/**
 * Class representing the DataBase, capable of persisting data within
 * the Platform.
 */
public class DBMSController {

    /**
     * This attribute represents the instance of the class, capable of
     * being able to invoke it without instantiating a new class and losing persistence.
     */
    private static DBMSController instance;

    /**
     * This attribute rapresent the name of this DataBase.
     */
    private final String nameDb;

    /**
     * This attribute represents the list of (final) Clients
     * registered within the platform.
     */
    private final Set<Cliente> clientiIscritti;

    private final Map<Azienda, Set<Dipendente>> dipendentiAzienda;


    public DBMSController(String nameDb) {
        this.nameDb = nameDb;
        this.clientiIscritti = new HashSet<>();
        this.dipendentiAzienda = new HashMap<>();
    }

    public static DBMSController getInstance() {
        if (instance == null) {
            instance = new DBMSController(getInstance().nameDb);
        }
        return instance;
    }

    public Set<Cliente> getClientiIscritti() {
        return this.clientiIscritti;
    }

    public void addCliente(Cliente cliente) {
        Objects.requireNonNull(cliente);
        if (clientiIscritti.contains(cliente))
            throw new IllegalArgumentException("Costumer already present.");
        this.clientiIscritti.add(cliente);
    }

    public void removeCliente(Cliente cliente) {
        Objects.requireNonNull(cliente);
        if (!clientiIscritti.contains(cliente))
            throw new IllegalArgumentException("Costumer does not exist.");
        this.clientiIscritti.remove(cliente);
    }

    public Map<Azienda, Set<Dipendente>> getDipendentiAzienda() {
        return dipendentiAzienda;
    }

    public void addDipendenteAzienda(Azienda azienda, Dipendente dipendente){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(dipendente);
        for(Map.Entry<Azienda, Set<Dipendente>> entry : this.getDipendentiAzienda().entrySet()){
            if(azienda.equals(entry.getKey())){
                entry.getValue().add(dipendente);
            }
            throw new IllegalArgumentException("Company not exists");
        }
    }

    public void modificaDipendenteAzienda(Dipendente olddipendente, Azienda azienda, Dipendente newDipendente){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(olddipendente);
        Objects.requireNonNull(newDipendente);
       for(Map.Entry<Azienda, Set<Dipendente>> entry : this.getDipendentiAzienda().entrySet()){
           if(azienda.equals(entry.getKey())){
               if(entry.getValue().contains(olddipendente)){
                   entry.getValue().remove(olddipendente);
                   entry.getValue().add(newDipendente);
               }
               throw new IllegalArgumentException("User not exists");
           }
           throw new IllegalArgumentException("Company not exists");
       }
    }

    public void removeDipendenteAzienda(Azienda azienda, Dipendente dipendente) {
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(dipendente);
        for (Map.Entry<Azienda, Set<Dipendente>> entry : this.getDipendentiAzienda().entrySet()) {
            if (azienda.equals(entry.getKey())) {
                entry.getValue().remove(dipendente);
            }
            throw new IllegalArgumentException("Company not exists");
        }
    }
}
