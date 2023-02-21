package loyality_platform_model.Models;

/**
 *
 * Class representing the manager of a company.
 * The manager of the company is able to run a series
 * of activities, such as registering on the platform and purchasing a subscription,
 * purchasing SMS packages for SMS campaigns, creating new users, modifying and removing them,
 * adding loyalty programs made available by the platform and compiling them as desired.
 */
public class GestorePuntoVendita extends UtenteGenerico {

    private static int contatoreGestorePV = 0;

    private final int idGestorePuntoVendita;


    public GestorePuntoVendita(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
        this.idGestorePuntoVendita = ++contatoreGestorePV;
    }

    /**
     * This method returns the manager id
     * @return the manager id.
     */
    public int getIdGestorePuntoVendita() {
        return idGestorePuntoVendita;
    }


    @Override
    public String toString() {
        return "\t-DETAILS GESTORE PUNTO VENDITA-" +
                "\nnome: " + getNome() +
                "\ncognome: " + getCognome() +
                "\nemail: " + getEmail();
    }
}
