package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.ProgrammaFedelta;
import loyality_platform_model.Models.ProgrammaLivelli;
import loyality_platform_model.Models.ProgrammaPunti;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * This class is a loyalty program manager, so it is responsible for viewing the details
 * of a given loyalty program, adding, editing and removing a company's loyalty program.
 */
public class HandlerProgrammaFedelta {

    private final DBMS dbms;

    public HandlerProgrammaFedelta() {
        this.dbms = DBMS.getInstance();
    }

    public DBMS getDbms() {
        return dbms;
    }

    /**
     * This method returns the details of a specific loyalty program.
     * @param idAzienda   company that has the program.
     * @param idProgramma program to search.
     * @return the details of a specific loyalty program.
     */
    public String getDetailsProgrammaFedelta(int idAzienda, int idProgramma) {
        if (idProgramma < 1 || idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        for (Azienda azienda : this.getDbms().getAziendeIscritte()) {
            if (azienda.getIdAzienda() == idAzienda) {
                for (ProgrammaFedelta programmaFedelta : this.getDbms().getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgramma) {
                        return programmaFedelta.toString();
                    }
                    throw new IllegalArgumentException("Program not exists.");
                }
                throw new IllegalArgumentException("Company not exists.");
            }
        }
        return null;
    }

    /**
     * This method adds a points program to the company under consideration.
     *
     * @param idAzienda      considered company.
     * @param nome           name of the program.
     * @param numeroPuntiMax maximum number of points that can be obtained (if there is no maximum number it is zero).
     * @param puntiVip       points to become a vip.
     * @param puntiSpesa     points that are given with a total amount spent.
     * @param importoSpesa   amount to spend to get points.
     */
    public void aggiungiProgrammaPunti(int idAzienda, String nome, int numeroPuntiMax, int puntiVip, int puntiSpesa, double importoSpesa) {
        if (idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        ProgrammaPunti toAdd = creaProgrammaPunti(nome, numeroPuntiMax, puntiVip, puntiSpesa, importoSpesa);
        for(Azienda azienda : this.getDbms().getAziendeIscritte()){
            if(azienda.getIdAzienda() == idAzienda){
                for(ProgrammaFedelta programmaFedelta : this.getDbms().getProgrammiAzienda().get(azienda)){
                    if(!programmaFedelta.getProgrammaPunti().equals(toAdd)){
                        this.getDbms().addProgrammaAzienda(azienda, toAdd);
                    }
                    throw new IllegalArgumentException("Program already exists.");
                }
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }

    /**
     * This method adds a levels program to the company under consideration.
     *
     * @param idAzienda      considered company.
     * @param nome           name of the program.
     * @param massimoLivelli maximum number of levels that can be obtained
     * @param livelloVip     points to become a vip.
     * @param map            the set of levels that can be reached and the relative points associated with each level.
     * @param puntiSpesa     points that are given with a total amount spent.
     * @param importoSpesa   amount to spend to get points.
     */
    public void aggiungiProgrammaLivelli(int idAzienda, String nome, int massimoLivelli, int livelloVip, Map<Integer, Integer> map, int puntiSpesa, double importoSpesa) {
        if (idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        ProgrammaLivelli toAdd = creaProgrammaLivelli(nome, massimoLivelli, livelloVip, map, puntiSpesa, importoSpesa);
        for(Azienda azienda : this.getDbms().getAziendeIscritte()){
            if(azienda.getIdAzienda() == idAzienda){
                for(ProgrammaFedelta programmaFedelta: this.getDbms().getProgrammiAzienda().get(azienda)){
                    if(!programmaFedelta.getProgrammaLivelli().equals(toAdd)){
                        this.getDbms().addProgrammaAzienda(azienda, toAdd);
                    }
                    throw new IllegalArgumentException("Program already exists.");
                }
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }

    /**
     * This method modifies a selected points program type loyalty program.
     * @param idAzienda company that owns the loyalty program.
     * @param idProgramma loyalty program to edit.
     * @param nome new name.
     * @param numeroPuntiMax new maximum number of points.
     * @param puntiVip new points to become a vip.
     * @param puntiSpesa new points that are given with a total amount spent.
     * @param importoSpesa new amount to spend to get points.
     */
    public void modificaProgrammaPunti(int idAzienda, int idProgramma, String nome, Date dataAttivazione, int numeroPuntiMax, int puntiVip, int puntiSpesa, double importoSpesa) {
        if (idProgramma < 1 || idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        for(Azienda azienda : this.getDbms().getAziendeIscritte()){
            if(azienda.getIdAzienda() == idAzienda){
                for (ProgrammaFedelta programmaFedelta : this.getDbms().getProgrammiAzienda().get(azienda)) {
                    if (programmaFedelta.getIdProgramma() == idProgramma) {
                            setNomePP(nome, programmaFedelta);
                            setDataAttivazionePP(dataAttivazione, programmaFedelta);
                            setNumeroMaxPuntiPP(numeroPuntiMax, programmaFedelta);
                            setPuntiVipPP(puntiVip, programmaFedelta);
                            setPuntiSpesaPP(puntiSpesa, programmaFedelta);
                            setImportoSpesaPP(importoSpesa, programmaFedelta);
                            this.getDbms().updateProgrammaAzienda(azienda, programmaFedelta);
                    }
                    throw new IllegalArgumentException("Program not exists.");
                }
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }

    /**
     * This method modifies a selected levels program type loyalty program.
     * @param idAzienda company that owns the loyalty program.
     * @param idProgramma loyalty program to edit.
     * @param nome new name.
     * @param massimoLivelli new maximum number of levels.
     * @param livelloVip new levels to become a vip.
     * @param puntiSpesa new points that are given with a total amount spent.
     * @param importoSpesa new amount to spend to get points.
     */
    public void modificaProgrammaLivelli(int idAzienda, int idProgramma, String nome, Date dataAttivazione,int massimoLivelli, int livelloVip, int puntiSpesa, double importoSpesa, boolean updatePolicyLivelli,
                                         boolean addLevel, int pointsNewLevel, boolean removeLevel, int levelToRemove, boolean updatePointsLevel, int levelToUpdate, int newPoints) {
        if (idProgramma < 1 || idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        for(Azienda azienda: this.getDbms().getAziendeIscritte()){
            if(azienda.getIdAzienda() == idAzienda){
                for(ProgrammaFedelta programmaFedelta : this.getDbms().getProgrammiAzienda().get(azienda)){
                    if(programmaFedelta.getIdProgramma() == idProgramma){
                        setNomePL(nome, programmaFedelta);
                        setDataAttivazionePL(dataAttivazione, programmaFedelta);
                        setMassimoLivelliPL(massimoLivelli, programmaFedelta);
                        setLivelloVipPL(livelloVip, programmaFedelta);
                        setPuntiSpesaPL(puntiSpesa, programmaFedelta);
                        setImportoSpesaPL(importoSpesa, programmaFedelta);
                        if(updatePolicyLivelli){
                            updatePolicyPL(programmaFedelta, addLevel, pointsNewLevel, removeLevel, levelToRemove, updatePointsLevel, levelToUpdate, newPoints);
                        }
                        this.getDbms().updateProgrammaAzienda(azienda, programmaFedelta);
                    }
                    throw new IllegalArgumentException("Program not exists.");
                }
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }


    /**
     * This method removes a particular loyalty program within the company.
     *
     * @param idAzienda        considered company.
     * @param programmaFedelta loyalty program to be removed.
     */
    public void rimuoviProgrammaFedelta(int idAzienda, ProgrammaFedelta programmaFedelta) {
        Objects.requireNonNull(programmaFedelta);
        if (idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        for(Azienda azienda : this.getDbms().getAziendeIscritte()){
            if(azienda.getIdAzienda() == idAzienda){
                for(ProgrammaFedelta programmaFedelta1: this.getDbms().getProgrammiAzienda().get(azienda)){
                    if(programmaFedelta1.equals(programmaFedelta)){
                        this.getDbms().removeProgrammaAzienda(azienda, programmaFedelta);
                    }
                    throw new IllegalArgumentException("Program not presents.");
                }
            }
            throw new IllegalArgumentException("Company not exists.");
        }
    }

    /**
     * This method creates a new points program.
     *
     * @param nome           name of the program.
     * @param numeroPuntiMax maximum number of points that can be obtained (if there is no maximum number it is zero).
     * @param puntiVip       points to become a vip.
     * @param puntiSpesa     points that are given with a total amount spent.
     * @param importoSpesa   amount to spend to get points.
     * @return a new points program.
     */
    private ProgrammaPunti creaProgrammaPunti(String nome, int numeroPuntiMax, int puntiVip, int puntiSpesa, double importoSpesa) {
        return new ProgrammaPunti(nome, numeroPuntiMax, puntiVip, puntiSpesa, importoSpesa);
    }

    /**
     * This method creates a new levels program.
     *
     * @param nome           name of the program.
     * @param massimoLivelli maximum number of levels that can be reached.
     * @param livelloVip     levels to become a vip.
     * @param map            the set of levels that can be reached and the relative points associated with each level.
     * @param puntiSpesa     points that are given with a total amount spent.
     * @param importoSpesa   amount to spend to get points.
     * @return a new levels program.
     */
    private ProgrammaLivelli creaProgrammaLivelli(String nome, int massimoLivelli, int livelloVip, Map<Integer, Integer> map, int puntiSpesa, double importoSpesa) {
        return new ProgrammaLivelli(nome, massimoLivelli, livelloVip, map, puntiSpesa, importoSpesa);
    }

    /**
     * This method changes the name of the selected loyalty program.
     * @param nome new name.
     * @param programmaFedelta selected loyalty program.
     */
    private void setNomePP(String nome, ProgrammaFedelta programmaFedelta){
        ProgrammaPunti pp = programmaFedelta.getProgrammaPunti();
        if(!Objects.equals(nome, pp.getNome()))
            pp.setNome(nome);
    }

    /**
     * This method changes the activation date of the selected points program.
     * @param dataAttivazione new activation date.
     * @param programmaFedelta selected loyalty program.
     */
    private void setDataAttivazionePP(Date dataAttivazione, ProgrammaFedelta programmaFedelta){
        ProgrammaPunti pp = programmaFedelta.getProgrammaPunti();
        if(dataAttivazione != pp.getDataAttivazione())
            pp.setDataAttivazione(dataAttivazione);
    }

    /**
     * This method changes the maximum number of points of the selected loyalty program.
     * @param numeroMaxPunti new maximum number of points.
     * @param programmaFedelta selected loyalty program.
     */
    private void setNumeroMaxPuntiPP(int numeroMaxPunti, ProgrammaFedelta programmaFedelta){
        ProgrammaPunti pp = programmaFedelta.getProgrammaPunti();
        if(numeroMaxPunti != pp.getNumeroPuntiMassimi())
            pp.setNumeroPuntiMassimi(numeroMaxPunti);
    }

    /**
     * This method modifies the number of points to become vip of the selected points program.
     * @param puntiVip new number of points to become vip.
     * @param programmaFedelta selected loyalty program.
     */
    private void setPuntiVipPP(int puntiVip, ProgrammaFedelta programmaFedelta){
        ProgrammaPunti pp = programmaFedelta.getProgrammaPunti();
        if(puntiVip != pp.getPuntiVIP())
            pp.setPuntiVIP(puntiVip);
    }

    /**
     * This method modifies the number of points you get for each total purchase
     * of the selected points program.
     * @param puntiSpesa new points you get.
     * @param programmaFedelta selected loyalty program.
     */
    private void setPuntiSpesaPP(int puntiSpesa, ProgrammaFedelta programmaFedelta){
        ProgrammaPunti pp = programmaFedelta.getProgrammaPunti();
        if(puntiSpesa != pp.getPuntiSpesa())
            pp.setPuntiSpesa(puntiSpesa);
    }

    /**
     * This method changes the amount you spend to earn points in the selected points program.
     * @param importoSpesa new amount you spend to earn points.
     * @param programmaFedelta selected loyalty program.
     */
    private void setImportoSpesaPP(double importoSpesa, ProgrammaFedelta programmaFedelta){
        ProgrammaPunti pp = programmaFedelta.getProgrammaPunti();
        if(importoSpesa != pp.getImportoSpesa())
            pp.setImportoSpesa(importoSpesa);
    }

    /**
     * This method changes the name of the selected loyalty program.
     * @param nome new name.
     * @param programmaFedelta selected loyalty program.
     */
    private void setNomePL(String nome, ProgrammaFedelta programmaFedelta){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(!Objects.equals(nome, pl.getNome()))
            pl.setNome(nome);
    }

    /**
     * This method changes the activation date of the selected levels program.
     * @param dataAttivazione new activation date.
     * @param programmaFedelta selected loyalty program.
     */
    private void setDataAttivazionePL(Date dataAttivazione, ProgrammaFedelta programmaFedelta){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(dataAttivazione != pl.getDataAttivazione())
            pl.setDataAttivazione(dataAttivazione);
    }

    /**
     * This method changes the maximum number of levels of the selected loyalty program.
     * @param massimoLivelli new maximum number of levels.
     * @param programmaFedelta selected loyalty program.
     */
    private void setMassimoLivelliPL(int massimoLivelli, ProgrammaFedelta programmaFedelta){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(massimoLivelli != pl.getMassimoLivelli())
            pl.setMassimoLivelli(massimoLivelli);
    }

    /**
     * This method modifies the number of levels to become vip of the selected levels program.
     * @param livelloVip new number of levels to become vip.
     * @param programmaFedelta selected loyalty program.
     */
    private void setLivelloVipPL(int livelloVip, ProgrammaFedelta programmaFedelta){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(livelloVip != pl.getLivelloVip())
            pl.setLivelloVip(livelloVip);
    }

    /**
     * This method modifies the number of points you get for each total purchase
     * of the selected levels program.
     * @param puntiSpesa new number of points you get.
     * @param programmaFedelta selected loyalty program.
     */
    private void setPuntiSpesaPL(int puntiSpesa, ProgrammaFedelta programmaFedelta){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(puntiSpesa != pl.getPuntiSpesa())
            pl.setPuntiSpesa(puntiSpesa);
    }

    /**
     * This method changes the amount you spend to earn points in the selected levels program.
     * @param importoSpesa new amount you spend to earn points.
     * @param programmaFedelta selected loyalty program.
     */
    private void setImportoSpesaPL(double importoSpesa, ProgrammaFedelta programmaFedelta){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(importoSpesa != pl.getImportoSpesa())
            pl.setImportoSpesa(importoSpesa);
    }

    private void updatePolicyPL(ProgrammaFedelta programmaFedelta, boolean addLevel, int pointsNewLevel, boolean removeLevel, int levelToRemove, boolean updatePointsLevel, int levelToUpdate, int newPoints){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(addLevel){
            pl.addLivello(pointsNewLevel);
        } else if (removeLevel) {
            pl.removeLivello(levelToRemove);
        }else if(updatePointsLevel){
            pl.updatePuntiLivello(levelToUpdate, newPoints);
        }
    }

}
