package loyality_platform_model.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class that rapresent the concept of Reward Catalog.
 * This Catalog is owned by one of the many Loyality Programs,
 * and can be modified and biult directly by the Manager who
 * will choose to implement a new Loyalty Program within the platform.
 * It is composed of a series of information such as the prizes inside it.
 */
public class CatalogoPremi {

    /**
     * This attribute rapresents the
     * unique id of this Reward Catalogue.
     */
    private static int idCatalogoPremi;

    /**
     * This attribute rapresents the list
     * of prizes rapresented by a HashMap where the
     * key is the Reward, and the value is a the number
     * of points to reach that particular Rewards.
     */
    private final Map<Premio, Integer> premi;

    /**
     * This attribute rapresents
     */
    private String nomeProgramma;

    /**
     * Constructor that allow to create a new Reward
     * Catalog that will be used within a specific Loyality
     * Program, and which be visible to a Costumer to
     * redem any reward.
     *
     * @param nomeProgramma the name of this Reward Catalog.
     */
    public CatalogoPremi(String nomeProgramma){
        idCatalogoPremi++;
        this.setNomeProgramma(nomeProgramma);
        this.premi = new HashMap<>();
    }

    public int getId(){
        return idCatalogoPremi;
    }

    public String getNomeProgramma(){
        return this.nomeProgramma;
    }

    public void setNomeProgramma(String nomeProgramma){
        if(Objects.equals(nomeProgramma, ""))
            throw new IllegalArgumentException("Illegal name for Program");
        this.nomeProgramma = nomeProgramma;
    }

    public Map<Premio, Integer> getPremi(){
        return this.premi;
    }

    public void aggiungiPremio(Premio premio, int numeroPuntiDaRaggiungere){
        Objects.requireNonNull(premio);
        if(numeroPuntiDaRaggiungere <= 0)
            throw new IllegalArgumentException("Invalid number for receive Gift.");
        if(premi.containsKey(premio))
            throw new IllegalArgumentException("Gift already present.");
        this.premi.put(premio, numeroPuntiDaRaggiungere);
    }

    public void rimuoviPremio(Premio premio){
        Objects.requireNonNull(premio);
        if(!this.premi.containsKey(premio))
            throw new IllegalArgumentException("Gift not exist.");
        this.premi.remove(premio);
    }

    private String toStringPremi(){
        StringBuilder tmp = new StringBuilder();
        Set<Premio> nomePremi = premi.keySet();
        int i = 1;
        for(Premio premio : nomePremi){
            tmp.append("\n Premio numero ").append(i).append(" : ").append(premio.getNome());
            i++;
        }
        return tmp.toString();
    }
}
