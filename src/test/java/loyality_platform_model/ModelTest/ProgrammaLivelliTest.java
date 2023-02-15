package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.CatalogoPremi;
import loyality_platform_model.Models.Premio;
import loyality_platform_model.Models.ProgrammaLivelli;
import loyality_platform_model.Models.Tipo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class ProgrammaLivelliTest {

    @Test
    public void testClass(){
        Map<Integer, Integer> policy = new HashMap<>();
        policy.put(1, 5);
        policy.put(2, 10);
        policy.put(3, 20);
        policy.put(4, 30);
        ProgrammaLivelli programmaLivelli = new ProgrammaLivelli("ProgrammaL", "12-02-2023",4,3, policy, 5, 10);
        System.out.println(programmaLivelli.getIdProgramma());
        assertEquals(0, programmaLivelli.getIdProgramma());
        ProgrammaLivelli programmaLivelli1 = new ProgrammaLivelli("ProvaL","12-02-2023", 6, 4, policy, 3, 6);
        System.out.println(programmaLivelli1.getIdProgramma());
        assertEquals(1, programmaLivelli1.getIdProgramma());
        ProgrammaLivelli returnedProgrammaLivelli = programmaLivelli.getProgrammaLivelli();
        System.out.println("Programma originale " +  programmaLivelli);
        System.out.println("Programma ritornato " + returnedProgrammaLivelli);
        assertEquals(programmaLivelli, returnedProgrammaLivelli);
        assertNull(programmaLivelli.getProgrammaPunti());


        assertEquals("ProgrammaL", programmaLivelli.getNome());
        assertEquals(0, programmaLivelli.getIdProgramma());
        System.out.println(programmaLivelli);
        programmaLivelli.setNome("Programmaa");
        assertEquals("Programmaa", programmaLivelli.getNome());
        System.out.println("Nome nuovo" + programmaLivelli.getNome());
        assertEquals(Tipo.PROGRAMMALIVELLI, programmaLivelli.getTipoProgramma());
        assertEquals(4, programmaLivelli.getMassimoLivelli());
        assertEquals(3, programmaLivelli.getLivelloVip());
        assertEquals(policy, programmaLivelli.getPolicyLivelli());
        assertEquals(5, programmaLivelli.getPuntiSpesa());
        assertEquals(10, programmaLivelli.getImportoSpesa());
        assertNull(programmaLivelli.getCatalogoPremi());
        programmaLivelli.setLivelloVip(4);
        assertEquals(4,programmaLivelli.getLivelloVip());
        programmaLivelli.setPuntiSpesa(10);
        programmaLivelli.setImportoSpesa(20);
        assertEquals(10, programmaLivelli.getPuntiSpesa());
        assertEquals(20, programmaLivelli.getImportoSpesa());
        System.out.println(programmaLivelli);
        programmaLivelli.setMassimoLivelli(5);
        programmaLivelli.addLivello(50);
        policy.put(5, 50);
        assertEquals(policy, programmaLivelli.getPolicyLivelli());
        System.out.println("Nuova map" + programmaLivelli.getPolicyLivelli());
        programmaLivelli.updatePuntiLivello(5, 70);
        policy.replace(5, 70);
        assertEquals(policy, programmaLivelli.getPolicyLivelli());
        System.out.println("Map aggiornata: " + programmaLivelli.getPolicyLivelli());
        programmaLivelli.removeLivello(5);
        policy.remove(5);
        assertEquals(policy, programmaLivelli.getPolicyLivelli());
        System.out.println("Livello rimosso: "+ programmaLivelli.getPolicyLivelli());



        Premio premio = new Premio("Zaino", false, 4);
        Set<Premio> premi = new HashSet<>();
        premi.add(premio);
        CatalogoPremi catalogoPremi = new CatalogoPremi(premi);
        programmaLivelli.setCatalogoPremi(catalogoPremi);
        assertEquals(catalogoPremi, programmaLivelli.getCatalogoPremi());
        System.out.println(programmaLivelli);



        try{
            programmaLivelli.setMassimoLivelli(-1);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaLivelli.setLivelloVip(5);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaLivelli.setLivelloVip(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaLivelli.setPuntiSpesa(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaLivelli.setImportoSpesa(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaLivelli.addLivello(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaLivelli.removeLivello(7);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
}

