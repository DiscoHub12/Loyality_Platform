package loyality_platform_model.Models;

/**
 * IMPLEMENTED BY : Alessio Giacché.
 */


/**
 * Class that rapresents the concept of Award.
 * A Reward can be redeemed by a particular Costumer (member)
 * if he reaches the required number of points.
 */
public class Premio {

    /**
     * This attribute rapresents the
     * unique id of this award
     */
    private static int idPremio;

    /**
     * This attribute rapresents the name
     * of this Award.
     */
    private String nome;

    /**
     * Constructor that allow to create an object
     * of type Premio.
     * @param nome the name of the Award.
     */
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
