package loyality_platform_model.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 * Class representing a company within this platform.
 A company has its own loyalty space containing all its information,
 as loyalty programs and a set of customers and employees.
 */

public class Azienda {
    private static int idAzienda;

    private String nomeAzienda;

    private final SpazioFedelta spazioFedelta;

    private GestorePuntoVendita titolare;

    private final Set<Dipendente> dipendenti;

    private final Set<ProgrammaFedelta> programmiAttivi;

    private final Set<Cliente> clienti;

    private Abbonamento abbonamento;

    private PacchettoSMS pacchettoSms;

    public Azienda(GestorePuntoVendita titolare, SpazioFedelta spazioFedelta) {
        idAzienda++;
        this.setNomeAzienda(spazioFedelta.getNome());
        this.setTitolare(titolare);
        this.spazioFedelta = spazioFedelta;
        this.dipendenti = new HashSet<>();
        this.programmiAttivi = new HashSet<>();
        this.clienti = new HashSet<>();
    }

    /**
     * This method returns the company id
     * @return the company id.
     */
    public static int getId() {
        return idAzienda;
    }

    /**
     * This method returns me the company name.
     * @return the company name.
     */
    public String getNomeAzienda() {
        return this.nomeAzienda;
    }

    /**
     * This method sets the new company name.
     * @param nomeAzienda new name.
     */
    public void setNomeAzienda(String nomeAzienda) {
        if(Objects.equals(nomeAzienda, ""))
            throw new IllegalArgumentException("Illegal name for Agency");
        this.nomeAzienda = nomeAzienda;
    }

    /**
     * This method gives me back the loyalty space of the company.
     * @return the loyalty space of the company.
     */
    public SpazioFedelta getSpazioFedelta() {
        return this.spazioFedelta;
    }

    /**
     * This method returns me the owner of the company.
     * @return the owner of the company.
     */
    public GestorePuntoVendita getTitolare() {
        return this.titolare;
    }

    /**
     * This method sets up the new business owner.
     * @param titolare new business owner.
     */
    public void setTitolare(GestorePuntoVendita titolare) {
        Objects.requireNonNull(titolare);
        this.titolare = titolare;
    }

    /**
     * This method adds a new employee.
     * @param dipendente new employee.
     */
    public void addDipendente (Dipendente dipendente) {
        Objects.requireNonNull(dipendente);
        if(this.getDipendenti().contains(dipendente))
            throw new IllegalArgumentException("Employee already present.");
        this.dipendenti.add(dipendente);
    }

    /**
     * This method returns me a group of company employees.
     * @return a group of company employees.
     */
    public Set<Dipendente> getDipendenti() {
        return this.dipendenti;
    }

    /**
     * This method removes an employee.
     * @param dipendente employee to remove.
     */
    public void rimuoviDipendente(Dipendente dipendente){
        Objects.requireNonNull(dipendente);
        if(!this.getDipendenti().contains(dipendente))
            throw new IllegalArgumentException("Employee not exists.");
        this.dipendenti.remove(dipendente);
    }

    /**
     * This method adds a new loyalty program.
     * @param programmaFedelta new loyalty program.
     */
    public void addProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        if(this.getProgrammiAttivi().contains(programmaFedelta))
            throw new IllegalArgumentException("Loyalty program already present.");
        this.programmiAttivi.add(programmaFedelta);
    }

    /**
     * This method returns me a loyalty program of the company.
     * @return loyalty program of the company.
     */
    public Set<ProgrammaFedelta> getProgrammiAttivi() {
        return this.programmiAttivi;
    }

    /**
     * This method removes a loyalty program.
     * @param programmaFedelta loyalty program to be removed.
     */
    public void rimuoviProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        if(!this.getProgrammiAttivi().contains(programmaFedelta))
            throw new IllegalArgumentException("Loyalty program not exists.");
        this.programmiAttivi.remove(programmaFedelta);
    }

    /**
     * This method adds a new costumer.
     * @param cliente new costumer.
     */
    public void addCliente (Cliente cliente){
        Objects.requireNonNull(cliente);
        if(this.getClienti().contains(cliente))
            throw new IllegalArgumentException("Customer already present.");
        this.clienti.add(cliente);
    }

    /**
     * This method returns me a costumers of the company.
     * @return costumers of the company.
     */
    public Set<Cliente> getClienti() {
        return this.clienti;
    }

    /**
     * This method sets the subscription passed as a parameter.
     * @param abbonamento purchased subscription.
     */
    public void setAbbonamento(Abbonamento abbonamento){
        Objects.requireNonNull(abbonamento);
        this.abbonamento = abbonamento;
    }

    /**
     * This method gives me back the subscription purchased from the company.
     * @return the subscription purchased from the company.
     */
    public Abbonamento getAbbonamento() {
        return this.abbonamento;
    }

    /**
     * This method sets the one passed as a parameter as sms packet.
     * @param pacchettoSms sms package purchased.
     */
    public void setPacchettoSms (PacchettoSMS pacchettoSms){
        Objects.requireNonNull(pacchettoSms);
        this.pacchettoSms = pacchettoSms;
    }

    /**
     * This method gives me back the sms package purchased from the company.
     * @return the sms package purchased from the company.
     */
    public PacchettoSMS getPacchettoSms() {
        return this.pacchettoSms;
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "id=" + idAzienda +
                ", spazioFedelta=" + spazioFedelta +
                ", titolare=" + titolare +
                ", dipendenti=" + toStringDipendenti() +
                ", programmiAttivi=" + toStringProgrammi() +
                ", clienti=" + toStringClienti() +
                ", abbonamento=" + abbonamento +
                ", pacchettoSms=" + pacchettoSms +
                '}';
    }

    private String toStringDipendenti(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Dipendente dipendente : this.getDipendenti()) {
            stringBuilder.append("\n-").append(dipendente);
        }
        return stringBuilder.toString();
    }

    private String toStringProgrammi(){
        StringBuilder stringBuilder = new StringBuilder();
        for(ProgrammaFedelta programmaFedelta : this.getProgrammiAttivi()){
            stringBuilder.append("\n-").append(programmaFedelta);
        }
        return stringBuilder.toString();
    }

    private String toStringClienti(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Cliente cliente : this.getClienti()){
            stringBuilder.append("\n-").append(cliente);
        }
        return stringBuilder.toString();
    }
}
