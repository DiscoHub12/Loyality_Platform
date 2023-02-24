package loyality_platform_model.HandlerTest;
import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.HandlerMessaggi;
import loyality_platform_model.Handler.HandlerVisite;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class HandlerMessaggiTest {
    private final DBMS db = DBMS.getInstance();
    private final Cliente cliente= new Cliente("fabio","evangelista","1111","email","1234");
    private final Cliente cliente1= new Cliente("carlo","evangelista","1211","Email","123");
    private final HandlerMessaggi handler = new HandlerMessaggi();
    private final SMS sms= new SMS("ciao");
    private final GestorePuntoVendita titolare = new GestorePuntoVendita("Nome", "Cognome", "prova@gmail.com", "password");
    private final SpazioFedelta spazio = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Azienda azienda = new Azienda(titolare,spazio);

    public void init(){
        db.addCliente(cliente);
        db.addCliente(cliente1);
        db.addAzienda(azienda);
    }
    @Test
    public void testInviaSMS(){
        init();
        assertThrows(IllegalArgumentException.class, () -> handler.inviaSMS(1,""));
        assertTrue(this.handler.inviaSMS(cliente.getIdCliente(),"prova"));
        Set<SMS> smss=db.getSMSClienteById(cliente.getIdCliente());
        assertEquals(1,smss.size());
        this.handler.inviaSMS(cliente.getIdCliente(),"prova2");
        Set<SMS> smss1=db.getSMSClienteById(cliente.getIdCliente());
        assertEquals(2,smss1.size());
    }
    @Test
    public void testInviaSMSGenerale(){
        init();
        Set<Cliente> clienti=db.getClientiIscritti();
        assertThrows(IllegalArgumentException.class, () -> handler.inviaSmsGenerale("",clienti));
        assertTrue(this.handler.inviaSmsGenerale("prova1",clienti));
        Set<SMS> sms1=db.getSMSClienteById(cliente.getIdCliente());
        Set<SMS> sms2=db.getSMSClienteById(cliente1.getIdCliente());
        assertEquals(1,sms1.size());
        assertEquals(1,sms2.size());
        assertTrue(this.handler.inviaSmsGenerale("prova2",clienti));
        Set<SMS> sms3=db.getSMSClienteById(cliente.getIdCliente());
        Set<SMS> sms4=db.getSMSClienteById(cliente1.getIdCliente());
        assertEquals(2,sms3.size());
        assertEquals(2,sms4.size());
    }
    @Test
    public void testInviaSMSPreconfigurato(){
        init();
        ConfigurazioneSMS newConfig= new ConfigurazioneSMS("prova");
        assertThrows(IllegalArgumentException.class, () -> handler.inviaSMSPreConfigurato(1,null));
        assertThrows(IllegalArgumentException.class, () -> handler.inviaSMSPreConfigurato(-1,newConfig));
        ConfigurazioneSMS newConfig2= new ConfigurazioneSMS("prova2");
        assertTrue(this.handler.inviaSMSPreConfigurato(cliente.getIdCliente(),newConfig));
        Set<SMS> smss=db.getSMSCliente().get(cliente);
        assertEquals(1,smss.size());
        assertTrue(this.handler.inviaSMSPreConfigurato(cliente.getIdCliente(),newConfig2));
        Set<SMS> smss1=db.getSMSCliente().get(cliente);
        assertEquals(2,smss1.size());

    }
    @Test
    public void testInviaSMSPreconfiguratoGenerale(){
        init();
        Set<Cliente> clienti=db.getClientiIscritti();
        ConfigurazioneSMS newConfig= new ConfigurazioneSMS("prova");
        ConfigurazioneSMS newConfig2= new ConfigurazioneSMS("prova2");
        assertThrows(IllegalArgumentException.class, () -> handler.inviaSMSPreconfiguratoGenerale(clienti,null));
        assertTrue(this.handler.inviaSMSPreconfiguratoGenerale(clienti,newConfig));
        Set<SMS> smss=db.getSMSCliente().get(cliente);
        Set<SMS> smss1=db.getSMSCliente().get(cliente1);
        assertEquals(1,smss.size());
        assertEquals(1,smss1.size());
        handler.inviaSMS(cliente.getIdCliente(), "ciao");
        Set<SMS> smss2=db.getSMSCliente().get(cliente);
        Set<SMS> smss3=db.getSMSCliente().get(cliente1);
        assertEquals(2,smss2.size());
        assertEquals(1,smss3.size());
        assertTrue(this.handler.inviaSMSPreconfiguratoGenerale(clienti,newConfig2));
        Set<SMS> smss4=db.getSMSCliente().get(cliente);
        Set<SMS> smss5=db.getSMSCliente().get(cliente1);
        assertEquals(3,smss4.size());
        assertEquals(2,smss5.size());
    }
    @Test
    public void testAggiungiConfigurazioneSMS(){
        init();
        assertThrows(IllegalArgumentException.class, () -> handler.aggiungiConfigurazioneSMS(-1,"testo"));
        assertThrows(IllegalArgumentException.class, () -> handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), ""));
        handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), "configurazione");
        Set<SMS> configurazioni=db.getSMSPreconfigurati(azienda.getIdAzienda());
        assertEquals(1,configurazioni.size());
        handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), "configurazione1");
        Set<SMS> configurazioni1=db.getSMSPreconfigurati(azienda.getIdAzienda());
        assertEquals(2,configurazioni1.size());
    }
    @Test
    public void testAggiornaConfigurazioneSMS(){
        init();
        assertThrows(IllegalArgumentException.class, () -> handler.aggiornaConfigurazioneSMS(1,-1,"testo"));
        assertThrows(IllegalArgumentException.class, () -> handler.aggiornaConfigurazioneSMS(-1,-1,"testo"));
        assertThrows(IllegalArgumentException.class, () -> handler.aggiornaConfigurazioneSMS(1,1,""));
        assertTrue(handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), "configurazione"));
        Set<SMS> configurazioni=db.getSMSPreconfigurati(azienda.getIdAzienda());
        assertEquals(1,configurazioni.size());
        assertTrue(handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), "configurazione2"));
        assertEquals(2,configurazioni.size());
        assertTrue(handler.aggiornaConfigurazioneSMS(azienda.getIdAzienda(),2,"ciao"));
        assertEquals(2,configurazioni.size());
        //testo cambiato correttamente visto con il debug nel giusto id
    }
    @Test
    public void rimuoviConfigurazioneSMS(){
        init();
        assertThrows(IllegalArgumentException.class, () -> handler.rimuoviConfigurazioenSMS(1,-1));
        assertThrows(IllegalArgumentException.class, () -> handler.rimuoviConfigurazioenSMS(-1,1));
        handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), "configurazione");
        Set<SMS> configurazioni=db.getSMSPreconfigurati(azienda.getIdAzienda());
        assertEquals(1,configurazioni.size());
        assertTrue(handler.aggiungiConfigurazioneSMS(azienda.getIdAzienda(), "configurazione2"));
        assertEquals(2,configurazioni.size());
        assertTrue(handler.rimuoviConfigurazioenSMS(azienda.getIdAzienda(), 2));
        assertEquals(1,configurazioni.size());
    }
}
