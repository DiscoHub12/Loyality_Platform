package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerTessera;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


public class HandlerTesseraTest {

    private final HandlerTessera handlerTessera = new HandlerTessera();

    private final DBMS dbms = DBMS.getInstance();

    private final Cliente cliente = new Cliente("Mario", "Rossi", "0000", "aaaa@aaa", "password");


    public void init(){
        this.dbms.addCliente(cliente);
    }

    @Test
    public void testCreaTessera(){
        init();
        assertNull(cliente.getTessera());
        assertTrue(handlerTessera.creaTessera(cliente.getIdCliente()));
        System.out.println(dbms.getClientiIscritti());
        System.out.println(dbms.getTessereClienti());

        try {
            handlerTessera.creaTessera(0);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

     @Test
    public void testAddPuntiAcquistoPP(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
         dbms.addTessera(tessera);
         GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Neri", "mario", "password");
         SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "azienda", "00000", "emailAzienda");
         Azienda azienda = new Azienda(gestorePuntoVendita, spazioFedelta);
         ProgrammaFedelta programmaFedelta = new ProgrammaPunti("PP", "2022-02-02", 100, 80, 10, 10, null);
         dbms.addAzienda(azienda);
         dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta);
         tessera.addPogrammaFedelta(programmaFedelta);

         handlerTessera.addPuntiAcquisto(10, tessera, azienda);
         assertEquals(10, cliente.getTessera().getPunti());
         System.out.println(cliente.getTessera());
         handlerTessera.addPuntiAcquisto(15, tessera, azienda);
         System.out.println("Acquisto: " + cliente.getTessera());
         assertEquals(25, cliente.getTessera().getPunti());

    }


