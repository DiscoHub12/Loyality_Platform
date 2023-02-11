package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.CatalogoPremi;
import loyality_platform_model.Models.Coupon;
import loyality_platform_model.Models.Premio;

import java.util.Date;
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
    public Set<Coupon> getCouponPreconfiguratiAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    /**
     * This method allows you to take a
     * preconfigured Coupon of a specific Company,
     * created by an Owner, if it exists.
     *
     * @param idAzienda the id for the Company (Azienda).
     * @param idCoupon  the id of the Coupon.
     * @return Coupon if exists, null otherwise.
     */
    public Coupon getCouponPreconfiguratoAzienda(int idAzienda, int idCoupon) {
        //Todo implementare
        return null;
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
        //Todo implementare
        return "";
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
        //Todo implementare
        return null;
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
        //Todo implementare.
        return null;
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
    public Coupon creaCoupon(int valoreSconto, Date dataScadenza) {
        //Todo implementare.
        return null;
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
    public void aggiungiPremio(int idCatalogoPremi, String nome, boolean isPoints, int number) {
        //Todo implementare.
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
     * @throws IllegalArgumentException if the idCatalogoPremi is not valid or if not exist.
     */
    public boolean modificaPremio(int idCatalogoPremi, String nome, boolean isPoints, int number) {
        //Todo implementare
        return false;
    }

    /**
     * This method allows you to remove a specific Prize within a
     * specific Reward Catalogue.
     *
     * @param idCatalogoPremi the id of Reward Catalog to remove the Prize.
     * @param idPremio        the id of the Prize.
     * @return true if the prize is removed, false othwerise.
     * @throws IllegalArgumentException if the idPremio is not correct, if the idPremio not exists.
     */
    public boolean rimuoviPremio(int idCatalogoPremi, int idPremio) {
        //Todo implementare.
        return false;
    }

    /**
     * This method allows you to add a new Rewards Catalog
     * of a specific Company.
     *
     * @param idAzienda     the id for the Company to add the Reward Catalog.
     * @param premiCatalogo the Prize for the new Catalog.
     * @throws NullPointerException if the premiCatalogo is null.
     */
    public void aggiungiCatalogoPremi(int idAzienda, Set<Premio> premiCatalogo) {
        //Todo implementare.
    }

    /**
     * This method allows you to remove a Rewards Catalog
     * of a specific Company, if it exists.
     *
     * @param idAzienda       the id for the Company to remove the Reward Catalog.
     * @param idCatalogoPremi the id for the Reward Catalog to remove.
     * @return true if the Reward Catalog is removed, false otherwise.
     */
    public boolean rimuoviCatalogoPremi(int idAzienda, int idCatalogoPremi) {
        //Todo implementare.
        return false;
    }

    /**
     * This method allows you to add a new Reward Catalog,
     * to an existing loyalty program of a specific company,
     * if it is existing and active.
     *
     * @param idAzienda          the id for the Company.
     * @param idProgrammaFedelta the id for the Loyalty Program to add the new Catalog, if not exists.
     * @param premiCatalogo      the Prize for the new Catalog.
     */
    public void aggiungiCatalogoAProgramma(int idAzienda, int idProgrammaFedelta, Set<Premio> premiCatalogo) {
        //Todo implementare.
    }

    /**
     * This method allows you to remove a Reward Catalog,
     * from an existing loyalty program of a specific company,
     * if it were existing and active.
     *
     * @param idAzienda          the id for the Company.
     * @param idProgrammaFedelta the id for the Loyalty Program to remove the Reward Catalog, if exists.
     * @param idCatalogoPremi    the id for the Reward Catalog to remove.
     */
    public void removeCatalogoAProgramma(int idAzienda, int idProgrammaFedelta, int idCatalogoPremi) {
        //Todo implementare
    }

    /**
     * This method allows you to add a pre-configured
     * Coupon within a specific Company.
     *
     * @param idAzienda    the id for the Company.
     * @param valoreSconto the discount value.
     * @param dataScadenza the expiration date for this Coupon.
     */
    public void aggiungiCouponPreconfigurato(int idAzienda, int valoreSconto, Date dataScadenza) {
        //Todo implementare
    }

    /**
     * This method, allows you to update a pre-configurated
     * Coupon within a specific Company.
     *
     * @param idAzienda    the id for the Company.
     * @param idCoupon     the id for the pre-configured Coupon.
     * @param valoreSconto the discount value.
     * @param dataScadenza the expiration date for this Coupon.
     * @return
     */
    public boolean modificaCouponPreconfigurato(int idAzienda, int idCoupon, int valoreSconto, Date dataScadenza) {
        //Todo implementare.
        return false;
    }

    /**
     * This method, allows you to remove a pre-configured
     * Coupon within a specific Company, if exists.
     *
     * @param idAzienda the id for the Company.
     * @param idCoupon  the id for the pre-configured Coupon to remove.
     * @return true if the pre-configured Coupon is removed, false otherwise.
     */
    public boolean rimuoviCouponPreconfigurato(int idAzienda, int idCoupon) {
        //Todo implementare.
        return false;
    }

    /**
     * This method allows you to add a new Prize for a specific Client, if
     * it exists, and it registered into platform.
     *
     * @param idCliente the id for the Customer.
     * @param idPremio  the id for the Prize to add into the Customer profile.
     */
    public void aggiungiPremioCliente(int idCliente, int idPremio) {
        //Todo implementare.
    }

    /**
     * This method allows you to remove a Prize for a specific Client,
     * if it exists, it registered into the platform.
     *
     * @param idCliente the id for the Customer.
     * @param idPremio  the id for the Prize to removed into the Customer profile.
     * @return true if the Prize is removed, false otherwise.
     */
    public boolean deletePremioCliente(int idCliente, int idPremio) {
        //Todo implementare.
        return false;
    }

    /**
     * This method allows you to add a specific Coupon into Customer
     * profile.
     *
     * @param idCliente the id for the Customer.
     * @param idCoupon  the id for the Coupon to add into the Customer profile.
     */
    public void aggiungiCouponCliente(int idCliente, int idCoupon) {
        //Todo implementare.
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
        //Todo implementare.
        return false;
    }

}
