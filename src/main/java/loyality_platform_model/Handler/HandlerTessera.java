package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;
import java.util.HashSet;
import java.util.Map;
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
     * @return <code>true</code> if the card is created successfully, <code>false</code> otherwise.
     */
    public boolean creaTessera(int idCliente) {
        if (idCliente < 1)
            throw new IllegalArgumentException("Id not correct.");
        Tessera tessera = new Tessera(idCliente);
        return this.getDbms().addTessera(tessera);
    }


    /**
     * This method allows you to automatically add points to a customer's card after they have made a purchase,
     * by applying the policies of a certain loyalty program to which they belong.
     *
     * @param importoSpeso amount spent by the customer.
     * @param tessera      customer card on which to add points.
     * @param azienda      company where you make the purchase.
     */
    public void addPuntiAcquisto(double importoSpeso, Tessera tessera, Azienda azienda) {
        Objects.requireNonNull(tessera);
        Objects.requireNonNull(azienda);
        if (importoSpeso < 1)
            throw new IllegalArgumentException("Import not correct.");
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
                }
                if (programmaFedelta.getProgrammaLivelli() != null) {
                    addLivelloPL(programmaFedelta.getProgrammaLivelli(), tessera);
                }
            }
        }
    }

    /**
     * This method allows you to manually add points to a customer's card.
     * @param numeroPunti points to add.
     * @param idTessera customer card on which to add points.
     * @return <code>true</code> if the points are added correctly, <code>false</code> otherwise.
     */
    public boolean addPuntiManuale(int numeroPunti, int idTessera) {
        if (idTessera < 1)
            throw new IllegalArgumentException("Id not correct");
        if (numeroPunti < 1)
            throw new IllegalArgumentException("Points not correct");
        if (this.getDbms().getTesseraById(idTessera) != null) {
            Tessera tessera = this.getDbms().getTesseraById(idTessera);
            tessera.addPunti(numeroPunti);
            return true;
        }
        return false;
    }

    /**
     * This method allows you to manually add points to a customer's card.
     * @param idTessere customer cards on which to add points.
     * @param numeroPunti points to add.
     * @return <code>true</code> if the points are added correctly, <code>false</code> otherwise.
     */
    public boolean addPuntiClienti(Set<Integer> idTessere, int numeroPunti) {
        if (numeroPunti < 1)
            throw new IllegalArgumentException("Points not correct");
        for (Integer toScroll : idTessere) {
            if (toScroll < 1)
                throw new IllegalArgumentException("Id not correct.");
            if (this.getDbms().getTesseraById(toScroll) != null) {
                Tessera tessera = this.getDbms().getTesseraById(toScroll);
                tessera.addPunti(numeroPunti);
                return true;
            }
        }
        return false;
    }

    /**
     * This method allows you to manually remove points to a customer's card.
     * @param numeroPunti points to remove.
     * @param idTessera customer card on which to add points.
     * @return <code>true</code> if the points are deleted correctly, <code>false</code> otherwise.
     */
    public boolean removePuntiCliente(int idTessera, int numeroPunti) {
        if (idTessera < 1)
            throw new IllegalArgumentException("Id not correct");
        if (numeroPunti < 1)
            throw new IllegalArgumentException("Points not correct");
        if (this.getDbms().getTesseraById(idTessera) != null) {
            Tessera tessera = this.getDbms().getTesseraById(idTessera);
            tessera.deletePunti(numeroPunti);
            return true;
        }
        return false;
    }

    /**
     * This method allows you to manually remove points to a customer's card.
     * @param idTessere customer cards on which to remove points.
     * @param numeroPunti points to remove.
     * @return <code>true</code> if the points are deleted correctly, <code>false</code> otherwise.
     */
    public boolean removePuntiClienti(Set<Integer> idTessere, int numeroPunti) {
        if (numeroPunti < 1)
            throw new IllegalArgumentException("Points not correct");
        for (Integer toScroll : idTessere) {
            if (toScroll < 1)
                throw new IllegalArgumentException("Id not correct.");
            if (this.getDbms().getTesseraById(toScroll) != null) {
                Tessera tessera = this.getDbms().getTesseraById(toScroll);
                tessera.deletePunti(numeroPunti);
                return true;
            }
        }
        return false;
    }


    /**
     * This method adds a loyalty program to the customer card,
     * when the customer signs up for a loyalty program.
     * @param idTessera card where to add the program.
     * @param programmaFedelta loyalty program to add.
     * @return <code>true</code> if the program is added successfully, <code>false</code> otherwise.
     */
    public boolean addProgrammaFedelta(int idTessera, ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        if (idTessera < 1)
            throw new IllegalArgumentException("Id not correct");
        Tessera tessera = this.getDbms().getTesseraById(idTessera);
        for (Map.Entry<Azienda, Set<ProgrammaFedelta>> entry : this.getDbms().getProgrammiAzienda().entrySet()) {
            for (ProgrammaFedelta program : entry.getValue()) {
                if (program.equals(programmaFedelta)) {
                    for (ProgrammaFedelta toScroll : tessera.getProgrammiFedelta()) {
                        if (!toScroll.equals(program)) {
                            tessera.addPogrammaFedelta(programmaFedelta);
                            return this.getDbms().addClienteCoalizione(tessera.getIdCliente(), programmaFedelta);
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method removes a loyalty program to the customer card,
     * when the customer signs up for a loyalty program.
     * @param idTessera card where to remove the program.
     * @param programmaFedelta loyalty program to remove.
     * @return <code>true</code> if the program is deleted successfully, <code>false</code> otherwise.
     */
    public boolean deleteProgrammaFedelta(int idTessera, ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        if (idTessera < 1)
            throw new IllegalArgumentException("Id not correct");
        Tessera tessera = this.getDbms().getTesseraById(idTessera);
        for (ProgrammaFedelta program : tessera.getProgrammiFedelta()) {
            if (program.equals(programmaFedelta)) {
                tessera.deleteProgrammaFedelta(programmaFedelta);
                return this.getDbms().deleteClienteCoalizione(tessera.getIdCliente(), programmaFedelta);
            }
        }
        return false;
    }

    /**
     * This loyalty program based method enforces the policies and adds the points to the card.
     *
     * @param pp           points program considered.
     * @param importoSpeso amount spent by the customer.
     * @param tessera      customer card on which to add points.
     */
    private void addPuntiPP(ProgrammaPunti pp, double importoSpeso, Tessera tessera) {
        int puntiDaAggiungere = 0;
        puntiDaAggiungere += (importoSpeso * pp.getPuntiSpesa() / pp.getImportoSpesa());
        tessera.addPunti(puntiDaAggiungere);
    }

    /**
     * This method enforces the loyalty program policies and escalates the customer level if necessary.
     *
     * @param pl      levels program considered.
     * @param tessera customer card.
     */
    private void addLivelloPL(ProgrammaLivelli pl, Tessera tessera) {
        Objects.requireNonNull(tessera);
        Objects.requireNonNull(pl);
        //TODO implementare
        tessera.incrementLivello();
    }

}
