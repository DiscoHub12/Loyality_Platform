package loyality_platform_model.ModelTest;

import loyality_platform_model.Models.CatalogoPremi;
import loyality_platform_model.Models.Premio;
import loyality_platform_model.Models.ProgrammaPunti;
import loyality_platform_model.Models.Tipo;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammaPuntiTest {

    @Test
    public void testClass() {
        ProgrammaPunti programmaPunti = new ProgrammaPunti("Programma0", 100,80, 5, 20);
        assertEquals("Programma0", programmaPunti.getNome());
        assertEquals(0, programmaPunti.getIdProgramma());
        assertEquals(Tipo.PROGRAMMAPUNTI, programmaPunti.getTipoProgramma());
        assertTrue(programmaPunti.isMaxPunti());
        assertNull(programmaPunti.getDataAttivazione());
        System.out.println(programmaPunti.getIdProgramma());
        System.out.println(programmaPunti);
        programmaPunti.setNome("Programma0.0");
        assertEquals("Programma0.0", programmaPunti.getNome());
        System.out.println(programmaPunti);
        assertEquals(100, programmaPunti.getNumeroPuntiMassimi());
        assertEquals(80, programmaPunti.getPuntiVIP());
        assertEquals(5, programmaPunti.getPuntiSpesa());
        assertEquals(20, programmaPunti.getImportoSpesa());
        programmaPunti.setPuntiVIP(60);
        assertEquals(60, programmaPunti.getPuntiVIP());
        programmaPunti.setPuntiSpesa(2);
        assertEquals(2, programmaPunti.getPuntiSpesa());
        programmaPunti.setImportoSpesa(10);
        assertEquals(10, programmaPunti.getImportoSpesa());
        assertNull(programmaPunti.getCatalogoPremi());
        assertEquals(programmaPunti, programmaPunti.getProgrammaPunti());
        assertNull(programmaPunti.getProgrammaLivelli());


        Premio premio = new Premio("Zaino", true, 20);
        Set<Premio> premi = new HashSet<>();
        premi.add(premio);
        CatalogoPremi catalogoPremi = new CatalogoPremi(premi);
        programmaPunti.setCatalogoPremi(catalogoPremi);
        assertEquals(catalogoPremi, programmaPunti.getCatalogoPremi());
        System.out.println(programmaPunti);



        try{
            programmaPunti.setPuntiVIP(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            programmaPunti.setPuntiVIP(101);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


        ProgrammaPunti programmaPunti1 = new ProgrammaPunti("Programma1", 0, 80, 5, 10);
        assertEquals(programmaPunti1, programmaPunti1.getProgrammaPunti());
        assertNull(programmaPunti1.getProgrammaLivelli());
        assertFalse(programmaPunti1.isMaxPunti());
        assertEquals(0, programmaPunti1.getNumeroPuntiMassimi());
        assertEquals(1, programmaPunti1.getIdProgramma());
        System.out.println(programmaPunti1);
        System.out.println(programmaPunti1.getIdProgramma());
        programmaPunti1.setNumeroPuntiMassimi(200);
        assertTrue(programmaPunti1.isMaxPunti());
        assertEquals(200, programmaPunti1.getNumeroPuntiMassimi());
        System.out.println(programmaPunti1);

        try{
            programmaPunti1.setNumeroPuntiMassimi(-1);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


    }
}
