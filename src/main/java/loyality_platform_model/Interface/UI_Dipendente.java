package loyality_platform_model.Interface;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import loyality_platform_model.Utils.Utils;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * This is a prototype class and represents the terminal interface for the employee.
 * The Employee interface will consist of several parts, divided into methods.
 */

public class UI_Dipendente {

    private final Scanner scanner;

    private final Azienda azienda;

    private final Utils utils;

    private final UI_Home home;

    public UI_Dipendente(Azienda azienda, UI_Home home) {
        this.azienda = azienda;
        this.scanner = new Scanner(System.in);
        this.utils = new Utils();
        this.home = home;
        homeDipendente();
    }


    private void homeDipendente() {
        int choice;
        System.out.println("""
                BENVENUTO
                Elenco le sezioni disponibili nella Dashboard Dipendente:
                                
                1. Sezione Crea Tessera
                2. Sezione Identifica Cliente
                3. Sezione Convalida Acquisto
                4. Logout
                Inserisci il numero corrispettivo
                   
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sezioneCreaTessera();
            case 2 -> sezioneIdentificaCliente();
            case 3 -> {
                int tessera;
                System.out.println("""
                        Il Cliente possiede la tessera ?
                        1. Si'
                        2. No
                        Inserisci il numero corrispettivo
                           
                        """);
                tessera = scanner.nextInt();
                if (tessera == 1) {
                    sezioneConvalidaAcquisto();
                } else if (tessera == 2) {
                    int newTessera;
                    System.out.println("""
                            Vuoi creare una nuova tessera ?
                            1. Si'
                            2. No
                            Inserisci il numero corrispettivo
                               
                            """);
                    newTessera = scanner.nextInt();
                    if (newTessera == 1)
                        this.sezioneCreaTessera();
                    if (newTessera == 2) {
                        System.out.println("Non e' possibile accedere alla sezione convalida acquisto.");
                        this.homeDipendente();
                    }
                }
            }
            case 4 -> this.sezioneLogout();
        }
    }

    //-------------------SEZIONE CREA TESSERA-----------------------
    private void sezioneCreaTessera() {
        int choice;
        String nome;
        System.out.println("Inserisci il nome del Cliente : \n");
        nome = scanner.next();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Cliente : \n");
            nome = scanner.next();
        }
        System.out.println("Inserisci il cognome del Cliente : ");
        String cognome = scanner.next();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Cliente : \n");
            cognome = scanner.next();
        }
        System.out.println("Inserisci la mail del Cliente : ");
        String email = scanner.next();
        while (Objects.equals(email, "") || email == null) {
            System.out.println("Email non valida. Reinserisci l'email del Cliente : \n");
            email = scanner.next();
        }
        System.out.println("Inserisci il numero di telefono del Cliente : ");
        String telefono = scanner.next();
        while (Objects.equals(telefono, "") || telefono == null) {
            System.out.println("Telefono non valido. Reinserisci il telefono del Cliente : \n");
            telefono = scanner.next();
        }

        System.out.println("""
                                
                1. Conferma Creazione.
                2. Annulla Creazione.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                int idCliente = 0;
                for (Cliente cliente1 : DBMS.getInstance().getClientiIscritti()) {
                    if (cliente1.getNome().equals(nome) && cliente1.getCognome().equals(cognome) &&
                            cliente1.getEmail().equals(email) && cliente1.getTelefono().equals(telefono))
                        idCliente = cliente1.getIdCliente();
                }
                if (this.utils.getHandlerTessera().creaTessera(idCliente))
                    System.out.println("Tessera creata con successo.");
                else System.out.println("Errore nella creazione della tessera. Riprovare.");
                this.homeDipendente();

            }
            case 2 -> homeDipendente();
        }
    }

    //-------------------SEZIONE IDENTIFICA CLIENTE-----------------------
    private void sezioneIdentificaCliente() {
        int choice;
        System.out.println("""
                Scegli il metodo per identificare il cliente :
                1. Dati Personali.
                2. Codice Tessera.
                3. Tessera fisca.
                4. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> selezioneDatiPersonali();
            case 2 -> selezioneCodiceTessera();
            case 3 -> selezioneTesseraFisica();
            case 4 -> homeDipendente();
        }
    }

    private void selezioneDatiPersonali() {
        int choice;
        System.out.println("Inserisci il nome del Cliente : \n");
        String nome = scanner.next();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Cliente : \n");
            nome = scanner.next();
        }
        System.out.println("Inserisci il cognome del Cliente : ");
        String cognome = scanner.next();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Cliente : \n");
            cognome = scanner.next();
        }
        System.out.println("""
                                
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                int idCliente = this.utils.getHandlerCliente().identificaCliente(nome, cognome).getIdCliente();
                sezioneClienteIdentificato(idCliente);
            }
            case 2 -> sezioneIdentificaCliente();
        }
    }

    private void selezioneCodiceTessera() {
        int choice;
        System.out.println("Inserisci il codice della tessera del Cliente");
        int codice = scanner.nextInt();
        while (codice < 1) {
            System.out.println("Codice non valido. Reinserisci il codice della tessera : \n");
            codice = scanner.nextInt();
        }
        System.out.println("""
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 ->
                    sezioneClienteIdentificato(this.utils.getHandlerCliente().identificaClienteCodice(codice).getIdCliente());
            case 2 -> sezioneIdentificaCliente();
        }
    }

    private void selezioneTesseraFisica() {
        int choice;
        System.out.println("""
                Lettore non disponibile, scegli un'altro metodo :
                1. Dati Personali.
                2. Codice Tessera.
                3. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> selezioneDatiPersonali();
            case 2 -> selezioneCodiceTessera();
            case 3 -> homeDipendente();
        }
    }


    //-------------------SEZIONE CONVALIDA ACQUISTO-----------------------
    private void sezioneConvalidaAcquisto() {
        int choice, idCoupon;
        Tessera tessera = null;
        Cliente cliente = null;
        Coupon coupon = null;
        System.out.println("""
                Per favore inserisci i dati del Cliente :
                1. Dati Personali.
                2. Tessera fisca.
                3. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                cliente = convalidaAcquistoDati();
                tessera = cliente.getTessera();
            }
            case 2 -> {
                System.out.println("Scan non disponibile");
                cliente = convalidaAcquistoDati();
                tessera = cliente.getTessera();
            }
            case 3 -> homeDipendente();
        }
        if (tessera == null) {
            System.out.println("Il cliente non possiede la tessera.");
            this.homeDipendente();
        } else {
            if (tessera.getProgrammiFedelta().isEmpty()) {
                System.out.println("Il cliente non e' iscritto a nessun programma fedelta', quindi impossibile accedere alla sezione convalida acquisto");
                this.homeDipendente();
            } else {
                System.out.println("Inserisci l'importo speso dal cliente : ");
                double importo = scanner.nextDouble();
                System.out.println("""
                         Inserire un codice sconto ?
                        1. Aggiungi codice sconto
                        2. Annulla.
                                        
                        """);
                int codice = scanner.nextInt();
                switch (codice) {
                    case 1 -> {
                        Set<Coupon> couponCliente = this.utils.getHandlerCliente().getCouponCliente(cliente.getIdCliente());
                        if (couponCliente == null)
                            System.out.println("Il cliente non possiede nessun coupon.");
                        else System.out.println("Elenco dei coupon posseduti dal cliente: " +
                                this.utils.getHandlerCliente().getCouponCliente(cliente.getIdCliente()));
                        System.out.println("Inserisce un coupon tra quelli sopra elencati oppure premi 0 per annullare : ");
                        idCoupon = scanner.nextInt();
                        if (idCoupon == 0) {
                            this.utils.getHandlerCliente().convalidaAcquisto(azienda.getIdAzienda(), tessera.getIdTessera(), importo, null, utils.getHandlerTessera(), utils.getHandlerPremi());
                            this.homeDipendente();
                        } else {
                            for (Map.Entry<Cliente, Set<Coupon>> entry : DBMS.getInstance().getCouponCliente().entrySet()) {
                                for (Coupon coupon1 : entry.getValue()) {
                                    if (coupon1.getIdCoupon() == idCoupon)
                                        coupon = coupon1;
                                    this.utils.getHandlerCliente().convalidaAcquisto(azienda.getIdAzienda(), tessera.getIdTessera(), importo, coupon, utils.getHandlerTessera(), utils.getHandlerPremi());
                                    this.homeDipendente();
                                }
                            }
                        }
                    }
                    case 2 -> {
                        this.utils.getHandlerCliente().convalidaAcquisto(azienda.getIdAzienda(), tessera.getIdTessera(), importo, null, utils.getHandlerTessera(), utils.getHandlerPremi());
                        this.homeDipendente();
                    }
                }
            }
        }
    }

    private Cliente convalidaAcquistoDati() {
        System.out.println("Inserisci il nome del Cliente : ");
        String nome = scanner.next();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Cliente : \n");
            nome = scanner.next();
        }
        System.out.println("Inserisci il cognome del Cliente : ");
        String cognome = scanner.next();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Cliente : \n");
            cognome = scanner.next();
        }
        return this.utils.getHandlerCliente().identificaCliente(nome, cognome);
    }


    //-------------------SEZIONE CLIENTE IDENTIFICATO-----------------------
    private void sezioneClienteIdentificato(int idClienteIdentificato) {
        int choice;
        System.out.println("""
                HOME DEL CLIENTE IDENTIFICATO : \n
                Elenco delle sezioni disponibili per il Cliente identificato :
                1. Sezione Tessera
                2. Sezione Aggiungi Codice Sconto
                3. Sezione Visite
                4. Sezione Messaggi
                5. Sezione Iscrivi Cliente Programma Fedelta'
                6. Annulla
                Inserisci il numero corrispondente
                   
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sezioneTessera(idClienteIdentificato);
            case 2 -> sezioneAggiungiCodiceSconto(idClienteIdentificato);
            case 3 -> sezioneVisite(idClienteIdentificato);
            case 4 -> sezioneMessaggi(idClienteIdentificato);
            case 5 -> sezioneIscriviCliente(idClienteIdentificato);
            case 6 -> homeDipendente();
        }
    }



    //-------------------SEZIONE TESSERA-----------------------
    private void sezioneTessera(int idCliente) {
        int choice;
        Tessera tessera = this.utils.getHandlerCliente().getTesseraCliente(idCliente);
        if (tessera == null) {
            System.out.println("Il cliente non possiede la tessera.");
            this.sezioneClienteIdentificato(idCliente);
        } else {
            System.out.println("SEZIONE TESSERA CLIENTE. " +
                    "\n Informazione relative alla tessera del Cliente: " +
                    "\n" + tessera);
            System.out.println("""
                    Elenco le possibili operazioni che puoi effettuare :
                    1. Aggiungi punti
                    2. Rimuovi punti
                    3. Ritorna alla Home del Cliente
                    Inserisci il numero corrispondente
                                    
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> selezioneAggiungiPunti(tessera);
                case 2 -> selezioneRimuoviPunti(tessera);
                case 3 -> sezioneClienteIdentificato(idCliente);
            }
        }
    }

    private void selezioneAggiungiPunti(Tessera tessera) {
        int punti, choice;
        System.out.println("Inserisci il numero di punti da aggiungere : ");
        punti = scanner.nextInt();
        while (punti < 1) {
            System.out.println("Numero punti non valido. Reinserisci il numero di punti : \n");
            punti = scanner.nextInt();
        }
        System.out.println("""
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                if (this.utils.getHandlerTessera().addPuntiManuale(punti, tessera.getIdTessera())) {
                    System.out.println("Punti aggiunti con successo.");
                    this.utils.getHandlerMessaggi().inviaSMS(tessera.getIdCliente(), "Ti sono stati aggiunti dei punti, controllare il profilo.");
                } else System.out.println("Punti non aggiunti. Riprovare.");
                this.sezioneTessera(tessera.getIdCliente());
            }
            case 2 -> sezioneTessera(tessera.getIdCliente());
        }
    }


    private void selezioneRimuoviPunti(Tessera tessera) {
        int punti, choice;
        System.out.println("Inserisci il numero di punti da rimuovere : ");
        punti = scanner.nextInt();
        while (punti < 1) {
            System.out.println("Numero punti non valido. Reinserisci il numero di punti : \n");
            punti = scanner.nextInt();
        }
        System.out.println("""
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                if (this.utils.getHandlerTessera().removePuntiCliente(tessera.getIdTessera(), punti)) {
                    System.out.println("Punti rimossi con successo.");
                    this.utils.getHandlerMessaggi().inviaSMS(tessera.getIdCliente(), "Ti sono stati rimossi dei punti, controlla il profilo.");
                } else System.out.println("Punti non rimossi. Riprovare.");
                this.sezioneTessera(tessera.getIdCliente());
            }
            case 2 -> sezioneTessera(tessera.getIdCliente());
        }
    }


    //-------------------SEZIONE AGGIUNGI COUPON-----------------------
    private void sezioneAggiungiCodiceSconto(int idCliente) {
        int choice, conferma;
        Set<Coupon> couponPreconfigurati = this.utils.getHandlerPremi().getCouponPreconfiguratiAzienda(azienda.getIdAzienda());
        if (couponPreconfigurati == null) {
            System.out.println("Non ci sono coupon preconfigurati");
            this.sezioneClienteIdentificato(idCliente);
        } else {
            System.out.println("SEZIONE AGGIUNGI CODICE SCONTO. " +
                    "\n Ecco l'elenco dei coupon preconfigurati disponibili : " +
                    "\n" + couponPreconfigurati);
            System.out.println("Seleziona un coupon : ");
            choice = scanner.nextInt();
            System.out.println("""
                    1. Conferma.
                    2. Annulla.
                                    
                    """);
            conferma = scanner.nextInt();
            switch (conferma) {
                case 1 -> {
                    if (this.utils.getHandlerPremi().aggiungiCouponCliente(idCliente, azienda.getIdAzienda(), choice)) {
                        System.out.println("Coupon aggiunto con successo.");
                        this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Ti e' stato aggiunto un coupon, controllare il profilo");
                    } else System.out.println("Coupon non aggiunto. Riprovare.");
                    this.sezioneClienteIdentificato(idCliente);
                }
                case 2 -> sezioneClienteIdentificato(idCliente);
            }
        }
    }


    //-------------------SEZIONE VISITE-----------------------
    private void sezioneVisite(int idCliente) {
        int choice;
        Set<Visita> visite = this.utils.getHandlerCliente().getVisiteCliente(idCliente);
        if (visite == null) {
            System.out.println("Non sono presenti visite.");
            System.out.println("""
                    Desideri aggiungere una visita :
                    1. Conferma
                    2. Annulla
                    Inserisci il numero corrispondente
                                    
                    """);
            int choice1 = scanner.nextInt();
            switch (choice1) {
                case 1 -> this.selezioneAggiungiVisita(idCliente);
                case 2 -> sezioneClienteIdentificato(idCliente);
            }
        } else {
            System.out.println("SEZIONE VISITE CLIENTE. " +
                    "\n Informazione relative alle visite del Cliente: " +
                    "\n" + visite);
            System.out.println("""
                    Elenco le possibili operazioni che puoi effettuare :
                    1. Aggiungi visita
                    2. Gestisci visite
                    3. Ritorna alla Home del Cliente
                    Inserisci il numero corrispondente
                                    
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> selezioneAggiungiVisita(idCliente);
                case 2 -> selezioneGestisciVisite(idCliente);
                case 3 -> sezioneClienteIdentificato(idCliente);
            }
        }
    }


    private void selezioneAggiungiVisita(int idCliente) {
        int choice;
        scanner.nextLine();
        Visita visita;
        System.out.println("Inserisci il luogo della visita : \n");
        String luogo = scanner.next();
        while (Objects.equals(luogo, "") || luogo == null) {
            System.out.println("Luogo non valido. Reinserisci il luogo della Visita : \n");
            luogo = scanner.next();
        }
        System.out.println("Inserisci la data della visita : \n");
        String data = scanner.next();
        while (Objects.equals(data, "") || data == null) {
            System.out.println("Data non valida. Reinserisci la data della Visita : \n");
            data = scanner.next();
        }
        System.out.println("""
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                visita = new Visita(luogo, data);
                Set<Visita> visiteCliente = this.utils.getHandlerCliente().getVisiteCliente(idCliente);
                if (visiteCliente == null) {
                    this.utils.getHandlerVisite().aggiungiVisita(idCliente, data, luogo);
                    this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Hai una nuova visita.");
                    System.out.println("Visita aggiunta con successo.");
                    this.sezioneVisite(idCliente);
                } else {
                    for (Visita toScroll : visiteCliente) {
                        if (toScroll.equals(visita)) {
                            System.out.println("Impossibile aggiungere la visita, visita gia' presente.");
                            sezioneVisite(idCliente);
                        } else {
                            this.utils.getHandlerVisite().aggiungiVisita(idCliente, data, luogo);
                            this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Hai una nuova visita.");
                            System.out.println("Visita aggiunta con successo.");
                            this.sezioneVisite(idCliente);
                        }
                    }
                }
            }
            case 2 -> sezioneVisite(idCliente);
        }
    }


    private void selezioneGestisciVisite(int idCliente) {
        int idVisita;
        System.out.println("Inserisci l'id di una visita sopra elencate : ");
        idVisita = scanner.nextInt();
        System.out.println(
                "Informazione relative alla visita selezionata : " +
                        "\n" + this.utils.getHandlerVisite().getDetailsVisita(idCliente, idVisita));
        operazioniVisita(idCliente, idVisita);
    }

    private void operazioniVisita(int idCliente, int idVisita) {
        int choice;
        System.out.println("""
                Elenco le possibili operazioni che puoi effettuare :
                1. Rimuovi visita
                2. Modifica visita
                3. Annulla
                Inserisci il numero corrispondente
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> selezioneRimuoviVisita(idVisita, idCliente);
            case 2 -> selezioneModificaVisita(idVisita, idCliente);
            case 3 -> sezioneVisite(idCliente);
        }
    }

    private void selezioneRimuoviVisita(int idVisita, int idCliente) {
        int choice;
        System.out.println("""
                1. Conferma Rimozione.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                this.utils.getHandlerVisite().rimuoviVisita(idCliente, idVisita);
                this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Ti e' stata rimossa una visita, controllare il profilo.");
                System.out.println("Visita rimossa correttamente.");
                this.sezioneVisite(idCliente);
            }
            case 2 -> operazioniVisita(idCliente, idVisita);
        }
    }

    private void selezioneModificaVisita(int idVisita, int idCliente) {
        int choice1, choice2, conferma;
        Visita toChange = null;
        for (Visita visita : this.utils.getHandlerCliente().getVisiteCliente(idCliente)) {
            if (visita.getIdVisita() == idVisita)
                toChange = visita;
        }
        String newDate = null;
        String newLuogo = null;
        System.out.println("""
                Vuoi modificare la data della visita :
                1. Conferma
                2. No
                3. Annulla operazione
                Inserisci il numero corrispondente
                                
                """);
        choice1 = scanner.nextInt();
        switch (choice1) {
            case 1 -> {
                System.out.println("Inserisci la data : \n");
                newDate = scanner.next();
            }
            case 2 -> {
                assert toChange != null;
                newDate = toChange.getData();
            }
            case 3 -> operazioniVisita(idCliente, idVisita);
        }
        System.out.println("""
                Vuoi modificare il luogo della visita :
                1. Conferma
                2. No
                3. Annulla operazione
                Inserisci il numero corrispondente
                                
                """);
        choice2 = scanner.nextInt();
        switch (choice2) {
            case 1 -> {
                System.out.println("Inserisci il luogo : \n");
                newLuogo = scanner.next();
            }
            case 2 -> {
                assert toChange != null;
                newLuogo = toChange.getLuogo();
            }
            case 3 -> operazioniVisita(idCliente, idVisita);
        }
        System.out.println("""
                La visita e' stata completata?
                1. Conferma
                2. No
                3. Annulla operazione
                Inserisci il numero corrispondente
                                
                """);
        int choice = scanner.nextInt();
        boolean completata = false;
        switch (choice) {
            case 1 -> completata = true;
            case 2 -> {
            }
            case 3 -> operazioniVisita(idCliente, idVisita);
        }
        System.out.println("""
                1. Conferma Modifica.
                2. Annulla.
                                
                """);
        conferma = scanner.nextInt();
        switch (conferma) {
            case 1 -> {
                Visita newVisita = new Visita(newLuogo, newDate);
                for (Visita visita : this.utils.getHandlerCliente().getVisiteCliente(idCliente)) {
                    if (visita.equals(newVisita)) {
                        System.out.println("Modifica non effettuata, visita gia' presente con queste informazioni");
                        operazioniVisita(idCliente, idVisita);
                    }
                }
                this.utils.getHandlerVisite().modificaVisita(idCliente, idVisita, newDate, newLuogo, completata);
                this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Potrebbe essere stata modificata una visita, controlla il proflo.");
                System.out.println("Visita modificata con successo.");
                this.sezioneVisite(idCliente);
            }
            case 2 -> operazioniVisita(idCliente, idVisita);
        }
    }


    //-------------------SEZIONE MESSAGGI-----------------------
    private void sezioneMessaggi(int idCliente) {
        int choice;
        Set<SMS> smsCliente = this.utils.getHandlerCliente().getSMSCliente(idCliente);
        if (smsCliente == null) {
            System.out.println("Il cliente non ha ancora nessun messaggio.");
        } else {
            System.out.println(
                    "Elenco degli SMS del Cliente : " +
                            "\n" + this.utils.getHandlerCliente().getSMSCliente(idCliente));
        }
        System.out.println("""
                Vuoi inviare un SMS :
                1. Conferma
                2. Annulla operazione
                Inserisci il numero corrispondente
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> inviaSms(idCliente);
            case 2 -> sezioneClienteIdentificato(idCliente);
        }
    }


    private void inviaSms(int idCliente) {
        int choice, conferma;
        String testo = "";
        System.out.println("""
                Scegli la tipologia di SMS che vuoi inviare:
                1. Messaggio Preconfigurato
                2. Messaggio
                3. Annulla
                Inserisci il numero corrispondente
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> testo = selezioneMessaggioPreconfigurato(idCliente);
            case 2 -> testo = selezioneMessaggio();
            case 3 -> sezioneMessaggi(idCliente);
        }
        System.out.println("""
                1. Conferma invio.
                2. Annulla.
                                
                """);
        conferma = scanner.nextInt();
        switch (conferma) {
            case 1 -> {
                this.utils.getHandlerMessaggi().inviaSMS(idCliente, testo);
                this.sezioneMessaggi(idCliente);
            }
            case 2 -> sezioneMessaggi(idCliente);
        }
    }

    private String selezioneMessaggioPreconfigurato(int idCliente) {
        int choice;
        String testo = "";
        Set<SMS> SMSpreConfigurati = this.utils.getHandlerMessaggi().getSMSPreconfigurati(azienda.getIdAzienda());
        if (SMSpreConfigurati == null) {
            System.out.println("Non ci sono sms preconfigurati.");
            this.inviaSms(idCliente);
        } else {
            System.out.println(
                    "Elenco di SMS preconfigurati : " +
                            "\n" + SMSpreConfigurati);
            System.out.println("Inserisci l'id del messaggio da inviare :");
            choice = scanner.nextInt();
            for (SMS sms : SMSpreConfigurati) {
                if (sms.getIdSMS() == choice)
                    testo = sms.getTesto();
            }
        }
        return testo;
    }

    private String selezioneMessaggio() {
        String testo;
        System.out.println("Inserisci il testo del messaggio : \n");
        testo = scanner.next();
        return testo;
    }


    //-------------------SEZIONE ISCRIVI CLIENTE PROGRAMMA FEDELTA'-----------------------
    private void sezioneIscriviCliente(int idCliente) {
        System.out.println("SEZIONE ISCRIVI CLIENTE PROGRAMMA FEDELTA'." +
                "Ecco tutti i programmi disponibili: " +
                this.utils.getHandlerAzienda().getProgrammiFedeltaAzienda(azienda.getIdAzienda()));
        int choice;
        System.out.println("Inserire il codice del programma a cui iscrivere il cliente tra quelli elencati :");
        choice = scanner.nextInt();
        System.out.println("""
                1. Conferma.
                2. Annulla.
                                
                """);
        int conferma = scanner.nextInt();
        switch (conferma){
            case 1 -> {
                Tessera tessera = this.utils.getHandlerCliente().getTesseraCliente(idCliente);
                if (tessera == null) {
                    System.out.println("Il cliente non ha la tessera. Crearla e riprovare.");
                    this.homeDipendente();
                } else {
                    ProgrammaFedelta programmaFedelta = this.utils.getHandlerProgrammaFedelta().getProgrammaFedeltaById(azienda.getIdAzienda(), choice);
                    if (this.utils.getHandlerTessera().addProgrammaFedelta(idCliente, programmaFedelta)) {
                        System.out.println("Cliente iscritto correttamente al programma.");
                        this.sezioneClienteIdentificato(idCliente);
                    } else {
                        System.out.println("Iscrizione non avvenuta.");
                        this.sezioneClienteIdentificato(idCliente);
                    }
                }
            }
            case 2 -> this.sezioneClienteIdentificato(idCliente);
        }
    }


    //-------------------SEZIONE LOGOUT-----------------------
    private void sezioneLogout() {
        int choice;
        System.out.println("""
                Premi 1 per confermare il Logout.
                Altrimenti premi 0.
                """);
        choice = scanner.nextInt();
        if (choice == 1) {
            this.home.welcomePage();
        } else if (choice == 0) {
            homeDipendente();
        }
    }
}
