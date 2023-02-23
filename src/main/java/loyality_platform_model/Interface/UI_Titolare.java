package loyality_platform_model.Interface;


import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import loyality_platform_model.Utils.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This is a prototype class, and represents the terminal interface for the owner,
 * hence the Backoffice section.
 * It will be composed of many methods that represent relationships and functions
 * between user terminal input and data output.
 * The Owner interface will consist of several parts, divided into methods.
 */
public class UI_Titolare {

    /**
     * The Scanner for the input by terminal.
     */
    private final Scanner sc;


    /**
     * The Company that access.
     */
    private final Azienda azienda;

    /**
     * This attributes rapresent the Home UI.
     * In fact, when the User want to log out ,
     * the system return to the Home.
     */
    private final UI_Home home;


    /**
     * The Utils class that contains
     * all handler.
     */
    private final Utils handlers;


    /**
     * Constructor that create an Interface prototype
     * for the Backoffice (Owner)
     */
    public UI_Titolare(Azienda azienda, UI_Home home) {
        this.azienda = azienda;
        this.handlers = new Utils();
        this.home = home;
        this.sc = new Scanner(System.in);
        //homeBackoffice();
        sezioneBackOffice();
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
        } else {
            sc.nextLine();
            this.home.welcomePage();
        }
    }

    //SEZIONE BACKOFFICE PRINCIPALE
    public void sezioneBackOffice() {
        int choice;
        System.out.println("""
                SEZIONE BACKOFFICE
                Elenco tutte le attivita' nella sezione Backoffice:
                1. Sezione Spazio Fedelta'
                2. Sezione Programma Fedelta'
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
                Dettagli del tuo Spazio Fedelta' :
                """);
        SpazioFedelta spazioFedelta = this.handlers.getHandlerAzienda().getSpazioFedeltaAzienda(this.azienda.getIdAzienda());
        System.out.println(spazioFedelta.toString());
        System.out.println("""
                \nElenco le attivita' disponibili nella sezione Spazio Fedelta':
                1. Modifica Spazio Fedelta'
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
        sc.nextLine();
        int choice;
        Set<ProgrammaFedelta> programmaFedeltas = this.handlers.getHandlerAzienda().getProgrammiFedeltaAzienda(this.azienda.getIdAzienda());
        if (programmaFedeltas == null || programmaFedeltas.isEmpty()) {
            System.out.println("Non hai ancora attivato nessun programma fedelta'.");
        } else {
            System.out.println("""
                    SEZIONE PROGRAMMI FEDELTA' .
                    Elenco tutti i Programmi Fedelta' attivi:
                    """);
            for (ProgrammaFedelta programmaFedelta : programmaFedeltas) {
                System.out.println("Id Programma : " + programmaFedelta.getIdProgramma() + " Nome Programma : " + programmaFedelta.getNome());
            }
        }
        System.out.println("""
                \nElenco le attivita' disponibili nella sezione Programam Fedelta':
                1. Aggiungi Programma Fedelta'
                2. Seleziona Programma Fedelta'
                3. Rimuovi Programma Fedeltaa'
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
        System.out.println("Inserisci l'id del Programma Fedelta' per vederne i dettagli :");
        idProgramma = sc.nextInt();
        ProgrammaFedelta programmaFedelta = this.handlers.getHandlerProgrammaFedelta().getProgrammaFedeltaById(this.azienda.getIdAzienda(), idProgramma);
        System.out.println("Ecco i dettagli del Programma Fedelta' : \n " + programmaFedelta.toString());
        System.out.println("""
                \nElenco le attivita' disponibili:
                1. Aggiungi Catalogo Premi
                2. Controlla Catalogo Premi
                3. Modifica Programma Fedelta'
                4. Ritorna alla home
                Inserisci il numero corrispettivo
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                if (programmaFedelta.getCatalogoPremi() != null || !programmaFedelta.getCatalogoPremi().getPremiCatalogo().isEmpty()) {
                    System.out.println("""
                            Il Programma Fedelta' possiede gia' un Catalogo Premi.
                            Per aggiungere un nuovo Catalogo Premi, devi rimuovere quello gia' presente.
                            Desideri continuare ?
                            1. Rimuovi Catalogo Premi.
                            2. Annulla.
                            """);
                }
                int choice1 = sc.nextInt();
                if (choice1 == 1) {
                    if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMAPUNTI) {
                        aggiungiCatalogoPremiPPunti(programmaFedelta);
                    } else {
                        aggiungiCatalogoPremiPLivelli(programmaFedelta);
                    }
                } else {
                    System.out.println("Ritorno alla schermata precedente.");
                    sezioneProgrammiFedelta();
                }
            }
            case 2 -> {
                if (programmaFedelta.getCatalogoPremi() == null) {
                    System.out.println("""
                            Questo programma fedelta' non ha ancora il Catalogo Premi.
                            Seleziona :
                            1. Aggiungi un Catalogo Premi a questo Programma Fedelta'.
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
        sc.nextLine();
        int choice;
        Set<CatalogoPremi> catalogoPremi = this.handlers.getHandlerAzienda().getCatalogoPremiAzienda(this.azienda.getIdAzienda());
        if (catalogoPremi == null || catalogoPremi.isEmpty()) {
            System.out.println("Non hai ancora attivato nessun Catalogo Premi.");
        } else {
            System.out.println("""
                    SEZIONE CATALOGO PREMI
                    Elenco tutti i Cataloghi Premi attualmente disponibili :
                    """);
            for (CatalogoPremi catalogoPremi1 : catalogoPremi) {
                System.out.println("Id Catalogo : " + catalogoPremi1.getIdCatalogoPremi() + " numero premi al suo interno : " + catalogoPremi1.getPremiCatalogo().size());
            }
        }
        System.out.println("""
                \nElenco le attivita' disponibili nella sezione Catalogo Premi :
                1. Aggiungi Catalogo Premi
                2. Controlla Catalogo Premi
                3. Ritorna alla Home.
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> aggiungiCatalogoPremiGenerale();
            case 2 -> {
                System.out.println("Inserisci l'id del Catalogo Premi che vuoi controllare :");
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
        sc.nextLine();
        int choice;
        System.out.println("Ecco i dettagli del Catalogo Premi : \n" + catalogoPremi.toString());
        System.out.println("""
                \nElenco le attivita' disponibili :
                1. Modifica Catalogo Premi
                2. Rimuovi Catalogo Premi
                3. Ritorna alla schermata precedente.
                4. Ritorna alla Home.
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
                    case 1 -> {
                        System.out.println("""
                                Sezione non disponibile.
                                Ritorno alla schermata precedente. 
                                """);
                        sezioneCatalogoPremi();
                    }
                    case 2 -> {
                        sc.nextLine();
                        System.out.println("Inserisci il nome per il premio :");
                        String nome = sc.nextLine();
                        int numero = 0;
                        int tipo = 0;
                        if (programmaFedelta != null) {
                            if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMAPUNTI) {
                                tipo = 1;
                                System.out.println("Inserisci il numero di punti per riscattare il premio :");
                                numero = sc.nextInt();
                            } else if (programmaFedelta.getTipoProgramma() == Tipo.PROGRAMMALIVELLI) {
                                System.out.println("Inserisci il numero di livelli per riscattare il premio :");
                                numero = sc.nextInt();
                            }
                        } else {
                            System.out.println("""
                                    Inserisci :
                                    1. Il Premio deve essere riscattato attraverso i punti.
                                    2. Il Premio deve essere riscattato attraverso i livelli.
                                    """);
                            int choice3 = sc.nextInt();
                            if (choice3 == 1) {
                                System.out.println("Inserisci il numero di punti per riscattare il premio :");
                                numero = sc.nextInt();
                                while (numero <= 1) {
                                    System.out.println("Numero di punti non valido. Reinserisci il numnero di punti :");
                                    numero = sc.nextInt();
                                }
                                tipo = 1;
                            } else {
                                System.out.println("Inserisci il numero di livelli per riscattare il premio :");
                                numero = sc.nextInt();
                                while (numero <= 1) {
                                    System.out.println("Numero di livelli non valido. Reinserisci il numnero di livelli :");
                                    numero = sc.nextInt();
                                }
                            }
                        }
                        sc.nextLine();
                        System.out.println("Sei sicuro di aggiungere il Premio al Catalogo Premi ? (SI-NO)");
                        String choice2 = sc.nextLine();
                        if (Objects.equals(choice2, "SI") || Objects.equals(choice2, "si")) {
                            if (programmaFedelta != null) {
                                this.handlers.getHandlerPremi().aggiungiPremioProgramma(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), nome, tipo == 1, numero);
                                System.out.println("""
                                        Premio aggiunto correttamente. 
                                        Ritorno alla schermata precedente.
                                        """);
                                controllaCatalogoPremi(catalogoPremi, programmaFedelta);
                            } else
                                this.handlers.getHandlerPremi().aggiungiPremioCatalogo(this.azienda.getIdAzienda(), catalogoPremi.getIdCatalogoPremi(), nome, tipo == 1, numero);
                            System.out.println("""
                                    Premio aggiunto correttamente. 
                                    Ritorno alla schermata precedente.
                                    """);
                            controllaCatalogoPremi(catalogoPremi, programmaFedelta);
                        } else {
                            System.out.println("Ritorno alla schermata precedente.");
                            controllaCatalogoPremi(catalogoPremi, programmaFedelta);
                        }
                    }
                    case 3 -> {
                        System.out.println("Inserisci il nome del Premio che vuoi rimuovere tra quelli elencati :");
                        String nome = sc.nextLine();
                        for (Premio premio : catalogoPremi.getPremiCatalogo()) {
                            if (Objects.equals(premio.getNome(), nome)) {
                                if (programmaFedelta != null) {
                                    this.handlers.getHandlerPremi().deletePremioProgramama(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), catalogoPremi.getIdCatalogoPremi(), premio.getIdPremio());
                                } else
                                    this.handlers.getHandlerPremi().deletePremio(this.azienda.getIdAzienda(), catalogoPremi.getIdCatalogoPremi(), premio.getIdPremio());
                            }
                        }
                    }
                }
            }
            case 2 -> {
                sc.nextLine();
                System.out.println("""
                        Sei sicuro di voler rimuovere il Catalogo Premi ? (SI-NO)
                        """);
                String choice3 = sc.nextLine();
                if (Objects.equals(choice3, "SI") || Objects.equals(choice3, "si")) {
                    if (programmaFedelta == null) {
                        this.handlers.getHandlerPremi().deleteCatalogoPremi(this.azienda.getIdAzienda(), catalogoPremi.getIdCatalogoPremi());
                        System.out.println("""
                                Catalogo Premi Rimosso correttamente. 
                                Ritorno alla schermata precedente.
                                """);
                        sezioneCatalogoPremi();
                    } else
                        this.handlers.getHandlerPremi().deleteCatalogoProgramma(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), catalogoPremi.getIdCatalogoPremi());
                    System.out.println("""
                            Catalogo Premi rimosso correttamente. 
                            Ritorno alla schermata precedente. 
                            """);
                    sezioneProgrammiFedelta();
                }
            }
            case 3 -> sezioneCatalogoPremi();
            case 4 -> sezioneBackOffice();
        }
    }


    //---------------------SEZIONE CONFIGURAZIONI --------------------

    //SEZIONE 1
    public void sezioneConfigurazioni() {
        int choice;
        System.out.println("""
                \nElenco le attivita' disponibili nella sezione Configurazioni :
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
        Set<Coupon> couponAzienda = this.handlers.getHandlerPremi().getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda());
        if (couponAzienda == null || couponAzienda.isEmpty()) {
            System.out.println("Non hai ancora nessun coupon.");
        } else {
            System.out.println("""
                    SEZIONE CONFIGURAZIONE COUPON.
                    Elenco tutti i Coupon creati precedentemente :
                    """);
            for (Coupon coupon : couponAzienda) {
                System.out.println("Id Coupon : " + coupon.getIdCoupon() + " Valore Sconto : " + coupon.getValoreSconto());
            }
        }
        System.out.println("""
                \nElenco le possibili operazioni :
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
        sc.nextLine();
        int choice;
        Set<SMS> smsPreconfigurati = this.handlers.getHandlerMessaggi().getSMSPreconfigurati(this.azienda.getIdAzienda());
        if (smsPreconfigurati == null || smsPreconfigurati.isEmpty()) {
            System.out.println("Non hai ancora nessun messaggio preconfigurato.");
        } else {
            System.out.println("""
                    SEZIONE CONFIGURAZIONI SMS
                    Elenco tutti i tuoi SMS Preconfigurati creati precedentemente :
                    """);
            for (SMS sms : smsPreconfigurati) {
                if(sms.getConfigurazione() != null)
                    System.out.println("Id SMS : " + sms.getIdSMS() + " Messaggio Preconfigurato : " + sms.getConfigurazione().getTestoConfigurato());
            }
        }
        System.out.println("""
                \nElenco le possibili operazioni :
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
        System.out.println("Inserisci l'id dell'SMS preconfigurato che vuoi gestire :");
        int idSMS = sc.nextInt();
        while (idSMS == 0) {
            System.out.println("Id non disponibile. Reinserisci l'id dell'SMS preconfigurato :");
            idSMS = sc.nextInt();
        }
        ConfigurazioneSMS sms = this.handlers.getHandlerMessaggi().getSMSPreconfigurato(this.azienda.getIdAzienda(), idSMS);
        if (sms != null) {
            System.out.println("""
                    \nElenco le possibili operazioni :
                    1. Modifica SMS Preconfigurato
                    2. Rimuovi SMS Preconfigurato
                    3. Ritorna alla schermata precedente.
                    Inserisci il numero corrispettivo
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    sc.nextLine();
                    System.out.println("Inserisci il nuovo testo per l'SMS :");
                    String nuovoTesto = sc.nextLine();
                    while (Objects.equals(nuovoTesto, "") || nuovoTesto == null) {
                        System.out.println("Testo non valido. Reinserisci il testo :");
                        nuovoTesto = sc.nextLine();
                    }
                    while (Objects.equals(nuovoTesto, sms.getTestoConfigurato())) {
                        System.out.println("Il testo e' uguale a quello gia' presente. Reinserisci il testo :");
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
                                this.handlers.getHandlerMessaggi().aggiungiConfigurazioneSMS(this.azienda.getIdAzienda(), nuovoTesto);
                                System.out.println("""
                                        Configurazione SMS modificata con successo.
                                        Ritorno alla schermata precedente.
                                        """);
                                sezioneConfigurazioneSMS();
                            }
                            case 2 -> {
                                System.out.println("""
                                        Operazione annullata.
                                        Ritorno alla schermata precedente.
                                        """);
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
                        this.handlers.getHandlerMessaggi().rimuoviConfigurazioenSMS(this.azienda.getIdAzienda(), idSMS);
                        System.out.println("""
                                SMS Preconfigurato rimosso correttamente.
                                Ritorno alla schermata precedente.
                                """);
                        sezioneConfigurazioneSMS();
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
        System.out.println("Inserisci l'id del Coupon che vuoi gestire :");
        int idCoupon = sc.nextInt();
        while (idCoupon == 0) {
            System.out.println("Id non valido. Reinserisci l'id del Coupon :");
            idCoupon = sc.nextInt();
        }
        Coupon coupon = this.handlers.getHandlerPremi().getCouponPreconfiguratoAzienda(this.azienda.getIdAzienda(), idCoupon);
        if (coupon != null) {
            System.out.println("Ecco le informazioni del Coupon selezionato : " +
                    "\n" + coupon.toString());
            System.out.println("""
                    \nElenco le possibili operazioni :
                    1. Modifica Coupon Preconfigurato
                    2. Rimuovi Coupon Preconfigurato
                    3. Ritorna alla schermata precedente.
                    Inserisci il numero corrispettivo
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    sc.nextLine();
                    System.out.println("Inserisci il nuovo valore sconto o premi invio per non modificare questo campo :");
                    int valoreSconto = sc.nextInt();
                    if (valoreSconto == 0)
                        valoreSconto = coupon.getValoreSconto();
                    sc.nextLine();
                    System.out.println("Inserisci la nuova data di scadenza o premi invio per non modificare questo campo :");
                    String dataScadenza = sc.nextLine();
                    if (Objects.equals(dataScadenza, "") || dataScadenza == null)
                        dataScadenza = coupon.getDataScadenza();
                    sc.nextLine();
                    System.out.println("""
                            Inserisci :
                            1. Conferma modifica.
                            2. Annulla modifica.
                            3. Ritorna alla home
                            """);
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            sc.nextLine();
                            this.handlers.getHandlerPremi().modificaCouponPreconfigurato(this.azienda.getIdAzienda(), coupon.getIdCoupon(), valoreSconto, dataScadenza);
                            System.out.println("""
                                    Coupon Preconfigurato modificato correttamente
                                    Ritorno alla schermata precedente.
                                    """);
                            sezioneConfigurazioneCoupon();
                        }
                        case 2 -> {
                            System.out.println("""
                                    Operazione annullata.
                                    Ritorno alla schermata precedente.
                                    """);
                            sezioneConfigurazioneCoupon();
                        }
                        case 3 -> sezioneBackOffice();
                    }

                }
                case 2 -> {
                    sc.nextLine();
                    System.out.println("Sei Sicuro di voler eliminare il Coupon ? (SI-NO)");
                    String choice1 = sc.nextLine();
                    if (Objects.equals(choice1, "SI") || Objects.equals(choice1, "si")) {
                        this.handlers.getHandlerPremi().deleteCouponPreconfigurato(this.azienda.getIdAzienda(), coupon.getIdCoupon());
                        System.out.println("""
                                Coupon rimosso correttamente.
                                Ritorno alla schermata precedente.
                                """);
                        sezioneConfigurazioneCoupon();

                    } else {
                        System.out.println("Ritorno alla schermata principale.");
                        sezioneCouponPreconfigurato();
                    }
                }
                case 3 -> {
                    sezioneConfigurazioneCoupon();
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
        Set<Dipendente> dipendenti = this.handlers.getHandlerAzienda().getDipendentiAzienda(this.azienda.getIdAzienda());
        if (dipendenti == null || dipendenti.isEmpty()) {

        }
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
                \nElenco le possibili operazioni :
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
        System.out.println("\nInserisci l'id del Dipendente che vuoi gestire :");
        idDipendente = sc.nextInt();
        while (idDipendente == 0) {
            System.out.println("Id non valido. Reinserisci l'id del Dipendente :");
            idDipendente = sc.nextInt();
        }
        Dipendente identificato = this.handlers.getHandlerAzienda().getDipendenteById(this.azienda.getIdAzienda(), idDipendente);
        if (identificato != null) {
            System.out.println("\nEcco le informazioni del Dipendente selezionato : " +
                    "\n" + identificato.toString());
            System.out.println("""
                    \nElenco le possibili operazioni :
                    1. Rimuovi Account Dipendente.
                    2. Modifica informazioni Dipendente.
                    3. Ritorna alla schermata precedente.
                    Inserisci il numero corrispettivo
                    """);
            choice = sc.nextInt();
            while (choice == 0) {
                System.out.println("Scelta non valida. Reinserisci il numero corrispettivo :");
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("""
                            Sei sicuro di voler eliminare l'account ?
                            Inserisci :
                            1. Per rimuoverlo definitivamente.
                            2. Torna indietro.
                            """);
                    choice = sc.nextInt();
                    if (choice == 1) {
                        this.handlers.getHandlerAzienda().rimuoviDipendente(this.azienda.getIdAzienda(), idDipendente);
                        System.out.println("""
                                Account rimosso con successo.
                                Ritorno alla schermata principale.
                                """);
                        sezioneUtenti();
                    } else sezioneDipendente();
                }
                case 2 -> {
                    int restrizioni;
                    boolean restr;
                    String email = "";
                    System.out.println("Campi che puoi modificare : " +
                            "Email : " + identificato.getEmail() +
                            "Restrizioni : " + identificato.isRestrizioni());
                    System.out.println("Inserisci le restrizioni (0 per acccesso completo, 1 per ristretto)");
                    restrizioni = sc.nextInt();
                    restr = restrizioni != 0;
                    System.out.println("Vuoi cambiare l'email del Dipendente ? " +
                            "Inserisci : " +
                            "1. Per cambiare email ");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        sc.nextLine();
                        System.out.println("Inserisci la nuova email del Dipendente :");
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
                        this.handlers.getHandlerAzienda().modificaDipendente(this.azienda.getIdAzienda(), idDipendente, email, restr);
                        System.out.println("""
                                Modifiche avvenute con successo.
                                Ritorno alla schermata precedente con la lista aggiornata.
                                """);
                        sezioneUtenti();
                    }
                }
                case 3 -> sezioneUtenti();
            }
        } else {
            System.out.println("""
                    Dipendente con il seguente id non trovato.
                    Ritorno alla schermata precedente.
                    """);
            sezioneDipendente();
        }


    }

    //-------------------------SEZIONE CLIENTI ISCRITTI-----------------------------

    //SEZIONE 1
    public void sezioneClientiIscritti() {
        sc.nextLine();
        int choice;
        Set<Cliente> clientiIscritti = this.handlers.getHandlerAzienda().getClientiAzienda(this.azienda.getIdAzienda(), DBMS.getInstance().getCoalizione());
        System.out.println("Ecco la lista di tutti i tuoi clienti :");
        if (clientiIscritti == null || clientiIscritti.isEmpty()) {
            System.out.println("""
                    Nessun Cliente iscritto al/ai Programmi Fedelta'.
                    Ritorno alla Home.
                    """);
            sezioneBackOffice();
        } else {
            for (Cliente cliente : clientiIscritti)
                System.out.println("Id Cliente : " + cliente.getIdCliente() + " Nome Cliente : " + cliente.getNome());
            System.out.println("""
                    \nElenco le possibili operazioni :
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
    }

    //SEZIONE 2
    public void sezioneCliente() {
        int choice;
        System.out.println("Inserisci l'id del Cliente per prendere i dettagli :");
        choice = sc.nextInt();
        while (choice == 0) {
            System.out.println("Id non valid. Reinserisci l'id del Cliente da identificare :");
            choice = sc.nextInt();
        }
        Cliente identificato = this.handlers.getHandlerCliente().identificaClienteCodice(choice);
        if (identificato != null) {
            System.out.println("Ecco le informazioni del cliente selezionato : " +
                    "\n" + identificato);
            System.out.println("""
                    \nElenco le possibili operazioni :
                    1. Torna indietro
                    2. Torna alla Home del Backoffice
                    3. Logout.
                    Inserisci il numero corrispettivo
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> sezioneClientiIscritti();
                case 2 -> sezioneBackOffice();
                case 3 -> {
                    sc.nextLine();
                    this.home.welcomePage();
                }
            }
        } else {
            System.out.println("""
                    Non e' stato possibile identificare il Cliente.
                    Ritorno alla schermata precedente.
                    """);
            sezioneClientiIscritti();
        }
    }

    //-----------------------SEZIONE COALIZIONE AZIENDA----------------------------

    //SEZIONE 1
    public void sezioneCoalizioneAzienda() {
        sc.nextLine();
        System.out.println("""
                \nElenco le possibili operazioni : 
                1. Partecipa Coalizione. 
                2. Vedi Aziende iscritte.
                3. Torna alla Home.
                """);
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> partecipaCoalizione();
            case 2 -> aziendeIscritteCoalizione();
            case 3 -> sezioneBackOffice();
        }
    }

    public void partecipaCoalizione(){
        sc.nextLine();
        System.out.println("""
                SEZIONE PARTECIPA COALIZIONE
                Qui sottostante, verranno mostrate le Aziende iscritte 
                alla piattaforma, e i relativi Programmi Fedeltà. 
                """);
        Map<ProgrammaFedelta, Set<Azienda>> aziendeProgrammi = DBMS.getInstance().getCoalizione().getAziendePerProgramma();
        if(aziendeProgrammi == null || aziendeProgrammi.isEmpty()){
            System.out.println("""
                    Nessun azienda iscritta e nessun programma fedeltà disponibile. 
                    Ritorno alla schermata precedente.. 
                    """);
            sezioneCoalizioneAzienda();
        }else {
            for(ProgrammaFedelta programmaFedelta : aziendeProgrammi.keySet()){
                System.out.println("\nPROGRAMMA FEDELTA : " +
                        "\n" + programmaFedelta.toString());
                for(Azienda azienda : aziendeProgrammi.get(programmaFedelta)){
                    System.out.println( "\nAZIENDE ISCRITTE : " +
                            "\nNome Azienda : " + azienda.getSpazioFedelta().getNome());
                }
            }
        }
        System.out.println("""
                Inserisci l'id del Programma Fedeltà a cui vuoi iscriverti:
                """);
        int programmaFedelta = sc.nextInt();
        assert aziendeProgrammi != null;
        for(ProgrammaFedelta programmaFedelta1 : aziendeProgrammi.keySet()){
            if(programmaFedelta == programmaFedelta1.getIdProgramma()){
                if(this.handlers.getHandlerProgrammaFedelta().aggiungiProgrammaEsistente(azienda.getIdAzienda(), programmaFedelta1)){
                    System.out.println("""
                        Programma aggiunto correttamente.
                        Ritorno alla sezione Coalizione.
                        """);
                    sezioneCoalizioneAzienda();
                }else {
                    System.out.println("""
                            Non è possibile aggiungere il seguente Programma Fedeltà.
                            Ritorno alla schermata precedente.
                            """);
                    partecipaCoalizione();
                }
            }
        }


    }

    public void aziendeIscritteCoalizione(){
        sc.nextLine();
        System.out.println("""
                SEZIONE COALIZIONE AZIENDA
                In questa sezione, verranno mostrati i tuoi Programmi Fedelta'
                e le corrispettive Aziende che ne sono iscritte.
                """);
        if (this.handlers.getHandlerAzienda().getProgrammiFedeltaAzienda(this.azienda.getIdAzienda()) != null) {
            for (ProgrammaFedelta programmaFedelta : this.handlers.getHandlerAzienda().getProgrammiFedeltaAzienda(this.azienda.getIdAzienda())) {
                System.out.println("Programma Fedelta' : " +
                        "Id Programma : " + programmaFedelta.getIdProgramma() +
                        "Nome Programma : " + programmaFedelta.getNome() +
                        "Aziende iscritte : ");
                if (DBMS.getInstance().getCoalizione().getAziendeIscritteProgramma(programmaFedelta.getIdProgramma()) != null) {
                    for (Azienda azienda : DBMS.getInstance().getCoalizione().getAziendeIscritteProgramma(programmaFedelta.getIdProgramma())) {
                        System.out.println("-Id Azienda : " + azienda.getIdAzienda() +
                                "-Nome Azienda : " + azienda.getSpazioFedelta().getNome() +
                                "-Indirizzo Azienda : " + azienda.getSpazioFedelta().getIndirizzo());
                    }
                } else
                    System.out.println("Nessun Azienda iscritta al tuo Programma Fedelta' con l'id : " + programmaFedelta.getIdProgramma());
            }
        } else System.out.println("Nessun Programma Fedelta' attivo e nessuna Azienda partecipante.");
        System.out.println("Inserisci 1 per ritornare alla schermata principale :");
        int choice = sc.nextInt();
        if (choice == 1)
            sezioneBackOffice();
    }

    //-------------------------SEZIONE LOGOUT--------------------

    //SEZIONE 1
    public void sezioneLogout() {
        int choice;
        System.out.println("Inserisci 1 per confermare il Logout :");
        choice = sc.nextInt();
        if (choice == 1) {
            sc.nextLine();
            this.home.welcomePage();
        }
    }


    //---------------------METODI PRIVATI INTERNI -------------------------

    //1) METODO MODIFICA SPAZIO FEDELTA
    private void modificaSpazioFedelta(SpazioFedelta spazioFedelta) {
        sc.nextLine();
        int choice;
        String nome;
        String indirizzo;
        String numeroTelefono;
        String email;
        System.out.println("Inserisci il nuovo nome per lo Spazio Fedelta' o premi invio per mantenere :");
        nome = sc.nextLine();
        if (Objects.equals(nome, "") || nome == null)
            nome = spazioFedelta.getNome();
        //sc.close();
        System.out.println("Inserisci il nuovo indirizzo per lo Spazio Fedleta' o premi invio per mantenere :");
        indirizzo = sc.nextLine();
        if (Objects.equals(indirizzo, "") || indirizzo == null)
            indirizzo = spazioFedelta.getIndirizzo();
        //sc.close();
        System.out.println("Inserisci il nuovo numero di telefono per lo spazio fedelta' o premi invio per mantenere :");
        numeroTelefono = sc.nextLine();
        if (Objects.equals(numeroTelefono, "") || numeroTelefono == null)
            numeroTelefono = spazioFedelta.getNumeroTelefono();
        //sc.close();
        System.out.println("Inserisci la nuova email per lo spazio fedelta' o premi invio per mantenere :");
        email = sc.nextLine();
        if (Objects.equals(email, "") || email == null)
            email = spazioFedelta.getEmail();
        //sc.close();
        System.out.println("""
                Inserisci :
                1. Conferma Modifiche.
                2. Annulla Modifiche.
                3. Ritorna alla home
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.handlers.getHandlerAzienda().modificaSpazioFedelta(this.azienda.getSpazioFedelta().getIdSpazioFedelta(), nome, indirizzo, numeroTelefono, email);
                System.out.println("""
                        Modifiche avvenute con successo.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("""
                        Operazione annullata.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }

    }


    private void creaNuovoDipendente() {
        sc.nextLine();
        int choice;
        System.out.println("""
                Creazione di un nuovo Account per un dipendente.
                Inserisci il nome del Dipendente :
                                
                """);
        String nome = sc.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Dipendente :");
            nome = sc.next();
        }
        System.out.println("Inserisci il cognome del Dipendente : ");
        String cognome = sc.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Dipendente :");
            cognome = sc.next();
        }
        System.out.println("Inserisci la email del Dipendente : ");
        String email = sc.nextLine();
        while (Objects.equals(email, "") || email == null) {
            System.out.println("Email non valida. Reinserisci l'email del Dipendente :");
            email = sc.nextLine();
        }
        System.out.println("Crea una password temporanea per il Dipendente : ");
        String password = sc.nextLine();
        while (Objects.equals(password, "") || password == null) {
            System.out.println("Email non valida. Reinserisci l'email del Dipendente :");
            password = sc.nextLine();
        }
        System.out.println("Inserisci 0 per l'accesso a tutta la piattaforma, 1 se ha accesso ristretto :");
        int restrizioni = sc.nextInt();
        boolean restr;
        while (restrizioni < 0 || restrizioni > 1) {
            System.out.println("Restrizioni non valide. Reinserisci la restrizione,deve essere un numero 0 o 1 :");
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
                this.handlers.getHandlerAzienda().aggiungiDipendente(this.azienda.getIdAzienda(), nome, cognome, email, password, restr);
                System.out.println("""
                        Account Dipendente creato correttamente.
                        Ritorno alla Home.
                        """);
                sezioneUtenti();
            }
            case 2 -> sezioneUtenti();
            case 3 -> sezioneBackOffice();
        }
    }


    private void aggiungiProgrammaFedelta() {
        sc.nextLine();
        int choice;
        System.out.println("""
                Benvenuto nella creazione di un Programma Fedelta'.
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
        sc.nextLine();
        int choice;
        int numeroMassimoPunti = 0;
        int puntiPerVip;
        System.out.println("""
                Hai scelto Programma a Punti.
                Inserisci il nome che vuoi assegnare a questo programma :
                """);
        String nomeProgramma = sc.nextLine();
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio :");
        double importoDaSpendere = Double.parseDouble(sc.nextLine());
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto :");
        int numeroPunti = sc.nextInt();
        sc.nextLine();
        System.out.println("Vuoi impostare un numero massimo di punti che un Cliente puo' acquisire in una singola spesa ? (SI-NO)");
        String numeroMassimo = sc.nextLine();
        if (Objects.equals(numeroMassimo, "SI")) {
            System.out.println("""
                    Hai scelto che un Cliente puo' acquisire un numero massimo di punti in una sola spesa.
                    Inserisci il numero massimo:
                    """);
            numeroMassimoPunti = sc.nextInt();
        }
        System.out.println("Inserisci il numero di punti per diventare un cliente VIP :");
        puntiPerVip = sc.nextInt();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                """);
        choice = sc.nextInt();
        Set<Premio> premiCatalogo = new HashSet<>();
        String nomeCatalogo = "";
        switch (choice) {
            case 1 -> {
                sc.nextLine();
                System.out.println("Vuoi aggiungere un Catalogo Premi al Programma Fedelta' ? (SI-NO)");
                String scelta = sc.nextLine();
                if (Objects.equals(scelta, "SI")) {
                    System.out.println("Inserisci il nome per il Catalogo Premi :");
                    nomeCatalogo = sc.nextLine();
                    premiCatalogo = compilaCatalogoPremiPunti();
                }
            }
            case 2 -> {
                System.out.println("""
                        Operazione annullata.
                        Ritorno alla Home.
                        """);
                sezioneProgrammiFedelta();
            }
            case 3 -> sezioneBackOffice();
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataAttivazione = dateFormat.format(date);
        CatalogoPremi catalogoPremi = this.handlers.getHandlerPremi().creaCatalogo(nomeCatalogo, premiCatalogo);
        this.handlers.getHandlerProgrammaFedelta().aggiungiProgrammaPunti(this.azienda.getIdAzienda(), nomeProgramma, dataAttivazione, numeroMassimoPunti, puntiPerVip, numeroPunti, importoDaSpendere, catalogoPremi);
        System.out.println("""
                Programma Fedelta' aggiunto correttamente.
                Ritorno alla Home.
                """);
        sezioneBackOffice();
    }

    private void aggiungiProgrammaLivelli() {
        sc.nextLine();
        int choice;
        System.out.println("""
                Hai scelto Programma a Livelli.
                Inserisci il nome che vuoi assegnare a questo programma :
                """);
        String nomeProgramma = sc.nextLine();
        System.out.println("Inserisci il numero di livelli massimi che un Cliente puo' raggiungere :");
        int livelliMassimi = sc.nextInt();
        System.out.println("Inserisci il livello che permette al Cliente di passare a VIP :");
        int livelloVIP = sc.nextInt();
        Map<Integer, Integer> policyLivelli = new HashMap<>();
        int livello = 1;
        for (int i = 0; i < livelliMassimi; i++) {
            System.out.println("Inserisci il numero di punti da raggiungere per passare al livello " + livello);
            int puntis = sc.nextInt();
            policyLivelli.put(i, puntis);
            livello++;
        }
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio :");
        double importoDaSpendere = sc.nextDouble();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto :");
        int numeroPunti = sc.nextInt();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                """);
        choice = sc.nextInt();
        Set<Premio> premiCatalogo = new HashSet<>();
        String nomeCatalogo = "";
        switch (choice) {
            case 1 -> {
                sc.nextLine();
                System.out.println("Vuoi aggiungere un Catalogo Premi al Programma Fedelta' ? (SI-NO)");
                String scelta = sc.nextLine();
                if (Objects.equals(scelta, "SI")) {
                    System.out.println("Inserisci il nome per il Catalogo : ");
                    nomeCatalogo = sc.nextLine();
                    premiCatalogo = compilaCatalogoPremiLivelli();
                }
            }
            case 2 -> {
                System.out.println("Annullamento creazione. Ritorno alla schermata principale.");
                sezioneProgrammiFedelta();
            }
            case 3 -> sezioneBackOffice();
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dataAttivazione = dateFormat.format(date);
        CatalogoPremi catalogoPremi = this.handlers.getHandlerPremi().creaCatalogo(nomeCatalogo, premiCatalogo);
        this.handlers.getHandlerProgrammaFedelta().aggiungiProgrammaLivelli(this.azienda.getIdAzienda(), nomeProgramma, dataAttivazione, livelliMassimi, livelloVIP, policyLivelli, numeroPunti, importoDaSpendere, catalogoPremi);
        System.out.println("""
                Programma Fedelta' aggiunto correttamente.
                Ritorno alla Home.
                """);
        sezioneBackOffice();
    }

    private void modificaProgrammaAPunti(ProgrammaPunti programmaPunti) {
        sc.nextLine();
        System.out.println("Inserisci il nuovo nome per il programma, altrimenti premi invio per non modificare il valore :");
        String nomeUpdated = sc.nextLine();
        if (nomeUpdated == null)
            nomeUpdated = programmaPunti.getNome();
        System.out.println("Inserisci il numero punti massimi :");
        int numeroPuntiMassimi = sc.nextInt();
        if (numeroPuntiMassimi == 0)
            numeroPuntiMassimi = programmaPunti.getNumeroPuntiMassimi();
        System.out.println("Inserisci il numero di punti per diventare un Cliente VIP, altrimenti premi invio per non modificare il valore :");
        int numeroPuntiVip = sc.nextInt();
        if (numeroPuntiVip == 0)
            numeroPuntiVip = programmaPunti.getPuntiVIP();
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio :");
        double importoDaSpendere = sc.nextDouble();
        if (importoDaSpendere == 0)
            importoDaSpendere = programmaPunti.getImportoSpesa();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto :");
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
                this.handlers.getHandlerProgrammaFedelta().modificaProgrammaPunti(this.azienda.getIdAzienda(), programmaPunti.getIdProgramma(), nomeUpdated, numeroPuntiMassimi, numeroPuntiVip, numeroPunti, importoDaSpendere);
                System.out.println("""
                        Programma Fedelta' modificato correttamente.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("""
                        Operazione annullata.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private void modificaProgrammaALivelli(ProgrammaLivelli programmaLivelli) {
        sc.nextLine();
        System.out.println("Inserisci il nuovo nome per il programma, altrimenti premi invio per non modificare il valore :");
        String nomeUpdated = sc.nextLine();
        if (nomeUpdated == null)
            nomeUpdated = programmaLivelli.getNome();
        System.out.println("Inserisci il numero massimo di livelli :");
        int numeroLivelliMassimi = sc.nextInt();
        if (numeroLivelliMassimi == 0)
            numeroLivelliMassimi = programmaLivelli.getMassimoLivelli();
        System.out.println("Inserisci il numero di livelli per diventare un Cliente VIP, altrimenti premi invio per non modificare il valore :");
        int numeroLivelloVIP = sc.nextInt();
        if (numeroLivelloVIP == 0)
            numeroLivelloVIP = programmaLivelli.getLivelloVip();
        System.out.println("Vuoi modificare la Policy per i livelli ? (SI-NO)");
        String choiceS = sc.nextLine();
        Map<Integer, Integer> updatedMap = new HashMap<>();
        if (Objects.equals(choiceS, "SI") || Objects.equals(choiceS, "si"))
            updatedMap = modificaPolicy(numeroLivelliMassimi);
        System.out.println("Inserisci l'importo da spendere per acquisire un numero di punti che imposterai dopo questo passaggio :");
        double importoDaSpendere = sc.nextDouble();
        if (importoDaSpendere == 0)
            importoDaSpendere = programmaLivelli.getImportoSpesa();
        System.out.println("Inserisci il numero di punti per l'importo appena aggiunto :");
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
                this.handlers.getHandlerProgrammaFedelta().modificaProgrammaLivelli(this.azienda.getIdAzienda(), programmaLivelli.getIdProgramma(), nomeUpdated, numeroLivelliMassimi, numeroLivelloVIP, updatedMap, numeroPunti, importoDaSpendere);
                System.out.println("""
                        Programma modificato correttamente.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("""
                        Operazione annullata.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private Map<Integer, Integer> modificaPolicy(int numeroMassimoLivelli) {
        sc.nextLine();
        System.out.println("Hai scelto di modificare la policy.");
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
        sc.nextLine();
        int choice;
        System.out.println("Inserisci l'id del Programma Fedelta' da rimuovere :");
        int idProgramma = sc.nextInt();
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
                this.handlers.getHandlerProgrammaFedelta().rimuoviProgrammaFedelta(this.azienda.getIdAzienda(), idProgramma);
                System.out.println("""
                        Programma Fedelta' rimosso con successo.
                        Ritorno alla Home.
                        """);
                sezioneProgrammiFedelta();
            }
            case 2 -> {
                System.out.println("""
                        Operazione annullata.
                        Ritorno alla Home.
                        """);
                sezioneProgrammiFedelta();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private void aggiungiCoupon() {
        sc.nextLine();
        int choice;
        System.out.println("""
                Benvenuto nella creazione di un Coupon
                Inserisci l'importo per il Coupon :
                """);
        int importoCoupon = sc.nextInt();
        sc.nextLine();
        System.out.println("Inserisci la data di attivazione del Coupon :");
        String dataAttivazione = sc.nextLine();
        System.out.println("Inserisci la data di scadenza del Coupon :");
        String dataScadenza = sc.nextLine();
        System.out.println("""
                Inserisci :
                1. Conferma Creazione.
                2. Annulla Creazione.
                3. Ritorna alla home
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.handlers.getHandlerPremi().aggiungiCouponPreconfigurato(this.azienda.getIdAzienda(), importoCoupon, dataAttivazione, dataScadenza);
                System.out.println("""
                        Coupon creato correttamente.
                        Ritorno alla Home.
                        """);
                sezioneConfigurazioneCoupon();
            }
            case 2 -> sezioneConfigurazioneCoupon();
            case 3 -> sezioneBackOffice();
        }
    }

    private void aggiungiSMSPreconfigurato() {
        sc.nextLine();
        System.out.println("""
                Benvenuto nella creazione di un SMS Preconfigurato.,
                Inserisci il testo del messaggio :
                                
                """);
        String testoConfigurato = sc.nextLine();
        while (Objects.equals(testoConfigurato, "") || testoConfigurato == null) {
            System.out.println("Testo non valido. Reinserisci il testo per il messaggio preconfigurato :");
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
                this.handlers.getHandlerMessaggi().aggiungiConfigurazioneSMS(this.azienda.getIdAzienda(), testoConfigurato);
                System.out.println("""
                        Messaggio Preconfigurato creato correttamente.
                        Ritorno alla schermata precedente.
                        """);
                sezioneConfigurazioneSMS();
            }
            case 2 -> {
                System.out.println("""
                        Hai annullato l'operazione.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }

    }

    private void aggiungiCatalogoPremiGenerale() {
        sc.nextLine();
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi :");
        String nome = sc.nextLine();
        int i = 0;
        Set<Premio> premiCreati = new HashSet<>();
        int choice;
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo
                    2. Se vuoi terminare l'aggiunta dei premi.
                                        
                    """);
            int variabile = sc.nextInt();
            if (variabile == 1) {
                sc.nextLine();
                Premio premio;
                System.out.println("Inserisci il nome del Premio :");
                String nomePremio = sc.nextLine();
                System.out.println("""
                        Il Premio deve essere riscattato attraverso i punti o i livelli ? (inserisci il numero corrispettivo)
                        1. Punti.
                        2. Livelli.
                        """);
                int isPunti = sc.nextInt();
                if (isPunti == 1) {
                    System.out.println("Inserisci il numero di punti per riscattare il premio :");
                    int numeroPuntiPerRiscatto = sc.nextInt();
                    premio = new Premio(nomePremio, true, numeroPuntiPerRiscatto);
                    premiCreati.add(premio);
                } else {
                    System.out.println("Inserisci il numero di livelli per riscattare il premio :");
                    int numeroLivelliPerRiscatto = sc.nextInt();
                    premio = new Premio(nomePremio, false, numeroLivelliPerRiscatto);
                    premiCreati.add(premio);
                }
            } else i = 1;
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
                this.handlers.getHandlerPremi().aggiungiCatalogoPremi(this.azienda.getIdAzienda(), nome, premiCreati);
                System.out.println("""
                        Catalogo Premi aggiungo correttamente.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("""
                        Hai annullato l'operazione.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private void aggiungiCatalogoPremiPPunti(ProgrammaFedelta programmaFedelta) {
        sc.nextLine();
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi :");
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
                this.handlers.getHandlerPremi().aggiungiCatalogoAProgrammaPunti(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), nome, premiCreati);
                System.out.println("""
                        Catalogo Premi aggiunto correttamente.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("""
                        Hai annullato l'operazione.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private void aggiungiCatalogoPremiPLivelli(ProgrammaFedelta programmaFedelta) {
        sc.nextLine();
        if (programmaFedelta.getCatalogoPremi() != null) {
            System.out.println("""
                    Il Programma Fedelta' possiede gia' un Catalogo Premi.
                    Per inserire il nuovo Catalogo, questo deve essere rimosso.
                    Desideri continuare ? (SI-NO)
                                        
                    """);
            String choice = sc.nextLine();
            if (Objects.equals(choice, "NO") || Objects.equals(choice, "no")) {
                System.out.println("Ritorno alla schermata precedente.");
                sezioneProgrammaFedelta();
            }
        }
        System.out.println("Inserisci il nome per il nuovo Catalogo Premi :");
        String nomeCatalogo = sc.nextLine();
        Set<Premio> premiCreati = this.compilaCatalogoPremiLivelli();
        int choice;
        System.out.println("""
                Inserisci :
                1. Conferma aggiunta.
                2. Annulla aggiunta.
                3. Ritorna alla Home.
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                this.handlers.getHandlerPremi().aggiungiCatalogoProgrammaLivelli(this.azienda.getIdAzienda(), programmaFedelta.getIdProgramma(), nomeCatalogo, premiCreati);
                System.out.println("""
                        Catalogo Premi aggiunto correttamente.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 2 -> {
                System.out.println("""
                        Hai annullato l'operazione.
                        Ritorno alla Home.
                        """);
                sezioneBackOffice();
            }
            case 3 -> sezioneBackOffice();
        }
    }

    private Set<Premio> compilaCatalogoPremiPunti() {
        sc.nextLine();
        int i = 0;
        Set<Premio> premiCreati = new HashSet<>();
        while (i == 0) {
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo.
                    2. Se vuoi terminare l'aggiunta dei premi.  
                    """);
            int premi = sc.nextInt();
            if (premi == 1) {
                sc.nextLine();
                Premio premio;
                System.out.println("Inserisci il nome del Premio :");
                String nomePremio = sc.nextLine();
                System.out.println("Inserisci il numero di punti per riscattare il premio :");
                int numeroLivelliPerRiscatto = sc.nextInt();
                premio = new Premio(nomePremio, true, numeroLivelliPerRiscatto);
                premiCreati.add(premio);
            } else i = 1;
        }
        return premiCreati;
    }

    private Set<Premio> compilaCatalogoPremiLivelli() {
        sc.nextLine();
        int i = 0;
        Set<Premio> premiCreati = new HashSet<>();
        while (i == 0) {
            sc.nextLine();
            System.out.println("""
                    Seleziona :
                    1. Aggiungi un premio al Catalogo.
                    2. Se vuoi terminare l'aggiunta dei premi.  
                    """);
            int premi = sc.nextInt();
            if (premi == 1) {
                sc.nextLine();
                Premio premio;
                System.out.println("Inserisci il nome del Premio :");
                String nomePremio = sc.nextLine();
                System.out.println("Inserisci il numero di livelli per riscattare il premio :");
                int numeroLivelliPerRiscatto = sc.nextInt();
                premio = new Premio(nomePremio, true, numeroLivelliPerRiscatto);
                premiCreati.add(premio);
            } else i = 1;
        }
        return premiCreati;
    }
}
