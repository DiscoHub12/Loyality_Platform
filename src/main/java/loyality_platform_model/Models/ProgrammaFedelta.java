package loyality_platform_model.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ProgrammaFedelta <T>{

    private static int idProgrammaFedelta;

    private final String nome;

    private final Date dataAttivazione;

    private T tipoProgramma;

    public ProgrammaFedelta(String nome, T tipoProgramma){
        Objects.requireNonNull(tipoProgramma);
        idProgrammaFedelta++;
        this.nome = nome;
        this.dataAttivazione = new Date();
        checkProgramma(tipoProgramma);
    }

    public void checkProgramma(T tipoProgramma){
        if((tipoProgramma instanceof ProgrammaPunti) || (tipoProgramma instanceof ProgrammaLivelli))
            this.tipoProgramma = tipoProgramma;
        else throw new IllegalArgumentException("Illegal type of Loyality Program.");
    }

    public int getId(){
        return idProgrammaFedelta;
    }

    public String getNome(){
        return this.nome;
    }

    public Date getDataAttivazione(){
        return this.dataAttivazione;
    }

    public T getTipoProgramma(){
        return this.tipoProgramma;
    }

    private String getDetailsTipoProgramma(){
        String tmp = "";
        tmp += this.tipoProgramma.toString();
        return tmp;
    }

    private String dataAttivazione(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(this.dataAttivazione);
    }

    public String toString(){
        return "\n\tLOYALITY PROGRAM : " +
                "\nId Programma : " + idProgrammaFedelta +
                "\nNome Programma : " + this.nome +
                "\nData attivazione : " + dataAttivazione() +
                "\n\tDati Programma : " + getDetailsTipoProgramma();
    }
}
