package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerAzienda;
import loyality_platform_model.Handler.HandlerCliente;
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
    private final ProgrammaFedelta programmaFedeltaPunti1 = new ProgrammaPunti("ProgrammaUno",  "22-02-2022", 100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaPunti2 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaPunti3 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20, null);

    //PROGRAMMI FEDELT' LIVELLI
    private final ProgrammaFedelta programmaFedeltaLivelli1 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);
    private final ProgrammaFedelta programmaFedeltaLivelli2 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);
    private final ProgrammaFedelta programmaFedeltaLivelli3 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);

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

    private final HandlerCliente gestoreCliente = new HandlerCliente();
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
        //CATALOGO PREMI PROGRAMMI
        this.programmaFedeltaPunti3.setCatalogoPremi(catalogoPremiPunti);
        this.programmaFedeltaLivelli3.setCatalogoPremi(catalogoPremiLivelli);
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
        Set<CatalogoPremi> cataloghiPremiPunti = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, cataloghiPremiPunti.size());
        CatalogoPremi catalogoPremiPunti = null;
        for(CatalogoPremi catalogoPremi : cataloghiPremiPunti){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiPunti = catalogoPremi;
        }
        assert catalogoPremiPunti != null;
        assertEquals(4, catalogoPremiPunti.getPremiCatalogo().size());
        int size1 = catalogoPremiPunti.getPremiCatalogo().size();
        CatalogoPremi catalogoPremiLivelli = null;
        for(CatalogoPremi catalogoPremi : cataloghiPremiPunti){
            if(catalogoPremi.getIdCatalogoPremi() == 2)
                catalogoPremiLivelli = catalogoPremi;
        }
        assert catalogoPremiLivelli != null;
        assertEquals(4, catalogoPremiLivelli.getPremiCatalogo().size());
        int sizeL1 = catalogoPremiLivelli.getPremiCatalogo().size();
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremioCatalogo(-1, 1, "nome", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremioCatalogo(1, -1, "nome", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremioCatalogo(1, -1, "", false, 10));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiPremioCatalogo(1, -1, "nome", false, -1));
        assertTrue(this.gestorePremi.aggiungiPremioCatalogo(this.azienda.getIdAzienda(), 1, "nome", true, 10));
        assertTrue(this.gestorePremi.aggiungiPremioCatalogo(this.azienda.getIdAzienda(), 1, "nome", true, 10));
        assertTrue(this.gestorePremi.aggiungiPremioCatalogo(this.azienda.getIdAzienda(), 1, "nome", true, 10));
        assertTrue(this.gestorePremi.aggiungiPremioCatalogo(this.azienda.getIdAzienda(), 2, "nome", false, 10));
        assertTrue(this.gestorePremi.aggiungiPremioCatalogo(this.azienda.getIdAzienda(), 2, "nome", false, 10));
        assertTrue(this.gestorePremi.aggiungiPremioCatalogo(this.azienda.getIdAzienda(), 2, "nome", false, 10));
        Set<CatalogoPremi> catalogoPremiAggiornatoPunti = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        CatalogoPremi catalogoPremiPuntiAggiornato = null ;
        for(CatalogoPremi catalogoPremi : catalogoPremiAggiornatoPunti){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiPuntiAggiornato = catalogoPremi;
        }
        assert catalogoPremiPuntiAggiornato != null;
        assertEquals(7, catalogoPremiPuntiAggiornato.getPremiCatalogo().size());
        int size2 = catalogoPremiPuntiAggiornato.getPremiCatalogo().size();
        assertNotEquals(size1, size2);
        CatalogoPremi catalogoPremiLivelliAggiornato = null;
        for(CatalogoPremi catalogoPremi : catalogoPremiAggiornatoPunti){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiLivelliAggiornato = catalogoPremi;
        }
        assert catalogoPremiLivelliAggiornato != null;
        assertEquals(7, catalogoPremiLivelliAggiornato.getPremiCatalogo().size());
        int sizeL2 = catalogoPremiLivelliAggiornato.getPremiCatalogo().size();
        assertNotEquals(sizeL1, sizeL2);
    }

    @Test
    public void testModificaPremio(){
        initDb();
        Set<CatalogoPremi> cataloghiPremiPunti = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, cataloghiPremiPunti.size());
        CatalogoPremi catalogoPremiPunti = null;
        for(CatalogoPremi catalogoPremi : cataloghiPremiPunti){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiPunti = catalogoPremi;
        }
        assert catalogoPremiPunti != null;
        Premio premioStandard1 = null;
        for(Premio premio : catalogoPremiPunti.getPremiCatalogo()){
            if(premio.getIdPremio() == 1)
                premioStandard1 = premio;
        }
        assert premioStandard1 != null;
        assertEquals("nome", premioStandard1.getNome());
        assertEquals(5, premioStandard1.getPunti());
        assertEquals(0, premioStandard1.getLivelli());
        assertTrue(this.gestorePremi.modificaPremio(this.azienda.getIdAzienda(), 1, 1, "ciao", true, 25));
        Set<CatalogoPremi> cataloghiPremiPunti1 = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, cataloghiPremiPunti1.size());
        CatalogoPremi catalogoPremiPunti1 = null;
        for(CatalogoPremi catalogoPremi : cataloghiPremiPunti1){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiPunti1 = catalogoPremi;
        }
        Premio premioAggiornato = null;
        assert catalogoPremiPunti1 != null;
        for(Premio premio : catalogoPremiPunti1.getPremiCatalogo()){
            if(premio.getIdPremio() == 1)
                premioAggiornato = premio;
        }
        assert premioAggiornato != null;
        assertEquals("ciao",premioAggiornato.getNome());
        assertEquals(25, premioAggiornato.getPunti());

    }

    @Test
    public void testRimuoviPremio(){
        initDb();
        Set<CatalogoPremi> cataloghiPremiPunti = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, cataloghiPremiPunti.size());
        CatalogoPremi catalogoPremiPunti = null;
        for(CatalogoPremi catalogoPremi : cataloghiPremiPunti){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiPunti = catalogoPremi;
        }
        assert catalogoPremiPunti != null;
        Premio premioStandard1 = null;
        for(Premio premio : catalogoPremiPunti.getPremiCatalogo()){
            if(premio.getIdPremio() == 1)
                premioStandard1 = premio;
        }
        assert premioStandard1 != null;
        assertEquals("nome", premioStandard1.getNome());
        assertEquals(5, premioStandard1.getPunti());
        assertEquals(0, premioStandard1.getLivelli());
        assertTrue(this.gestorePremi.deletePremio(this.azienda.getIdAzienda(), 1, 1));
        Set<CatalogoPremi> cataloghiPremiPuntiUpdated = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, cataloghiPremiPuntiUpdated.size());
        CatalogoPremi catalogoPremiPuntiUpdated = null;
        for(CatalogoPremi catalogoPremi : cataloghiPremiPuntiUpdated){
            if(catalogoPremi.getIdCatalogoPremi() == 1)
                catalogoPremiPuntiUpdated = catalogoPremi;
        }
        assert catalogoPremiPuntiUpdated != null;
        assertFalse(catalogoPremiPuntiUpdated.getPremiCatalogo().contains(premioStandard1));
    }

    @Test
    public void testAggiungiCatalogoPremi(){
        initDb();
        Set<CatalogoPremi> catalogoPremi = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, catalogoPremi.size());
        Premio premio1 = new Premio("nome", true, 10);
        Premio premio2 = new Premio("nome", true, 10);
        Premio premio3 = new Premio("nome", true, 10);
        Premio premio4 = new Premio("nome", true, 10);
        Set<Premio> premi1 = new HashSet<>();
        premi1.add(premio1);
        premi1.add(premio2);
        premi1.add(premio3);
        premi1.add(premio4);
        assertTrue(this.gestorePremi.aggiungiCatalogoPremi(this.azienda.getIdAzienda(), premi1));
        assertTrue(this.gestorePremi.aggiungiCatalogoPremi(this.azienda.getIdAzienda(), premi1));
        Set<CatalogoPremi> catalogoPremiUpdated = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(4, catalogoPremiUpdated.size());
        assertTrue(this.gestorePremi.aggiungiCatalogoPremi(this.azienda.getIdAzienda(), premi1));
        assertTrue(this.gestorePremi.aggiungiCatalogoPremi(this.azienda.getIdAzienda(), premi1));
        Set<CatalogoPremi> catalogoPremiUpdated1 = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(6, catalogoPremiUpdated1.size());
    }

    @Test
    public void testRimuoviCatalogoPremi(){
        initDb();
        Set<CatalogoPremi> catalogoPremi = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, catalogoPremi.size());
        assertEquals(2, catalogoPremi.size());
        assertTrue(this.gestorePremi.deleteCatalogoPremi(this.azienda.getIdAzienda(), 1));
        Set<CatalogoPremi> catalogoPremiUpdated = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertEquals(1, catalogoPremiUpdated.size());
    }

    @Test
    public void testAggiungiCatalogoProgrammaPunti(){
        initDb();
        Set<Premio> premiCatalogo = new HashSet<>();
        premiCatalogo.add(new Premio("premio", true, 10));
        premiCatalogo.add(new Premio("premi1", true, 10));
        premiCatalogo.add(new Premio("premio2", true, 10));
        assertThrows(NullPointerException.class, () -> this.gestorePremi.aggiungiCatalogoAProgrammaPunti(this.azienda.getIdAzienda(), this.programmaFedeltaPunti1.getIdProgramma(), null));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiCatalogoAProgrammaPunti(this.azienda.getIdAzienda(), -1, premiCatalogo));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiCatalogoAProgrammaPunti(-1, this.programmaFedeltaPunti1.getIdProgramma(), premiCatalogo));
        Set<ProgrammaFedelta> programmiAzienda = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedelta = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAzienda){
            if(programmaFedelta1.getIdProgramma() == 1)
                programmaFedelta = programmaFedelta1;
        }
        assertNotNull(programmaFedelta);
        assertNull(programmaFedelta.getCatalogoPremi());
        assertTrue(this.gestorePremi.aggiungiCatalogoAProgrammaPunti(this.azienda.getIdAzienda(), this.programmaFedeltaPunti1.getIdProgramma(), premiCatalogo));
        Set<ProgrammaFedelta> programmiAziendaUpdated = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedeltaUpdated = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAziendaUpdated){
            if(programmaFedelta1.getIdProgramma() == 1)
                programmaFedeltaUpdated = programmaFedelta1;
        }
        assertNotNull(programmaFedeltaUpdated);
        assertNotNull(programmaFedeltaUpdated.getCatalogoPremi());

    }

    @Test
    public void testAggiungiCatalogoProgrammaLivelli(){
        initDb();
        Set<Premio> premiCatalogo = new HashSet<>();
        premiCatalogo.add(new Premio("premio", false, 10));
        premiCatalogo.add(new Premio("premi1", false, 10));
        premiCatalogo.add(new Premio("premio2", false, 10));
        assertThrows(NullPointerException.class, () -> this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), this.programmaFedeltaPunti1.getIdProgramma(), null));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), -1, premiCatalogo));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiCatalogoProgrammaLivelli(-1, this.programmaFedeltaPunti1.getIdProgramma(), premiCatalogo));
        Set<ProgrammaFedelta> programmiAzienda = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedelta = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAzienda){
            if(programmaFedelta1.getIdProgramma() == 4)
                programmaFedelta = programmaFedelta1;
        }
        assertNotNull(programmaFedelta);
        assertNull(programmaFedelta.getCatalogoPremi());
        assertTrue(this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), this.programmaFedeltaLivelli1.getIdProgramma(), premiCatalogo));
        Set<ProgrammaFedelta> programmiAziendaUpdated = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedeltaUpdated = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAziendaUpdated){
            if(programmaFedelta1.getIdProgramma() == 4)
                programmaFedeltaUpdated = programmaFedelta1;
        }
        assertNotNull(programmaFedeltaUpdated);
        assertNotNull(programmaFedeltaUpdated.getCatalogoPremi());
    }

    @Test
    public void testRimuoviCatalogoProgramma(){
        initDb();
        Set<Premio> premiCatalogo = new HashSet<>();
        premiCatalogo.add(new Premio("premio", false, 10));
        premiCatalogo.add(new Premio("premi1", false, 10));
        premiCatalogo.add(new Premio("premio2", false, 10));
        assertThrows(NullPointerException.class, () -> this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), this.programmaFedeltaPunti1.getIdProgramma(), null));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), -1, premiCatalogo));
        assertThrows(IllegalArgumentException.class, () -> this.gestorePremi.aggiungiCatalogoProgrammaLivelli(-1, this.programmaFedeltaPunti1.getIdProgramma(), premiCatalogo));
        Set<ProgrammaFedelta> programmiAzienda = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedelta = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAzienda){
            if(programmaFedelta1.getIdProgramma() == 4)
                programmaFedelta = programmaFedelta1;
        }
        assertNotNull(programmaFedelta);
        assertNull(programmaFedelta.getCatalogoPremi());
        assertTrue(this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), this.programmaFedeltaLivelli1.getIdProgramma(), premiCatalogo));
        Set<ProgrammaFedelta> programmiAziendaUpdated = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedeltaUpdated = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAziendaUpdated){
            if(programmaFedelta1.getIdProgramma() == 4)
                programmaFedeltaUpdated = programmaFedelta1;
        }
        assertNotNull(programmaFedeltaUpdated);
        assertNotNull(programmaFedeltaUpdated.getCatalogoPremi());
        assertTrue(this.gestorePremi.deleteCatalogoProgramma(this.azienda.getIdAzienda(), programmaFedeltaUpdated.getIdProgramma(), programmaFedeltaUpdated.getCatalogoPremi().getIdCatalogoPremi()));
        Set<ProgrammaFedelta> programmiAziendaUpdated1 = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        ProgrammaFedelta programmaFedeltaUpdated1 = null;
        for(ProgrammaFedelta programmaFedelta1 : programmiAziendaUpdated1){
            if(programmaFedelta1.getIdProgramma() == 4)
                programmaFedeltaUpdated1 = programmaFedelta1;
        }
        assertNotNull(programmaFedeltaUpdated1);
        assertNull(programmaFedeltaUpdated.getCatalogoPremi());
    }

    @Test
    public void aggiungiCouponPreconfigurato(){
        initDb();
        Set<Coupon> couponAzienda = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(3, couponAzienda.size());
        assertTrue(this.gestorePremi.aggiungiCouponPreconfigurato(this.azienda.getIdAzienda(), 10, "dataScadenza"));
        Set<Coupon> couponAziendaUpdated = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(4, couponAziendaUpdated.size());
        Coupon coupon = null;
        for(Coupon coupons : couponAziendaUpdated){
            if(coupons.getIdCoupon() == 4)
                coupon = coupons;
        }
        assert coupon != null;
        assertEquals(10, coupon.getValoreSconto());
        assertEquals("dataScadenza", coupon.getDataScadenza());
    }

    @Test
    public void modificaCouponPreconfigurato(){
        initDb();
        Set<Coupon> couponAzienda = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(3, couponAzienda.size());
        assertTrue(this.gestorePremi.aggiungiCouponPreconfigurato(this.azienda.getIdAzienda(), 10, "dataScadenza"));
        Set<Coupon> couponAziendaUpdated = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(4, couponAziendaUpdated.size());
        Coupon coupon = null;
        for(Coupon coupons : couponAziendaUpdated){
            if(coupons.getIdCoupon() == 4)
                coupon = coupons;
        }
        assert coupon != null;
        assertEquals(10, coupon.getValoreSconto());
        assertEquals("dataScadenza", coupon.getDataScadenza());
        assertTrue(this.gestorePremi.modificaCouponPreconfigurato(this.azienda.getIdAzienda(), 4, 100, "dataScadenza1"));
        Set<Coupon> couponAziendaUpdated1 = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(4, couponAziendaUpdated.size());
        Coupon coupon1 = null;
        for(Coupon coupons : couponAziendaUpdated){
            if(coupons.getIdCoupon() == 4)
                coupon1 = coupons;
        }
        assert coupon1 != null;
        assertEquals(100, coupon.getValoreSconto());
        assertEquals("dataScadenza1", coupon.getDataScadenza());
    }

    @Test
    public void testRimuoviCouponPreconfigurato(){
        initDb();
        Set<Coupon> couponAzienda = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(3, couponAzienda.size());
        Coupon coupon = null;
        for(Coupon coupons : couponAzienda){
            if(coupons.getIdCoupon() == 1)
                coupon = coupons;
        }
        assertTrue(this.gestorePremi.deleteCouponPreconfigurato(this.azienda.getIdAzienda(), 1));
        Set<Coupon> couponAziendaUpdated = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        assertEquals(2, couponAziendaUpdated.size());
        assertFalse(couponAziendaUpdated.contains(coupon));
    }

    @Test
    public void aggiungiPremioClienteCatalogoGenerale(){
        initDb();
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoGenerale(this.azienda.getIdAzienda(), 1, 1));
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoGenerale(this.azienda.getIdAzienda(), 1, 2));
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoGenerale(this.azienda.getIdAzienda(), 1, 3));
        Set<Premio> premiCliente = this.gestoreCliente.getPremiCliente(1);
        assertNotNull(premiCliente);
        assertEquals(3, premiCliente.size());
    }

    @Test
    public void aggiungiPremioClienteCatalogoProgramma(){
        initDb();
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoProgramma(this.azienda.getIdAzienda() , this.programmaFedeltaPunti3.getIdProgramma(), this.cliente.getIdCliente(), 1));
        Set<Premio> premiCliente = this.gestoreCliente.getPremiCliente(this.cliente.getIdCliente());
        assertNotNull(premiCliente);
        assertTrue(premiCliente.contains(this.premioPunti));
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoProgramma(this.azienda.getIdAzienda() , this.programmaFedeltaLivelli3.getIdProgramma(), this.cliente.getIdCliente(), 6));
        Set<Premio> premiCliente1 = this.gestoreCliente.getPremiCliente(this.cliente.getIdCliente());
        assertNotNull(premiCliente1);
        assertTrue(premiCliente1.contains(this.premioLivelli1));
    }

    @Test
    public void deletePremioCliente(){
        initDb();
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoGenerale(this.azienda.getIdAzienda(), 1, 1));
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoGenerale(this.azienda.getIdAzienda(), 1, 2));
        assertTrue(this.gestorePremi.aggiungiPremioClienteCatalogoGenerale(this.azienda.getIdAzienda(), 1, 3));
        Set<Premio> premiCliente = this.gestoreCliente.getPremiCliente(1);
        assertNotNull(premiCliente);
        assertEquals(3, premiCliente.size());
        assertTrue(this.gestorePremi.deletePremioCliente(cliente.getIdCliente(), 1));
        assertTrue(this.gestorePremi.deletePremioCliente(cliente.getIdCliente(), 2));
        Set<Premio> premiClienteUpdated = this.gestoreCliente.getPremiCliente(1);
        assertNotNull(premiClienteUpdated);
        assertEquals(1, premiClienteUpdated.size());

    }

    @Test
    public void aggiungiCouponCliente(){
        initDb();
        assertTrue(this.gestorePremi.aggiungiCouponCliente(this.azienda.getIdAzienda(), this.cliente.getIdCliente(), this.coupon.getIdCoupon()));
        assertTrue(this.gestorePremi.aggiungiCouponCliente(this.azienda.getIdAzienda(), this.cliente.getIdCliente(), this.coupon1.getIdCoupon()));
        assertTrue(this.gestorePremi.aggiungiCouponCliente(this.azienda.getIdAzienda(), this.cliente.getIdCliente(), this.coupon2.getIdCoupon()));
        Set<Coupon> couponCliente = this.gestoreCliente.getCouponCliente(this.cliente.getIdCliente());
        assertNotNull(couponCliente);
        assertEquals(3, couponCliente.size());
    }

    @Test
    public void deleteCouponCliente(){
        initDb();
        assertTrue(this.gestorePremi.aggiungiCouponCliente(this.azienda.getIdAzienda(), this.cliente.getIdCliente(), this.coupon.getIdCoupon()));
        assertTrue(this.gestorePremi.aggiungiCouponCliente(this.azienda.getIdAzienda(), this.cliente.getIdCliente(), this.coupon1.getIdCoupon()));
        assertTrue(this.gestorePremi.aggiungiCouponCliente(this.azienda.getIdAzienda(), this.cliente.getIdCliente(), this.coupon2.getIdCoupon()));
        Set<Coupon> couponCliente = this.gestoreCliente.getCouponCliente(this.cliente.getIdCliente());
        assertNotNull(couponCliente);
        assertEquals(3, couponCliente.size());
        assertTrue(this.gestorePremi.deleteCouponCliente(this.cliente.getIdCliente(), this.coupon.getIdCoupon()));
        Set<Coupon> couponCliente1 = this.gestoreCliente.getCouponCliente(this.cliente.getIdCliente());
        assertNotNull(couponCliente1);
        assertEquals(2, couponCliente1.size());
        assertTrue(this.gestorePremi.deleteCouponCliente(this.cliente.getIdCliente(), this.coupon1.getIdCoupon()));
        Set<Coupon> couponCliente2 = this.gestoreCliente.getCouponCliente(this.cliente.getIdCliente());
        assertNotNull(couponCliente2);
        assertEquals(1, couponCliente2.size());

    }

}
