package loyality_platform_model.DBMS;


import loyality_platform_model.Models.*;

import java.util.*;

/**
 * Class representing the DataBase, capable of persisting data within
 * the Platform.
 */
public class DBMS {


    /**
     * This attribute represents the instance of the class, capable of
     * being able to invoke it without instantiating a new class and losing persistence.
     */
    private static DBMS instance;

    /**
     * This attribute represents companies that are
     * registered on the Platform.
     */
    private final Set<Azienda> aziendeIscritte;

    /**
     * This attribute represents a map where the key is the company,
     * and its value is the employees who have an Account within the platform.
     */
    private final Map<Azienda, Set<Dipendente>> dipendentiAzienda;

    /**
     * This attribute represents a map where the key is a company,
     * and the value is a list of Loyalty Programs that are active in that
     * particular company.
     */
    private final Map<Azienda, Set<ProgrammaFedelta>> programmiAzienda;

    /**
     * This attribute represents a map where the Key is the company,
     * and the value is a list of pre-configured Coupons that the
     * company has created.
     */
    private final Map<Azienda, Set<Coupon>> couponPreconfiguratiAzienda;

    /**
     * This attribute represents a map where the Key is the Company,
     * and the value is a list of Preconfigured SMS that a Company
     * has created and can use to send to Customers.
     */
    private final Map<Azienda, Set<SMS>> SMSPreconfiguratiAzienda;

    /**
     * This attribute represents all Customers who are registered
     * on the platform.
     */
    private final Set<Cliente> clientiIscritti;

    /**
     * This attribute represents a map where the Key is a registered Customer,
     * and the Value is a list of SMS that the Customer has received,
     * it is possible for the Employee to see the messages received from
     * the Customer.
     */
    private final Map<Cliente, Set<SMS>> SMSCliente;

    /**
     * This attribute represents a map where the Key is a Client,
     * and the Value is a list of Visits that the Client has booked.
     */
    private final Map<Cliente, Set<Visita>> visiteCliente;

    /**
     * This attribute represents a map where the Key is a Customer,
     * and the Value is a list of Coupons that a Customer owns.
     */
    private final Map<Cliente, Set<Coupon>> couponCliente;

    /**
     * This attribute represents a map where the Key is a Customer,
     * and the Value is a list of Rewards that the Customer has claimed.
     */
    private final Map<Cliente, Set<Premio>> premiCliente;

    /**
     * This attributes rapresents all Card in the Platform.
     */
    private final Set<Tessera> tessereClienti;

    /**
     * This attribute represents the list of all Loyalty programs
     * made available by the PlatformManager.
     */
    private final Set<ProgrammaFedelta> programmiDisponibili;

    /**
     * This attribute represents a list of SMS Packages
     * that Cardholders can purchase.
     */
    private final Set<PacchettoSMS> pacchettiSMS;

    /**
     * This attribute represents a list of purchasable
     * Subscriptions for the platform.
     */
    private final Set<Abbonamento> abbonamenti;

    /**
     * This attribute represents a Coalition-type object, which therefore takes
     * into account the entire Coalition that is formed through the
     * Companies and the corresponding Loyalty Programmes.
     */
    private Coalizione coalizione;


    /**
     * Private Constructor, instantiates a DBMS type object to keep
     * track of all data within the platform, acts as a kind of DataBase
     * only for project simulation.
     * The methods within this class, pretend to be a Backend that simulates
     * requests to the Database by taking the data, adding, removing or updating any data.
     */
    private DBMS() {
        this.aziendeIscritte = new HashSet<>();
        this.dipendentiAzienda = new HashMap<>();
        this.programmiAzienda = new HashMap<>();
        this.couponPreconfiguratiAzienda = new HashMap<>();
        this.SMSPreconfiguratiAzienda = new HashMap<>();
        this.clientiIscritti = new HashSet<>();
        this.tessereClienti = new HashSet<>();
        this.SMSCliente = new HashMap<>();
        this.couponCliente = new HashMap<>();
        this.premiCliente = new HashMap<>();
        this.visiteCliente = new HashMap<>();
        this.programmiDisponibili = new HashSet<>();
        this.pacchettiSMS = new HashSet<>();
        this.abbonamenti = new HashSet<>();
        this.coalizione = new Coalizione();
    }

