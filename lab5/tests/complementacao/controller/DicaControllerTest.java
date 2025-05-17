package complementacao.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DicaControllerTest {

	private DicaController dicaController;
    private UsuarioController usuarioController;

    private final String CPF_VALIDO = "12345678900";
    private final String SENHA_VALIDA = "senha123";
    private final String NOME_USUARIO = "Isadora";

    @BeforeEach
    public void setUp() {
        usuarioController = new UsuarioController();
        usuarioController.criarEstudante(NOME_USUARIO, CPF_VALIDO, SENHA_VALIDA, "1234567");

        dicaController = new DicaController(usuarioController);
    }
    
    @Test
    public void testAdicionarElementoTextoDicaComTextoVazio() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        assertThrows(IllegalArgumentException.class, () -> {
            dicaController.adicionarElementoTextoDica(CPF_VALIDO, SENHA_VALIDA, 0, "");
        });
    }

    @Test
    public void testAdicionarElementoTextoDicaComTextoNulo() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        assertThrows(IllegalArgumentException.class, () -> {
            dicaController.adicionarElementoTextoDica(CPF_VALIDO, SENHA_VALIDA, 0, null);
        });
    }
    
    @Test
    public void testAdicionarMuitosElementosTextoNaMesmaDica() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        for (int i = 0; i < 1000; i++) {
            boolean resultado = dicaController.adicionarElementoTextoDica(CPF_VALIDO, SENHA_VALIDA, 0, "Texto #" + i);
            assertTrue(resultado);
        }
    }
    
    @Test
    public void testListarDicasSemNenhumaDicaAdicionada() {
        List<String> dicas = dicaController.listarDicas();
        assertTrue(dicas.isEmpty());
    }
    
    @Test
    public void testAdicionarElementoMultimidiaComUrlVazia() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        assertThrows(IllegalArgumentException.class, () -> {
            dicaController.adicionarElementoMultimidiaDica(CPF_VALIDO, SENHA_VALIDA, 0, "", "Sem URL", 5);
        });
    }
    
    @Test
    public void testAdicionarVariosTiposDeElementoDica() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");

        boolean resultadoTexto = dicaController.adicionarElementoTextoDica(CPF_VALIDO, SENHA_VALIDA, 0, "Texto de apoio");
        assertTrue(resultadoTexto);

        boolean resultadoMultimidia = dicaController.adicionarElementoMultimidiaDica(CPF_VALIDO, SENHA_VALIDA, 0,
                "https://video.com", "Vídeo explicativo", 5);
        assertTrue(resultadoMultimidia);

        boolean resultadoReferencia = dicaController.adicionarElementoReferenciaDica(CPF_VALIDO, SENHA_VALIDA, 0,
                "Livro", "Editora", 2030, true, 5);
        assertTrue(resultadoReferencia);
    }

    
    @Test
    public void testAdicionarElementoReferenciaComAnoInvalido() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        assertThrows(IllegalArgumentException.class, () -> {
            dicaController.adicionarElementoReferenciaDica(CPF_VALIDO, SENHA_VALIDA, 0,
                    "Livro", "Editora", -100, true, 5);
        });
    }
    
    @Test
    public void testAdicionarDicaComSenhaIncorretaLancaExcecao() {
        assertThrows(SecurityException.class, () -> {
            dicaController.adicionarDica(CPF_VALIDO, "senhaErrada", "monitoria");
        });
    }
    
    @Test
    public void testAdicionarDicaComTemaCaseInsensitive() {
        assertDoesNotThrow(() -> {
            dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "MONITORIA");
            dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "Monitoria");
            dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        });
    }

    @Test
    public void testAdicionarDicaComDadosValidos() {
        int index = dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        assertEquals(0, index);
    }

    @Test
    public void testAdicionarDicaComCpfInvalidoLancaSecurityException() {
        assertThrows(SecurityException.class, () -> {
            dicaController.adicionarDica("00000000000", SENHA_VALIDA, "DESENVOLVIMENTO");
        });
    }

    @Test
    public void testAdicionarDicaComTemaInvalidoLancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "TEMA_INEXISTENTE");
        });
    }

    @Test
    public void testAdicionarElementoTextoDicaComSucesso() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        boolean resultado = dicaController.adicionarElementoTextoDica(CPF_VALIDO, SENHA_VALIDA, 0, "Use boas práticas!");
        assertTrue(resultado);
    }

    @Test
    public void testAdicionarElementoTextoDicaComPosicaoInvalidaLancaExcecao() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dicaController.adicionarElementoTextoDica(CPF_VALIDO, SENHA_VALIDA, 10, "Texto fora do índice");
        });
    }

    @Test
    public void testAdicionarElementoMultimidiaDica() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        boolean adicionado = dicaController.adicionarElementoMultimidiaDica(CPF_VALIDO, SENHA_VALIDA, 0,
                "https://video.com", "Vídeo explicativo", 5);
        assertTrue(adicionado);
    }

    @Test
    public void testAdicionarElementoReferenciaDica() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        boolean adicionado = dicaController.adicionarElementoReferenciaDica(CPF_VALIDO, SENHA_VALIDA, 0,
                "Livro Java", "Editora ABC", 2020, true, 5);
        assertTrue(adicionado);
    }

    @Test
    public void testListarDicasRetornaResumo() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        List<String> dicas = dicaController.listarDicas();
        assertEquals(1, dicas.size());
    }

    @Test
    public void testListarDicaDetalhesComIndiceValido() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");
        String detalhes = dicaController.listarDicaDetalhes(0);
        assertNotNull(detalhes);
    }

    @Test
    public void testListarDicaDetalhesComIndiceInvalido() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dicaController.listarDicaDetalhes(99);
        });
    }

    @Test
    public void testAdicionarElementoDicaComAutenticacaoInvalida() {
        dicaController.adicionarDica(CPF_VALIDO, SENHA_VALIDA, "monitoria");

        assertThrows(SecurityException.class, () -> {
            dicaController.adicionarElementoTextoDica("00000000000", SENHA_VALIDA, 0, "Texto de dica");
        });
    }
}