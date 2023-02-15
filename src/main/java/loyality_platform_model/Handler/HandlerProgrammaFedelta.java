package loyality_platform_model.Handler;

import loyality_platform_model.DBMS.DBMS;
import loyality_platform_model.Models.Azienda;
import loyality_platform_model.Models.ProgrammaFedelta;
import loyality_platform_model.Models.ProgrammaLivelli;
import loyality_platform_model.Models.ProgrammaPunti;
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
                }
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
    public boolean aggiungiProgrammaPunti(int idAzienda, String nome, String dataAttivazione, int numeroPuntiMax, int puntiVip, int puntiSpesa, double importoSpesa) {
        if (idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        ProgrammaPunti toAdd = creaProgrammaPunti(nome, dataAttivazione, numeroPuntiMax, puntiVip, puntiSpesa, importoSpesa);
        return this.getDbms().addProgrammaAzienda(idAzienda, toAdd);
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
    public boolean aggiungiProgrammaLivelli(int idAzienda, String nome, String dataAttivazione, int massimoLivelli, int livelloVip, Map<Integer, Integer> map, int puntiSpesa, double importoSpesa) {
        if (idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        ProgrammaLivelli toAdd = creaProgrammaLivelli(nome, dataAttivazione,massimoLivelli, livelloVip, map, puntiSpesa, importoSpesa);
        return this.getDbms().addProgrammaAzienda(idAzienda, toAdd);
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
    public boolean modificaProgrammaPunti(int idAzienda, int idProgramma, String nome, String dataAttivazione, int numeroPuntiMax, int puntiVip, int puntiSpesa, double importoSpesa) {
        if (idProgramma < 1 || idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        ProgrammaPunti pp = new ProgrammaPunti(nome, dataAttivazione, numeroPuntiMax, puntiVip, puntiSpesa, importoSpesa);
        return this.getDbms().updateProgrammaAzienda(idAzienda, idProgramma, pp);
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
    public boolean modificaProgrammaLivelli(int idAzienda, int idProgramma, String nome, String dataAttivazione,int massimoLivelli, int livelloVip, int puntiSpesa, double importoSpesa,
                                            boolean addLevel, int pointsNewLevel, boolean removeLevel, int levelToRemove, boolean updatePointsLevel, int levelToUpdate, int newPoints) {
        if (idProgramma < 1 || idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        Map<Integer, Integer> newMap = updatePolicyPL(programmaFedelta, addLevel, pointsNewLevel, removeLevel, levelToRemove, updatePointsLevel, levelToUpdate, newPoints);
        ProgrammaLivelli pl = new ProgrammaLivelli(nome, dataAttivazione, massimoLivelli, livelloVip, newMap, puntiSpesa, importoSpesa);
        return this.getDbms().updateProgrammaAzienda(idAzienda, idProgramma, pl);
    }


    /**
     * This method removes a particular loyalty program within the company.
     *
     * @param idAzienda        considered company.
     * @param programmaFedelta loyalty program to be removed.
     */
    public boolean rimuoviProgrammaFedelta(int idAzienda, int idProgramma) {
        if (idAzienda < 1)
            throw new IllegalArgumentException("Id not correct");
        return this.getDbms().removeProgrammaAzienda(idAzienda, idProgramma);
    }

    /**
     * This method creates a new points program.
     *
     * @param nome name of the program.
     * @param dataAttivazione date of activation program.
     * @param numeroPuntiMax maximum number of points that can be obtained (if there is no maximum number it is zero).
     * @param puntiVip       points to become a vip.
     * @param puntiSpesa     points that are given with a total amount spent.
     * @param importoSpesa   amount to spend to get points.
     * @return a new points program.
     */
    private ProgrammaPunti creaProgrammaPunti(String nome, String dataAttivazione, int numeroPuntiMax, int puntiVip, int puntiSpesa, double importoSpesa) {
        return new ProgrammaPunti(nome, dataAttivazione, numeroPuntiMax, puntiVip, puntiSpesa, importoSpesa);
    }

    /**
     * This method creates a new levels program.
     *
     * @param nome           name of the program.
     * @param dataAttivazione date of activation program.
     * @param massimoLivelli maximum number of levels that can be reached.
     * @param livelloVip     levels to become a vip.
     * @param map            the set of levels that can be reached and the relative points associated with each level.
     * @param puntiSpesa     points that are given with a total amount spent.
     * @param importoSpesa   amount to spend to get points.
     * @return a new levels program.
     */
    private ProgrammaLivelli creaProgrammaLivelli(String nome, String dataAttivazione, int massimoLivelli, int livelloVip, Map<Integer, Integer> map, int puntiSpesa, double importoSpesa) {
        return new ProgrammaLivelli(nome, dataAttivazione, massimoLivelli, livelloVip, map, puntiSpesa, importoSpesa);
    }


    /**
     * This method allows you to go and change the policies on the levels of a specific levels program.
     * @param programmaFedelta selected loyalty program.
     * @param addLevel <code>true</code> if you want to add a new level, <code>false</code> otherwise.
     * @param pointsNewLevel number of points for the new level.
     * @param removeLevel <code>true</code> if you want to remove a level, <code>false</code> otherwise.
     * @param levelToRemove  number of levels to remove.
     * @param updatePointsLevel <code>true</code> if you want to change points of a level, <code>false</code> otherwise.
     * @param levelToUpdate  number of levels to update.
     * @param newPoints number of points for the level selected.
     */
    private Map<Integer, Integer> updatePolicyPL(ProgrammaFedelta programmaFedelta, boolean addLevel, int pointsNewLevel, boolean removeLevel, int levelToRemove, boolean updatePointsLevel, int levelToUpdate, int newPoints){
        ProgrammaLivelli pl = programmaFedelta.getProgrammaLivelli();
        if(addLevel){
            pl.addLivello(pointsNewLevel);
        } else if (removeLevel) {
            pl.removeLivello(levelToRemove);
        }else if(updatePointsLevel){
            pl.updatePuntiLivello(levelToUpdate, newPoints);
        }
        return programmaFedelta.getProgrammaLivelli().getPolicyLivelli();
    }
}
