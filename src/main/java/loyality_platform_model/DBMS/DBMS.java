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

    private final Set<Azienda> aziendeIscritte;

    private final Map<Azienda, Set<Dipendente>> dipendentiAzienda;

    private final Map<Azienda, Set<ProgrammaFedelta>> programmiAzienda;

    private final Map<Azienda, Set<Coupon>> couponPreconfiguratiAzienda;

    private final Map<Azienda, Set<SMS>> SMSPreconfiguratiAzienda;

    private final Set<Cliente> clientiIscritti;

    private final Map<Cliente, Set<SMS>> SMSCliente;

    private final Map<Cliente, Set<Visita>> visiteCliente;

    private final Map<Cliente, Set<Coupon>> couponCliente;

    private final Map<Cliente, Set<Premio>> premiCliente;

    private final Set<Tessera> tessereClienti;

    private final Set<ProgrammaFedelta> programmiDisponibili;

    private final Set<PacchettoSMS> pacchettiSMS;

    private final Set<Abbonamento> abbonamenti;

    private Coalizione coalizione;


    private DBMS() {
        this.aziendeIscritte = new HashSet<>();
        this.dipendentiAzienda = new HashMap<>();
        this.programmiAzienda = new HashMap<>();
        this.couponPreconfiguratiAzienda = new HashMap<>();
        this.clientiIscritti = new HashSet<>();
        this.SMSCliente = new HashMap<>();
        this.couponCliente = new HashMap<>();
        this.premiCliente = new HashMap<>();
        this.visiteCliente = new HashMap<>();
        this.tessereClienti = new HashSet<>();
        this.programmiDisponibili = new HashSet<>();
        this.pacchettiSMS = new HashSet<>();
        this.abbonamenti = new HashSet<>();
        this.SMSPreconfiguratiAzienda = new HashMap<>();
    }

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
        return false;
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

    //--OPERAZIONI AZIENDA--

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

    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getCatalogoPremi();
            }
        }
        return null;
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
                        return true;
                    } else {
                        for (ProgrammaFedelta programmaFedelta1 : this.getProgrammiAzienda().get(azienda)) {
                            if (programmaFedelta1.equals(programmaFedelta))
                                return false;
                            return this.getProgrammiAzienda().get(azienda).add(programmaFedelta);
                        }
                    }
                } else {
                    Set<ProgrammaFedelta> programmiAzienda = new HashSet<>();
                    programmiAzienda.add(programmaFedelta);
                    this.getProgrammiAzienda().put(azienda, programmiAzienda);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateProgrammaAzienda(int idAzienda, int idProgrammaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        for (Azienda azienda : this.getProgrammiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgrammaFedelta) {
                        if (programmaFedelta.getProgrammaPunti() != null) {
                            updateProgrammaPunti(programmaFedelta, programmaFedeltaUpdated);
                            return true;
                        } else if (programmaFedelta.getProgrammaLivelli() != null) {
                            updateProgrammaLivelli(programmaFedelta, programmaFedeltaUpdated);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void updateProgrammaPunti(ProgrammaFedelta programmaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        programmaFedelta.getProgrammaPunti().setNome(programmaFedeltaUpdated.getProgrammaPunti().getNome());
        programmaFedelta.getProgrammaPunti().setDataAttivazione(programmaFedeltaUpdated.getProgrammaPunti().getDataAttivazione());
        programmaFedelta.getProgrammaPunti().setNumeroPuntiMassimi(programmaFedeltaUpdated.getProgrammaPunti().getNumeroPuntiMassimi());
        programmaFedelta.getProgrammaPunti().setPuntiVIP(programmaFedeltaUpdated.getProgrammaPunti().getPuntiVIP());
        programmaFedelta.getProgrammaPunti().setPuntiSpesa(programmaFedeltaUpdated.getProgrammaPunti().getPuntiSpesa());
        programmaFedelta.getProgrammaPunti().setImportoSpesa(programmaFedeltaUpdated.getProgrammaPunti().getImportoSpesa());
    }

    private void updateProgrammaLivelli(ProgrammaFedelta programmaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        programmaFedelta.getProgrammaLivelli().setNome(programmaFedeltaUpdated.getProgrammaLivelli().getNome());
        programmaFedelta.getProgrammaLivelli().setDataAttivazione(programmaFedeltaUpdated.getProgrammaLivelli().getDataAttivazione());
        programmaFedelta.getProgrammaLivelli().setMassimoLivelli(programmaFedeltaUpdated.getProgrammaLivelli().getMassimoLivelli());
        programmaFedelta.getProgrammaLivelli().setLivelloVip(programmaFedeltaUpdated.getProgrammaLivelli().getLivelloVip());
        programmaFedelta.getProgrammaLivelli().setPolicyLivelli(programmaFedeltaUpdated.getProgrammaLivelli().getPolicyLivelli());
        programmaFedelta.getProgrammaLivelli().setPuntiSpesa(programmaFedeltaUpdated.getProgrammaLivelli().getPuntiSpesa());
        programmaFedelta.getProgrammaLivelli().setImportoSpesa(programmaFedeltaUpdated.getProgrammaLivelli().getImportoSpesa());
    }

    public boolean removeProgrammaAzienda(int idAzienda, int idProgrammaFedelta) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta1 : this.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta1.getIdProgramma() == idProgrammaFedelta) {
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
                        coupon.setDataAttivazione(coupondUpdated.getDataAttivazione());
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


    //--OPERAZIONI CLIENTE--

    public boolean addCliente(Cliente cliente) {
        return this.getClientiIscritti().add(cliente);
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


    public boolean addPremioCliente(int idAzienda, int idCliente, int idPremio) {
        //TODO implementare
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
                        return this.getPremiCliente().get(cliente).remove(premio);
                    }
                }
            }
        }
        return false;
    }

    public boolean addVisita(int idCliente, Visita visita) {
        for (Cliente cliente : this.getVisiteCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                if (this.getVisiteCliente().containsKey(cliente)) {
                    if (this.getVisiteCliente().get(cliente) == null) {
                        Set<Visita> visiteCliente = new HashSet<>();
                        visiteCliente.add(visita);
                        this.getVisiteCliente().put(cliente, visiteCliente);
                        return true;
                    } else {
                        return this.getVisiteCliente().get(cliente).add(visita);
                    }
                } else {
                    Set<Visita> visiteCliente = new HashSet<>();
                    visiteCliente.add(visita);
                    this.getVisiteCliente().put(cliente, visiteCliente);
                    return true;
                }
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
        for (Tessera toScroll : this.getTessereClienti()) {
            if (!tessera.equals(toScroll)) {
                for (Cliente cliente : this.getClientiIscritti()) {
                    if (cliente.getIdCliente() == tessera.getIdCliente()) {
                        if (cliente.getTessera() == null)
                            cliente.setTessera(tessera);
                    }
                    this.getClientiIscritti().add(cliente);
                    return this.getTessereClienti().add(tessera);
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


    //TODO rivedere, per me non serve
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
}

