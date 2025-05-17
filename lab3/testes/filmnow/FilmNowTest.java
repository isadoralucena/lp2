package filmnow;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmNowTest {
	private Filme filmeBase;
	private FilmNow fn;
	
	@BeforeEach
	void setupFilme() {
		this.filmeBase = new Filme("Avatar", "2009", "Disney+");
		this.fn = new FilmNow();
	}
	
	@Test
	void testAdicionaFilmePosicaoVazia() {
		assertEquals(fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmePosicaoExistente() {
		Filme filme = new Filme("20 dias em Mariupol", "2023", "Cinema");
		assertEquals(fn.cadastraFilme(1, filme.getNome(), filme.getAnoLancamento(), filme.getLocal()), "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmeDuplicado() {
		assertEquals(fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "FILME ADICIONADO");
		assertEquals(fn.cadastraFilme(3, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "FILME JÁ ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmeDuplicadoLocalDiferente() {
		Filme filme = new Filme("Avatar", "2009", "Popcornflix");
		assertEquals(fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "FILME ADICIONADO");
		assertEquals(fn.cadastraFilme(3, filme.getNome(), filme.getAnoLancamento(), filme.getLocal()), "FILME JÁ ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmePosicaoLimite() {
		assertEquals(fn.cadastraFilme(100, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmePosicaoLimiteSuperior() {
		assertEquals(fn.cadastraFilme(101, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testAdicionaFilmePosicaoLimiteInferior() {
		assertEquals(fn.cadastraFilme(0, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testAdicionaFilmeLocalVazio() {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	Filme filme = new Filme("20 dias em Mariupol", "2023", "");
	        fn.cadastraFilme(1, filme.getNome(), filme.getAnoLancamento(), filme.getLocal());
	    }, "Local não pode ser nulo ou vazio");
	}

	@Test
	void testAdicionaFilmeAnoLancamentoVazio() {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	Filme filme = new Filme("20 dias em Mariupol", "", "Cinema");
	        fn.cadastraFilme(1, filme.getNome(), filme.getAnoLancamento(), filme.getLocal());
	    }, "Ano de lançamento não pode ser nulo ou vazio");
	}

	@Test
	void testAdicionaNomeVazio() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        fn.cadastraFilme(1, "", "2023", "Cinema");
	    }, "Nome não pode ser nulo ou vazio");
	}

	
	@Test
	void testDetalhaFilmeTodosDados() {
		assertEquals(fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal()), "FILME ADICIONADO");
		assertEquals("Avatar, 2009\nDisney+", fn.detalhaFilme(1));
	}
	
	@Test
	void testDetalhaFilmeSemAnoLancamento() {
		assertThrows(IllegalArgumentException.class, () -> {
			fn.cadastraFilme(1, "20 dias em Mariupol", "", "Cinema");
			assertEquals("20 dias em Mariupol\nCinema", fn.detalhaFilme(1));
		});
	}
	
	@Test
	void testDetalhaFilmeEmPosicaoSemFilme() {
		assertEquals("POSIÇÃO INVÁLIDA", fn.detalhaFilme(100));;
	}
	
	@Test
	void testDetalhaFilmePosicaoLimiteInferior() {
		assertEquals("POSIÇÃO INVÁLIDA", fn.detalhaFilme(0));
	}
	
	@Test
	void testDetalhaFilmePosicaoLimite() {
		fn.cadastraFilme(100, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal());
		assertEquals("Avatar, 2009\nDisney+", fn.detalhaFilme(100));
	}
	
	@Test
	void testDetalhaFilmePosicaoLimiteSuperior() {
		assertEquals("POSIÇÃO INVÁLIDA", fn.detalhaFilme(101));
	}
	
	@Test
	void testDetalhaFilmeDaHotlist() {
		fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal());
		assertEquals(fn.atribuirHot(1, 1), "FILME CADASTRADO NA HOTLIST NA POSIÇÃO 1!");
		assertEquals("🔥 Avatar, 2009\nDisney+", fn.detalhaFilme(1));
	}
	
	@Test
	void testAtribuirHotMesmoFilme() {
		fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal());
		fn.atribuirHot(1, 1);
		assertEquals(fn.atribuirHot(1, 2), "FILME JÁ ADICIONADO");
	}
	
	@Test
	void testAtribuirHotPosicaoLimiteDaHotlist() {
		fn.cadastraFilme(2, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal());
		assertEquals(fn.atribuirHot(2, 10), "FILME CADASTRADO NA HOTLIST NA POSIÇÃO 10!");
	}
	
	@Test
	void testAtribuirHotPosicaoLimiteSuperiorDaHotlist() {
		assertEquals(fn.atribuirHot(2, 11), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testAtribuirHotPosicaoLimiteInferiorDaHotlist() {
		assertEquals(fn.atribuirHot(2, 0), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testRemoverHot() {
		fn.cadastraFilme(1, filmeBase.getNome(), filmeBase.getAnoLancamento(), filmeBase.getLocal());
		fn.atribuirHot(1, 1);
		assertEquals("🔥 Avatar, 2009\nDisney+", fn.detalhaFilme(1));
		assertEquals(fn.removerHot(1), "FILME REMOVIDO NA HOTLIST");
		assertEquals("Avatar, 2009\nDisney+", fn.detalhaFilme(1));
	}
}
