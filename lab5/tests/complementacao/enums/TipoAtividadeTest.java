package complementacao.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TipoAtividadeTest {
	@Test
    void testConversaoPesquisaExtensao() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("participei de um projeto de pesquisa");
        assertEquals(TipoAtividade.PESQUISA_EXTENSAO, tipo);
    }

    @Test
    void testConversaoExtensaoComAcento() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("atividade de extensão universitária");
        assertEquals(TipoAtividade.PESQUISA_EXTENSAO, tipo);
    }

    @Test
    void testConversaoMonitoria() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("monitoria de algoritmos");
        assertEquals(TipoAtividade.MONITORIA, tipo);
    }

    @Test
    void testConversaoEnsinoComoPalavraChaveDeMonitoria() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("experiência no ensino de matemática");
        assertEquals(TipoAtividade.MONITORIA, tipo);
    }

    @Test
    void testConversaoEstagio() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("fiz estágio supervisionado");
        assertEquals(TipoAtividade.ESTAGIO, tipo);
    }

    @Test
    void testConversaoTrabalhoComoPalavraChaveDeEstagio() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("trabalho voluntário na área");
        assertEquals(TipoAtividade.ESTAGIO, tipo);
    }

    @Test
    void testConversaoRepresentacaoEstudantil() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("representação da turma");
        assertEquals(TipoAtividade.REPRESENTACAO_ESTUDANTIL, tipo);
    }

    @Test
    void testConversaoEstudantilComoPalavraChave() {
        TipoAtividade tipo = TipoAtividade.converterStringEmAtividade("participação estudantil");
        assertEquals(TipoAtividade.REPRESENTACAO_ESTUDANTIL, tipo);
    }

    @Test
    void testTextoSemCorrespondenciaLancaExcecao() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            TipoAtividade.converterStringEmAtividade("atividade esportiva");
        });
        assertEquals("Atividade inválida", ex.getMessage());
    }

    @Test
    void testTextoNuloLancaExcecao() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            TipoAtividade.converterStringEmAtividade(null);
        });
        assertEquals("Atividade inválida", ex.getMessage());
    }
}