package loyality_platform_model.HandlerTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DBMSTest {

    //AZIENDA 1
    private final GestorePuntoVendita gestore = new GestorePuntoVendita("Alessio", "Cognome1", "prova@gmail.com", "password");
    private final SpazioFedelta spazio = new SpazioFedelta("Spazio1", "Indirizzo1", "Telefono1", "email1");
    private final Azienda azienda = new Azienda(gestore, spazio);

    //AZIENDA 2
    private final GestorePuntoVendita gestore1 = new GestorePuntoVendita("Nome", "Cognome", "nomecognome@gmail.com", "password");
    private final SpazioFedelta spazio1 = new SpazioFedelta("Spazio2", "Indirizzo2", "Telefono2", "email2");
    private final Azienda azienda1 = new Azienda(gestore1, spazio1);

    //DIPENDENTI AZIENDA
    private final Dipendente dipendente = new Dipendente("Dipendente1", "Cognome1", "prova@gmail1.com", "password1", false);
    private final Dipendente dipendente1 = new Dipendente("Dipendente2", "Cognome2", "prova@gmail1.com", "password2" ,true);


    //POLICY PROGRAMMI E PROGRAMMI
    private final Map<Integer, Integer> policy= new HashMap<>();
    private final ProgrammaFedelta programmaFedeltaPunti1 = new ProgrammaPunti("ProgrammaUno",  "22-02-2022", 100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaPunti2 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaPunti3 = new ProgrammaPunti("ProgrammaUno", "22-02-2022",100, 2, 10, 20, null);
    private final ProgrammaFedelta programmaFedeltaLivelli1 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);
    private final ProgrammaFedelta programmaFedeltaLivelli2 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);
    private final ProgrammaFedelta programmaFedeltaLivelli3 = new ProgrammaLivelli("ProgrammaUno", "22-02-2022",100, 2, policy, 2, 10, null);

    //PREMI P
    private final Premio premio1 = new Premio("Premio1", false, 2);
    private final Premio premio2 = new Premio("Premio2", false, 2);
    private final Premio premio3 = new Premio("Premio3", false, 2);
    private final Premio premio4 = new Premio("Premio4", false, 2);
    private final Set<Premio> premi = new HashSet<>();
    private final Set<CatalogoPremi> cataloghi = new HashSet<>();

    //CLIENTE
    private final Cliente cliente = new Cliente("Nome", "Cognome", "Telefono", "Email", "Password");

    private final Tessera tessera = new Tessera(cliente.getIdCliente());

    private final DBMS db = DBMS.getInstance();

    void initDb(){
        System.out.println("Id Azienda : " + this.azienda.getIdAzienda());
        System.out.println("Id Spazio Fedeltà azienda : " + this.spazio.getIdSpazioFedelta());
        System.out.println("Id Dipendente azienda : " + this.dipendente.getIdDipendente());
        System.out.printf("Id Dipendente 2 azienda : " + this.dipendente1.getIdDipendente());
        Set<Premio> premi = new HashSet<>();
        premi.add(premio1);
        premi.add(premio2);
        premi.add(premio3);
        premi.add(premio4);
        CatalogoPremi catalogo = new CatalogoPremi("Catalogo Premi", premi);
        Set<CatalogoPremi> catalogoPremi = new HashSet<>();
        catalogoPremi.add(catalogo);
        catalogoPremi.add(catalogo);
        //Azienda 1
        db.addAzienda(azienda);
        db.addDipendente(azienda.getIdAzienda(), dipendente);
        db.addDipendente(azienda.getIdAzienda(), dipendente1);
        db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti1);

        db.addAzienda(azienda1);
        // db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti2);
        //db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaPunti3);
        //db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli1);
        //db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli2);
        //db.addProgrammaAzienda(this.azienda.getIdAzienda(), programmaFedeltaLivelli3);
        //db.getCoalizione().addAziendaCoalizione(programmaFedeltaPunti1, azienda);
        //db.getCoalizione().addAziendaCoalizione(programmaFedeltaPunti2, azienda);
        //db.getCoalizione().addAziendaCoalizione(programmaFedeltaPunti3, azienda);
        //db.getCoalizione().addAziendaCoalizione(programmaFedeltaLivelli1, azienda);
        //db.getCoalizione().addAziendaCoalizione(programmaFedeltaLivelli2, azienda);
        //db.getCoalizione().addAziendaCoalizione(programmaFedeltaLivelli3, azienda);
        //CLIENTE
        db.addCliente(cliente);
        db.addTessera(tessera);
        //db.getCoalizione().addClienteCoalizione(programmaFedeltaPunti1, cliente);
        //db.getCoalizione().addClienteCoalizione(programmaFedeltaPunti2, cliente);
        //db.getCoalizione().addClienteCoalizione(programmaFedeltaPunti3, cliente);
        //db.getCoalizione().addClienteCoalizione(programmaFedeltaLivelli1, cliente);
        //db.getCoalizione().addClienteCoalizione(programmaFedeltaLivelli2, cliente);
        //db.getCoalizione().addClienteCoalizione(programmaFedeltaLivelli3, cliente);
    }

    @Test
    public void testAddProgrammaAzienda(){
        initDb();

        assertEquals(1, this.db.getProgrammiAzienda().get(azienda).size());
        assertTrue(this.db.addProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaPunti2));
        assertTrue(this.db.addProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaPunti3));
        assertEquals(3, this.db.getProgrammiAzienda().get(azienda).size());
        assertEquals(1, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).size());
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).contains(this.azienda));


        assertTrue(this.db.addProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaLivelli1));
        assertTrue(this.db.addProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaLivelli2));
        assertEquals(5, this.db.getProgrammiAzienda().get(azienda).size());
        assertEquals(1, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaLivelli1).size());
        assertEquals(1, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaLivelli2).size());
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaLivelli1).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaLivelli2).contains(this.azienda));

        assertNull(this.db.getProgrammiAzienda().get(azienda1));
        assertTrue(this.db.addProgrammaAzienda(this.azienda1.getIdAzienda(), this.programmaFedeltaPunti1));
        assertTrue(this.db.addProgrammaAzienda(this.azienda1.getIdAzienda(), this.programmaFedeltaPunti2));
        assertTrue(this.db.addProgrammaAzienda(this.azienda1.getIdAzienda(), this.programmaFedeltaPunti3));
        assertEquals(3, this.db.getProgrammiAzienda().get(azienda1).size());
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).contains(this.azienda1));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).contains(this.azienda1));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).contains(this.azienda1));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).contains(this.azienda));
        assertEquals(2, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).size());
        assertEquals(2, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).size());
        assertEquals(2, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).size());



    }

    @Test
    public void testRemoveProgrammaAzienda(){
        initDb();
        assertEquals(1, this.db.getProgrammiAzienda().get(azienda).size());
        assertTrue(this.db.addProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaPunti2));
        assertTrue(this.db.addProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaPunti3));
        assertEquals(3, this.db.getProgrammiAzienda().get(azienda).size());
        assertEquals(1, this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).size());
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).contains(this.azienda));

        assertTrue(this.db.removeProgrammaAzienda(this.azienda.getIdAzienda(), this.programmaFedeltaPunti1.getIdProgramma()));
        assertEquals(2, this.db.getProgrammiAzienda().get(this.azienda).size());
        assertFalse(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti1).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti2).contains(this.azienda));
        assertTrue(this.db.getCoalizione().getAziendePerProgramma().get(programmaFedeltaPunti3).contains(this.azienda));

    }

    @Test
    public void testAddClienteCoalizione(){
        initDb();
        assertTrue(this.db.getClientiIscritti().contains(cliente));
        assertTrue(this.db.addClienteCoalizione(cliente.getIdCliente(), this.programmaFedeltaPunti1));
        assertEquals(1, this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).size());
        assertTrue(this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).contains(this.cliente));
    }

    @Test
    public void testRemoveClienteCoalizione(){
        initDb();
        assertTrue(this.db.getClientiIscritti().contains(cliente));
        assertTrue(this.db.addClienteCoalizione(cliente.getIdCliente(), this.programmaFedeltaPunti1));
        assertEquals(1, this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).size());
        assertTrue(this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).contains(this.cliente));

        Cliente cliente1 = new Cliente("nome", "cognome", "telefono", "email", "password");

        this.db.addCliente(cliente1);
        assertTrue(this.db.getClientiIscritti().contains(cliente1));
        assertTrue(this.db.getClientiIscritti().contains(cliente1));
        assertTrue(this.db.addClienteCoalizione(cliente1.getIdCliente(), this.programmaFedeltaPunti1));
        assertEquals(2, this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).size());
        assertTrue(this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).contains(cliente1));

        assertTrue(this.db.deleteClienteCoalizione(cliente.getIdCliente(), programmaFedeltaPunti1));
        assertEquals(1, this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).size());
        assertFalse(this.db.getCoalizione().getClientiIscritti().get(programmaFedeltaPunti1).contains(this.cliente));
    }


}
