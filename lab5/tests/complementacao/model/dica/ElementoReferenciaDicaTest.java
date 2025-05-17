package complementacao.model.dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ElementoReferenciaDicaTest {

	@Test
    void testConstrutorValidoComConferidaTrue() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("Título", "Fonte", 2023, true, 3);
        assertEquals(15, elemento.getValor());
        assertEquals("Titulo: Título\nFonte: Fonte\nAno: 2023", elemento.visualizacaoResumida());
        assertTrue(elemento.visualizacaoDetalhada().contains("Conferida"));
    }

    @Test
    void testConstrutorValidoComConferidaFalse() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("Livro", "Site", 2019, false, 4);
        assertEquals(0, elemento.getValor());
        assertTrue(elemento.visualizacaoDetalhada().contains("Não conferida"));
    }

    @Test
    void testVisualizacaoDetalhada() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("Ref", "Fonte XYZ", 2022, true, 5);

        String esperado = "Titulo: Ref\n" +
                          "Fonte: Fonte XYZ\n" +
                          "Ano: 2022\n" +
                          "Conferida\n" +
                          "Importância: 5";

        assertEquals(esperado, elemento.visualizacaoDetalhada());
    }

    @Test
    void testValorNaoExcedeMaximoBonus() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("A", "B", 2020, true, 5);
        assertTrue(elemento.getValor() <= ElementoDica.MAXIMO_VALOR_BONUS);
    }

    @Test
    void testTituloNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica(null, "Fonte", 2020, true, 3));
        assertEquals("O título não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testTituloVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("   ", "Fonte", 2020, true, 3));
        assertEquals("O título não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testFonteNula() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("Título", null, 2020, true, 3));
        assertEquals("A fonte não pode ser nula ou vazia.", e.getMessage());
    }

    @Test
    void testFonteVazia() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("Título", "   ", 2020, true, 3));
        assertEquals("A fonte não pode ser nula ou vazia.", e.getMessage());
    }

    @Test
    void testAnoZero() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("Título", "Fonte", 0, true, 3));
        assertEquals("O tempo precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testAnoNegativo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("Título", "Fonte", -100, true, 3));
        assertEquals("O tempo precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testImportanciaMenorQue1() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("Título", "Fonte", 2023, true, 0));
        assertEquals("Importância precisa estar entre 1 e 5.", e.getMessage());
    }

    @Test
    void testImportanciaMaiorQue5() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            new ElementoReferenciaDica("Título", "Fonte", 2023, true, 6));
        assertEquals("Importância precisa estar entre 1 e 5.", e.getMessage());
    }

    @Test
    void testGerarValorConferidaTrue() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("Ref", "Fonte", 2023, true, 2);
        assertEquals(15, elemento.gerarValor());
    }

    @Test
    void testGerarValorConferidaFalse() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("Ref", "Fonte", 2023, false, 2);
        assertEquals(0, elemento.gerarValor());
    }

    @Test
    void testVisualizacaoResumidaFormato() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("Minha Ref", "Jornal", 2015, true, 1);
        String vis = elemento.visualizacaoResumida();
        assertTrue(vis.contains("Titulo: Minha Ref"));
        assertTrue(vis.contains("Fonte: Jornal"));
        assertTrue(vis.contains("Ano: 2015"));
    }

    @Test
    void testVisualizacaoDetalhadaComImportanciaMinima() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("ABC", "DEF", 2023, false, 1);
        assertTrue(elemento.visualizacaoDetalhada().contains("Importância: 1"));
    }

    @Test
    void testVisualizacaoDetalhadaComImportanciaMaxima() {
        ElementoReferenciaDica elemento = new ElementoReferenciaDica("ABC", "DEF", 2023, false, 5);
        assertTrue(elemento.visualizacaoDetalhada().contains("Importância: 5"));
    }
}
