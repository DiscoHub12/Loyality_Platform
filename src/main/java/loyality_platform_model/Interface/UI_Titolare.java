package loyality_platform_model.Interface;


import loyality_platform_model.Models.*;
import loyality_platform_model.Utils.Utils;

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

    /**
     *
     */
    private final Scanner sc;


    /**
     *
     */
    private final Azienda azienda;

    /**
     *
     */
    private final Coalizione coalizione;

    /**
     *
     */
    private final Utils gestori;


    /**
     * Constructor that create an Interface prototype
     * for the Backoffice (Owner)
     */
    public UI_Titolare(Azienda azienda, Coalizione coalizione) {
        this.azienda = azienda;
        this.coalizione = coalizione;
        this.gestori = new Utils();
        this.sc = new Scanner(System.in);
        homeBackoffice();
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
                7. Sezione Coalizione Azienda
                8. Logout
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
            case 7 -> sezioneCoalizioneAzienda();
            case 8 -> sezioneLogout();
        }
    }


    //-------------------SEZIONE SPAZIO FEDELTA-----------------------
    public void sezioneSpazioFedelta() {
        int choice;
        System.out.println("""
                SEZIONE SPAZIO FEDELTA'
                Dettagli del tuo Spazio Fedeltà :
                                
                """);
        SpazioFedelta spazioFedelta = this.gestori.getHandlerAzienda().getSpazioFedeltaAzienda(this.azienda.getIdAzienda());
        System.out.println(spazioFedelta.toString());
        System.out.println("""
                Elenco le attività disponibili nella sezione Spazio Fedeltà:
                1. Modifica Spazio Fedeltà
                2. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> modificaSpazioFedelta(spazioFedelta);
            case 2 -> sezioneBackOffice();
        }
    }


    //-------------------------SEZIONE PROGRAMMI FEDELTA -----------------------------

    //SEZIONE 1:
    public void sezioneProgrammiFedelta() {
        int choice;
        System.out.println("""
                SEZIONE PROGRAMMI FEDELTA' .
                Elenco tutti i Programmi Fedeltà attivi:
                                
                """);
        Set<ProgrammaFedelta> programmaFedeltas = this.gestori.getHandlerAzienda().getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        if (programmaFedeltas != null) {
            for (ProgrammaFedelta programmaFedelta : programmaFedeltas) {
                System.out.println("Id Programma : " + programmaFedelta.getIdProgramma() + "Nome Programma : " + programmaFedelta.getNome());
            }
        } else System.out.println("Non hai ancora attivato nessun programma fedeltà.");
        System.out.println("""
                Elenco le attività disponibili nella sezione Programam Fedelta':
                1. Aggiungi Programma Fedeltà
                2. Seleziona Programma Fedeltà
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

    //SEZIONE 2
    public void sezioneProgrammaFedelta() {
        int idProgramma;
        int choice;
        System.out.println("Inserisci l'id del Programma Fedeltà per vederne i dettagli : \n");
        idProgramma = sc.nextInt();
        ProgrammaFedelta programmaFedelta = this.gestori.getHandlerProgrammaFedelta().getProgrammaFedeltaById(this.azienda.getIdAzienda(), idProgramma);
        System.out.println("Ecco i dettagli del Programma Fedeltà : \n " + programmaFedelta.toString());
        System.out.println("""
                Elenco le attività disponibili:
                1. Aggiungi Catalogo Premi
                2. Controlla Catalogo Premi
                3. Modifica Programma Fedeltà
                4. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                if (programmaFedelta.getCatalogoPremi() != null) {
                    System.out.println("Il Programma Fedeltà possiede già un Catalogo Premi. Per aggiungere un nuovo Catalogo Premi, devi rimuovere quello già presente." +
                            "Desiseri continunare ? " +
                            "1. Rimuovi Catalogo Premi" +
                            "2. Annulla");
                }
                int choice1 = sc.nextInt();
                if (choice1 == 1) {
                    if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMAPUNTI) {
                        aggiungiCatalogoPremiPPunti(programmaFedelta);
                    } else {
                        aggiungiCatalogoPremiPLivelli(programmaFedelta);
                    }
                } else {
                    System.out.println("Ritorno alla schermata precedente.\n");
                    sezioneProgrammiFedelta();
                }
            }
            case 2 -> {
                if (programmaFedelta.getCatalogoPremi() == null) {
                    System.out.println("""
                            Questo programma fedeltà non ha ancora il Catalogo Premi.
                            Seleziona : 
                            1. Aggiungi un Catalogo Premi a questo Programma Fedeltà. 
                            2. Ritorna alla schermata Principale. 
                                                        
                            """);
                    int choice1 = sc.nextInt();
                    switch (choice1) {
                        case 1 -> {
                            if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMAPUNTI) {
                                aggiungiCatalogoPremiPPunti(programmaFedelta);
                            } else {
                                aggiungiCatalogoPremiPLivelli(programmaFedelta);
                            }
                        }
                        case 2 -> sezioneProgrammaFedelta();
                    }
                } else controllaCatalogoPremi(programmaFedelta.getCatalogoPremi(), programmaFedelta);
            }
            case 3 -> {
                if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMAPUNTI) {
                    modificaProgrammaAPunti(programmaFedelta.getProgrammaPunti());
                } else {
                    modificaProgrammaALivelli(programmaFedelta.getProgrammaLivelli());
                }
            }
            case 4 -> sezioneBackOffice();
        }
    }

    //---------------------SEZIONE CATALOGO PREMI -----------------------

    //SEZIONE 1
    public void sezioneCatalogoPremi() {
        int choice;
        Set<CatalogoPremi> catalogoPremi = this.gestori.getHandlerAzienda().getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        System.out.println("""
                SEZIONE CATALOGO PREMI
                Elenco tutti i Cataloghi Premi attualmente disponibili :
                                
                """);
        if (catalogoPremi != null) {
            for (CatalogoPremi catalogoPremi1 : catalogoPremi) {
                System.out.println("Id Catalogo : " + catalogoPremi1.getIdCatalogoPremi() + " numero premi al suo interno : " + catalogoPremi1.getPremiCatalogo().size());
            }
        } else System.out.println("Non hai ancora attivato nessun Catalogo Premi.");
        System.out.println("""
                Elenco le attività disponibili nella sezione Catalogo Premi :
                1. Aggiungi Catalogo Premi
                2. Controlla Catalogo Premi
                3. Ritorna alla Home.
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiCatalogoPremiGenerale();
            case 2 -> {
                System.out.println("Inserisci l'id del Catalogo Premi che vuoi controllare : \n");
                int idCatalogo = sc.nextInt();
                assert catalogoPremi != null;
                for (CatalogoPremi catalogoPremi1 : catalogoPremi) {
                    if (catalogoPremi1.getIdCatalogoPremi() == idCatalogo) {
                        controllaCatalogoPremi(catalogoPremi1, null);
                    }
                }
            }
            case 3 -> sezioneBackOffice();
        }
    }

    public void controllaCatalogoPremi(CatalogoPremi catalogoPremi, ProgrammaFedelta programmaFedelta) {
        int choice;
        System.out.println("Ecco i dettagli del Catalogo Premi : \n" + catalogoPremi.toString());
        System.out.println("""
                Elenco le attività disponibili :
                1. Modifica Catalogo Premi
                2. Rimuovi Catalogo Premi
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("""
                        Elenco le possibili modifiche :
                        1. Modifica premi
                        2. Aggiungi Premio
                        3. Rimuovi Premio.
                                                
                        """);
                int choice1 = sc.nextInt();
                switch (choice1) {
                    case 1 -> System.out.println("Modifica non disponibile.");
                    case 2 -> {
                        System.out.println("Inserisci il nome per il premio : \n");
                        String nome = sc.nextLine();
                        int numero = 0;
                        int tipo = 0;
                        if(programmaFedelta != null){
                            if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMAPUNTI) {
                                tipo = 1;
                                System.out.println("Inserisci il numero di punti per riscattare il premio : \n");
                                numero = sc.nextInt();
                            } else if(programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMALIVELLI){
                                System.out.println("Inserisci il numero di livelli per riscattare il premio : \n");
                                numero = sc.nextInt();
                            }
                        }else {
                            System.out.println("""
                                    Inserisci :
                                    1. Il Premio deve essere riscattato attraverso i punti. 
                                    2. Il Premio deve essere riscattato attraverso i livelli. 
                                    
                                    """);
                            int choice3 = sc.nextInt();
                            if(choice3 == 1){
                                System.out.println("Inserisci il numero di punti per riscattare il premio : ");
                                numero = sc.nextInt();
                                while (numero <= 1){
                                    System.out.println("Numero di punti non valido. Reinserisci il numnero di punti : ");
                                    numero = sc.nextInt();
                                }
                                tipo = 1;
                            }else {
                                System.out.println("Inserisci il numero di livelli per riscattare il premio : ");
                                numero = sc.nextInt();
                                while (numero <= 1){
                                    System.out.println("Numero di livelli non valido. Reinserisci il numnero di livelli : ");
                                    numero = sc.nextInt();
                                }
                            }
                        }
                        System.out.println("Sei sicuro di aggiungere il Premio al Catalogo Premi ? (SI-NO)\n");
                        String choice2 = sc.nextLine();
                        if (Objects.equals(choice2, "SI") || Objects.equals(choice2, "si")) {
                            if (programmaFedelta != null) {
                                this.gestori.getHandlerPremi().aggiungiPremioProgramma(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), nome, tipo == 1, numero);
                            } else this.gestori.getHandlerPremi().aggiungiPremioCatalogo(this.azienda.getIdAzienda(), catalogoPremi.getIdCatalogoPremi(), nome, tipo == 1, numero);
                        } else {
                            System.out.println("Ritorno alla schermata precedente.\n");
                            controllaCatalogoPremi(catalogoPremi, programmaFedelta);
                        }
                    }
                    case 3 -> {
                        System.out.println("Inserisci il nome del Premio che vuoi rimuovere tra quelli elencati : ");
                        String nome = sc.nextLine();
                        for (Premio premio : catalogoPremi.getPremiCatalogo()) {
                            if (Objects.equals(premio.getNome(), nome)) {
                                if (programmaFedelta != null) {
                                    this.gestori.getHandlerPremi().deletePremioProgramama(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), catalogoPremi.getIdCatalogoPremi(), premio.getIdPremio());
                                } else
                                    this.gestori.getHandlerPremi().deletePremio(this.azienda.getIdAzienda(), catalogoPremi.getIdCatalogoPremi(), premio.getIdPremio());
                            }
                        }
                    }
                }
            }
            case 2 -> {
                System.out.println("""
                        Sei sicuro di voler rimuovere il Catalogo Premi ? (SI-NO)
                                                
                        """);
                String choice3 = sc.nextLine();
                if (Objects.equals(choice3, "SI") || Objects.equals(choice3, "si")) {
                    if (programmaFedelta == null) {
                        this.gestori.getHandlerPremi().deleteCatalogoPremi(this.azienda.getIdAzienda(), catalogoPremi.getIdCatalogoPremi());
                    } else
                        this.gestori.getHandlerPremi().deleteCatalogoProgramma(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), catalogoPremi.getIdCatalogoPremi());
                }
            }
        }
    }


    //---------------------SEZIONE CONFIGURAZIONI --------------------

    //SEZIONE 1
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

    //SEZIONE 2
    public void sezioneConfigurazioneCoupon() {
        int choice;
        Set<Coupon> couponAzienda = this.gestori.getHandlerPremi().getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        System.out.println("""
                SEZIONE CONFIGURAZIONE COUPON.
                Elenco tutti i Coupon creati precedentemente
                                
                """);
        if (couponAzienda != null) {
            for (Coupon coupon : couponAzienda) {
                System.out.println("Id Coupon : " + coupon.getIdCoupon() + " Valore Sconto : " + coupon.getValoreSconto());
            }
        } else System.out.println("Non hai ancora nessun coupon.");
        System.out.println("""
                Elenco le possibili operazioni :
                1. Aggiungi Coupon
                2. Seleziona Coupon
                3. Ritorna alla Home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiCoupon();
            case 2 -> sezioneCouponPreconfigurato();
            case 3 -> homeBackoffice();
        }
    }

    //SEZIONE 3
    public void sezioneConfigurazioneSMS() {
        int choice;
        Set<SMS> smsPreconfigurati = this.gestori.getHandlerMessaggi().getSMSPreconfigurati(this.azienda.getIdAzienda());
        System.out.println("""
                SEZIONE CONFIGURAZIONI SMS
                Elenco tutti i tuoi SMS Preconfigurati creati precedentemente :
                                
                """);
        if (smsPreconfigurati != null) {
            for (SMS sms : smsPreconfigurati) {
                System.out.println("Id SMS : " + sms.getIdSMS() + " Messaggio Preconfigurato : " + sms.getConfigurazione().getTestoConfigurato());
            }
        } else System.out.println("Non hai ancora nessun messaggio preconfigurato.");
        System.out.println("""
                Elenco le possibili operazioni :
                1. Aggiungi SMS Preconfigurato
                2. Seleziona SMS Preconfigurato.
                3. Ritorna alla home.
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiSMSPreconfigurato();
            case 2 -> sezioneSMSPreconfigurato();
            case 3 -> sezioneBackOffice();
        }
    }


    //SEZIONE 4
    public void sezioneSMSPreconfigurato() {
        int choice;
        System.out.println("Inserisci l'id dell'SMS preconfigurato che vuoi gestire : \n");
        int idSMS = sc.nextInt();
        while (idSMS == 0) {
            System.out.println("Id non disponibile. Reinserisci l'id dell'SMS preconfigurato : \n");
            idSMS = sc.nextInt();
        }
        ConfigurazioneSMS sms = this.gestori.getHandlerMessaggi().getSMSPreconfigurato(this.azienda.getIdAzienda(), idSMS);
        if (sms != null) {
            System.out.println("""
                    Elenco le possibili operazioni :
                    1. Modifica SMS Preconfigurato
                    2. Rimuovi SMS Preconfigurato
                    3. Ritorna alla schermata precedente.
                    Inserisci il numero corrispettivo
                                        
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Inserisci il nuovo testo per l'SMS : \n");
                    String nuovoTesto = sc.nextLine();
                    while (Objects.equals(nuovoTesto, "") || nuovoTesto == null) {
                        System.out.println("Testo non valido. Reinserisci il testo : \n");
                        nuovoTesto = sc.nextLine();
                    }
                    while (Objects.equals(nuovoTesto, sms.getTestoConfigurato())) {
                        System.out.println("Il testo è uguale a quello già presente. Reinserisci il testo : ");
                        nuovoTesto = sc.nextLine();
                        System.out.println("""
                                Inserisci :
                                1. Conferma modifica.
                                2. Annulla modifica.
                                3. Ritorna alla home
                                                
                                """);
                        int choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1 -> {
                                //Todo implementare, manca HandlerMessaggi
                                System.out.println("Configurazione SMS modificata con successo. Ritorno alla schermata precedente.");
                                sezioneConfigurazioneSMS();
                            }
                            case 2 -> {
                                System.out.println("Hai annullato l'operazione. Ritorno alla schermata precedente.");
                                sezioneConfigurazioneSMS();
                            }
                            case 3 -> sezioneBackOffice();
                        }
                    }

                }
                case 2 -> {
                    System.out.println("Sei Sicuro di voler eliminare l'SMS Preconfigurato ? (SI-NO)");
                    String choice1 = sc.nextLine();
                    if (Objects.equals(choice1, "SI") || Objects.equals(choice1, "si")) {
                        //Todo implementare
                    } else {
                        System.out.println("Ritorno alla schermata principale.");
                        sezioneCouponPreconfigurato();
                    }
                }
            }
        } else {
            System.out.println("""
                    SMS non trovato. Inserisci :
                    1. Reinserisci l'id
                    2. Ritorna alla schermata precedente.
                                        
                    """);
            choice = sc.nextInt();
            if (choice == 1) {
                sezioneSMSPreconfigurato();
            } else sezioneConfigurazioneSMS();
        }
    }

    //SEZIONE 5
    public void sezioneCouponPreconfigurato() {
        int choice;
        System.out.println("\nInserisci l'id del Coupon che vuoi gestire : \n");
        int idCoupon = sc.nextInt();
        while (idCoupon == 0) {
            System.out.println("Id non valido. Reinserisci l'id del Coupon : \n");
            idCoupon = sc.nextInt();
        }
        Coupon coupon = this.gestori.getHandlerPremi().getCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), idCoupon);
        if (coupon != null) {
            System.out.println("\nEcco le informazioni del Coupon selezionato : " +
                    "\n" + coupon);
            System.out.println("""
                    Elenco le possibili operazioni :
                    1. Modifica Coupon Preconfigurato
                    2. Rimuovi Coupon Preconfigurato
                    3. Ritorna alla schermata precedente.
                    Inserisci il numero corrispettivo
                                        
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Inserisci il nuovo valore sconto o premi invio per non modificare questo campo : ");
                    int valoreSconto = sc.nextInt();
                    if (valoreSconto == 0)
                        valoreSconto = coupon.getValoreSconto();
                    System.out.println("Inserisci la nuova data di scadenza o premi invio per non modificare questo campo : ");
                    String dataScadenza = sc.nextLine();
                    if (Objects.equals(dataScadenza, "") || dataScadenza == null)
                        dataScadenza = coupon.getDataScadenza();
                    System.out.println("""
                            Inserisci :
                            1. Conferma modifica.
                            2. Annulla modifica.
                            3. Ritorna alla home
                                            
                            """);
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            this.gestori.getHandlerPremi().modificaCouponPreconfigurato(this.azienda.getIdAzienda(), coupon.getIdCoupon(), valoreSconto, dataScadenza);
                            System.out.println("Coupon Preconfigurato modificato correttamente. Ritorno alla schermata precedente.");
                            sezioneConfigurazioneCoupon();
                        }
                        case 2 -> {
                            System.out.println("Annulamento modifiche.Ritorno alla schermata principale");
                            sezioneConfigurazioneCoupon();
                        }
                        case 3 -> sezioneBackOffice();
                    }

                }
                case 2 -> {
                    System.out.println("Sei Sicuro di voler eliminare il Coupon ? (SI-NO)");
                    String choice1 = sc.nextLine();
                    if (Objects.equals(choice1, "SI") || Objects.equals(choice1, "si")) {
                        this.gestori.getHandlerPremi().deleteCouponPreconfigurato(this.azienda.getIdAzienda(), coupon.getIdCoupon());
                    } else {
                        System.out.println("Ritorno alla schermata principale.");
                        sezioneCouponPreconfigurato();
                    }
                }
            }
        } else {
            System.out.println("""
                    Coupon non trovato. Inserisci :
                    1. Reinserisci l'id
                    2. Ritorna alla schermata precedente.
                                        
                    """);
            choice = sc.nextInt();
            if (choice == 1) {
                sezioneCouponPreconfigurato();
            } else sezioneConfigurazioneCoupon();
        }
    }

    //-----------------------SEZIONE UTENTI----------------------

    //SEZIONE 1
    public void sezioneUtenti() {
        int choice;
        Set<Dipendente> dipendenti = this.gestori.getHandlerAzienda().getDipendentiAzienda(this.azienda.getIdAzienda());
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
                2. Seleziona Dipendente
                3. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> creaNuovoDipendente();
            case 2 -> sezioneDipendente();
            case 3 -> sezioneBackOffice();
        }
    }


    //SEZIONE 2
    public void sezioneDipendente() {
        int choice;
        int idDipendente;
        System.out.println("\nInserisci l'id del Dipendente che vuoi gestire : \n");
        idDipendente = sc.nextInt();
        while (idDipendente == 0) {
            System.out.println("Id non valido. Reinserisci l'id del Dipendente : \n");
            idDipendente = sc.nextInt();
        }
        Dipendente identificato = this.gestori.getHandlerAzienda().getDipendenteById(this.azienda.getIdAzienda(), idDipendente);
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
                        this.gestori.getHandlerAzienda().rimuoviDipendente(this.azienda.getIdAzienda(), idDipendente);
                        System.out.println("Account rimosso con successo.\nRitorno alla schermata principale.");
                        sezioneUtenti();
                    } else sezioneDipendente();
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
                        this.gestori.getHandlerAzienda().modificaDipendente(this.azienda.getIdAzienda(), idDipendente, email, restr);
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

    //-------------------------SEZIONE CLIENTI ISCRITTI-----------------------------

    //SEZIONE 1
    public void sezioneClientiIscritti() {
        int choice;
        Set<Cliente> clientiIscritti = this.gestori.getHandlerAzienda().getClientiAzienda(this.azienda.getIdAzienda(), this.coalizione);
        System.out.println("\nEcco la lista di tutti i tuoi clienti: ");
        if (clientiIscritti == null) {
            System.out.println("Clienti non disponibili.");
        } else {
            for (Cliente cliente : clientiIscritti)
                System.out.println("Id Cliente : " + cliente.getIdCliente() + " Nome Cliente : " + cliente.getNome());
        }
        System.out.println("""
                Elenco le possibili operazioni :
                1. Identifica un Cliente
                2. Ritorna alla Home.
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneCliente();
            case 2 -> sezioneBackOffice();
        }
    }

    //SEZIONE 2
    public void sezioneCliente() {
        int choice;
        System.out.println("\nInserisci l'id del Cliente per prendere i dettagli : \n");
        choice = sc.nextInt();
        while (choice == 0) {
            System.out.println("\nId non valid. Reinserisci l'id del Cliente da identificare : \n");
            choice = sc.nextInt();
        }
        Cliente identificato = this.gestori.getHandlerCliente().identificaClienteCodice(choice);
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

    //-----------------------SEZIONE COALIZIONE AZIENDA----------------------------

    //SEZIONE 1
    public void sezioneCoalizioneAzienda() {
        System.out.println("""
                SEZIONE COALIZIONE AZIENDA
                In questa sezione, verranno mostrati i tuoi Programmi Fedeltà
                e le corrispettive Aziende che ne sono iscritte.
                                
                """);
        for (ProgrammaFedelta programmaFedelta : this.gestori.getHandlerAzienda().getProgrammiFedeltaAzienda(this.azienda.getIdAzienda())) {
            System.out.println("Programma Fedeltà : " +
                    "Id Programma : " + programmaFedelta.getIdProgramma() +
                    "Nome Programma : " + programmaFedelta.getNome() +
                    "Aziende iscritte : ");
            if (this.coalizione.getAziendeIscritteProgramma(programmaFedelta.getIdProgramma()) != null) {
                for (Azienda azienda : this.coalizione.getAziendeIscritteProgramma(programmaFedelta.getIdProgramma())) {
                    System.out.println("-Id Azienda : " + azienda.getIdAzienda() +
                            "-Nome Azienda : " + azienda.getSpazioFedelta().getNome() +
                            "-Indirizzo Azienda : " + azienda.getSpazioFedelta().getIndirizzo());
                }
            } else
                System.out.println("Nessun Azienda iscritta al tuo Programma Fedeltà con l'id : " + programmaFedelta.getIdProgramma());
        }
    }

    //-------------------------SEZIONE LOGOUT--------------------

    //SEZIONE 1
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


    //---------------------METODI PRIVATI INTERNI -------------------------

    //1) METODO MODIFICA SPAZIO FEDELTA
    private void modificaSpazioFedelta(SpazioFedelta spazioFedelta) {
        int choice;
        String nome;
        String indirizzo;
        String numeroTelefono;
        String email;
        System.out.println("\nInserisci il nuovo nome per lo Spazio Fedeltà o premi invio per mantenere: \n");
        nome = sc.nextLine();
        if (Objects.equals(nome, "") || nome == null)
            nome = spazioFedelta.getNome();
        System.out.println("\nInserisci il nuovo indirizzo per lo Spazio Fedletà o premi invio per mantenere: \n");
        indirizzo = sc.nextLine();
        if (Objects.equals(indirizzo, "") || indirizzo == null)
            indirizzo = spazioFedelta.getIndirizzo();
        System.out.println("\nInserisci il nuovo numero di telefono per lo spazio fedeltà o premi invio per mantenere: \n");
        numeroTelefono = sc.nextLine();
        if (Objects.equals(numeroTelefono, "") || numeroTelefono == null)
            numeroTelefono = spazioFedelta.getNumeroTelefono();
        System.out.println("\nInserisci la nuova email per lo spazio fedeltà o premi invio per mantenere: \n");
        email = sc.nextLine();
        if (Objects.equals(email, "") || email == null)
            email = spazioFedelta.getEmail();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestori.getHandlerAzienda().modificaSpazioFedelta(this.azienda.getSpazioFedelta().getIdSpazioFedelta(), nome, indirizzo, numeroTelefono, email);
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


    private void creaNuovoDipendente() {
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
                this.gestori.getHandlerAzienda().aggiungiDipendente(this.azienda.getIdAzienda(), nome, cognome, email, restr);
                System.out.println("\nAccount per il dipendente creato correttamente.\nRitorno alla schermata principale.");
                sezioneUtenti();
            }
            case 2 -> sezioneUtenti();
            case 3 -> sezioneBackOffice();
        }
    }


    private void aggiungiProgrammaFedelta() {
        int choice;
        System.out.println("""
                Benvenuto nella creazione di un Programma Fedeltà.
                Inserisci il tipo di Programma che vuoi creare(attraverso il numero corrispettivo) in base a quelli elencati:
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

    private void aggiungiProgrammaPunti() {
        int choice;
        String nomeProgramma;
        double importoDaSpendere;
        int numeroPunti;
        String numeroMassimo = "";
        int numeroMassimoPunti = 0;
        int puntiPerVip;
        System.out.println("""
                Hai scelto Programma a Punti.
                Inserisci il nome che vuoi assegnare a questo programma :""");
        nomeProgramma = sc.nextLine();
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio : ");
        importoDaSpendere = Double.parseDouble(sc.nextLine());
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto :");
        numeroPunti = sc.nextInt();
        System.out.println("Vuoi impostare un numero massimo di punti che un Cliente può acquisire in una singola spesa ? (SI-NO)");
        sc.nextLine();
        if (Objects.equals(numeroMassimo, "SI")) {
            System.out.println("""
                    Hai scelto che un Cliente può acquisire un numero massimo di punti in una sola spesa.
                    Inserisci il numero massimo:
                                            
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
        Set<Premio> premiCatalogo = new HashSet<>();
        switch (choice) {
            case 1 -> {
                System.out.println("Vuoi aggiungere un Catalogo Premi al Programma Fedeltà ? (SI-NO)");
                String scelta = sc.nextLine();
                if (Objects.equals(scelta, "SI")) {
                    premiCatalogo = compilaCatalogoPremiPunti();
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
        CatalogoPremi catalogoPremi = this.gestori.getHandlerPremi().creaCatalogo(premiCatalogo);
        this.gestori.getHandlerProgrammaFedelta().aggiungiProgrammaPunti(this.azienda.getIdAzienda(), nomeProgramma, dataAttivazione, numeroMassimoPunti, puntiPerVip, numeroPunti, importoDaSpendere, catalogoPremi);
        System.out.println("""
                Programma Fedeltà aggiunto correttamente e in corso fin da subito.
                Ritorno alla home principale.
                                    
                """);
        sezioneBackOffice();
    }

    private void aggiungiProgrammaLivelli() {
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
            System.out.println("Inserisci il numero di punti da raggiungere per passare al livello " + livello);
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
        Set<Premio> premiCatalogo = new HashSet<>();
        switch (choice) {
            case 1 -> {
                System.out.println("Vuoi aggiungere un Catalogo Premi al Programma Fedeltà ? (SI-NO)\n");
                String scelta = sc.nextLine();
                if (Objects.equals(scelta, "SI")) {
                    premiCatalogo = compilaCatalogoPremiLivelli();
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
        CatalogoPremi catalogoPremi = this.gestori.getHandlerPremi().creaCatalogo(premiCatalogo);
        this.gestori.getHandlerProgrammaFedelta().aggiungiProgrammaLivelli(this.azienda.getIdAzienda(), nomeProgramma, dataAttivazione, livelliMassimi, livelloVIP, policyLivelli, numeroPunti, importoDaSpendere, catalogoPremi);
        System.out.println("""
                Programma Fedeltà aggiunto correttamente e in corso fin da subito.
                Ritorno alla home principale.
                                    
                """);
        sezioneBackOffice();
    }

    private void modificaProgrammaAPunti(ProgrammaPunti programmaPunti) {
        System.out.println("Inserisci il nuovo nome per il programma, altrimenti premi invio per non modificare il valore : ");
        String nomeUpdated = sc.nextLine();
        if (nomeUpdated == null)
            nomeUpdated = programmaPunti.getNome();
        System.out.println("Inserisci il numero punti massimi : ");
        int numeroPuntiMassimi = sc.nextInt();
        if (numeroPuntiMassimi == 0)
            numeroPuntiMassimi = programmaPunti.getNumeroPuntiMassimi();
        System.out.println("Inserisci il numero di punti per diventare un Cliente VIP, altrimenti premi invio per non modificare il valore : ");
        int numeroPuntiVip = sc.nextInt();
        if (numeroPuntiVip == 0)
            numeroPuntiVip = programmaPunti.getPuntiVIP();
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio:\n");
        double importoDaSpendere = sc.nextDouble();
        if (importoDaSpendere == 0)
            importoDaSpendere = programmaPunti.getImportoSpesa();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto:\n");
        int numeroPunti = sc.nextInt();
        if (numeroPunti == 0)
            numeroPunti = programmaPunti.getPuntiSpesa();
        System.out.println("""
                Inserisci :
                1. Conferma modifica.
                2. Annulla modifica.
                3. Ritorna alla home
                                
                """);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestori.getHandlerProgrammaFedelta().modificaProgrammaPunti(this.azienda.getIdAzienda(), programmaPunti.getIdProgramma(), nomeUpdated, numeroPuntiMassimi, numeroPuntiVip, numeroPunti, importoDaSpendere);
                System.out.println("Programma modificato correttamente.\nRitorno alla home principale.");
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("Creazione annullata.\nRitorno alla home principale.");
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private void modificaProgrammaALivelli(ProgrammaLivelli programmaLivelli) {
        System.out.println("Inserisci il nuovo nome per il programma, altrimenti premi invio per non modificare il valore : ");
        String nomeUpdated = sc.nextLine();
        if (nomeUpdated == null)
            nomeUpdated = programmaLivelli.getNome();
        System.out.println("Inserisci il numero massimo di livelli : ");
        int numeroLivelliMassimi = sc.nextInt();
        if (numeroLivelliMassimi == 0)
            numeroLivelliMassimi = programmaLivelli.getMassimoLivelli();
        System.out.println("Inserisci il numero di livelli per diventare un Cliente VIP, altrimenti premi invio per non modificare il valore : ");
        int numeroLivelloVIP = sc.nextInt();
        if (numeroLivelloVIP == 0)
            numeroLivelloVIP = programmaLivelli.getLivelloVip();
        System.out.println("Vuoi modificare la Policy per i livelli ? (SI-NO)\n");
        String choiceS = sc.nextLine();
        Map<Integer, Integer> updatedMap = new HashMap<>();
        if (Objects.equals(choiceS, "SI") || Objects.equals(choiceS, "si"))
            updatedMap = modificaPolicy(numeroLivelliMassimi);
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio:\n");
        double importoDaSpendere = sc.nextDouble();
        if (importoDaSpendere == 0)
            importoDaSpendere = programmaLivelli.getImportoSpesa();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto:\n");
        int numeroPunti = sc.nextInt();
        if (numeroPunti == 0)
            numeroPunti = programmaLivelli.getPuntiSpesa();
        System.out.println("""
                Inserisci :
                1. Conferma modifica.
                2. Annulla modifica.
                3. Ritorna alla home
                                
                """);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestori.getHandlerProgrammaFedelta().modificaProgrammaLivelli(this.azienda.getIdAzienda(), programmaLivelli.getIdProgramma(), nomeUpdated, numeroLivelliMassimi, numeroLivelloVIP, updatedMap, numeroPunti, importoDaSpendere);
                System.out.println("Programma modificato correttamente.\nRitorno alla home principale.");
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("Creazione annullata.\nRitorno alla home principale.");
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private Map<Integer, Integer> modificaPolicy(int numeroMassimoLivelli) {
        System.out.println("Hai scelto di modificare la policy.\n");
        Map<Integer, Integer> updatedMap = new HashMap<>();
        int livelloInCorso = 1;
        for (int i = 0; i < numeroMassimoLivelli; i++) {
            System.out.println("Inserisci il numero di punti per passare al livello " + livelloInCorso);
            int numeroPuntiPerLivello = sc.nextInt();
            updatedMap.put(i, numeroPuntiPerLivello);
            livelloInCorso++;
        }
        System.out.println("Policy Livelli aggiornata correttamente.");
        return updatedMap;
    }

    private void rimuoviProgrammaFedelta() {
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
                this.gestori.getHandlerProgrammaFedelta().rimuoviProgrammaFedelta(this.azienda.getIdAzienda(), idProgramma);
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

    private void aggiungiCoupon() {
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
                this.gestori.getHandlerPremi().aggiungiCouponPreconfigurato(this.azienda.getIdAzienda(), importoCoupon, dataScadenza);
                System.out.println("\nCoupon creato correttamente. Ritorno alla schermata principale.\n");
                sezioneConfigurazioneCoupon();
            }
            case 2 -> sezioneConfigurazioneCoupon();
            case 3 -> sezioneBackOffice();
        }
    }

    private void aggiungiSMSPreconfigurato() {
        System.out.println("""
                Benvenuto nella creazione di un SMS Preconfigurato.,
                Inserisci il testo del messaggio :
                                
                """);
        String testoConfigurato = sc.nextLine();
        while (Objects.equals(testoConfigurato, "") || testoConfigurato == null) {
            System.out.println("Testo non valido. Reinserisci il testo per il messaggio preconfigurato : ");
            testoConfigurato = sc.nextLine();
        }
        System.out.println("""
                Inserisci :
                1. Conferma creazione.
                2. Annulla creazione.
                3. Ritorna alla home.
                                
                """);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                //Todo implementare, manca i metodi in HandlerMessaggi.
            }
            case 2 -> {
                System.out.println("Hai annullato l'operazione. Ritorno alla home principale.");
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }

    }

    private void aggiungiCatalogoPremiGenerale() {
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
                Premio premio;
                System.out.println("Inserisci il nome del Premio : \n");
                String nomePremio = sc.nextLine();
                System.out.println("""
                        Il Premio deve essere riscattato attraverso i punti o i livelli ? (inserisci il numero corrispettivo)
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
                    this.gestori.getHandlerPremi().aggiungiCatalogoPremi(this.azienda.getIdAzienda(), premiCreati);
                    System.out.println("Catalogo Premi Generale aggiunto correttamente.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 2 -> {
                    System.out.println("Hai annullato l'operazione.\nRitorno alla home.");
                    sezioneBackOffice();
                }
                case 3 -> sezioneBackOffice();
            }
        }
    }

    private void aggiungiCatalogoPremiPPunti(ProgrammaFedelta programmaFedelta) {
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi : \n");
        String nome = sc.nextLine();
        int choice;
        Set<Premio> premiCreati = this.compilaCatalogoPremiPunti();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestori.getHandlerPremi().aggiungiCatalogoAProgrammaPunti(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), premiCreati);
                System.out.println("Catalogo Premi Generale aggiunto correttamente.\nRitorno alla home.");
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("Hai annullato l'operazione.\nRitorno alla home.");
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private void aggiungiCatalogoPremiPLivelli(ProgrammaFedelta programmaFedelta) {
        if (programmaFedelta.getCatalogoPremi() != null) {
            System.out.println("""
                    Il Programma Fedeltà possiede già un Catalogo Premi.
                    Per inserire il nuovo Catalogo, questo deve essere rimosso.
                    Desideri continuare ? (SI-NO)
                                        
                    """);
            String choice = sc.nextLine();
            if (Objects.equals(choice, "NO") || Objects.equals(choice, "no")) {
                System.out.println("Ritorno alla schermata principale.");
                sezioneProgrammaFedelta();
            }
        }
        Set<Premio> premiCreati = this.compilaCatalogoPremiLivelli();
        int choice;
        System.out.println("""
                Inserisci :
                1. Conferma aggiunta.
                2. Annulla aggiunta.
                3. Ritorna alla home
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.gestori.getHandlerPremi().aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), premiCreati);
                System.out.println("Catalogo Premi Generale aggiunto correttamente.\nRitorno alla home.");
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("Hai annullato l'operazione.\nRitorno alla home.");
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private Set<Premio> compilaCatalogoPremiPunti() {
        int i = 0;
        int premi;
        Set<Premio> premiCreati = new HashSet<>();
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo
                    2. Se vuoi terminare l'aggiunta dei premi.
                                        
                    """);
            premi = sc.nextInt();
            if (premi == 1) {
                Premio premio;
                System.out.println("Inserisci il nome del Premio : \n");
                String nomePremio = sc.nextLine();
                System.out.println("Inserisci il numero di punti per riscattare il premio : \n");
                int numeroPuntiPerRiscatto = sc.nextInt();
                premio = new Premio(nomePremio, true, numeroPuntiPerRiscatto);
                premiCreati.add(premio);
            } else i = 1;
        }
        return premiCreati;
    }

    private Set<Premio> compilaCatalogoPremiLivelli() {
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi : \n");
        String nome = sc.nextLine();
        int i = 0;
        int premi;
        Set<Premio> premiCreati = new HashSet<>();
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo
                    2. Se vuoi terminare l'aggiunta dei premi.
                                        
                    """);
            premi = sc.nextInt();
            if (premi == 1) {
                Premio premio;
                System.out.println("Inserisci il nome del Premio : \n");
                String nomePremio = sc.nextLine();
                System.out.println("Inserisci il numero di livelli per riscattare il premio : \n");
                int numeroLivelliPerRiscatto = sc.nextInt();
                premio = new Premio(nomePremio, true, numeroLivelliPerRiscatto);
                premiCreati.add(premio);
            } else i = 1;
        }
        return premiCreati;

    }
}
