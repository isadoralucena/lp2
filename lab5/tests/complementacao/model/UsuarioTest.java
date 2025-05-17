package complementacao.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import complementacao.enums.TipoAtividade;
import complementacao.model.atividade.Atividade;
import complementacao.model.atividade.AtividadeEstagio;
import complementacao.model.atividade.AtividadeMonitoria;

class UsuarioTest {
    private Usuario usuario;
    private Atividade atividade;
    private TipoAtividade tipoAtividade;

    @BeforeEach
    void setUp() {
    	usuario = new Usuario("Ana Souza", "12345678909", "senha123", "202312345");
    	tipoAtividade = TipoAtividade.PESQUISA_EXTENSAO;
        String id = usuario.gerarCodigoAtividade(tipoAtividade);
        atividade = new AtividadeEstagio(id, 5, "Empresa exemplo");
    }

    @Test
    public void testConstrutorValido() {
    	assertEquals("Ana Souza", usuario.getNome());
        assertEquals("12345678909", usuario.getCpf());
        assertEquals("202312345", usuario.getMatricula());
    }

    @Test
    public void testConstrutorComCPFInvalido() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Usuario("Ana", "123", "senha123", "mat123"));
    }

    @Test
    public void testConstrutorComSenhaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Usuario("Ana", "12345678900", "123", "mat123"));
    }

    @Test
    public void testConstrutorComMatriculaVazia() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Usuario("Ana", "12345678900", "senha123", ""));
    }
    
    @Test
    void testAutenticacaoCorreta() {
        assertTrue(usuario.autenticar("12345678909", "senha123"));
    }

    @Test
    void testAutenticacaoIncorreta() {
        assertFalse(usuario.autenticar("12345678909", "senhaErrada"));
    }

    @Test
    void testAlterarSenhaComSucesso() {
        assertTrue(usuario.alterarSenhaEstudante("12345678909", "senha123", "novaSenhaSegura123"));
    }

    @Test
    void testAlterarSenhaComFalhaNaAutenticacao() {
        assertFalse(usuario.alterarSenhaEstudante("12345678909", "senhaErrada", "novaSenhaSegura"));
    }

    @Test
    void testAlterarSenhaInvalida() {
        assertThrows(IllegalArgumentException.class, () ->
            usuario.alterarSenhaEstudante("12345678909", "senha123", "123"));
    }
    
    private int calcularCreditosPossiveis(Atividade atividade) {
        int unidade = atividade.getUnidadeAcumulada();
        int refUnidade = atividade.getReferenciaUnidadeAcumulada();
        int refCredito = atividade.getReferenciaCreditos();
        int maxCredito = atividade.getCreditoMaximo();
        int minUnidade = atividade.getQuantidadeMinimaUnidadeAcumulada();

        if (unidade < minUnidade) return 0;

        int creditosCalculados = (unidade / refUnidade) * refCredito;
        return Math.min(creditosCalculados, maxCredito);
    }
    
    @Test
    public void testAdicionarAtividade() {
        usuario.adicionarAtividadePorTipo(tipoAtividade, atividade);
        int esperado = calcularCreditosPossiveis(atividade);

        assertEquals(esperado, usuario.creditosAtividade(tipoAtividade));
        assertEquals(esperado, usuario.getCreditosAcumulados());
    }

    @Test
    public void testAcumularCreditosAteLimite() {
        Atividade temporaria = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), 1, "Empresa temporária");
        int max = temporaria.getCreditoMaximo();
        int refUnidade = temporaria.getReferenciaUnidadeAcumulada();

        int unidadeParaMaximo = (max * refUnidade);

        int metade = unidadeParaMaximo / 2;
        Atividade a1 = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), metade, "Empresa A");
        Atividade a2 = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), unidadeParaMaximo - metade, "Empresa B");

        usuario.adicionarAtividadePorTipo(tipoAtividade, a1);
        usuario.adicionarAtividadePorTipo(tipoAtividade, a2);

        assertEquals(max, usuario.getCreditosAcumulados());
    }
    
    @Test
    public void testCreditosNaoUltrapassamLimite() {
        Atividade temp = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), 1, "Empresa qualquer");
        int max = temp.getCreditoMaximo();
        int refU = temp.getReferenciaUnidadeAcumulada();

        int unidadeExcedente = (max + 3) * refU;

        Atividade a = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), unidadeExcedente, "Empresa qualquer");

        usuario.adicionarAtividadePorTipo(tipoAtividade, a);

        assertEquals(max, usuario.getCreditosAcumulados());
    }
    
    @Test
    public void testCreditosNaoUltrapassamMaximoComUnidadeExcedente() {
        Atividade temp = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), 1, "Empresa qualquer");
        
        int maxCreditos = temp.getCreditoMaximo();
        int refUnidade = temp.getReferenciaUnidadeAcumulada();
        int refCreditos = temp.getReferenciaCreditos();

        int unidadesExcedentes = (maxCreditos * refUnidade) + (2 * refUnidade);

        Atividade atividadeExcedente = new AtividadeEstagio(
            usuario.gerarCodigoAtividade(tipoAtividade),
            unidadesExcedentes,
            "Empresa Qualquer"
        );

        usuario.adicionarAtividadePorTipo(tipoAtividade, atividadeExcedente);
        assertEquals(maxCreditos, usuario.getCreditosAcumulados());
    }

    @Test
    public void testMetaAlcancada() {
    	int maximoGeral = Usuario.getMaximoCreditos();

        Atividade tempEstagio = new AtividadeEstagio(usuario.gerarCodigoAtividade(TipoAtividade.ESTAGIO), 1, "Empresa qualquer");
        int refU_Estagio = tempEstagio.getReferenciaUnidadeAcumulada();
        int unidadeMaxEstagio = tempEstagio.getCreditoMaximo() * refU_Estagio;

        Atividade estagio = new AtividadeEstagio(usuario.gerarCodigoAtividade(TipoAtividade.ESTAGIO), unidadeMaxEstagio, "Empresa Estágio");
        usuario.adicionarAtividadePorTipo(TipoAtividade.ESTAGIO, estagio);

        Atividade tempMonitoria = new AtividadeMonitoria(usuario.gerarCodigoAtividade(TipoAtividade.MONITORIA), 1, "Disciplina qualquer");
        int refU_Monitoria = tempMonitoria.getReferenciaUnidadeAcumulada();
        int unidadeMaxMonitoria = tempMonitoria.getCreditoMaximo() * refU_Monitoria;

        Atividade monitoria = new AtividadeMonitoria(usuario.gerarCodigoAtividade(TipoAtividade.MONITORIA), unidadeMaxMonitoria, "Disciplina qualquer");
        usuario.adicionarAtividadePorTipo(TipoAtividade.MONITORIA, monitoria);

        assertEquals(maximoGeral, usuario.getCreditosAcumulados());
        assertTrue(usuario.metaAlcancada());
    }
    
    @Test
    public void testMetaNaoAlcancada() {
        Atividade a = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), 5, "Empresa qualquer");
        usuario.adicionarAtividadePorTipo(tipoAtividade, a);

        assertFalse(usuario.metaAlcancada());
    }

    @Test
    public void testMetaAlcancadaPorTipo() {
        Atividade temp = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), 1, "Empresa qualquer");
        int max = temp.getCreditoMaximo();
        int refU = temp.getReferenciaUnidadeAcumulada();
        int unidadeParaMax = max * refU;

        Atividade atividade = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), unidadeParaMax, "Empresa qualquer");
        usuario.adicionarAtividadePorTipo(tipoAtividade, atividade);

        assertTrue(usuario.metaAlcancadaPorAtividade(tipoAtividade));
    }

    @Test
    public void testUnidadeAbaixoDoMinimoNaoGeraCredito() {
        Atividade temp = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), 1, "Empresa qualquer");
        int min = temp.getQuantidadeMinimaUnidadeAcumulada();

        Atividade atividade = new AtividadeEstagio(usuario.gerarCodigoAtividade(tipoAtividade), min - 1, "Empresa qualquer");
        usuario.adicionarAtividadePorTipo(tipoAtividade, atividade);

        assertEquals(0, usuario.getCreditosAcumulados());
    }

    @Test
    public void testGerarCodigoAtividade() {
        String codigo = usuario.gerarCodigoAtividade(tipoAtividade);
        assertTrue(codigo.startsWith("123.456.789-09_"));
    }

    @Test
    public void testSalvarEListarHistorico() {
        LocalDate hoje = LocalDate.now();
        usuario.salvarRelatorio(hoje, "Relatório completo");
        String historico = usuario.listarHistorico();
        assertTrue(historico.contains("Relatório completo"));
    }

    @Test
    void testExcluirRelatorio() {
        LocalDate data = LocalDate.of(2025, 4, 7);
        usuario.salvarRelatorio(data, "Relatório teste");
        assertTrue(usuario.excluirItemHistorico(data));
    }
    
    @Test
    void testExcluirRelatorioDataInvalida() {
        LocalDate dataInvalida = LocalDate.of(2025, 4, 8);
        assertFalse(usuario.excluirItemHistorico(dataInvalida));
    }

    
    @Test
    public void testAlterarDescricaoAtividade() {
        usuario.adicionarAtividadePorTipo(tipoAtividade, atividade);
        String codigo = atividade.getId();
        assertTrue(usuario.alterarDescricaoAtividade(codigo, "Nova descrição"));
    }

    @Test
    public void testAlterarComprovacaoAtividade() {
        usuario.adicionarAtividadePorTipo(tipoAtividade, atividade);
        String codigo = atividade.getId();
        assertTrue(usuario.alterarComprovacaoAtividade(codigo, "https://novo.link"));
    }

    @Test
    public void testAlterarAtividadeInexistente() {
        assertFalse(usuario.alterarDescricaoAtividade("codigo-invalido", "x"));
        assertFalse(usuario.alterarComprovacaoAtividade("codigo-invalido", "x"));
    }


    @Test
    void testToStringUsuario() {
        String esperado = "Nome: Ana Souza, matrícula: 202312345, bônus: 0";
        assertEquals(esperado, usuario.toString());
    }
    
    @Test
    public void testEqualsEHashCode() {
        Usuario outro = new Usuario("Maria", "12345678909", "senhaForte123", "20239999");
        assertEquals(usuario, outro);
        assertEquals(usuario.hashCode(), outro.hashCode());
    }

    @Test
    public void testToString() {
        String saida = usuario.toString();
        assertTrue(saida.contains("Ana Souza"));
        assertTrue(saida.contains("202312345"));
        assertTrue(saida.contains(String.valueOf(usuario.getBonificacao())));
    }
}