package loyality_platform_model.Models;


import java.util.Objects;

/**
 * Class that rapresents the concept of Award.
 * A Reward can be redeemed by a particular Costumer (member)
 * if he reaches the required number of points.
 */
public class Premio {

    /**
     * This attribute represents the
     * unique id of this award.
     */
    private static int idPremio;

    /**
     * This attribute represents the name
     * of this Award.
     */
    private String nome;

    /**
     * This attributes, if a Reward is for a points program,
     * represent the number of points required to redeem
     * that reward.
     */
    private int punti;

    /**
     * This attributes, if a Reward is for a level program,
     * represent the number of level required to redeem
     * that reward.
     */
    private int livelli;


    /**
     * Constructor that allow to create an object
     * of type Premio.
     * The constructor also takes as input a boolean
     * variable which indicates whether to redeem this prize,
     * you must have so many points (number) or so many levels (number)
     *
     * @param nome     the name of the Award.
     * @param isPoints indicates whether the reward is for a tiered or points program
     * @param number   the number of points if points are needed to redeem the prize, otherwise levels
     */
    public Premio(String nome, boolean isPoints, int number) {
        this.nome = nome;
        if (isPoints) {
            this.setPunti(number);
        } else {
            this.setLivelli(number);
        }
    }

    public int getIdPremio() {
        return idPremio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPunti(int numeroPunti) {
        if (numeroPunti <= 0)
            throw new IllegalArgumentException("Illegal number of points.");
        this.punti = numeroPunti;
    }

    public int getPunti() {
        return this.punti;
    }

    public int getLivelli() {
        return this.livelli;
    }

    public void setLivelli(int numeroLivelli) {
        if (numeroLivelli <= 0)
            throw new IllegalArgumentException("Illegal number of level.");
        this.livelli = numeroLivelli;
    }

    /**
     * This method allows you to update the prize
     *
     * @param nome     the new name for this Prize.
     * @param isPoints indicates whether the reward is for a tiered or points program
     * @param number   the number of points if points are needed to redeem the prize, otherwise levels
     * @throws IllegalArgumentException if the Name of prize or number is not correct.
     */
    public void updatePremio(String nome, boolean isPoints, int number) {
        if (Objects.equals(nome, ""))
            throw new IllegalArgumentException("Illegal Name for prize.");
        if (number <= 0)
            throw new IllegalArgumentException("Illegal number.");
        this.setNome(nome);
        if (isPoints) {
            this.setPunti(number);
        } else this.setLivelli(number);
    }

    /**
     * Equals method of the Award, simply
     * compare if the passed object is equivalent to this award,
     * by checking the id, name.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof Premio tmp) {
            if (this.getIdPremio() == tmp.getIdPremio() && this.getNome() == tmp.getNome())
                return true;
        }
        return false;
    }

    public String toString() {
        return "\t-DETAILS PREMIO-" +
                "\nId Premio: " + idPremio +
                "\nNome Premio : " + this.nome +
                "\nPunti riscatto Premio : " + this.punti +
                "\nLivelli per premio : " + this.livelli;
    }


}