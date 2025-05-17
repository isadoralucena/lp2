package complementacao.model.atividade;

import complementacao.enums.SubtipoPesquisaExtensao;

/**
 * Representa uma atividade complementar do tipo pesquisa ou extensão.
 * Cada atividade desse tipo possui um subtipo específico definido por {@link SubtipoPesquisaExtensao}.
 * Herda as regras de conversão de unidades para créditos da classe {@link Atividade}, com parâmetros fixos:
 * mínimo de 0 unidades, máximo de 18 créditos, com proporção de 12 unidades para 10 créditos.
 * 
 * @author Isadora Lucena
 */
public class AtividadePesquisaExtensao extends Atividade{
	private SubtipoPesquisaExtensao subtipo;
	
	/**
	 * Cria uma nova atividade do tipo pesquisa ou extensão com base no subtipo informado.
	 *
	 * @param id ID único da atividade.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas inicialmente.
	 * @param subtipo String que representa o subtipo da atividade.
	 * 
	 * @throws IllegalArgumentException se o subtipo informado não corresponder a um valor válido de {@link SubtipoPesquisaExtensao}.
	 */
	public AtividadePesquisaExtensao(String id, int unidadeAcumulada, String subtipo) {
		super(id, unidadeAcumulada, 0, 18, 12, 10);
		this.subtipo = SubtipoPesquisaExtensao.converterStringEmSubtipoPesquisaExtensao(subtipo);
	}
}
