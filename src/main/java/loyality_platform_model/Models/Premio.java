package loyality_platform_model.Models;

public class Premio {

    private static int idPremio;

    private String nome;

    public Premio(String nome){
        this.nome = nome;
    }

    public static int getIdPremio() {
        return idPremio;
    }

    public static void setIdPremio(int idPremio) {
        Premio.idPremio = idPremio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
