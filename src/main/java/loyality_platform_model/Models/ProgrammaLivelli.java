package loyality_platform_model.Models;

import java.util.Map;
import java.util.Objects;

/**
 *  Class representing one of many loyalty programs
 *  available within the platform.
 *  In particular, this program follows the rule of levels, ie
 *  the more a customer spends, the more points he will have, the more he will level up.
 *  Every time a customer makes a purchase, he gets a certain number of points,
 *  depending on purchase.
 *  Thanks to these levels the Customer will be able to choose the rewards
 *  present in the Awards Catalogue.
 */
public class ProgrammaLivelli implements ProgrammaFedelta {

    private final int idProgramma;

    private static int contatorePL = 0;

    private String nome;

    private String dataAttivazione;

    private int massimoLivelli, livelloVip;

    private Map<Integer, Integer> policyLivelli;

    private int puntiSpesa;

    private double importoSpesa;

    private CatalogoPremi catalogoPremi;

    private final Tipo tipoProgramma = Tipo.PROGRAMMALIVELLI;

    public ProgrammaLivelli(String nome, String dataAttivazione, int massimoLivelli, int livelloVip, Map<Integer, Integer> policy, int puntiSpesa, double importoSpesa) {
        this.idProgramma = contatorePL++;
        this.setNome(nome);
        this.setDataAttivazione(dataAttivazione);
        this.setMassimoLivelli(massimoLivelli);
        this.setLivelloVip(livelloVip);
        this.setPuntiSpesa(puntiSpesa);
        this.setImportoSpesa(importoSpesa);
        this.policyLivelli = policy ;
        this.catalogoPremi= null;
    }

    /**
     * This method returns the program id
     * @return the program id.
     */
    @Override
    public int getIdProgramma() {
        return idProgramma;
    }

    /**
     * This method return the program name.
     * @return the program name.
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * This method changes the name of the program.
     * @param nome new program name.
     */
    @Override
    public void setNome(String nome) {
        if (Objects.equals(nome, ""))
            throw new IllegalArgumentException("Name not valid.");
        this.nome = nome;
    }

    /**
     * This method sets the activation date of the program.
     * @param dataAttivazione the activation date of the program.
     */
    @Override
    public void setDataAttivazione(String dataAttivazione) {
        if (Objects.equals(dataAttivazione, ""))
            throw new IllegalArgumentException("Name not valid.");
        this.dataAttivazione = dataAttivazione;
    }

    /**
     * This method returns the activation date of the program.
     * @return the activation date of the program.
     */
    @Override
    public String getDataAttivazione() {
        return dataAttivazione;
    }

    /**
     * This method returns the type of the program.
     * @return the type of the program.
     */
    @Override
    public Tipo getTipoProgramma() {
        return tipoProgramma;
    }

    /**
     * This method return null because this is a levels program.
     * @return null.
     */
    @Override
    public ProgrammaPunti getProgrammaPunti() {
        return null;
    }

    /**
     * This method returns this program.
     * @return this program.
     */
    @Override
    public ProgrammaLivelli getProgrammaLivelli() {
        return this;
    }


    /**
     * This method returns the maximum number of levels.
     * @return the maximum number of levels.
     */
    public int getMassimoLivelli() {
        return massimoLivelli;
    }

    /**
     * This method sets the maximum number of levels.
     * @param massimoLivelli maximum number of levels.
     */
    public void setMassimoLivelli(int massimoLivelli) {
        if(massimoLivelli < 1)
            throw new IllegalArgumentException("Invalid number for level number.");
        this.massimoLivelli = massimoLivelli;
    }

    /**
     * This method returns the level number to become vip.
     * @return the level number to become vip.
     */
    public int getLivelloVip() {
        return livelloVip;
    }

    /**
     * This method sets the level number to become vip.
     * @param livelloVip the level number to become vip.
     */
    public void setLivelloVip(int livelloVip) {
        if (livelloVip < 1 || livelloVip > this.getMassimoLivelli())
            throw new IllegalArgumentException("Illegal max number of level.");
        this.livelloVip = livelloVip;
    }

    /**
     * This method returns the number of points to get for each level.
     * @return the number of points to get for each level.
     */
    public Map<Integer, Integer> getPolicyLivelli() {
        return policyLivelli;
    }

    /**
     * This method sets the level policy.
     * @param policyLivelli new policy.
     */
    public void setPolicyLivelli(Map<Integer, Integer> policyLivelli) {
        this.policyLivelli = policyLivelli;
    }

