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

    /**
     * AZIENDA 1:
     */
    private final Azienda azienda = new Azienda(new GestorePuntoVendita("Alessio", "Cognome1", "prova@gmail.com"), new SpazioFedelta("Azienda1", "Indirizzo1", "Numero1", "Email1"));
    private final SpazioFedelta spazio = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Dipendente dipendente = new Dipendente("Dipendente1", "Cognome1", "prova@gmail1.com", false);

    /**
     * PROGRAMMI FEDELTA PER TEST
     */

    private final Map<Integer, Integer> policy= new HashMap<>();
    private final ProgrammaFedelta programmaFedeltaPunti1 = new ProgrammaPunti("ProgrammaUno", 100, 2, 10, 20);
    private final ProgrammaFedelta programmaFedeltaPunti2 = new ProgrammaPunti("ProgrammaUno", 100, 2, 10, 20);
    private final ProgrammaFedelta programmaFedeltaPunti3 = new ProgrammaPunti("ProgrammaUno", 100, 2, 10, 20);
    private final ProgrammaFedelta programmaFedeltaLivelli1 = new ProgrammaLivelli("ProgrammaUno", 100, 2, policy, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli2 = new ProgrammaLivelli("ProgrammaUno", 100, 2, policy, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli3 = new ProgrammaLivelli("ProgrammaUno", 100, 2, policy, 2, 10);

    /**
     * PREMI PER TEST
     */
    private final Premio premio1 = new Premio("Premio1", false, 2);
    private final Premio premio2 = new Premio("Premio2", false, 2);
    private final Premio premio3 = new Premio("Premio3", false, 2);
    private final Premio premio4 = new Premio("Premio4", false, 2);
    private final Set<Premio> premi = new HashSet<>();
    private final Set<CatalogoPremi> cataloghi = new HashSet<>();

    /**
     * HANDLERAZIENDA E DBMS
     */
    private final HandlerAzienda handler = new HandlerAzienda();
    private final DBMS db = DBMS.getInstance();


    public void initTotal() {
        System.out.println("Azienda numero 1 : " + this.azienda.getIdAzienda());
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
        azienda.setSpazioFedelta(spazio);
        azienda.setCatalogoPremi(this.cataloghi);
        db.addAzienda(azienda);
        db.addDipendente(azienda, dipendente);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti1);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti2);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti3);
        db.addProgrammaAzienda(azienda, programmaFedeltaLivelli1);
        db.addProgrammaAzienda(azienda, programmaFedeltaLivelli2);
        db.addProgrammaAzienda(azienda, programmaFedeltaLivelli3);

    }

    @Test
    public void testGetTitolareAzienda() {
        initTotal();
        GestorePuntoVendita gestore1 = this.handler.getTitolareAzienda(1);
        assertNotNull(gestore1);
        assertEquals(gestore1, this.handler.getTitolareAzienda(1));
        GestorePuntoVendita gestore2 = this.handler.getTitolareAzienda(2);
        assertNotNull(gestore2);
        assertEquals(gestore2, this.handler.getTitolareAzienda(2));
        GestorePuntoVendita gestore3 = this.handler.getTitolareAzienda(3);
        assertNotNull(gestore3);
        assertEquals(gestore3, this.handler.getTitolareAzienda(3));
        assertThrows(IllegalArgumentException.class, () -> this.handler.getTitolareAzienda(0));
    }

    @Test
    public void testGetDipendentiAzienda() {
        initTotal();
        Set<Dipendente> dipendenti = handler.getDipendentiAzienda(1);
        assertNotNull(dipendenti);
        assertEquals(dipendenti, this.db.getDipendentiAzienda().get(azienda));
        Set<Dipendente> dipendenti1 = handler.getDipendentiAzienda(2);
        assertNotNull(dipendenti1);
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
    }

    @Test
    public void testAggiungiDipendente() {
        initTotal();
        handler.aggiungiDipendente(1, "Nome", "Cognome", "Email", false);
        handler.aggiungiDipendente(1, "Alessio", "Cognome1", "email", false);
        Set<Dipendente> dipendentiAzienda1 = this.db.getDipendentiAzienda().get(azienda);
        assertEquals(2, dipendentiAzienda1.size());
    }

    @Test
    public void testModificaDipendente() {
        initTotal();
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaDipendente(-1, 1, handler.getTitolareAzienda(1), "email", false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaDipendente(1, -1, handler.getTitolareAzienda(1), "email", false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaDipendente(1, 1, handler.getTitolareAzienda(1), "", false));
        assertTrue(this.handler.modificaDipendente(1, 1, this.handler.getTitolareAzienda(1), "ciao", false));
        Set<Dipendente> dipendenti = this.handler.getDipendentiAzienda(azienda.getTitolare().getIdGestorePuntoVendita());
        for(Dipendente dipendente : dipendenti){
            if(dipendente.getIdDipendente() == 1){
                assertEquals("ciao", dipendente.getEmail());
                assertFalse(dipendente.isRestrizioni());
            }
        }
    }

    @Test
    public void testRimuoviDpendente() {
        initTotal();
        //Todo implementare
    }

    @Test
    public void testGetSpazioFedeta() {
        initTotal();
        //Todo implementare
    }

    @Test
    public void testModificaSpazioFedelta() {
        initTotal();
        //Todo implementare
    }

    @Test
    public void testGetProgrammiFedeltaAzienda() {
        initTotal();
        //Todo implementare
    }

    @Test
    public void testGetCatalogoPremiAzienda() {
        initTotal();
        //Todo implementare
    }

    @Test
    public void testGetClientiAzienda() {
        initTotal();
        //Todo implementare
    }

}
