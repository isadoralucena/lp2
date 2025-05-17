package complementacao.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SubtipoPesquisaExtensaoTest {

	@Test
    void testConversaoValidaComLetrasMaiusculas() {
        SubtipoPesquisaExtensao subtipo = SubtipoPesquisaExtensao.converterStringEmSubtipoPesquisaExtensao("PIBIC");
        assertEquals(SubtipoPesquisaExtensao.PIBIC, subtipo);
    }

    @Test
    void testConversaoValidaComLetrasMinusculas() {
        SubtipoPesquisaExtensao subtipo = SubtipoPesquisaExtensao.converterStringEmSubtipoPesquisaExtensao("pet");
        assertEquals(SubtipoPesquisaExtensao.PET, subtipo);
    }

    @Test
    void testConversaoValidaComLetrasMisturadas() {
        SubtipoPesquisaExtensao subtipo = SubtipoPesquisaExtensao.converterStringEmSubtipoPesquisaExtensao("Pibiti");
        assertEquals(SubtipoPesquisaExtensao.PIBITI, subtipo);
    }
    
    @Test
    void testConversaoValidaComEspacosRepresentacaoEstudantil() {
        SubtipoRepresentacaoEstudantil subtipo = SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao("  comissao  ");
        assertEquals(SubtipoRepresentacaoEstudantil.COMISSAO, subtipo);
    }

    @Test
    void testConversaoComValorInvalidoLancaExcecao() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            SubtipoPesquisaExtensao.converterStringEmSubtipoPesquisaExtensao("banana");
        });
        assertEquals("Subtipo de pesquisa e extensão inválido", ex.getMessage());
    }

    @Test
    void testConversaoComNullLancaExcecao() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            SubtipoPesquisaExtensao.converterStringEmSubtipoPesquisaExtensao(null);
        });
        assertEquals("Subtipo de pesquisa e extensão inválido", ex.getMessage());
    }
}