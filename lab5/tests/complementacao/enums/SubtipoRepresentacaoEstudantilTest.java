package complementacao.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SubtipoRepresentacaoEstudantilTest {

	@Test
    void testConversaoDiretoriaSemAcentoMaiuscula() {
        SubtipoRepresentacaoEstudantil subtipo = 
            SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao("DIRETORIA");
        assertEquals(SubtipoRepresentacaoEstudantil.DIRETORIA, subtipo);
    }

    @Test
    void testConversaoComissaoComMinusculasEAcento() {
        SubtipoRepresentacaoEstudantil subtipo = 
            SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao("comissão");
        assertEquals(SubtipoRepresentacaoEstudantil.COMISSAO, subtipo);
    }

    @Test
    void testConversaoComEspacosReconhece() {
        SubtipoRepresentacaoEstudantil subtipo = SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao("  diretoria  ");
        assertEquals(SubtipoRepresentacaoEstudantil.DIRETORIA, subtipo);
    }

    @Test
    void testConversaoComValorInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao("representação");
        });
        assertEquals("Subtipo de representação estudantil inválido.", ex.getMessage());
    }

    @Test
    void testConversaoComNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao(null);
        });
        assertEquals("Subtipo de representação estudantil inválido.", ex.getMessage());
    }

    @Test
    void testConversaoComAcentosMistosEMaiusculas() {
        SubtipoRepresentacaoEstudantil subtipo = 
            SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao("COMISSÃO");
        assertEquals(SubtipoRepresentacaoEstudantil.COMISSAO, subtipo);
    }
}