    /**
     * This method adds a new layer and associates its points to it.
     * @param punti points associated with that level.
     */
    public void addLivello(int punti){
        int appoggio = 1;
        if(punti < 1)
            throw new IllegalArgumentException("Invalid points.");
        appoggio += this.getPolicyLivelli().size();
        if(appoggio > this.getMassimoLivelli())
            throw new IllegalArgumentException("Invalid level.");
        this.getPolicyLivelli().put(appoggio, punti);
    }

    /**
     * This method changes the number of points for a given level.
     * @param livello level to change.
     * @param punti number of points to be awarded at that level.
     */
    public void updatePuntiLivello(int livello, int punti){
        if(livello < 0 || livello > this.getMassimoLivelli())
            throw new IllegalArgumentException("Invalid level.");
        if(punti < 1)
            throw new IllegalArgumentException("Invalid points.");
        this.getPolicyLivelli().replace(livello, punti);
    }

    /**
     * This method removes me one layer.
     * @param livello level to remove.
     */
    public void removeLivello(int livello){
        if(livello < 0 || livello > this.getMassimoLivelli())
            throw new IllegalArgumentException("Invalid level.");
        this.getPolicyLivelli().remove(livello);
    }

    /**
     * This method returns the number of points to be associated with the amount spent.
     * @return the number of points.
     */
    public int getPuntiSpesa() {
        return puntiSpesa;
    }

    /**
     * This method sets the number of points to be associated with the amount spent.
     * @param puntiSpesa the number of points.
     */
    public void setPuntiSpesa(int puntiSpesa) {
        if (puntiSpesa < 1)
            throw new IllegalArgumentException("Illegal number of points.");
        this.puntiSpesa = puntiSpesa;
    }

    /**
     * This method returns the amount to be spent in order for the points to be awarded.
     * @return  the amount to be spent.
     */
    public double getImportoSpesa() {
        return importoSpesa;
    }

    /**
     * This method sets the amount to be spent in order for points to be awarded.
     * @param importoSpesa the amount to be spent.
     */
    public void setImportoSpesa(double importoSpesa) {
        if (importoSpesa < 1)
            throw new IllegalArgumentException("Illegal number of imports.");
        this.importoSpesa = importoSpesa;
    }

    /**
     * This method returns the rewards catalog of this loyalty program.
     * @return the rewards catalog of this loyalty program.
     */
    @Override
    public CatalogoPremi getCatalogoPremi() {
        return catalogoPremi;
    }

    /**
     * This method sets up the rewards catalog of this loyalty program.
     * @param catalogoPremi catalog of this loyalty program.
     */
    @Override
    public void setCatalogoPremi(CatalogoPremi catalogoPremi) {
        Objects.requireNonNull(catalogoPremi);
        this.catalogoPremi = catalogoPremi;
    }

    /**
     * Equals method of the program class, simply
     * compare if the passed object is equivalent to this program,
     * by checking the id, and the name.
     * Returns true if the object is equal, false otherwise.
     *
     * @param o the Object to compare.
     * @return true if is equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammaLivelli that = (ProgrammaLivelli) o;
        return massimoLivelli == that.massimoLivelli &&
                livelloVip == that.livelloVip &&
                puntiSpesa == that.puntiSpesa &&
                Double.compare(that.importoSpesa, importoSpesa) == 0 &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(dataAttivazione, that.dataAttivazione) &&
                Objects.equals(policyLivelli, that.policyLivelli) &&
                tipoProgramma == that.tipoProgramma &&
                Objects.equals(catalogoPremi, that.catalogoPremi);
    }


    @Override
    public String toString() {
        return "\t-ProgrammaLivelli-" +
                "\nnome: " + nome +
                "\ndataAttivazione: " + dataAttivazione +
                "\nmassimoLivelli: " + massimoLivelli +
                "\nlivelloVip: " + livelloVip +
                "\npolicyLivelli: " + policyLivelli +
                "\npuntiSpesa: " + puntiSpesa +
                "\nimportoSpesa: " + importoSpesa +
                "\ntipoProgramma: " + tipoProgramma+
                "\nCatalogo Premi: " + toStringCatalogo();
    }

    private String toStringCatalogo(){
        if(catalogoPremi != null){
            return catalogoPremi.toString();
        }
        return null;
    }
}
