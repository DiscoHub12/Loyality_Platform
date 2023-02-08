package loyality_platform_model.Models;

/**
 * This interface represents a generic loyalty program characterized by a name,
 * an activation date and a type.
 */

import java.util.Date;

public interface ProgrammaFedelta {

     int getIdProgramma();

    String getNome();

    void setNome(String nome);

    Date getDataAttivazione();

    void setDataAttivazione(Date dataAttivazione);

    Tipo getTipoProgramma();

    String toString();

}
