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

    private final DBMS dbms;


    public HandlerAzienda(Azienda azienda) {
        Objects.requireNonNull(azienda);
        this.dbms = DBMS.getInstance();
    }

    public GestorePuntoVendita getTitolareAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    public Set<Dipendente> getDipendentiAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    public void creaDipendente(int idAzienda, String nome, String cognome, String email) {
        //Todo implementare
    }

    public void aggiungiDipendente(int idAzienda, String nome, String cognome, String email) {
        //Todo implementare
    }

    public void modificaDipendente(int idAzienda, String nome, String cognome, String email) {
        //Todo implementare
    }

    public void rimuoviDipendente(int idAzienda, Dipendente dipendente) {
        //Todo implementare
    }

    public SpazioFedelta getSpazioFedeltaAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    public void modificaSpazioFedelta(int idAzienda) {
        //Todo implementare
    }

    public Set<ProgrammaFedelta> getProgrammiFedeltaAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    public Set<CatalogoPremi> getCatalogoPremiAzienda(int idAzienda) {
        //Todo implementare
        return null;
    }

    public Set<Cliente> getClientiAzienda(int idAzienda, Coalizione coalizione) {
        //Todo implementare
        return null;
    }
}
