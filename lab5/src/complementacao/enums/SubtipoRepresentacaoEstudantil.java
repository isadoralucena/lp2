package complementacao.enums;

import java.text.Normalizer;

/**
 * Enumeração dos subtipos de representação estudantil.
 * Pode representar a Diretoria ou Comissão de uma organização estudantil.
 * 
 * 
 * @author Isadora lucena
 */
public enum SubtipoRepresentacaoEstudantil {
	DIRETORIA,
	COMISSAO;
	
	/**
     * Converte uma string para o subtipo correspondente, ignorando acentos e capitalização.
     *
     * @param texto A string a ser convertida.
     * @return O subtipo correspondente.
     * @throws IllegalArgumentException Se a string não corresponder a nenhum subtipo válido.
     */
    public static SubtipoRepresentacaoEstudantil converterStringEmSubtipoPesquisaExtensao(String texto) {
    	String erro = "Subtipo de representação estudantil inválido.";

        if (texto == null) throw new IllegalArgumentException(erro);

        String textoNormalizado = removerAcentos(texto).trim().toLowerCase();

        for (SubtipoRepresentacaoEstudantil subtipo : SubtipoRepresentacaoEstudantil.values()) {
            if (subtipo.name().toLowerCase().equals(textoNormalizado)) {
                return subtipo;
            }
        }
        throw new IllegalArgumentException(erro);
    }
    
    private static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "");
    }
}
