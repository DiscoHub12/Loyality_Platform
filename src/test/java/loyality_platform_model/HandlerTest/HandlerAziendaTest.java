package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerAzienda;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents a Test class
 * to test the correct functioning of HandlerAzienda.
 */
public class HandlerAziendaTest {

    //TITOLARE
    private final GestorePuntoVendita gestore = new GestorePuntoVendita("Alessio", "Cognome1", "prova@gmail.com");

    //SPAZIO FEDELTA
    private final SpazioFedelta spazio = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");

    //AZIENDA
    private final Azienda azienda = new Azienda(gestore, spazio);

    //DIPENDENTI AZIENDA
    private final Dipendente dipendente = new Dipendente("Dipendente1", "Cognome1", "prova@gmail1.com", false);
    private final Dipendente dipendente1 = new Dipendente("Dipendente2", "Cognome2", "prova@gmail1.com", true);


    //POLICY PROGRAMMI E PROGRAMMI
    private final Map<Integer, Integer> policy= new HashMap<>();
    private final ProgrammaFedelta programmaFedeltaPunti1 = new ProgrammaPunti("ProgrammaUno",  "22-02-2022", 100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaPunti2 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaPunti3 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaLivelli1 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);
    private final ProgrammaFedelta programmaFedeltaLivelli2 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);
    private final ProgrammaFedelta programmaFedeltaLivelli3 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);

    //PREMI P
    private final Premio premio1 = new Premio("Premio1", false, 2);
    private final Premio premio2 = new Premio("Premio2", false, 2);
    private final Premio premio3 = new Premio("Premio3", false, 2);
    private final Premio premio4 = new Premio("Premio4", false, 2);
    private final Set<Premio> premi = new HashSet<>();
    private final Set<CatalogoPremi> cataloghi = new HashSet<>();

    //CLIENTE
    private final Cliente cliente = new Cliente("Nome", "Cognome", "Telefono", "Email");

    private final Tessera tessera = new Tessera(cliente.getIdCliente());

    /**
     * HANDLERAZIENDA E DBMS
     */
    private final HandlerAzienda handler = new HandlerAzienda();
    private final DBMS db = DBMS.getInstance();


    public void initTotal() {
        System.out.println("Id Azienda : " + this.azienda.getIdAzienda());
        System.out.println("Id Spazio Fedeltà azienda : " + this.spazio.getIdSpazioFedelta());
        System.out.println("Id Dipendente azienda : " + this.dipendente.getIdDipendente());
        System.out.printf("Id Dipendente 2 azienda : " + this.dipendente1.getIdDipendente());
        Set<Premio> premi = new HashSet<>();
        premi.add(premio1);
        premi.add(premio2);
        premi.add(premio3);
        premi.add(premio4);
        CatalogoPremi catalogo = new CatalogoPremi(premi);
        Set<CatalogoPremi> catalogoPremi = new HashSet<>();
        catalogoPremi.add(catalogo);
        catalogoPremi.add(catalogo);
        //Azienda 1
        db.addAzienda(azienda);
        db.addDipendente(azienda.getIdAzienda(), dipendente);
        db.addDipendente(azienda.getIdAzienda(), dipendente1);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti1);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti2);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti3);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli1);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli2);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli3);
        db.getCoalizione().addAziendaCoalizione(programmaFedeltaPunti1, azienda);
        db.getCoalizione().addAziendaCoalizione(programmaFedeltaPunti2, azienda);
        db.getCoalizione().addAziendaCoalizione(programmaFedeltaPunti3, azienda);
        db.getCoalizione().addAziendaCoalizione(programmaFedeltaLivelli1, azienda);
        db.getCoalizione().addAziendaCoalizione(programmaFedeltaLivelli2, azienda);
        db.getCoalizione().addAziendaCoalizione(programmaFedeltaLivelli3, azienda);
        //CLIENTE
        db.addCliente(cliente);
        db.addTessera(tessera);
        db.getCoalizione().addClienteCoalizione(programmaFedeltaPunti1, cliente);
        db.getCoalizione().addClienteCoalizione(programmaFedeltaPunti2, cliente);
        db.getCoalizione().addClienteCoalizione(programmaFedeltaPunti3, cliente);
        db.getCoalizione().addClienteCoalizione(programmaFedeltaLivelli1, cliente);
        db.getCoalizione().addClienteCoalizione(programmaFedeltaLivelli2, cliente);
        db.getCoalizione().addClienteCoalizione(programmaFedeltaLivelli3, cliente);
    }

    @Test
    public void testGetTitolareAzienda() {
        initTotal();
        assertNotNull(this.handler.getTitolareAzienda(1));
        assertEquals(1, this.handler.getTitolareAzienda(1).getIdGestorePuntoVendita());
        assertEquals("Alessio", this.handler.getTitolareAzienda(1).getNome());
    }

    @Test
    public void testGetDipendentiAzienda() {
        initTotal();
        Set<Dipendente> dipendenti = handler.getDipendentiAzienda(1);
        assertNotNull(dipendenti);
        assertEquals(dipendenti, this.db.getDipendentiAzienda().get(azienda));
        this.handler.aggiungiDipendente(1, "alessio", "giacche", "email", true);
        Set<Dipendente> dipendenti1 = handler.getDipendentiAzienda(1);
        assertNotNull(dipendenti1);
        assertEquals(dipendenti1, this.db.getDipendentiAzienda().get(azienda));
    }

    @Test
    public void testCreaDipendente() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> handler.creaDipendente("","","",false));
        Dipendente dipendente = handler.creaDipendente("Nome", "Cognome", "Email", false);
        assertNotNull(dipendente);
        assertEquals("Nome", dipendente.getNome());
        assertEquals("Cognome", dipendente.getCognome());
        assertEquals("Email", dipendente.getEmail());
        Dipendente dipendente1 = this.handler.creaDipendente("Alessio", "Giacche", "email", true);
        assertNotNull(dipendente1);
        assertEquals("Alessio", dipendente1.getNome());
        assertEquals("Giacche", dipendente1.getCognome());
        assertEquals("email", dipendente1.getEmail());
    }

    @Test
    public void testAggiungiDipendente() {
        initTotal();
        this.handler.aggiungiDipendente(1, "Nome", "Cognome", "Email", false);
        this.handler.aggiungiDipendente(1, "Alessio", "Cognome1", "email", false);
        Set<Dipendente> dipendentiAzienda1 = this.db.getDipendentiAzienda().get(azienda);
        assertEquals(dipendentiAzienda1, this.handler.getDipendentiAzienda(this.azienda.getIdAzienda()));
        this.handler.aggiungiDipendente(this.azienda.getIdAzienda(), "sofia", "scattolini", "email", true);
        Set<Dipendente> dipendentiAzienda2= this.db.getDipendentiAzienda().get(azienda);
        assertEquals(dipendentiAzienda2, this.handler.getDipendentiAzienda(this.azienda.getIdAzienda()));
    }

    @Test
    public void testModificaDipendente() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaDipendente(-1, 1, "email", false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaDipendente(1, -1, "email", false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaDipendente(1, 1, "", false));
        assertTrue(this.handler.modificaDipendente(1, 1, "ciao", false));
        Dipendente dipendente1 = this.handler.getDipendenteById(this.azienda.getIdAzienda(), 1);
        assertEquals("ciao", dipendente1.getEmail());
        assertFalse(dipendente1.isRestrizioni());
        assertTrue(this.handler.modificaDipendente(1, 1,  "email", true));
        Dipendente dipendente2 = this.handler.getDipendenteById(this.azienda.getIdAzienda(), 1);
        assertEquals("email", dipendente2.getEmail());
        assertTrue(dipendente2.isRestrizioni());
    }

    @Test
    public void testRimuoviDpendente() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> this.handler.rimuoviDipendente(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> this.handler.rimuoviDipendente(1, -1));
        Dipendente dipendente1 = this.handler.getDipendenteById(this.azienda.getIdAzienda(), 1);
        Set<Dipendente> dipendenti = this.handler.getDipendentiAzienda(this.azienda.getIdAzienda());
        assertTrue(this.handler.rimuoviDipendente(this.azienda.getIdAzienda(), 1));
        assertFalse(dipendenti.contains(dipendente1));
        this.handler.aggiungiDipendente(this.azienda.getIdAzienda(), "test", "test", "email", false);
        Dipendente dipendente2 = this.handler.getDipendenteById(this.azienda.getIdAzienda(), 2);
        Set<Dipendente> dipendenti2= this.handler.getDipendentiAzienda(this.azienda.getIdAzienda());
        assertTrue(this.handler.rimuoviDipendente(this.azienda.getIdAzienda(), 2));
        assertFalse(dipendenti2.contains(dipendente2));
    }

    @Test
    public void testGetSpazioFedeta() {
        initTotal();
        SpazioFedelta spazioFedelta = this.handler.getSpazioFedeltaAzienda(this.azienda.getIdAzienda());
        assertNotNull(spazioFedelta);
        assertEquals(spazioFedelta, this.azienda.getSpazioFedelta());
    }

    @Test
    public void testModificaSpazioFedelta() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaSpazioFedelta(-1, "nome", "indirizzo", "numeroTelefono", "email"));
        SpazioFedelta spazioFedeltaUpdated = new SpazioFedelta("nome", "indirizzo", "numerotelefono", "email");
        assertTrue(this.handler.modificaSpazioFedelta(this.azienda.getIdAzienda(), "nome", "indirizzo", "numeroTelefono", "email"));
        assertEquals("nome", this.handler.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()).getNome());
        assertEquals("indirizzo", this.handler.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()).getIndirizzo());
        assertEquals("numeroTelefono", this.handler.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()).getNumeroTelefono());
        assertEquals("email", this.handler.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()).getEmail());
    }

    @Test
    public void testGetProgrammiFedeltaAzienda() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> this.handler.getProgrammiFedeltaAzienda(-1));
        Set<ProgrammaFedelta> programmiFedelta = this.handler.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        assertEquals(programmiFedelta, this.handler.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda()));
    }

    @Test
    public void testGetCatalogoPremiAzienda() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> this.handler.getCatalogoPremiAzienda(-1));
        Set<CatalogoPremi> catalogoPremi = this.handler.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        assertNotNull(catalogoPremi);
        assertEquals(catalogoPremi, this.handler.getCatalogoPremiAzienda(this.azienda.getIdAzienda()));
    }

    @Test
    public void testGetClientiAzienda() {
        initTotal();
    }

}
