package loyality_platform_model.Interface;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import loyality_platform_model.Utils.Utils;
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
                                
                1. Sezione Iscrizione Programmi Fedeltà
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
        System.out.println("""
                SEZIONE PROGRAMMI FEDELTA' .
                Elenco dei programmi fedeltà disponibili :
                             
                """);
        Set<Azienda> aziende = DBMS.getInstance().getAziendeIscritte();
        for (Azienda azienda : aziende) {
            System.out.println("Azienda: " + azienda.toString());
            Set<ProgrammaFedelta> programmiFedelta = DBMS.getInstance().getProgrammiFedeltaAzienda(azienda.getIdAzienda());
            if (programmiFedelta != null) {
                for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
                    System.out.println("Id Programma : " + programmaFedelta1.getIdProgramma() + " Nome Programma : " + programmaFedelta1.getNome());
                }
            } else {
                System.out.println("L'azienda: " + azienda + "non possiede alcun programma");
            }
        }
        System.out.println("Inserire l'id di una azienda: ");
        int azienda = sc.nextInt();
        System.out.println("Inserire l'id di un programma a cui iscriversi: ");
        choice = sc.nextInt();
        this.selezioneProgramma(choice, azienda);
    }

    //SOTTO SEZIONE CERCA NEGOZI (DI CLIENTI)
    public void sezioneCercaNegozi() {
        int choice;
        String nomeNegozio;
        Set<Azienda> aziendeIscritte = DBMS.getInstance().getAziendeIscritte();
        System.out.println("Elenco dei negozi disponibili: "
                + aziendeIscritte.toString());

        System.out.println("""
                Desideri cercare un negozio
                1. Si
                2. Annulla
                Inserisci il numero corrispettivo

                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Inserisci il nome del negozio: ");
                nomeNegozio = sc.next();
                for (Azienda toScroll : aziendeIscritte) {
                    if (toScroll.getSpazioFedelta().getNome().equals(nomeNegozio)) {
                        System.out.println("Informazioni relative al negozio cercato: " +
                                toScroll);
                        System.out.println("""
                                Desideri iscriverti ad un programma fedelta' del negozio: 
                                1. Si
                                2. Annulla
                                Inserisci il numero corrispettivo

                                """);
                        int iscrivi = sc.nextInt();
                        switch (iscrivi) {
                            case 1 -> {
                                Set<ProgrammaFedelta> programmiAzienda = this.utils.getHandlerAzienda().getProgrammiFedeltaAzienda(toScroll.getIdAzienda());
                                System.out.println("Elenco dei programmi fedeltà disponibili del negozio: " +
                                        programmiAzienda.toString());
                                System.out.println("Inserire l'id di un programma a cui iscriversi: ");
                                int program = sc.nextInt();
                                this.selezioneProgramma(program, toScroll.getIdAzienda());
                            }
                            case 2 -> this.homeCliente();
                        }
                    } else {
                        System.out.println("Il negozio cercato non e' stato trovato. Riprovare.");
                        this.sezioneCercaNegozi();
                    }
                }
            }
            case 2 -> homeCliente();
        }
    }

    //SOTTO SEZIONE CERCA NEGOZI (DI ISCRIZIONE PROGRAMMA FEDELTA)
    public void selezioneProgramma(int idProgramma, int idAzienda) {
        if (cliente.getTessera() == null) {
            System.out.println("Impossibile iscriversi al programma fedelta',  perchè non si possiede una tessera.");
            System.out.println("""
                    Vuoi creare una tessera?
                      1. Si
                      2. Annulla
                      Inserisci il numero corrispettivo

                      """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    if (this.utils.getHandlerTessera().creaTessera(cliente.getIdCliente())) {
                        System.out.println("Tessera creata correttamente.");
                    } else {
                        System.out.println("Tessera non creata riprovare.");
                        this.homeCliente();
                    }
                }
                case 2 -> {
                    System.out.println("Impossibile iscriversi al programma fedelta'");
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
                    System.out.println("Iscrizione avvenuta con successo.");
                    this.homeCliente();
                } else {
                    System.out.println("Iscrizione non avvenuta.");
                    this.homeCliente();
                }
            }
            case 2 -> {
                System.out.println("Iscrizione annullata.");
                this.homeCliente();
            }
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
        System.out.println(cliente.getTessera().toString());
        System.out.println("""
                Vuoi vedere i dettagli di un determinato programma fedeltà a cui sei iscritto?
                1. Sì
                2. No (Ritornerai alla home clienti)
                Inserisci il numero corrispettivo
                                
                """);
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Inserisci l'id del programma fedeltà desiderato : ");
                id = sc.nextInt();
                Tessera tesseraCliente = DBMS.getInstance().getTesseraCliente(cliente.getIdCliente());
                Set<ProgrammaFedelta> programmiFedelta = tesseraCliente.getProgrammiFedelta();
                for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
                    if (programmaFedelta1.getIdProgramma() == id) {
                        System.out.println(programmaFedelta1);
                        System.out.println("""
                                Vuoi vedere altri programmi fedeltà aggiunti alla tua tessera?
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
    }

    //SOTTO SEZIONE PREMI (DI CLIENTI)
    public void sezioneCatalogoPremi() {
        Set<Premio> premi = this.utils.getHandlerCliente().getPremiCliente(cliente.getIdCliente());
        System.out.println("""
                SEZIONE PREMI
                Elenco dei premi riscattati :

                """);
        if (premi != null) {
            for (Premio premio : premi) {
                System.out.println(premio.toString());

            }
        } else {
            System.out.println("Non c'è nessun premio riscattato\n");

        }
        System.out.println("""
                Vuoi riscattare un premio: 
                1. Conferma
                2. Annulla
                                
                """);
        int riscatta = sc.nextInt();
        switch (riscatta){
            case 1 -> sezioneRiscattaPremio();
            case 2 -> this.homeCliente();
        }
    }

    public void sezioneRiscattaPremio() {
        int id;
        int id1;
        int id2;
        int id3;
        System.out.println("""
                SEZIONE RISCATTA PREMIO
                Elenco dei programmi fedeltà a cui si è iscritto :
                                
                """);
        Tessera tesseraCliente = DBMS.getInstance().getTesseraCliente(cliente.getIdCliente());
        Set<ProgrammaFedelta> programmiFedelta = tesseraCliente.getProgrammiFedelta();
        for (ProgrammaFedelta programmaFedelta1 : programmiFedelta) {
            System.out.println(programmaFedelta1.toString());
        }
        System.out.println("Inserisci l'id del programma fedeltà su cui vuoi vedere il catalogo premi : ");
        id = sc.nextInt();
        Set<Azienda> aziende = DBMS.getInstance().getCoalizione().getAziendeIscritteProgramma(id);
        for (Azienda azienda1 : aziende) {
            System.out.println(("Id Negozio : " + azienda1.getIdAzienda() + " Nome Azienda : " + azienda1.getSpazioFedelta().getNome()));
        }
        System.out.println("Inserisci id del negozio su cui vuoi vedere il catalogo premi : ");
        id1 = sc.nextInt();
        Set<CatalogoPremi> cataloghiPremi = this.utils.getHandlerAzienda().getCatalogoPremiAzienda(id1);
        for (CatalogoPremi catalogoPremi1 : cataloghiPremi) {
            System.out.println(catalogoPremi1.toString());
        }
        System.out.println("Inserisci id del catalogo premi che ti interessa : ");
        id2 = sc.nextInt();
        for (CatalogoPremi catalogoPremi2 : cataloghiPremi) {
            if (catalogoPremi2.getIdCatalogoPremi() == id2) {
                Set<Premio> premi1 = catalogoPremi2.getPremiCatalogo();
                for (Premio premio1 : premi1) {
                    System.out.println(premio1.toString());
                    System.out.println("\nInserisci id del premio che si vuole riscattare : ");
                    id3 = sc.nextInt();
                    if (premio1.getIdPremio() == id3) {
                        if (this.cliente.getTessera().getPunti() >= premio1.getPunti()) {
                            this.utils.getHandlerPremi().aggiungiPremioClienteCatalogoProgramma(id1, id, cliente.getIdCliente(), id3);
                            this.utils.getHandlerTessera().removePuntiCliente(DBMS.getInstance().getTesseraCliente(cliente.getIdCliente()).getIdTessera(), premio1.getPunti());
                        } else if (this.cliente.getTessera().getLivelli() >= premio1.getLivelli()) {
                            this.utils.getHandlerPremi().aggiungiPremioClienteCatalogoProgramma(id1, id, cliente.getIdCliente(), id3);
                        }
                    } else {
                        System.out.println("Non è stato trovato nessun premio \n");
                        homeCliente();
                    }
                }
            } else {
                System.out.println("Non è stato trovato nessun catalogo \n");
                homeCliente();
            }
        }
    }


    public void sezioneCoupon() {
        Set<Coupon> couponCliente= this.utils.getHandlerCliente().getCouponCliente(cliente.getIdCliente());
        System.out.println("Ecco i tuoi coupon: "
        + couponCliente.toString());
        System.out.println("Premi 1 per tornare alla home: ");
        int choice = sc.nextInt();
        if(choice == 1){
            this.homeCliente();
        } else this.sezioneCoupon();
    }

    public void sezioneVisite(){
        Set<Visita> visiteCliente = this.utils.getHandlerCliente().getVisiteCliente(cliente.getIdCliente());
        System.out.println("Ecco le tue visite: "+
                visiteCliente.toString());
        System.out.println("""
                Elenco delle attivita' disponibili: 
                1. Prenota visita
                2. Cancella visita
                3. Annulla
                
                """);
        int choice = sc.nextInt();
        switch (choice){
            case 1, 2 -> {
                System.out.println("Sezione non disponibile.");
                this.homeCliente();
            }
            case 3 -> this.homeCliente();
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
