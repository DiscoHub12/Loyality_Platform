package loyality_platform_model.DBController;

import loyality_platform_model.Models.Cliente;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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


    public DBMSController(String nameDb) {
        this.nameDb = nameDb;
        this.clientiIscritti = new HashSet<>();
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
}
