package loyality_platform_model.ModelTest;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CoalizioneTest {

    //CLIENTI TEST
    private final Cliente cliente0 = new Cliente("nome0", "cognome0", "telefono0", "email0");
    private final Cliente cliente1 = new Cliente("nome0", "cognome0", "telefono0", "email0");
    private final Cliente cliente2 = new Cliente("nome0", "cognome0", "telefono0", "email0");
    private final Cliente cliente3 = new Cliente("nome0", "cognome0", "telefono0", "email0");
    private final Cliente cliente4 = new Cliente("nome0", "cognome0", "telefono0", "email0");
    private final Cliente cliente5 = new Cliente("nome0", "cognome0", "telefono0", "email0");

    //GESTORE E SPAZIO FEDELTA' PER TEST
    private final GestorePuntoVendita gestore = new GestorePuntoVendita("nome", "cognome", "email");
    private final SpazioFedelta spazioFedelta = new SpazioFedelta("nome", "indirizzo", "telefono", "email");

    //AZIENDE PER TEST
    private final Azienda azienda0 = new Azienda(gestore, spazioFedelta);
    private final Azienda azienda1 = new Azienda(gestore, spazioFedelta);
    private final Azienda azienda2 = new Azienda(gestore, spazioFedelta);
    private final Azienda azienda3 = new Azienda(gestore, spazioFedelta);

    //PROGRAMMI FEDELTA' PER TEST (PUNTI)
    private final ProgrammaFedelta programmaPunti0 = new ProgrammaPunti("nome", "dataAttivazione", 300, 200, 10, 1, null);
    private final ProgrammaFedelta programmaPunti1 = new ProgrammaPunti("nome", "dataAttivazione", 300, 200, 10, 1, null);
    private final ProgrammaFedelta programmaPunti2 = new ProgrammaPunti("nome", "dataAttivazione", 300, 200, 10, 1, null);
    private final ProgrammaFedelta programmaPunti3 = new ProgrammaPunti("nome", "dataAttivazione", 300, 200, 10, 1, null);

    //PROGRAMMI FEDELTA' PER TEST (LIVELLI)

    private final Map<Integer, Integer> policy = new HashMap<>();

    private final ProgrammaFedelta programmaLivelli0 = new ProgrammaLivelli("nome", "dataAttivazione", 5, 3, policy, 3, 10, null);
    private final ProgrammaFedelta programmaLivelli1 = new ProgrammaLivelli("nome", "dataAttivazione", 5, 3, policy, 3, 10, null);
    private final ProgrammaFedelta programmaLivelli2 = new ProgrammaLivelli("nome", "dataAttivazione", 5, 3, policy, 3, 10, null);
    private final ProgrammaFedelta programmaLivelli3 = new ProgrammaLivelli("nome", "dataAttivazione", 5, 3, policy, 3, 10, null);
    private final DBMS dbms = DBMS.getInstance();

    void initDb() {
        //AZIENDE
        this.dbms.addAzienda(azienda0);
        this.dbms.addAzienda(azienda1);
        this.dbms.addAzienda(azienda2);
        this.dbms.addAzienda(azienda3);
        //CLIENTI
        this.dbms.addCliente(cliente0);
        this.dbms.addCliente(cliente1);
        this.dbms.addCliente(cliente2);
        this.dbms.addCliente(cliente3);
        this.dbms.addCliente(cliente4);
        this.dbms.addCliente(cliente5);
    }

    @Test
    public void testAddProgrammaFedelta() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));
        assertFalse(this.dbms.getCoalizione().getAziendePerProgramma().keySet().isEmpty());
        assertFalse(this.dbms.getCoalizione().getClientiIscritti().keySet().isEmpty());
        assertEquals(6, this.dbms.getCoalizione().getAziendePerProgramma().keySet().size());
        assertEquals(6, this.dbms.getCoalizione().getClientiIscritti().keySet().size());
    }

    @Test
    public void testGetClientiIscrittiProgramma() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));

        Set<Cliente> clientiProgrammaPunti0 = this.dbms.getCoalizione().getClientiIscrittiProgramma(programmaPunti0.getIdProgramma());

        assertTrue(clientiProgrammaPunti0.contains(cliente0));
        assertTrue(clientiProgrammaPunti0.contains(cliente1));
        assertTrue(clientiProgrammaPunti0.contains(cliente2));

    }

    @Test
    public void testGetClientiIscrittiAzienda() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti1, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti1, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti2, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti2, azienda0));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente0));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente1));


        Set<Cliente> clientiIscrittiProgrammaAzienda = this.dbms.getCoalizione().getClientiIscrittiAProgrammaAzienda(azienda0.getIdAzienda(), programmaPunti0.getIdProgramma());
        assertNotNull(clientiIscrittiProgrammaAzienda);
        assertTrue(clientiIscrittiProgrammaAzienda.contains(cliente0));
        assertTrue(clientiIscrittiProgrammaAzienda.contains(cliente1));
        assertFalse(clientiIscrittiProgrammaAzienda.contains(cliente2));
        assertFalse(clientiIscrittiProgrammaAzienda.contains(cliente3));
    }

    @Test
    public void testGetClientiAzienda(){
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti1, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti1, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti2, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti2, azienda0));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente0));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente1));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente3));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti1, cliente3));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente3));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti2, cliente3));

        Set<Cliente> clientiAzienda = this.dbms.getCoalizione().getClientiAzienda(azienda0.getIdAzienda());

        assertNotNull(clientiAzienda);

        assertTrue(clientiAzienda.contains(cliente0));
        assertTrue(clientiAzienda.contains(cliente1));
        assertTrue(clientiAzienda.contains(cliente2));
        assertTrue(clientiAzienda.contains(cliente3));
    }

    @Test
    public void testGetAziendeIscritteProgramma() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda1));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda1));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda2));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda2));

        Set<Azienda> aziendeIscritteProgramma = this.dbms.getCoalizione().getAziendeIscritteProgramma(programmaPunti0.getIdProgramma());

        assertTrue(aziendeIscritteProgramma.contains(azienda0));
        assertTrue(aziendeIscritteProgramma.contains(azienda1));
        assertTrue(aziendeIscritteProgramma.contains(azienda2));
    }

    @Test
    public void testAddAziendaCoalizione() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda1));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda1));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda2));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda2));

        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaLivelli0, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaLivelli0, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaLivelli0, azienda1));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaLivelli0, azienda1));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaLivelli0, azienda2));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaLivelli0, azienda2));

        assertEquals(3, this.dbms.getCoalizione().getAziendePerProgramma().get(programmaPunti0).size());
        assertEquals(3, this.dbms.getCoalizione().getAziendePerProgramma().get(programmaLivelli0).size());
    }

    @Test
    public void testAddClienteCoalizione() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));

        assertEquals(3, this.dbms.getCoalizione().getClientiIscritti().get(programmaPunti0).size());

    }

    @Test
    public void deleteAziendaProgramma() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda0));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda1));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda1));
        assertTrue(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda2));
        assertFalse(this.dbms.getCoalizione().addAziendaCoalizione(programmaPunti0, azienda2));

        Set<Azienda> aziendaProgramma = this.dbms.getCoalizione().getAziendeIscritteProgramma(programmaPunti0.getIdProgramma());

        assertTrue(aziendaProgramma.contains(azienda0));
        assertTrue(aziendaProgramma.contains(azienda1));
        assertTrue(aziendaProgramma.contains(azienda2));

        assertTrue(this.dbms.getCoalizione().deleteAziendaProgramma(azienda0.getIdAzienda(), programmaPunti0.getIdProgramma()));
        assertTrue(this.dbms.getCoalizione().deleteAziendaProgramma(azienda1.getIdAzienda(), programmaPunti0.getIdProgramma()));

        Set<Azienda> aziendaProgrammaUpdated = this.dbms.getCoalizione().getAziendeIscritteProgramma(programmaPunti0.getIdProgramma());

        assertFalse(aziendaProgrammaUpdated.contains(azienda0));
        assertFalse(aziendaProgrammaUpdated.contains(azienda1));
        assertTrue(aziendaProgrammaUpdated.contains(azienda2));
    }

    @Test
    public void deleteClienteProgramma() {
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaPunti2));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli0));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli1));
        assertTrue(this.dbms.getCoalizione().addProgrammaFedelta(programmaLivelli2));

        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente0));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente1));
        assertTrue(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));
        assertFalse(this.dbms.getCoalizione().addClienteCoalizione(programmaPunti0, cliente2));

        Set<Cliente> clientiIscritti = this.dbms.getCoalizione().getClientiIscrittiProgramma(programmaPunti0.getIdProgramma());

        assertTrue(clientiIscritti.contains(cliente0));
        assertTrue(clientiIscritti.contains(cliente1));
        assertTrue(clientiIscritti.contains(cliente2));

        assertTrue(this.dbms.getCoalizione().deleteClienteProgramma(cliente0.getIdCliente(), programmaPunti0.getIdProgramma()));
        assertTrue(this.dbms.getCoalizione().deleteClienteProgramma(cliente1.getIdCliente(), programmaPunti0.getIdProgramma()));

        Set<Cliente> clientiIscrittiUpdated = this.dbms.getCoalizione().getClientiIscrittiProgramma(programmaPunti0.getIdProgramma());

        assertFalse(clientiIscrittiUpdated.contains(cliente0));
        assertFalse(clientiIscrittiUpdated.contains(cliente1));
        assertTrue(clientiIscrittiUpdated.contains(cliente2));

    }


}
