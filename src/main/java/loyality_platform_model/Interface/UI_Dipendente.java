package loyality_platform_model.Interface;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import loyality_platform_model.Utils.Utils;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import static java.lang.System.exit;

public class UI_Dipendente {

    private final Scanner scanner;

    private final Azienda azienda;

    private final Utils utils = new Utils();

    public UI_Dipendente(Azienda azienda) {
        this.azienda = azienda;
        this.scanner = new Scanner(System.in);
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
                if (tessera == 1)
                    sezioneConvalidaAcquisto();
                if (tessera == 2) {
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
                    this.sezioneConvalidaAcquisto();
                }
            }
            case 4 -> this.sezioneLogout();
        }
    }

    private void sezioneCreaTessera() {
        int choice;
        String nome;
        System.out.println("Inserisci il nome del Cliente : \n");
        nome = scanner.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Cliente : \n");
            nome = scanner.nextLine();
        }
        System.out.println("Inserisci il cognome del Cliente : ");
        String cognome = scanner.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Cliente : \n");
            cognome = scanner.nextLine();
        }
        System.out.println("Inserisci la mail del Cliente : ");
        String email = scanner.nextLine();
        while (Objects.equals(email, "") || email == null) {
            System.out.println("Email non valida. Reinserisci l'email del Cliente : \n");
            email = scanner.nextLine();
        }
        System.out.println("Inserisci il numero di telefono del Cliente : ");
        String telefono = scanner.nextLine();
        while (Objects.equals(telefono, "") || telefono == null) {
            System.out.println("Telefono non valido. Reinserisci il telefono del Cliente : \n");
            telefono = scanner.nextLine();
        }

        System.out.println("""
                
                1. Conferma Creazione.
                2. Annulla Creazione.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                Cliente cliente = new Cliente(nome, cognome, telefono, email);
                int idCliente = 0;
                for(Cliente cliente1 : DBMS.getInstance().getClientiIscritti()){
                    if(cliente.equals(cliente1))
                        idCliente = cliente1.getIdCliente();
                }
                if(this.utils.getHandlerTessera().creaTessera(idCliente))
                    System.out.println("Tessera creata con successo.");
                else System.out.println("Errore nella creazione della tessera. Riprovare.");
                this.homeDipendente();

            }
            case 2 -> homeDipendente();
        }
    }

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
        System.out.println("Inserisci il nome del Cliente : ");
        String nome = scanner.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Cliente : \n");
            nome = scanner.nextLine();
        }
        System.out.println("Inserisci il cognome del Cliente : ");
        String cognome = scanner.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Cliente : \n");
            cognome = scanner.nextLine();
        }
        System.out.println("""
                
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sezioneClienteIdentificato(this.utils.getHandlerCliente().identificaCliente(nome, cognome).getIdCliente());
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
            case 1 -> sezioneClienteIdentificato(this.utils.getHandlerCliente().identificaClienteCodice(codice).getIdCliente());
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


    private void sezioneConvalidaAcquisto() {
        int choice, idCoupon;
        int idTessera = 0;
        Coupon coupon = null;
        System.out.println("""
                Per favore inserisci i dati del Cliente :
                1. Dati Personali.
                2. Tessera fisca.
                3. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> idTessera = convalidaAcquistoDati();
            case 2 -> {
                System.out.println("Scan non disponibile");
                idTessera = convalidaAcquistoDati();
            }
            case 3 -> homeDipendente();
        }
        System.out.println("Inserisci l'importo speso dal cliente : ");
        double importo = scanner.nextDouble();
        System.out.println("""
                 Inserire un codice sconto ?
                1. Aggiungi codice sconto
                2. Annulla.
                                
                """);
        int codice = scanner.nextInt();
        if (codice == 1) {
            System.out.println("Inserisce il valore del coupon : ");
            idCoupon = scanner.nextInt();
            for (Map.Entry<Cliente, Set<Coupon>> entry : DBMS.getInstance().getCouponCliente().entrySet()) {
                for (Coupon coupon1 : entry.getValue()) {
                    if (coupon1.getIdCoupon() == idCoupon)
                        coupon = coupon1;
                    this.utils.getHandlerCliente().convalidaAcquisto(azienda.getIdAzienda(), idTessera, importo, coupon, utils.getHandlerTessera(), utils.getHandlerPremi());
                }
            }
        }
    }

    private int convalidaAcquistoDati() {
        int idTessera;
        System.out.println("Inserisci il nome del Cliente : ");
        String nome = scanner.nextLine();
        while (Objects.equals(nome, "") || nome == null) {
            System.out.println("Nome non valido. Reinserisci il nome del Cliente : \n");
            nome = scanner.nextLine();
        }
        System.out.println("Inserisci il cognome del Cliente : ");
        String cognome = scanner.nextLine();
        while (Objects.equals(cognome, "") || cognome == null) {
            System.out.println("Cognome non valido. Reinserisci il cognome del Cliente : \n");
            cognome = scanner.nextLine();
        }
        idTessera = this.utils.getHandlerCliente().identificaCliente(nome, cognome).getTessera().getIdTessera();
        return idTessera;
    }

    private void sezioneClienteIdentificato(int idClienteIdentificato) {
        int choice;
        System.out.println("""
                HOME DEL CLIENTE IDENTIFICATO : \n
                Elenco delle sezioni disponibili per il Cliente identificato :
                1. Sezione Tessera
                2. Sezione Aggiungi Codice Sconto
                3. Sezione Visite
                4. Sezione Messaggi
                5. Annulla
                Inserisci il numero corrispondente
                   
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sezioneTessera(idClienteIdentificato);
            case 2 -> sezioneAggiungiCodiceSconto(idClienteIdentificato);
            case 3 -> sezioneVisite(idClienteIdentificato);
            case 4 -> sezioneMessaggi(idClienteIdentificato);
            case 5 -> homeDipendente();
        }
    }

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
                if(this.utils.getHandlerTessera().addPuntiManuale(punti, tessera.getIdTessera())){
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
                if(this.utils.getHandlerTessera().removePuntiCliente(punti, tessera.getIdTessera())){
                    System.out.println("Punti rimossi con successo.");
                    this.utils.getHandlerMessaggi().inviaSMS(tessera.getIdCliente(), "Ti sono stati rimossi dei punti, controlla il profilo.");
                } else System.out.println("Punti non rimossi. Riprovare.");
                this.sezioneTessera(tessera.getIdCliente());
            }
            case 2 -> sezioneTessera(tessera.getIdCliente());
        }
    }


    private void sezioneAggiungiCodiceSconto(int idCliente) {
        int choice, conferma;
        System.out.println("SEZIONE AGGIUNGI CODICE SCONTO. " +
                "\n Ecco l'elenco dei coupon preconfigurati disponibili : " +
                "\n" + printCouponPreconfigurati());
        if (printCouponPreconfigurati().equals(" ")) {
            System.out.println("Non ci sono coupon preconfigurati.");
            sezioneClienteIdentificato(idCliente);
        } else {
            System.out.println("Seleziona un coupon : ");
            choice = scanner.nextInt();
            System.out.println("""
                    1. Conferma.
                    2. Annulla.
                                    
                    """);
            conferma = scanner.nextInt();
            switch (conferma) {
                case 1 -> {
                    if(this.utils.getHandlerPremi().aggiungiCouponCliente(idCliente, azienda.getIdAzienda(), choice)){
                        System.out.println("Coupon aggiunto con successo.");
                        this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Ti è stato aggiunto un coupon, controllare il profilo");
                    } else System.out.println("Coupon non aggiunto. Riprovare.");
                    this.sezioneClienteIdentificato(idCliente);
                }
                case 2 -> sezioneClienteIdentificato(idCliente);
            }
        }
    }

    private String printCouponPreconfigurati() {
        StringBuilder tmp = new StringBuilder();
        Set<Coupon> couponAzienda = this.utils.getHandlerPremi().getCouponPreconfiguratiAzienda(azienda.getIdAzienda());
        if (couponAzienda == null) {
            tmp.append(" ");
        }
        for (int i = 0; i < Objects.requireNonNull(couponAzienda).size(); i++) {
            tmp.append(i).append(") PREMIO : ").append(couponAzienda.toArray()[i].toString());
        }
        return tmp.toString();
    }

    private void sezioneVisite(int idCliente) {
        int choice;
        System.out.println("SEZIONE VISITE CLIENTE. " +
                "\n Informazione relative alle visite del Cliente: " +
                "\n" + printVisiteCliente(idCliente));
        if (printVisiteCliente(idCliente).equals(" ")) {
            System.out.println("Non sono presenti visite.");
            System.out.println("""
                    Desideri aggiungere una visita :
                    1. Conferma
                    2. Annulla
                    Inserisci il numero corrispondente
                                    
                    """);
            int choice1 = scanner.nextInt();
            switch (choice1){
                case 1 -> this.selezioneAggiungiVisita(idCliente);
                case 2 -> sezioneClienteIdentificato(idCliente);
            }
        } else {
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

    private String printVisiteCliente(int idCliente) {
        StringBuilder tmp = new StringBuilder();
        Set<Visita> visite = this.utils.getHandlerCliente().getVisiteCliente(idCliente);
        if (visite == null) {
            tmp.append(" ");
        }
        for (int i = 0; i < Objects.requireNonNull(visite).size(); i++) {
            tmp.append(i).append(") VISITA : ").append(visite.toArray()[i].toString());
        }
        return tmp.toString();
    }


    private void selezioneAggiungiVisita(int idCliente) {
        int choice;
        Visita visita;
        System.out.println("Inserisci il luogo della visita : \n");
        String luogo = scanner.nextLine();
        while (Objects.equals(luogo, "") || luogo == null) {
            System.out.println("Luogo non valido. Reinserisci il luogo della Visita : \n");
            luogo = scanner.nextLine();
        }
        System.out.println("Inserisci la data della visita : \n");
        String data = scanner.nextLine();
        while (Objects.equals(data, "") || data == null) {
            System.out.println("Data non valida. Reinserisci la data della Visita : \n");
            data = scanner.nextLine();
        }
        System.out.println("""
                1. Conferma.
                2. Annulla.
                                
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                visita = new Visita(luogo, data);
                for (Visita toScroll : this.utils.getHandlerCliente().getVisiteCliente(idCliente)) {
                    if (toScroll.equals(visita)) {
                        System.out.println("Impossibile aggiungere la visita, visita già presente.");
                        sezioneVisite(idCliente);
                    } else {
                        this.utils.getHandlerVisite().aggiungiVisita(idCliente, data, luogo);
                        this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Hai una nuova visita.");
                        System.out.println("Visita aggiunta con successo.");
                        this.sezioneVisite(idCliente);
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
                this.utils.getHandlerMessaggi().inviaSMS(idCliente, "Ti è stata rimossa una visita, controllare il profilo.");
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
                newDate = scanner.nextLine();
            }
            case 2 -> {
                assert toChange != null;
                newDate = String.valueOf(toChange.getData());
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
                newLuogo = scanner.nextLine();
            }
            case 2 -> {
                assert toChange != null;
                newLuogo = toChange.getLuogo();
            }
            case 3 -> operazioniVisita(idCliente, idVisita);
        }
        System.out.println("""
                La visita è stata completata:
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
                        System.out.println("Modifica non effettuata, visita già presente con queste informazioni");
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


    private void sezioneMessaggi(int idCliente) {
        int choice;
        System.out.println(
                "Elenco degli SMS del Cliente : " +
                        "\n" + this.utils.getHandlerCliente().getSMSCliente(idCliente));
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
            case 1 -> testo = selezioneMessaggioPreconfigurato();
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

    private String selezioneMessaggioPreconfigurato() {
        int choice;
        String testo = "";
        Set<SMS> SMSpreConfigurati = this.utils.getHandlerMessaggi().getSMSPreconfigurati(azienda.getIdAzienda()); //??
        System.out.println(
                "Elenco di SMS preconfigurati : " +
                        "\n" + SMSpreConfigurati.toString());
        System.out.println("Inserisci l'id del messaggio da inviare :");
        choice = scanner.nextInt();
        for (SMS sms : SMSpreConfigurati) {
            if (sms.getIdSMS() == choice)
                testo = sms.getTesto();
        }
        return testo;
    }

    private String selezioneMessaggio() {
        String testo;
        System.out.println("Inserisci il testo del messaggio : \n");
        testo = scanner.nextLine();
        return testo;
    }

    private void sezioneLogout() {
        int choice;
        System.out.println("""
                Premi 1 per confermare il Logout.
                Altrimenti premi 0.
                """);
        choice = scanner.nextInt();
        if (choice == 1) {
            exit(0);
        } else if (choice == 0) {
            homeDipendente();
        }
    }
}