    @Test
    public void testAddPuntiAcquistoPPPL(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        dbms.addTessera(tessera);
        GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Neri", "mario", "password1");
        SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "azienda", "00000", "emailAzienda");
        Azienda azienda = new Azienda(gestorePuntoVendita, spazioFedelta);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 25);
        map.put(2, 40);
        map.put(3, 60);
        ProgrammaFedelta pp = new ProgrammaPunti("PP", "2022-02-02", 100, 80, 10, 10, null);
        ProgrammaFedelta programmaFedelta = new ProgrammaLivelli("PL", "2022-12-02", 3, 2, map, 10, 10,null );
        dbms.addAzienda(azienda);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), pp);
        tessera.addPogrammaFedelta(programmaFedelta);
        tessera.addPogrammaFedelta(pp);

        handlerTessera.addPuntiAcquisto(10, tessera, azienda);
        assertEquals(10, cliente.getTessera().getPunti());
        System.out.println(cliente.getTessera());
        handlerTessera.addPuntiAcquisto(15, tessera, azienda);
        assertEquals(25, cliente.getTessera().getPunti());
        assertEquals(1, cliente.getTessera().getLivelli());
        System.out.println("Acquisto: " + cliente.getTessera());

    }

    @Test
    public void testAddPuntiAcquistoPL(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        dbms.addTessera(tessera);
        GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Neri", "mario", "password2");
        SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "azienda", "00000", "emailAzienda");
        Azienda azienda = new Azienda(gestorePuntoVendita, spazioFedelta);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 25);
        map.put(2, 40);
        map.put(3, 60);
        ProgrammaFedelta programmaFedelta = new ProgrammaLivelli("PL", "2022-12-02", 3, 2, map, 10, 10,null );
        dbms.addAzienda(azienda);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta);
        tessera.addPogrammaFedelta(programmaFedelta);

        handlerTessera.addPuntiAcquisto(10, tessera, azienda);
        assertEquals(10, cliente.getTessera().getPunti());
        System.out.println(cliente.getTessera());
        handlerTessera.addPuntiAcquisto(15, tessera, azienda);
        assertEquals(25, cliente.getTessera().getPunti());
        assertEquals(1, cliente.getTessera().getLivelli());
        System.out.println("Acquisto: " + cliente.getTessera());

    }


    @Test
    public void testAddPuntiManuale(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        dbms.addTessera(tessera);
        assertTrue(handlerTessera.addPuntiManuale(5, 1));
        assertEquals(5, cliente.getTessera().getPunti());
        System.out.println(dbms.getClientiIscritti());
        System.out.println(cliente.getTessera());


        try {
            handlerTessera.addPuntiManuale(0,1);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            handlerTessera.addPuntiManuale(10,0);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddPuntiClienti(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        Cliente cliente1 = new Cliente("Mario", "Neri", "0000", "ciao", "password3");
        Tessera tessera1 = new Tessera(cliente1.getIdCliente());
        dbms.addTessera(tessera);
        dbms.addCliente(cliente1);
        dbms.addTessera(tessera1);
        Set<Integer> toAdd = new HashSet<>();
        toAdd.add(1);
        toAdd.add(2);
        assertTrue(handlerTessera.addPuntiClienti(toAdd, 10));
        assertEquals(10, cliente.getTessera().getPunti());
        assertEquals(10, cliente1.getTessera().getPunti());
        System.out.println(dbms.getTessereClienti());

        try {
            Set<Integer> tessere = new HashSet<>();
            tessere.add(1);
            tessere.add(0);
            handlerTessera.addPuntiClienti(tessere,1);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Set<Integer> tessere = new HashSet<>();
            tessere.add(1);
            tessere.add(2);
            handlerTessera.addPuntiClienti(tessere,0);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testRemovePuntiCliente(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        dbms.addTessera(tessera);
        tessera.addPunti(10);
        assertTrue(handlerTessera.removePuntiCliente(1, 2));
        assertEquals(8, cliente.getTessera().getPunti());
        System.out.println(cliente.getTessera());


        try {
            handlerTessera.removePuntiCliente(0,1);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            handlerTessera.removePuntiCliente(1,0);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testRemovePuntiClienti(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        Cliente cliente1 = new Cliente("Mario", "Neri", "0000", "ciao", "password");
        Tessera tessera1 = new Tessera(cliente1.getIdCliente());
        dbms.addTessera(tessera);
        dbms.addCliente(cliente1);
        dbms.addTessera(tessera1);
        tessera.addPunti(10);
        tessera1.addPunti(20);
        Set<Integer> toAdd = new HashSet<>();
        toAdd.add(1);
        toAdd.add(2);
        assertTrue(handlerTessera.removePuntiClienti(toAdd, 8));
        assertEquals(2, cliente.getTessera().getPunti());
        assertEquals(12, cliente1.getTessera().getPunti());
        System.out.println(dbms.getTessereClienti());

        try {
            Set<Integer> tessere = new HashSet<>();
            tessere.add(1);
            tessere.add(0);
            handlerTessera.removePuntiClienti(tessere,1);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Set<Integer> tessere = new HashSet<>();
            tessere.add(1);
            tessere.add(2);
            handlerTessera.removePuntiClienti(tessere,0);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddProgrammaFedelta(){
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        dbms.addTessera(tessera);
        GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Neri", "mario", "password4");
        SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "azienda", "00000", "emailAzienda");
        Azienda azienda = new Azienda(gestorePuntoVendita, spazioFedelta);
        ProgrammaFedelta programmaFedelta = new ProgrammaPunti("PP", "2022-02-02", 100, 80, 10, 10, null);
        dbms.addAzienda(azienda);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta);
        assertTrue(handlerTessera.addProgrammaFedelta(tessera.getIdTessera(), programmaFedelta));
        System.out.println(dbms.getTessereClienti());
        System.out.println(dbms.getCoalizione().getClientiIscrittiAProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta.getIdProgramma()));


        try {
            handlerTessera.addProgrammaFedelta(0,programmaFedelta);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try{
            handlerTessera.addProgrammaFedelta(1, null);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testDeleteProgrammaFedelta() {
        init();
        Tessera tessera = new Tessera(cliente.getIdCliente());
        dbms.addTessera(tessera);
        GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Neri", "mario", "password5");
        SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda", "azienda", "00000", "emailAzienda");
        Azienda azienda = new Azienda(gestorePuntoVendita, spazioFedelta);
        ProgrammaFedelta programmaFedelta = new ProgrammaPunti("PP", "2022-02-02", 100, 80, 10, 10, null);
        dbms.addAzienda(azienda);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta);
        tessera.addPogrammaFedelta(programmaFedelta);
        dbms.getCoalizione().addProgrammaFedelta(programmaFedelta);
        dbms.getCoalizione().addClienteCoalizione(programmaFedelta, cliente);
        System.out.println("Coalizione: " + dbms.getCoalizione().getClientiIscrittiProgramma(programmaFedelta.getIdProgramma()));
        System.out.println(dbms.getTessereClienti());
        assertTrue(handlerTessera.deleteProgrammaFedelta(tessera.getIdTessera(), programmaFedelta));
        System.out.println(dbms.getTessereClienti());
        System.out.println("Coalizione: " + dbms.getCoalizione().getClientiIscrittiProgramma(programmaFedelta.getIdProgramma()));


        try {
            handlerTessera.deleteProgrammaFedelta(0, programmaFedelta);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            handlerTessera.deleteProgrammaFedelta(1, null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
