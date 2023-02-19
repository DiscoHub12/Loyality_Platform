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
        return this.dbms.getTitolareAzienda(idAzienda);
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
        return this.dbms.getDipendentiAziendaById(idAzienda);
    }

    /**
     *
     * @param idAzienda
     * @param idDipendente
     * @return
     */
    public Dipendente getDipendenteById(int idAzienda, int idDipendente){
        if(idAzienda <= 0 || idDipendente <= 0)
            throw new IllegalArgumentException("Invalid id for Company or Employee.");
       return this.dbms.getDipendenteById(idAzienda, idDipendente);
    }

    /**
     *
     * @param idDipendente
     * @return
     */
    public String getDetailsDipendente(int idAzienda, int idDipendente) {
        if(idAzienda <= 0 || idDipendente <= 0)
            throw new IllegalArgumentException("Invalid id for Company or Employee");
        Dipendente dipendente = getDipendenteById(idAzienda, idDipendente);
        if(dipendente != null)
            return dipendente.toString();
        return "Employee with this id dont'e exists.";
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
        Dipendente created = creaDipendente(nome, cognome, email, restrizioni);
        return this.dbms.addDipendente(idAzienda, created);
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
    public boolean modificaDipendente(int idAzienda, int idDipendente, String email, boolean restrizioni) {
        if (idAzienda <= 0 || idDipendente <= 0)
            throw new IllegalArgumentException("Illegal id for the fields.");
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal fields for update the Employee.");
        return this.dbms.updateDipendente(idAzienda, idDipendente, email, restrizioni);
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
        return this.dbms.removeDipendente(idAzienda, idDipendente);
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
        return this.dbms.getSpazioFedeltaAzienda(idAzienda);
    }

    /**
     * This method allowa you to change the Loyalty Area of
     * a registered Company (Azienda) taken through its id.
     *
     * @param idAzienda        the Company (Azienda) id.
     * @throws NullPointerException     if the spazioFedeltaNew is null.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public boolean modificaSpazioFedelta(int idAzienda, String nome, String indirizzo, String numeroTelefono, String email) {
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        SpazioFedelta newSpazioFedelta = new SpazioFedelta(nome, indirizzo, numeroTelefono, email);
        return this.dbms.modificaSpazioFedelta(idAzienda, newSpazioFedelta);
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
        return this.dbms.getProgrammiFedeltaAzienda(idAzienda);
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
        return this.dbms.getCatalogoPremiAzienda(idAzienda);
    }

    /**
     * This method returns the list of all Customers of a
     * given Company, enrolled in the Loyalty Programs offered by that
     * particular company, passed as an ID.
     *
     * @param idAzienda  the Company (Azienda) id.
     * @param coalizione the Coalition.
     * @return the list of all Customers.
     * @throws NullPointerException     if coalizione is null.
     * @throws IllegalArgumentException if the idAzienda is not valid.
     */
    public Set<Cliente> getClientiAzienda(int idAzienda, Coalizione coalizione) {
        Objects.requireNonNull(coalizione);
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company");
        return coalizione.getClientiAzienda(idAzienda);
    }
}