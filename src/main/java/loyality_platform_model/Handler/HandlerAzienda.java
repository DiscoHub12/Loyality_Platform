package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

import java.util.Objects;
import java.util.Set;

/**
 * Classes that represent capable a company manager
 * to retrieve all available information.
 */
public class HandlerAzienda {

    /**
     * This attribute represents the DataBase.
     */
    private final DBMS dbms;


    /**
     * Constructor that create manager for Company (Azienda)
     */
    public HandlerAzienda() {
        this.dbms = DBMS.getInstance();
    }

    /**
     * This method returns the Owner of the Company (Azienda)
     * passed as parameters by id, if it is registered
     * on the Platform.
     *
     * @param idAzienda the id of Company.
     * @return the Owner (GestorePuntoVendita).
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public GestorePuntoVendita getTitolareAzienda(int idAzienda) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getTitolare();
            }
        }
        return null;
    }

    /**
     * This method returns the list of employees of the
     * company passed as parameters with the id, if it is
     * registered on the Platform.
     *
     * @param idAzienda the id of Company (Azienda).
     * @return a list of Employee of the Company (Azienda).
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public Set<Dipendente> getDipendentiAzienda(int idAzienda) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (!this.dbms.getDipendentiAzienda().get(azienda).isEmpty()) {
                    return this.dbms.getDipendentiAzienda().get(azienda);
                }
            }
        }
        return null;
    }

    /**
     *
     * @param idDipendente
     * @return
     */
    public Dipendente getDetailsDipendente(int idDipendente){
        //Todo implementare
        return null;
    }

    /**
     * This method allows you to create a new employee
     * with access to the platform of a specific Company.
     *
     * @param nome    the name for the Employee to add.
     * @param cognome the surname for the Employee to add.
     * @param email   the email for the Employee to add.
     * @throws IllegalArgumentException if the id Company, Name, Surname or Email for Employee is not valid.
     */
    public Dipendente creaDipendente(String nome, String cognome, String email, boolean restrizioni) {
        if (Objects.equals(nome, "") || Objects.equals(cognome, "") || Objects.equals(email, ""))
            throw new IllegalArgumentException("Invalid fields for name, surname or email.");
        return new Dipendente(nome, cognome, email, restrizioni);
    }

    /**
     * This method allows to add a new Emplooyee created
     * within a specific Company.
     *
     * @param idAzienda the Company (Azienda) id.
     * @param nome      the name for the Employee to add.
     * @param cognome   the Surname for the Employee to add.
     * @param email     the email for the Employee to add.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public boolean aggiungiDipendente(int idAzienda, String nome, String cognome, String email, boolean restrizioni) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                Dipendente created = creaDipendente(nome, cognome, email, restrizioni);
                for (Dipendente dipendente : this.dbms.getDipendentiAzienda().get(azienda)) {
                    if (!(Objects.equals(dipendente.getEmail(), created.getEmail()) && Objects.equals(dipendente.getNome(), created.getNome()))) {
                        this.dbms.addDipendente(azienda, created);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method allows you to change the information of a
     * particular Employee within Company.
     *
     * @param idAzienda    the Company (Azienda) id.
     * @param idDipendente the id for Employee to update.
     * @param email        the email for the Employee.
     * @throws NullPointerException     if the gestore is null.
     * @throws IllegalArgumentException if the idAzienda or idDipendente is not valid || email is not valid.
     */
    public boolean modificaDipendente(int idAzienda, int idDipendente, GestorePuntoVendita gestore, String email, boolean restrizioni) {
        Objects.requireNonNull(gestore);
        if (idAzienda <= 0 || idDipendente <= 0)
            throw new IllegalArgumentException("Illegal id for the fields.");
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal fields for update the Employee.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente : this.dbms.getDipendentiAzienda().get(azienda)) {
                    if (dipendente.getIdDipendente() == idDipendente) {
                        this.dbms.updateDipendente(azienda, idDipendente, email, restrizioni);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method allows you to remove
     * the profile of a specific employee within a Company.
     *
     * @param idAzienda    the Company (Azienda) id.
     * @param idDipendente the Employee id to remove.
     * @throws IllegalArgumentException if the idAzienda or idDipendente is not valid.
     */
    public boolean rimuoviDipendente(int idAzienda, int idDipendente) {
        if (idAzienda <= 0 || idDipendente <= 0)
            throw new IllegalArgumentException("Illegal id for Employee to remove.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente : this.dbms.getDipendentiAzienda().get(azienda)) {
                    if (dipendente.getIdDipendente() == idAzienda) {
                        this.dbms.removeDipendente(azienda, dipendente);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method allows you to take a specific Loyality
     * Space of a registered Company (Azienda) through its id.
     *
     * @param idAzienda the Company (Azienda) id.
     * @return the Loyalty Space of the Company.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public SpazioFedelta getSpazioFedeltaAzienda(int idAzienda) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getSpazioFedelta();
            }
        }
        return null;
    }

    /**
     * This method allowa you to change the Loyalty Area of
     * a registered Company (Azienda) taken through its id.
     *
     * @param idAzienda        the Company (Azienda) id.
     * @param spazioFedeltaNew Loyality space edited.
     * @throws NullPointerException     if the spazioFedeltaNew is null.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public boolean modificaSpazioFedelta(int idAzienda, SpazioFedelta spazioFedeltaNew) {
        Objects.requireNonNull(spazioFedeltaNew);
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                azienda.setSpazioFedelta(spazioFedeltaNew);
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns all active Loyalty Programs of a given
     * Company, by contacting the Coalition class (Coalizione) that
     * keeps track of the Coalition in the platform.
     *
     * @param idAzienda the Company (Azienda) id.
     * @return a list of Loyality Programs active of a given Company.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public Set<ProgrammaFedelta> getProgrammiFedeltaAzienda(int idAzienda) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid if for the Company");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.dbms.getProgrammiAzienda().get(azienda);
            }
        }
        return null;
    }

    /**
     * This method returns the list of active Reward Catalogs, of
     * a given Company.
     *
     * @param idAzienda the Company (Azienda) id.
     * @return list of Reward Catalogs of a given Company, if it has.
     * @throws IllegalArgumentException if the idAzienda is not correct.
     */
    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getCatalogoPremi();
            }
        }
        return null;
    }

    /**
     * This method returns the list of all Customers of a
     * given Company, enrolled in the Loyalty Programs offered by that
     * particular company, passed as an ID.
     *
     * @param idAzienda  the Company (Azienda) id.
     * @param coalizione the Coalition.
     * @return the list of all Customers.
     * @throws NullPointerException if coalizione is null.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public Set<Cliente> getClientiAzienda(int idAzienda, Coalizione coalizione) {
        Objects.requireNonNull(coalizione);
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                //Todo controllare coalizione.
                if (coalizione.getClientiIscritti().get(azienda).isEmpty())
                    return coalizione.getClientiIscritti().get(azienda);
            }
        }
        return null;
    }
}