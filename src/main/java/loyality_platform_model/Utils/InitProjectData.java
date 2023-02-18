package loyality_platform_model.Utils;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

public class InitProjectData {

    private static InitProjectData instance;

    //GESTORE E AZIENDA
    private GestorePuntoVendita titolare = new GestorePuntoVendita("Mario", "Rossi", "mariorossi@gmail.com");

    private Dipendente dipendente0 = new Dipendente("Luca", "Neri", "lucaneri@gmail.com", true);

    private Dipendente dipendente1 = new Dipendente("Marco", "Magni", "marcomagni@gmail.com", true);

    private SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "Indirizzo", "000000", "emailazienda@gmail.com");

    private Azienda azienda = new Azienda(titolare, spazioFedelta);

    //CLIENTE
    private Cliente cliente = new Cliente("Luigi", "Mauri", "0000000", "lucamauri@gmail.com");

    //DATABASE
    private DBMS dbms = DBMS.getInstance();

    private InitProjectData() {
        this.dbms.addAzienda(azienda);
        this.dbms.addDipendente(azienda.getIdAzienda(), dipendente0);
        this.dbms.addDipendente(azienda.getIdAzienda(), dipendente1);
        this.dbms.addCliente(cliente);
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
