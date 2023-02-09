package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

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
     * @throws IllegalArgumentException if the idAzienda is not valid, or if the Company
     *                                  is not registered inside Platform.
     */
    public GestorePuntoVendita getTitolareAzienda(int idAzienda) {
        //Todo implementare
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
        //Todo implementare
        return null;
    }

    /**
     * This method allows you to create a new employee
     * with access to the platform of a specific Company.
     *
     * @param idAzienda the Company (Azienda) id.
     * @param nome      the name for the Employee to add.
     * @param cognome   the surname for the Employee to add.
     * @param email     the email for the Employee to add.
     * @throws IllegalArgumentException if the Name, Surname or email is not valid.
     */
    public void creaDipendente(int idAzienda, String nome, String cognome, String email) {
        //Todo implementare
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
    public void aggiungiDipendente(int idAzienda, String nome, String cognome, String email) {
        //Todo implementare
    }

    /**
     * This method allows you to change the information of a
     * particular Employee within Company.
     *
     * @param idAzienda the Company (Azienda) id.
     * @param nome      the name for the Employee.
     * @param cognome   the surname for the Employee.
     * @param email     the email for the Employee.
     * @throws IllegalArgumentException if the new Name, Surname or email is not valid.
     */
    public void modificaDipendente(int idAzienda, String nome, String cognome, String email) {
        //Todo implementare
    }

    /**
     * This method allows you to remove
     * the profile of a specific employee within a Company..
     * @param idAzienda the Company (Azienda) id.
     * @param dipendente the Employee to remove.
     */
    public void rimuoviDipendente(int idAzienda, Dipendente dipendente) {
        //Todo implementare
    }

    /**
     * This method allows you to take a specific Loyality
     * Space of a registered Company (Azienda) through its id.
     * @param idAzienda the Company (Azienda) id.
     * @return the Loyalty Space of the Company.
     */
    public SpazioFedelta getSpazioFedeltaAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    /**
     * This method allowa you to change the Loyalty Area of
     * a registered Company (Azienda) taken through its id.
     * @param idAzienda the Company (Azienda) id.
     * @param spazioFedeltaOld the Loyalty space to edit.
     * @param spazioFedeltaNew Loyality space edited.
     */
    public void modificaSpazioFedelta(int idAzienda, SpazioFedelta spazioFedeltaOld, SpazioFedelta spazioFedeltaNew) {
        //Todo implementare
    }

    /**
     * This method returns all active Loyalty Programs of a given
     * Company, by contacting the Coalition class (Coalizione) that
     * keeps track of the Coalition in the platform.
     * @param idAzienda the Company (Azienda) id.
     * @param coalizione the Coalition.
     * @return a list of Loyality Programs active of a given Company.
     */
    public Set<ProgrammaFedelta> getProgrammiFedeltaAzienda(int idAzienda, Coalizione coalizione) {
        //Todo implementare
        return null;
    }

    /**
     * This method returns the list of active Reward Catalogs, of
     *  a given Company.
     * @param idAzienda the Company (Azienda) id.
     * @return list of Reward Catalogs of a given Company, if it has.
     */
    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    /**
     * This method returns the list of all Customers of a
     * given Company, enrolled in the Loyalty Programs offered by that
     * particular company, passed as an ID.
     * @param idAzienda the Company (Azienda) id.
     * @param coalizione the Coalition.
     * @return the list of all Customers.
     */
    public Set<Cliente> getClientiAzienda(int idAzienda, Coalizione coalizione) {
        //Todo implementare
        return null;
    }

}
