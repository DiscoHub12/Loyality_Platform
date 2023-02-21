package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerProgrammaFedelta;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class HandlerProgrammaFedeltaTest {

    private final DBMS dbms = DBMS.getInstance();

    private final HandlerProgrammaFedelta handlerProgrammaFedelta = new HandlerProgrammaFedelta();

    private final GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Rossi", "mario.rossi@gmmail.com", "password");

    private final SpazioFedelta spazioFedelta = new SpazioFedelta("Azienda1", "c.da azienda", "0000", "azienda@gmmail.com");

    private final Azienda azienda = new Azienda(gestorePuntoVendita, spazioFedelta);

    private final ProgrammaFedelta programmaFedelta1 = new ProgrammaPunti("PP", "2022-02-13", 100, 80, 10, 10, null);

    private final Map<Integer, Integer> map = new HashMap<>();


    private final ProgrammaFedelta programmaFedelta2 = new ProgrammaLivelli("PL", "2022-04-12", 3, 2, map, 10, 10, null);


    public void initTotal(){
        dbms.addAzienda(azienda);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta1);
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 40);
        dbms.addProgrammaAzienda(azienda.getIdAzienda(), programmaFedelta2);
        System.out.println(dbms.getProgrammaFedeltaById(azienda.getIdAzienda(), programmaFedelta1.getIdProgramma()));
        System.out.println(dbms.getAziendeIscritte().toString());
        System.out.println(dbms.getProgrammiAzienda());

    }

    @Test
    public void testGestProgrammaFedeltaById(){
        initTotal();
        ProgrammaFedelta toReturn = handlerProgrammaFedelta.getProgrammaFedeltaById(azienda.getIdAzienda(), programmaFedelta1.getIdProgramma());
        System.out.println("Programma Punti trovato" + toReturn);
        assertEquals(programmaFedelta1, toReturn);

        ProgrammaFedelta toReturn2 = handlerProgrammaFedelta.getProgrammaFedeltaById(azienda.getIdAzienda(), programmaFedelta2.getIdProgramma());
        System.out.println("Programma Livelli trovato " + toReturn2);
        assertEquals(programmaFedelta2, toReturn2);

        try {
            handlerProgrammaFedelta.getProgrammaFedeltaById(0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetDetailsProgrammaFedelta(){
        initTotal();
        assertEquals(programmaFedelta1.toString(), handlerProgrammaFedelta.getDetailsProgrammaFedelta(azienda.getIdAzienda(), programmaFedelta1.getProgrammaPunti().getIdProgramma()));

        try {
            handlerProgrammaFedelta.getDetailsProgrammaFedelta(0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAggiungiProgrammaPunti(){
        initTotal();
        assertTrue(handlerProgrammaFedelta.aggiungiProgrammaPunti(azienda.getIdAzienda(),"Program", "2022-07-02",0,90,5, 10, null));
        System.out.println("Add programma: "+ dbms.getProgrammiAzienda());
        assertFalse(handlerProgrammaFedelta.aggiungiProgrammaPunti(4,"ProvaFalse","2022-01-02",0, 70, 10, 10, null));

        try {
            handlerProgrammaFedelta.aggiungiProgrammaPunti(0,"Prova", "",0,70,10, 10, null);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAggiungiProgrammaLivelli(){
        initTotal();
        Map<Integer, Integer> policy = new HashMap<>();
        policy.put(1, 10);
        policy.put(2, 20);
        policy.put(3, 40);
        policy.put(4, 60);
        policy.put(5, 80);
        assertTrue(handlerProgrammaFedelta.aggiungiProgrammaLivelli(azienda.getIdAzienda(), "ProgramL", "2022-02-15", 5, 4, policy, 10, 10, null));
        System.out.println("Add program : " + dbms.getProgrammiAzienda().get(azienda));
        assertFalse(handlerProgrammaFedelta.aggiungiProgrammaLivelli(4,"ProvaFalse","2022-01-02",5, 3, policy,10, 10, null));

        System.out.println("Programmi totali :" + this.dbms.getProgrammiAzienda().get(azienda));
        ProgrammaFedelta toReturn = handlerProgrammaFedelta.getProgrammaFedeltaById(azienda.getIdAzienda(), 2);
        System.out.println("Programma trovato" + toReturn);

    }


    @Test
    public void testModificaProgrammaPunti(){
        initTotal();
        assertTrue(handlerProgrammaFedelta.modificaProgrammaPunti(azienda.getIdAzienda(), 1, "Program", 0, 80, 5, 10));
        System.out.println("Modifica programma: " + dbms.getProgrammiAzienda().get(azienda));
    }

    @Test
    public void testModificaProgrammaLivelli(){
        initTotal();
        map.put(4, 55);
        assertTrue(handlerProgrammaFedelta.modificaProgrammaLivelli(azienda.getIdAzienda(), 2, "PL", 4, 2, map, 10, 10));
        System.out.println("Modifica programma: " + dbms.getProgrammiAzienda().get(azienda));
    }

    @Test
    public void testRimuoviProgrammaPunti(){
        initTotal();
        assertTrue(handlerProgrammaFedelta.rimuoviProgrammaFedelta(azienda.getIdAzienda(), 2));
        System.out.println("Programma rimosso" + dbms.getProgrammiAzienda().get(azienda));
    }

}

