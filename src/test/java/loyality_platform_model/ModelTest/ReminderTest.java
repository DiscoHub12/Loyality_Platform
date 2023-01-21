package loyality_platform_model.ModelTest;
import loyality_platform_model.Handler.HandlerMessaggi;
import loyality_platform_model.Models.Reminder;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * IMPLEMENTED BY : Alessio Giacché.
 */


public class ReminderTest {

    private Date date = new Date(2023, Calendar.JANUARY, 21);

    private final HandlerMessaggi gestoreMessaggi = new HandlerMessaggi();

    private final String nomeVisita = "Reminder Test";

    @Test
    public void testCostructor(){
        assertThrows(NullPointerException.class, () -> new Reminder(nomeVisita, null));
        Reminder reminder = new Reminder(nomeVisita, date);
    }

    @Test
    public void testGetNome(){
        Reminder reminder = new Reminder(nomeVisita, date);
        assertEquals("Reminder Test", reminder.getNomeVisita());
        String nomeVisita1 = "Reminder Test 1";
        Reminder reminder1 = new Reminder(nomeVisita1, date);
        assertEquals("Reminder Test 1", reminder1.getNomeVisita());
        String nomeVisita2 = "Reminder Test 2";
        Reminder reminder2 = new Reminder(nomeVisita2, date);
        assertEquals("Reminder Test 2", reminder2.getNomeVisita());
        String nomeVisita3 = "Reminder Test 3";
        Reminder reminder3 = new Reminder(nomeVisita3, date);
        assertEquals("Reminder Test 3", reminder3.getNomeVisita());
        String nomeVisita4 = "Reminder Test 4";
        Reminder reminder4 = new Reminder(nomeVisita4, date);
        assertEquals("Reminder Test 4", reminder4.getNomeVisita());
    }

    @Test
    public void testSetNome(){
        Reminder reminder = new Reminder(nomeVisita, date);
        assertEquals("Reminder Test", reminder.getNomeVisita());
        reminder.setNomeVisita("Reminder Test 2");
        assertEquals("Reminder Test 2", reminder.getNomeVisita());
        reminder.setNomeVisita("Reminder Test 3");
        assertEquals("Reminder Test 3", reminder.getNomeVisita());
        reminder.setNomeVisita("Reminder Test 4");
        assertEquals("Reminder Test 4", reminder.getNomeVisita());
        reminder.setNomeVisita("Reminder Test 5");
        assertEquals("Reminder Test 5", reminder.getNomeVisita());

    }

    @Test
    public void testGetData(){
        Reminder reminder = new Reminder(nomeVisita, date);
        assertEquals(date, reminder.getData());
        Date uno = new Date(2023, Calendar.JANUARY, 21);
        Reminder reminder2 = new Reminder(nomeVisita, uno);
        assertEquals(uno, reminder2.getData());
        Date due = new Date(2023, Calendar.JANUARY, 22);
        Reminder reminder3 = new Reminder(nomeVisita, due);
        assertEquals(due, reminder3.getData());
        Date tre = new Date(2023, Calendar.JANUARY, 23);
        Reminder reminder4 = new Reminder(nomeVisita, tre);
        assertEquals(tre, reminder4.getData());
    }

    @Test
    public void testSetData(){
        Reminder reminder = new Reminder(nomeVisita, date);
        assertEquals(date, reminder.getData());
        Date uno = new Date(2023, Calendar.JANUARY, 21);
        reminder.setData(uno);
        assertEquals(uno, reminder.getData());
        Date due = new Date(2023, Calendar.JANUARY, 22);
        reminder.setData(due);
        assertEquals(due, reminder.getData());
        Date tre = new Date(2023, Calendar.JANUARY, 23);
        reminder.setData(tre);
        assertEquals(tre, reminder.getData());
    }

    @Test
    public void testCreaMessaggio(){
        //Todo implementare, manca HandlerMessaggi.
    }

    @Test
    public void testInviaNotifica(){
        //Todo implementare, manca HandlerMessaggi.
    }
}
