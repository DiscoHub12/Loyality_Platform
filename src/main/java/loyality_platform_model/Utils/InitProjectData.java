package loyality_platform_model.Utils;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

public class InitProjectData {

    private static InitProjectData instance;

    //GESTORE E AZIENDA
    private final GestorePuntoVendita titolare = new GestorePuntoVendita("Mario", "Rossi", "mariorossi@gmail.com");

    private final Dipendente dipendente0 = new Dipendente("Luca", "Neri", "lucaneri@gmail.com", true);

    private final Dipendente dipendente1 = new Dipendente("Marco", "Magni", "marcomagni@gmail.com", true);

    private final SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "Indirizzo", "000000", "emailazienda@gmail.com");

    private final Azienda azienda = new Azienda(titolare, spazioFedelta);

    //CLIENTE
    private final Cliente cliente = new Cliente("Luigi", "Mauri", "0000000", "lucamauri@gmail.com");

    public InitProjectData() {
        //DATABASE
        DBMS dbms = DBMS.getInstance();
        dbms.addAzienda(azienda);
        dbms.addDipendente(azienda.getIdAzienda(), dipendente0);
        dbms.addDipendente(azienda.getIdAzienda(), dipendente1);
        dbms.addCliente(cliente);
    }

    public static InitProjectData getInstance() {
        if (instance == null)
            instance = new InitProjectData();
        return instance;
    }

    //GETTER METHODS
    public GestorePuntoVendita getTitolare() {
        return this.titolare;
    }

    public Dipendente getDipendente0() {
        return this.dipendente0;
    }

    public Dipendente getDipendente1() {
        return this.dipendente1;
    }

    public SpazioFedelta getSpazioFedelta() {
        return this.spazioFedelta;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
