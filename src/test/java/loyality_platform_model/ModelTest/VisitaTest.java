package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.Visita;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisitaTest {

    private final String luogo = "Camerino";
    private final String data="20/01/2023";

    @Test
    public void testCostructor(){
        assertThrows(IllegalArgumentException.class, () -> new Visita("", ""));
        assertThrows(IllegalArgumentException.class, () -> new Visita(luogo , null));
        assertThrows(IllegalArgumentException.class, () -> new Visita("", data));
    }

    @Test
    public void testGetIdVisita(){
        Visita visita = new Visita(luogo, data);
        assertEquals(1, visita.getIdVisita());
        Visita visita2 = new Visita(luogo, data);
        assertEquals(2, visita2.getIdVisita());
        Visita visita3 = new Visita(luogo, data);
        assertEquals(3, visita3.getIdVisita());
        Visita visita4 = new Visita(luogo, data);
        assertEquals(4, visita4.getIdVisita());
        Visita visita5 = new Visita(luogo, data);
        assertEquals(5, visita5.getIdVisita());
    }

    @Test
    public void testGetData(){
        Visita visita = new Visita(luogo, data);
        assertEquals(data, visita.getData());
        String data1= "21/01/2023";
        Visita visita2 = new Visita(luogo, data1);
        assertEquals(data1, visita2.getData());
        String data2= "22/01/2023";
        Visita visita3 = new Visita(luogo, data2);
        assertEquals(data2, visita3.getData());
        String data3="23/01/2023";
        Visita visita4 = new Visita(luogo, data3);
        assertEquals(data3, visita4.getData());
    }

    @Test
    public void testSetData(){
        Visita visita = new Visita(luogo, data);
        assertEquals(data, visita.getData());
        String data1= "21/01/2023";
        visita.setData(data1);
        assertEquals(data1, visita.getData());
        String data2= "22/01/2023";
        visita.setData(data2);
        assertEquals(data2, visita.getData());
        String data3="23/01/2023";
        visita.setData(data3);
        assertEquals(data3, visita.getData());
    }

    @Test
    public void testGetLuogo(){
        Visita visita = new Visita(luogo, data);
        assertEquals(luogo, visita.getLuogo());
        Visita visita2 = new Visita("Luogo1", data);
        assertEquals("Luogo1", visita2.getLuogo());
        Visita visita3 = new Visita("Luogo2", data);
        assertEquals("Luogo2", visita3.getLuogo());
        Visita visita4 = new Visita("Luogo3", data);
        assertEquals("Luogo3", visita4.getLuogo());
    }

    @Test
    public void testSetLuogo(){
        Visita visita = new Visita(luogo, data);
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
    public void testGetSetCompletata(){
        Visita visita = new Visita(luogo, data);
        assertFalse(visita.isCompletata());
        visita.setCompletata(true);
        assertTrue(visita.isCompletata());
        visita.setCompletata(false);
        assertFalse(visita.isCompletata());
    }

}
