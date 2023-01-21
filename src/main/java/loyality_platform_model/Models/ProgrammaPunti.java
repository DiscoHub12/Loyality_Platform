package loyality_platform_model.Models;

import java.util.Objects;

/**
 * IMPLEMENTED BY : Alessio Giacché.
 */

/**
 * Class that represents one of the many loyalty programs
 * available within the platform.
 * Especially, this program follows the points rule, i.e.
 * the more a customer spends, the more points he will have.
 * Every time a customer makes a purchase, he gets a certain number of points,
 * depending on the purchase.
 * Thanks to these points, the Customer will be able to choose prizes
 * that are present in the Prize Catalogue.
 */
public class ProgrammaPunti {

    /**
     * This attribute represents the id of a
     * given points program.
     */
    private static int id;

    /**
     * This attribute represents the amount that
     * a specific Customer must pay to obtain a certain
     * number of points.
     */
    private double importoSpesa;

    /**
     * This attribute represents the points that
     * a given Customer achieves when they spend the
     * expected amount.
     */
    private int puntiPerImporto;

    /**
     * This attribute represents a boolean value that
     * indicates whether this specific program has a maximum of
     * attainable points or not.
     */
    private boolean maxPunti;

    /**
     * This attribute represents (if this program has a
     * maximum of points) the maximum number of points.
     */
    private int numeroPuntiMassimi;

    /**
     * This attribute represents the number of
     * points to reach the VIP level.
     */
    private int puntiVIP;

    /**
     * This object represents a Prize Catalogue.
     * The Rewards Catalog is part of every loyalty program,
     * as it contains a list of available rewards,
     * given to Customers who reach certain points.
     */
    private CatalogoPremi catalogoPremi;

    /**
     * Constructor who creates a Loyalty Program of the Points Program type.
     * This constructor is called if this Points Program does not have a
     * maximum number of points. It requests the fundamental attributes for creation,
     * and invokes set methods which contain controls within them.
     * @param importoSpesa the amount that a specific Customer must pay to obtain a certain number of points.
     * @param puntiPerImporto the points that a given Customer achieves when they spend the expected amount.
     * @param puntiVIP the number of points to reach the VIP level.
     * @param catalogoPremi  a Prize Catalogue.
     */
    public ProgrammaPunti(double importoSpesa, int puntiPerImporto, int puntiVIP, CatalogoPremi catalogoPremi){
        id++;
        setImportoSpesa(importoSpesa);
        setPuntiPerImporto(puntiPerImporto);
        setPuntiVIP(puntiVIP);
        setCatalogoPremi(catalogoPremi);
        this.maxPunti = false;
    }

    /**
     * Constructor who creates a Loyalty Program of the Points Program type.
     * This constructor is called if this Points Program have a
     * maximum number of points. It requests the fundamental attributes for creation,
     * and invokes set methods which contain controls within them.
     * @param importoSpesa the amount that a specific Customer must pay to obtain a certain number of points.
     * @param puntiPerImporto the points that a given Customer achieves when they spend the expected amount.
     * @param numeroPuntiMassimi the max number of points that Costumer must have.
     * @param puntiVIP the number of points to reach the VIP level.
     * @param catalogoPremi  a Prize Catalogue.
     */
    public ProgrammaPunti(double importoSpesa, int puntiPerImporto, int numeroPuntiMassimi, int puntiVIP, CatalogoPremi catalogoPremi){
        id++;
        setImportoSpesa(importoSpesa);
        setPuntiPerImporto(puntiPerImporto);
        setNumeroPuntiMassimi(numeroPuntiMassimi);
        setPuntiVIP(puntiVIP);
        setCatalogoPremi(catalogoPremi);
        this.maxPunti = true;
    }

    public double getImportoSpesa() {
        return importoSpesa;
    }

    public void setImportoSpesa(double importoSpesa) {
        if(importoSpesa <= 0)
            throw new IllegalArgumentException("Illegal expense amount.");
        this.importoSpesa = importoSpesa;
    }

    public int getPuntiPerImporto() {
        return puntiPerImporto;
    }

    public void setPuntiPerImporto(int puntiPerImporto) {
        if(puntiPerImporto <= 0)
            throw new IllegalArgumentException("Illegal number of points for expense amount.");
        this.puntiPerImporto = puntiPerImporto;
    }

    public boolean isMaxPunti() {
        return maxPunti;
    }

    public void setMaxPunti(boolean maxPunti) {
        this.maxPunti = maxPunti;
    }

    public int getNumeroPuntiMassimi() {
        return numeroPuntiMassimi;
    }

    public void setNumeroPuntiMassimi(int numeroPuntiMassimi) {
        if(numeroPuntiMassimi <= 0)
            throw new IllegalArgumentException("Illegal max number of points.");
        this.numeroPuntiMassimi = numeroPuntiMassimi;
    }

    public int getPuntiVIP() {
        return puntiVIP;
    }

    public void setPuntiVIP(int puntiVIP) {
        if(puntiVIP <= 0)
            throw new IllegalArgumentException("Illegal number for VIP level.");
        this.puntiVIP = puntiVIP;
    }

    public CatalogoPremi getCatalogoPremi() {
        return catalogoPremi;
    }

    public void setCatalogoPremi(CatalogoPremi catalogoPremi) {
        Objects.requireNonNull(catalogoPremi);
        this.catalogoPremi = catalogoPremi;
    }
}
