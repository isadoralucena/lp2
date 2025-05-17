package complementacao.model.dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ElementoTextoDicaTest {
	@Test
    void testTextoComMenosDe100CaracteresTemValorZero() {
        String texto = "Texto com menos de 100 caracteres apenas para teste.";
        ElementoTextoDica elemento = new ElementoTextoDica(texto);
        assertEquals(0, elemento.getValor());
    }

    @Test
    void testTextoComExatamente100Caracteres() {
        String texto = "A".repeat(100);
        ElementoTextoDica elemento = new ElementoTextoDica(texto);
        assertEquals(10, elemento.getValor());
    }

    @Test
    void testTextoCom250Caracteres() {
        String texto = "B".repeat(250);
        ElementoTextoDica elemento = new ElementoTextoDica(texto);
        assertEquals(25, elemento.getValor());
    }

    @Test
    void testTextoComMaisDe500CaracteresLancaExcecao() {
        String texto = "C".repeat(600);
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ElementoTextoDica(texto);
        });
        assertEquals("O texto deve ter no máximo 500 caracteres.", e.getMessage());
    }

    @Test
    void testVisualizacaoResumida() {
        String texto = "Um texto qualquer com conteúdo.";
        ElementoTextoDica elemento = new ElementoTextoDica(texto);
        assertEquals("Texto: " + texto, elemento.visualizacaoResumida());
    }

    @Test
    void testVisualizacaoDetalhada() {
        String texto = "Exemplo de texto com um número conhecido de caracteres: 1234567890";
        ElementoTextoDica elemento = new ElementoTextoDica(texto);
        String esperado = "Texto: " + texto + "\nTamanho de caracteres: " + texto.length();
        assertEquals(esperado, elemento.visualizacaoDetalhada());
    }

    @Test
    void testTextoNuloLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ElementoTextoDica(null);
        });
        assertEquals("O texto não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testTextoVazioLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ElementoTextoDica("   ");
        });
        assertEquals("O texto não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testTextoComCaracteresDeEspacoSomenteLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new ElementoTextoDica("     \n\t");
        });
        assertEquals("O texto não pode ser nulo ou vazio.", e.getMessage());
    }
}
