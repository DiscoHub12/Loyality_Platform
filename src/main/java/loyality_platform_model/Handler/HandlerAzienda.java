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
     */
    public GestorePuntoVendita getTitolareAzienda(int idAzienda) {
        GestorePuntoVendita gestore = null;
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null)
            gestore = azienda.getTitolare();
        return gestore;
    }

    /**
     * This method returns the list of employees of the
     * company passed as parameters with the id, if it is
     * registered on the Platform.
     *
     * @param idAzienda the id of Company (Azienda).
     * @return a list of Employee of the Company (Azienda).
     */
    public Set<Dipendente> getDipendentiAzienda(int idAzienda) {
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            return this.dbms.getDipendentiAzienda().get(azienda);
        }
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
     * @throws IllegalArgumentException if the Name, Surname or email is not valid.
     */
    public void aggiungiDipendente(int idAzienda, String nome, String cognome, String email, boolean restrizioni) {
        Dipendente created = creaDipendente(nome, cognome, email, restrizioni);
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            for (Dipendente dipendente : this.dbms.getDipendentiAzienda().get(azienda)) {
                if (!dipendente.equals(created)) {
                    this.dbms.addDipendente(azienda, created);
                } else throw new IllegalArgumentException("Invalid operation, the Employee already exist.");
            }
        }
    }

    /**
     * This method allows you to change the information of a
     * particular Employee within Company.
     *
     * @param idAzienda    the Company (Azienda) id.
     * @param idDipendente the id for Employee to update.
     * @param email        the email for the Employee.
     * @throws IllegalArgumentException if the new Name, Surname or email is not valid.
     */
    public boolean modificaDipendente(int idAzienda, int idDipendente, GestorePuntoVendita gestore, String email, boolean restrizioni) {
        if (idDipendente <= 0)
            throw new IllegalArgumentException("Illegal id for the Employee.");
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal fields for update the Employee.");
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            for (Dipendente dipendente : this.dbms.getDipendentiAzienda().get(azienda)) {
                if (dipendente.getIdDipendente() == idAzienda) {
                    dipendente.setEmail(email);
                    dipendente.setRestrizioni(gestore, restrizioni);
                    return true;
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
     */
    public boolean rimuoviDipendente(int idAzienda, int idDipendente) {
        if (idDipendente <= 0)
            throw new IllegalArgumentException("Illegal id for Employee to remove.");
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            for (Dipendente dipendente : this.dbms.getDipendentiAzienda().get(azienda)) {
                if (dipendente.getIdDipendente() == idAzienda) {
                    this.dbms.removeDipendente(azienda, dipendente);
                    return true;
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
     */
    public SpazioFedelta getSpazioFedeltaAzienda(int idAzienda) {
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null)
            return azienda.getSpazioFedelta();
        return null;
    }

    /**
     * This method allowa you to change the Loyalty Area of
     * a registered Company (Azienda) taken through its id.
     *
     * @param idAzienda        the Company (Azienda) id.
     * @param spazioFedeltaNew Loyality space edited.
     * @throws NullPointerException if the spazioFedeltaNew is null.
     */
    public void modificaSpazioFedelta(int idAzienda, SpazioFedelta spazioFedeltaNew) {
        Objects.requireNonNull(spazioFedeltaNew);
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            azienda.setSpazioFedelta(spazioFedeltaNew);
        }
    }

    /**
     * This method returns all active Loyalty Programs of a given
     * Company, by contacting the Coalition class (Coalizione) that
     * keeps track of the Coalition in the platform.
     *
     * @param idAzienda the Company (Azienda) id.
     * @return a list of Loyality Programs active of a given Company.
     */
    public Set<ProgrammaFedelta> getProgrammiFedeltaAzienda(int idAzienda) {
        Set<ProgrammaFedelta> programmi = null;
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null)
            programmi = this.dbms.getProgrammiAzienda().get(azienda);
        return programmi;
    }

    /**
     * This method returns the list of active Reward Catalogs, of
     * a given Company.
     *
     * @param idAzienda the Company (Azienda) id.
     * @return list of Reward Catalogs of a given Company, if it has.
     */
    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            return azienda.getCatalogoPremi();
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
     */
    public Set<Cliente> getClientiAzienda(int idAzienda, Coalizione coalizione) {
        Objects.requireNonNull(coalizione);
        //Todo implementare, manca coalizione.
        return null;
    }

    /**
     * Private method that allows to reduce all the duplicated code,
     * since in most of the methods, the same requests are made to the Database,
     * taking the ID of the Company.
     * This method returns the interested company that you want to take,
     * if it is registered and the id is valid otherwise it returns null.
     *
     * @param idAzienda the id of the Company to be taken in the Database.
     * @return Company (Azienda) if exists, null otherwise.
     * @throws IllegalArgumentException if the idAzienda is not valid or if the Company(Azienda)
     *                                  is not registered inside the Platform.
     */
    private Azienda getAziendaById(int idAzienda) {
        Azienda company = null;
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Illegal id for the Company");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda)
                company = azienda;
        }
        return company;
    }

}