    /**
     * SINGLETON Pattern
     *
     * @return the instance of the Database,
     * in order not to lose any data, so that it is not instantiated
     */
    public static DBMS getInstance() {
        if (instance == null) {
            instance = new DBMS();
        }
        return instance;
    }

    public Set<ProgrammaFedelta> getProgrammiDisponibili() {
        return this.programmiDisponibili;
    }

    public Set<PacchettoSMS> getPacchettiSMS() {
        return this.pacchettiSMS;
    }

    public Set<Abbonamento> getAbbonamenti() {
        return this.abbonamenti;
    }

    public Set<Azienda> getAziendeIscritte() {
        return this.aziendeIscritte;
    }


    public Set<Cliente> getClientiIscritti() {
        return this.clientiIscritti;
    }

    public Set<Tessera> getTessereClienti() {
        return this.tessereClienti;
    }

    public Coalizione getCoalizione() {
        return this.coalizione;
    }

    public boolean addClienteCoalizione(int idCliente, ProgrammaFedelta programmaFedelta) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getIdCliente() == idCliente) {
                return this.coalizione.addClienteCoalizione(programmaFedelta, cliente);
            }
            return false;
        }
        return false;
        /**
         for (Cliente cliente : this.getClientiIscritti()) {
         if (cliente.getIdCliente() == idCliente) {
         for (Map.Entry<Azienda, Set<ProgrammaFedelta>> entry : this.getProgrammiAzienda().entrySet()) {
         for (ProgrammaFedelta toScroll : entry.getValue()) {
         if (toScroll.equals(programmaFedelta)) {
         for (ProgrammaFedelta programmaFedelta1 : this.getCoalizione().getClientiIscritti().keySet()) {
         if (programmaFedelta.equals(programmaFedelta1)) {
         this.getCoalizione().getClientiIscritti().get(programmaFedelta).add(cliente);
         }
         Set<Cliente> clienti = new HashSet<>();
         clienti.add(cliente);
         this.getCoalizione().getClientiIscritti().put(programmaFedelta, clienti);
         }
         return true;
         }
         }
         }
         }
         }
         */
    }

    public boolean deleteClienteCoalizione(int idCliente, ProgrammaFedelta programmaFedelta) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Map.Entry<Azienda, Set<ProgrammaFedelta>> entry : this.getProgrammiAzienda().entrySet()) {
                    for (ProgrammaFedelta toScroll : entry.getValue()) {
                        if (toScroll.equals(programmaFedelta)) {
                            for (ProgrammaFedelta programmaFedelta1 : this.getCoalizione().getClientiIscritti().keySet()) {
                                if (programmaFedelta.equals(programmaFedelta1)) {
                                    return this.getCoalizione().getClientiIscritti().get(programmaFedelta).remove(cliente);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean addAziendaCoalizione(int idAzienda, int idProgramma) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.coalizione.getAziendePerProgramma().keySet()) {
                    if (programmaFedelta.getIdProgramma() == idProgramma) {
                        return this.coalizione.addAziendaCoalizione(programmaFedelta, azienda);
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteAziendaCoalizione(int idAzienda, int idProgramma) {
        for (ProgrammaFedelta programmaFedelta : this.coalizione.getAziendePerProgramma().keySet()) {
            if (programmaFedelta.getIdProgramma() == idProgramma) {
                for (Azienda azienda : this.coalizione.getAziendeIscritteProgramma(programmaFedelta.getIdProgramma())) {
                    if (azienda.getIdAzienda() == idAzienda) {
                        return this.coalizione.deleteAziendaProgramma(idAzienda, idProgramma);
                    }
                }
            }
        }
        return false;
    }


    public boolean addProgrammaCoalizione(int idAzienda, ProgrammaFedelta programmaFedelta) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.coalizione.addAziendaCoalizione(programmaFedelta, azienda);
            }
        }
        return false;
    }
        /**for (ProgrammaFedelta toScroll : this.getCoalizione().getAziendePerProgramma().keySet()) {
         if (!toScroll.equals(programmaFedelta)) {
         Set<Azienda> aziende = new HashSet<>();
         aziende.add(azienda);
         this.getCoalizione().getAziendePerProgramma().put(programmaFedelta, aziende);
         return true;
         }
         for (Map.Entry<ProgrammaFedelta, Set<Azienda>> entry : this.getCoalizione().getAziendePerProgramma().entrySet()) {
         for (Azienda azienda1 : entry.getValue()) {
         if (!azienda1.equals(azienda)) {
         return this.getCoalizione().getAziendePerProgramma().get(programmaFedelta).add(azienda);
         }
         }
         }
         }
         */


    //--OPERAZIONI AZIENDA--

    public Azienda getAziendaById(int idAzienda) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda;
            }
        }
        return null;
    }

    public boolean addAzienda(Azienda azienda) {
        return this.getAziendeIscritte().add(azienda);
    }


    public boolean updateAzienda(int idAzienda, Azienda aziendaNew) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                azienda.setTitolare(aziendaNew.getTitolare());
                azienda.setSpazioFedelta(aziendaNew.getSpazioFedelta());
                azienda.setCatalogoPremi(aziendaNew.getCatalogoPremi());
                return true;
            }
        }
        return false;
    }

    public boolean removeAzienda(int idAzienda) {
        for (Azienda azienda1 : this.getAziendeIscritte()) {
            if (azienda1.getIdAzienda() == idAzienda) {
                return this.getAziendeIscritte().remove(azienda1);
            }
        }
        return false;
    }

    public GestorePuntoVendita getTitolareAzienda(int idAzienda) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getTitolare();
            }
        }
        return null;
    }


    public Map<Azienda, Set<Dipendente>> getDipendentiAzienda() {
        return this.dipendentiAzienda;
    }


    public Set<Dipendente> getDipendentiAziendaById(int idAzienda) {
        for (Azienda azienda : this.getDipendentiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.getDipendentiAzienda().get(azienda);
            }
        }
        return null;
    }


    public Dipendente getDipendenteById(int idAzienda, int idDipendente) {
        for (Azienda azienda : this.getDipendentiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente : this.getDipendentiAzienda().get(azienda)) {
                    if (dipendente.getIdDipendente() == idDipendente) {
                        return dipendente;
                    }
                }
            }
        }
        return null;
    }

    public SpazioFedelta getSpazioFedeltaAzienda(int idAzienda) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getSpazioFedelta();
            }
        }
        return null;
    }


    public Map<Azienda, Set<SMS>> getSMSPreconfiguratiAzienda() {
        return this.SMSPreconfiguratiAzienda;
    }

    public Set<SMS> getSMSPreconfigurati(int idAzienda) {
        for (Azienda azienda : this.getSMSPreconfiguratiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.getSMSPreconfiguratiAzienda().get(azienda);
            }
        }
        return null;
    }

    public ConfigurazioneSMS getSMSPreconfigurato(int idAzienda, int idSMS) {
        for (Azienda azienda : this.getSMSPreconfiguratiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (SMS sms : this.getSMSPreconfiguratiAzienda().get(azienda)) {
                    if (sms.getIdSMS() == idSMS) {
                        return sms.getConfigurazione();
                    }
                }
            }
        }
        return null;
    }

    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getCatalogoPremi();
            }
        }
        return null;
    }

    public boolean addCatalogoPremiAzienda(int idAzienda, CatalogoPremi catalogoPremi) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                azienda.addCatalogoPremi(catalogoPremi);
                return true;
            }
        }
        return false;
    }

    public boolean updateCatalogoPremiAzienda(int idAzienda, int idCatalogoPremi, CatalogoPremi catalogoPremiUpdated) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (CatalogoPremi catalogoPremi : azienda.getCatalogoPremi()) {
                    if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                        catalogoPremi.setPremiCatalogo(catalogoPremiUpdated.getPremiCatalogo());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeCatalogoPremiAzienda(int idAzienda, int idCatalogoPremi) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (CatalogoPremi catalogoPremi : azienda.getCatalogoPremi()) {
                    if (catalogoPremi.getIdCatalogoPremi() == idCatalogoPremi) {
                        azienda.removeCatalogoPremi(catalogoPremi);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Set<Cliente> getClientiAzienda(int idAzienda) {
        //TODO implementare
        return null;
    }

    public Map<Azienda, Set<Coupon>> getCouponPreconfiguratiAzienda() {
        return this.couponPreconfiguratiAzienda;
    }

    public Set<Coupon> getCouponPreconfiguratiAzienda(int idAzienda) {
        for (Azienda azienda : this.getCouponPreconfiguratiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.getCouponPreconfiguratiAzienda().get(azienda);
            }
        }
        return null;
    }


    public Map<Azienda, Set<ProgrammaFedelta>> getProgrammiAzienda() {
        return this.programmiAzienda;
    }


    public Set<ProgrammaFedelta> getProgrammiFedeltaAzienda(int idAzienda) {
        for (Azienda azienda : this.getProgrammiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.getProgrammiAzienda().get(azienda);
            }
        }
        return null;
    }


    public boolean modificaSpazioFedelta(int idAzienda, SpazioFedelta spazioFedeltaNew) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                azienda.getSpazioFedelta().setNome(spazioFedeltaNew.getNome());
                azienda.getSpazioFedelta().setEmail(spazioFedeltaNew.getEmail());
                azienda.getSpazioFedelta().setNumeroTelefono(spazioFedeltaNew.getNumeroTelefono());
                azienda.getSpazioFedelta().setIndirizzo(spazioFedeltaNew.getIndirizzo());
                return true;
            }
        }
        return false;
    }

    public boolean addDipendente(int idAzienda, Dipendente dipendente) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (this.getDipendentiAzienda().containsKey(azienda)) {
                    if (this.getDipendentiAzienda().get(azienda) == null) {
                        Set<Dipendente> dipendentiAzienda = new HashSet<>();
                        dipendentiAzienda.add(dipendente);
                        this.getDipendentiAzienda().put(azienda, dipendentiAzienda);
                    } else {
                        return this.getDipendentiAzienda().get(azienda).add(dipendente);
                    }
                } else {
                    Set<Dipendente> dipendentiAzienda = new HashSet<>();
                    dipendentiAzienda.add(dipendente);
                    this.getDipendentiAzienda().put(azienda, dipendentiAzienda);
                }
                return true;
            }
        }
        return false;
    }

    public boolean updateDipendente(int idAzienda, int idDipendente, String email, boolean restrizioni) {
        for (Azienda azienda : this.getDipendentiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente : this.getDipendentiAzienda().get(azienda)) {
                    if (dipendente.getIdDipendente() == idDipendente) {
                        dipendente.setEmail(email);
                        dipendente.setRestrizioni(restrizioni);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeDipendente(int idAzienda, int idDipendente) {
        for (Azienda azienda : this.getDipendentiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente1 : this.getDipendentiAzienda().get(azienda)) {
                    if (dipendente1.getIdDipendente() == idDipendente) {
                        return this.getDipendentiAzienda().get(azienda).remove(dipendente1);
                    }
                }
            }
        }

        return false;
    }


    public boolean addProgrammaAzienda(int idAzienda, ProgrammaFedelta programmaFedelta) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (this.getProgrammiAzienda().containsKey(azienda)) {
                    if (this.getProgrammiAzienda().get(azienda) == null) {
                        Set<ProgrammaFedelta> programmiAzienda = new HashSet<>();
                        programmiAzienda.add(programmaFedelta);
                        this.getProgrammiAzienda().put(azienda, programmiAzienda);
                        this.addProgrammaCoalizione(idAzienda, programmaFedelta);
                        return true;
                    } else {
                        for (ProgrammaFedelta programmaFedelta1 : this.getProgrammiAzienda().get(azienda)) {
                            if (programmaFedelta1.equals(programmaFedelta))
                                return false;
                            this.addProgrammaCoalizione(idAzienda, programmaFedelta);
                            return this.getProgrammiAzienda().get(azienda).add(programmaFedelta);
                        }
                    }
                } else {
                    Set<ProgrammaFedelta> programmiAzienda = new HashSet<>();
                    programmiAzienda.add(programmaFedelta);
                    this.getProgrammiAzienda().put(azienda, programmiAzienda);
                    this.addProgrammaCoalizione(idAzienda, programmaFedelta);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateProgrammaAzienda(int idAzienda, int idProgrammaFedelta) {
        for (Azienda azienda : this.getProgrammiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgrammaFedelta) {
                        Set<ProgrammaFedelta> programmi = this.getProgrammiAzienda().get(azienda);
                        this.getProgrammiAzienda().put(azienda, programmi);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeProgrammaAzienda(int idAzienda, int idProgrammaFedelta) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta1 : this.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta1.getIdProgramma() == idProgrammaFedelta) {
                        this.coalizione.deleteAziendaProgramma(idAzienda, idProgrammaFedelta);
                        return this.getProgrammiAzienda().get(azienda).remove(programmaFedelta1);
                    }

                }
            }
        }
        return false;
    }

    public ProgrammaFedelta getProgrammaFedeltaById(int idAzienda, int idProgramma) {
        for (Azienda azienda : this.getProgrammiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgramma) {
                        return programmaFedelta;
                    }
                }
            }
        }
        return null;
    }

    public boolean addCouponPreconfiguratoAzienda(int idAzienda, Coupon couponPreconfigurato) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (this.getCouponPreconfiguratiAzienda().containsKey(azienda)) {
                    if (this.getCouponPreconfiguratiAzienda().get(azienda) == null) {
                        Set<Coupon> couponAzienda = new HashSet<>();
                        couponAzienda.add(couponPreconfigurato);
                        this.getCouponPreconfiguratiAzienda().put(azienda, couponAzienda);
                        return true;
                    } else {
                        return this.getCouponPreconfiguratiAzienda().get(azienda).add(couponPreconfigurato);
                    }
                } else {
                    Set<Coupon> couponAzienda = new HashSet<>();
                    couponAzienda.add(couponPreconfigurato);
                    this.getCouponPreconfiguratiAzienda().put(azienda, couponAzienda);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean updateCouponPreconfiguratoAzienda(int idAzienda, int idCoupon, Coupon coupondUpdated) {
        for (Azienda azienda : this.getCouponPreconfiguratiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Coupon coupon : this.getCouponPreconfiguratiAzienda().get(azienda)) {
                    if (coupon.getIdCoupon() == idCoupon) {
                        coupon.setValoreSconto(coupondUpdated.getValoreSconto());
                        coupon.setDataScadenza(coupondUpdated.getDataScadenza());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeCouponPreconfiguratoAzienda(int idAzienda, int idCoupon) {
        for (Azienda azienda : this.getCouponPreconfiguratiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Coupon coupon : this.getCouponPreconfiguratiAzienda().get(azienda)) {
                    if (coupon.getIdCoupon() == idCoupon) {
                        return this.getCouponPreconfiguratiAzienda().get(azienda).remove(coupon);
                    }
                }
            }
        }
        return false;
    }

    public boolean addConfigurazioneSMS(int idAzienda, SMS smsPreconfigurato) {
        for(Azienda azienda : this.getSMSPreconfiguratiAzienda().keySet()){
            if(azienda.getIdAzienda() == idAzienda){
                if(this.getSMSPreconfiguratiAzienda().containsKey(azienda)){
                    if(this.getSMSPreconfiguratiAzienda().get(azienda) == null){
                        Set<SMS> smsAzienda = new HashSet<>();
                        smsAzienda.add(smsPreconfigurato);
                        this.getSMSPreconfiguratiAzienda().put(azienda, smsAzienda);
                        return true;
                    }else {
                        return this.getSMSPreconfiguratiAzienda().get(azienda).add(smsPreconfigurato);
                    }
                }else {
                    Set<SMS> smsAzienda = new HashSet<>();
                    smsAzienda.add(smsPreconfigurato);
                    this.getSMSPreconfiguratiAzienda().put(azienda,smsAzienda);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateConfigurazioneSMS(int idAzienda, int idConfigurazione, SMS smsPreconfigurato) {
        for(Azienda azienda : this.getSMSPreconfiguratiAzienda().keySet()){
            if(azienda.getIdAzienda() == idAzienda){
                for(SMS smsAzienda : this.getSMSPreconfiguratiAzienda().get(azienda)){
                    if(smsAzienda.getIdSMS() == idConfigurazione){
                        smsAzienda.setTesto(smsPreconfigurato.getTesto());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeConfigurazioneSMS(int idAzienda, int idConfigurazione) {
        //Todo implementare
        for(Azienda azienda : this.getSMSPreconfiguratiAzienda().keySet()){
            if(azienda.getIdAzienda() == idAzienda){
                for(SMS smsAzienda : this.getSMSPreconfiguratiAzienda().get(azienda)){
                    if(smsAzienda.getIdSMS() == idConfigurazione){
                        this.getSMSPreconfiguratiAzienda().get(azienda).remove(smsAzienda);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //--OPERAZIONI CLIENTE--

    public boolean addCliente(Cliente cliente) {
        return this.clientiIscritti.add(cliente);
    }

    public boolean updateCliente(int idCliente, Cliente clienteUpdated) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                cliente.setEmail(clienteUpdated.getEmail());
                cliente.setTelefono(clienteUpdated.getTelefono());
                cliente.setTessera(clienteUpdated.getTessera());
                return true;
            }
        }
        return false;
    }

    public boolean removeCliente(Cliente cliente) {
        for (Cliente cliente1 : this.getClientiIscritti()) {
            if (cliente1.getIdCliente() == cliente.getIdCliente()) {
                return this.getClientiIscritti().remove(cliente1);
            }
        }
        return false;
    }

    public Cliente identificaCliente(String nome, String cognome) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (Objects.equals(cliente.getNome(), nome) && Objects.equals(cliente.getCognome(), cognome)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente identificaClienteTessera(int idTessera) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (cliente.getTessera().getIdTessera() == idTessera) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente identificaClienteCodice(int idCliente) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public Tessera getTesseraCliente(int idCliente) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente.getTessera();
            }
        }
        return null;
    }

    public Map<Cliente, Set<SMS>> getSMSCliente() {
        return this.SMSCliente;
    }


    public Set<SMS> getSMSClienteById(int idCliente) {
        for (Cliente cliente : this.getSMSCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.getSMSCliente().get(cliente);
            }
        }
        return null;
    }

    public Map<Cliente, Set<Visita>> getVisiteCliente() {
        return this.visiteCliente;
    }

    public Set<Visita> getVisiteClienteById(int idCliente) {
        for (Cliente cliente : this.getVisiteCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.getVisiteCliente().get(cliente);
            }
        }
        return null;
    }

    public Map<Cliente, Set<Premio>> getPremiCliente() {
        return this.premiCliente;
    }


    public Set<Premio> getPremiClienteById(int idCliente) {
        for (Cliente cliente : this.getPremiCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.getPremiCliente().get(cliente);
            }
        }
        return null;
    }

    public Map<Cliente, Set<Coupon>> getCouponCliente() {
        return this.couponCliente;
    }


    public Set<Coupon> getCouponClienteById(int idCliente) {
        for (Cliente cliente : this.getCouponCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.getCouponCliente().get(cliente);
            }
        }
        return null;
    }


    public boolean addSMS(int idCliente, SMS sms) {
        for (Cliente cliente : this.getClientiIscritti()) {
            if (cliente.getIdCliente() == idCliente) {
                if (this.getSMSCliente().containsKey(cliente)) {
                    if (this.getSMSCliente().get(cliente) == null) {
                        Set<SMS> smsCliente = new HashSet<>();
                        smsCliente.add(sms);
                        this.getSMSCliente().put(cliente, smsCliente);
                        return true;
                    } else {
                        return this.getSMSCliente().get(cliente).add(sms);
                    }
                } else {
                    Set<SMS> smsCliente = new HashSet<>();
                    smsCliente.add(sms);
                    this.getSMSCliente().put(cliente, smsCliente);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean addCoupon(int idAzienda, int idCliente, int idCoupon) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Cliente cliente : this.getClientiIscritti()) {
                    if (cliente.getIdCliente() == idCliente) {
                        for (Coupon coupon : this.getCouponPreconfiguratiAzienda().get(azienda)) {
                            if (coupon.getIdCoupon() == idCoupon) {
                                if (this.getCouponCliente().containsKey(cliente)) {
                                    if (this.getCouponCliente().get(cliente) == null) {
                                        Set<Coupon> couponCliente = new HashSet<>();
                                        couponCliente.add(coupon);
                                        this.getCouponCliente().put(cliente, couponCliente);
                                    } else {
                                        return this.getCouponCliente().get(cliente).add(coupon);
                                    }
                                } else {
                                    Set<Coupon> couponCliente = new HashSet<>();
                                    couponCliente.add(coupon);
                                    this.getCouponCliente().put(cliente, couponCliente);
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean updateCoupon(Cliente cliente, int idCoupon, Coupon couponUpdated) {
        for (Cliente cliente1 : this.getCouponCliente().keySet()) {
            if (cliente1.equals(cliente)) {
                for (Coupon coupon : this.getCouponCliente().get(cliente)) {
                    if (coupon.getIdCoupon() == idCoupon) {
                        coupon.setValoreSconto(couponUpdated.getValoreSconto());
                        coupon.setDataScadenza(couponUpdated.getDataScadenza());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeCoupon(int idCliente, int idCoupon) {
        for (Cliente cliente : this.getCouponCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Coupon coupon : this.getCouponCliente().get(cliente)) {
                    if (coupon.getIdCoupon() == idCoupon) {
                        return this.getCouponCliente().get(cliente).remove(coupon);
                    }
                }
            }
        }
        return false;
    }


    public boolean addPremioCliente(int idCliente, Premio premio) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getIdCliente() == idCliente) {
                if (!premiCliente.containsKey(cliente)) {
                    Set<Premio> premiCliente = new HashSet<>();
                    premiCliente.add(premio);
                    this.premiCliente.put(cliente, premiCliente);
                    return true;
                } else {
                    this.premiCliente.get(cliente).add(premio);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updatePremioCliente(Cliente cliente, int idPremio, Premio premioUpdated) {
        for (Cliente cliente1 : this.getPremiCliente().keySet()) {
            if (cliente.equals(cliente1)) {
                for (Premio premio : this.getPremiCliente().get(cliente)) {
                    if (premio.getIdPremio() == idPremio) {
                        premio.setNome(premioUpdated.getNome());
                        premio.setLivelli(premioUpdated.getLivelli());
                        premio.setPunti(premioUpdated.getPunti());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removePremioCliente(int idCliente, int idPremio) {
        for (Cliente cliente : this.getPremiCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Premio premio : this.getPremiCliente().get(cliente)) {
                    if (premio.getIdPremio() == idPremio) {
                        this.getPremiCliente().get(cliente).remove(premio);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Visita getVisitaById(int idCliente, int idVisita) {
        for (Cliente cliente : this.getVisiteCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.getVisiteCliente().get(cliente)) {
                    if (visita.getIdVisita() == idVisita) {
                        return visita;
                    }
                }
            }
        }
        return null;
    }

    public boolean addVisita(int idCliente, Visita visita) {
        for(Cliente cliente : this.getClientiIscritti()){
            if(cliente.getIdCliente() == idCliente){
                if(this.getVisiteCliente().containsKey(cliente)){
                    if(this.getVisiteCliente().get(cliente) == null){
                        Set<Visita> visite = new HashSet<>();
                        visite.add(visita);
                        this.getVisiteCliente().put(cliente, visite);
                    }else{
                        return this.getVisiteCliente().get(cliente).add(visita);
                    }
            }else{
                    Set<Visita> visite = new HashSet<>();
                    visite.add(visita);
                    this.getVisiteCliente().put(cliente, visite);
                }
                return true;
            }
        }
        return false;
    }

    public boolean updateVisita(int idCliente, int idVisita, Visita visitaUpdated) {
        for (Cliente cliente : this.getVisiteCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.getVisiteCliente().get(cliente)) {
                    if (visita.getIdVisita() == idVisita) {
                        visita.setLuogo(visitaUpdated.getLuogo());
                        visita.setData(visitaUpdated.getData());
                        visita.setCompletata(visitaUpdated.isCompletata());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeVisita(int idCliente, int idVisita) {
        for (Cliente cliente : this.getVisiteCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.getVisiteCliente().get(cliente)) {
                    if (visita.getIdVisita() == idVisita) {
                        return this.getVisiteCliente().get(cliente).remove(visita);
                    }
                }
            }
        }
        return false;
    }

    public boolean addTessera(Tessera tessera) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getIdCliente() == tessera.getIdCliente()) {
                if (cliente.getTessera() == null) {
                    if (this.tessereClienti.isEmpty()) {
                        cliente.setTessera(tessera);
                        return this.tessereClienti.add(tessera);
                    } else {
                        for (Tessera toScroll : this.tessereClienti) {
                            if (!toScroll.equals(tessera)) {
                                cliente.setTessera(tessera);
                                return this.tessereClienti.add(tessera);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Tessera getTesseraById(int idTessera) {
        for (Tessera tessera : this.getTessereClienti()) {
            if (tessera.getIdTessera() == idTessera) {
                return tessera;
            }
        }
        return null;
    }


    public boolean addClienteProgramma(int idCliente, ProgrammaFedelta programmaFedelta){
        Tessera tessera =  this.getTesseraCliente(idCliente);
        if(tessera != null){
            if(tessera.getProgrammiFedelta().isEmpty())
                return tessera.addPogrammaFedelta(programmaFedelta) && this.addClienteCoalizione(tessera.getIdCliente(), programmaFedelta);
            else {
                for (Map.Entry<Azienda, Set<ProgrammaFedelta>> entry : this.getProgrammiAzienda().entrySet()) {
                    for (ProgrammaFedelta program : entry.getValue()) {
                        if (program.equals(programmaFedelta)) {
                            for (ProgrammaFedelta toScroll : tessera.getProgrammiFedelta()) {
                                if (!toScroll.equals(program)) {
                                    return tessera.addPogrammaFedelta(programmaFedelta) && this.addClienteCoalizione(tessera.getIdCliente(), programmaFedelta);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public boolean deleteClienteProgramma(int idCliente, ProgrammaFedelta programmaFedelta){
        Tessera tessera = this.getTesseraCliente(idCliente);
        if (tessera != null) {
            if (!tessera.getProgrammiFedelta().isEmpty()) {
                for (ProgrammaFedelta program : tessera.getProgrammiFedelta()) {
                    if (program.equals(programmaFedelta)) {
                        return this.deleteClienteCoalizione(tessera.getIdCliente(), programmaFedelta) && tessera.deleteProgrammaFedelta(programmaFedelta);
                    }
                }
            }
        }
        return false;
    }





}

