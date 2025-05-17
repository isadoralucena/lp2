package filmnow;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmeTest {
	
	private Filme filmeBase;
	
	@BeforeEach
	void setupFilme() {
		this.filmeBase = new Filme("Avatar", "2009", "Disney+");
	}
	
	@Test
    public void testConstrutorValido() {
        assertNotNull(filmeBase);
        assertEquals("Avatar", filmeBase.getNome());
        assertEquals("2009", filmeBase.getAnoLancamento());
        assertEquals("Disney+", filmeBase.getLocal());
    }

	@Test
	public void testConstrutorNomeNulo() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme(null, "2009", "Disney+");
	    }, "Nome não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorNomeVazio() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("", "2009", "Disney+");
	    }, "Nome não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorNomeEmBranco() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("   ", "2009", "Disney+");
	    }, "Nome não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorAnoNulo() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("Avatar", null, "Disney+");
	    }, "Ano de lançamento não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorAnoVazio() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("Avatar", "", "Disney+");
	    }, "Ano de lançamento não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorAnoEmBranco() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("Avatar", "   ", "Disney+");
	    }, "Ano de lançamento não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorLocalNulo() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("Avatar", "2009", null);
	    }, "Local não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorLocalVazio() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("Avatar", "2009", "");
	    }, "Local não pode ser nulo ou vazio");
	}

	@Test
	public void testConstrutorLocalEmBranco() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Filme("Avatar", "2009", "   ");
	    }, "Local não pode ser nulo ou vazio");
	}
    
    @Test
    public void testHashCodeValido() {
    	Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Avatar", "2009", "Disney+");
        assertEquals(filme1.hashCode(), filme2.hashCode());
    }
    
    @Test
    public void testHashCodeComNomesDiferentes() {
    	Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Titanic", "2009", "Disney+");
        assertNotEquals(filme1.hashCode(), filme2.hashCode());
    }
    
    @Test
    public void testHashCodeComAnosDiferentes() {
    	Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Avatar", "2005", "Disney+");
        assertNotEquals(filme1.hashCode(), filme2.hashCode());
    }
    
    @Test
    public void testHashCodeComLocaisDiferentes() {
    	Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Avatar", "2009", "Paramount");
        assertEquals(filme1.hashCode(), filme2.hashCode());
    }
    
    @Test
    public void testHashCodeMesmoObjeto() {
        Filme filme = new Filme("Avatar", "2009", "Disney+");
        assertEquals(filme.hashCode(), filme.hashCode());
    }
    
    @Test
    public void testHashCodeComObjetoNulo() {
        Filme filme = new Filme("Avatar", "2009", "Disney+");
        assertNotEquals(filme.hashCode(), 0);
    }

    @Test
    public void testHashCodeComOutroTipoDeObjeto() {
        Filme filme = new Filme("Avatar", "2009", "Disney+");
        String outroObjeto = "Avatar";
        assertNotEquals(filme.hashCode(), outroObjeto.hashCode());
    }
    
    @Test
    public void testEqualsMesmoObjeto() {
        Filme filme = new Filme("Avatar", "2009", "Disney+");
        assertEquals(filme, filme);
    }

    @Test
    public void testEqualsObjetosIguais() {
        Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Avatar", "2009", "Disney+");
        assertEquals(filme1, filme2);
    }

    @Test
    public void testEqualsComNomesDiferentes() {
        Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Titanic", "2009", "Disney+");
        assertNotEquals(filme1, filme2);
    }

    @Test
    public void testEqualsComAnosDiferentes() {
        Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Avatar", "2005", "Disney+");
        assertNotEquals(filme1, filme2);
    }

    @Test
    public void testEqualsComLocaisDiferentes() {
        Filme filme1 = new Filme("Avatar", "2009", "Disney+");
        Filme filme2 = new Filme("Avatar", "2009", "Paramount");
        assertEquals(filme1, filme2);
    }

    @Test
    public void testEqualsComObjetoNulo() {
        Filme filme = new Filme("Avatar", "2009", "Disney+");
        assertNotEquals(filme, null);
    }

    @Test
    public void testEqualsComOutroTipoDeObjeto() {
        Filme filme = new Filme("Avatar", "2009", "Disney+");
        String outroObjeto = "Avatar";
        assertNotEquals(filme, outroObjeto);
    }
    
    @Test
    public void testToString() {
    	assertEquals("Avatar, 2009\nDisney+", filmeBase.toString());
    }
}
