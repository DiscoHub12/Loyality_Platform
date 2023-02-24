package loyality_platform_model.Models;

import java.util.*;

public class Coalizione {

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
     * @return a set of Customers registered in a certain Loyalty Program.
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
     * This method allows you to take all the costumers
     * at a certain Company.
     * @param idAzienda the of Company.
     * @return a set of Customers.
     */
    public Set<Cliente> getClientiAzienda(int idAzienda){
        Set<Cliente> clienti = new HashSet<>();
        if(idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for(ProgrammaFedelta programmaFedelta : this.aziendePerProgramma.keySet()){
            for(Azienda azienda : this.aziendePerProgramma.get(programmaFedelta)){
                if(azienda.getIdAzienda() == idAzienda){
                    clienti.addAll(this.clientiIscritti.get(programmaFedelta));
                }
            }
        }
        return clienti;
    }

    /**
     * This method allows you to take all the Customers
     * who are registered in an active Loyalty Program of a
     * specific Company, if it exists.
     * @param idAzienda the id for the Company.
     * @param idProgramma the id for the Loyalty Program.
     * @return a set of Customers enrolled in a certain Loyalty Program
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
        if(!this.clientiIscritti.containsKey(programmaFedelta)){
            Set<Cliente> clienti = new HashSet<>();
            clienti.add(cliente);
            this.clientiIscritti.put(programmaFedelta, clienti);
            return true;
        }else {
            Set<Cliente> clientiIscritti = this.clientiIscritti.get(programmaFedelta);
            if(clientiIscritti.contains(cliente))
                return false;
            clientiIscritti.add(cliente);
            this.clientiIscritti.put(programmaFedelta, clientiIscritti);
            return true;
        }
    }

    public boolean addAziendaCoalizione(ProgrammaFedelta programmaFedelta, Azienda azienda){
        if(!this.aziendePerProgramma.containsKey(programmaFedelta)){
            Set<Azienda> azienda1 = new HashSet<>();
            azienda1.add(azienda);
            this.aziendePerProgramma.put(programmaFedelta, azienda1);
            return true;
        }else {
            Set<Azienda> aziendeIscritte = this.aziendePerProgramma.get(programmaFedelta);
            if(aziendeIscritte.contains(azienda))
                return false;
            aziendeIscritte.add(azienda);
            this.aziendePerProgramma.put(programmaFedelta, aziendeIscritte);
            return true;
        }
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

    public boolean deleteClienteProgramma(int idCliente, int idProgramma){
        for(ProgrammaFedelta programmaFedelta : this.clientiIscritti.keySet()){
            if(programmaFedelta.getIdProgramma() == idProgramma){
                for(Cliente cliente : this.clientiIscritti.get(programmaFedelta)){
                    if(cliente.getIdCliente() == idCliente){
                        this.clientiIscritti.get(programmaFedelta).remove(cliente);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteAziendaProgramma(int idAzienda, int idProgramma){
        for(ProgrammaFedelta programmaFedelta : this.aziendePerProgramma.keySet()){
            if(programmaFedelta.getIdProgramma() == idProgramma){
                for(Azienda azienda : this.aziendePerProgramma.get(programmaFedelta)){
                    if(azienda.getIdAzienda() == idAzienda){
                        this.aziendePerProgramma.get(programmaFedelta).remove(azienda);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean addProgrammaFedelta(ProgrammaFedelta programmaFedelta){
        if(programmaFedelta == null)
            return false;
        this.aziendePerProgramma.put(programmaFedelta, new HashSet<>());
        this.clientiIscritti.put(programmaFedelta, new HashSet<>());
        return true;
    }

}
