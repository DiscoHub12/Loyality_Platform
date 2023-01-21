package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.Reminder;
import loyality_platform_model.Models.Visita;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class VisitaTest {

    private String luogo = "Camerino";
    private Date data = new Date(2023, Calendar.JANUARY, 21);
    String ora = "14:00";

    @Test
    public void testCostructor(){
        assertThrows(NullPointerException.class, () -> new Visita("", null, ""));
        assertThrows(NullPointerException.class, () -> new Visita(luogo, null, ora));
        Visita visita = new Visita(luogo, data, ora);
    }

    @Test
    public void testGetIdVisita(){
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(1, visita.getIdVisita());
        Visita visita2 = new Visita(luogo, data, ora);
        assertEquals(2, visita.getIdVisita());
        Visita visita3 = new Visita(luogo, data, ora);
        assertEquals(3, visita.getIdVisita());
        Visita visita4 = new Visita(luogo, data, ora);
        assertEquals(4, visita.getIdVisita());
        Visita visita5 = new Visita(luogo, data, ora);
        assertEquals(5, visita.getIdVisita());
    }

    @Test
    public void testGetData(){
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(data, visita.getData());
        Date uno = new Date(2023, Calendar.JANUARY, 21);
        Visita visita2 = new Visita(luogo, uno, ora);
        assertEquals(uno, visita2.getData());
        Date due = new Date(2023, Calendar.JANUARY, 22);
        Visita visita3 = new Visita(luogo, due, ora);
        assertEquals(due, visita3.getData());
        Date tre = new Date(2023, Calendar.JANUARY, 23);
        Visita visita4 = new Visita(luogo, tre, ora);
        assertEquals(tre, visita4.getData());
    }

    @Test
    public void testSetData(){
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(data, visita.getData());
        Date uno = new Date(2023, Calendar.JANUARY, 21);
        visita.setData(uno);
        assertEquals(uno, visita.getData());
        Date due = new Date(2023, Calendar.JANUARY, 22);
        visita.setData(due);
        assertEquals(due, visita.getData());
        Date tre = new Date(2023, Calendar.JANUARY, 23);
        visita.setData(tre);
        assertEquals(tre, visita.getData());
    }

    @Test
    public void testGetLuogo(){
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(luogo, visita.getLuogo());
        Visita visita2 = new Visita("Luogo1", data, ora);
        assertEquals("Luogo1", visita2.getLuogo());
        Visita visita3 = new Visita("Luogo2", data, ora);
        assertEquals("Luogo2", visita3.getLuogo());
        Visita visita4 = new Visita("Luogo3", data, ora);
        assertEquals("Luogo3", visita4.getLuogo());
    }

    @Test
    public void testSetLuogo(){
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(luogo, visita.getLuogo());
        visita.setLuogo("Luogo1");
        assertEquals("Luogo1", visita.getLuogo());
        visita.setLuogo("Luogo2");
        assertEquals("Luogo2", visita.getLuogo());
        visita.setLuogo("Luogo3");
        assertEquals("Luogo3", visita.getLuogo());
        visita.setLuogo("Luogo4");
        assertEquals("Luogo4", visita.getLuogo());
    }

    @Test
    public void testGetOra() {
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(ora, visita.getOra());
        Visita visita2 = new Visita(luogo, data, "13:00");
        assertEquals("13:00", visita2.getOra());
        Visita visita3 = new Visita(luogo, data, "15:00");
        assertEquals("15:00", visita3.getOra());
        Visita visita4 = new Visita(luogo, data, "16:00");
        assertEquals("16:00", visita4.getOra());
        Visita visita5 = new Visita(luogo, data, "17:00");
        assertEquals("17:00", visita5.getOra());
    }

    @Test
    public void testSetOra(){
        Visita visita = new Visita(luogo, data, ora);
        assertEquals(ora, visita.getOra());
        visita.setOra("13:00");
        assertEquals("13:00", visita.getOra());
        visita.setOra("15:00");
        assertEquals("15:00", visita.getOra());
        visita.setOra("16:00");
        assertEquals("16:00", visita.getOra());
        visita.setOra("17:00");
        assertEquals("17:00", visita.getOra());
        visita.setOra("18:00");
        assertEquals("18:00", visita.getOra());
    }

    @Test
    public void testGetSetCompletata(){
        Visita visita = new Visita(luogo, data, ora);
        assertFalse(visita.isCompletata());
        visita.setCompletata(true);
        assertTrue(visita.isCompletata());
        visita.setCompletata(false);
        assertFalse(visita.isCompletata());
    }

    @Test
    public void testCreaReminder(){
        Visita visita = new Visita(luogo, data, ora);
        Reminder uno = visita.creaReminder("Reminder", data);
        Reminder due = visita.creaReminder("Reminder Uno", data);
        Reminder tre = visita.creaReminder("Reminder Tre", data);
    }
}
