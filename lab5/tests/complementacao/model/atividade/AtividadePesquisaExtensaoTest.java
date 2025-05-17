package complementacao.model.atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AtividadePesquisaExtensaoTest {

	@Test
    void testCriaPesquisaComSubtipoValido() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE01", 36, "Pet");
        assertEquals(18, atividade.getCreditos());
    }

	@Test
	void testCriaExtensaoComSubtipoValido() {
	    AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE02", 36, "Pet");
	    assertEquals(18, atividade.getCreditos());
	}

    @Test
    void testLimiteMaximoDeCreditos() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE03", 1000, "Pet");
        assertEquals(18, atividade.getCreditos());
    }

    @Test
    void testUnidadesExatasParaLimiteMaximo() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE04", 24, "Pet");
        assertEquals(18, atividade.getCreditos());
    }

    @Test
    void testUnidadeZeroLancaExcecao() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadePesquisaExtensao("PE05", 0, "Pet");
        });
        assertEquals("A unidade acumulada precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testCreditoComUnidadesMenorQueBloco() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE06", 11, "Pet");
        assertEquals(0, atividade.getCreditos());
    }

    @Test
    void testCreditoComUnidadesParcialmenteDivisiveis() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE07", 25, "Pet");
        assertEquals(18, atividade.getCreditos());
    }

    @Test
    void testSubtipoInvalidoLancaExcecao() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadePesquisaExtensao("PE08", 20, "Outro");
        });
        assertEquals("Subtipo de pesquisa e extensão inválido", e.getMessage());
    }

    @Test
    void testSubtipoNullLancaExcecao() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadePesquisaExtensao("PE09", 20, null);
        });
        assertEquals("Subtipo de pesquisa e extensão inválido", e.getMessage());
    }

    @Test
    void testUnidadeNegativaLancaExcecao() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadePesquisaExtensao("PE10", -5, "Pesquisa");
        });
        assertEquals("A unidade acumulada precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testAceitaSubtipoComCaixaAlta() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE11", 12, "Pet");
        assertEquals(10, atividade.getCreditos());
    }

    @Test
    void testAceitaSubtipoComCaixaMista() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE12", 12, "Pet");
        assertEquals(10, atividade.getCreditos());
    }

    @Test
    void testCreditoComUnidadeAcumuladaExatamentePara1Bloco() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE13", 12, "Pet");
        assertEquals(10, atividade.getCreditos());
    }

    @Test
    void testCreditoComUnidadeAcumuladaPara1eMeioBloco() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE14", 18, "Pet");
        assertEquals(10, atividade.getCreditos());
    }

    @Test
    void testCreditoComUnidadeAcumuladaNaoMultipla() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE15", 13, "Pet");
        assertEquals(10, atividade.getCreditos());
    }

    @Test
    void testCreditoComUnidadeAcumuladaMuitoAlta() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE16", 9999, "Pet");
        assertEquals(18, atividade.getCreditos());
    }

    @Test
    void testCreditoComUnidadeAcumuladaMaiorQueUmBlocoMasAbaixoDoLimite() {
        AtividadePesquisaExtensao atividade = new AtividadePesquisaExtensao("PE17", 21, "Pet");
        assertEquals(10, atividade.getCreditos());
    }
}