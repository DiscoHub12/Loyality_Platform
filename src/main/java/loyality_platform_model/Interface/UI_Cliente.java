package loyality_platform_model.Interface;
import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Handler.*;
import loyality_platform_model.Models.*;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.exit;
public class UI_Cliente {
    private final Scanner sc;
    private final Cliente cliente;
    private final HandlerCliente gestoreCliente = new HandlerCliente();
    private final HandlerPremi gestorePremi = new HandlerPremi();
    private final HandlerProgrammaFedelta gestoreProgrammi = new HandlerProgrammaFedelta();
    private final HandlerVisite gestoreVisite = new HandlerVisite();
    private final HandlerAzienda gestoreAzienda = new HandlerAzienda();
    private final HandlerTessera gestoreTessera= new HandlerTessera();
    public UI_Cliente(Cliente cliente){
        this.cliente = cliente;
        this.sc = new Scanner(System.in);
    }
    public void homeBackoffice() {
        int choice;
        System.out.println("""
                BENVENUTO
                Elenco le sezioni disponibili nella Dashboard Cliente:
                1. Clienti
                2. Logout
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        if (choice == 1) {
            sezioneClienti();
        } else exit(0);
    }
    //SEZIONE CLIENTI
    public void sezioneClienti() {
        int choice = 0;
        System.out.println("""
                SEZIONE CLIENTI
                Elenco tutte le attività nella sezione Backoffice:
                1. Sezione Iscrizione Programmi Fedeltà
                2. Sezione Cerca Negozi
                3. Sezione Tessera
                4. Sezione Riscatta Premi
                5. Logout
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneIscrizioneProgrammiFedelta();
            case 2 -> sezioneCercaNegozi();
            case 3 -> sezioneTessera();
            case 4 -> sezioneCatalogoPremi();
            case 5 -> sezioneLogout();
        }
    }
    //SOTTO-SEZIONE PROGRAMMI FEDELTA' (DI CLIENTI)
    public void sezioneIscrizioneProgrammiFedelta() {
        int choice;
        System.out.println("""
                SEZIONE PROGRAMMI FEDELTA' .
                Elenco delle attività disponibili nella sezione Iscrizione Programma Fedeltà: 
                1. iscriviti ad un programma fedelta appartenente ad un negozio
                2. iscriviti ad un programma fedelta disponibile nella piattaforma
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneCercaNegoziProgrammaFedelta();
            case 2 -> sezioneIscrizioneProgrammaFedeltaPiattaforma();
        }
    }
    //SOTTO SEZIONE CERCA NEGOZI (DI CLIENTI)
    public void sezioneCercaNegozi() {
        int choice;
        String nomeNegozio;
        int id;
        System.out.println("""
                SEZIONE CERCA NEGOZIO.
                Cerca un negozio attraverso il nome:
                Inserisci il nome del negozio.
                                
                """);
        nomeNegozio = sc.nextLine();
        Set<Azienda> aziende = DBMS.getInstance().getAziendeIscritte();
        for (Azienda azienda1 : aziende) {
            if (azienda1.getSpazioFedelta().getNome() == nomeNegozio) {
                System.out.println("Id Negozio : " + azienda1.getSpazioFedelta().getIdSpazioFedelta() + "Nome Negozio : " + azienda1.getSpazioFedelta().getNome() +
                        "Indirizzo Negozio : " + azienda1.getSpazioFedelta().getIndirizzo() + "Numero Telefono : " + azienda1.getSpazioFedelta().getNumeroTelefono() +
                        "Email del negozio : " + azienda1.getSpazioFedelta().getEmail());
            }
            else
                System.out.println("\nNon è stato trovato nessun negozio\nProva a cercare un altro negozio.");
                sezioneCercaNegozi();
        }
    }
    //SOTTO SEZIONE CERCA NEGOZI (DI ISCRIZIONE PROGRAMMA FEDELTA)
    public void sezioneCercaNegoziProgrammaFedelta(){
        //TODO
        int choice;
        String nomeNegozio;
        int id;
        System.out.println("""
                SEZIONE PROGRAMMI FEDELTA' NEGOZIO.
                Cerca un negozio attraverso il nome:
                Inserisci il nome del negozio.
                                
                """);
        nomeNegozio = sc.nextLine();
        Set<Azienda> aziende= DBMS.getInstance().getAziendeIscritte();
        for(Azienda azienda1 : aziende){
            if(azienda1.getSpazioFedelta().getNome()==nomeNegozio){
                Set<ProgrammaFedelta> programmiFedelta= this.gestoreAzienda.getProgrammiFedeltaAzienda(azienda1.getIdAzienda());
                for(ProgrammaFedelta programmaFedelta1 : programmiFedelta){
                    System.out.println("Id Programma : " + programmaFedelta1.getIdProgramma() + "Nome Programma : " + programmaFedelta1.getNome());
                    System.out.println("""
                    Possiedi la tessera cliente?
                    1. Si
                    2. No
                    Inserisci il numero corrispettivo

                    """);
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Inserisci l'id del programma fedeltà desiderato : ");
                            id=sc.nextInt();
                            if(programmaFedelta1.getIdProgramma()==id){
                                if(cliente.getTessera()!=null) {
                                    this.gestoreTessera.addProgrammaFedelta(cliente.getTessera().getIdTessera(), programmaFedelta1);
                                    System.out.println("Programma fedeltà aggiunto alla tua tessera");
                                }else
                                    throw new IllegalArgumentException("Errore: la tessera non è stata trovata o non esiste");
                            }
                        }
                        case 2 -> creaTessera();
                    }
                    }
                }
            }
    }
        //SOTTO SEZIONE ISCRIZIONE PROGRAMMA FEDELTA PIATTAFORMA (DI ISCRIZIONE PROGRAMMA FEDELTA)
        public void sezioneIscrizioneProgrammaFedeltaPiattaforma() {
            int choice;
            int id;
            System.out.println("""
                    SEZIONE PROGRAMMI FEDELTA' PIATTAFORMA.
                    Elenco tutti i Programmi Fedeltà disponibili all'interno della piattaforma:
                                    
                    """);
            Set<ProgrammaFedelta> programmiFedelta = DBMS.getInstance().getProgrammiDisponibili();
            if (programmiFedelta != null) {
                for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
                    System.out.println("Id Programma : " + programmaFedelta1.getIdProgramma() + "Nome Programma : " + programmaFedelta1.getNome());
                }
            }
                System.out.println("""
                        Per iscriversi al programma fedeltà desiderato è necessario essere in possesso di una tessera. Ne possiede già una?
                        1. Sì
                        2. No
                        Inserisci il numero corrispettivo
                                        
                        """);
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Inserisci l'id del programma fedeltà desiderato : ");
                        id = sc.nextInt();
                        Set<ProgrammaFedelta> programmiFedelta1 = DBMS.getInstance().getProgrammiDisponibili();
                        if (programmiFedelta1 != null) {
                            for (ProgrammaFedelta programmaFedelta2 : programmiFedelta1) {
                                if (programmaFedelta2.getIdProgramma() == id) {
                                    if (cliente.getTessera() != null) {
                                        this.gestoreTessera.addProgrammaFedelta(cliente.getTessera().getIdTessera(), programmaFedelta2);
                                        System.out.println("Programma fedeltà aggiunto alla tua tessera");
                                    } else
                                        throw new IllegalArgumentException("Errore: la tessera non è stata trovata o non esiste");
                                }else
                                    throw new IllegalArgumentException("Errore : programma selezionato non disponibile");
                            }
                        }
                        else
                            System.out.println("Non è disponibile nessun programma fedeltà all'interno della piattaforma.");
                    }
                    case 2 -> creaTessera();
                }
            }

    //SOTTO SEZIONE TESSERA (DI CLIENTI)
    public void sezioneTessera() {
        int choice;
        int id;
        System.out.println("""
                    SEZIONE INFO TESSERA
                    Ecco le info della tua tessera :

                    """);
        cliente.getTessera().toString();
        System.out.println("""
                        Vuoi vedere i dettagli di un determinato programma fedeltà?
                        1. Sì
                        2. No (Ritornerai alla home)
                        Inserisci il numero corrispettivo
                                        
                        """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Inserisci l'id del programma fedeltà desiderato : ");
            id = sc.nextInt();
            Set<ProgrammaFedelta> programmiFedelta = DBMS.getInstance().getProgrammiDisponibili();
            for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
                if (programmaFedelta1.getIdProgramma() == id) {
                    programmaFedelta1.toString();
                }
            }
        }
            case 2 -> sezioneClienti();
        }
    }
    //SOTTO SEZIONE PREMI (DI CLIENTI)
    public void sezioneCatalogoPremi(){
        int id;
        int id1;
        int id2;
        int id3;
        Set<Premio> premi=this.gestoreCliente.getPremiCliente(cliente.getIdCliente());
        Set<ProgrammaFedelta> programmiFedelta=this.gestoreCliente.getTesseraCliente(cliente.getIdCliente()).getProgrammiFedelta();
        System.out.println("""
                    SEZIONE PREMI
                    Elenco dei premi riscattati :

                    """);
        if(premi!=null){
            for(Premio premio : premi){
                System.out.println("Id : " + premio.getIdPremio() + "Nome : " +premio.getNome());
            }
        }

        System.out.println("""
                SEZIONE RISCATTA PREMIO
                Elenco dei programmi fedeltà a cui si è iscritto :
                                
                """);
        if(programmiFedelta != null){
            for(ProgrammaFedelta programmaFedelta1 : programmiFedelta){
                System.out.println("Id : " + programmaFedelta1.getIdProgramma() + "Nome : " +programmaFedelta1.getNome());
            }
        }
        System.out.println("Inserisci l'id del programma fedeltà su cui vuoi vedere il catalogo premi : ");
        id=sc.nextInt();
        Set<Azienda> aziende = DBMS.getInstance().getCoalizione().getAziendeIscritteProgramma(id);
        for(Azienda azienda1 : aziende){
            System.out.println(("Id Negozio : " +azienda1.getIdAzienda() + "Nome Azienda : " +azienda1.getSpazioFedelta().getNome()));
        }
        System.out.println("Inserisci id del negozio su cui vuoi vedere il catalogo premi : ");
        id1=sc.nextInt();
        Set<CatalogoPremi> cataloghiPremi= this.gestoreAzienda.getCatalogoPremiAzienda(id1);
        for(CatalogoPremi catalogoPremi1 : cataloghiPremi){
            System.out.println(catalogoPremi1.toString());
        }
        System.out.println("Inserisci id del catalogo premi che ti interessa : ");
        id2=sc.nextInt();
        for(CatalogoPremi catalogoPremi2 : cataloghiPremi){
            if(catalogoPremi2.getIdCatalogoPremi()==id2){
                Set<Premio> premi1 = catalogoPremi2.getPremiCatalogo();
                for(Premio premio1 : premi1){
                    System.out.println(premio1.toString());
                }
            }
        }
        System.out.println("Inserisci id del premio che si vuole riscattare : ");
        id3=sc.nextInt();
        this.gestorePremi.aggiungiPremioClienteCatalogoProgramma(id1,id,cliente.getIdCliente(),id3);
    }

    //SOTTO-SEZIONE LOGOUT
    public void sezioneLogout() {
        int choice;
        System.out.println("""
                Premi 1 per confermare il Logout.
                """);
        choice = sc.nextInt();
        if (choice == 1) {
            exit(0);
        }
    }

    //---------------------------------------------------------------------------------------

    public void creaTessera() {
        //TODO
        int choice;
        System.out.println("""
                SEZIONE CREA TESSERA .
                Vuoi creare una nuova tessera per questa piattaforma?: 
                1. Si
                2. No (ritornerai alla schermata home)
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestoreTessera.creaTessera(cliente.getIdCliente());
                System.out.println("\nTessera creata correttamente.\nRitorno alla schermata principale.");
                sezioneCercaNegoziProgrammaFedelta();
            }
            case 2 -> sezioneClienti();
        }
    }
}
