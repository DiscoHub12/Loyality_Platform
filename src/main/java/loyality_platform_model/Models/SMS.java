package loyality_platform_model.Models;

public class SMS {

    private String testo;

    private final String oraInvio;

    private boolean configurato;

    public SMS(String testo, String oraInvio, boolean configurato) {
        this.setTesto(testo);
        this.oraInvio = oraInvio;
        this.setConfigurato(configurato);
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getOraInvio() {
        return oraInvio;
    }

    public boolean isConfigurato() {
        return configurato;
    }

    public void setConfigurato(boolean configurato) {
        this.configurato = configurato;
    }

    public void setMessaggioConfigurato(ConfigurazioneSMS configurazioneSMS){
        this.setTesto(configurazioneSMS.getTestoConfigurato());
    }

    @Override
    public String toString() {
        return "SMS{" +
                "testo='" + testo + '\'' +
                ", oraInvio='" + oraInvio + '\'' +
                '}';
    }
}
