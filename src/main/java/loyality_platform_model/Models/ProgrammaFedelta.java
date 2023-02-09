package loyality_platform_model.Models;

/**
 * This interface represents a generic loyalty program characterized by a name,
 * an activation date and a type.
 */

import java.util.Date;

public interface ProgrammaFedelta {

    /**
     * This method returns the id of the program.
     * @return the id of the program.
     */
     int getIdProgramma();

    /**
     * This method returns the name of the program.
     * @return the name of the program.
     */
    String getNome();

    /**
     * This method sets the name of the program.
     * @param nome name to assigned.
     */
    void setNome(String nome);

    /**
     * This method returns the date of activation of the program.
     * @return the date of activation of the program.
     */
    Date getDataAttivazione();

    /**
     * This method sets the date of activation of the program.
     * @param dataAttivazione the date of activation of the program.
     */
    void setDataAttivazione(Date dataAttivazione);

    /**
     * This method returns the type of the program.
     * @return the type of the program.
     */
    Tipo getTipoProgramma();

    /**
     * This method, if the program is a points program, returns the corresponding points program.
     * @return the corresponding points program.
     */
    ProgrammaPunti getProgrammaPunti();

    /**
     * This method, if the program is a levels program, returns the corresponding levels program.
     * @return the corresponding levels program.
     */
    ProgrammaLivelli getProgrammaLivelli();

}
