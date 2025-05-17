package complementacao.model.dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ElementoMultimidiaDicaTest {
	
	@Test
    void testConstrutorValido() {
        assertDoesNotThrow(() -> new ElementoMultimidiaDica("https://youtube.com/video", "Video Aula", 120));
    }

    @Test
    void testConstrutorComLinkNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoMultimidiaDica(null, "Video", 60));
        assertEquals("O link não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testConstrutorComLinkVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoMultimidiaDica("   ", "Video", 60));
        assertEquals("O link não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testConstrutorComCabecalhoNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoMultimidiaDica("https://site.com", null, 60));
        assertEquals("O cabeçalho não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testConstrutorComCabecalhoVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoMultimidiaDica("https://site.com", "   ", 60));
        assertEquals("O cabeçalho não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testConstrutorComTempoNegativo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoMultimidiaDica("https://site.com", "Cabeçalho", -10));
        assertEquals("O tempo precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testValorGeradoCorretamentePara60Segundos() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("link", "cabecalho", 60);
        assertEquals(5, elemento.getValor());
    }

    @Test
    void testValorGeradoComArredondamentoParaBaixo() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("link", "cabecalho", 59);
        assertEquals(0, elemento.getValor());
    }

    @Test
    void testValorMaximoNaoUltrapassa50() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("link", "cabecalho", 7200);
        assertEquals(50, elemento.getValor());
    }

    @Test
    void testValorLimiteInferior() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("https://video.com", "Aula curta", 59);
        assertEquals(0, elemento.getValor());
    }

    @Test
    void testValorLimiteSuperior() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("https://longo.com", "Aula gigante", 7200); // 2 horas
        assertEquals(50, elemento.getValor());
    }

    @Test
    void testValorExatamenteNoLimite() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("https://limite.com", "Material exato", 600); // 10 minutos
        assertEquals(50, elemento.getValor());
    }

    @Test
    void testVisualizacaoResumida() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("https://curso.com", "Curso de Java", 120);
        String esperado = "Link: https://curso.com\nCabeçalho: Curso de Java";
        assertEquals(esperado, elemento.visualizacaoResumida());
    }

    @Test
    void testVisualizacaoDetalhada() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("https://aulinha.com", "Estrutura de Dados", 180);
        String esperado = "Link: https://aulinha.com\nCabeçalho: Estrutura de Dados\nTempo em segundos: 180";
        assertEquals(esperado, elemento.visualizacaoDetalhada());
    }

    @Test
    void testLinkVazio() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () ->
            new ElementoMultimidiaDica("", "Vídeo", 60)
        );
        assertEquals("O link não pode ser nulo ou vazio.", erro.getMessage());
    }

    @Test
    void testLinkNulo() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () ->
            new ElementoMultimidiaDica(null, "Vídeo", 60)
        );
        assertEquals("O link não pode ser nulo ou vazio.", erro.getMessage());
    }

    @Test
    void testCabecalhoVazio() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () ->
            new ElementoMultimidiaDica("https://teste.com", "", 60)
        );
        assertEquals("O cabeçalho não pode ser nulo ou vazio.", erro.getMessage());
    }

    @Test
    void testCabecalhoNulo() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () ->
            new ElementoMultimidiaDica("https://teste.com", null, 60)
        );
        assertEquals("O cabeçalho não pode ser nulo ou vazio.", erro.getMessage());
    }

    @Test
    void testTempoZero() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () ->
            new ElementoMultimidiaDica("https://teste.com", "Vídeo legal", 0)
        );
        assertEquals("O tempo precisa ser um valor positivo.", erro.getMessage());
    }

    @Test
    void testTempoNegativo() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class, () ->
            new ElementoMultimidiaDica("https://teste.com", "Vídeo legal", -10)
        );
        assertEquals("O tempo precisa ser um valor positivo.", erro.getMessage());
    }

    @Test
    void testGerarValorManual() {
        ElementoMultimidiaDica elemento = new ElementoMultimidiaDica("https://abc.com", "Teste", 601);
        assertEquals(50, elemento.getValor());
    }
}
