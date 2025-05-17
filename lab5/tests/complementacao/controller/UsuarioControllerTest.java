package complementacao.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioControllerTest {
	private UsuarioController controller;

	@BeforeEach
	public void setUp() {
	    controller = new UsuarioController();
	}
	
	@Test
	public void testCriarEstudanteComSucesso() {
	    assertTrue(controller.criarEstudante("João", "12345678900", "senha12345671233456", "20231234"));
	    assertEquals("João", controller.getNome("12345678900", "senha12345671233456"));
	}
	
	@Test
	public void testCriarEstudanteDuplicado() {
	    controller.criarEstudante("Maria", "12345678900", "senha12345671233456", "20231111");
	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
	        controller.criarEstudante("Maria2", "12345678900", "senha1234567456", "20239999"));
	    assertEquals("Usuário com este CPF já existe.", e.getMessage());
	}
	
	@Test
	public void testCriarEstudanteCPFInvalido() {
	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
	        controller.criarEstudante("Carlos", "123", "senha12345671233456", "20230001"));
	    assertEquals("CPF inválido.", e.getMessage());
	}
	
	@Test
	public void testCriarEstudanteSenhaCurta() {
	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
	        controller.criarEstudante("Ana", "12345678900", "abc", "20230001"));
	    assertEquals("A senha deve ter pelo menos 8 caracteres.", e.getMessage());
	}
	
	@Test
	public void testAutenticarComSucesso() {
	    controller.criarEstudante("João", "12345678900", "senha12345671233456", "20231234");
	    assertTrue(controller.autenticar("12345678900", "senha12345671233456"));
	}
	
	@Test
	public void testAutenticarComSenhaIncorreta() {
	    controller.criarEstudante("Lia", "98765432100", "minhasenha1234567", "20235555");
	    assertFalse(controller.autenticar("98765432100", "errada"));
	}
	
	@Test
	public void testAutenticarCpfInexistente() {
	    assertFalse(controller.autenticar("00000000000", "qualquer"));
	}
	
	@Test
	public void testAlterarSenhaComSucesso() {
	    controller.criarEstudante("Ana", "12345678900", "minhasenha1234567", "20238888");
	    boolean alterou = controller.alterarSenhaEstudante("12345678900", "minhasenha1234567", "novasenha12345671233456");
	    assertTrue(alterou);
	    assertTrue(controller.autenticar("12345678900", "novasenha12345671233456"));
	}
	
	@Test
	void testAlterarSenhaAutenticacaoFalha() {
	    controller.criarEstudante("Alice", "12345678900", "senha1234567123", "2021003");

	    assertThrows(SecurityException.class, () -> {
	        controller.alterarSenhaEstudante("12345678900", "senha1234567Errada", "novasenha1234567");
	    });
	}
	
	@Test
	public void testCriarAtividadeEstagio() {
	    controller.criarEstudante("Carlos", "12345678900", "senha12345671233456", "20235555");
	    
	    String retorno = controller.criarAtividadeEstagio("12345678900", "senha12345671233456", 300, "Empresa XYZ");
	    
	    assertNotNull(retorno);
	    assertTrue(retorno.contains("Atividade cadastrada com sucesso."));

	    int creditos = controller.creditosAtividade("12345678900", "senha12345671233456", "ESTAGIO");
	    assertEquals(5, creditos);
	}

	
	@Test
	public void testCreditosPorTipo() {
		controller.criarEstudante("Pedro", "99988877766", "senha1234567123345645678", "20234444");
	    controller.criarAtividadeRepresentacaoEstudantil("99988877766", "senha1234567123345645678", 1, "Diretoria");

	    int creditos = controller.creditosAtividade("99988877766", "senha1234567123345645678", "representacao estudantil");

	    assertEquals(2, creditos);
	}
	
	 @Test
	    public void testGerarRelatorioParcialGeral() {
	        controller.criarEstudante("Ana", "00011122233", "senha123456712334563456", "20230001");
	        controller.criarAtividadeEstagio("00011122233", "senha123456712334563456", 1080, "Empresa ABC");

	        String relatorio = controller.gerarRelatorioParcial("00011122233", "senha123456712334563456", false);

	        assertTrue(relatorio.contains("Ana"));
	        assertTrue(relatorio.contains("ESTAGIO: 18/18"));
	    }

    @Test
    public void testGerarRelatorioFinalGeralComMeta() {
        controller.criarEstudante("Bia", "00011122233", "senha12345671233456", "20230002");
        controller.criarAtividadePesquisaExtensao("00011122233", "senha12345671233456", 24, "Pet");
        controller.criarAtividadeEstagio("00011122233", "senha12345671233456", 1080, "Empresa ABC");

        String relatorio = controller.gerarRelatorioFinal("00011122233", "senha12345671233456");

        assertTrue(relatorio.contains("Bia"));
        assertTrue(relatorio.contains("TOTAL: 22"));
    }

    @Test
    public void testGerarRelatorioFinalGeralSemMeta() {
        controller.criarEstudante("Caio", "44455566677", "senha12345671233456", "20230003");
        controller.criarAtividadeRepresentacaoEstudantil("44455566677", "senha12345671233456", 5, "Diretoria");

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            controller.gerarRelatorioFinal("44455566677", "senha12345671233456");
        });

        assertEquals("Meta de créditos não alcançada.", e.getMessage());
    }

    @Test
    public void testGerarRelatorioParcialPorAtividade() {
        controller.criarEstudante("Dani", "77788899900", "senha12345671233456", "20230004");
        controller.criarAtividadePesquisaExtensao("77788899900", "senha12345671233456", 12, "Pet");

        String relatorio = controller.gerarRelatorioParcialPorAtividade("77788899900", "senha12345671233456", false, "extensao");

        assertTrue(relatorio.contains("EXTENSAO"));
        assertTrue(relatorio.contains("10/18"));
    }

    @Test
    public void testGerarRelatorioFinalPorAtividadeComMeta() {
        controller.criarEstudante("Eva", "88877766655", "senha12345671233456", "20230005");
        controller.criarAtividadeRepresentacaoEstudantil("88877766655", "senha12345671233456", 1, "Diretoria");

        String relatorio = controller.gerarRelatorioFinalPorAtividade("88877766655", "senha12345671233456", "representacao_estudantil");

        assertTrue(relatorio.contains("REPRESENTACAO_ESTUDANTIL"));
        assertTrue(relatorio.contains("2/2"));
    }

    @Test
    public void testGerarRelatorioFinalPorAtividadeSemMeta() {
        controller.criarEstudante("Fernanda", "12312312399", "senha12345671233456", "20230006");
        controller.criarAtividadeEstagio("12312312399", "senha12345671233456", 12, "Empresa ZZZ");

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            controller.gerarRelatorioFinalPorAtividade("12312312399", "senha12345671233456", "estagio");
        });

        assertEquals("Meta de créditos da atividade não alcançada.", e.getMessage());
    }

    @Test
    public void testRelatorioSalvoNoHistorico() {
    	controller.criarEstudante("Gabriel", "32132132100", "senha12345671233456", "20230007");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa ZZZ");

        controller.gerarRelatorioParcial("32132132100", "senha12345671233456", true);

        String historico = controller.listarHistorico("32132132100", "senha12345671233456");

        assertNotNull(historico);
        assertTrue(historico.contains("ESTAGIO:"));
        assertTrue(historico.contains("5/18"));
    }
    
    @Test
    public void testAlterarDescricaoAtividadeComAutenticacaoValida() {
    	controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa XPTO");
        boolean resultado = controller.alterarDescricaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_1", "nova descricao");
        
        assertTrue(resultado);
    }

    @Test
    public void testAlterarDescricaoAtividadeComAutenticacaoInvalida() {
        controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa XPTO");

        Exception exception = assertThrows(SecurityException.class, () -> {
            controller.alterarDescricaoAtividade("32132132100", "senhaErrada", "321.321.321-00_1", "nova descricao");
        });

        assertEquals("Autenticação falhou.", exception.getMessage());
    }

    @Test
    public void testAlterarDescricaoAtividadesComAutenticacaoValidaEMultiplasAtividades() {
        controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa A");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 900, "Empresa B");

        boolean resultado1 = controller.alterarDescricaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_1", "novo estagio");
        assertTrue(resultado1);

        boolean resultado2 = controller.alterarDescricaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_2", "nova extensão");
        assertTrue(resultado2);
    }
    
    @Test
    public void testAlterarDescricaoAtividadeInexistente() {
        controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");

        boolean resultado = controller.alterarDescricaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_2", "nova descricao");

        assertFalse(resultado);
    }
    
    @Test
    public void testAlterarComprovacaoProbatoriaComAutenticacaoValida() {
        controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa XPTO");

        boolean resultado = controller.alterarComprovacaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_1", "novo comprovante.pdf");
        
        assertTrue(resultado);
    }

    @Test
    public void testAlterarComprovacaoProbatoriaComAutenticacaoInvalida() {
        controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa XPTO");

        Exception exception = assertThrows(SecurityException.class, () -> {
            controller.alterarComprovacaoAtividade("32132132100", "senhaErrada", "321.321.321-00_1", "tentativaComprovacao.pdf");
        });

        assertEquals("Autenticação falhou.", exception.getMessage());
    }

    @Test
    public void testAlterarComprovacaoProbatoriaComAutenticacaoValidaEMultiplasAtividades() {
        controller.criarEstudante("Carlos", "32132132100", "senha12345671233456", "20235555");

        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 300, "Empresa A");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 120, "Empresa B");
        controller.criarAtividadeEstagio("32132132100", "senha12345671233456", 200, "Empresa C");

        boolean resultado1 = controller.alterarComprovacaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_1", "comprovante_estagio.pdf");
        assertTrue(resultado1);

        boolean resultado2 = controller.alterarComprovacaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_2", "comprovante_extensao.pdf");
        assertTrue(resultado2);

        boolean resultado3 = controller.alterarComprovacaoAtividade("32132132100", "senha12345671233456", "321.321.321-00_3", "comprovante_pesquisa.pdf");
        assertTrue(resultado3);
    }
	
	@Test
	public void testBonificacao() {
	    controller.criarEstudante("Carlos", "22211100099", "senha12345671233456", "20235555");
	    controller.atualizarBonificacao("22211100099", "senha12345671233456", 20);

	    List<String> ranking = controller.listarUsuariosRankingDicas();

	    assertFalse(ranking.isEmpty());
	    assertEquals(1, ranking.size(), "Ranking deveria conter apenas um usuário.");
	    assertTrue(ranking.get(0).contains("Carlos"));
	    assertTrue(ranking.get(0).contains("20235555"));
	    assertTrue(ranking.get(0).contains("bônus: 20"));
	}
	
	@Test
	public void testHierarquiaRankingBonificacao() {
	    controller.criarEstudante("Alice", "11111111111", "senha1234567123345645", "20230001");
	    controller.criarEstudante("Bruno", "22222222222", "senha123456754321", "20230002");
	    controller.criarEstudante("Carla", "33333333333", "senha123456709876", "20230003");

	    controller.atualizarBonificacao("11111111111", "senha1234567123345645", 10);
	    controller.atualizarBonificacao("22222222222", "senha123456754321", 30);
	    controller.atualizarBonificacao("33333333333", "senha123456709876", 20);

	    List<String> ranking = controller.listarUsuariosRankingDicas();

	    assertEquals(3, ranking.size());

	    assertTrue(ranking.get(0).contains("Bruno") && ranking.get(0).contains("bônus: 30"));
	    assertTrue(ranking.get(1).contains("Carla") && ranking.get(1).contains("bônus: 20"));
	    assertTrue(ranking.get(2).contains("Alice") && ranking.get(2).contains("bônus: 10"));
	}
	
	@Test
	public void testMetaNaoAlcancada() {
	    controller.criarEstudante("Lia", "88899900011", "minhasenha1234567", "20230001");
	    assertFalse(controller.verificarMetaAlcancada("88899900011", "minhasenha1234567"));
	}
	
	@Test
	public void testGetNome() {
	    controller.criarEstudante("Laura", "12345678900", "senha12345671233456", "20237777");
	    assertEquals("Laura", controller.getNome("12345678900", "senha12345671233456"));
	}
	
	@Test
	public void testGetNomeComSenhaIncorreta() {
	    controller.criarEstudante("Igor", "12345678900", "senhaSegura", "20238888");
	    assertThrows(SecurityException.class, () -> controller.getNome("12345678900", "senhaErrada"));
	}
	
	@Test
	public void testListarEstudantesOrdenados() {
	    controller.criarEstudante("Bruna", "12345678901", "senha12345671233456", "1");
	    controller.criarEstudante("Amanda", "12345678902", "senha1234567456", "2");
	    List<String> lista = controller.exibirEstudantes();
	    assertTrue(lista.get(0).contains("Amanda"));
	    assertTrue(lista.get(1).contains("Bruna"));
	}
	
	@Test
	public void testListarEstudantesNaoExibeCPFOuSenha() {
	    controller.criarEstudante("Bruna", "12345678901", "senha12345671233456", "1");
	    controller.criarEstudante("Amanda", "12345678902", "senha1234567456", "2");
	    
	    List<String> lista = controller.exibirEstudantes();

	    for (String estudante : lista) {
	        assertFalse(estudante.contains("12345678901"));
	        assertFalse(estudante.contains("12345678902"));
	        assertFalse(estudante.contains("senha12345671233456"));
	        assertFalse(estudante.contains("senha1234567456"));
	    }
	}
	
	@Test
	public void testCriarEstudanteComMatriculaVazia() {
	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
	        controller.criarEstudante("Leo", "12345678901", "senha1234567senha1234567", ""));
	    assertEquals("Matrícula não pode ser nula ou vazia.", e.getMessage());
	}
	
	@Test
	void testCriarUsuarioDuplicado() {
	    controller.criarEstudante("Maria", "12345678900", "senha1234567123", "2021001");

	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
	        controller.criarEstudante("João", "12345678900", "senha1234567456", "2021002");
	    });

	    assertEquals("Usuário com este CPF já existe.", e.getMessage());
	}

	@Test
	void testRelatorioParcialPorTipoDeAtividade() {
	    controller.criarEstudante("Lucas", "12345678900", "senha1234567", "2021004");
	    controller.criarAtividadeEstagio("12345678900", "senha1234567", 300, "Empresa XPTO");

	    String relatorio = controller.gerarRelatorioParcialPorAtividade("12345678900", "senha1234567", false, "estagio");

	    assertTrue(relatorio.contains("ESTAGIO"));
	    assertTrue(relatorio.contains("5"));
	}
	
	@Test
	void testMetaDeCreditosAlcancada() {
	    controller.criarEstudante("Carla", "12345678900", "senha12345", "2021005");

	    controller.criarAtividadeEstagio("12345678900", "senha12345", 20, "Empresa A");
	    controller.criarAtividadeMonitoria("12345678900", "senha12345", 300, "Cálculo I");
	    controller.criarAtividadePesquisaExtensao("12345678900", "senha12345", 20, "Pet");

	    boolean meta = controller.verificarMetaAlcancada("12345678900", "senha12345");

	    assertTrue(meta);
	}
	
	@Test
	void testAutenticacaoCpfInexistente() {
	    boolean autenticado = controller.autenticar("000", "qualquersenha1234567");

	    assertFalse(autenticado);
	}

	@Test
    void testCriarAtividadeSenhaIncorreta() {
        controller.criarEstudante("Joao", "12345678900", "senha1234567", "2023.1");
        assertThrows(SecurityException.class, () -> {
            controller.criarAtividadeEstagio("12345678900", "senha1234567Errada", 10, "Empresa A");
        });
    }

    @Test
    void testTipoDeAtividadeInvalido() {
        controller.criarEstudante("Maria", "12345678900", "senha1234567", "2023.1");
        assertThrows(IllegalArgumentException.class, () -> {
            controller.creditosAtividade("12345678900", "senha1234567", "TIPO_INVALIDO");
        });
    }

    @Test
    void testOrdenacaoDeEstudantesComNomesSimilares() {
    	controller.criarEstudante("Ana", "12345678900", "senha123456", "2023.1");
        controller.criarEstudante("Ana Beatriz", "12345678911", "senha123456bb", "2023.1");

        List<String> lista = controller.exibirEstudantes();

        String primeiro = lista.get(0);
        String segundo = lista.get(1);

        assertTrue(primeiro.contains("Ana"));
        assertTrue(segundo.contains("Ana Beatriz"));
    }

    @Test
    void testRelatorioParcialUmaAtividade() {
        controller.criarEstudante("Lia", "12345678900", "senha1234567", "2023.1");
        controller.criarAtividadeMonitoria("12345678900", "senha1234567", 5, "EDB1");
        String relatorio = controller.gerarRelatorioFinalPorAtividade("12345678900", "senha1234567", "monitoria");
        assertTrue(relatorio.contains("MONITORIA"));
        assertTrue(relatorio.contains("16/16"));
    }

    @Test
    void testRelatorioCompletoTodasAtividades() {
        controller.criarEstudante("Rafa", "12345678900", "senha1234567", "2023.1");
        controller.criarAtividadeEstagio("12345678900", "senha1234567", 5, "Empresa B");
        controller.criarAtividadeMonitoria("12345678900", "senha1234567", 5, "POO");
        controller.criarAtividadePesquisaExtensao("12345678900", "senha1234567", 5, "PET");
        String relatorio = controller.gerarMapaCreditosAtividades("12345678900", "senha1234567");
        assertTrue(relatorio.contains("ESTAGIO"));
        assertTrue(relatorio.contains("MONITORIA"));
        assertTrue(relatorio.contains("PESQUISA"));
        assertTrue(relatorio.contains("REPRESENTACAO"));
    }

    @Test
    void testRelatorioSemAtividades() {
        controller.criarEstudante("Sergio", "12345678900", "senha1234567123456", "2023.1");
        String relatorio = controller.gerarMapaCreditosAtividades("12345678900", "senha1234567123456");
        assertTrue(relatorio.contains("0"));
    }

    @Test
    void testBonificacaoNegativa() {
        controller.criarEstudante("Julia", "12345678900", "senha1234567123456", "2023.1");
        assertThrows(IllegalArgumentException.class, () -> {
            controller.atualizarBonificacao("12345678900", "senha1234567123456", -5);
        });
    }

    @Test
    void testAlterarSenhaInvalida() {
        controller.criarEstudante("Carlos", "12345678900", "senha1234567Antiga", "2023.1");
        assertThrows(IllegalArgumentException.class, () -> {
            controller.alterarSenhaEstudante("12345678900", "senha1234567Antiga", "");
        });
    }

    @Test
    void testVerificarMetaComCreditoExato() {
        controller.criarEstudante("Luana", "12345678900", "senha1234567", "2023.1");
        controller.criarAtividadeEstagio("12345678900", "senha1234567", 300, "Empresa C");
        controller.criarAtividadeMonitoria("12345678900", "senha1234567", 10, "FMCC2");
        controller.criarAtividadePesquisaExtensao("12345678900", "senha1234567", 24, "Pet");
        assertTrue(controller.verificarMetaAlcancada("12345678900", "senha1234567"));
    }

    @Test
    void testVerificarMetaSemAtividades() {
        controller.criarEstudante("Leo", "12345678900", "senha1234567", "2023.1");
        assertFalse(controller.verificarMetaAlcancada("12345678900", "senha1234567"));
    }
    
    @Test
    public void testListarHistoricoQuandoVazio() {
    	controller.criarEstudante("Luana", "12345678900", "senha1234567", "2023.1");
        String resultado = controller.listarHistorico("12345678900", "senha1234567");
        
        assertEquals("", resultado);
    }

    @Test
    void testRankingBonificacaoComEmpate() {
        controller.criarEstudante("Ana", "12345678900", "senha123456", "2023.1");
        controller.criarEstudante("Bruna", "12345678911", "senha09876", "2023.1");
        controller.atualizarBonificacao("12345678900", "senha123456", 10);
        controller.atualizarBonificacao("12345678911", "senha09876", 10);

        List<String> ranking = controller.listarUsuariosRankingDicas();

        boolean contemAna = ranking.stream().anyMatch(str -> str.contains("Ana"));
        boolean contemBruna = ranking.stream().anyMatch(str -> str.contains("Bruna"));
        assertTrue(contemAna);
        assertTrue(contemBruna);
    }

    @Test
    void testMapaCreditosSemAtividades() {
        controller.criarEstudante("Claudio", "12345678900", "senha1234567", "2023.1");
        String mapa = controller.gerarMapaCreditosAtividades("12345678900", "senha1234567");
        assertTrue(mapa.contains("0"));
    }
}