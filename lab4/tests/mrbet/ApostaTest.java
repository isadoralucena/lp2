package mrbet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApostaTest {
	private Time time;
    private Campeonato campeonato;
    
    @BeforeEach
    public void setup() {
        this.time = new Time("230_PB", "Nacional de Patos", "Canário");
        this.campeonato = new Campeonato("Brasileirão série A 2023", 5);
    }
    
    @Test
    public void testConstrutorComParametrosValidos() {
        Aposta aposta = new Aposta(this.time, campeonato, 1, 100.0);
        assertNotNull(aposta);
        assertEquals(time, aposta.getTime());
        assertEquals(campeonato, aposta.getCampeonato());
        assertEquals(1, aposta.getColocacao());
        assertEquals(100.0, aposta.getValor(), 0.0001);
    }

    @Test
    public void testConstrutorComTimeNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Aposta(null, campeonato, 1, 100.0);
        });

        assertEquals("TIME NÃO PODE SER NULO!", exception.getMessage());
    }

    @Test
    public void testConstrutorComCampeonatoNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Aposta(time, null, 1, 100.0);
        });

        assertEquals("CAMPEONATO NÃO PODE SER NULO!", exception.getMessage());
    }

    @Test
    public void testConstrutorComColocacaoInvalida() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Aposta(time, campeonato, 0, 100.0);
        });

        assertEquals("COLOCAÇÃO DEVE SER MAIOR QUE 0!", exception.getMessage());
    }


    @Test
    public void testConstrutorComValorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Aposta(time, campeonato, 1, 0.0);
        });

        assertEquals("VALOR DA APOSTA DEVE SER MAIOR QUE 0!", exception.getMessage());
    }


    @Test
    public void testGetters() {
        Aposta aposta = new Aposta(time, campeonato, 1, 100.0);
        assertEquals(time, aposta.getTime());
        assertEquals(campeonato, aposta.getCampeonato());
        assertEquals(1, aposta.getColocacao());
        assertEquals(100.0, aposta.getValor(), 0.0001);
    }

    @Test
    public void testToString() {
        Aposta aposta = new Aposta(time, campeonato, 1, 100.0);
        String esperado = time.toString() + "\n" + campeonato.getNome() + "\n" +
                campeonato.getTimesCadastrados() + "/" + campeonato.getNumeroParticipantes() + "\nR$ " + 
                String.format("%.2f", 100.0);
        assertEquals(esperado, aposta.toString());
    }
}
