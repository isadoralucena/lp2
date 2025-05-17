package mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampeonatoTest {
	    
	private Time time1, time2, time3, time4;
    private Campeonato campeonato1, campeonato2;

    @BeforeEach
    public void setup() {
        time1 = new Time("230_PB", "Nacional de Patos", "Canário");
        time2 = new Time("232_RJ", "Fluminense", "Tricolor");
        time3 = new Time("234_SP", "São Paulo", "Paulista");
        time4 = new Time("240_PE", "Sport", "Leão");

        campeonato1 = new Campeonato("Brasileirão série A 2023", 3);
        campeonato2 = new Campeonato("Copa do Mundo 2022", 32);
    }
    
    @Test
    public void testConstrutorComNomeNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Campeonato(null, 5);
        });
        assertEquals("NOME DO CAMPEONATO NÃO PODE SER NULO OU VAZIO!", exception.getMessage());
    }
    
    @Test
    public void testConstrutorComNomeVazio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Campeonato("", 5);
        });
        assertEquals("NOME DO CAMPEONATO NÃO PODE SER NULO OU VAZIO!", exception.getMessage());
    }

    @Test
    public void testConstrutorComNumeroParticipantesMenorOuIgualZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Campeonato("Campeonato Inválido", 0);
        });
        assertEquals("NÚMERO DE PARTICIPANTES DEVE SER MAIOR QUE 0!", exception.getMessage());
    }

    @Test
    public void testAdicionarTimeNoCampeonato() {
        String resultado = campeonato1.adicionarTime(time1);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado);
        
        resultado = campeonato1.adicionarTime(time2);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado);
        
        resultado = campeonato1.adicionarTime(time3);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado);
    }

    @Test
    public void testAdicionarTimeJaRegistrado() {
        campeonato1.adicionarTime(time1);
        String resultado = campeonato1.adicionarTime(time1);
        assertEquals("O TIME JÁ ESTÁ NO CAMPEONATO!", resultado);
    }

    @Test
    public void testVerificarTimeNoCampeonato() {
        campeonato1.adicionarTime(time1);
        assertEquals("O TIME ESTÁ NO CAMPEONATO!", campeonato1.verificarTimeNoCampeonato(time1.getId()));
        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", campeonato1.verificarTimeNoCampeonato(time3.getId()));
    }

    @Test
    public void testEqualsCampeonatos() {
        Campeonato campeonato3 = new Campeonato("Brasileirão série A 2023", 10);
        assertTrue(campeonato1.equals(campeonato3));
        
        Campeonato campeonato4 = new Campeonato("Copa do Mundo 2022", 32);
        assertFalse(campeonato1.equals(campeonato4));
    }

    @Test
    public void testAdicionarTimeNoCampeonatoComParticipantesCheios() {
    	campeonato1.adicionarTime(time1);
    	campeonato1.adicionarTime(time2);
    	campeonato1.adicionarTime(time3);
        
        String resultado = campeonato1.adicionarTime(new Time("245_SE", "Santos", "Peixe"));
        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", resultado);
    }

    @Test
    public void testHashCode() {
        Campeonato campeonato3 = new Campeonato("Brasileirão série A 2023", 10);
        assertEquals(campeonato1.hashCode(), campeonato3.hashCode());
        
        Campeonato campeonato4 = new Campeonato("Copa do Mundo 2022", 32);
        assertNotEquals(campeonato1.hashCode(), campeonato4.hashCode());
    }
}
