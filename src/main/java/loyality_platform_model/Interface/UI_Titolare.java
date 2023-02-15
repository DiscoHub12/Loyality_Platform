package loyality_platform_model.Interface;

import loyality_platform_model.Handler.*;
import loyality_platform_model.Models.*;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

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
        int choice = 0;
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
        System.out.println("\nSEZIONE SPAZIO FEDELTA'." +
                "\nEcco i dettalgi del tuo spazio fedelta" +
                "\n" + this.gestoreAzienda.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()));
        System.out.println("""
                Elenco le attività disponibili nella sezione Spazio Fedeltà: 
                1. Modifica Spazio Fedeltà
                2. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneSpazioFedelta();
            case 2 -> sezioneBackOffice();
        }
    }

    //SOTTO-SEZIONE PROGRAMMA FEDELTA (DI BACKOFFICE)
    public void sezioneProgrammiFedelta() {
        int choice;
        System.out.println("""
                """);
        choice = sc.nextInt();
        switch (choice) {

        }
    }

    //SOTTO-SEZIONE CATALOGO PREMI (DI BACKOFFICE)
    public void sezioneCatalogoPremi() {
        int choice;
        System.out.println("""
                """);
        choice = sc.nextInt();
        switch (choice) {

        }
    }

    //SOTTO-SEZIONE CONFIGURAZIONI (DI BACKOFFICE)
    public void sezioneConfigurazioni() {
        int choice;
        System.out.println("""
                Elenco le attività disponibili nella sezione Configurazioni : 
                1.Configurazioni Coupon
                2.Configurazioni SMS
                3.Ritorna alla home
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
        System.out.println("\nSEZIONE CONFIGURAZIONE COUPON. " +
                "\nElenco tutti i Coupon creati precedentemente : " +
                "\n" + this.gestorePremi.getCouponPreconfiguratiAzienda(this.azienda.getIdAzienda()));
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
        System.out.println("\nSEZIONE ACCOUNT DIPENDENTI" +
                "\nElenco gli Account di tutti i Dipendenti della tua piattaforma" +
                "\n" + this.gestoreAzienda.getDipendentiAzienda(this.azienda.getIdAzienda()));
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
        System.out.println("\nEcco la lista di tutti i tuoi clienti: " +
                this.gestoreAzienda.getClientiAzienda(this.azienda.getIdAzienda(), this.coalizione));
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
        String nome = "";
        String indirizzo = "";
        String numeroTelefono = "";
        String email = "";
        System.out.println("\nInserisci il nuovo nome per lo Spazio Fedeltà: \n");
        nome = sc.nextLine();
        System.out.println("\nInserisci il nuovo indirizzo per lo Spazio Fedletà: \n");
        indirizzo = sc.nextLine();
        System.out.println("\nInserisci il nuovo numero di telefono per lo spazio fedeltà: \n");
        numeroTelefono = sc.nextLine();
        System.out.println("\nInserisci la nuova email per lo spazio fedeltà : \n");
        email = sc.nextLine();
        SpazioFedelta spazioFedelta = new SpazioFedelta(nome, indirizzo, numeroTelefono, email);
        if (Objects.equals(nome, "")) {
            spazioFedelta.setNome(this.azienda.getSpazioFedelta().getNome());
        }
        ;
        if (Objects.equals(indirizzo, "")) {
            spazioFedelta.setIndirizzo(this.azienda.getSpazioFedelta().getIndirizzo());
        }
        if (Objects.equals(numeroTelefono, "")) {
            spazioFedelta.setNumeroTelefono(this.azienda.getSpazioFedelta().getNumeroTelefono());
        }
        if (Objects.equals(numeroTelefono, "")) {
            spazioFedelta.setEmail(this.azienda.getSpazioFedelta().getEmail());
        }
        this.gestoreAzienda.modificaSpazioFedelta(this.azienda.getSpazioFedelta().getIdSpazioFedelta(), spazioFedelta);
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
                    "\n" + identificato.toString());
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

    public void gestisciDipendente() {
        int choice;
        int idDipendente;
        System.out.println("\nInserisci l'id del Dipendente che vuoi gestire : \n");
        idDipendente = sc.nextInt();
        while (idDipendente == 0) {
            System.out.println("Id non valido. Reinserisci l'id del Dipendente : \n");
            idDipendente = sc.nextInt();
        }
        Dipendente identificato = this.gestoreAzienda.getDetailsDipendente(idDipendente);
        if (identificato != null) {
            System.out.println("\nEcco le informazioni del Dipendente selezionato : " +
                    "\n" + identificato.toString());
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
                    } else gestisciDipendente();
                }
                case 2 -> {
                    int restrizioni;
                    boolean restr;
                    String email = "";
                    System.out.println("""
                            Puoi cambiare solamente l'email e le restriozioni.\s
                            Inserisci le restrizioni (0 per acccesso completo, 1 per ristretto) 
                                                        
                            """);
                    restrizioni = sc.nextInt();
                    if (restrizioni == 0) {
                        restr = false;
                    } else restr = true;
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
                    if(choice == 1){
                        this.gestoreAzienda.modificaDipendente(this.azienda.getIdAzienda(), idDipendente, this.titolare, email, restr);
                        System.out.println("""
                                Modifiche avvenute con successo.
                                Ritorno alla schermata precedente con la lista aggiornata.
                                
                                """);
                        sezioneUtenti();
                    }
                }
                case 3 -> sezioneUtenti();
            }
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
        if (restrizioni == 0) {
            restr = false;
        } else restr = true;

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

    public void aggiungiCoupon() {
        int importoCoupon;
        String dataAttivazione;
        String dataScadenza;
        int choice;
        System.out.println("\nBenvenuto nella creazione di un Coupon" +
                "\nInserisci l'importo per il Coupon : \n");
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
                this.gestorePremi.aggiungiCouponPreconfigurato(this.azienda.getIdAzienda(), importoCoupon, "10-2-2022");
                System.out.println("\nCoupon creato correttamente. Ritorno alla schermata principale.\n");
                sezioneConfigurazioneCoupon();
            }
            case 2 -> sezioneConfigurazioneCoupon();
            case 3 -> sezioneBackOffice();
        }
    }

}
