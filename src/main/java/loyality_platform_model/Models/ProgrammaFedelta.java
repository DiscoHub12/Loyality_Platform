package loyality_platform_model.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public interface ProgrammaFedelta {

    int getIdProgramma();
    String getNome();

    Date getDataAttivazione();

    Tipo getTipoProgramma();

}
