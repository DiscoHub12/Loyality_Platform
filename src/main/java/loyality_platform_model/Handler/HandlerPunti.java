package loyality_platform_model.Handler;

import loyality_platform_model.DBController.DBMSController;
import loyality_platform_model.Models.Cliente;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents a customer points manager.
 * In fact, it has a number of features, such as adding points to
 * certain customers, removing them and much more.
 * Of course, customers must have a membership card and must participate
 * in at least 1 loyalty program
 */
public class HandlerPunti {

    /**
     * This attribute represents the Database.
     */
    public DBMSController dbmsController;

    /**
     * Constructor that allows you to create a HandlerPoint object
     * having inside it the instance of the Database for data persistence.
     */
    public HandlerPunti() {
        this.dbmsController = DBMSController.getInstance();
    }

    /**
     * Method that allows you to add a certain number of points to a particular
     * Costumer. Obviously, the Costumer must be in possession of the Card and must
     * partecupate in at least one Loyality Program.
     *
     * @param cliente     the Costumer to add the point.
     * @param numeroPunti the number of the points to add.
     * @throws NullPointerException     if the <cliente> is null.
     * @throws IllegalArgumentException if the number of points is incorrect.
     */
    public void aggiungiPunti(Cliente cliente, int numeroPunti) {
        Objects.requireNonNull(cliente);
        if (numeroPunti <= 0)
            throw new IllegalArgumentException("Illegal number of point.");

        //Todo implementare, manca Cliente e Tessera
    }

    /**
     * Method that allows you to remove a certain number of points to a particular
     * Costumer. Obviously, the Costumer must be in possession of the Card and must
     * partecupate in at least one Loyality Program.
     *
     * @param cliente     the Costumer to add the point.
     * @param numeroPunti the number of the points to add.
     * @throws NullPointerException     if the <cliente> is null.
     * @throws IllegalArgumentException if the number of points is incorrect.
     */
    public void rimuoviPunti(Cliente cliente, int numeroPunti) {
        //Todo implementare, manca Cliente e Tessera
    }

    /**
     * Method that allows you to add a certain number of points to a list of
     * selected customers. Obviously here too, Customers must be registered
     * and must participate in at least 1 Loyalty Programme.
     *
     * @param clienti     the list of the Costumers to add the points.
     * @param numeroPunti the number of points to add.
     * @throws NullPointerException     if the <clienti>is null.
     * @throws IllegalArgumentException if the number of points to add is incorrect.
     */
    public void aggiungiPuntiGenerale(List<Cliente> clienti, int numeroPunti) {
        //Todo implementare, manca Cliente e Tessera
    }

    /**
     * Method that allows you to remove a certain number of points to a list of
     * selected customers. Obviously here too, Customers must be registered and must
     * participate in at least 1 Loyalty Programme.
     *
     * @param clienti     the list of the Costumers to add the points.
     * @param numeroPunti the number of points to add.
     * @throws NullPointerException     if the <clienti>is null.
     * @throws IllegalArgumentException if the number of points to add is incorrect.
     */
    public void rimuoviPuntiGenerale(List<Cliente> clienti, int numeroPunti) {
        //Todo implementare, manca Cliente e Tessera
    }

}
