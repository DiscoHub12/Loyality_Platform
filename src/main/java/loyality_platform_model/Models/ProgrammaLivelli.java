package loyality_platform_model.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 *  Class representing one of many loyalty programs
 *  available within the platform.
 *  In particular, this program follows the rule of levels, ie
 *  the more a customer spends, the more points he will have, the more he will level up.
 *  Every time a customer makes a purchase, he gets a certain number of points,
 *  depending on purchase.
 *  Thanks to these levels the Customer will be able to choose the rewards
 *  present in the Awards Catalogue.
 */
public class ProgrammaLivelli {

    private static int idProgrammaLivelli;

    private int massimoLivelli, livelloVip;

    private final Map<Integer, Integer> puntiPerLivello;

    private CatalogoPremi catalogoPremi;

    public ProgrammaLivelli(int massimoLivelli, int livelloVip, CatalogoPremi catalogoPremi) {
        idProgrammaLivelli++;
        this.setMassimoLivelli(massimoLivelli);
        this.setLivelloVip(livelloVip);
        this.setCatalogoPremi(catalogoPremi);
        puntiPerLivello = new HashMap<>();
    }

    /**
     * This method returns the program id
     * @return the program id.
     */
    public static int getIdProgrammaLivelli() {
        return idProgrammaLivelli;
    }

    /**
     * This method returns the maximum number of levels.
     * @return the maximum number of levels.
     */
    public int getMassimoLivelli() {
        return massimoLivelli;
    }

    /**
     * This method sets the maximum number of levels.
     * @param massimoLivelli maximum number of levels.
     */
    public void setMassimoLivelli(int massimoLivelli) {
        if(massimoLivelli < 1)
            throw new IllegalArgumentException("Invalid number for level number.");
        this.massimoLivelli = massimoLivelli;
    }

    /**
     * This method returns the level number to become vip.
     * @return the level number to become vip.
     */
    public int getLivelloVip() {
        return livelloVip;
    }

    /**
     * This method sets the level number to become vip.
     * @param livelloVip the level number to become vip.
     */
    public void setLivelloVip(int livelloVip) {
        if (livelloVip < 1 || livelloVip > this.getMassimoLivelli())
            throw new IllegalArgumentException("Illegal max number of level.");
        this.livelloVip = livelloVip;
    }

    /**
     * This method returns the number of points to get for each level.
     * @return the number of points to get for each level.
     */
    public Map<Integer, Integer> getPuntiPerLivello() {
        return puntiPerLivello;
    }

    /**
     * This method returns the prize catalog.
     * @return the prize catalog.
     */
    public CatalogoPremi getCatalogoPremi() {
        return catalogoPremi;
    }

    /**
     * This method sets up the rewards catalog.
     * @param catalogoPremi the rewards catalog.
     */
    public void setCatalogoPremi(CatalogoPremi catalogoPremi) {
        Objects.requireNonNull(catalogoPremi);
        this.catalogoPremi = catalogoPremi;
    }

    /**
     * This method adds a new layer and the points associated with it.
     * @param punti points associated at the level.
     */
    public void aggiungiLivello(int punti) {
        int appoggio = 1;
        if(punti < 1 )
            throw new IllegalArgumentException("Invalid number of points.");
         appoggio += this.getPuntiPerLivello().get(this.puntiPerLivello.size());
         if(appoggio > this.getMassimoLivelli())
             throw new IllegalArgumentException("Is not possible to add a new level.");
        this.getPuntiPerLivello().put(appoggio, punti);
    }

    /**
     * This method removes a layer and its associated points.
     * @param livello layer to remove.
     */
    public void rimuoviLivello(int livello){
        if(livello < 1 || livello> this.puntiPerLivello.size())
            throw new IllegalArgumentException("Invalid number of level.");
        if(!this.getPuntiPerLivello().containsKey(livello))
            throw new IllegalArgumentException("Level not exist.");
        this.getPuntiPerLivello().remove(livello);
    }

    /**
     * This method modifies the points for a certain level.
     * @param punti points to edit.
     * @param livello level taken into consideration.
     */
    public void modificaPuntiPerLivello(int punti, int livello){
        if(punti < 1 )
            throw new IllegalArgumentException("Invalid number of points.");
        if(livello < 1 || livello> this.puntiPerLivello.size())
            throw new IllegalArgumentException("Invalid number of level.");
        for (Map.Entry<Integer, Integer> entry : this.getPuntiPerLivello().entrySet()){
            if(livello== entry.getKey()){
                this.getPuntiPerLivello().replace(livello, entry.getValue(), punti);
            }
        }
    }
}
