package complementacao.model.atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AtividadeMonitoriaTest {
	    @Test
	    void testConstrutorValido() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M1", 2, "Cálculo I");
	        assertEquals("M1", monitoria.getId());
	        assertEquals(2, monitoria.getUnidadeAcumulada());
	        assertEquals(1, monitoria.getReferenciaUnidadeAcumulada());
	        assertEquals(4, monitoria.getReferenciaCreditos());
	        assertEquals(16, monitoria.getCreditoMaximo());
	        assertEquals(1, monitoria.getQuantidadeMinimaUnidadeAcumulada());
	    }

	    @Test
	    void testCreditosCalculadosCorretamente() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M2", 3, "Estrutura de Dados");
	        assertEquals(12, monitoria.getCreditos());
	    }

	    @Test
	    void testCreditosLimitadosAoMaximo() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M3", 10, "POO");
	        assertEquals(16, monitoria.getCreditos());
	    }

	    @Test
	    void testLancaExcecaoComZeroUnidades() {
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            new AtividadeMonitoria("M13", 0, "Comp. Gráfica");
	        });
	        assertEquals("A unidade acumulada precisa ser um valor positivo.", exception.getMessage());
	    }

	    @Test
	    void testLancaExcecaoAbaixoDoMinimo() {
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            new AtividadeMonitoria("M4", -1, "Algoritmos");
	        });
	        assertEquals("A unidade acumulada precisa ser um valor positivo.", exception.getMessage());
	    }

	    @Test
	    void testCreditosExatamenteNoLimite() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M5", 4, "Teoria da Computação");
	        assertEquals(16, monitoria.getCreditos());
	    }

	    @Test
	    void testNomeDisciplinaNuloLancaExcecao() {
	        Exception e = assertThrows(IllegalArgumentException.class, () -> {
	            new AtividadeMonitoria("M6", 2, null);
	        });
	        assertEquals("Nome da disciplina não pode ser nulo ou vazio", e.getMessage());
	    }

	    @Test
	    void testNomeDisciplinaVazioLancaExcecao() {
	        Exception e = assertThrows(IllegalArgumentException.class, () -> {
	            new AtividadeMonitoria("M7", 2, "  ");
	        });
	        assertEquals("Nome da disciplina não pode ser nulo ou vazio", e.getMessage());
	    }

	    @Test
	    void testUnidadeAcumuladaNegativaLancaExcecao() {
	        Exception e = assertThrows(IllegalArgumentException.class, () -> {
	            new AtividadeMonitoria("M8", -1, "Compiladores");
	        });
	        assertEquals("A unidade acumulada precisa ser um valor positivo.", e.getMessage());
	    }

	    @Test
	    void testCreditosComUnidadeExataNoMinimo() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M9", 1, "Sistemas Operacionais");
	        assertEquals(4, monitoria.getCreditos());
	    }

	    @Test
	    void testDescricaoPodeSerSetada() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M10", 2, "Banco de Dados");
	        assertDoesNotThrow(() -> monitoria.setDescricao("Monitoria em BD"));
	    }

	    @Test
	    void testDocumentacaoPodeSerSetada() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M11", 2, "Redes");
	        assertDoesNotThrow(() -> monitoria.setDocumentacaoComprobatoria("certificado.pdf"));
	    }

	    @Test
	    void testUnidadeAcumuladaMuitoAltaLimitaCreditos() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M12", 100, "IA");
	        assertEquals(16, monitoria.getCreditos());
	    }

	    @Test
	    void testIdPodeSerQualquerString() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("xyz123", 2, "Engenharia de Software");
	        assertEquals("xyz123", monitoria.getId());
	    }

	    @Test
	    void testCreditosComUnidadeAcumuladaProximaDoLimite() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M14", 3, "Empreendedorismo");
	        assertEquals(12, monitoria.getCreditos());
	    }

	    @Test
	    void testCreditosComUnidadeSuficienteParaExcederLimite() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M15", 5, "Computação Quântica");
	        assertEquals(16, monitoria.getCreditos());
	    }

	    @Test
	    void testCreditosComUnidadeGrandeEMultiplaDeLimite() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M16", 4, "Matemática Discreta");
	        assertEquals(16, monitoria.getCreditos());
	    }

	    @Test
	    void testCreditosComUnidadeJustoAntesDeExceder() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M17", 3, "Lógica");
	        assertEquals(12, monitoria.getCreditos());
	    }

	    @Test
	    void testCreditosNaoUltrapassam16ComUnidadeLimiteMais1() {
	        AtividadeMonitoria monitoria = new AtividadeMonitoria("M18", 5, "TCC");
	        assertEquals(16, monitoria.getCreditos());
	    }
}
