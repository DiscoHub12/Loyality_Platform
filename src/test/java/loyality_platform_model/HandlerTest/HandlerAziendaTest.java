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
     * AZIENDA 0:
     */
    private final Azienda azienda = new Azienda(new GestorePuntoVendita("Alessio", "Cognome1", "prova@gmail.com"), new SpazioFedelta("Azienda1", "Indirizzo1", "Numero1", "Email1"));
    private final SpazioFedelta spazio = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Dipendente dipendente = new Dipendente("Dipendente1", "Cognome1", "prova@gmail1.com", false);

    /**
     * AZIENDA 1
     */
    private final Azienda azienda1 = new Azienda(new GestorePuntoVendita("Luca", "Cognome2", "prova1@gmail.com"), new SpazioFedelta("Azienda2", "Indirizzo2", "Numero2", "Email2"));
    private final SpazioFedelta spazio1 = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Dipendente dipendente1 = new Dipendente("Dipendente2", "Cognome2", "prova@gmail2.com", false);

    /**
     * AZIENDA 2
     */
    private final Azienda azienda2 = new Azienda(new GestorePuntoVendita("Marco", "Cognome3", "prova2@gmail.com"), new SpazioFedelta("Azienda3", "Indirizzo3", "Numero3", "Email3"));
    private final SpazioFedelta spazio2 = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Dipendente dipendente2 = new Dipendente("Dipendente3", "Cognome3", "prova@gmail3.com", false);

    /**
     * AZIENDA 3
     */
    private final Azienda azienda3 = new Azienda(new GestorePuntoVendita("Sofia", "Cognome4", "prova3@gmail.com"), new SpazioFedelta("Azienda4", "Indirizzo4", "Numero4", "Email4"));
    private final SpazioFedelta spazio3 = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Dipendente dipendente3 = new Dipendente("Dipendente4", "Cognome4", "prova@gmail4.com", false);

    /**
     * PROGRAMMI FEDELTA PER TEST
     */
    private final Map<Integer, Integer> policy= new HashMap<>();
    private final ProgrammaFedelta programmaFedeltaPunti = new ProgrammaPunti("ProgrammaUno", 100, 2, 10);
    private final ProgrammaFedelta programmaFedeltaPunti1 = new ProgrammaPunti("ProgrammaUno", 100, 2, 10);
    private final ProgrammaFedelta programmaFedeltaPunti2 = new ProgrammaPunti("ProgrammaUno", 100, 2, 10);
    private final ProgrammaFedelta programmaFedeltaPunti3 = new ProgrammaPunti("ProgrammaUno", 100, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli1 = new ProgrammaLivelli("ProgrammaUno", 100, 2, policy, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli2 = new ProgrammaLivelli("ProgrammaUno", 100, 2, policy, 2, 10);
    private final ProgrammaFedelta programmaFedeltaLivelli3 = new ProgrammaLivelli("ProgrammaUno", 100, 2, policy, 2, 10);

    /**
     * PREMI PER TEST
     */
    private final Premio premio = new Premio("Premio", false, 2);
    private final Premio premio1 = new Premio("Premio1", false, 2);
    private final Premio premio2 = new Premio("Premio2", false, 2);
    private final Premio premio3 = new Premio("Premio3", false, 2);
    private final Premio premio4 = new Premio("Premio4", false, 2);
    private final Set<Premio> premi = new HashSet<>();
    private final CatalogoPremi catalogo = new CatalogoPremi(premi);
    private final Set<CatalogoPremi> cataloghi = new HashSet<>();

    /**
     * HANDLERAZIENDA E DBMS
     */
    private final HandlerAzienda handler = new HandlerAzienda();
    private final DBMS db = DBMS.getInstance();


    public void initTotal() {
        System.out.println("Azienda numero 1 : " + this.azienda.getIdAzienda());
        System.out.println("Azienda numero 2 : " + this.azienda1.getIdAzienda());
        System.out.println("Azienda numero 3 : " + this.azienda2.getIdAzienda());
        System.out.println("Azienda numero 4 : " + this.azienda3.getIdAzienda());
        this.catalogo.setPremiCatalogo(premi);
        Set<CatalogoPremi> cataloghi = new HashSet<>();
        cataloghi.add(catalogo);
        //Azienda 1
        azienda.setSpazioFedelta(spazio);
        azienda.setCatalogoPremi(this.cataloghi);
        db.addAzienda(azienda);
        db.addDipendente(azienda, dipendente);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti1);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti2);
        db.addProgrammaAzienda(azienda, programmaFedeltaPunti3);
        db.addProgrammaAzienda(azienda, programmaFedeltaLivelli1);
        db.addProgrammaAzienda(azienda, programmaFedeltaLivelli2);
        db.addProgrammaAzienda(azienda, programmaFedeltaLivelli3);
        //Azienda 2
        azienda1.setSpazioFedelta(spazio1);
        azienda1.setCatalogoPremi(this.cataloghi);
        db.addAzienda(azienda1);
        db.addDipendente(azienda1, dipendente1);
        db.addProgrammaAzienda(azienda1, programmaFedeltaPunti);
        db.addProgrammaAzienda(azienda1, programmaFedeltaPunti1);
        db.addProgrammaAzienda(azienda1, programmaFedeltaPunti2);
        db.addProgrammaAzienda(azienda1, programmaFedeltaPunti3);
        db.addProgrammaAzienda(azienda1, programmaFedeltaLivelli1);
        db.addProgrammaAzienda(azienda1, programmaFedeltaLivelli2);
        db.addProgrammaAzienda(azienda1, programmaFedeltaLivelli3);
        //Azienda 3
        azienda2.setSpazioFedelta(spazio2);
        azienda2.setCatalogoPremi(this.cataloghi);
        db.addAzienda(azienda2);
        db.addDipendente(azienda2, dipendente2);
        db.addProgrammaAzienda(azienda2, programmaFedeltaPunti);
        db.addProgrammaAzienda(azienda2, programmaFedeltaPunti1);
        db.addProgrammaAzienda(azienda2, programmaFedeltaPunti2);
        db.addProgrammaAzienda(azienda2, programmaFedeltaPunti3);
        db.addProgrammaAzienda(azienda2, programmaFedeltaLivelli1);
        db.addProgrammaAzienda(azienda2, programmaFedeltaLivelli2);
        db.addProgrammaAzienda(azienda2, programmaFedeltaLivelli3);
        //Azienda 4
        azienda3.setSpazioFedelta(spazio3);
        azienda3.setCatalogoPremi(this.cataloghi);
        db.addAzienda(azienda3);
        db.addDipendente(azienda3, dipendente3);
        db.addProgrammaAzienda(azienda3, programmaFedeltaPunti);
        db.addProgrammaAzienda(azienda3, programmaFedeltaPunti1);
        db.addProgrammaAzienda(azienda3, programmaFedeltaPunti2);
        db.addProgrammaAzienda(azienda3, programmaFedeltaPunti3);
        db.addProgrammaAzienda(azienda3, programmaFedeltaLivelli1);
        db.addProgrammaAzienda(azienda3, programmaFedeltaLivelli2);
        db.addProgrammaAzienda(azienda3, programmaFedeltaLivelli3);

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
        assertEquals(dipendenti1, this.db.getDipendentiAzienda().get(azienda1));
        Set<Dipendente> dipendenti2 = handler.getDipendentiAzienda(3);
        assertNotNull(dipendenti2);
        assertEquals(dipendenti2, this.db.getDipendentiAzienda().get(azienda2));
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
        Set<Dipendente> dipendentiAzienda1 = this.db.getDipendentiAzienda().get(azienda);
        for(Dipendente dipendente : dipendentiAzienda1){
            assertSame("Nome", dipendente.getNome());
        }
    }

    @Test
    public void testModificaDipendente() {
        initTotal();
        //Todo implementare
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
