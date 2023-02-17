package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerAzienda;
import loyality_platform_model.Handler.HandlerPremi;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class HandlerPremiTest {

    //TITOLARE E AZIENDA
    private final GestorePuntoVendita titolare = new GestorePuntoVendita("Nome", "Cognome", "Email");
    private final Dipendente dipendente = new Dipendente("Dipendente1", "Cognome1", "prova@gmail1.com", false);
    private final Dipendente dipendente1 = new Dipendente("Dipendente2", "Cognome2", "prova@gmail1.com", true);
    private final SpazioFedelta spazioFedelta = new SpazioFedelta("Nome", "Indizzo", "Numero Telefono", "Email");
    private final Azienda azienda = new Azienda(titolare, spazioFedelta);

    //PROGRAMMI FEDELTA' PUNTI
    private final Map<Integer, Integer> policy= new HashMap<>();
    private final ProgrammaFedelta programmaFedeltaPunti1 = new ProgrammaPunti("ProgrammaUno",  "22-02-2022", 100, 2, 10, 20);
    private final ProgrammaFedelta programmaFedeltaPunti2 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20);
    private final ProgrammaFedelta programmaFedeltaPunti3 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20);

    //PROGRAMMI FEDELT' LIVELLI
    private final ProgrammaFedelta programmaFedeltaLivelli1 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli2 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli3 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10);

    //COUPON
    private final Coupon coupon = new Coupon(20, "22-03-2022");
    private final Coupon coupon1 = new Coupon(20, "22-03-2022");
    private final Coupon coupon2 = new Coupon(20, "22-03-2022");
    private final Set<Coupon> couponAzienda = new HashSet<>();

    //CATALOGO PREMI PUNTI
    private final Premio premioPunti = new Premio("nome", true, 5);
    private final Premio premioPunti1 = new Premio("nome", true, 5);
    private final Premio premioPunti2 = new Premio("nome", true, 5);
    private final Premio premioPunti3 = new Premio("nome", true, 5);
    private final Set<Premio> premiPunti = new HashSet<>();

    //CATALOGO PREMI LIVELLI
    private final Premio premioLivelli = new Premio("nome", false, 10);
    private final Premio premioLivelli1 = new Premio("nome", false, 10);
    private final Premio premioLivelli2 = new Premio("nome", false, 10);
    private final Premio premioLivelli3 = new Premio("nome", false, 10);
    private final Set<Premio> premiLivelli = new HashSet<>();

    //CLIENTE E TESSERA
    private final Cliente cliente = new Cliente("Nome", "Cognome", "Telefono", "Email");
    private final Tessera tesseraCliente = new Tessera(this.cliente.getIdCliente());


    //HANDLER E DB
    private final HandlerPremi gestorePremi = new HandlerPremi();
    private final HandlerAzienda gestoreAzienda = new HandlerAzienda();
    private final DBMS dbms = DBMS.getInstance();

    void initDb(){
        //AZIENDA
        this.dbms.addAzienda(azienda);
        this.dbms.addDipendente(this.azienda.getIdAzienda(), dipendente);
        this.dbms.addDipendente(this.azienda.getIdAzienda(), dipendente1);
        //PROGRAMMI FEDELTA' PUNTI
        this.dbms.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti1);
        this.dbms.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti2);
        this.dbms.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti3);
        //PROGRAMMI FEDELTA' LIVELLI
        this.dbms.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli1);
        this.dbms.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli2);
        this.dbms.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli3);
        //COUPON
        this.couponAzienda.add(coupon);
        this.couponAzienda.add(coupon1);
        this.couponAzienda.add(coupon2);
        this.dbms.addCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), coupon);
        this.dbms.addCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), coupon1);
        this.dbms.addCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), coupon2);
        //PREMI LIVELLI E CATALOGO
        this.premiPunti.add(premioPunti);
        this.premiPunti.add(premioPunti1);
        this.premiPunti.add(premioPunti2);
        this.premiPunti.add(premioPunti3);
        CatalogoPremi catalogoPremiPunti = new CatalogoPremi(this.premiPunti);
        this.dbms.addCatalogoPremiAzienda(this.azienda.getIdAzienda(), catalogoPremiPunti);
        //PREMI PUNTI E CATALOGO
        this.premiLivelli.add(premioLivelli);
        this.premiLivelli.add(premioLivelli1);
        this.premiLivelli.add(premioLivelli2);
        this.premiLivelli.add(premioLivelli3);
        CatalogoPremi catalogoPremiLivelli = new CatalogoPremi(this.premiLivelli);
        this.dbms.addCatalogoPremiAzienda(this.azienda.getIdAzienda(), catalogoPremiLivelli);
        //CLIENTE
        this.dbms.addCliente(cliente);
        this.dbms.addTessera(tesseraCliente);
    }


    @Test
    public void testGetCouponPreconfiguratiAzienda(){
        initDb();
        Set<Coupon> couponAzienda = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertNotNull(couponAzienda);
        assertEquals(couponAzienda, this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda()));
    }

    @Test
    public void testGetCouponPreconfiguratoAzienda(){
        initDb();
        Coupon coupon = this.gestorePremi.getCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), 1);
        assertNotNull(coupon);
        assertEquals(coupon, this.gestorePremi.getCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), 1));
    }

    @Test
    public void testCreaPremio(){
        initDb();
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.creaPremio("", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.creaPremio("nome", false, -1));
        Premio premio = this.gestorePremi.creaPremio("nome", true, 10);
        assertEquals("nome", premio.getNome());
        assertEquals(10, premio.getPunti());
        Premio premio1 = this.gestorePremi.creaPremio("premio1", false, 10);
        assertEquals("premio1", premio1.getNome());
        assertEquals(10, premio1.getLivelli());

    }

    @Test
    public void testCreaCatalogo(){
        initDb();
        assertThrows(NullPointerException.class, () -> this.gestorePremi.creaCatalogo(null));
        CatalogoPremi catalogo = this.gestorePremi.creaCatalogo(this.premiPunti);
        assertNotNull(catalogo);
        assertEquals(this.premiPunti, catalogo.getPremiCatalogo());
        CatalogoPremi catalogo1 = this.gestorePremi.creaCatalogo(this.premiLivelli);
        assertNotNull(catalogo1);
        assertEquals(this.premiLivelli, catalogo1.getPremiCatalogo());
    }

    @Test
    public void testCreaCoupon(){
        initDb();
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.creaCoupon(-1, "12-03-2022"));
        assertThrows(NullPointerException.class, () -> this.gestorePremi.creaCoupon(1, null));
        Coupon coupon = this.gestorePremi.creaCoupon(10, "data");
        assertNotNull(coupon);
        assertEquals(10, coupon.getValoreSconto());
        assertEquals("data", coupon.getDataScadenza());
        Coupon coupon1 = this.gestorePremi.creaCoupon(20, "data");
        assertNotNull(coupon1);
        assertEquals(20, coupon1.getValoreSconto());
        assertEquals("data", coupon1.getDataScadenza());
    }

    @Test
    public void testAggiungiPremio(){
        initDb();
        Set<CatalogoPremi> cataloghiPremi = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        CatalogoPremi catalogoPremi;
        for(CatalogoPremi catalogoPremi1 : cataloghiPremi){
            if(catalogoPremi1.getIdCatalogoPremi() == 1)
                catalogoPremi = catalogoPremi1;
        }
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremio(-1, 1, "nome", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremio(1, -1, "nome", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremio(1, -1, "", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremio(1, -1, "nome", false, -1));
        assertTrue(this.gestorePremi.aggiungiPremio(this.azienda.getIdAzienda(), 1, "nome", true, 10));
        assertTrue(this.gestorePremi.aggiungiPremio(this.azienda.getIdAzienda(), 1, "nome", true, 10));
        assertTrue(this.gestorePremi.aggiungiPremio(this.azienda.getIdAzienda(), 1, "nome", true, 10));
    }

    @Test
    public void testModificaPremio(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testRimuoviPremio(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testAggiungiCatalogoPremi(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testRimuoviCatalogoPremi(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testAggiungiCatalogoProgrammaPunti(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testAggiungiCatalogoProgrammaLivelli(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testRimuoviCatalogoProgramma(){
        initDb();
        //Todo implementare
    }

    @Test
    public void aggiungiCouponPreconfigurato(){
        initDb();
        //Todo implementare
    }

    @Test
    public void modificaCouponPreconfigurato(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testRimuoviCouponPreconfigurato(){
        initDb();
        //Todo implementare
    }

    @Test
    public void aggiungiPremioClienteCatalogoGenerale(){
        initDb();
        //Todo implementare
    }

    @Test
    public void aggiungiPremioClienteCatalogoProgramma(){
        initDb();
        //Todo implementare
    }

    @Test
    public void deletePremioCliente(){
        initDb();
        //Todo implementare
    }

    @Test
    public void aggiungiCouponCliente(){
        initDb();
        //Todo implementare
    }

    @Test
    public void deleteCouponCliente(){
        initDb();
        //Todo implementare
    }

}
