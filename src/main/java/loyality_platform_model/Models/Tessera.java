package loyality_platform_model.Models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * This attribute indicates loyalty program list
     */
    private final Set<ProgrammaFedelta> programmiFedelta;
    /**
     * constructor allows you to create a new card within the platform
     */
    public Tessera(){
        id++;
        this.programmiFedelta=new HashSet<>();
    }
    public int getId(){
        return id;
    }
    public Set<ProgrammaFedelta> getProgrammiFedelta() {
        return this.programmiFedelta;
    }
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta) {
        this.programmiFedelta.add(programmaFedelta);
    }

    public void removeProgrammmaFedelta(ProgrammaFedelta programmaFedelta) {
        this.programmiFedelta.remove(programmaFedelta);
    }
    //guardare classe programmi fedelta
    public void addPunti(int p,ProgrammaFedelta programmaFedelta) {
        //TODO implementare
    }
    //guardare classe programmi fedelta
    public void removePunti(int p,ProgrammaFedelta programmaFedelta) {
        //TODO implementare
    }
    public String toString(){
        return "Tessera{" +
                "id=" + id +
                ", lista di programmi fedelta=" + toStringProgrammiFedelta() +
                '}';
    }

    private String toStringProgrammiFedelta(){
        StringBuilder stringBuilder = new StringBuilder();
        for(ProgrammaFedelta programmaFedelta : this.getProgrammiFedelta()){
            stringBuilder.append("\n-").append(programmaFedelta);
        }
        return stringBuilder.toString();
    }
}
