package loyality_platform_model.Models;

import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * Class that represent the concept of Reward Catalog.
 * This Catalog is owned by one of the many Loyalty Programs,
 * and can be modified and built directly by the Manager who
 * will choose to implement a new Loyalty Program within the platform.
 * It is composed of a series of information such as the prizes inside it.
 */
public class CatalogoPremi {


    /**
     * This attribute represents the
     * unique id of this Reward Catalogue.
     */
    private static int idCatalogoPremi;

    /**
     * This attribute represents the list
     * of prizes of this CatalogoPremi
     * presented by a Set.
     */
    private Set<Premio> premiCatalogo;


    /**
     * Constructor that allow to create a new Reward
     * Catalog that will be used within a specific Loyality
     * Program, and which be visible to a Costumer to
     * redem any reward.
     *
     * @param premiCatalogo the prizes of this catalogue
     * @throws NullPointerException if the premiCatalogo is null.
     */
    public CatalogoPremi(Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        idCatalogoPremi++;
        this.setPremiCatalogo(premiCatalogo);

    }

    public int getIdCatalogoPremi() {
        return idCatalogoPremi;
    }

    public Set<Premio> getPremiCatalogo() {
        return this.premiCatalogo;
    }

    public void setPremiCatalogo(Set<Premio> premi) {
        Objects.requireNonNull(premi);
        this.premiCatalogo = premi;
    }

    /**
     * This method allows you to add a new prize to this prize
     * Catalog if it does not exist.
     *
     * @param premio the prize to add.
     * @throws NullPointerException     if the premio is null.
     * @throws IllegalArgumentException if Reward Catalog already contains this Prize.
     */
    public void aggiungiPremio(Premio premio) {
        Objects.requireNonNull(premio);
        if (this.premiCatalogo.contains(premio))
            throw new IllegalArgumentException("Prize already exists in this Reward Catalog.");
        this.premiCatalogo.add(premio);
    }

    /**
     * This method allows you to update a prize if
     * it exists in this Reward Catalog.
     *
     * @param premioOld the prize to update.
     * @param premioNew the updated prize.
     * @throws NullPointerException     if the premioOld or premioNew are null.
     * @throws IllegalArgumentException if the Reward Catalog not contains premioOld.
     */
    public void updatePremio(Premio premioOld, Premio premioNew) {
        Objects.requireNonNull(premioOld);
        Objects.requireNonNull(premioNew);
        if (!this.premiCatalogo.contains(premioOld))
            throw new IllegalArgumentException("Prize not exist in this Reward Catalog.");
        for (Premio premio : this.premiCatalogo) {
            if (premio.equals(premioOld)) {
                premio = premioNew;
            }
        }
    }

    /**
     * This method allows you to remove a particular prize in
     * this Reward Catalog.
     *
     * @param premio the prize to remove.
     * @throws NullPointerException     if the premio is null.
     * @throws IllegalArgumentException it this Reward Catalog not contains this prize.
     */
    public void rimuoviPremio(Premio premio) {
        Objects.requireNonNull(premio);
        if (!this.premiCatalogo.contains(premio))
            throw new IllegalArgumentException("Prize not exist in this Reward Catalog.");
        this.premiCatalogo.remove(premio);
    }

    private String toStringPremi() {
        if(this.premiCatalogo.isEmpty())
            return "There are not prize in this Reward Catalog.";
        String tmp = "";
        for(int i=0; i < this.premiCatalogo.size(); i++){
            tmp += i + ") PREMIO : " + this.premiCatalogo.toArray()[i].toString();
        }
        return tmp;
    }

    /**
     * Equals method of the CatalogoPremi class, simply
     * compare if the passed object is equivalent to this CatalogoPremi,
     * by checking the id, and the prizes.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if(object == null)
            return false;
        if(object instanceof CatalogoPremi tmp){
            if(this.getIdCatalogoPremi() == tmp.getIdCatalogoPremi() && this.getPremiCatalogo() == tmp.getPremiCatalogo())
                return true;
        }
        return false;
    }

    public String toString() {
        return "\t-DETAILS CATALOGO PREMI-" +
                "\nId Catalogo : " + idCatalogoPremi +
                "\nElenco Premi : " + toStringPremi();
    }
}