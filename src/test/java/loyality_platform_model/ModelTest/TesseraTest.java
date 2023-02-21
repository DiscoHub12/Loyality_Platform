package loyality_platform_model.ModelTest;


import loyality_platform_model.Models.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TesseraTest {

    Cliente cliente = new Cliente("Mario", "Rossi", "000", "mario.rossi@gmmail.com", "password");


    @Test
    public void testClass() {
        Tessera tessera = new Tessera(cliente.getIdCliente());
        Tessera tessera1 = new Tessera(cliente.getIdCliente());
        System.out.println("Id tessera " + tessera.getIdTessera() + "Id tessera1 " + tessera1.getIdTessera());
        assertEquals(tessera.getIdCliente(), cliente.getIdCliente());
        assertEquals(0, tessera.getPunti());
        System.out.println("Numero punti " + tessera.getPunti());
        ProgrammaFedelta programmaFedelta = new ProgrammaPunti("Prova", "12-02-2023",100, 80, 1, 10, null);
        tessera.addPogrammaFedelta(programmaFedelta);
        Set<ProgrammaFedelta> programmaFedeltaSet = new HashSet<>();
        programmaFedeltaSet.add(programmaFedelta);
        assertEquals(programmaFedeltaSet, tessera.getProgrammiFedelta());
        System.out.println(programmaFedelta);
        programmaFedelta.setNome("Ciao");
        System.out.println(programmaFedelta);
        tessera.deleteProgrammaFedelta(programmaFedelta);
        programmaFedeltaSet.remove(programmaFedelta);
        assertEquals(programmaFedeltaSet, tessera.getProgrammiFedelta());
        System.out.println("Lista programmi Tessera :" + tessera.getProgrammiFedelta());
        tessera.addPunti(5);
        assertEquals(5, tessera.getPunti());
        System.out.println("Numero punti " + tessera.getPunti());
        tessera.deletePunti(2);
        tessera.addPogrammaFedelta(programmaFedelta);
        System.out.println("Lista programmi Tessera :" + tessera.getProgrammiFedelta());
        assertEquals(3, tessera.getPunti());
        System.out.println("Numero punti " + tessera.getPunti());

        try{
            tessera.addPogrammaFedelta(programmaFedelta);
            System.out.println(programmaFedeltaSet);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            tessera.deleteProgrammaFedelta(programmaFedelta);
            System.out.println("Lista programmi Tessera :" + tessera.getProgrammiFedelta());
            tessera.deleteProgrammaFedelta(programmaFedelta);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            tessera.addPunti(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            tessera.deletePunti(-1);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            tessera.deletePunti(4);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

}