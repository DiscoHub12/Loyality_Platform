package loyality_platform_model.Models;

import java.util.Date;
import java.util.Objects;

/**
 * IMPLEMENTED BY : Alessio Giacché.
 */


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
     * This attribute rapresents the
     * Hour duration of this Visit.
     */
    private String ora;

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
     * @param ora   the Hour of this Visit.
     */
    public Visita(String luogo, Date data, String ora) {
        idVisita++;
        this.setData(data);
        this.setLuogo(luogo);
        this.setOra(ora);
        this.completata = false;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Objects.requireNonNull(data);
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        if (Objects.equals(luogo, ""))
            throw new IllegalArgumentException("Invalid Place");
        this.luogo = luogo;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        if (Objects.equals(ora, ""))
            throw new IllegalArgumentException("Invalid hour.");
        this.ora = ora;
    }

    public boolean isCompletata() {
        return completata;
    }

    public void setCompletata(boolean completata) {
        this.completata = completata;
    }

    public Reminder creaReminder(String nomeVisita, Date data) {
        Objects.requireNonNull(data);
        if (Objects.equals(nomeVisita, ""))
            throw new IllegalArgumentException("Invalid name for the Reminder.");
        return new Reminder(nomeVisita, data);
    }

    public String toString() {
        return "\n\t VISIT DETAILS : " +
                "\n Place : " + this.luogo +
                "\n Date : " + this.data.toString() +
                "\n Hour : " + this.ora +
                "\n Complete : " + this.completata;
    }
}
