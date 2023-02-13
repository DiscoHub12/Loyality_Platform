package loyality_platform_model.Interface;
import loyality_platform_model.Handler.*;
import loyality_platform_model.Models.*;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;
public class UI_Cliente {
    private final Scanner sc;
    private final Cliente cliente;
    //private final Azienda azienda;
    private final HandlerCliente gestoreCliente = new HandlerCliente();
    private final HandlerPremi gestorePremi = new HandlerPremi();
    private final HandlerProgrammaFedelta gestoreProgrammi = new HandlerProgrammaFedelta();
    private final HandlerVisite gestoreVisite = new HandlerVisite();
    private final HandlerAzienda gestoreAzienda = new HandlerAzienda();
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
        System.out.println("\nSEZIONE PROGRAMMI FEDELTA'." +
                "\nElenco dei programmi fedelta attivi per il negozio selezionato" +
                "\n"); //+ this.gestoreAzienda.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()));
        System.out.println("""
                Elenco le attività disponibili nella sezione Spazio Fedeltà: 
                1. Modifica Spazio Fedeltà
                2. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneIscrizioneProgrammiFedelta();
            case 2 -> sezioneClienti();
        }
    }
    public void sezioneCercaNegozi(){
        int choice;
        System.out.println("\nSEZIONE CERCA NEGOZI." +
                "\nEcco la lista dei negozi registrati nella piattaforma" +
                "\n"); //+ this.gestoreAzienda.getSpazioFedeltaAzienda(this.azienda.getIdAzienda()));
        System.out.println("""
                Elenco le possibili operazioni :
                1. Cerca un negozio
                2. Ritorna alla Home.
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneCercaNegozi();
            case 2 -> sezioneClienti();
        }
    }
    //SOTTO SEZIONE TESSERA (DI CLIENTI)
    public void sezioneTessera() {
        //TODO
        int choice;
        System.out.println("\nSEZIONE TESSERA." +
                "\nEcco i dettagli della tua tessera" +
                "\n");
                //TODO in handlerTessera mi serve il metodo getTessera e getProgrammaFedeltà
        System.out.println("""
                Elenco le attività disponibili nella sezione Tessera: 
                1. Dettagli Programma Fedeltà a cui si è iscritto
                2. Ritorna alla home
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> sezioneTessera();
            case 2 -> sezioneClienti();
        }
    }
    //SOTTO SEZIONE PREMI (DI CLIENTI)
    public void sezioneCatalogoPremi(){
        //TODO
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
}
