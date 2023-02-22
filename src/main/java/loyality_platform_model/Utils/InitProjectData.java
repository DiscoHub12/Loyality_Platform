package loyality_platform_model.Utils;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

public class InitProjectData {

    private static InitProjectData instance;

    //GESTORE E AZIENDA 1 :
    private final GestorePuntoVendita titolare = new GestorePuntoVendita("Mario", "Rossi", "mariorossi@gmail.com", "password");

    private final Dipendente dipendente0 = new Dipendente("Luca", "Neri", "lucaneri@gmail.com", "password1", true);

    private final Dipendente dipendente1 = new Dipendente("Marco", "Magni", "marcomagni@gmail.com", "password2", true);

    private final SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "Indirizzo", "000000", "emailazienda@gmail.com");

    private final Azienda azienda = new Azienda(titolare, spazioFedelta);

    //GESTORE E AZIENDA 2:
    private final GestorePuntoVendita titolare1 = new GestorePuntoVendita("Sofia", "Scattolini", "sofiascattolini@gmail.com", "password");

    private final SpazioFedelta spazioFedelta1 = new SpazioFedelta("Azienda1", "Indirizzo1", "000001", "emailazienda1@gmail.com");

    private final Azienda azienda1 = new Azienda(titolare1, spazioFedelta1);

    //ALTRO :

    private final Coupon coupon = new Coupon(20, "2022-04-12", "2022-12-12");

    private final ConfigurazioneSMS configurazioneSMS = new ConfigurazioneSMS("Benvenuto.");

    private final SMS sms = new SMS(configurazioneSMS.getTestoConfigurato());

    //CLIENTE
    private final Cliente cliente = new Cliente("Luigi", "Mauri", "0000000", "lucamauri@gmail.com", "password3");


    public InitProjectData() {
        //DATABASE
        DBMS dbms = DBMS.getInstance();
        dbms.addAzienda(azienda);
        dbms.addDipendente(azienda.getIdAzienda(), dipendente0);
        dbms.addDipendente(azienda.getIdAzienda(), dipendente1);
        dbms.addCouponPreconfiguratoAzienda(azienda.getIdAzienda(), coupon);
        dbms.addConfigurazioneSMS(azienda.getIdAzienda(), sms);
        dbms.addCliente(cliente);
        dbms.addCoupon(azienda.getIdAzienda(), cliente.getIdCliente(), coupon.getIdCoupon());

        dbms.addAzienda(azienda1);

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

    public Coupon getCoupon(){ return coupon; }

    public SMS getSms(){ return sms; }

    public Cliente getCliente() {
        return cliente;
    }

}
