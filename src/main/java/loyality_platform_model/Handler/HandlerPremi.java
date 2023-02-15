package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class HandlerPremi {

    /**
     * This attribute represents the DataBase.
     */
    private final DBMS dbms;

    public HandlerPremi() {
        this.dbms = DBMS.getInstance();
    }

    /**
     * This method allows you to take all the
     * preconfigured Coupons of a specific Company,
     * created by an Owner, if they exist.
     *
     * @param idAzienda the id for the Company (Azienda).
     * @return a list of preconfigured Coupons if exists, null otherwise.
     */
    public Set<Coupon> getCouponPreconfiguratiAzienda() {
            return this.dbms.getCouponPreconfiguratiAzienda();
    }

    /**
     * This method allows you to take a
     * preconfigured Coupon of a specific Company,
     * created by an Owner, if it exists.
     *
     * @param idAzienda the id for the Company (Azienda).
     * @param idCoupon  the id of the Coupon.
     * @return Coupon if exists, null otherwise.
     * @throws IllegalArgumentException if the idCoupon is not valid.
     */
    public Coupon getCouponPreconfiguratoAzienda(int idAzienda, int idCoupon) {
        this.dbms.getCouponPreconfiguratoAzienda();

    }

    /**
     * This method allows you to take the details of a
     * Catalog of Awards of a particular Company, if it exists.
     *
     * @param idAzienda       the id for the Company (Azienda).
     * @param idCatalogoPremi the id of the Reward Catalog.
     * @return a details of Reward Catalog if exists, null otherwise.
     */
    public String getDetailsCatalogoPremi(int idAzienda, int idCatalogoPremi) {
        String tmp = "";
        Azienda azienda = getAziendaById(idAzienda);
        if (azienda != null) {
            for (CatalogoPremi catalogoPremi : azienda.getCatalogoPremi()) {
                if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                    tmp = "Details Catalogo Premi : " + catalogoPremi.toString();
                } else tmp = "Catalogo premi non esiste.";

            }
        }
        return tmp;
    }

    /**
     * This method allows you to create a prize.
     *
     * @param nome     the name for the new Prize.
     * @param isPoints the variable that indicates if the Prize is for points program, or
     *                 level program.
     * @param number   the number of point, if the Prize is for points program, level otherwise.
     * @return the new Prize created.
     * @throws IllegalArgumentException if nome, number is invalid.
     */
    public Premio creaPremio(String nome, boolean isPoints, int number) {
        if (Objects.equals(nome, "") || number <= 0)
            throw new IllegalArgumentException("Illegal value for name or number");
        return new Premio(nome, isPoints, number);
    }

    /**
     * This method allows you to create a new Rewards Catalog,
     * passing a list of already created Prize as parameters.
     *
     * @param premiCatalogo the list of already created Prize.
     * @return the new Reward Catalog.
     * @throws NullPointerException if the premiCatalogo is null.
     */
    public CatalogoPremi creaCatalogo(Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        return new CatalogoPremi(premiCatalogo);
    }

    /**
     * This method allows you to create a new
     * Coupon that will be made available and can be used
     * by a specific Employee.
     *
     * @param valoreSconto the discount value this Coupon will offer.
     * @param dataScadenza the expiry date of the Coupon.
     * @return the new Coupon.
     * @throws NullPointerException     if dataScadenza is null.
     * @throws IllegalArgumentException if valoreSconto value is not valid.
     */
    public Coupon creaCoupon(int valoreSconto, String dataScadenza) {
        Objects.requireNonNull(dataScadenza);
        if (valoreSconto <= 0)
            throw new IllegalArgumentException("Illegal value for the discount value.");
        return new Coupon(valoreSconto, dataScadenza);
    }

    /**
     * This method allows you to add a certain Prize
     * to a CatalogReward if exists.
     *
     * @param idCatalogoPremi the id of Reward Catalog to add the new Prize.
     * @param nome            the name of the Prize.
     * @param isPoints        the variable that indicates if the Prize is for points program, or
     *                        level program.
     * @param number          the number of point, if the Prize is for points program, level otherwise.
     * @throws IllegalArgumentException if the idCatalogoPremi is not valid or if not exist.
     */
    public void aggiungiPremio(int idAzienda, int idCatalogoPremi, String nome, boolean isPoints, int number) {
        if (idAzienda <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Illegal id for the Reward Catalog.");
        if (Objects.equals(nome, "") || number <= 0)
            throw new IllegalArgumentException("Illegal value for name or number.");
        Premio premio = creaPremio(nome, isPoints, number);
        this.dbms.aggiungiPremio(premio);
    }

    /**
     * This method allows you to change a certain Prize
     * to a CatalogReward
     *
     * @param idCatalogoPremi the id of Reward Catalog to update the new Prize.
     * @param nome            the name of the Prize.
     * @param isPoints        the variable that indicates if the Prize is for points program, or
     *                        level program.
     * @param number          the number of point, if the Prize is for points program, level otherwise.
     * @return true if the prize is updated, false otherwise.
     * @throws IllegalArgumentException if the fields (idAzienda, idCatalogoPremi...) is not valid.
     */
    public boolean modificaPremio(int idAzienda, int idCatalogoPremi, int idPremio, String nome, boolean isPoints, int number) {
        if (idCatalogoPremi <= 0 || idPremio <= 0 || Objects.equals(nome, "") || number <= 0)
            throw new IllegalArgumentException("Incorrect value.");
        this.dbms.updatePremio(nome, isPoints, number);

    }

    /**
     * This method allows you to remove a specific Prize within a
     * specific Reward Catalogue.
     *
     * @param idCatalogoPremi the id of Reward Catalog to remove the Prize.
     * @param idPremio        the id of the Prize.
     * @return true if the prize is removed, false othwerise.
     * @throws IllegalArgumentException if the idAzienda - idCatalogoPremi - idPremio is not correct.
     */
    public boolean rimuoviPremio(int idAzienda, int idCatalogoPremi, int idPremio) {
        if (idPremio <= 0 || idAzienda <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        this.dbms.rimuoviPremio(premio);
        return true;
    }

    /**
     * This method allows you to add a new Rewards Catalog
     * of a specific Company.
     *
     * @param idAzienda     the id for the Company to add the Reward Catalog.
     * @param premiCatalogo the Prize for the new Catalog.
     * @throws NullPointerException     if the premiCatalogo is null.
     * @throws IllegalArgumentException if the id for the Company is not valid.
     */
    public void aggiungiCatalogoPremi(int idAzienda, Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Illegal id for the Company.");
                CatalogoPremi newCatalogo = creaCatalogo(premiCatalogo);
                this.dbms.addCatalogoPremi(newCatalogo);
            }
        }
    }

    /**
     * This method allows you to remove a Rewards Catalog
     * of a specific Company, if it exists.
     *
     * @param idAzienda       the id for the Company to remove the Reward Catalog.
     * @param idCatalogoPremi the id for the Reward Catalog to remove.
     * @return true if the Reward Catalog is removed, false otherwise.
     * @throws IllegalArgumentException if the idAzienda or idCatalogoPremi is not correct.
     */
    public boolean rimuoviCatalogoPremi(int idAzienda, int idCatalogoPremi) {
        if (idAzienda <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Invalid id for Company id Reward Catalog id");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (CatalogoPremi catalogoPremi : azienda.getCatalogoPremi()) {
                    if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                        azienda.removeCatalogoPremi(catalogoPremi);
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method allows you to add a new Reward Catalog,
     * to an existing Loyalty Program Points of a specific company,
     * if it is existing and active.
     *
     * @param idAzienda          the id for the Company.
     * @param idProgrammaFedelta the id for the Loyalty Program to add the new Catalog, if not exists.
     * @param premiCatalogo      the Prize for the new Catalog.
     * @throws IllegalArgumentException if the idAzienda or idProgrammaFedeltà is not correct.
     */
    public void aggiungiCatalogoAProgrammaPunti(int idAzienda, int idProgrammaFedelta, Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        if (idAzienda <= 0 || idProgrammaFedelta <= 0)
            throw new IllegalArgumentException("Invalid id for the fileds.");
        CatalogoPremi newCatalogo = creaCatalogo(premiCatalogo);
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.dbms.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgrammaFedelta) {
                        if(programmaFedelta.getCatalogoPremi() != null){
                            //Todo implementare, manca getCatalogoPremi() su ProgrammaFedeltà, è presente su P.Punti e Livelli ma non posso accedere
                        }
                    }
                }
            }
        }
    }

    /**
     * This method allows you to add a new Reward Catalog,
     * to an existing Loyalty Program Level of a specific company,
     * if it is existing and active.
     * @param idAzienda the id for the Company.
     * @param idProgrammaFedelta the id for the Loyalty Program to add the new Catalog, if not exists.
     * @param premiCatalogo the prizes for the new Reward Catalog.
     */
    public void aggiungiCatalogoProgrammaLivelli(int idAzienda, int idProgrammaFedelta, Set<Premio> premiCatalogo){
        Objects.requireNonNull(premiCatalogo);
        //Todo implementare, manca getCatalogoPremi() su ProgrammaFedeltà, è presente su P.Punti e Livelli ma non posso accedere
    }

    /**
     * This method allows you to remove a Reward Catalog,
     * from an existing loyalty program of a specific company,
     * if it were existing and active.
     *
     * @param idAzienda          the id for the Company.
     * @param idProgrammaFedelta the id for the Loyalty Program to remove the Reward Catalog, if exists.
     * @param idCatalogoPremi    the id for the Reward Catalog to remove.
     * @throws IllegalArgumentException if the idAzienda or idProgrammaFedelta or idCatalogoPremi is not correct.
     */
    public boolean removeCatalogoAProgramma(int idAzienda, int idProgrammaFedelta, int idCatalogoPremi) {
        if (idAzienda <= 0 || idProgrammaFedelta <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        for (Azienda azienda : this.dbms.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.dbms.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgrammaFedelta) {
                        if(programmaFedelta.getCatalogoPremi().getIdCatalogoPremi() == idCatalogoPremi){
                            programmaFedelta.setCatalogoPremi(null); //Todo controllare il null.
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method allows you to add a pre-configured
     * Coupon within a specific Company.
     *
     * @param idAzienda    the id for the Company.
     * @param valoreSconto the discount value.
     * @param dataScadenza the expiration date for this Coupon.
     * @throws NullPointerException     if the dataScadenza is null.
     * @throws IllegalArgumentException if the idAzienda or valoreSconto is not correct.
     */
    public void aggiungiCouponPreconfigurato(int idAzienda, int valoreSconto, String dataScadenza) {
        Objects.requireNonNull(dataScadenza);
        if (idAzienda <= 0 || valoreSconto <= 1)
            throw new IllegalArgumentException("Invalid id for the fields.");
        Coupon newCoupon = creaCoupon(valoreSconto, dataScadenza);
        this.dbms.addCouponPreconfiguratoAzienda(azienda, newCoupon);
    }

    /**
     * This method, allows you to update a pre-configurated
     * Coupon within a specific Company.
     *
     * @param idAzienda    the id for the Company.
     * @param idCoupon     the id for the pre-configured Coupon.
     * @param valoreSconto the discount value.
     * @param dataScadenza the expiration date for this Coupon.
     * @return true if the Coupon is success update, false otherwise.
     * @throws NullPointerException     if the dataScadenza is null.
     * @throws IllegalArgumentException if the idAzienda or idCoupon is not correct or valoreSconto is invalid.
     */
    public boolean modificaCouponPreconfigurato(int idAzienda, int idCoupon, int valoreSconto, String dataScadenza) {
        Objects.requireNonNull(dataScadenza);
        if (idAzienda <= 0 || idCoupon <= 0 || valoreSconto <= 1)
            throw new IllegalArgumentException("Invalid id for the filed.");
                    Coupon coupon1 = new Coupon(valoreSconto, dataScadenza);
                    this.dbms.updateCouponPreconfiguratoAzienda(azienda, idCoupon, coupon1);
                    return true;
    }

    /**
     * This method, allows you to remove a pre-configured
     * Coupon within a specific Company, if exists.
     *
     * @param idAzienda the id for the Company.
     * @param idCoupon  the id for the pre-configured Coupon to remove.
     * @return true if the pre-configured Coupon is removed, false otherwise.
     * @throws IllegalArgumentException if the idAzienda or idCoupon is not valid.
     */
    public boolean rimuoviCouponPreconfigurato(int idAzienda, int idCoupon) {
        if (idAzienda <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        this.dbms.removeCouponPreconfiguratoAzienda(azienda, coupon);
        return true;
    }

    /**
     * This method allows you to add a new Prize for a specific Client, if
     * it exists, and it registered into platform.
     *
     * @param idCliente the id for the Customer.
     * @param idPremio  the id for the Prize to add into the Customer profile.
     */
    public void aggiungiPremioClienteCatalogoGenerale(int idAzienda, int idCliente, int idPremio) {
        if (idCliente <= 0 || idPremio <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        this.dbms.addPremioCliente(cliente, premio);
    }

    /**
     * This method allows you to add a specific Prize (stay into a specific
     * Loyalty Program in Company) to the Customer.
     *
     * @param idAzienda          the id for the Company.
     * @param idProgrammaFedelta the id of the Loyalty Program for the Company.
     * @param idCliente          the id for the Customer to add Prize.
     * @param idPremio           the id of the Prize.
     * @throws IllegalArgumentException if the idAzienda or idProgrammaFedeltà or idCliente or idPremio is not correct.
     */
    public void aggiungiPremioClienteCatalogoProgramma(int idAzienda, int idProgrammaFedelta, int idCliente, int idPremio) {
        if (idAzienda <= 0 || idProgrammaFedelta <= 0 || idCliente <= 0 || idPremio <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        //Todo implementare, manca getCatalogoPremi() su ProgrammaFedeltà, è presente su P.Punti e Livelli ma non posso accedere

    }

    /**
     * This method allows you to remove a Prize for a specific Client,
     * if it exists, it registered into the platform.
     *
     * @param idCliente the id for the Customer.
     * @param idPremio  the id for the Prize to removed into the Customer profile.
     * @return true if the Prize is removed, false otherwise.
     * @throws IllegalArgumentException if the idCliente or idPremio is not correct.
     */
    public boolean deletePremioCliente(int idCliente, int idPremio) {
        if (idCliente <= 0 || idPremio <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        this.dbms.removePremioCliente(cliente, premio);
        return true;
    }

    /**
     * This method allows you to add a specific Coupon into Customer
     * profile.
     *
     * @param idCliente the id for the Customer.
     * @param idCoupon  the id for the Coupon to add into the Customer profile.
     * @throws IllegalArgumentException if the idCliente or idCoupon is not correct.
     */
    public boolean aggiungiCouponCliente(int idCliente, int idCoupon) {
        if(idCliente <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        this.dbms.addCoupon(cliente, coupon);
        return true;
    }

    /**
     * This method allows you to remove a specific Coupon into
     * Customer profile, if exists.
     *
     * @param idCliente the id for the Customer.
     * @param idCoupon  the if for the Coupon to remove into the Customer profile.
     * @return true if the Coupon is removed, false othrwise.
     */
    public boolean deleteCouponCliente(int idCliente, int idCoupon) {
        if(idCliente <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        this.dbms.removeCoupon(cliente, coupon);
        return true;
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
