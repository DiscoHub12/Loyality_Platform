package loyality_platform_model.Interface;

import loyality_platform_model.Handler.*;
import loyality_platform_model.Models.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.exit;

/**
 * This is a prototype class, and represents the terminal interface for the owner,
 * hence the Backoffice section.
 * It will be composed of many methods that represent relationships and functions
 * between user terminal input and data output.
 * The Owner interface will consist of several parts, divided into methods.
 */
public class UI_Titolare {

    private final Scanner sc;
    private final GestorePuntoVendita titolare;
    private final Azienda azienda;
    private final HandlerAzienda gestoreAzienda = new HandlerAzienda();

    private final HandlerCliente gestoreCliente = new HandlerCliente();

    private final HandlerMessaggi gestoreMessaggi = new HandlerMessaggi();

    private final HandlerPremi gestorePremi = new HandlerPremi();

    private final HandlerProgrammaFedelta gestoreProgrammi = new HandlerProgrammaFedelta();

    private final HandlerVisite gestoreVisite = new HandlerVisite();

    private final Coalizione coalizione;

    /**
     * Constructor that create an Interface prototype
     * for the Backoffice (Owner)
     *
     * @param titolare the Owner (GestorePuntoVendita)
     */
    public UI_Titolare(Azienda azienda, GestorePuntoVendita titolare, Coalizione coalizione) {
        this.azienda = azienda;
        this.titolare = titolare;
        this.coalizione = coalizione;
        this.sc = new Scanner(System.in);
    }

    public void homeBackoffice() {
        int choice;
        System.out.println("""
                BENVENUTO
                Elenco le sezioni disponibili nella Dashboard Titolare:
                1. Backoffice
                2. Logout
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        if (choice == 1) {
            sezioneBackOffice();
        } else exit(0);
    }

    //SEZIONE BACKOFFICE PRINCIPALE
    public void sezioneBackOffice() {
        int choice;
        System.out.println("""
                SEZIONE BACKOFFICE
                Elenco tutte le attività nella sezione Backoffice:
                1. Sezione Spazio Fedeltà
                2. Sezione Programma Fedeltà
                3. Sezione Catalogo Premi
                4. Sezione Configurazioni
                5. Sezione Utenti
                6. Sezione Clienti iscritti
                7. Logout
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneSpazioFedelta();
            case 2 -> sezioneProgrammiFedelta();
            case 3 -> sezioneCatalogoPremi();
            case 4 -> sezioneConfigurazioni();
            case 5 -> sezioneUtenti();
            case 6 -> sezioneClientiIscritti();
            case 7 -> sezioneLogout();
        }
    }

