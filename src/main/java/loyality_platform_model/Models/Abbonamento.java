package loyality_platform_model.Models;

import java.util.Date;

/**
 * Class that represents a subscription that can be
 * purchased by companies that want to join the platform.
 */
public class Abbonamento {
    private static int idAbbonamento;

    private final Date dataInizio;

    private Date dataFine;

    private double prezzo;

    public Abbonamento(Date dataInizio, Date dataFine, double prezzo) {
        idAbbonamento++;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.setPrezzo(prezzo);
    }

    /**
     * This method returns the subscription id.
     * @return the subscription id.
     */
    public static int getId() {
        return idAbbonamento;
    }

    /**
     * This method returns me the start date of the subscription
     * @return the start date of the subscription
     */
    public Date getDataInizio() {
        return this.dataInizio;
    }

    /**
     * This method returns me the end date of the subscription
     * @return the end date of the subscription
     */
    public Date getDataFine() {
        return this.dataFine;
    }

    /**
     * This method returns me the subscription price.
     * @return the subscription price.
     */
    public double getPrezzo() {
        return this.prezzo;
    }

    /**
     * This method sets the subscription price to the one passed as a parameter
     * @param prezzo new price
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * This method renews my subscription end date.
     * @param dataFine new end date
     */
    public void rinnovaAbbonamento(Date dataFine){
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", prezzo=" + prezzo +
                '}';
    }
}
