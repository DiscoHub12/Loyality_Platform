package loyality_platform_model.Models;

import java.util.Date;
import java.util.Objects;

/**
 * Class that rapresent the concept of a Visit.
 * A Visit can be booked by a specific Costumer , or by
 * an Employee once the Costumer has been identified.
 * A normal visit, consists od a Date, the Place and
 * much other information.
 */
public class Visita {

    /**
     * This attribute rapresents the unique
     * id of this Visit.
     */
    private static int idVisita;

    /**
     * This attribute rapresents the
     * Date of this Visit.
     */
    private Date data;

    /**
     * This attribute rapresents the
     * Place of this Visit.
     */
    private String luogo;
    /**
     * This attribute rapresents the status
     * of the visit, so whther it was completed
     * or not.
     */
    private boolean completata;

    /**
     * Constructor that alllow to create a
     * Visit with certain information.
     *
     * @param luogo the Place of this Visit.
     * @param data  the Date of this Visit.
     */
    public Visita(String luogo, Date data) {
        idVisita++;
        this.setData(data);
        this.setLuogo(luogo);
        this.completata = false;
    }
    /**
     * method that returns the id of the visit
     */
    public int getIdVisita() {
        return idVisita;
    }
    /**
     * method that returns the date of the visit
     */
    public Date getData() {
        return data;
    }
    /**
     * method that update the date of the visit
     */
    public void setData(Date data) {
        Objects.requireNonNull(data);
        this.data = data;
    }
    /**
     * method that returns the place of the visit
     */
    public String getLuogo() {
        return luogo;
    }
    /**
     * method that update the place of the visit
     */
    public void setLuogo(String luogo) {
        if (Objects.equals(luogo, ""))
            throw new IllegalArgumentException("Invalid Place");
        this.luogo = luogo;
    }
    /**
     * method that returns whether the visit is completed or not
     * @return true if it's completed
     * @return false if it it's not completed
     */
    public boolean isCompletata() {
        return completata;
    }
    /**
     * method that set true or false on the boolean attribute
     * indicating whether a visit is completed or not
     */
    public void setCompletata(boolean completata) {
        this.completata = completata;
    }

    public String toString() {
        return "\n\t Visit details : " +
                "\n Place : " + this.luogo +
                "\n Date : " + this.data.toString() +
                "\n Complete : " + this.completata;
    }
}
