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

    public void addAzienda(Azienda azienda) {
        this.aziendeIscritte.add(azienda);
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


    public Map<Azienda, Set<Dipendente>> getDipendentiAzienda() {
        return this.dipendentiAzienda;
    }

    public void addDipendente(Azienda azienda, Dipendente dipendente) {
        if (this.dipendentiAzienda.containsKey(azienda)) {
            if (this.dipendentiAzienda.get(azienda) == null) {
                Set<Dipendente> dipendentiAzienda = new HashSet<>();
                dipendentiAzienda.add(dipendente);
                this.dipendentiAzienda.put(azienda, dipendentiAzienda);
            } else {
                this.dipendentiAzienda.get(azienda).add(dipendente);
            }
        } else {
            Set<Dipendente> dipendentiAzienda = new HashSet<>();
            dipendentiAzienda.add(dipendente);
            this.dipendentiAzienda.put(azienda, dipendentiAzienda);
        }
    }

    public boolean updateDipendente(Azienda azienda, int idDipendente,String email, boolean restrizioni) {
        for (Dipendente dipendente : this.dipendentiAzienda.get(azienda)) {
            if (dipendente.getIdDipendente() == idDipendente) {
                dipendente.setEmail(email);
                dipendente.setRestrizioni(restrizioni);
                return true;
            }
        }
        return false;
    }

    public boolean removeDipendente(Azienda azienda, Dipendente dipendente) {
        for (Dipendente dipendente1 : this.dipendentiAzienda.get(azienda)) {
            if (dipendente1.getIdDipendente() == dipendente.getIdDipendente()) {
                this.dipendentiAzienda.get(azienda).remove(dipendente);
                return true;
            }
        }
        return false;
    }

    public Map<Azienda, Set<ProgrammaFedelta>> getProgrammiAzienda() {
        return this.programmiAzienda;
    }

    public void addProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta) {
        if (this.programmiAzienda.containsKey(azienda)) {
            if (this.programmiAzienda.get(azienda) == null) {
                Set<ProgrammaFedelta> programmiAzienda = new HashSet<>();
                programmiAzienda.add(programmaFedelta);
                this.programmiAzienda.put(azienda, programmiAzienda);
            } else {
                this.programmiAzienda.get(azienda).add(programmaFedelta);
            }
        } else {
            Set<ProgrammaFedelta> programmiAzienda = new HashSet<>();
            programmiAzienda.add(programmaFedelta);
            this.programmiAzienda.put(azienda, programmiAzienda);
        }
    }

    public boolean updateProgrammaAzienda(Azienda azienda, int idProgrammaFedelta, ProgrammaFedelta programmaFedeltaUpdated) {
        for (ProgrammaFedelta programmaFedelta : this.programmiAzienda.get(azienda)) {
            if (programmaFedelta.getIdProgramma() == idProgrammaFedelta) {
                programmaFedelta.setNome(programmaFedeltaUpdated.getNome());
                programmaFedelta.setCatalogoPremi(programmaFedeltaUpdated.getCatalogoPremi());
                programmaFedelta.setDataAttivazione(programmaFedeltaUpdated.getDataAttivazione());
                return true;
            }
        }
        return false;
    }

    public boolean removeProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta) {
        for (ProgrammaFedelta programmaFedelta1 : this.programmiAzienda.get(azienda)) {
            if (programmaFedelta1.getIdProgramma() == programmaFedelta.getIdProgramma()) {
                this.programmiAzienda.get(azienda).remove(programmaFedelta);
                return true;
            }
        }
        return false;
    }

    public Map<Azienda, Set<Coupon>> getCouponPreconfiguratiAzienda() {
        return this.couponPreconfiguratiAzienda;
    }

    public void addCouponPreconfiguratoAzienda(Azienda azienda, Coupon couponPreconfigurato) {
        if (this.couponPreconfiguratiAzienda.containsKey(azienda)) {
            if (this.couponPreconfiguratiAzienda.get(azienda) == null) {
                Set<Coupon> couponAzienda = new HashSet<>();
                couponAzienda.add(couponPreconfigurato);
                this.couponPreconfiguratiAzienda.put(azienda, couponAzienda);
            } else {
                this.couponPreconfiguratiAzienda.get(azienda).add(couponPreconfigurato);
            }
        } else {
            Set<Coupon> couponAzienda = new HashSet<>();
            couponAzienda.add(couponPreconfigurato);
            this.couponPreconfiguratiAzienda.put(azienda, couponAzienda);
        }
    }

    public boolean updateCouponPreconfiguratoAzienda(Azienda azienda, int idCoupon, Coupon coupondUpdated) {
        for (Coupon coupon : this.couponPreconfiguratiAzienda.get(azienda)) {
            if (coupon.getIdCoupon() == idCoupon) {
                coupon.setValoreSconto(coupondUpdated.getValoreSconto());
                coupon.setDataAttivazione(coupondUpdated.getDataAttivazione());
                coupon.setDataScadenza(coupondUpdated.getDataScadenza());
                return true;
            }
        }
        return false;
    }

    public boolean removeCouponPreconfiguratoAzienda(Azienda azienda, Coupon coupon) {
        for (Coupon coupon1 : this.getCouponPreconfiguratiAzienda().get(azienda)) {
            if (coupon1.getIdCoupon() == coupon.getIdCoupon()) {
                this.couponPreconfiguratiAzienda.get(azienda).remove(coupon);
                return true;
            }
        }
        return false;
    }

    public Set<Cliente> getClientiIscritti() {
        return this.clientiIscritti;
    }

    public void addCliente(Cliente cliente) {
        this.clientiIscritti.add(cliente);
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

    public void addSMS(Cliente cliente, SMS sms) {
        if (this.SMSCliente.containsKey(cliente)) {
            if (this.SMSCliente.get(cliente) == null) {
                Set<SMS> smsCliente = new HashSet<>();
                smsCliente.add(sms);
                this.SMSCliente.put(cliente, smsCliente);
            } else {
                this.SMSCliente.get(cliente).add(sms);
            }
        } else {
            Set<SMS> smsCliente = new HashSet<>();
            smsCliente.add(sms);
            this.SMSCliente.put(cliente, smsCliente);
        }
    }

    public Map<Cliente, Set<Coupon>> getCouponCliente() {
        return this.couponCliente;
    }

    public void addCoupon(Cliente cliente, Coupon coupon) {
        if(this.couponCliente.containsKey(cliente)){
            if(this.couponCliente.get(cliente) == null){
                Set<Coupon> couponCliente = new HashSet<>();
                couponCliente.add(coupon);
                this.couponCliente.put(cliente, couponCliente);
            }else {
                this.couponCliente.get(cliente).add(coupon);
            }
        }else {
            Set<Coupon> couponCliente = new HashSet<>();
            couponCliente.add(coupon);
            this.couponCliente.put(cliente, couponCliente);
        }
    }

    public boolean updateCoupon(Cliente cliente, int idCoupon, Coupon couponUpdated) {
        for(Coupon coupon : this.couponCliente.get(cliente)){
            if(coupon.getIdCoupon() == idCoupon){
                coupon.setValoreSconto(couponUpdated.getValoreSconto());
                coupon.setDataScadenza(couponUpdated.getDataScadenza());
                return true;
            }
        }
        return false;
    }

    public boolean removeCoupon(Cliente cliente, Coupon coupon) {
        for(Coupon coupon1 : this.couponCliente.get(cliente)){
            if(coupon1.getIdCoupon() == coupon.getIdCoupon()){
                this.couponCliente.get(cliente).remove(coupon);
                return true;
            }
        }
        return false;
    }


    public Map<Cliente, Set<Premio>> getPremiCliente() {
        return premiCliente;
    }

    public void addPremioCliente(Cliente cliente, Premio premio) {
        if(this.premiCliente.containsKey(cliente)){
            if(this.premiCliente.get(cliente) == null){
                Set<Premio> premiCliente = new HashSet<>();
                premiCliente.add(premio);
                this.premiCliente.put(cliente, premiCliente);
            }else {
                this.premiCliente.get(cliente).add(premio);
            }
        }else {
            Set<Premio> premiCliente = new HashSet<>();
            premiCliente.add(premio);
            this.premiCliente.put(cliente, premiCliente);
        }
    }

    public boolean updatePremioCliente(Cliente cliente, int idPremio, Premio premioUpdated) {
        for(Premio premio : this.premiCliente.get(cliente)){
            if(premio.getIdPremio() == idPremio){
                premio.setNome(premioUpdated.getNome());
                premio.setLivelli(premioUpdated.getLivelli());
                premio.setPunti(premioUpdated.getPunti());
                return true;
            }
        }
        return false;
    }

    public boolean removePremioCliente(Cliente cliente, Premio premio) {
        for(Premio premio1 : this.premiCliente.get(cliente)){
            if(premio1.getIdPremio() == premio.getIdPremio()){
                this.premiCliente.get(cliente).remove(premio);
                return true;
            }
        }
        return false;
    }

    public Map<Cliente, Set<Visita>> getVisiteCliente() {
        return visiteCliente;
    }

    public void addVisita(Cliente cliente, Visita visita) {
        if(this.visiteCliente.containsKey(cliente)){
            if(this.visiteCliente.get(cliente) == null){
                Set<Visita> visiteCliente = new HashSet<>();
                visiteCliente.add(visita);
                this.visiteCliente.put(cliente, visiteCliente);
            }else {
                this.visiteCliente.get(cliente).add(visita);
            }
        }else {
            Set<Visita> visiteCliente = new HashSet<>();
            visiteCliente.add(visita);
            this.visiteCliente.put(cliente, visiteCliente);
        }
    }

    public boolean updateVisita(Cliente cliente, int idVisita, Visita visitaUpdated) {
        for(Visita visita : this.visiteCliente.get(cliente)){
            if(visita.getIdVisita() == idVisita){
                visita.setLuogo(visitaUpdated.getLuogo());
                visita.setData(visitaUpdated.getData());
                visita.setCompletata(visitaUpdated.isCompletata());
                return true;
            }
        }
        return false;
    }

    public boolean removeVisita(Cliente cliente, Visita visita) {
        for(Visita visita1 : this.visiteCliente.get(cliente)){
            if(visita1.getIdVisita() == visita.getIdVisita()){
                this.visiteCliente.get(cliente).remove(visita1);
                return true;
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
        for(Tessera tessera : this.tessereClienti){
            if(tessera.getIdTessera() == idTessera){
                tessera.setVipLivelli(tesseraUpdated.isVipLivelli());
                tessera.setVipPunti(tesseraUpdated.isVipPunti());
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
}

