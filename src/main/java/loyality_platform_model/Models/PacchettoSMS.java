package loyality_platform_model.Models;
/**
 * IMPLEMENTED BY : Fabio Evangelista.
 */
/**
 * Class representing an SMS packet.
 * It contains an id, the price and the messages available inside
 */
public class PacchettoSMS {
    /**
     *attribute indicating the package id
     */
    private static int id;
    /**
     *attribute indicating the price of the package
     */
    private double prezzo;
    /**
     *attribute indicating the messages available within a package
     */
    private int messaggiDisponibili;
    /**
     *Constructor who creates an SMS package.
     * @param prezzo the price of a package
     * @param messaggiDisponibili Messages you can send are available
     */

    public PacchettoSMS(double prezzo,int messaggiDisponibili){
        id++;
        this.prezzo=prezzo;
        this.messaggiDisponibili=messaggiDisponibili;
    }

    public int getId(){
        return id;
    }
    public double getPrezzo(){
        return prezzo;
    }
    public void setPrezzo(double p){
        this.prezzo=p;
    }
    public void setMessaggiDisponibili(int n){
        this.messaggiDisponibili=n;
    }

    public int getMessaggiDisponibili() {
        return messaggiDisponibili;
    }
}

