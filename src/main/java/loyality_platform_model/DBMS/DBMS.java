package loyality_platform_model.DBMS;

import ch.qos.logback.core.net.server.Client;
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

    public Set<Azienda> getAziendeIscritte() {
        return this.aziendeIscritte;
    }

    public boolean addAzienda(Azienda azienda) {
        this.aziendeIscritte.add(azienda);
        return true;
    }

    public boolean updateAzienda(int idAzienda, Azienda aziendaNew) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                azienda.setTitolare(aziendaNew.getTitolare());
                azienda.setSpazioFedelta(aziendaNew.getSpazioFedelta());
                azienda.setCatalogoPremi(aziendaNew.getCatalogoPremi());
                return true;
            }
        }
        return false;
    }

    public boolean removeAzienda(Azienda azienda) {
        for (Azienda azienda1 : this.aziendeIscritte) {
            if (azienda1.equals(azienda)) {
                this.aziendeIscritte.remove(azienda);
                return true;
            }
        }
        return false;
    }

    public GestorePuntoVendita getTitolareAzienda(int idAzienda) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getTitolare();
            }
        }
        return null;
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
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getSpazioFedelta();
            }
        }
        return null;
    }

    public boolean modificaSpazioFedeltà(int idAzienda, SpazioFedelta spazioFedeltaNew) {
        for (Azienda azienda : this.aziendeIscritte) {
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

    public Set<ProgrammaFedelta> getProgrammiFedeltaAzienda(int idAzienda) {
        for (Azienda azienda : this.programmiAzienda.keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.programmiAzienda.get(azienda);
            }
        }
        return null;
    }

    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                return azienda.getCatalogoPremi();
            }
        }
        return null;
    }

    public Set<Cliente> getClientiAzienda(int idAzienda) {
        //Tood implementare
        return null;
    }

    public Cliente identificaCliente(String nome, String cognome) {
        for (Cliente cliente : this.clientiIscritti) {
            if (Objects.equals(cliente.getNome(), nome) && Objects.equals(cliente.getCognome(), cognome)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente identificaClienteTessera(int idTessera) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getTessera().getIdTessera() == idTessera) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente identificaClienteCodice(int idCliente) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }


    public Tessera getTesseraCliente(int idCliente) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente.getTessera();
            }
        }
        return null;
    }

    public Set<SMS> getSMSClienteById(int idCliente) {
        for (Cliente cliente : this.getSMSCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.getSMSCliente().get(cliente);
            }
        }
        return null;
    }

    public Set<Visita> getVisiteClienteById(int idCliente) {
        for (Cliente cliente : this.getVisiteCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.getVisiteCliente().get(cliente);
            }
        }
        return null;
    }

    public Set<Premio> getPremiClienteById(int idCliente) {
        for (Cliente cliente : this.premiCliente.keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.premiCliente.get(cliente);
            }
        }
        return null;
    }

    public Set<Coupon> getCouponClienteById(int idCliente) {
        for (Cliente cliente : this.getCouponCliente().keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                return this.couponCliente.get(cliente);
            }
        }
        return null;
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


    public Map<Azienda, Set<Dipendente>> getDipendentiAzienda() {
        return this.dipendentiAzienda;
    }

    public boolean addDipendente(int idAzienda, Dipendente dipendente) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (this.dipendentiAzienda.containsKey(azienda)) {
                    if (this.dipendentiAzienda.get(azienda) == null) {
                        Set<Dipendente> dipendentiAzienda = new HashSet<>();
                        dipendentiAzienda.add(dipendente);
                        this.dipendentiAzienda.put(azienda, dipendentiAzienda);
                        return true;
                    } else {
                        this.dipendentiAzienda.get(azienda).add(dipendente);
                        return true;
                    }
                } else {
                    Set<Dipendente> dipendentiAzienda = new HashSet<>();
                    dipendentiAzienda.add(dipendente);
                    this.dipendentiAzienda.put(azienda, dipendentiAzienda);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateDipendente(int idAzienda, int idDipendente, String email, boolean restrizioni) {
        for (Azienda azienda : this.dipendentiAzienda.keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente : this.dipendentiAzienda.get(azienda)) {
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
        for (Azienda azienda : this.dipendentiAzienda.keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Dipendente dipendente1 : this.dipendentiAzienda.get(azienda)) {
                    if (dipendente1.getIdDipendente() == idDipendente) {
                        this.dipendentiAzienda.get(azienda).remove(dipendente1);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Set<Coupon> getCouponPreconfiguratiAzienda(int idAzienda) {
        for (Azienda azienda : this.couponPreconfiguratiAzienda.keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                return this.couponPreconfiguratiAzienda.get(azienda);
            }
        }
        return null;
    }


    public Map<Azienda, Set<ProgrammaFedelta>> getProgrammiAzienda() {
        return this.programmiAzienda;
    }

    public boolean addProgrammaAzienda(int idAzienda, ProgrammaFedelta programmaFedelta) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (this.programmiAzienda.containsKey(azienda)) {
                    this.programmiAzienda.get(azienda).add(programmaFedelta);
                } else {
                    Set<ProgrammaFedelta> programmiAzienda = new HashSet<>();
                    programmiAzienda.add(programmaFedelta);
                    this.programmiAzienda.put(azienda, programmiAzienda);
                }
                return true;
            }
        }
        return false;
    }

    public boolean updateProgrammaAzienda(int idAzienda, int idProgrammaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        for (Azienda azienda : this.programmiAzienda.keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.programmiAzienda.get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgrammaFedelta) {
                        if (programmaFedelta.getProgrammaPunti() != null) {
                            updateProgrammaPunti(programmaFedelta, programmaFedeltaUpdated);
                            return true;
                        } else if (programmaFedelta.getProgrammaLivelli() != null) {
                            updateProgrammaLivelli(programmaFedelta, programmaFedeltaUpdated);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean updateProgrammaPunti(ProgrammaFedelta programmaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        programmaFedelta.getProgrammaPunti().setNome(programmaFedeltaUpdated.getProgrammaPunti().getNome());
        programmaFedelta.getProgrammaPunti().setDataAttivazione(programmaFedeltaUpdated.getProgrammaPunti().getDataAttivazione());
        programmaFedelta.getProgrammaPunti().setNumeroPuntiMassimi(programmaFedeltaUpdated.getProgrammaPunti().getNumeroPuntiMassimi());
        programmaFedelta.getProgrammaPunti().setPuntiVIP(programmaFedeltaUpdated.getProgrammaPunti().getPuntiVIP());
        programmaFedelta.getProgrammaPunti().setPuntiSpesa(programmaFedeltaUpdated.getProgrammaPunti().getPuntiSpesa());
        programmaFedelta.getProgrammaPunti().setImportoSpesa(programmaFedeltaUpdated.getProgrammaPunti().getImportoSpesa());
        return true;
    }

    private boolean updateProgrammaLivelli(ProgrammaFedelta programmaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        programmaFedelta.getProgrammaLivelli().setNome(programmaFedeltaUpdated.getProgrammaLivelli().getNome());
        programmaFedelta.getProgrammaLivelli().setDataAttivazione(programmaFedeltaUpdated.getProgrammaLivelli().getDataAttivazione());
        programmaFedelta.getProgrammaLivelli().setMassimoLivelli(programmaFedeltaUpdated.getProgrammaLivelli().getMassimoLivelli());
        programmaFedelta.getProgrammaLivelli().setLivelloVip(programmaFedeltaUpdated.getProgrammaLivelli().getLivelloVip());
        programmaFedelta.getProgrammaLivelli().setPolicyLivelli(programmaFedeltaUpdated.getProgrammaLivelli().getPolicyLivelli());
        programmaFedelta.getProgrammaLivelli().setPuntiSpesa(programmaFedeltaUpdated.getProgrammaLivelli().getPuntiSpesa());
        programmaFedelta.getProgrammaLivelli().setImportoSpesa(programmaFedeltaUpdated.getProgrammaLivelli().getImportoSpesa());
        return true;
    }

    public boolean removeProgrammaAzienda(int idAzienda, int idProgrammaFedelta) {
        for (Azienda azienda : this.getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta1 : this.getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta1.getIdProgramma() == idProgrammaFedelta) {
                        this.getProgrammiAzienda().get(azienda).remove(programmaFedelta1);
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public ProgrammaFedelta getProgrammaFedeltaById(int idAzienda, int idProgramma) {
        for (Azienda azienda : this.getProgrammiAzienda().keySet()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.programmiAzienda.get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgramma) {
                        return programmaFedelta;
                    }
                }
            }
        }
        return null;
    }

    public Map<Azienda, Set<Coupon>> getCouponPreconfiguratiAzienda() {
        return this.couponPreconfiguratiAzienda;
    }

    public boolean addCouponPreconfiguratoAzienda(int idAzienda, Coupon couponPreconfigurato) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                if (this.couponPreconfiguratiAzienda.containsKey(azienda)) {
                    if (this.couponPreconfiguratiAzienda.get(azienda) == null) {
                        Set<Coupon> couponAzienda = new HashSet<>();
                        couponAzienda.add(couponPreconfigurato);
                        this.couponPreconfiguratiAzienda.put(azienda, couponAzienda);
                        return true;
                    } else {
                        this.couponPreconfiguratiAzienda.get(azienda).add(couponPreconfigurato);
                        return true;
                    }
                } else {
                    Set<Coupon> couponAzienda = new HashSet<>();
                    couponAzienda.add(couponPreconfigurato);
                    this.couponPreconfiguratiAzienda.put(azienda, couponAzienda);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean updateCouponPreconfiguratoAzienda(int idAzienda, int idCoupon, Coupon coupondUpdated) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Coupon coupon : this.couponPreconfiguratiAzienda.get(azienda)) {
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
                for (Coupon coupon : this.couponPreconfiguratiAzienda.get(azienda)) {
                    if (coupon.getIdCoupon() == idCoupon) {
                        this.couponPreconfiguratiAzienda.get(azienda).remove(coupon);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Set<Cliente> getClientiIscritti() {
        return this.clientiIscritti;
    }

    public boolean addCliente(Cliente cliente) {
        this.clientiIscritti.add(cliente);
        return true;
    }

    public boolean updateCliente(int idCliente, Cliente clienteUpdated) {
        for (Cliente cliente : this.clientiIscritti) {
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
        for (Cliente cliente1 : this.clientiIscritti) {
            if (cliente1.getIdCliente() == cliente.getIdCliente()) {
                this.clientiIscritti.remove(cliente1);
                return true;
            }
        }
        return false;
    }

    public Map<Cliente, Set<SMS>> getSMSCliente() {
        return this.SMSCliente;
    }

    public boolean addSMS(int idCliente, SMS sms) {
        for (Cliente cliente : this.clientiIscritti) {
            if (cliente.getIdCliente() == idCliente) {
                if (this.SMSCliente.containsKey(cliente)) {
                    if (this.SMSCliente.get(cliente) == null) {
                        Set<SMS> smsCliente = new HashSet<>();
                        smsCliente.add(sms);
                        this.SMSCliente.put(cliente, smsCliente);
                        return true;
                    } else {
                        this.SMSCliente.get(cliente).add(sms);
                        return true;
                    }
                } else {
                    Set<SMS> smsCliente = new HashSet<>();
                    smsCliente.add(sms);
                    this.SMSCliente.put(cliente, smsCliente);
                    return true;
                }
            }
        }
        return false;
    }


    public Map<Cliente, Set<Coupon>> getCouponCliente() {
        return this.couponCliente;
    }

    public boolean addCoupon(int idAzienda, int idCliente, int idCoupon) {
        for (Azienda azienda : this.aziendeIscritte) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (Cliente cliente : this.clientiIscritti) {
                    if (cliente.getIdCliente() == idCliente) {
                        for (Coupon coupon : this.couponPreconfiguratiAzienda.get(azienda)) {
                            if (coupon.getIdCoupon() == idCoupon) {
                                if (this.couponCliente.containsKey(cliente)) {
                                    if (this.couponCliente.get(cliente) == null) {
                                        Set<Coupon> couponCliente = new HashSet<>();
                                        couponCliente.add(coupon);
                                        this.couponCliente.put(cliente, couponCliente);
                                    } else {
                                        this.couponCliente.get(cliente).add(coupon);
                                    }
                                } else {
                                    Set<Coupon> couponCliente = new HashSet<>();
                                    couponCliente.add(coupon);
                                    this.couponCliente.put(cliente, couponCliente);
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
        for (Coupon coupon : this.couponCliente.get(cliente)) {
            if (coupon.getIdCoupon() == idCoupon) {
                coupon.setValoreSconto(couponUpdated.getValoreSconto());
                coupon.setDataScadenza(couponUpdated.getDataScadenza());
                return true;
            }
        }
        return false;
    }

    public boolean removeCoupon(int idCliente, int idCoupon) {
        for (Cliente cliente : this.couponCliente.keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Coupon coupon : this.couponCliente.get(cliente)) {
                    if (coupon.getIdCoupon() == idCoupon) {
                        this.couponCliente.get(cliente).remove(coupon);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Map<Cliente, Set<Premio>> getPremiCliente() {
        return premiCliente;
    }

    public boolean addPremioCliente(int idAzienda, int idCliente, int idPremio) {
        return false;
    }

    public boolean updatePremioCliente(Cliente cliente, int idPremio, Premio premioUpdated) {
        for (Premio premio : this.premiCliente.get(cliente)) {
            if (premio.getIdPremio() == idPremio) {
                premio.setNome(premioUpdated.getNome());
                premio.setLivelli(premioUpdated.getLivelli());
                premio.setPunti(premioUpdated.getPunti());
                return true;
            }
        }
        return false;
    }

    public boolean removePremioCliente(int idCliente, int idPremio) {
        for (Cliente cliente : this.premiCliente.keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Premio premio : this.premiCliente.get(cliente)) {
                    if (premio.getIdPremio() == idPremio) {
                        this.premiCliente.get(cliente).remove(premio);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Map<Cliente, Set<Visita>> getVisiteCliente() {
        return visiteCliente;
    }

    public void addVisita(int idCliente, Visita visita) {
        for (Cliente cliente : this.visiteCliente.keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                if (this.visiteCliente.containsKey(cliente)) {
                    if (this.visiteCliente.get(cliente) == null) {
                        Set<Visita> visiteCliente = new HashSet<>();
                        visiteCliente.add(visita);
                        this.visiteCliente.put(cliente, visiteCliente);
                    } else {
                        this.visiteCliente.get(cliente).add(visita);
                    }
                } else {
                    Set<Visita> visiteCliente = new HashSet<>();
                    visiteCliente.add(visita);
                    this.visiteCliente.put(cliente, visiteCliente);
                }
            }
        }
    }

    public boolean updateVisita(int idCliente, int idVisita, Visita visitaUpdated) {
        for (Cliente cliente : this.visiteCliente.keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.visiteCliente.get(cliente)) {
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
        for (Cliente cliente : this.visiteCliente.keySet()) {
            if (cliente.getIdCliente() == idCliente) {
                for (Visita visita : this.visiteCliente.get(cliente)) {
                    if (visita.getIdVisita() == idVisita) {
                        this.visiteCliente.get(cliente).remove(visita);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Set<Tessera> getTessereClienti() {
        return tessereClienti;
    }

    public void addTessera(Tessera tessera) {
        this.tessereClienti.add(tessera);
    }

    public boolean updateTessera(int idTessera, Tessera tesseraUpdated) {
        for (Tessera tessera : this.tessereClienti) {
            if (tessera.getIdTessera() == idTessera) {

                return true;
            }
        }
        return false;
    }


    public Set<ProgrammaFedelta> getProgrammiDisponibili() {
        return programmiDisponibili;
    }


    public Set<PacchettoSMS> getPacchettiSMS() {
        return this.pacchettiSMS;
    }

    public Set<Abbonamento> getAbbonamenti() {
        return this.abbonamenti;
    }

    public Map<Azienda, Set<SMS>> getSMSPreconfiguratiAzienda() {
        return this.SMSPreconfiguratiAzienda;
    }


    //HANDLER PROGRAMMA FEDELTA:
}

