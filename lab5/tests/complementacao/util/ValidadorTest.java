package complementacao.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ValidadorTest {

	@Test
    void testValidarCPFValido() {
        assertDoesNotThrow(() -> Validador.validarCPF("12345678901"));
    }

    @Test
    void testValidarCPFComMenosDigitos() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            Validador.validarCPF("12345"));
        assertEquals("CPF inválido.", e.getMessage());
    }
    
    @Test
    void testValidaIdValido() {
        assertDoesNotThrow(() -> Validador.validaId("ATV123"));
    }

    @Test
    void testValidaIdNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validador.validaId(null));
        assertEquals("ID inválido.", exception.getMessage());
    }

    @Test
    void testValidaIdVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validador.validaId(""));
        assertEquals("ID inválido.", exception.getMessage());
    }

    @Test
    void testValidaIdEspacos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validador.validaId("   "));
        assertEquals("ID inválido.", exception.getMessage());
    }

    @Test
    void testValidarCPFComCaracteresNaoNumericos() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            Validador.validarCPF("12345abc901"));
        assertEquals("CPF inválido.", e.getMessage());
    }

    @Test
    void testValidarCPFComEspacosOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarCPF(null));
        assertThrows(IllegalArgumentException.class, () -> Validador.validarCPF("   "));
    }

    @Test
    void testValidarSenhaValida() {
        assertDoesNotThrow(() -> Validador.validarSenha("senhaSegura"));
    }

    @Test
    void testValidarSenhaInvalida() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            Validador.validarSenha("123"));
        assertTrue(e.getMessage().contains("A senha deve ter pelo menos"));
    }

    @Test
    void testValidarStringNulaOuVazia() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarString(null, "Erro"));
        assertThrows(IllegalArgumentException.class, () -> Validador.validarString("  ", "Erro"));
    }

    @Test
    void testValidarStringValida() {
        assertDoesNotThrow(() -> Validador.validarString("texto", "Erro"));
    }

    @Test
    void testValidarTextoDicaValido() {
        assertDoesNotThrow(() -> Validador.validarTextoDica("Essa é uma dica válida"));
    }

    @Test
    void testValidarTextoDicaMuitoLongo() {
        String longo = "a".repeat(501);
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            Validador.validarTextoDica(longo));
        assertTrue(e.getMessage().contains("no máximo"));
    }

    @Test
    void testValidarTextoDicaInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarTextoDica("  "));
        assertThrows(IllegalArgumentException.class, () -> Validador.validarTextoDica(null));
    }

    @Test
    void testValidarTempoPositivoValido() {
        assertDoesNotThrow(() -> Validador.validarTempoPositivo(10));
    }

    @Test
    void testValidarTempoNegativoOuZero() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarTempoPositivo(0));
        assertThrows(IllegalArgumentException.class, () -> Validador.validarTempoPositivo(-1));
    }

    @Test
    void testValidarUnidadeAcumuladaValida() {
        assertDoesNotThrow(() -> Validador.validarUnidadeAcumuladaPositiva(2));
    }

    @Test
    void testValidarUnidadeAcumuladaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarUnidadeAcumuladaPositiva(0));
    }

    @Test
    void testValidarImportanciaValida() {
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            assertDoesNotThrow(() -> Validador.validarImportanciaDica(finalI));
        }
    }

    @Test
    void testValidarImportanciaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarImportanciaDica(0));
        assertThrows(IllegalArgumentException.class, () -> Validador.validarImportanciaDica(6));
    }

    @Test
    void testValidarEConverterDataValida() {
        LocalDate date = Validador.validarEConverterData("07/04/2025");
        assertEquals(LocalDate.of(2025, 4, 7), date);
    }

    @Test
    void testValidarEConverterDataInvalida() {
        assertThrows(IllegalArgumentException.class, () -> Validador.validarEConverterData("2025/04/07"));
        assertThrows(IllegalArgumentException.class, () -> Validador.validarEConverterData("99/99/9999"));
    }
}