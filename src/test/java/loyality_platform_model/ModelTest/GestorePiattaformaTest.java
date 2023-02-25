package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.GestorePiattaforma;
import loyality_platform_model.Models.GestorePuntoVendita;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GestorePiattaformaTest {

    private final String nome ="Mario";

    private final String cognome ="Rossi";

    private final String email ="mario.rossi@gmmail.com";

    private final String password = "password";


    @Test
    public void testCostructor() {
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma("", "", "", ""));
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma(nome, "", "", ""));
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma("", cognome, "", ""));
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma("", "", email, ""));
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma(nome, cognome, "", ""));
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma(nome, "", email, ""));
        assertThrows(IllegalArgumentException.class, () -> new GestorePiattaforma("", cognome, email, password));
    }
    @Test
    public void testClass(){
        GestorePiattaforma gestorePiattaforma = new GestorePiattaforma(nome,cognome,email, password);
        assertEquals(1, gestorePiattaforma.getIdGestore());
        GestorePuntoVendita gestorePiattaforma1 = new GestorePuntoVendita("Mario1", "Rossi1", "aa@aa.it", "password");
        assertEquals(1, gestorePiattaforma1.getIdGestorePuntoVendita());
        System.out.println("IdGestorePiattaforma : "+ gestorePiattaforma.getIdGestore());
        System.out.println("IdGestorePiattaforma1: "+ gestorePiattaforma1.getIdGestorePuntoVendita());
        assertEquals("Mario", gestorePiattaforma.getNome());
        assertEquals("Rossi", gestorePiattaforma.getCognome());
        assertEquals("mario.rossi@gmmail.com", gestorePiattaforma.getEmail());
        assertEquals("password", gestorePiattaforma.getPassword());
        System.out.println(gestorePiattaforma);

        gestorePiattaforma.setEmail("mario.rossi@libbero.it");
        assertEquals("mario.rossi@libbero.it", gestorePiattaforma.getEmail());
        System.out.println(gestorePiattaforma);

        gestorePiattaforma.setPassword("passwordMario");
        assertEquals("passwordMario", gestorePiattaforma.getPassword());
        System.out.println(gestorePiattaforma);


        try{
            gestorePiattaforma.setEmail("");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            gestorePiattaforma.setPassword("");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
