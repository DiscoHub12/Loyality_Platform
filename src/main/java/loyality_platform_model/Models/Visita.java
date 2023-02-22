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
    private static int contatoreVisita=0;
    /**
     * This attribute rapresents the unique
     * id of this Visit.
     */
    private int idVisita;

    /**
     * This attribute rapresents the
     * Date of this Visit.
     */
    private String data;

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
    public Visita(String luogo, String data) {
        this.idVisita=++contatoreVisita;
        this.data=data;
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
    public String getData() {
        return this.data;
    }
    /**
     * method that update the date of the visit
     */
    public void setData(String data) {
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
     * @return <code>false</code> if it's not completed, <code>true</code> otherwise.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return completata == visita.completata && Objects.equals(data, visita.data) && Objects.equals(luogo, visita.luogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVisita, data, luogo, completata);
    }

    public String toString() {
        return "\t Visit details : " +
                "\n Id visita : " + this.idVisita +
                "\n Place : " + this.luogo +
                "\n Date : " + this.data+
                "\n Complete : " + this.completata;
    }
}
