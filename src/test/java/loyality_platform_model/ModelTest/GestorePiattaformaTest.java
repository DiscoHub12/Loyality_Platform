package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.GestorePiattaforma;
import loyality_platform_model.Models.GestorePuntoVendita;
import org.junit.jupiter.api.Test;

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
}
