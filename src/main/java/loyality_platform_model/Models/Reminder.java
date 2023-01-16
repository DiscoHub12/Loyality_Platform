package loyality_platform_model.Models;

import loyality_platform_model.Handler.HandlerMessaggi;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class that rapresents the concept of a
 * Reminder, taken as notifications. In fact,
 * this Reminder sends a Notification/SMS to a Client
 * or a list of Client for a specific Activity.
 */
public class Reminder {

    /**
     * This attribute rapresents the
     * name of the Visit.
     */
    private String nomeVisita;

    /**
     * This attribute rapresents
     * the Data of this Reminder.
     */
    private Date data;

    /**
     * Constructor that allow to create a Reminder,
     * passing basic information.
     *
     * @param nomeVisita the name of the Visit.
     * @param data       the Data of this Reminder.
     * @throws NullPointerException if the Data is null.
     */
    public Reminder(String nomeVisita, Date data) {
        Objects.requireNonNull(data);
        this.setNomeVisita(nomeVisita);
        this.setData(data);
    }

    public String getNomeVisita() {
        return nomeVisita;
    }

    public void setNomeVisita(String nomeVisita) {
        if (Objects.equals(nomeVisita, ""))
            throw new IllegalArgumentException("Incorrect visit name.");
        this.nomeVisita = nomeVisita;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        Objects.requireNonNull(data);
        if (data == this.data)
            throw new IllegalArgumentException("Date equal to the one already present");
        this.data = data;
    }

    public SMS creaMessaggio(HandlerMessaggi gestoreMessaggi) {
        //Todo implementare, manca HandlerMessaggi.
        return null;
    }

    public void inviaNotifica(List<Cliente> clienti, HandlerMessaggi gestoreMessaggi) {
        Objects.requireNonNull(clienti);
        Objects.requireNonNull(gestoreMessaggi);
        //Todo implementare, manca HandlerMessaggi.
    }
}
