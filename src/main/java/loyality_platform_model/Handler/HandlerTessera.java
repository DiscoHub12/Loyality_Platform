package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class represents a manager of the card,
 * therefore it takes care of all the operations that can be performed on it,
 * such as: adding or removing points and adding or removing a loyalty program.
 */
public class HandlerTessera {

    private final DBMS dbms;

    public HandlerTessera() {
        this.dbms = DBMS.getInstance();
    }


    public DBMS getDbms() {
        return dbms;
    }

    /**
     * This method creates the card associated with a specific customer.
     * @param idCliente customer to whom the card is created.
     */
    public void creaTessera(int idCliente) {
        Tessera tessera;
        if (idCliente < 1)
            throw new IllegalArgumentException("Id non corretto");
        for (Cliente cliente : this.getDbms().getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                tessera = new Tessera(idCliente);
                for(Tessera toScroll : this.getDbms().getTessereClienti()){
                    if(!toScroll.equals(tessera)){
                        cliente.setTessera(tessera);
                        this.getDbms().addTessera(tessera);
                    }
                    throw new IllegalArgumentException("Card already presents.");
                }
            }
            throw new IllegalArgumentException("Costumer not exists.");
        }
    }


    public void addPuntiAcquisto(double importoSpeso, Tessera tessera, Azienda azienda) {
        Objects.requireNonNull(tessera);
        Set<ProgrammaFedelta> programmiIscritti = new HashSet<>();
                for (ProgrammaFedelta programmaFedelta : this.getDbms().getProgrammiAzienda().get(azienda)) {
                    for (ProgrammaFedelta programmaFedelta1 : tessera.getProgrammiFedelta()) {
                        if (programmaFedelta1.equals(programmaFedelta)) {
                            programmiIscritti.add(programmaFedelta);
                        }
                    }
                }
        if (!programmiIscritti.isEmpty()) {
            for (ProgrammaFedelta programmaFedelta : programmiIscritti) {
                if (programmaFedelta.getProgrammaPunti() != null) {
                    addPuntiPP(programmaFedelta.getProgrammaPunti(), importoSpeso, tessera);
                } else if (programmaFedelta.getProgrammaLivelli() != null) {
                    addLivelloPL(programmaFedelta.getProgrammaLivelli(), tessera);
                }
            }
        }
    }

    public void addPuntiManuale(int numeroPunti, int idTessera, ProgrammaFedelta pf) {
        Objects.requireNonNull(pf);
        for (Tessera tessera : this.getDbms().getTessereClienti()) {
            if (tessera.getIdTessera() == idTessera) {
                tessera.addPunti(numeroPunti);
                //isPuntiVip(tessera.getPunti(), pf.getProgrammaPunti(), tessera);
            }
            throw new IllegalArgumentException("Card not exists.");
        }
    }

    public void addPuntiClienti(Set<Integer> idTessere, int numeroPunti) {
        //TODO implementare
    }

    public void removePuntiCliente(int idTessera, int numeroPunti){
        if (idTessera < 1)
            throw new IllegalArgumentException("Id not correct");
        if(numeroPunti < 1)
            throw new IllegalArgumentException("Points not correct");
        for(Tessera tessera : this.getDbms().getTessereClienti()){
            if(tessera.getIdTessera() == idTessera){
                tessera.deletePunti(numeroPunti);
                //Controlla cliente vip
            }
            throw new IllegalArgumentException("Card not exists.");
        }
    }

    public void removePuntiClienti(Set<Integer> idTessere, int numeroPunti){
        //TODO implementare
    }


    /**
     * This method adds a loyalty program to the customer card,
     * when the customer signs up for a loyalty program.
     * @param idTessera card where to add the program.
     * @param programmaFedelta loyalty program to add.
     */
    public void addProgrammaFedelta(int idTessera, ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        if (idTessera < 1)
            throw new IllegalArgumentException("Id not correct");
        for (Tessera tessera : this.getDbms().getTessereClienti()) {
            if (tessera.getIdTessera() == idTessera) {
                for(ProgrammaFedelta program: this.getDbms().getProgrammiDisponibili()) {
                    if (program.equals(programmaFedelta)) {
                        for(ProgrammaFedelta toScroll : tessera.getProgrammiFedelta()){
                            if(!toScroll.equals(program)){
                                tessera.addPogrammaFedelta(programmaFedelta);
                            }
                            throw new IllegalArgumentException("Program already exists.");
                        }
                    }
                    throw new IllegalArgumentException("Program not exists.");
                }
            }
            throw new IllegalArgumentException("Card not exists.");
        }
    }

    /**
     * This method removes a loyalty program from the customer's card,
     * if they unsubscribe from the program.
     * @param idTessera card where to remove the program.
     * @param programmaFedelta loyalty program to remove.
     */
    public void deleteProgrammaFedelta(int idTessera, ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        if(idTessera <1)
            throw new IllegalArgumentException("Id not correct");
        for(Tessera tessera : this.getDbms().getTessereClienti()){
            if(tessera.getIdTessera()==idTessera){
                for(ProgrammaFedelta program : tessera.getProgrammiFedelta()){
                    if(program.equals(programmaFedelta)){
                        tessera.deleteProgrammaFedelta(programmaFedelta);
                    }
                    throw new IllegalArgumentException("Program not presents.");
                }
            }
            throw new IllegalArgumentException("Card not exists.");
        }
    }

    private void addPuntiPP(ProgrammaPunti pp, double importoSpeso, Tessera tessera) {
        int puntiDaAggiungere = 0;
        puntiDaAggiungere += (importoSpeso * pp.getPuntiSpesa() / pp.getImportoSpesa());
        tessera.addPunti(puntiDaAggiungere);
        int puntiAccumulati = tessera.getPunti();
        isPuntiVip(puntiAccumulati, pp, tessera);
    }

    private void isPuntiVip(int puntiTotali, ProgrammaPunti pp, Tessera tessera) {
        if (puntiTotali >= pp.getPuntiVIP())
            tessera.setVipPunti(true);
    }

    private void addLivelloPL(ProgrammaLivelli pl, Tessera tessera) {
        //TODO implementare
    }

}
