package loyality_platform_model.Models;


/**
 /**
 * Class representing an SMS packet.
 * It contains an id, the price and the messages available inside
 */
public class PacchettoSMS {

    private static int contatorePacchettoSMS=0;

    /**
     * attribute indicating the package id
     */
    private final int idPacchettoSMS;

    /**
     * attribute indicating the price of the package
     */
    private double prezzo;

    /**
     * attribute indicating the messages available within a package
     */
    private int numeroMessaggi;

    /**
     * Constructor who creates an SMS package.
     *
     * @param prezzo         the price of a package
     * @param numeroMessaggi Messages you can send are available
     */

    public PacchettoSMS(double prezzo, int numeroMessaggi) {
        this.idPacchettoSMS=++contatorePacchettoSMS;
        this.prezzo = prezzo;
        this.numeroMessaggi = numeroMessaggi;
    }

    public int getId() {
        return this.idPacchettoSMS;
    }

    public double getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(double p) {
        this.prezzo = p;
    }

    public void setNumeroMessaggi(int n) {
        this.numeroMessaggi = n;
    }

    public int getNumeroMessaggi() {
        return this.numeroMessaggi;
    }


    public String toString() {
        return "\t-DETAILS PACCHETTO SMS-" +
                "\nId: " + this.idPacchettoSMS +
                "\nPrezzo: " + this.prezzo +
                "\nNumero di messaggi: " + this.numeroMessaggi;
    }
}

