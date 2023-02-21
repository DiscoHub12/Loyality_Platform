package loyality_platform_model.HandlerTest;
import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerVisite;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class HandlerVisiteTest {
    private final Visita visita= new Visita("Camerino","16/02/2023");
    private final Visita visita1 = new Visita("civitanova","17/02/2023");
    private final Cliente cliente= new Cliente("fabio","evangelista","1111","email");
    private final Cliente cliente1= new Cliente("carlo","evangelista","1211","Email");
    private final HandlerVisite handler = new HandlerVisite();
    private final DBMS db = DBMS.getInstance();
    public void init(){
        db.addCliente(cliente);
        db.addCliente(cliente1);
        db.addVisita(cliente.getIdCliente(), visita);
        db.addVisita(cliente.getIdCliente(), visita1);
        db.addVisita(cliente1.getIdCliente(), visita);
    }
    @Test
    public void testCreaVisita(){
        assertThrows(IllegalArgumentException.class, () -> handler.creaVisita("",""));
        Visita visita1= handler.creaVisita("Corridonia","16/02/2023");
        assertNotNull(visita1);
        assertEquals("Corridonia", visita1.getLuogo());
        assertEquals("16/02/2023", visita1.getData());
        Visita visita2 = handler.creaVisita("Camerino","12/02/2023");
        assertNotNull(visita2);
        assertEquals("Camerino", visita2.getLuogo());
        assertEquals("12/02/2023", visita2.getData());
    }

    @Test
    public void testAggiungiVisita(){
        this.handler.aggiungiVisita(1,"1/1/1","Civitanova");
        this.handler.aggiungiVisita(1,"1/2/1","Macerata");
        Set<Visita> visita1=this.db.getVisiteCliente().get(cliente);
        assertEquals(visita1,this.handler.getVisiteCliente(this.cliente.getIdCliente()));
        this.handler.aggiungiVisita(this.cliente.getIdCliente(),"1/3/1","Petriolo");
        Set<Visita> visita2=this.db.getVisiteCliente().get(cliente);
        assertEquals(visita2,this.handler.getVisiteCliente(this.cliente.getIdCliente()));
    }

    @Test
    public void testAggiungiVisitaGenerale() {
        init();
        Set<Cliente> clienti1 = db.getClientiIscritti();
        assertTrue(this.handler.aggiungiVisitaGenerale(clienti1, "mc", "1/1/1"));
        Set<Visita> visite1 = this.db.getVisiteCliente().get(cliente);
        Set<Visita> visite2 = this.db.getVisiteCliente().get(cliente1);
        assertEquals(3,visite1.size());
        assertEquals(2,visite2.size());
    }
    @Test
    public void testModificaVisita(){
        init();
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaVisita(-1, 1, "1/1/1", "mc", false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaVisita(1, -1, "1/1/1", "mc",false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaVisita(1, 1, "", "mc",false));
        assertThrows(IllegalArgumentException.class, () -> this.handler.modificaVisita(1, 1, "1/1/1", "",false));
        assertTrue(this.handler.modificaVisita(cliente.getIdCliente(), visita.getIdVisita(), "1/1/1", "mc",false));
        Visita newVisita = new Visita("mc","1/1/1");
        assertEquals(newVisita.getLuogo(),"mc");
        assertEquals(newVisita.getData(),"1/1/1");
    }

    @Test
    public void testRimuoviVisita(){
        init();
        assertThrows(IllegalArgumentException.class, () -> this.handler.rimuoviVisita(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> this.handler.rimuoviVisita(1, -1));
        Visita visita1 = this.handler.getVisitaById(this.cliente.getIdCliente(),1);
        Set<Visita> visite = this.handler.getVisiteCliente(this.cliente.getIdCliente());
        assertTrue(this.handler.rimuoviVisita(this.cliente.getIdCliente(), 1));
        assertFalse(visite.contains(visita1));
        this.handler.aggiungiVisita(this.cliente.getIdCliente(), "1/2/3","ciao");
        Visita visita2 = this.handler.getVisitaById(this.cliente.getIdCliente(), 2);
        Set<Visita> visite2= this.handler.getVisiteCliente(this.cliente.getIdCliente());
        assertTrue(this.handler.rimuoviVisita(this.cliente.getIdCliente(), 2));
        assertFalse(visite2.contains(visita2));
        }

    @Test
    public void testRimuoviVisitaGenerale(){
        init();
        Set<Cliente> clienti1 = db.getClientiIscritti();
        Set<Visita> visite1 = this.db.getVisiteCliente().get(cliente);
        assertEquals(2,visite1.size());
        Visita visitaDel = this.handler.getVisitaById(this.cliente.getIdCliente(),1);
        assertTrue(this.handler.rimuoviVisitaGenerale(clienti1,visitaDel));
        Set<Visita> visite2 = this.db.getVisiteCliente().get(cliente);
        assertEquals(1,visite2.size());
        }
}


