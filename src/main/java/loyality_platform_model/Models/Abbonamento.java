package loyality_platform_model.Models;

import java.util.Date;

/**
 * Class that represents a subscription that can be
 * purchased by companies that want to join the platform.
 */
public class Abbonamento {
    /**
     * attribute representing the id of the subscription
     */
    private static int contatoreAbbonamento=0;
    private int idAbbonamento;
    /**
     * attribute of type Date,
     * indicating the start date of the subscription
     */
    private final Date dataInizio;
    /**
     * attribute of type Date,
     * indicating the end date of the subscription
     */
    private Date dataFine;
    /**
     * attribute indicating the price of the subscription
     */
    private double prezzo;
    /**
     * constructor that instantiates a new object for the Subscription class,
     * where to be created, it needs the following parameters.
     * @param dataInizio day on which the subscription starts
     * @param dataFine day on which the subscription end
     * @param prezzo price of subscription
     */
    public Abbonamento(Date dataInizio, Date dataFine, double prezzo) {
        this.idAbbonamento=++contatoreAbbonamento;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.setPrezzo(prezzo);
    }

    /**
     * This method returns the subscription id.
     * @return the subscription id.
     */
    public int getIdAbbonamento() {
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
     * This method edit the attribute of the Date attribute
     * @param df new date type
     */
    public void setDataFine(Date df){
        this.dataFine=df;
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

    @Override
    public String toString() {
        return "Abbonamento{" +
                "dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", prezzo=" + prezzo +
                '}';
    }
}
