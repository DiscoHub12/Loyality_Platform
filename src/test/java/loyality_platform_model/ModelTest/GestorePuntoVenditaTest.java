package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.GestorePuntoVendita;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorePuntoVenditaTest {

    @Test
    public void testClass(){
        GestorePuntoVendita gestorePuntoVendita = new GestorePuntoVendita("Mario", "Rossi", "mario.ross@gmmail.com");
        assertEquals(0, gestorePuntoVendita.getIdGestorePuntoVendita());
        GestorePuntoVendita gestorePuntoVendita1 = new GestorePuntoVendita("Mario1", "Rossi1", "aa@aa.it");
        assertEquals(1, gestorePuntoVendita1.getIdGestorePuntoVendita());
        System.out.println("IdGestore : "+ gestorePuntoVendita.getIdGestorePuntoVendita());
        System.out.println("IdGestore1: "+ gestorePuntoVendita1.getIdGestorePuntoVendita());
        assertEquals("Mario", gestorePuntoVendita.getNome());
        assertEquals("Rossi", gestorePuntoVendita.getCognome());
        assertEquals("mario.ross@gmmail.com", gestorePuntoVendita.getEmail());
        System.out.println(gestorePuntoVendita);

        gestorePuntoVendita.setEmail("mario.rossi@libbero.it");
        assertEquals("mario.rossi@libbero.it", gestorePuntoVendita.getEmail());
        System.out.println(gestorePuntoVendita);


        try{
            gestorePuntoVendita.setEmail("");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
