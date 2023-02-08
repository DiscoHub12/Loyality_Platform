package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.Cliente;
import loyality_platform_model.Models.Dipendente;
import loyality_platform_model.Models.ProgrammaFedelta;
import java.util.Objects;
import java.util.Set;

/**
 * IMPLEMENTED BY : Sofia Scattolini.
 *
 *  Classes that represent capable a company manager
 *  to retrieve all available information.
 */
public class HandlerAzienda {

    private final DBMS dbms;

    private final Azienda azienda;

    private static HandlerAzienda instance;

    public HandlerAzienda(Azienda azienda) {
        Objects.requireNonNull(azienda);
        this.dbms = DBMS.getInstance();
        this.azienda = azienda;
    }

    public static HandlerAzienda getInstance() {
        if(instance == null){
            instance = new HandlerAzienda(getInstance().azienda);
        }
        return instance;
    }

    /**
     * This method returns the loyalty space of the company in question.
     * @return the loyalty space of the company.
     */
    public String getSpazioFedelta(){
       return this.getAzienda().getSpazioFedelta().toString();
    }

    /**
     * This method returns the set of employees who are part of the company.
     * @return the set of employees.
     */
    public Set<Dipendente> getUtenti() {
        return this.getAzienda().getDipendenti();
    }

    /**
     * This method returns the details of a particular employee.
     * @param dipendente considered employee.
     * @return the details of a particular employee.
     */
    public String getDetailsUtente(Dipendente dipendente){
        Objects.requireNonNull(dipendente);
        if(this.getAzienda().getDipendenti().contains(dipendente))
            return dipendente.getDetails();
        return "Dipendente non trovato";
    }

    /**
     * This method returns the loyalty programs active in the company.
     * @return the loyalty programs active in the company.
     */
    public Set<ProgrammaFedelta> getProgrammiAttivi(){
        return this.getAzienda().getProgrammiAttivi();
    }

    /**
     * This method returns the details of a certain loyalty program.
     * @param programmaFedelta loyalty program considered.
     * @return the details of a certain loyalty program.
     */
    public String getDetailsProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(programmaFedelta);
        if(this.getAzienda().getProgrammiAttivi().contains(programmaFedelta)){
            return programmaFedelta.toString();
        }
        return "Programma fedeltà non trovato";
    }

    /**
     * This method returns the company's customers.
     * @return the company's customers.
     */
    public Set<Cliente> getClienti(){
        return this.getAzienda().getClienti();
    }

    /**
     * This method returns the details of a specific customer of the company.
     * @param cliente customer considered.
     * @return the details of a specific customer of the company.
     */
    public String getDetailsCliente(Cliente cliente){
        Objects.requireNonNull(cliente);
        if(this.getAzienda().getClienti().contains(cliente)){
            return HandlerCliente.getInstance(cliente).getDetailsCliente();
        }
        return "Cliente non trovato";
    }

    /**
     * This method returns the company.
     * @return the company.
     */
    public Azienda getAzienda(){
        return azienda;
    }

    /**
     * This method returns all company details.
     * @return all company details.
     */
    public String getDetailsAzienda() {
        return this.getAzienda().toString();
    }
}
