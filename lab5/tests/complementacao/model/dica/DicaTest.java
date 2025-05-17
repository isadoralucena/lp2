package complementacao.model.dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import complementacao.enums.TipoAtividade;

class DicaTest {

	private Dica dica;

    @BeforeEach
    void setUp() {
        dica = new Dica("Isadora", TipoAtividade.ESTAGIO);
    }

    @Test
    void testConstrutorValido() {
        assertDoesNotThrow(() -> new Dica("Autor", TipoAtividade.MONITORIA));
    }

    @Test
    void testConstrutorComNomeNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Dica(null, TipoAtividade.MONITORIA));
        assertEquals("Nome do autor não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testConstrutorComNomeVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Dica("   ", TipoAtividade.MONITORIA));
        assertEquals("Nome do autor não pode ser nulo ou vazio.", e.getMessage());
    }

    @Test
    void testGetBonusUltimoElementoSemElementos() {
        assertEquals(0, dica.getBonusUltimoElementoDica());
    }
    
    @Test
    void testGetBonusUltimoElementoComElementos() {
        Dica dica = new Dica("Ana", TipoAtividade.MONITORIA);
        ElementoDica e1 = new ElementoTextoDica("A".repeat(100));
        ElementoDica e2 = new ElementoTextoDica("B".repeat(200)); 
        dica.adicionarElementoDica(e1);
        dica.adicionarElementoDica(e2);
        assertEquals(20, dica.getBonusUltimoElementoDica());
    }
    
    @Test
    void testAdicionarElementoDica() {
        Dica dica = new Dica("João", TipoAtividade.MONITORIA);
        ElementoDica elemento = new ElementoTextoDica("Esse é um texto com mais de 100 caracteres. " + "X".repeat(80));
        
        dica.adicionarElementoDica(elemento);

        assertEquals(elemento.getValor(), dica.getBonusUltimoElementoDica());

        String visualizacao = dica.visualizacaoResumida();
        assertTrue(visualizacao.contains("Texto: Esse é um texto"));
    }
    @Test
    void testVisualizacaoResumidaRetornaSomenteAutorQuandoSemElementos() {
        Dica dica = new Dica("Lucas", TipoAtividade.MONITORIA);
        String esperado = "Autor: Lucas\n";
        assertEquals(esperado, dica.visualizacaoResumida());
    }

    @Test
    void testVisualizacaoResumidaIncluiAutorEPrimeirasPalavrasDoTexto() {
        Dica dica = new Dica("Lucas", TipoAtividade.MONITORIA);
        dica.adicionarElementoDica(new ElementoTextoDica("Dica bem útil sobre projetos." + "X".repeat(80)));

        String visualizacao = dica.visualizacaoResumida();
        assertTrue(visualizacao.contains("Autor: Lucas"));
        assertTrue(visualizacao.contains("Texto: Dica bem útil"));
    }

    @Test
    void testVisualizacaoDetalhadaIncluiTamanhoDeCaracteres() {
        Dica dica = new Dica("Lucas", TipoAtividade.MONITORIA);
        dica.adicionarElementoDica(new ElementoTextoDica("Texto detalhado." + "X".repeat(85)));

        String visualizacao = dica.visualizacaoDetalhada();
        assertTrue(visualizacao.contains("Tamanho de caracteres:"));
    }

    @Test
    void testVisualizacaoResumidaComMultiplosElementos() {
        String texto1 = "A".repeat(100);
        String texto2 = "B".repeat(110);

        dica.adicionarElementoDica(new ElementoTextoDica(texto1));
        dica.adicionarElementoDica(new ElementoTextoDica(texto2));

        String esperado = "Autor: Isadora\n" +
                "Texto: " + texto1 + "\n" +
                "Texto: " + texto2;

        assertEquals(esperado, dica.visualizacaoResumida());
    }

    @Test
    void testVisualizacaoDetalhadaComElementoUnico() {
        String texto = "D".repeat(105);
        dica.adicionarElementoDica(new ElementoTextoDica(texto));

        String esperado = "Autor: Isadora\n" +
                "Texto: " + texto + "\n" +
                "Tamanho de caracteres: 105";

        assertEquals(esperado, dica.visualizacaoDetalhada());
    }
}