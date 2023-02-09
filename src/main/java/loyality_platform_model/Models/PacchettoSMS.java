package loyality_platform_model.Models;
/**
 /**
 * Class representing an SMS packet.
 * It contains an id, the price and the messages available inside
 */
public class PacchettoSMS {
    /**
     * attribute indicating the package id
     */
    private static int idPacchettoSMS;
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
        idPacchettoSMS++;
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
        return "Pacchetto SMS{" +
                "id =" + this.idPacchettoSMS +
                ", prezzo =" + this.prezzo +
                ", numero dei messaggi =" + this.numeroMessaggi +
                '}';
    }
}

