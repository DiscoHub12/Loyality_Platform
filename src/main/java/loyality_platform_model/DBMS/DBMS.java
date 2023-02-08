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
     * This attribute rapresent the name of this DataBase.
     */
    private final String nameDb;

    /**
     * This attribute represents the list of (final) Clients
     * registered within the platform.
     */
    private final Set<Cliente> clientiIscritti;

    private final Set<ProgrammaFedelta> programmiDisponibili;

    private final Map<Azienda, Set<Dipendente>> dipendentiAzienda;

    private final Map<Azienda, Set<ProgrammaFedelta>> programmiAzienda;
    private final Map<Tessera, Cliente> tesseraCliente;

    public DBMS(String nameDb) {
        this.nameDb = nameDb;
        this.clientiIscritti = new HashSet<>();
        this.programmiDisponibili = new HashSet<>();
        this.dipendentiAzienda = new HashMap<>();
        this.programmiAzienda = new HashMap<>();
        this.tesseraCliente=new HashMap<>();
    }

    public static DBMS getInstance() {
        if (instance == null) {
            instance = new DBMS(getInstance().nameDb);
        }
        return instance;
    }

    public String getNameDb() {
        return nameDb;
    }

    public Set<Cliente> getClientiIscritti() {
        return this.clientiIscritti;
    }

    public void addCliente(Cliente cliente) {
        Objects.requireNonNull(cliente);
        if (clientiIscritti.contains(cliente))
            throw new IllegalArgumentException("Costumer already present.");
        this.clientiIscritti.add(cliente);
    }

    public void removeCliente(Cliente cliente) {
        Objects.requireNonNull(cliente);
        if (!clientiIscritti.contains(cliente))
            throw new IllegalArgumentException("Costumer does not exist.");
        this.clientiIscritti.remove(cliente);
    }

    public Set<ProgrammaFedelta> getProgrammiDisponibili() {
        return programmiDisponibili;
    }

    public Map<Azienda, Set<Dipendente>> getDipendentiAzienda() {
        return dipendentiAzienda;
    }

    public void addDipendenteAzienda(Azienda azienda, Dipendente dipendente){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(dipendente);
        for(Map.Entry<Azienda, Set<Dipendente>> entry : this.getDipendentiAzienda().entrySet()){
            if(azienda.equals(entry.getKey())){
                if(entry.getValue().contains(dipendente))
                    throw new IllegalArgumentException("User already exists.");
                entry.getValue().add(dipendente);
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }

    //Da rivedere
    public void modificaDipendenteAzienda(Dipendente olddipendente, Azienda azienda, Dipendente newDipendente){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(olddipendente);
        Objects.requireNonNull(newDipendente);
       for(Map.Entry<Azienda, Set<Dipendente>> entry : this.getDipendentiAzienda().entrySet()){
           if(azienda.equals(entry.getKey())){
               if(entry.getValue().contains(olddipendente)){
                   entry.getValue().remove(olddipendente);
                   entry.getValue().add(newDipendente);
               }
               throw new IllegalArgumentException("User not exists");
           }
           throw new IllegalArgumentException("Company not exists");
       }
    }

    public void removeDipendenteAzienda(Azienda azienda, Dipendente dipendente) {
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(dipendente);
        for (Map.Entry<Azienda, Set<Dipendente>> entry : this.getDipendentiAzienda().entrySet()) {
            if (azienda.equals(entry.getKey())) {
                if(!entry.getValue().contains(dipendente))
                    throw new IllegalArgumentException("Employee not exists.");
                entry.getValue().remove(dipendente);
            }
            throw new IllegalArgumentException("Company not exists");
        }
    }

    public Map<Azienda, Set<ProgrammaFedelta>> getProgrammiAzienda() {
        return programmiAzienda;
    }

    public void addProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(programmaFedelta);
        for(Map.Entry<Azienda, Set<ProgrammaFedelta>> entry : this.getProgrammiAzienda().entrySet()){
            if(azienda.equals(entry.getKey())){
                if(entry.getValue().contains(programmaFedelta))
                    throw new IllegalArgumentException("Program already exists.");
                entry.getValue().add(programmaFedelta);
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }

    public void modificaProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(programmaFedelta);
        //TODO implementare
    }

    public void removeProgrammaAzienda(Azienda azienda, ProgrammaFedelta programmaFedelta){
        Objects.requireNonNull(azienda);
        Objects.requireNonNull(programmaFedelta);
        for (Map.Entry<Azienda, Set<ProgrammaFedelta>> entry : this.getProgrammiAzienda().entrySet()) {
            if (azienda.equals(entry.getKey())) {
                if(!entry.getValue().contains(programmaFedelta))
                    throw new IllegalArgumentException("Program not exists.");
                entry.getValue().remove(programmaFedelta);
            }
            throw new IllegalArgumentException("Company not exists");
        }
    }
    public Map<Tessera, Cliente> getTesseraCliente() {
        return tesseraCliente;
    }
    public void addTessera(Tessera tessera, Cliente cliente) {
        Objects.requireNonNull(tessera);
        Objects.requireNonNull(cliente);
        for (Map.Entry<Tessera, Cliente> entry : this.getTesseraCliente().entrySet()) {
            if (tessera.equals(entry.getKey()) || cliente.equals(entry.getValue())) {
                throw new IllegalArgumentException("User already exists.");
            }
            else {
                tesseraCliente.put(tessera,cliente);
            }
        }
    }
    public void removeTessera(Tessera tessera, Cliente cliente){
        Objects.requireNonNull(tessera);
        Objects.requireNonNull(cliente);
        for (Map.Entry<Tessera, Cliente> entry : this.getTesseraCliente().entrySet()) {
            if (tessera.equals(entry.getKey()) || cliente.equals(entry.getValue())) {
                tesseraCliente.remove(tessera,cliente);
            }
            else{
                throw new IllegalArgumentException("Card not exists.");
            }
        }
    }
}
