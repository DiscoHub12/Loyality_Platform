package loyality_platform_model.Models;

import java.util.Date;
import java.util.Objects;


/**
 * Class representing the Coupon concept.
 * Within this platform, an employee can assign a Coupon to a Customer,
 * and a Customer when making a purchase can use Coupons which are nothing more
 * than a discount to be applied to the current purchase.
 */
public class Coupon {

    private static int contatoreCoupon = 0;

    /**
     * This attribute rapresents the
     * unique id of this Coupon.
     */
    private int idCoupon;

    /**
     * This attribute rapresents the discount
     * value of this Coupon.
     */
    private int valoreSconto;

    /**
     * This attribute rapresents the activation date
     * of this Coupon.
     */
    private Date dataAttivazione;

    /**
     * This attribute rapresents the expiration
     * date of this Coupon.
     */
    private String dataScadenza;

    /**
     * Constructor that allows you to create a new Coupon,
     * and it will be possible to provided it to a Costumer as a
     * gift, or a Costumer who owns it will be able to user it
     * for a Purchase.
     *
     * @param valoreSconto the discount value of this Coupon.
     * @param dataScadenza expiration date of this Coupon.
     */
    public Coupon(int valoreSconto, String dataScadenza) {
        this.idCoupon = ++contatoreCoupon;
        this.setValoreSconto(valoreSconto);
        this.setDataScadenza(dataScadenza);
    }

    public int getIdCoupon() {
        return idCoupon;
    }

    public int getValoreSconto() {
        return valoreSconto;
    }

    public void setValoreSconto(int valoreSconto) {
        if (valoreSconto < 5)
            throw new IllegalArgumentException("Illegal discount value for the Coupon.");
        this.valoreSconto = valoreSconto;
    }

    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    /**
     * Method that set the actiovation Date about this Coupon.
     *
     * @param dataAttivazione the activation Date.
     * @throws NullPointerException if the dataAttivazione is null.
     */
    public void setDataAttivazione(Date dataAttivazione) {
        Objects.requireNonNull(dataAttivazione);
        this.dataAttivazione = dataAttivazione;
    }

    public String getDataScadenza() {
        return this.dataScadenza;
    }

    /**
     * Method that set the expiration date about this Coupon.
     *
     * @param dataScadenza the expiration date.
     * @throws NullPointerException if the dataScadenza is null.
     */
    public void setDataScadenza(String dataScadenza) {
        if(Objects.equals(dataScadenza, ""))
            throw new IllegalArgumentException("Illegal expiration date.");
        this.dataScadenza = dataScadenza;
    }

    /**
     * Equals method of the Coupon, simply
     * compare if the passed object is equivalent to this Coupon,
     * by checking the id, and the value.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof Coupon tmp) {
            if (this.getIdCoupon() == tmp.getIdCoupon() && this.getValoreSconto() == tmp.getValoreSconto())
                return true;
        }
        return false;
    }

    public String toString() {
        return "\t-DETAILS COUPON-" +
                "\nId Coupon : " + this.getIdCoupon() +
                "\nValore Sconto : " + this.getValoreSconto() +
                "\nData attivazione : " + this.getDataAttivazione() +
                "\nData Scadenza : " + this.getDataScadenza();
    }
}
