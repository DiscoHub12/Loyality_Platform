package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.GestorePiattaforma;
import loyality_platform_model.Models.GestorePuntoVendita;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GestorePiattaformaTest {

    private String nome ="Mario";

    private String cognome ="Rossi";

    private String email ="mario.rossi@gmmail.com";

    private GestorePiattaforma gestorePiattaforma;


    @Test
    public void testCostructor() {
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma("", "", ""));
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma(nome, "", ""));
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma("", cognome, ""));
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma("", "", email));
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma(nome, cognome, ""));
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma(nome, "", email));
        assertThrows(NullPointerException.class, () -> new GestorePiattaforma("", cognome, email));
        gestorePiattaforma = new GestorePiattaforma(nome, cognome, email);
    }
    @Test
    public void testClass(){
        GestorePiattaforma gestorePiattaforma = new GestorePiattaforma(nome,cognome,email);
        assertEquals(0, gestorePiattaforma.getIdGestore());
        GestorePuntoVendita gestorePiattaforma1 = new GestorePuntoVendita("Mario1", "Rossi1", "aa@aa.it");
        assertEquals(1, gestorePiattaforma1.getIdGestorePuntoVendita());
        System.out.println("IdGestorePiattaforma : "+ gestorePiattaforma.getIdGestore());
        System.out.println("IdGestorePiattaforma1: "+ gestorePiattaforma1.getIdGestorePuntoVendita());
        assertEquals("Mario", gestorePiattaforma.getNome());
        assertEquals("Rossi", gestorePiattaforma.getCognome());
        assertEquals("mario.ross@gmmail.com", gestorePiattaforma.getEmail());
        System.out.println(gestorePiattaforma);

        gestorePiattaforma.setEmail("mario.rossi@libbero.it");
        assertEquals("mario.rossi@libbero.it", gestorePiattaforma.getEmail());
        System.out.println(gestorePiattaforma);


        try{
            gestorePiattaforma.setEmail("");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
