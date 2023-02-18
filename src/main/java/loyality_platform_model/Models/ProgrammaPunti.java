package loyality_platform_model.Models;

import java.util.*;

/**
 * Class that represents one of the many loyalty programs
 * available within the platform.
 * Especially, this program follows the points rule, i.e.
 * the more a customer spends, the more points he will have.
 * Every time a customer makes a purchase, he gets a certain number of points,
 * depending on the purchase.
 * Thanks to these points, the Customer will be able to choose prizes
 * that are present in the Prize Catalogue.
 */
public class ProgrammaPunti implements ProgrammaFedelta {

    private final int idProgrammaPunti;

    public static int contatore = 0;

    private String nome;

    private final String dataAttivazione;

    private boolean maxPunti;

    private int numeroPuntiMassimi;

    private int puntiVIP;

    private int puntiSpesa;

    private double importoSpesa;

    private CatalogoPremi catalogoPremi;

    private final Tipo tipoProgramma = Tipo.PROGRAMMAPUNTI;

    /**
     * Constructor who creates a Loyalty Program of the Points Program type.
     * This constructor is called if this Points Program does not have a
     * maximum number of points. It requests the fundamental attributes for creation,
     * and invokes set methods which contain controls within them.
     */
    public ProgrammaPunti(String nome, String dataAttivazione, int numeroPuntiMassimi, int puntiVIP, int puntiSpesa, double importoSpesa, CatalogoPremi catalogoPremi) {
        this.idProgrammaPunti = ++contatore;
        this.dataAttivazione = dataAttivazione;
        this.setNome(nome);
        this.setPuntiVIP(puntiVIP);
        this.setPuntiSpesa(puntiSpesa);
        this.setImportoSpesa(importoSpesa);
        this.setNumeroPuntiMassimi(numeroPuntiMassimi);
        this.setCatalogoPremi(catalogoPremi);

    }

    /**
     * This method returns the program id
     * @return the program id.
     */
    @Override
    public int getIdProgramma() {
        return idProgrammaPunti;
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
    public void setNome(String nome) {
        if (Objects.equals(nome, ""))
            throw new IllegalArgumentException("Name not valid.");
        this.nome = nome;
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
     * This method returns this program.
     * @return this program.
     */
    @Override
    public ProgrammaPunti getProgrammaPunti() {
        return this;
    }

    /**
     * This method return null because this is a points program.
     * @return null.
     */
    @Override
    public ProgrammaLivelli getProgrammaLivelli() {
        return null;
    }


    /**
     * This method returns <code>true</code> if the manager wants to put a maximum of points,
     * <code>false</code> otherwise.
     * @return <code>true</code> if the manager wants to put a maximum of points,
     * <code>false</code> otherwise.
     */
    public boolean isMaxPunti() {
        return maxPunti;
    }

    /**
     * This method sets the variable to tell whether or not there are a maximum number of points.
     * @param maxPunti <code>true</code> if there are a maximum number of points
     *                 <code>false</code> otherwise.
     */
    public void setMaxPunti(boolean maxPunti) {
        this.maxPunti = maxPunti;
    }

    /**
     * This method returns the maximum number of points.
     * @return the maximum number of points.
     */
    public int getNumeroPuntiMassimi() {
        return numeroPuntiMassimi;
    }

    /**
     * This method sets the maximum number of points.
     * @param numeroPuntiMassimi new maximum number of points.
     */
    public void setNumeroPuntiMassimi(int numeroPuntiMassimi) {
        if (numeroPuntiMassimi < 0)
            throw new IllegalArgumentException("Illegal max number of points.");
        this.setMaxPunti(numeroPuntiMassimi > 0);
        this.numeroPuntiMassimi = numeroPuntiMassimi;

    }

    /**
     * This method returns the number of points to become VIP.
     * @return the number of points to become VIP.
     */
    public int getPuntiVIP() {
        return puntiVIP;
    }

    /**
     * This method sets the number of points to become VIP.
     * @param puntiVIP new number of points to become VIP.
     */
    public void setPuntiVIP(int puntiVIP) {
        if (puntiVIP < 1)
            throw new IllegalArgumentException("Illegal number for VIP point.");
        if(this.isMaxPunti()){
            if(puntiVIP > this.getNumeroPuntiMassimi())
                throw new IllegalArgumentException("Illegal number of points");
        }
        this.puntiVIP = puntiVIP;
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
        if(this.isMaxPunti()){
            if(puntiSpesa > this.getNumeroPuntiMassimi())
                throw new IllegalArgumentException("Illegal number of points");
        }
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
        this.catalogoPremi = catalogoPremi;
    }

    /**
     * Equals method of the program class, simply
     * compare if the passed object is equivalent to this program,
     * by checking the id, and the name.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof ProgrammaPunti tmp) {
            return this.getIdProgramma() == tmp.getIdProgramma() && Objects.equals(this.getNome(), tmp.getNome());
        }
        return false;
    }

    @Override
    public String toString() {
        return "\t-DETAILS PROGRAMMA PUNTI-" +
                "\nId: " + idProgrammaPunti +
                "\nNome: " + nome +
                "\nData Attivazione: " + dataAttivazione +
                "\nMassimo Punti: " + maxPunti +
                "\nNumeroPuntiMassimi: " + numeroPuntiMassimi +
                "\nPunti VIP: " + puntiVIP +
                "\nPunti Spesa: " + puntiSpesa +
                "\nImporto Spesa: " + importoSpesa +
                "\nTipo Programma: " + tipoProgramma +
                "\nCatalogo Premi: " + toStringCatalogo();
    }

    private String toStringCatalogo(){
        if(catalogoPremi != null){
            return catalogoPremi.toString();
        }
        return null;
    }
}
