package complementacao.enums;

/**
 * Enumeração dos subtipos de pesquisa e extensão.
 * Representa diferentes programas de iniciação científica ou extensão universitária.
 * 
 * @author Isadora Lucena
 */
public enum SubtipoPesquisaExtensao {
	PET,
    PIBIC,
    PIVIC,
    PIBITI,
    PIVITI,
    PROBEX,
    PDI;
    
    /**
     * Converte uma string em um valor da enumeração {@code SubtipoPesquisaExtensao}.
     * A comparação ignora diferenças entre maiúsculas e minúsculas.
     *
     * @param texto a string a ser convertida
     * @return o subtipo correspondente
     * @throws IllegalArgumentException se a string não corresponde a nenhum subtipo válido
     */
	public static SubtipoPesquisaExtensao converterStringEmSubtipoPesquisaExtensao(String texto) {
		String erro = "Subtipo de pesquisa e extensão inválido";
	    if (texto == null) throw new IllegalArgumentException(erro);

	    String textoNormalizado = texto.trim().toUpperCase();
	    for (SubtipoPesquisaExtensao subtipo : SubtipoPesquisaExtensao.values()) {
	        if (subtipo.name().equals(textoNormalizado)) return subtipo;
	    }

	    throw new IllegalArgumentException(erro);
    }

}
