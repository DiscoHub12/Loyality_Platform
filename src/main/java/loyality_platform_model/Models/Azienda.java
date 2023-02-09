package loyality_platform_model.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing a company within this platform.
 * A company has its own loyalty space containing all its information,
 * as loyalty programs and a set of customers and employees.
 */

public class Azienda {

    /**
     * This attribute represents the unique id
     * of this Company (Azienda).
     */
    private static int idAzienda;

    /**
     * This attribute represents the Loyalty Space
     * containing all Company (Azienda) details.
     */
    private SpazioFedelta spazioFedelta;

    /**
     * This attribute represents the
     * Store Manager of this Company (Azienda).
     */
    private GestorePuntoVendita titolare;

    /**
     * This attribute represents the
     * general Rewards Catalog, which this Company offers to
     * enrolled Customers.
     */
    private Set<CatalogoPremi> catalogoPremi;

    /**
     * Constructor that allows you to create and add a new
     * registered Company (Azienda) to the platform.
     *
     * @param titolare      the Holder for this Company (Azienda).
     * @param spazioFedelta the Loyality Space for this Company (Azienda)
     */
    public Azienda(GestorePuntoVendita titolare, SpazioFedelta spazioFedelta) {
        idAzienda++;
        this.setTitolare(titolare);
        this.spazioFedelta = spazioFedelta;
        this.catalogoPremi = new HashSet<>();
    }

    public int getIdAzienda() {
        return idAzienda;
    }

    public SpazioFedelta getSpazioFedelta() {
        return this.spazioFedelta;
    }

    public void setSpazioFedelta(SpazioFedelta spazioFedelta) {
        Objects.requireNonNull(spazioFedelta);
        this.spazioFedelta = spazioFedelta;
    }

    public GestorePuntoVendita getTitolare() {
        return this.titolare;
    }

    public void setTitolare(GestorePuntoVendita titolare) {
        Objects.requireNonNull(titolare);
        this.titolare = titolare;
    }

    public Set<CatalogoPremi> getCatalogoPremi() {
        return this.catalogoPremi;
    }

    public void setCatalogoPremi(Set<CatalogoPremi> catalogoPremi) {
        Objects.requireNonNull(catalogoPremi);
        this.catalogoPremi = catalogoPremi;
    }

    /**
     * This method allows you to add a general Reward
     * Catalog to the Store.
     *
     * @param catalogoPremi the Reward Catalog to add.
     * @throws NullPointerException     if catalogoPremi is null.
     * @throws IllegalArgumentException if the list of Reward Catalog already container catalogoPremi.
     */
    public void addCatalogoPremi(CatalogoPremi catalogoPremi) {
        Objects.requireNonNull(catalogoPremi);
        if (this.catalogoPremi.contains(catalogoPremi))
            throw new IllegalArgumentException("Reward Catalog already exist.");
        this.catalogoPremi.add(catalogoPremi);
    }

    /**
     * This method allows you to update a Reward Catalog
     * (taken by its id).
     *
     * @param idCatalogoPremi  the id for the Reward Catalog to update.
     * @param newCatalogoPremi the updated Reward Catalog.
     * @throws NullPointerException     if the newCatalogoPremi is null.
     * @throws IllegalArgumentException if the idCatalogoPremi is not correct or if the Reward Catalogo
     *                                  not contains a Catalog with this id.
     */
    public void updateCatalogoPremi(int idCatalogoPremi, CatalogoPremi newCatalogoPremi) {
        Objects.requireNonNull(newCatalogoPremi);
        if (idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Illegal id for the Reward Catalog.");
        for (CatalogoPremi catalogoPremi : this.catalogoPremi) {
            if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                catalogoPremi = newCatalogoPremi;
            } else throw new IllegalArgumentException("The Reward Catalog with this id not exists.");
        }
    }

    /**
     * This method allows you to remove a
     * Rewards Catalog if it exists.
     *
     * @param catalogoPremi the Reward Catalog to remove.
     * @throws NullPointerException     if catalogoPremi is null.
     * @throws IllegalArgumentException if the Rewards Catalogs not contains catalogoPremi.
     */
    public void removeCatalogoPremi(CatalogoPremi catalogoPremi) {
        Objects.requireNonNull(catalogoPremi);
        if (!this.catalogoPremi.contains(catalogoPremi))
            throw new IllegalArgumentException("Reward Catalog not present.");
        this.catalogoPremi.remove(catalogoPremi);
    }

    /**
     * This is the Equals method, and allows you to compare two Companies,
     * verifying if the id and loyalty space are equal.
     *
     * @param object the object to compare.
     * @return true if are equals, false otherwise.
     */
    public boolean equals(Object object) {
        if(object == null)
            return false;
        if(object instanceof Azienda tmp){
            if(this.getIdAzienda() == tmp.getIdAzienda() && this.getSpazioFedelta() == tmp.getSpazioFedelta())
                return true;
        }
        return false;
    }

    private String catalogoPremiIfExist(){
        String tmp = "";
        if(this.catalogoPremi.isEmpty())
            tmp = "There is no active Rewards Catalogue.";
        for(int i=0; i < this.catalogoPremi.size(); i++){
            tmp += i + ")" + this.catalogoPremi.toArray()[i].toString();
        }
        return tmp;
    }

    public String toString() {
        return "\t-DETAILS AZIENDA-" +
                "\nId Azienda : " + this.getIdAzienda() +
                "\nSpazio Fedelta : " + this.getSpazioFedelta().toString() +
                "\nTitolare : " + this.getTitolare().toString() +
                "\n" + catalogoPremiIfExist();
    }
}
