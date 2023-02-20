package loyality_platform_model.Interface;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.Dipendente;
import loyality_platform_model.Models.GestorePuntoVendita;
import loyality_platform_model.Utils.InitProjectData;
import loyality_platform_model.Utils.Utils;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * This interface represents the Home of the Loyalty Program.
 * In fact, the first time the program is started, the welcome method is
 * started each time, where a user, from the terminal, has the possibility to
 * register-login or exit the program.
 * Once a specific user (Owner, Employee or User) logs in, the corresponding
 * and specific UI for that type of User will start, recalled in the Login methods.
 */
public class UI_Home {

    /**
     * Object of type Utils, it allows you to take all the Handlers
     * that this class makes available, as it reduces duplicate code
     * and was created specifically.
     */
    private final Utils utils;

    /**
     * Object of type InitProjectData, it will be called
     * inside the Constructor, in such a way that in the "Main"
     * class of the project, when the Home is instantiated,
     * the program will start, also instantiating all the "example" data
     * for program execution.
     */
    private final InitProjectData initData;

    /**
     * Scanner for User-terminal input.
     */
    private final Scanner sc;

    public UI_Home() {
        this.initData = InitProjectData.getInstance();
        this.utils = new Utils();
        this.sc = new Scanner(System.in);
        welcomePage();
    }

    public void welcomePage() {
        int choice;
        System.out.println("""
                WELCOME TO LOYALTY PLATFORM
                Ecco le sezioni disponibili :
                1. Registrazione
                2. Login.
                3. Esci.
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> registrazione();
            case 2 -> login();
            case 3 -> exitProgram();
        }
    }


    public void login() {
        int choice;
        System.out.println("""
                Seleziona :
                1. Login Gestore Piattaforma
                2. Login Titolare
                3. Login Dipendente
                4. Login Utente
                5. Ritorna alla home.
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> loginGestorePiattaforma();
            case 2 -> loginTitolare();
            case 3 -> loginDipendente();
            case 4 -> loginUtente();
            case 5 -> welcomePage();
        }
    }

    public void loginGestorePiattaforma() {
        sc.nextLine();
        String nome;
        String cognome;
        System.out.println("""
                Inserisci la tua email :
                                
                """);
        nome = sc.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("L'email non può essere vuota. Reinserisci l'email.");
            nome = sc.nextLine();
        }
        System.out.println("""
                Inserisci la password :
                                
                """);
        cognome = sc.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("La password non può essere vuota. Reinserisci la password : ");
            cognome = sc.nextLine();
        }
        Azienda azienda = getGestoreByLogin(nome, cognome);
        if(azienda == null){
            System.out.println("""
                    Credenziali non valide. Inserisci : 
                    1. Esegui nuovamente il Login. 
                    2. Ritorna alla Home. 
                    
                    """);
            int choice = sc.nextInt();
            switch (choice){
                case 1 -> loginTitolare();
                case 2 -> login();
            }
        } //Todo implementare
    }

    public void loginTitolare() {
        String nome;
        String cognome;
        System.out.println("""
                Inserisci il tuo nome :
                                
                """);
        nome = sc.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("L'email non può essere vuota. Reinserisci l'email.");
            nome = sc.nextLine();
        }
        System.out.println("""
                Inserisci la tua password :
                                
                """);
        cognome = sc.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("La password non può essere vuota. Reinserisci la password : ");
            cognome = sc.nextLine();
        }
        Azienda azienda = getGestoreByLogin(nome, cognome);
        if(azienda == null){
            System.out.println("""
                    Credenziali non valide. Inserisci : 
                    1. Esegui nuovamente il Login. 
                    2. Ritorna alla Home. 
                    
                    """);
            int choice = sc.nextInt();
            switch (choice){
                case 1 -> loginTitolare();
                case 2 -> login();
            }
        }else {
            System.out.println("Login Success.");
            UI_Titolare ui = new UI_Titolare(azienda, DBMS.getInstance().getCoalizione());
        }
    }

    public void loginDipendente() {
        System.out.println("""
                Inserisci la tua email :
                                
                """);
        String email = sc.nextLine();
        while (Objects.equals(email, "") || email == null) {
            System.out.println("L'email non può essere vuota. Reinserisci l'email.");
            email = sc.nextLine();
        }
        System.out.println("""
                Inserisci la password :
                                
                """);
        String password = sc.nextLine();
        while (Objects.equals(password, "") || password == null) {
            System.out.println("La password non può essere vuota. Reinserisci la password : ");
            password = sc.nextLine();
        }
        //Todo richiamare il metodo che torna il Dipendente loggato + richiamare la corrispettiva UI.
    }

    public void loginUtente() {
        System.out.println("""
                Inserisci la tua email :
                                
                """);
        String email = sc.nextLine();
        while (Objects.equals(email, "") || email == null) {
            System.out.println("L'email non può essere vuota. Reinserisci l'email.");
            email = sc.nextLine();
        }
        System.out.println("""
                Inserisci la password :
                                
                """);
        String password = sc.nextLine();
        while (Objects.equals(password, "") || password == null) {
            System.out.println("La password non può essere vuota. Reinserisci la password : ");
            password = sc.nextLine();
        }
        //Todo richiamare il metodo che torna l'Utente loggato + richiamare la corrispettiva UI.
    }

    public void registrazione() {
        System.out.println("""
                Benvenuto.
                Inserisci il tuo nome :
                                
                """);
        String nome = sc.nextLine();
        System.out.println("""
                Inserisci il tuo cognome:
                                
                """);
        String cognome = sc.nextLine();
        System.out.println("""
                Inserisci il tuo numero di telefono :
                                
                """);
        String numeroTelefono = sc.nextLine();
        System.out.println("""
                Inserisci la tua email:
                                
                """);
        String email = sc.nextLine();
        //Todo richiamare il metodo HandlerCliente che crea il cliente (metodo da aggiungere) + ritornare alla pagina Login.
    }

    public void exitProgram() {
        System.out.println("""
                Arrivederci.
                                
                """);
        exit(0);
    }

    private Azienda getGestoreByLogin(String nome, String cognome) {
        return this.utils.getAziendaByLogin(nome, cognome);
    }

    private Dipendente getDipendenteByLogin(String email, String password) {
        //Todo implemetare, ci deve essere un metodo o negli HandlerAzienda o nell'Utils che fa questo.
        return null;
    }

    private Cliente getClienteByLogin(String email, String password) {
        //Todo implemetare, ci deve essere un metodo o negli HandlerAzienda o nell'Utils che fa questo.
        return null;
    }
}
