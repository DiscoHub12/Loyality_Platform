package loyality_platform_model.Models;
import java.util.ArrayList;
import java.util.List;
/**
 the class represents the card of a specific customer.
 A card is identified by an id and its owner.
 Loyalty programs to which the customer is a member are also marked.
 Inside we can subscribe to a new loyalty program or remove an old one and add or remove points.
 */

public class Tessera {
    /**
     * This attribute rapresents the
     * unique id for this card.
     */
    private static int id;
    /**
     * This attribute indicates the owner of the card
     */
    private Cliente cliente;
    /**
     * This attribute indicates loyalty program list
     */
    private final List<ProgrammaFedelta> programmiFedelta;
    /**
     * constructor allows you to create a new card within the platform
     * @param cliente card owner.
     */
    public Tessera(Cliente cliente){
        id++;
        this.cliente=cliente;
        this.programmiFedelta=new ArrayList<>();
    }
    public int getId(){
        return this.id;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public List<ProgrammaFedelta> getProgrammiFedelta() {
        return this.programmiFedelta;
    }
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedeltà) {
        this.programmiFedelta.add(programmaFedeltà);
    }

    public void removeProgrammmaFedelta(ProgrammaFedelta programmaFedeltà) {
        this.programmiFedelta.remove(programmaFedeltà);
    }
    //guardare classe programmi fedelta
    public void addPunti(int p,ProgrammaFedelta programmaFedeltà) {
        //TODO implementare
    }
    //guardare classe programmi fedelta
    public void removePunti(int p,ProgrammaFedelta programmaFedeltà) {
        //TODO implementare
    }
    public String toString(){
        return "Tessera{" +
                "id=" + id +
                ", cliente=" + this.cliente +
                ", lista di programmi fedelta=" + this.programmiFedelta + //da vedere
                '}';
    }
}