    //SOTTO-SEZIONE SPAZIO FEDELTA (DI BACKOFFICE)
    public void sezioneSpazioFedelta() {
        int choice;
        System.out.println("""
                SEZIONE SPAZIO FEDELTA'
                Elenco tutti i dettagli del tuo Spazio Fedeltà : 
                
                """);
        System.out.println(this.gestoreAzienda.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()).toString());
        System.out.println("""
                Elenco le attività disponibili nella sezione Spazio Fedeltà:
                1. Modifica Spazio Fedeltà
                2. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> modificaSpazioFedelta();
            case 2 -> sezioneBackOffice();
        }
    }

    //SOTTO-SEZIONE PROGRAMMA FEDELTA (DI BACKOFFICE)
    public void sezioneProgrammiFedelta() {
        int choice;
        System.out.println("""
                SEZIONE PROGRAMMI FEDELTA' .
                Elenco tutti i Programmi Fedeltà attivi:
                                
                """);
        Set<ProgrammaFedelta> programmaFedeltas = this.gestoreAzienda.getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        if (programmaFedeltas != null) {
            for (ProgrammaFedelta programmaFedelta : programmaFedeltas) {
                System.out.println("Id Programma : " + programmaFedelta.getIdProgramma() + "Nome Programma : " + programmaFedelta.getNome());
            }
        } else System.out.println("Non hai ancora attivato nessun programma fedeltà.");
        System.out.println("""
                Elenco le attività disponibili nella sezione Programam Fedelta':
                1. Aggiungi Programma Fedeltà
                2. Gestisci Programma Fedeltà
                3. Rimuovi Programma Fedeltaà
                4. Ritorno alla Home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiProgrammaFedelta();
            case 2 -> sezioneProgrammaFedelta();
            case 3 -> rimuoviProgrammaFedelta();
            case 4 -> sezioneBackOffice();
        }
    }

    public void sezioneProgrammaFedelta() {
        int idProgramma;
        int choice;
        System.out.println("Inserisci l'id del Programma Fedeltà per vederne i dettagli : \n");
        idProgramma = sc.nextInt();
        System.out.println("Ecco i dettagli del Programma Fedeltà : \n " + this.gestoreProgrammi.getDetailsProgrammaFedelta(this.azienda.getIdAzienda(), idProgramma));
        System.out.println("""
                Elenco le attività disponibili:
                1. Aggiungi Catalogo Premi
                2. Modifica Programma Fedeltà
                3. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                //Todo implementare, manca getProgrammaFedeltàById(int idProgramma);
            }
            case 2 -> {
                //Todo controllare il tipo di programma, e richiamare la modifica corrispondente.
            }
            case 3 -> {

            }
        }
    }

    //SOTTO-SEZIONE CATALOGO PREMI (DI BACKOFFICE)
    public void sezioneCatalogoPremi() {
        int choice;
        Set<CatalogoPremi> catalogoPremi = this.gestoreAzienda.getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        System.out.println("""
                SEZIONE CATALOGO PREMI
                Elenco tutti i Cataloghi Premi attualmente disponibili :
                                
                """);
        if (catalogoPremi != null) {
            for (CatalogoPremi catalogoPremi1 : catalogoPremi) {
                System.out.println("Id Catalogo : " + catalogoPremi1.getIdCatalogoPremi());
            }
        } else System.out.println("Non hai ancora attivato nessun Catalogo Premi.");
        System.out.println("""
                Elenco le attività disponibili nella sezione Catalogo Premi :
                1. Aggiungi un nuovo Catalogo Premi
                2. Ritorna alla Home.
                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiCatalogoPremiGenerale();
            case 2 -> sezioneBackOffice();
        }
    }

    //SOTTO-SEZIONE CONFIGURAZIONI (DI BACKOFFICE)
    public void sezioneConfigurazioni() {
        int choice;
        System.out.println("""
                Elenco le attività disponibili nella sezione Configurazioni :
                1. Configurazioni Coupon
                2. Configurazioni SMS
                3. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneConfigurazioneCoupon();
            case 2 -> sezioneConfigurazioneSMS();
            case 3 -> sezioneBackOffice();
        }
    }

    public void sezioneConfigurazioneCoupon() {
        int choice;
        Set<Coupon> couponAzienda = this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        System.out.println("""
                SEZIONE CONFIGURAZIONE COUPON.
                Elenco tutti i Coupon creati precedentemente
                                
                """);
        if (couponAzienda != null) {
            System.out.println(this.azienda.catalogoPremiIfExist());

        } else System.out.println("Non hai ancora nessun coupon.");
        System.out.println("""
                Elenco le possibili operazioni :
                1. Aggiungi Coupon
                2. Ritorna alla Home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiCoupon();
            case 2 -> sezioneBackOffice();
        }
    }

    public void sezioneConfigurazioneSMS() {

    }

    //SOTTP-SEZIONE UTENTI (DI BACKOFFICE)
    public void sezioneUtenti() {
        int choice;
        Set<Dipendente> dipendenti = this.gestoreAzienda.getDipendentiAzienda(this.azienda.getIdAzienda());
        System.out.println("""
                SEZIONE ACCOUNT DIPENDENTI
                Elenco gli Account di tutti i Dipendenti della tua piattaforma:
                
                """);
        if (dipendenti != null) {
            for (Dipendente dipendente : dipendenti) {
                System.out.println("Id: " + dipendente.getIdDipendente() + "Nome Dipendente: " + dipendente.getNome());
            }
        } else System.out.println("Nessun account trovato.");
        System.out.println("""
                Elenco le possibili operazioni :
                1. Crea un nuovo utente
                2. Gestisci Dipendente
                3. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> creaNuovoDipendente();
            case 2 -> gestisciDipendente();
            case 3 -> sezioneBackOffice();
        }
    }

    //SOTTO-SEZIONE CLIENTI ISCRITTI (DI BACKOFFICE)
    public void sezioneClientiIscritti() {
        int choice;
        Set<Cliente> clientiIscritti = this.gestoreAzienda.getClientiAzienda(this.azienda.getIdAzienda(), this.coalizione);
        System.out.println("\nEcco la lista di tutti i tuoi clienti: ");
        if (clientiIscritti == null) {
            System.out.println("Clienti non disponibili.");
        } else {
            for (Cliente cliente : clientiIscritti)
                System.out.println(cliente.toString());
        }
        System.out.println("""
                Elenco le possibili operazioni :
                1. Identifica un Cliente
                2. Ritorna alla Home.
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> identificaCliente();
            case 2 -> sezioneBackOffice();
        }
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


    //---------------------METODI PRIVATI INTERNI DI INPUT--------------------------
    public void modificaSpazioFedelta() {
        int choice;
        String nome;
        String indirizzo;
        String numeroTelefono;
        String email = "";
        System.out.println("\nInserisci il nuovo nome per lo Spazio Fedeltà o premi invio per mantenere: \n");
        nome = sc.nextLine();
        System.out.println("\nInserisci il nuovo indirizzo per lo Spazio Fedletà o premi invio per mantenere: \n");
        indirizzo = sc.nextLine();
        System.out.println("\nInserisci il nuovo numero di telefono per lo spazio fedeltà o premi invio per mantenere: \n");
        numeroTelefono = sc.nextLine();
        System.out.println("\nInserisci la nuova email per lo spazio fedeltà o premi invio per mantenere: \n");
        email = sc.nextLine();
        SpazioFedelta spazioFedelta = new SpazioFedelta(nome, indirizzo, numeroTelefono, email);
        if (Objects.equals(nome, "")) {
            spazioFedelta.setNome(this.azienda.getSpazioFedelta().getNome());
        }
        if (Objects.equals(indirizzo, "")) {
            spazioFedelta.setIndirizzo(this.azienda.getSpazioFedelta().getIndirizzo());
        }
        if (Objects.equals(numeroTelefono, "")) {
            spazioFedelta.setNumeroTelefono(this.azienda.getSpazioFedelta().getNumeroTelefono());
        }
        if (Objects.equals(numeroTelefono, "")) {
            spazioFedelta.setEmail(this.azienda.getSpazioFedelta().getEmail());
        }
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestoreAzienda.modificaSpazioFedelta(this.azienda.getSpazioFedelta().getIdSpazioFedelta(), nome, indirizzo, numeroTelefono, email);
                System.out.println("Modifiche avvenute con successo.\nRitorno alla home");
                homeBackoffice();
            }
            case 2 -> {
                System.out.println("Hai annullato le modifiche.\nRitorno alla home.");
                homeBackoffice();
            }
            case 3 -> homeBackoffice();
        }

    }

    public void identificaCliente() {
        int choice;
        System.out.println("\nInserisci l'id del Cliente per prendere i dettagli : \n");
        choice = sc.nextInt();
        while (choice == 0) {
            System.out.println("\nId non valid. Reinserisci l'id del Cliente da identificare : \n");
            choice = sc.nextInt();
        }
        Cliente identificato = this.gestoreCliente.identificaClienteCodice(choice);
        if (identificato != null) {
            System.out.println("\nEcco le informazioni del cliente selezionato : " +
                    "\n" + identificato);
            System.out.println("""
                    Elenco le possibili operazioni :\s
                    1. Torna indietro
                    2. Torna alla Home del Backoffice
                    3. Logout.
                    Inserisci il numero corrispettivo
                                    
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> sezioneClientiIscritti();
                case 2 -> sezioneBackOffice();
                case 3 -> exit(0);
            }
        } else {
            System.out.println("Non è possibile identificare il Cliente.\n Ritorno alla schermata principale.");
            sezioneClientiIscritti();
        }
    }

    public void creaNuovoDipendente() {
        int choice;
        System.out.println("""
                Creazione di un nuovo Account per un dipendente.
                Inserisci il nome del Dipendente :
                                
                """);
        String nome = sc.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Dipendente : \n");
            nome = sc.nextLine();
        }
        System.out.println("Inserisci il cognome del Dipendente : ");
        String cognome = sc.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Dipendente : \n");
            cognome = sc.nextLine();
        }
        System.out.println("Inserisci la email del Dipendente : ");
        String email = sc.nextLine();
        while (Objects.equals(email, "") || email == null) {
            System.out.println("Email non valida. Reinserisci l'email del Dipendente : \n");
            email = sc.nextLine();
        }
        System.out.println("Inserisci 0 per l'accesso a tutta la piattaforma, 1 se ha accesso ristretto");
        int restrizioni = sc.nextInt();
        boolean restr;
        while (restrizioni < 0 || restrizioni > 1) {
            System.out.println("Restrizioni non valide. Reinserisci la restrizione,deve essere un numero 0 o 1 : \n");
            restrizioni = sc.nextInt();
        }
        restr = restrizioni != 0;
        System.out.println("""
                Premi :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestoreAzienda.aggiungiDipendente(this.azienda.getIdAzienda(), nome, cognome, email, restr);
                System.out.println("\nAccount per il dipendente creato correttamente.\nRitorno alla schermata principale.");
                sezioneUtenti();
            }
            case 2 -> sezioneUtenti();
            case 3 -> sezioneBackOffice();
        }
    }

    public void gestisciDipendente() {
        int choice;
        int idDipendente;
        System.out.println("\nInserisci l'id del Dipendente che vuoi gestire : \n");
        idDipendente = sc.nextInt();
        while (idDipendente == 0) {
            System.out.println("Id non valido. Reinserisci l'id del Dipendente : \n");
            idDipendente = sc.nextInt();
        }
        Dipendente identificato = this.gestoreAzienda.getDipendenteById(this.azienda.getIdAzienda(), idDipendente);
        if (identificato != null) {
            System.out.println("\nEcco le informazioni del Dipendente selezionato : " +
                    "\n" + identificato);
            System.out.println("""
                    Elenco le possibili operazioni :
                    1. Rimuovi Account Dipendente.
                    2. Modifica informazioni Dipendente.
                    3. Ritorna alla schermata precedente.
                    Inserisci il numero corrispettivo
                                        
                    """);
            choice = sc.nextInt();
            while (choice == 0) {
                System.out.println("Scelta non valida. Reinserisci il numero corrispettivo : ");
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("""
                            Sei sicuro di voler eliminare l'account ?\s
                            Inserisci :
                            1. Per rimuoverlo definitivamente.
                            2. Torna indietro.
                                                    
                            """);
                    choice = sc.nextInt();
                    if (choice == 1) {
                        this.gestoreAzienda.rimuoviDipendente(this.azienda.getIdAzienda(), idDipendente);
                        System.out.println("Account rimosso con successo.\nRitorno alla schermata principale.");
                        sezioneUtenti();
                    } else gestisciDipendente();
                }
                case 2 -> {
                    int restrizioni;
                    boolean restr;
                    String email = "";
                    System.out.println("Campi che puoi modificare : " +
                            "\nEmail : " + identificato.getEmail() +
                            "\nRestrizioni : " + identificato.isRestrizioni());
                    System.out.println("Inserisci le restrizioni (0 per acccesso completo, 1 per ristretto)\n");
                    restrizioni = sc.nextInt();
                    restr = restrizioni != 0;
                    System.out.println("\nVuoi cambiare l'email del Dipendente ? " +
                            "Inserisci : " +
                            "1. Per cambiare email ");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println("\nInserisci la nuova email del Dipendente.\n");
                        email = sc.nextLine();
                    }
                    System.out.println("""
                            Inserisci :
                            1. Conferma Creazione.
                            2. Annulla Creazione.
                            3. Ritorna alla home
                                            
                            """);
                    choice = sc.nextInt();
                    if (choice == 1) {
                        this.gestoreAzienda.modificaDipendente(this.azienda.getIdAzienda(), idDipendente, email, restr);
                        System.out.println("""
                                Modifiche avvenute con successo.
                                Ritorno alla schermata precedente con la lista aggiornata.
                                                                
                                """);
                        sezioneUtenti();
                    }
                }
                case 3 -> sezioneUtenti();
            }
        } else System.out.println("Dipendente con l'id non trovato.");

    }


    public void aggiungiProgrammaFedelta() {
        int choice;
        System.out.println("""
                Benvenuto nella creazione di un Programma Fedeltà.
                Inserisci il tipo di Programma (attraverso il numero corrispettivo) in base a quelli elencati:
                1. Programma a Punti
                2. Programma a Livelli
                3. Ritorna alla home.
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiProgrammaPunti();
            case 2 -> aggiungiProgrammaLivelli();
            case 3 -> sezioneBackOffice();
        }
    }

    public void aggiungiProgrammaPunti() {
        int choice;
        String nomeProgramma;
        double importoDaSpendere;
        int numeroPunti;
        String numeroMassimo = "";
        int numeroMassimoPunti = 0;
        int puntiPerVip;
        System.out.println("""
                Hai scelto Programma a Punti.
                Inserisci il nome che vuoi assegnare a questo programma :
                                    
                """);
        nomeProgramma = sc.nextLine();
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio : ");
        importoDaSpendere = sc.nextDouble();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto :");
        numeroPunti = sc.nextInt();
        System.out.println("Vuoi impostare un numero massimo di punti che un Cliente può acquisire in una singola spesa ? (SI-NO)");
        sc.nextLine();
        if (Objects.equals(numeroMassimo, "SI")) {
            System.out.println("""
                    Hai scelto che un Cliente può acquisire un numero massimo di punti in una sola spesa.
                    Inserisci il numero massimo.
                                            
                    """);
            numeroMassimoPunti = sc.nextInt();
        }
        System.out.println("Inserisci il numero di punti per diventare un cliente VIP: ");
        puntiPerVip = sc.nextInt();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Vuoi aggiungere un Catalogo Premi al Programma Fedeltà ? (SI-NO)");
                String scelta = sc.nextLine();
                if (Objects.equals(scelta, "SI")) {
                    aggiungiProgrammaFedelta();
                }
            }
            case 2 -> {
                System.out.println("Annullamento creazione. Ritorno alla schermata principale.");
                sezioneProgrammiFedelta();
            }
            case 3 -> sezioneBackOffice();
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAttivazione = dateFormat.format(date);
        this.gestoreProgrammi.aggiungiProgrammaPunti(this.azienda.getIdAzienda(), nomeProgramma, dataAttivazione, numeroMassimoPunti, puntiPerVip, numeroPunti, importoDaSpendere);
        System.out.println("""
                Programma Fedeltà aggiunto correttamente e in corso fin da subito.
                Ritorno alla home principale.
                                    
                """);
        sezioneBackOffice();
    }

    public void aggiungiProgrammaLivelli() {
        int choice;
        String nomeProgramma;
        int livelliMassimi;
        int livelloVIP;
        double importoDaSpendere;
        int numeroPunti;
        System.out.println("""
                Hai scelto Programma a Livelli.
                Inserisci il nome che vuoi assegnare a questo programma :
                                    
                """);
        nomeProgramma = sc.nextLine();
        System.out.println("Inserisci il numero di livelli massimi che un Cliente può raggiungere:\n");
        livelliMassimi = sc.nextInt();
        System.out.println("Inserisci il livello che permette al Cliente di passare a VIP:\n");
        livelloVIP = sc.nextInt();
        Map<Integer, Integer> policyLivelli = new HashMap<>();
        int livello = 1;
        for (int i = 0; i < livelliMassimi; i++) {
            System.out.println("Inserisci il numero di livelli da raggiungere per passare al livello " + livello);
            int puntis = sc.nextInt();
            policyLivelli.put(i, puntis);
            livello++;
        }
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio:\n");
        importoDaSpendere = sc.nextDouble();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto:\n");
        numeroPunti = sc.nextInt();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Vuoi aggiungere un Catalogo Premi al Programma Fedeltà ? (SI-NO)\n");
                String scelta = sc.nextLine();
                if (Objects.equals(scelta, "SI")) {
                    aggiungiCatalogoPremiGenerale();
                }
            }
            case 2 -> {
                System.out.println("Annullamento creazione. Ritorno alla schermata principale.\n");
                sezioneProgrammiFedelta();
            }
            case 3 -> sezioneBackOffice();
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dataAttivazione = dateFormat.format(date);
        this.gestoreProgrammi.aggiungiProgrammaLivelli(this.azienda.getIdAzienda(), nomeProgramma, dataAttivazione, livelliMassimi, livelloVIP, policyLivelli, numeroPunti, importoDaSpendere);
        System.out.println("""
                Programma Fedeltà aggiunto correttamente e in corso fin da subito.
                Ritorno alla home principale.
                                    
                """);
        sezioneBackOffice();
    }

    public void modificaProgrammaAPunti(ProgrammaPunti programmaPunti){
        System.out.println("Inserisci il nuovo nome per il programma, altrimenti premi invio per non modificare il valore : ");
        String nomeUpdated = sc.nextLine();
        if(nomeUpdated == null)
            nomeUpdated = programmaPunti.getNome();
        System.out.println("Inserisci il numero punti massimi : ");
        int numeroPuntiMassimi = sc.nextInt();
        if(numeroPuntiMassimi == 0)
            programmaPunti.getNumeroPuntiMassimi();
        System.out.println("Inserisci il numero di punti per diventare un Cliente VIP, altrimenti premi invio per non modificare il valore : ");
        int numeroPuntiVip = sc.nextInt();
        if(numeroPuntiVip == 0)
            numeroPuntiVip = programmaPunti.getPuntiVIP();
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio:\n");
        double importoDaSpendere = sc.nextDouble();
        if(importoDaSpendere == 0)
            importoDaSpendere = programmaPunti.getImportoSpesa();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto:\n");
        int numeroPunti = sc.nextInt();
        if(numeroPunti == 0)
            numeroPunti = programmaPunti.getPuntiSpesa();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> {
                this.gestoreProgrammi.modificaProgrammaPunti(this.azienda.getIdAzienda(), programmaPunti.getIdProgramma(), nomeUpdated, numeroPuntiMassimi, numeroPuntiVip, numeroPunti, importoDaSpendere);
                System.out.println("Programma modificato correttamente.\nRitorno alla home principale.");
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("Creazione annullata.\nRitorno alla home principale.");
                sezioneBackOffice();
            }
            case 3 -> {
                sezioneBackOffice();
            }
        }
    }

    public void modificaProgrammaALivelli(ProgrammaLivelli programmaLivelli) {
        System.out.println("Inserisci il nuovo nome per il programma, altrimenti premi invio per non modificare il valore : ");
        String nomeUpdated = sc.nextLine();
        if(nomeUpdated == null)
            nomeUpdated = programmaLivelli.getNome();
        System.out.println("Inserisci il numero massimo di livelli : ");
        int numeroLivelliMassimi = sc.nextInt();
        if(numeroLivelliMassimi == 0)
            numeroLivelliMassimi = programmaLivelli.getMassimoLivelli();
        System.out.println("Inserisci il numero di livelli per diventare un Cliente VIP, altrimenti premi invio per non modificare il valore : ");
        int numeroLivelloVIP = sc.nextInt();
        if(numeroLivelloVIP == 0)
            numeroLivelloVIP = programmaLivelli.getLivelloVip();
        //Todo finire.
    }

    public void rimuoviProgrammaFedelta() {
        int choice;
        int idProgramma;
        System.out.println("\nInserisci l'id del Programma Fedltà da rimuovere : \n");
        idProgramma = sc.nextInt();
        while (idProgramma == 0) {
            System.out.println("L'id del programma non può essere 0. Reinserisci l'id del programma.");
            idProgramma = sc.nextInt();
        }
        System.out.println("""
                Inserisci :
                1. Conferma Rimozione.
                2. Annulla Rimozione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestoreProgrammi.rimuoviProgrammaFedelta(this.azienda.getIdAzienda(), idProgramma);
                System.out.println("\nProgramma Fedeltà rimosso con successo. Ritorno alla schermata precedente.\n");
                sezioneProgrammiFedelta();
            }
            case 2 -> {
                System.out.println("Rimozione annullata.\nRitorno alla schermata precedente.");
                sezioneProgrammiFedelta();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    public void aggiungiCoupon() {
        int importoCoupon;
        String dataAttivazione;
        String dataScadenza;
        int choice;
        System.out.println("""
                Benvenuto nella creazione di un Coupon
                Inserisci l'importo per il Coupon :
                                
                """);
        importoCoupon = sc.nextInt();
        System.out.println("\nInserisci la data di attivazione del Coupon : \n");
        dataAttivazione = sc.nextLine();
        System.out.println("\nInserisci la data di scadenza del Coupon : \n");
        dataScadenza = sc.nextLine();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestorePremi.aggiungiCouponPreconfigurato(this.azienda.getIdAzienda(), importoCoupon, dataScadenza);
                System.out.println("\nCoupon creato correttamente. Ritorno alla schermata principale.\n");
                sezioneConfigurazioneCoupon();
            }
            case 2 -> sezioneConfigurazioneCoupon();
            case 3 -> sezioneBackOffice();
        }
    }

    public void aggiungiCatalogoPremiGenerale() {
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi : \n");
        String nome = sc.nextLine();
        int i = 0;
        int premi;
        Set<Premio> premiCreati = new HashSet<>();
        int choice;
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo
                    2. Se vuoi terminare l'aggiunta dei premi.
                                        
                    """);
            premi = sc.nextInt();
            if (premi == 1) {
                Premio premio = null;
                System.out.println("Inserisci il nome del Premio : \n");
                String nomePremio = sc.nextLine();
                System.out.println("""
                        Il Premio deve essere riscattato attraverso i punti o i livelli ?
                        1. Punti
                        2. Livelli
                                                
                        """);
                int isPunti = sc.nextInt();
                if (isPunti == 1) {
                    System.out.println("Inserisci il numero di punti per riscattare il premio : \n");
                    int numeroPuntiPerRiscatto = sc.nextInt();
                    premio = new Premio(nomePremio, true, numeroPuntiPerRiscatto);
                    premiCreati.add(premio);
                } else {
                    System.out.println("Inserisci il numero di livelli per riscattare il premio : \n");
                    int numeroLivelliPerRiscatto = sc.nextInt();
                    premio = new Premio(nomePremio, false, numeroLivelliPerRiscatto);
                    premiCreati.add(premio);
                }
            } else i = 1;
            System.out.println("""
                    Inserisci :
                    1. Conferma Creazione.
                    2. Annulla Creazione.
                    3. Ritorna alla home
                                    
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    this.gestorePremi.aggiungiCatalogoPremi(this.azienda.getIdAzienda(), premiCreati);
                    System.out.println("Catalogo Premi Generale aggiunto correttamente.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 2 -> {
                    System.out.println("Hai annullato l'operazione.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 3 -> {
                    sezioneBackOffice();
                }
            }
        }
    }
    public void aggiungiCatalogoPremiPPunti(int idProgramma) {
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi : \n");
        String nome = sc.nextLine();
        int i = 0;
        int premi;
        Set<Premio> premiCreati = new HashSet<>();
        int choice;
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo
                    2. Se vuoi terminare l'aggiunta dei premi. 
                                        
                    """);
            premi = sc.nextInt();
            if (premi == 1) {
                Premio premio = null;
                System.out.println("Inserisci il nome del Premio : \n");
                String nomePremio = sc.nextLine();
                System.out.println("Inserisci il numero di punti per riscattare il premio : \n");
                int numeroPunti = sc.nextInt();
                premio = new Premio(nomePremio, true, numeroPunti);
                premiCreati.add(premio);
            } else i = 1;
            System.out.println("""
                    Inserisci :
                    1. Conferma Creazione.
                    2. Annulla Creazione.
                    3. Ritorna alla home
                                    
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), idProgramma, premiCreati);
                    System.out.println("Catalogo Premi Generale aggiunto correttamente.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 2 -> {
                    System.out.println("Hai annullato l'operazione.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 3 -> {
                    sezioneBackOffice();
                }
            }
        }
    }

    public void aggiungiCatalogoPremiPLivelli(int idProgramma) {
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi : \n");
        String nome = sc.nextLine();
        int i = 0;
        int premi;
        Set<Premio> premiCreati = new HashSet<>();
        int choice;
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo
                    2. Se vuoi terminare l'aggiunta dei premi. 
                                        
                    """);
            premi = sc.nextInt();
            if (premi == 1) {
                Premio premio = null;
                System.out.println("Inserisci il nome del Premio : \n");
                String nomePremio = sc.nextLine();
                System.out.println("Inserisci il numero di livelli per riscattare il premio : \n");
                int numeroLivelliPerRiscatto = sc.nextInt();
                premio = new Premio(nomePremio, true, numeroLivelliPerRiscatto);
                premiCreati.add(premio);
            } else i = 1;
            System.out.println("""
                    Inserisci :
                    1. Conferma Creazione.
                    2. Annulla Creazione.
                    3. Ritorna alla home
                                    
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    this.gestorePremi.aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), idProgramma, premiCreati);
                    System.out.println("Catalogo Premi Generale aggiunto correttamente.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 2 -> {
                    System.out.println("Hai annullato l'operazione.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 3 -> {
                    sezioneBackOffice();
                }
            }
        }
    }
}
