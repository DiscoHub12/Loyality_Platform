package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerCliente;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class HandlerClienteTest {


    private Cliente cliente = new Cliente("Nome", "Cognome", "Telefono", "Email");

    private final Tessera tesseraCliente = new Tessera(this.cliente.getIdCliente());


    private final HandlerCliente handler = new HandlerCliente();

    private final DBMS dbms = DBMS.getInstance();

    void initDb(){
        this.dbms.addCliente(cliente);
        this.dbms.addTessera(tesseraCliente);
        //SMS CLIENTE :
        Set<SMS> smsCliente = new HashSet<>();
        SMS sms0 = new SMS("sms0");
        SMS sms1 = new SMS("sms1");
        SMS sms2 = new SMS("sms2");
        SMS sms3 = new SMS("sms3");
        smsCliente.add(sms0);
        smsCliente.add(sms1);
        smsCliente.add(sms2);
        smsCliente.add(sms3);
        this.dbms.getSMSCliente().put(cliente, smsCliente);
        //VISITE CLIENTE :
        Set<Visita> visiteCliente = new HashSet<>();
        Visita visita0 = new Visita("visita0", new Date());
        Visita visita1 = new Visita("visita1", new Date());
        Visita visita2 = new Visita("visita2", new Date());
        Visita visita3 = new Visita("visita3", new Date());
        visiteCliente.add(visita0);
        visiteCliente.add(visita1);
        visiteCliente.add(visita2);
        visiteCliente.add(visita3);
        this.dbms.getVisiteCliente().put(cliente, visiteCliente);
        //PREMI CLIENTE:
        Set<Premio> premiCliente = new HashSet<>();
        Premio premio0 = new Premio("premio0", false, 2);
        Premio premio1 = new Premio("premio1", false, 2);
        Premio premio2 = new Premio("premio2", false, 2);
        Premio premio3 = new Premio("premio3", false, 2);
        premiCliente.add(premio0);
        premiCliente.add(premio1);
        premiCliente.add(premio2);
        premiCliente.add(premio3);
        this.dbms.getPremiCliente().put(cliente, premiCliente);
        //COUPON CLIENTE :
        Set<Coupon> couponCliente = new HashSet<>();
        Coupon coupon0 = new Coupon(5, new Date());
        Coupon coupon1 = new Coupon(5, new Date());
        Coupon coupon2 = new Coupon(5, new Date());
        Coupon coupon3 = new Coupon(5, new Date());
        couponCliente.add(coupon0);
        couponCliente.add(coupon1);
        couponCliente.add(coupon2);
        couponCliente.add(coupon3);
        this.dbms.getCouponCliente().put(cliente, couponCliente);
    }


    @Test
    public void testIdrntificaCliente(){
        initDb();
        Cliente cliente = this.handler.identificaCliente("Nome", "Cognome");
        assertNotNull(cliente);
        assertEquals(this.cliente.getIdCliente(), cliente.getIdCliente());
        assertEquals("Nome", cliente.getNome());
        assertEquals("Cognome", cliente.getCognome());
    }

    @Test
    public void testIdentificaClienteTessera(){
        initDb();
        Cliente cliente = this.handler.identificaClienteTessera(this.tesseraCliente.getIdTessera());
        assertNotNull(cliente);
        assertEquals(this.cliente.getIdCliente(), cliente.getIdCliente());
        assertEquals("Nome", cliente.getNome());
        assertEquals("Cognome", cliente.getCognome());
    }

    @Test
    public void testIdrntificaClienteCodice(){
        initDb();
        Cliente cliente = this.handler.identificaClienteCodice(this.cliente.getIdCliente());
        assertNotNull(cliente);
        assertEquals(this.cliente.getIdCliente(), cliente.getIdCliente());
        assertEquals("Nome", cliente.getNome());
        assertEquals("Cognome", cliente.getCognome());
    }

    @Test
    public void testGetTesseraCliente(){
        initDb();
        Tessera tessera = this.handler.getTesseraCliente(this.cliente.getIdCliente());
        assertNotNull(tessera);
        assertEquals(1, tessera.getIdTessera());
        assertEquals(this.cliente.getIdCliente(), tessera.getIdCliente());
    }

    @Test
    public void testGetSMSCliente(){
        initDb();
        Set<SMS> smsCliente = this.handler.getSMSCliente(this.cliente.getIdCliente());
        assertNotNull(smsCliente);
        assertEquals(smsCliente, this.handler.getSMSCliente(this.cliente.getIdCliente()));
    }

    @Test
    public void testGetVisiteCliente(){
        initDb();
        Set<Visita> visiteCliente = this.handler.getVisiteCliente(this.cliente.getIdCliente());
        assertNotNull(visiteCliente);
    }

    @Test
    public void testGetPremiCliente(){
        initDb();
        //Todo implementare.
    }

    @Test
    public void testGetCouponCliente(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testConvalidaAcquisto(){
        initDb();
        //Todo implementare
    }

    @Test
    public void testConvalidaAcquistoTwo(){
        initDb();
        //Todo implementar
    }
}
