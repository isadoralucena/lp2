package mrbet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MrBetSistemaTest {

	private MrBetSistema sistema;

    @BeforeEach
    void setup() {
    	sistema = new MrBetSistema();
        sistema.adicionarTime("250_PB", "Nacional de Patos", "Canário");
        sistema.adicionarTime("252_PB", "Sport Lagoa Seca", "Carneiro");
        sistema.adicionarTime("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
        sistema.adicionarTime("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");
    }

    @Test
    public void testCadastrarCampeonatoValido() {
        String resultado = sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        assertEquals("CAMPEONATO ADICIONADO!", resultado);
    }
    
    @Test
    public void testCadastrarCampeonatoExistente() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        String resultado = sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        assertEquals("CAMPEONATO JÁ EXISTE!", resultado);
    }
    
    @Test
    public void testAdicionarCampeonatoCaseInsensitive() {
        String resultado1 = sistema.adicionarCampeonato("Brasileirão", 4);
        String resultado2 = sistema.adicionarCampeonato("brasileirão", 4);
        assertEquals("CAMPEONATO ADICIONADO!", resultado1);
        assertEquals("CAMPEONATO JÁ EXISTE!", resultado2);
    }
    
    @Test
    public void testIncluirTimeEmCampeonato() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        String resultado1 = sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "250_PB");
        String resultado2 = sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "252_PB");
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado1);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado2);
    }
    
    @Test
    public void testIncluirTimeDuplicadoEmCampeonato() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        String resultado1 = sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "250_PB");
        String resultado2 = sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "250_PB");
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado1);
        assertEquals("O TIME JÁ ESTÁ NO CAMPEONATO!", resultado2);
    }
    
    @Test
    public void testIncluirTimeNaoCadastradoNoCampeonato() {
    	sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        assertEquals("TIME NÃO EXISTE!", sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "005_PB"));
    }
    
    @Test
    public void testIncluirTimeEmCampeonatoNaoExistente() {
        assertEquals("CAMPEONATO NÃO EXISTE!", sistema.adicionarTimeEmCampeonato("Brasileirão série D 2023", "252_PB"));
    }
    
    @Test
    public void testIncluirTimeExcedendoParticipantes() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 1);
        String resultado1 = sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "252_PB");
        String resultado2 = sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "250_PB");
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", resultado1);
        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", resultado2);
    }
    
    @Test
    public void testVerificarTimeEmCampeonato() {
        sistema.adicionarCampeonato("Copa do Nordeste 2023", 4);
        sistema.adicionarTimeEmCampeonato("Copa do Nordeste 2023", "250_PB");
        String resultado1 = sistema.verificarTimeNoCampeonato("Copa do Nordeste 2023", "250_PB");
        String resultado2 = sistema.verificarTimeNoCampeonato("Copa do Nordeste 2023", "252_PB");
        assertEquals("O TIME ESTÁ NO CAMPEONATO!", resultado1);
        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", resultado2);
    }
    
    @Test
    public void testVerificarTimeEmCampeonatoNaoExistente() {
        assertEquals("CAMPEONATO NÃO EXISTE!", sistema.verificarTimeNoCampeonato("Brasileirão série D 2023", "252_PB"));
    }
    
    @Test
    public void testVerificarTimeNaoCadastradoEmCampeonato() {
        sistema.adicionarCampeonato("Copa do Nordeste 2023", 4);
        assertEquals("TIME NÃO EXISTE!", sistema.verificarTimeNoCampeonato("Copa do Nordeste 2023", "005_PB"));
    }
    
    @Test
    public void testRecuperarTimeInexistente() {
        assertEquals("TIME NÃO EXISTE!", sistema.recuperarTime("999_XY"));
    }
    
    @Test
    public void testRecuperarTimeExistente() {
        assertEquals("[250_PB] Nacional de Patos / Canário", sistema.recuperarTime("250_PB"));
    }
    
    @Test
    public void testListarCampeonatosDoTime() {
        sistema.adicionarCampeonato("Copa do Nordeste 2023", 4);
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        sistema.adicionarTimeEmCampeonato("Copa do Nordeste 2023", "250_PB");
        sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "250_PB");
        String resultado = sistema.campeonatosDoTime("250_PB");
        assertTrue(resultado.contains("Copa do Nordeste 2023"));
        assertTrue(resultado.contains("Brasileirão série A 2023"));
    }
    
    @Test
    public void testAdicionarApostaColocacaoInvalida() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        sistema.adicionarTime("250_PB", "Nacional de Patos", "Canário");
        assertEquals("APOSTA NÃO REGISTRADA!", sistema.adicionarAposta("250_PB", "Brasileirão série A 2023", 5, 100.0));
    }
    
    @Test
    public void testAdicionarApostaColocacaoValida() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        sistema.adicionarTime("250_PB", "Nacional de Patos", "Canário");
        assertEquals("APOSTA REGISTRADA!", sistema.adicionarAposta("250_PB", "Brasileirão série A 2023", 2, 100.0));
    }
    
    @Test
    public void testListarApostasVazias() {
        assertEquals("NENHUMA APOSTA CADASTRADA!", sistema.listarApostas());
    }
    
    @Test
    public void testListarApostas() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 4);
        sistema.adicionarTimeEmCampeonato("Brasileirão série A 2023", "250_PB");
        String resultado = sistema.adicionarAposta("250_PB", "Brasileirão série A 2023", 1, 150.0);
        assertEquals("APOSTA REGISTRADA!", resultado);
        String lista = sistema.listarApostas();
        assertTrue(lista.contains("250_PB"));
        assertTrue(lista.contains("Brasileirão série A 2023"));
        assertTrue(lista.contains("1"));
        assertTrue(lista.contains(String.format("%.2f", 150.0)));
    } 
}
