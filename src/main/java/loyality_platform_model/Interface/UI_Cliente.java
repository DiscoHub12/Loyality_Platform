package loyality_platform_model.Interface;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import loyality_platform_model.Utils.Utils;

import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class UI_Cliente {
    private final Scanner sc;

    private final Cliente cliente;

    private final Utils utils;

    private final UI_Home home;

    public UI_Cliente(Cliente cliente, UI_Home home) {
        this.cliente = cliente;
        this.sc = new Scanner(System.in);
        this.utils = new Utils();
        this.home = home;
        homeCliente();
    }

    //SEZIONE CLIENTI
    public void homeCliente() {
        int choice;
        System.out.println("""
                 BENVENUTO
                 Elenco le sezioni disponibili nel tuo profilo:
                 1. Sezione Iscrizione Programmi Fedelta'
                 2. Sezione Negozi Convenzionati
                 3. Sezione Tessera
                 4. Sezione Riscatta Premi
                 5. Sezione Coupon
                 6. Sezione Visite
                 7. Logout
                 Inserisci il numero corrispettivo
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneIscrizioneProgrammiFedelta();
            case 2 -> sezioneCercaNegozi();
            case 3 -> sezioneTessera();
            case 4 -> sezioneCatalogoPremi();
            case 5 -> sezioneCoupon();
            case 6 -> sezioneVisite();
            case 7 -> sezioneLogout();
        }
    }

    //SOTTO-SEZIONE PROGRAMMI FEDELTA' (DI CLIENTI)
    public void sezioneIscrizioneProgrammiFedelta() {
        int choice;
        Set<Azienda> aziende = DBMS.getInstance().getAziendeIscritte();
        System.out.println("""
                SEZIONE PROGRAMMI FEDELTA' .
                Elenco dei programmi fedeltà disponibili :
                """);
        if (aziende == null || aziende.isEmpty()) {
            System.out.println("""
                    Nessun Azienda iscritta. 
                    Ritorno alla Home.
                    """);
            homeCliente();
        } else {
            for (Azienda azienda : aziende) {
                System.out.println("Id Azienda : " + azienda.getIdAzienda());
                Set<ProgrammaFedelta> programmaFedeltas = this.utils.getHandlerAzienda().getProgrammiFedeltaAzienda(azienda.getIdAzienda());
                if (programmaFedeltas == null || programmaFedeltas.isEmpty()) {
                    System.out.println("L'azienda non possiede alcun Programma Fedelta' attivo.");
                } else {
                    for (ProgrammaFedelta programmaFedelta : programmaFedeltas) {
                        System.out.println("Id Programma : " + programmaFedelta.getIdProgramma() + " Nome Programma : " + programmaFedelta.getNome() + " Tipo Programma :" + programmaFedelta.getTipoProgramma());

                    }
                }
            }
            System.out.println("""
                    Elenco le operazioni disponibili: 
                    1. Iscriviti ad un Programma Fedelta'. 
                    2. Ritorna alla Home.
                    """);
            int choice1 = sc.nextInt();
            switch (choice1) {
                case 1 -> {
                    System.out.println("Inserire l'id di una azienda: ");
                    int azienda = sc.nextInt();
                    System.out.println("Inserire l'id di un programma a cui iscriversi: ");
                    choice = sc.nextInt();
                    this.selezioneProgramma(choice, azienda);
                }
                case 2 -> homeCliente();
            }
        }
    }

    //SOTTO SEZIONE CERCA NEGOZI (DI CLIENTI)
    public void sezioneCercaNegozi() {
        sc.nextLine();
        int choice;
        Set<Azienda> aziendeIscritte = DBMS.getInstance().getAziendeIscritte();
        System.out.println("Elenco Aziende Iscritte :");
        for (Azienda azienda : aziendeIscritte) {
            System.out.println("-Id Azienda : " + azienda.getIdAzienda() + "       Nome Azienda : " + azienda.getSpazioFedelta().getNome() + "       Indirizzo Azienda : " + azienda.getSpazioFedelta().getIndirizzo());
        }
        System.out.println("""
                Desideri cercare un negozio
                1. Si
                2. Annulla
                Inserisci il numero corrispettivo

                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                sc.nextLine();
                System.out.println("Inserisci il nome del negozio: ");
                String nomeNegozio = sc.nextLine();
                for (Azienda toScroll : aziendeIscritte) {
                    if (Objects.equals(nomeNegozio, toScroll.getSpazioFedelta().getNome())) {
                        System.out.println("\nInformazioni relative al negozio cercato: ");
                        System.out.println("\n -Id Azienda : " + toScroll.getIdAzienda() +
                                "\n -Nome Azienda : " + toScroll.getSpazioFedelta().getNome() +
                                "\n -Indirizzo Azienda : " + toScroll.getSpazioFedelta().getIndirizzo() +
                                "\n -Numero Telefono : " + toScroll.getSpazioFedelta().getNumeroTelefono() +
                                "\n -Email : " + toScroll.getSpazioFedelta().getEmail());
                        System.out.println("""
                                Desideri iscriverti ad un Programma Fedelta' del negozio:
                                1. Si
                                2. Annulla
                                Inserisci il numero corrispettivo

                                """);
                        int iscrivi = sc.nextInt();
                        switch (iscrivi) {
                            case 1 -> {
                                Set<ProgrammaFedelta> programmiAzienda = this.utils.getHandlerAzienda().getProgrammiFedeltaAzienda(toScroll.getIdAzienda());
                                if (programmiAzienda == null || programmiAzienda.isEmpty()) {
                                    System.out.println("""
                                            L'Azienda non ha alcun Programma Fedelta' attivo.
                                            Ritorno alla schermata Principale.
                                            """);
                                    sezioneCercaNegozi();
                                } else {
                                    System.out.println("Elenco dei programmi fedeltà disponibili del negozio: \n" +
                                            programmiAzienda.toString());
                                    System.out.println("Inserire l'id di un programma a cui iscriversi: ");
                                    int program = sc.nextInt();
                                    this.selezioneProgramma(program, toScroll.getIdAzienda());
                                }
                            }
                            case 2 -> this.homeCliente();
                        }
                    }
                }
                System.out.println("""
                        Azienda non trovata.
                        Inserisci :
                        1. Cerca di nuovo l'Azienda.
                        2. Ritorna alla Home.
                        """);
                int choice1 = sc.nextInt();
                if (choice1 == 1) {
                    sezioneCercaNegozi();
                } else homeCliente();
            }
            case 2 -> homeCliente();
        }
    }

    //SOTTO SEZIONE CERCA NEGOZI (DI ISCRIZIONE PROGRAMMA FEDELTA)
    public void selezioneProgramma(int idProgramma, int idAzienda) {
        sc.nextLine();
        if (cliente.getTessera() == null) {
            System.out.println("""
                    Non possiedi la Tessera.
                    Per iscriverti ad un Programma Fedelta', devi richiedere la Tessera.
                    Vuoi richiedere la Tessera ? 
                    Inserisci : 
                    1. Richiedi Tessera
                    2. Ritorna alla Home.
                    """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    if (this.utils.getHandlerTessera().creaTessera(cliente.getIdCliente())) {
                        System.out.println("Tessera creata correttamente.");
                    } else {
                        System.out.println("""
                                Errore. Riprovare
                                """);
                        selezioneProgramma(idProgramma, idAzienda);
                    }
                }
                case 2 -> {
                    System.out.println("""
                            Impossibile iscriversi al programma fedelta'
                            Ritorno alla Home.
                            """);
                    this.homeCliente();
                }
            }
        }
        System.out.println("""
                Conferma iscrizione:
                 1. Conferma
                 2. Annulla
                 Inserisci il numero corrispettivo
                                 
                 """);
        int conferma = sc.nextInt();
        switch (conferma) {
            case 1 -> {
                ProgrammaFedelta programmaFedelta = this.utils.getHandlerProgrammaFedelta().getProgrammaFedeltaById(idAzienda, idProgramma);
                if (this.utils.getHandlerTessera().addProgrammaFedelta(cliente.getIdCliente(), programmaFedelta)) {
                    System.out.println("""
                            Iscrizione avvenuta con successo.
                            Ritorno alla Home.
                            """);
                    this.homeCliente();
                } else {
                    System.out.println("""
                            Iscrizione non avvenuta.
                            Ritorno alla Home.
                            """);
                    this.homeCliente();
                }
            }
            case 2 -> {
                System.out.println("""
                        Iscrizione annullata.
                        Ritorno alla Home.
                        """);
                this.homeCliente();
            }
        }
    }

    //SOTTO SEZIONE TESSERA (DI CLIENTI)
    public void sezioneTessera() {
        sc.nextLine();
        int choice;
        int id;
        if (cliente.getTessera() == null) {
            System.out.println("""
                    Vuoi richiedere la Tessera ? 
                    Inserisci : 
                    1. Richiedi Tessera
                    2. Ritorna alla Home.
                    """);
            String choice1 = sc.nextLine();
            if (Objects.equals(choice1, "SI") || Objects.equals(choice1, "si")) {
                this.utils.getHandlerTessera().creaTessera(this.cliente.getIdCliente());
                System.out.println("Tessera creata correttamente.");
            } else {
                System.out.println("""
                        Hai scelto di non creare la tessera.
                        Ritorno alla Home. 
                        """);
                homeCliente();
            }
        }
        System.out.println("""
                SEZIONE TESSERA
                Ecco le info della tua tessera :

                """);
        System.out.println(cliente.getTessera().toString());
        if (!this.cliente.getTessera().getProgrammiFedelta().isEmpty()) {
            System.out.println("""
                    Vuoi vedere i dettagli di un determinato programma fedelta' a cui sei iscritto?
                    1. Si
                    2. No (Ritornerai alla home clienti)
                    Inserisci il numero corrispettivo
                                    
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Inserisci l'id del programma fedelta' desiderato : ");
                    id = sc.nextInt();
                    Tessera tesseraCliente = DBMS.getInstance().getTesseraCliente(cliente.getIdCliente());
                    Set<ProgrammaFedelta> programmiFedelta = tesseraCliente.getProgrammiFedelta();
                    for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
                        if (programmaFedelta1.getIdProgramma() == id) {
                            System.out.println(programmaFedelta1);
                            System.out.println("""
                                    Vuoi vedere altri programmi fedelta' aggiunti alla tua tessera?
                                    1. Sì
                                    2. No (Ritornerai alla home clienti)
                                    Inserisci il numero corrispettivo
                                                    
                                    """);
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1 -> sezioneTessera();
                                case 2 -> homeCliente();
                            }
                        }
                    }
                }
                case 2 -> homeCliente();
            }
        } else {
            System.out.println("""
                    Non sei iscritto a nessun Programma Fedelta'.
                    Ritorno alla Home.
                    """);
            homeCliente();
        }
    }

    //SOTTO SEZIONE PREMI (DI CLIENTI)
    public void sezioneCatalogoPremi() {
        sc.nextLine();
        Set<Premio> premi = this.utils.getHandlerCliente().getPremiCliente(cliente.getIdCliente());
        if (premi == null || premi.isEmpty()) {
            System.out.println("""
                     Non possiedi alcun Premio.
                    """);
        } else {
            System.out.println("""
                    SEZIONE PREMI
                    Elenco dei premi riscattati :

                    """);
            for (Premio premio : premi) {
                System.out.println(premio.toString());
            }

        }
        System.out.println("""
                Vuoi riscattare un premio:
                1. Conferma
                2. Annulla
                                                                
                """);
        int riscatta = sc.nextInt();
        switch (riscatta) {
            case 1 -> sezioneRiscattaPremio();
            case 2 -> this.homeCliente();
        }
    }

    public void sezioneRiscattaPremio() {
        sc.nextLine();
        int id;
        Tessera tessera = this.cliente.getTessera();
        if (tessera == null) {
            System.out.println("""
                    Non possiedi la Tessera e non sei iscritto ad alcun Programma Fedelta'. 
                    Ritorno alla Home.
                    """);
            homeCliente();
        } else {
            if (tessera.getProgrammiFedelta() == null || tessera.getProgrammiFedelta().isEmpty()) {
                System.out.println("""
                        Non sei iscritto a nessun Programma Fedelta'. Non puoi riscattare premi. 
                        Ritorno alla Home.
                        """);
                homeCliente();
            } else {
                System.out.println("""
                        SEZIONE RISCATTA PREMIO
                        Elenco dei programmi fedelta' a cui si e' iscritto :
                                        
                        """);
                Set<ProgrammaFedelta> programmiFedelta = tessera.getProgrammiFedelta();
                for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
                    System.out.println("-Id Programma : " + programmaFedelta1.getIdProgramma() + " Nome Programma : " + programmaFedelta1.getNome());
                }
                System.out.println("Inserisci l'id del programma fedelta' su cui vuoi vedere il catalogo premi :");
                id = sc.nextInt();
                for (ProgrammaFedelta programmaFedelta : programmiFedelta) {
                    if (id == programmaFedelta.getIdProgramma()) {
                        if (programmaFedelta.getCatalogoPremi() == null || programmaFedelta.getCatalogoPremi().getPremiCatalogo().isEmpty()) {
                            System.out.println("""
                                    Il Programma Fedelta' non possiede alcun Catalogo Premi.
                                    Seleziona :
                                    1. Ritorna alla schermata precedente.
                                    2. Ritorna alla Home.
                                    """);
                            int choice1 = sc.nextInt();
                            switch (choice1) {
                                case 1 -> sezioneCatalogoPremi();
                                case 2 -> homeCliente();
                            }
                        } else System.out.println(programmaFedelta.getCatalogoPremi());
                    }
                    Set<Azienda> aziendeIscritte = DBMS.getInstance().getCoalizione().getAziendeIscritteProgramma(programmaFedelta.getIdProgramma());
                    if (aziendeIscritte == null || aziendeIscritte.isEmpty()) {
                        System.out.println("Nessun Azienda ha in corso il Programma Fedelta' con id : " + programmaFedelta.getIdProgramma());
                    } else {
                        System.out.println(" Aziende che hanno attivo il Programma Fedelta' : ");
                        for (Azienda azienda : aziendeIscritte) {
                            System.out.println("-Id Azienda : " + azienda.getIdAzienda() + " Nome Azienda : " + azienda.getSpazioFedelta().getNome());
                        }
                        System.out.println("Inserisci l'id dell'Azienda su cui vedere il Catalogo Premi del Programma Fedeltà selezionato :");
                        int idAzienda = sc.nextInt();
                        for (Azienda azienda : aziendeIscritte) {
                            if (azienda.getIdAzienda() == idAzienda) {
                                System.out.println(" Catalogo Premi Azienda : " +
                                        "\n" + DBMS.getInstance().getProgrammaFedeltaById(azienda.getIdAzienda(), programmaFedelta.getIdProgramma()).getCatalogoPremi().toString());
                                Set<Premio> premiDisponibili = DBMS.getInstance().getProgrammaFedeltaById(azienda.getIdAzienda(), programmaFedelta.getIdProgramma()).getCatalogoPremi().getPremiCatalogo();
                                for (Premio premio : premiDisponibili) {
                                    System.out.println("Puoi Riscattare i Seguenti Premi : ");
                                    if (this.cliente.getTessera().getPunti() >= premio.getPunti()) {
                                        if (premio.getPunti() != 0) {
                                            System.out.println("-Id Premio : " + premio.getIdPremio() + " Nome premio : " + premio.getNome() + " Punti per il Premio : " + premio.getPunti());
                                        }
                                    } else if (this.cliente.getTessera().getLivelli() >= premio.getLivelli()) {
                                        System.out.println("-Id Premio : " + premio.getIdPremio() + " Nome premio : " + premio.getNome() + " Livelli per il Premio : " + premio.getPunti());

                                    }
                                }
                                System.out.println("Inserisci l'id del premio che vuoi riscattare : ");
                                int idPremio = sc.nextInt();
                                int numeroPunti = 0;
                                for (Premio premio : premiDisponibili) {
                                    if (premio.getIdPremio() == idPremio) {
                                        numeroPunti = premio.getPunti();
                                    }
                                }
                                boolean res = this.utils.getHandlerPremi().aggiungiPremioClienteCatalogoProgramma(azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), this.cliente.getIdCliente(), idPremio);
                                if (res) {
                                    this.utils.getHandlerTessera().removePuntiCliente(tessera.getIdTessera(), numeroPunti);
                                    System.out.println("""
                                            Premio riscattato correttamente.
                                            Ritorno alla schermata precedente.
                                            """);
                                    sezioneCatalogoPremi();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void sezioneCoupon() {
        Set<Coupon> couponCliente = this.utils.getHandlerCliente().getCouponCliente(cliente.getIdCliente());
        if (couponCliente == null || couponCliente.isEmpty()) {
            System.out.println("""
                    Non possiedi alcun Coupon.
                    Ritorno alla Home.
                    """);
            homeCliente();
        } else {
            System.out.println("Elenco i Coupon posseduti : " +
                    "\n" + couponCliente.toString());
            System.out.println("""
                    Elenco le possibili operazioni :
                    1. Ritorna alla Home. 
                    """);
            int choice = sc.nextInt();
            if (choice == 1) {
                this.homeCliente();
            } else {
                sezioneCoupon();
            }
        }
    }

    public void sezioneVisite() {
        sc.nextLine();
        Set<Visita> visiteCliente = this.utils.getHandlerCliente().getVisiteCliente(cliente.getIdCliente());
        if (visiteCliente == null || visiteCliente.isEmpty()) {
            System.out.println("""
                    Non hai nessuna Visita prenotata.
                    Ritorno alla Home.
                    """);
            homeCliente();
        } else {
            System.out.println("Elenco le Visite prenotate" +
                    "\n" + visiteCliente.toString());
            System.out.println("""
                    Elenco delle attivita' disponibili: 
                    1. Prenota visita
                    2. Cancella visita
                    3. Annulla
                                    
                    """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1, 2 -> {
                    System.out.println("Sezione non disponibile.");
                    this.homeCliente();
                }
                case 3 -> this.homeCliente();
            }
        }
    }

    //SOTTO-SEZIONE LOGOUT
    public void sezioneLogout() {
        int choice;
        System.out.println("""
                Premi 1 per confermare il Logout.
                Altrimenti premi 0.
                """);
        choice = sc.nextInt();
        if (choice == 1) {
            this.home.welcomePage();
        } else if (choice == 0) {
            homeCliente();
        }
    }
}
