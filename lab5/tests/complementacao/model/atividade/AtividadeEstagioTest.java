package complementacao.model.atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AtividadeEstagioTest {

	@Test
    void testConstrutorComParametrosValidos() {
        AtividadeEstagio estagio = new AtividadeEstagio("E1", 360, "Empresa X");
        assertEquals("E1", estagio.getId());
        assertEquals(360, estagio.getUnidadeAcumulada());
        assertEquals(60, estagio.getReferenciaUnidadeAcumulada());
        assertEquals(1, estagio.getReferenciaCreditos());
        assertEquals(18, estagio.getCreditoMaximo());
        assertEquals(300, estagio.getQuantidadeMinimaUnidadeAcumulada());
    }

    @Test
    void testCreditosCalculadosCorretamente() {
        AtividadeEstagio estagio = new AtividadeEstagio("E2", 600, "Empresa Y");
        assertEquals(10, estagio.getCreditos());
    }

    @Test
    void testCreditosLimitadosAoMaximo() {
        AtividadeEstagio estagio = new AtividadeEstagio("E3", 2000, "Empresa Z");
        assertEquals(18, estagio.getCreditos());
    }

    @Test
    void testCreditosZeradosSeAbaixoDoMinimo() {
        AtividadeEstagio estagio = new AtividadeEstagio("E4", 200, "Empresa W");
        assertEquals(0, estagio.getCreditos());
    }

    @Test
    void testNomeEmpresaNaoPodeSerNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadeEstagio("E5", 400, null);
        });
        assertEquals("Nome da empresa não pode ser nulo ou vazio", e.getMessage());
    }

    @Test
    void testNomeEmpresaNaoPodeSerVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadeEstagio("E6", 400, "  ");
        });
        assertEquals("Nome da empresa não pode ser nulo ou vazio", e.getMessage());
    }

    @Test
    void testUnidadeAcumuladaNegativaLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new AtividadeEstagio("E7", -100, "Empresa A");
        });
        assertEquals("A unidade acumulada precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testUnidadeAcumuladaIgualAoMinimo() {
        AtividadeEstagio estagio = new AtividadeEstagio("E8", 300, "Empresa B");
        assertEquals(5, estagio.getCreditos());
    }

    @Test
    void testCreditosComUnidadeNaoMultiplaDe60() {
        AtividadeEstagio estagio = new AtividadeEstagio("E9", 350, "Empresa C");
        assertEquals(5, estagio.getCreditos());
    }

    @Test
    void testDescricaoPodeSerSetada() {
        AtividadeEstagio estagio = new AtividadeEstagio("E10", 400, "Empresa D");
        estagio.setDescricao("Estágio em desenvolvimento");
        assertDoesNotThrow(() -> estagio.setDescricao("Outra descrição"));
    }

    @Test
    void testDocumentacaoPodeSerSetada() {
        AtividadeEstagio estagio = new AtividadeEstagio("E11", 400, "Empresa E");
        estagio.setDocumentacaoComprobatoria("comprovante.pdf");
        assertDoesNotThrow(() -> estagio.setDocumentacaoComprobatoria("novo.pdf"));
    }
}
