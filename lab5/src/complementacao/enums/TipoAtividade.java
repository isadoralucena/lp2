package complementacao.enums;

import java.text.Normalizer;
import java.util.List;

/**
 * Enum que representa os tipos de atividades complementares.
 * Cada tipo possui uma lista de palavras-chave associadas que podem ser usadas
 * para identificar a atividade a partir de uma string de entrada fornecida pelo usuário.
 *
 *
 * @author isadora Lucena
 */
public enum TipoAtividade {
	PESQUISA_EXTENSAO(List.of("pesquisa", "extensao")),
    MONITORIA(List.of("monitoria", "ensino")),
    ESTAGIO(List.of("estagio", "trabalho")),
    REPRESENTACAO_ESTUDANTIL(List.of("representacao", "estudantil"));

	private final List<String> palavrasChave;

    TipoAtividade(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    
    /**
     * Verifica se o texto fornecido contém alguma palavra-chave do tipo de atividade.
     *
     * @param texto O texto a ser analisado.
     * @return true se contiver uma palavra-chave, false caso contrário.
     */
    public static TipoAtividade converterStringEmAtividade(String texto) {
    	String erro = "Atividade inválida";
	    if (texto == null) throw new IllegalArgumentException(erro);
        String textoNormalizado = removerAcentos(texto).toLowerCase();

        for (TipoAtividade atividade : TipoAtividade.values()) {
            if (atividade.contemPalavraChave(textoNormalizado)) {
                return atividade;
            }
        }

        throw new IllegalArgumentException(erro);
    }
    
    private boolean contemPalavraChave(String texto) {
        return palavrasChave.stream().anyMatch(texto::contains);
    }
    
    private static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "");
    }
}
