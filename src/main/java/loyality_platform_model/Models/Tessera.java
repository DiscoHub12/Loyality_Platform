package loyality_platform_model.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 * The class represents the card of a specific customer.
 * A card is identified by an id and its owner.
 * Loyalty programs to which the customer is a member are also marked.
 * Inside we can subscribe to a new loyalty program or remove an old one and add or remove points.
 */

public class Tessera {

    private static int idTessera;

    private final int idCliente;
    private Set<ProgrammaFedelta> programmiFedelta;

    private int punti;

    public Tessera(int idCliente) {
        idTessera++;
        this.idCliente = idCliente;
        this.programmiFedelta = new HashSet<>();
    }

    /**
     * This method returns the card id
     * @return the card id.
     */
    public static int getIdTessera() {
        return idTessera;
    }

    /**
     * This method returns the customer id
     * @return the customer id.
     */
    public int getIdCliente() {
        return idCliente;
    }


    /**
     * This method returns a loyalty programs to which the customer is a member.
     * @return loyalty programs to which the customer is a member.
     */
    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    /**
     * This method returns the number of points the customer owns.
     * @return the number of points the customer owns.
     */
    public int getPunti() {
        return punti;
    }

    /**
     * This method adds a new loyalty program.
     * @param programmaFedelta new loyalty program.
     */
    public void addPogrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        if(this.getProgrammiFedelta().contains(programmaFedelta))
            throw new IllegalArgumentException("Program already present.");
        this.getProgrammiFedelta().add(programmaFedelta);
    }

    /**
     * This method removes a loyalty program.
     * @param programmaFedelta loyalty program to be removed.
     */
   public void deleteProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
       if(!this.getProgrammiFedelta().contains(programmaFedelta))
           throw new IllegalArgumentException("Program not exists.");
       this.getProgrammiFedelta().remove(programmaFedelta);

   }

   public void updateSetProgrammi(Set<ProgrammaFedelta> setAggiornato){
       this.programmiFedelta = setAggiornato;
   }

    /**
     * This method adds a certain number of points.
     * @param punti number of points to add.
     */
   public void addPunti(int punti){
        if(punti < 1)
            throw new IllegalArgumentException("Numero punti errato");
        this.punti += punti;
   }

    /**
     * This method removes a certain number of points.
     * @param punti number of points to remove.
     */
   public void deletePunti (int punti){
       if(punti < 1)
           throw new IllegalArgumentException("Numero punti errato");
       this.punti -= punti;
   }

    @Override
    public String toString() {
        return "Tessera{" +
                "idCliente =" + idCliente +
                ", programmiFedelta =" + toStringProgrammiFedelta() +
                ", punti =" + punti +
                '}';
    }

    private String toStringProgrammiFedelta() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ProgrammaFedelta programmaFedelta : this.getProgrammiFedelta()) {
            stringBuilder.append("\n-").append(programmaFedelta);
        }
        return stringBuilder.toString();
    }
}
