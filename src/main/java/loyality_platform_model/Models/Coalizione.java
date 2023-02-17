package loyality_platform_model.Models;

import loyality_platform_model.DBMS.DBMS;

import java.util.*;

public class Coalizione {

    /**
     * This attribute represents the DataBase.
     */


    /**
     * This attribute represents a map where the
     * Key is a Loyalty Program, and the Value is a
     * Set of Customers who are enrolled in a particular loyalty program.
     */
    private Map<ProgrammaFedelta, Set<Cliente>> clientiIscritti;

    /**
     * This attribute represents a map where the
     * Key is a Loyalty Program, and the value is a
     * Set of Companies that are part of a specific loyalty program,
     * thus creating that communication called Coalition.
     */
    private Map<ProgrammaFedelta, Set<Azienda>> aziendePerProgramma;

    /**
     * Constructor who creates an object of type Coalition
     */
    public Coalizione() {
        this.clientiIscritti = new HashMap<>();
        this.aziendePerProgramma = new HashMap<>();
    }

    public Map<ProgrammaFedelta, Set<Cliente>> getClientiIscritti() {
        return this.clientiIscritti;
    }

    public Map<ProgrammaFedelta, Set<Azienda>> getAziendePerProgramma() {
        return this.aziendePerProgramma;
    }

    /**
     * This method allows you to take all the customers
     * registered in a certain Loyalty Program.
     * @param idProgramma the id for Loyalty Program.
     * @return a list of Customers registered in a certain Loyalty Program.
     */
    public Set<Cliente> getClientiIscrittiProgramma(int idProgramma) {
        if (idProgramma <= 0)
            throw new IllegalArgumentException("Invalid id for the Loyalty Program.");
        for (ProgrammaFedelta programmaFedelta : this.clientiIscritti.keySet()) {
            if (programmaFedelta.getIdProgramma() == idProgramma) {
                return this.clientiIscritti.get(programmaFedelta);
            }
        }
        return null;
    }

    /**
     * This method allows you to take all the Customers
     * who are registered in an active Loyalty Program of a
     * specific Company, if it exists.
     * @param idAzienda the id for the Company.
     * @param idProgramma the id for the Loyalty Program.
     * @return a list of Customers enrolled in a certain Loyalty Program
     * active in a Company.
     */
    public Set<Cliente> getClientiIscrittiAProgrammaAzienda(int idAzienda, int idProgramma) {
        Set<Cliente> clientiIscritti = new HashSet<>();
        if (idAzienda <= 0 || idProgramma <= 0)
            throw new IllegalArgumentException("Invalid id for the Company or the Loyalty Program.");
        for (ProgrammaFedelta programmaFedelta : this.aziendePerProgramma.keySet()) {
            if (programmaFedelta.getIdProgramma() == idProgramma) {
                for (Azienda azienda : this.aziendePerProgramma.get(programmaFedelta)) {
                    if (azienda.getIdAzienda() == idAzienda) {
                        clientiIscritti.addAll(this.clientiIscritti.get(programmaFedelta));
                    }
                }
            }
        }
        return clientiIscritti;
    }

    /**
     * This method allows you to take all the Companies
     * that are registered in a specific Loyalty Program.
     * @param idProgramma the id for the Loyalty Program to get all Company.
     * @return all Companies that have that specific Loyalty Program active.
     */
    public Set<Azienda> getAziendeIscritteProgramma(int idProgramma) {
        for (ProgrammaFedelta programmaFedelta : this.aziendePerProgramma.keySet()) {
            if (programmaFedelta.getIdProgramma() == idProgramma) {
                return this.aziendePerProgramma.get(programmaFedelta);
            }
        }
        return null;
    }

    public boolean addClienteCoalizione(ProgrammaFedelta programmaFedelta, Cliente cliente){
        if(this.clientiIscritti == null || this.clientiIscritti.keySet().isEmpty()){
            Set<Cliente> clienti = new HashSet<>();
            clienti.add(cliente);
            assert  this.clientiIscritti != null;
            this.clientiIscritti.put(programmaFedelta, clienti);
            return true;
        }else if(!this.clientiIscritti.containsKey(programmaFedelta)){
            Set<Cliente> clienti = new HashSet<>();
            clienti.add(cliente);
            this.clientiIscritti.put(programmaFedelta, clienti);
            return true;
        }else if(!this.clientiIscritti.get(programmaFedelta).isEmpty()){
            this.clientiIscritti.get(programmaFedelta).add(cliente);
            return true;
        }
        return false;
    }

    public boolean addAziendaCoalizione(ProgrammaFedelta programmaFedelta, Azienda azienda){
        if(this.aziendePerProgramma == null || this.aziendePerProgramma.keySet().isEmpty()){
            Set<Azienda> aziende = new HashSet<>();
            aziende.add(azienda);
            assert this.aziendePerProgramma != null;
            this.aziendePerProgramma.put(programmaFedelta, aziende);
            return true;
        }else if(!this.aziendePerProgramma.containsKey(programmaFedelta)){
            Set<Azienda> aziende = new HashSet<>();
            aziende.add(azienda);
            this.aziendePerProgramma.put(programmaFedelta, aziende);
            return true;
        }else if (!this.aziendePerProgramma.get(programmaFedelta).isEmpty()){
            this.aziendePerProgramma.get(programmaFedelta).add(azienda);
            return true;
        }
        return false;
    }

    /**
     * This method is called when the Coalition
     * in general needs to be updated, so when the values within the map
     * regarding Customers-Loyalty Program.
     *
     * @param clientiIscritti the updated map.
     */
    public void updateClientiIscritti(Map<ProgrammaFedelta, Set<Cliente>> clientiIscritti) {
        Objects.requireNonNull(clientiIscritti);
        this.clientiIscritti = clientiIscritti;
    }

    /**
     * This method is called when the Coalition
     * in general needs to be updated, so when the values within the map
     * regarding Company-Loyalty Program.
     *
     * @param aziendePerProgramma the updated map.
     */
    public void updateAziendePerProgramma(Map<ProgrammaFedelta, Set<Azienda>> aziendePerProgramma) {
        Objects.requireNonNull(aziendePerProgramma);
        this.aziendePerProgramma = aziendePerProgramma;
    }

}
