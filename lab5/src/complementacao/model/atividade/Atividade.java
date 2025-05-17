package complementacao.model.atividade;

import complementacao.util.Validador;

/**
 * Representa uma atividade complementar no sistema.
 * Cada atividade é identificada por um ID e acumula uma certa quantidade de unidades que são convertidas em créditos.
 * A classe fornece métodos para acessar o ID, os créditos e definir uma descrição e documentação comprobatória.
*  A conversão de unidades em créditos é feita com base em parâmetros de conversão definidos no momento da criação da atividade, 
 * respeitando limites mínimos de unidade e máximos de créditos.
 * 
 * @author Isadora Lucena
 */

public abstract class Atividade {
	private String descricao;
	private String documentacaoComprobatoria;
	private String id;
	private int unidadeAcumulada;
	private final int quantidadeMinimaUnidadeAcumulada;
	private final int quantidadeMaximaCreditos;
	private final int referenciaUnidadeAcumulada;
	private final int referenciaCreditos;
	private int creditos;
	
	/**
	 * Cria uma nova atividade com os critérios de conversão definidos.
	 *
	 * @param id ID único da atividade.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas inicialmente.
	 * @param quantidadeMinimaUnidadeAcumulada Quantidade mínima de unidades necessária para gerar créditos.
	 * @param quantidadeMaximaCreditos Limite máximo de créditos que podem ser atribuídos à atividade.
	 * @param referenciaUnidadeAcumulada Quantidade de unidades que geram um determinado número de créditos.
	 * @param referenciaCreditos Quantidade de créditos concedida a cada grupo de unidades especificado.
	 */
	public Atividade(String id, int unidadeAcumulada, int quantidadeMinimaUnidadeAcumulada, int quantidadeMaximaCreditos, int referenciaUnidadeAcumulada, int referenciaCreditos) {
		Validador.validaId(id);
		this.id = id.trim();
		this.unidadeAcumulada = unidadeAcumulada;
		this.quantidadeMinimaUnidadeAcumulada = quantidadeMinimaUnidadeAcumulada;
		this.quantidadeMaximaCreditos = quantidadeMaximaCreditos;
		Validador.validarUnidadeAcumuladaPositiva(unidadeAcumulada);
		this.referenciaUnidadeAcumulada = referenciaUnidadeAcumulada;
		this.referenciaCreditos = referenciaCreditos;
		this.creditos = calculaCreditos();
	}
	
	/**
	 * Retorna o ID da atividade.
	 *
	 * @return ID da atividade.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Retorna a quantidade máxima de créditos permitida para esta atividade.
	 *
	 * @return Valor máximo de créditos.
	 */
	public int getCreditoMaximo() {
		return quantidadeMaximaCreditos;
	}
	
	/**
	 * Retorna a quantidade mínima de unidades acumuladas necessárias para gerar créditos.
	 *
	 * @return Quantidade mínima de unidade acumulada.
	 */
	public int getQuantidadeMinimaUnidadeAcumulada() {
	    return quantidadeMinimaUnidadeAcumulada;
	}
	
	/**
	 * Retorna a quantidade de unidades necessárias para gerar os créditos de referência.
	 *
	 * @return Referência de unidade acumulada.
	 */
	public int getReferenciaUnidadeAcumulada() {
	    return referenciaUnidadeAcumulada;
	}

	/**
	 * Retorna a quantidade de créditos concedida por grupo de referência de unidades.
	 *
	 * @return Referência de créditos.
	 */
	public int getReferenciaCreditos() {
	    return referenciaCreditos;
	}

	/**
	 * Retorna a quantidade de unidades acumuladas atualmente.
	 *
	 * @return Unidade acumulada.
	 */
	public int getUnidadeAcumulada() {
	    return unidadeAcumulada;
	}

	/**
	 * Define a descrição da atividade.
	 *
	 * @param descricao Texto descritivo da atividade.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Define a documentação que comprova a realização da atividade.
	 *
	 * @param documentacaoComprobatoria Documento comprobatório.
	 */
	public void setDocumentacaoComprobatoria(String documentacaoComprobatoria) {
		this.documentacaoComprobatoria = documentacaoComprobatoria;
	}
	
	/**
	 * Retorna a quantidade de créditos atribuída à atividade.
	 *
	 * @return Número de créditos gerados.
	 */
	public int getCreditos() {
		return creditos;
	}
	
	/**
	 * Calcula os créditos com base nas unidades acumuladas e nos critérios definidos.
	 * Se as unidades forem insuficientes para gerar créditos, o resultado será zero.
	 * A quantidade final é limitada ao valor máximo de créditos permitido.
	 *
	 * @return Créditos calculados de forma proporcional às unidades acumuladas.
	 */
	private int calculaCreditos() {
		if (unidadeAcumulada < quantidadeMinimaUnidadeAcumulada) return 0;
		int creditosCalculados = (unidadeAcumulada / referenciaUnidadeAcumulada ) * referenciaCreditos;
		
		return Math.min(creditosCalculados, quantidadeMaximaCreditos);
	}
}
