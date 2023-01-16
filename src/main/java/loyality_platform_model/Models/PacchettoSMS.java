package loyality_platform_model.Models;

public class PacchettoSMS {
    private static int id;
    private double prezzo;
    private int messaggiDisponibili;

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
}

