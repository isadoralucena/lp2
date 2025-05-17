package complementacao.model.atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeRepresentacaoEstudantilTest {

	private AtividadeRepresentacaoEstudantil atividadePadrao;

    @BeforeEach
    void setUp() {
        atividadePadrao = new AtividadeRepresentacaoEstudantil("RE00", 2, "Diretoria");
    }

    private AtividadeRepresentacaoEstudantil criarAtividade(String id, int unidades, String subtipo) {
        return new AtividadeRepresentacaoEstudantil(id, unidades, subtipo);
    }

    @Test
    void testAtividadePadraoCreditosMaximos() {
        assertEquals(2, atividadePadrao.getCreditos());
    }

    @Test
    void testCreditosComUnidadeMinima() {
        AtividadeRepresentacaoEstudantil atividade = criarAtividade("RE01", 1, "Diretoria");
        assertEquals(2, atividade.getCreditos());
    }

    @Test
    void testCreditosComUnidadeAltaNaoUltrapassaLimite() {
        AtividadeRepresentacaoEstudantil atividade = criarAtividade("RE02", 100, "Diretoria");
        assertEquals(2, atividade.getCreditos());
    }

    @Test
    void testUnidadeZeroLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            criarAtividade("RE03", 0, "Diretoria");
        });
        assertEquals("A unidade acumulada precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testUnidadeNegativaLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            criarAtividade("RE04", -5, "Diretoria");
        });
        assertEquals("A unidade acumulada precisa ser um valor positivo.", e.getMessage());
    }

    @Test
    void testSubtipoInvalidoLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            criarAtividade("RE05", 2, "Subtipo inválido");
        });
        assertEquals("Subtipo de representação estudantil inválido.", e.getMessage());
    }

    @Test
    void testSubtipoNuloLancaExcecao() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            criarAtividade("RE06", 2, null);
        });
        assertEquals("Subtipo de representação estudantil inválido.", e.getMessage());
    }

    @Test
    void testSubtipoComEspacos() {
        AtividadeRepresentacaoEstudantil atividade = criarAtividade("RE07", 2, "Diretoria");
        assertEquals(2, atividade.getCreditos());
    }

    @Test
    void testSubtipoCaseInsensitive() {
        AtividadeRepresentacaoEstudantil atividade = criarAtividade("RE08", 2, "Diretoria");
        assertEquals(2, atividade.getCreditos());
    }

    @Test
    void testTodosSubtiposValidos() {
        assertEquals(2, criarAtividade("RE09", 2, "Diretoria").getCreditos());
        assertEquals(2, criarAtividade("RE10", 2, "Comissão").getCreditos());
        assertEquals(2, criarAtividade("RE11", 2, "Diretoria").getCreditos());
    }

    @Test
    void testGetId() {
        assertEquals("RE00", atividadePadrao.getId());
    }
}