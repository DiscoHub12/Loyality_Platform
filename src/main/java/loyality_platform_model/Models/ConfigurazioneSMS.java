package loyality_platform_model.Models;

import java.util.Date;

public class ConfigurazioneSMS {
    private String testoConfigurato;
    private Date ora;
    public ConfigurazioneSMS(String testoConfigurato, Date ora){
        this.testoConfigurato=testoConfigurato;
        this.ora=ora;
    }
    public String getTestoConfigurato(){
        return testoConfigurato;
    }
    public void setTestoConfigurato(String t){
        this.testoConfigurato=t;
    }
    public Date getOra(){
        return ora;
    }
    public void setOra(Date o){
        this.ora=o;
    }

    @Override
    public String toString() {
        return "ConfigurazioneSMS{" +
                "testoConfigurato='" + testoConfigurato + '\'' +
                ", ora=" + ora +
                '}';
    }
}

