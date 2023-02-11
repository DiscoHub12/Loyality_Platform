package loyality_platform_model.Models;

import loyality_platform_model.DBMS.DBMS;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Coalizione {

    /**
     * This attribute represents the DataBase.
     */
    private final DBMS dbms;

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
        this.dbms = DBMS.getInstance();
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
