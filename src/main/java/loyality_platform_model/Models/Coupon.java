package loyality_platform_model.Models;

import java.util.Date;
import java.util.Objects;

public class Coupon {

    /**
     * This attribute rapresents the
     * unique id of this Coupon.
     */
    private static int idCoupon;

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
    private Date dataScadenza;

    /**
     * Constructor that allows you to create a new Coupon,
     * and it will be possible to provided it to a Costumer as a
     * gift, or a Costumer who owns it will be able to user it
     * for a Purchase.
     * @param valoreSconto the discount value of this Coupon.
     * @param dataScadenza expiration date of this Coupon.
     */
    private Coupon(int valoreSconto, Date dataScadenza) {
        idCoupon++;
        this.setValoreSconto(valoreSconto);
        this.setDataScadenza(dataScadenza);
    }

    public static int getIdCoupon() {
        return idCoupon;
    }

    public int getValoreSconto() {
        return valoreSconto;
    }

    public void setValoreSconto(int valoreSconto) {
        if(valoreSconto < 5)
            throw new IllegalArgumentException("Illegal discount value for the Coupon.");
        this.valoreSconto = valoreSconto;
    }

    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    /**
     * Method that set the actiovation Date about this Coupon.
     * @param dataAttivazione the activation Date.
     * @throws NullPointerException if the dataAttivazione is null.
     */
    public void setDataAttivazione(Date dataAttivazione){
        Objects.requireNonNull(dataAttivazione);
        this.dataAttivazione = dataAttivazione;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Method that set the expiration date about this Coupon.
     * @param dataScadenza the expiration date.
     * @throws NullPointerException if the dataScadenza is null.
     */
    public void setDataScadenza(Date dataScadenza) {
        Objects.requireNonNull(dataScadenza);
        this.dataScadenza = dataScadenza;
    }
}
