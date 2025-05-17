package mrbet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimeTest {

    private Time time;

    @BeforeEach
    public void setUp() {
        time = new Time("1", "Time A", "Mascote A");
    }

    @Test
    public void testCriarTimeComParametrosValidos() {
        assertEquals("1", time.getId());
        assertEquals("Time A", time.getNome());
        assertEquals("Mascote A", time.getMascote());
    }

    @Test
    public void testCriarTimeComIdInvalido() {
    	IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> new Time("", "Time A", "Mascote A"));
        assertEquals("CÓDIGO DO TIME NÃO DEVE SER NULO OU VAZIO!", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new Time(null, "Time A", "Mascote A"));
        assertEquals("CÓDIGO DO TIME NÃO DEVE SER NULO OU VAZIO!", exception2.getMessage());
    }

    @Test
    public void testCriarTimeComNomeInvalido() {
    	IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> new Time("1", "", "Mascote A"));
        assertEquals("NOME DO TIME NÃO DEVE SER NULO OU VAZIO!", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new Time("1", null, "Mascote A"));
        assertEquals("NOME DO TIME NÃO DEVE SER NULO OU VAZIO!", exception2.getMessage());
    }

    @Test
    public void testCriarTimeComMascoteInvalido() {
    	IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> new Time("1", "Time A", ""));
        assertEquals("MASCOTE DO TIME NÃO DEVE SER NULO OU VAZIO", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new Time("1", "Time A", null));
        assertEquals("MASCOTE DO TIME NÃO DEVE SER NULO OU VAZIO", exception2.getMessage());
    }

    @Test
    public void testCompararTimesComMesmoId() {
        Time time2 = new Time("1", "Time B", "Mascote B");
        assertTrue(time.equals(time2));
    }

    @Test
    public void testCompararTimesComIdDiferente() {
        Time time2 = new Time("2", "Time B", "Mascote B");
        assertFalse(time.equals(time2));
    }

    @Test
    public void testHashCodeComMesmoId() {
        Time time2 = new Time("1", "Time B", "Mascote B");
        assertEquals(time.hashCode(), time2.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("[1] Time A / Mascote A", time.toString());
    }
    
    @Test
    public void testTimesComIdIgual() {
        Time time2 = new Time("1", "Time A", "Mascote A");
        assertTrue(time.equals(time2));
        assertEquals(time.hashCode(), time2.hashCode());
    }
    
    @Test
    public void testCompararTimesComMesmoNomeEId() {
        Time time2 = new Time("1", "Time A", "Mascote A");
        Time time3 = time;
        assertTrue(time.equals(time2));
        assertTrue(time.equals(time3));
    }

    @Test
    public void testCompararTimesComIdNomeIguaisMasMascoteDiferente() {
        Time time2 = new Time("1", "Time A", "Mascote B");
        assertTrue(time.equals(time2));
    }

    @Test
    public void testCompararTimesComIdsDiferentesENomesIguais() {
        Time time2 = new Time("2", "Time A", "Mascote B");
        assertFalse(time.equals(time2));
    }
}