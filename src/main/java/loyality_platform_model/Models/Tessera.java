package loyality_platform_model.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * The class represents the card of a specific customer.
 * A card is identified by an id and its owner.
 * Loyalty programs to which the customer is a member are also marked.
 * Inside we can subscribe to a new loyalty program or remove an old one and add or remove points.
 */

public class Tessera {

    private int idTessera;

    private final int idCliente;
    private final Set<ProgrammaFedelta> programmiFedelta;

    private int punti;

    private int livelli;

    private boolean isVipPunti;

    private  boolean isVipLivelli;

    public Tessera(int idCliente) {
        idTessera++;
        this.idCliente = idCliente;
        this.programmiFedelta = new HashSet<>();
        this.punti = 0;
        this.livelli = 0;
        this.isVipPunti = false;
        this.isVipLivelli = false;
    }

    /**
     * This method returns the card id
     * @return the card id.
     */
    public int getIdTessera() {
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
     * This method adds a certain number of points.
     * @param punti number of points to add.
     */
    public void addPunti(int punti){
        if(punti < 1)
            throw new IllegalArgumentException("Invalid points.");
        this.punti += punti;
    }

    /**
     * This method removes a certain number of points.
     * @param punti number of points to remove.
     */
    public void deletePunti (int punti){
        if(punti < 1 || punti > this.getPunti())
            throw new IllegalArgumentException("Invalid points.");
        this.punti -= punti;
    }

    /**
     * This method returns <code>true</code> if in the points program they are vip customers,
     * <code>false</code> otherwise.
     * @return <code>true</code> if in the points program they are vip customers,
     *   <code>false</code> otherwise.
     */
    public boolean isVipPunti() {
        return isVipPunti;
    }

    /**
     * This method sets whether you are a vip customer in the points program.
     * @param vipPunti <code>true</code> if you are a vip costumer, <code>false</code> otherwise.
     */
    public void setVipPunti(boolean vipPunti) {
        isVipPunti = vipPunti;
    }

    /**
     * This method returns the number of the level reached.
     * @return the number of the level reached.
     */
    public int getLivelli() {
        return livelli;
    }

    /**
     * This method increments the number of the level reached.
     */
    public void incrementLivello(){
        this.livelli++;
    }

    /**
     * This method returns <code>true</code> if in the levels program they are vip customers,
     * <code>false</code> otherwise.
     * @return <code>true</code> if in the levels program they are vip customers,
     *   <code>false</code> otherwise.
     */
    public boolean isVipLivelli() {
        return isVipLivelli;
    }

    /**
     * This method sets whether you are a vip customer in the levels program.
     * @param vipLivelli <code>true</code> if you are a vip costumer, <code>false</code> otherwise.
     */
    public void setVipLivelli(boolean vipLivelli) {
        isVipLivelli = vipLivelli;
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

    /**
     * Equals method of the card class, simply
     * compare if the passed object is equivalent to this card,
     * by checking the id, and the id Costumer.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof Tessera tmp) {
            return this.getIdTessera() == tmp.getIdTessera() && this.getIdCliente() == tmp.getIdCliente();
        }
        return false;
    }

    @Override
    public String toString() {
        return "\t-DETAILS TESSERA-" +
                "\nId Tessera: "+ idTessera +
                "\nId Cliente: " + idCliente +
                "\nProgrammiFedelta: " + toStringProgrammiFedelta() +
                "\nPunti: " + punti ;
    }

    private String toStringProgrammiFedelta() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ProgrammaFedelta programmaFedelta : this.getProgrammiFedelta()) {
            stringBuilder.append("\n-").append(programmaFedelta);
        }
        return stringBuilder.toString();
    }
}
