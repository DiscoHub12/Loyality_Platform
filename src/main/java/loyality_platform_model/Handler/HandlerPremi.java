package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.*;

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
     * @return a Set of preconfigured Coupons if exists, null otherwise.
     */
    public Set<Coupon> getCouponPreconfiguratiAzienda(int idAzienda) {
        return this.dbms.getCouponPreconfiguratiAzienda(idAzienda);
    }

    /**
     * This method allows you to take a
     * preconfigured Coupon of a specific Company,
     * created by an Owner, if it exists.
     *
     * @param idAzienda the id for the Company (Azienda).
     * @param idCoupon  the id of the Coupon.
     * @return Coupon if exists, null otherwise.
     * @throws IllegalArgumentException if the idCoupon or idAzienda is not valid.
     */
    public Coupon getCouponPreconfiguratoAzienda(int idAzienda, int idCoupon) {
        if (idAzienda <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the Company or Coupon");
        Set<Coupon> couponAzienda = this.getCouponPreconfiguratiAzienda(idAzienda);
        for (Coupon coupon : couponAzienda) {
            if (coupon.getIdCoupon() == idCoupon) {
                return coupon;
            }
        }
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
        Set<CatalogoPremi> cataloghiPremiAzienda = this.dbms.getCatalogoPremiAzienda(idAzienda);
        for (CatalogoPremi catalogoPremi : cataloghiPremiAzienda) {
            if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                return catalogoPremi.toString();
            }
        }
        return "Catalogo Premi non trovato.";
    }

    /**
     * This method allows you to create a prize.
     *
     * @param nome     the name for the new Prize.
     * @param isPoints the variable that indicates if the Prize is for points program, or
     *                 level program.
     * @param number   the number of point, if the Prize is for points program, level otherwise.
     * @return the new Prize created.
     * @throws IllegalArgumentException if nome or number is invalid.
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
    public CatalogoPremi creaCatalogo(String nomeCatalogo, Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        return new CatalogoPremi(nomeCatalogo, premiCatalogo);
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
    public Coupon creaCoupon(int valoreSconto, String dataAttivazione ,String dataScadenza) {
        Objects.requireNonNull(dataAttivazione);
        Objects.requireNonNull(dataScadenza);
        if (valoreSconto <= 0)
            throw new IllegalArgumentException("Illegal value for the discount value.");
        return new Coupon(valoreSconto, dataAttivazione, dataScadenza);
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
    public boolean aggiungiPremioCatalogo(int idAzienda, int idCatalogoPremi, String nome, boolean isPoints, int number) {
        boolean response = false;
        if (idAzienda <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Illegal id for the Reward Catalog.");
        if (Objects.equals(nome, "") || number <= 0)
            throw new IllegalArgumentException("Illegal value for name or number.");
        Premio premio = creaPremio(nome, isPoints, number);
        Set<CatalogoPremi> catalogoPremiAzienda = this.dbms.getCatalogoPremiAzienda(idAzienda);
        for (CatalogoPremi catalogoPremi : catalogoPremiAzienda) {
            if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                catalogoPremi.aggiungiPremio(premio);
                response = this.dbms.updateCatalogoPremiAzienda(idAzienda, idCatalogoPremi, catalogoPremi);
            }
        }
        return response;
    }

    /**
     * This method allows you to add a new reward to a specific
     * Reward Catalog of a specific Loyalty Program.
     *
     * @param idAzienda   the id for the Company.
     * @param idProgramma the id for the Loyalty Program.
     * @param nome        the name for the Prize.
     * @param isPoints    the variable that indicates if the Prize is points or level.
     * @param number      the number of points-level for get the Prize.
     * @return true if success, false otherwise.
     */
    public boolean aggiungiPremioProgramma(int idAzienda, int idProgramma, String nome, boolean isPoints, int number) {
        if (idAzienda <= 0 || idProgramma <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        ProgrammaFedelta programmaFedelta = this.dbms.getProgrammaFedeltaById(idAzienda, idProgramma);
        if (programmaFedelta != null) {
            if (programmaFedelta.getCatalogoPremi() != null) {
                Premio premio = creaPremio(nome, isPoints, number);
                programmaFedelta.getCatalogoPremi().aggiungiPremio(premio);
                return this.dbms.updateProgrammaAzienda(idAzienda, idProgramma);
            }
        }
        return false;
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
        boolean response = false;
        if (idCatalogoPremi <= 0 || idPremio <= 0 || Objects.equals(nome, "") || number <= 0)
            throw new IllegalArgumentException("Incorrect value.");
        Set<CatalogoPremi> catalogoPremiAzienda = this.dbms.getCatalogoPremiAzienda(idAzienda);
        for (CatalogoPremi catalogoPremi : catalogoPremiAzienda) {
            if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                for (Premio premio : catalogoPremi.getPremiCatalogo()) {
                    if (premio.getIdPremio() == idPremio) {
                        if (premio.getPunti() == 0) {
                            premio.setNome(nome);
                            premio.setLivelli(number);
                        } else {
                            premio.setNome(nome);
                            premio.setPunti(number);
                        }
                        response = this.dbms.updateCatalogoPremiAzienda(idAzienda, idCatalogoPremi, catalogoPremi);
                    }
                }
            }
        }
        return response;
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
    public boolean deletePremio(int idAzienda, int idCatalogoPremi, int idPremio) {
        boolean response = false;
        if (idPremio <= 0 || idAzienda <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        Set<CatalogoPremi> catalogoPremiAzienda = this.dbms.getCatalogoPremiAzienda(idAzienda);
        for (CatalogoPremi catalogoPremi : catalogoPremiAzienda) {
            if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                for (Premio premio : catalogoPremi.getPremiCatalogo()) {
                    if (premio.getIdPremio() == idPremio) {
                        catalogoPremi.rimuoviPremio(premio);
                        if (this.dbms.updateCatalogoPremiAzienda(idAzienda, catalogoPremi.getIdCatalogoPremi(), catalogoPremi))
                            return true;
                    }
                }
            }
        }
        return response;
    }

    /**
     * This method allows you to go and remove a specific Reward
     * within a Reward Catalog of a loyalty program.
     *
     * @param idAzienda       the id for the Company.
     * @param idProgramma     the id for the Loyalty Program to get the Reward Catalog.
     * @param idCatalogoPremi the Reward Catalog.
     * @param idPremio        the Prize to remove into the Reward Catalog.
     * @return true if success, false otherwise.
     * @throws IllegalArgumentException if the id are not valid.
     */
    public boolean deletePremioProgramama(int idAzienda, int idProgramma, int idCatalogoPremi, int idPremio) {
        if (idAzienda <= 0 || idProgramma <= 0 || idCatalogoPremi <= 0 || idPremio <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        ProgrammaFedelta programmaFedelta = this.dbms.getProgrammaFedeltaById(idAzienda, idProgramma);
        if (programmaFedelta != null) {
            if (programmaFedelta.getCatalogoPremi().getIdCatalogoPremi() == idCatalogoPremi) {
                for (Premio premio : programmaFedelta.getCatalogoPremi().getPremiCatalogo()) {
                    if (premio.getIdPremio() == idPremio) {
                        programmaFedelta.getCatalogoPremi().rimuoviPremio(premio);
                        return this.dbms.updateProgrammaAzienda(idAzienda, idProgramma);
                    }
                }
            }
        }
        return false;
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
    public boolean aggiungiCatalogoPremi(int idAzienda, String nomeCatalogo, Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        if (idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the Company.");
        CatalogoPremi catalogoNew = creaCatalogo(nomeCatalogo, premiCatalogo);
        return this.dbms.addCatalogoPremiAzienda(idAzienda, catalogoNew);
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
    public boolean deleteCatalogoPremi(int idAzienda, int idCatalogoPremi) {
        if (idAzienda <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Invalid id for Company id Reward Catalog id");
        return this.dbms.removeCatalogoPremiAzienda(idAzienda, idCatalogoPremi);
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
    public boolean aggiungiCatalogoAProgrammaPunti(int idAzienda, int idProgrammaFedelta, String nomeCatalogo, Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        if (idAzienda <= 0 || idProgrammaFedelta <= 0)
            throw new IllegalArgumentException("Invalid id for the fileds.");
        CatalogoPremi catalogoPremi = creaCatalogo(nomeCatalogo, premiCatalogo);
        ProgrammaFedelta programmaPunti = this.dbms.getProgrammaFedeltaById(idAzienda, idProgrammaFedelta);
        programmaPunti.setCatalogoPremi(catalogoPremi);
        return this.dbms.updateProgrammaAzienda(idAzienda, idProgrammaFedelta);
    }

    /**
     * This method allows you to add a new Reward Catalog,
     * to an existing Loyalty Program Level of a specific company,
     * if it is existing and active.
     *
     * @param idAzienda          the id for the Company.
     * @param idProgrammaFedelta the id for the Loyalty Program to add the new Catalog, if not exists.
     * @param premiCatalogo      the prizes for the new Reward Catalog.
     * @throws NullPointerException     if the premiCatalogo is null.
     * @throws IllegalArgumentException if the idAzienda or idProgrammaFedelta is not correct.
     */
    public boolean aggiungiCatalogoProgrammaLivelli(int idAzienda, int idProgrammaFedelta, String nomeCatalogo, Set<Premio> premiCatalogo) {
        Objects.requireNonNull(premiCatalogo);
        if (idAzienda <= 0 || idProgrammaFedelta <= 0)
            throw new IllegalArgumentException("Invalid id for the Company or the id for Loyalty Program.");
        CatalogoPremi catalogoPremi = creaCatalogo(nomeCatalogo, premiCatalogo);
        ProgrammaFedelta programmaLivelli = this.dbms.getProgrammaFedeltaById(idAzienda, idProgrammaFedelta);
        programmaLivelli.setCatalogoPremi(catalogoPremi);
        return this.dbms.updateProgrammaAzienda(idAzienda, programmaLivelli.getIdProgramma());
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
    public boolean deleteCatalogoProgramma(int idAzienda, int idProgrammaFedelta, int idCatalogoPremi) {
        if (idAzienda <= 0 || idProgrammaFedelta <= 0 || idCatalogoPremi <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        ProgrammaFedelta programma = this.dbms.getProgrammaFedeltaById(idAzienda, idProgrammaFedelta);
        programma.setCatalogoPremi(null);
        return this.dbms.updateProgrammaAzienda(idAzienda, programma.getIdProgramma());
    }

    /**
     * This method allows you to add a pre-configured
     * Coupon within a specific Company.
     *
     * @param idAzienda    the id for the Company.
     * @param valoreSconto the discount value.
     * @param dataScadenza the expiration date for this Coupon.
     * @throws NullPointerException     if the dataAttivazione or dataScadenza is null.
     * @throws IllegalArgumentException if the idAzienda or valoreSconto is not correct.
     */
    public boolean aggiungiCouponPreconfigurato(int idAzienda, int valoreSconto, String dataAttivazione, String dataScadenza) {
        Objects.requireNonNull(dataScadenza);
        Objects.requireNonNull(dataAttivazione);
        if (idAzienda <= 0 || valoreSconto <= 1)
            throw new IllegalArgumentException("Invalid id for the fields.");
        Coupon newCoupon = creaCoupon(valoreSconto, dataAttivazione, dataScadenza);
        return this.dbms.addCouponPreconfiguratoAzienda(idAzienda, newCoupon);
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
        Coupon couponUpdated = new Coupon(valoreSconto, "", dataScadenza);
        return this.dbms.updateCouponPreconfiguratoAzienda(idAzienda, idCoupon, couponUpdated);
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
    public boolean deleteCouponPreconfigurato(int idAzienda, int idCoupon) {
        if (idAzienda <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        return this.dbms.removeCouponPreconfiguratoAzienda(idAzienda, idCoupon);
    }

    /**
     * This method allows you to add a new Prize for a specific Client, if
     * it exists, and it registered into platform.
     *
     * @param idCliente the id for the Customer.
     * @param idPremio  the id for the Prize to add into the Customer profile.
     * @throws IllegalArgumentException if the idAzienda or idCliente or idPremio is not valid.
     */
    public boolean aggiungiPremioClienteCatalogoGenerale(int idAzienda, int idCliente, int idPremio) {
        boolean response = false;
        if (idCliente <= 0 || idPremio <= 0 || idAzienda <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        Set<CatalogoPremi> catalogoPremiAzienda = this.dbms.getCatalogoPremiAzienda(idAzienda);
        for (CatalogoPremi catalogoPremi : catalogoPremiAzienda) {
            for (Premio premio : catalogoPremi.getPremiCatalogo()) {
                if (premio.getIdPremio() == idPremio) {
                    response = this.dbms.addPremioCliente(idCliente, premio);
                }
            }
        }
        return response;
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
    public boolean aggiungiPremioClienteCatalogoProgramma(int idAzienda, int idProgrammaFedelta, int idCliente, int idPremio) {
        boolean response = false;
        if (idAzienda <= 0 || idProgrammaFedelta <= 0 || idCliente <= 0 || idPremio <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        ProgrammaFedelta programmaFedelta = this.dbms.getProgrammaFedeltaById(idAzienda, idProgrammaFedelta);
        if (programmaFedelta != null) {
            for (Premio premio : programmaFedelta.getCatalogoPremi().getPremiCatalogo()) {
                if (premio.getIdPremio() == idPremio) {
                    response = this.dbms.addPremioCliente(idCliente, premio);
                }
            }
        }
        return response;
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
        return this.dbms.removePremioCliente(idCliente, idPremio);
    }

    /**
     * This method allows you to add a specific Coupon into Customer
     * profile.
     *
     * @param idCliente the id for the Customer.
     * @param idCoupon  the id for the Coupon to add into the Customer profile.
     * @throws IllegalArgumentException if the idCliente or idCoupon is not correct.
     */
    public boolean aggiungiCouponCliente(int idAzienda, int idCliente, int idCoupon) {
        if (idCliente <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        return this.dbms.addCoupon(idAzienda, idCliente, idCoupon);
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
        if (idCliente <= 0 || idCoupon <= 0)
            throw new IllegalArgumentException("Invalid id for the fields.");
        return this.dbms.removeCoupon(idCliente, idCoupon);
    }
}
