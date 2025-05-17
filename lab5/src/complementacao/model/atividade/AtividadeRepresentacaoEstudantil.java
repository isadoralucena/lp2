package complementacao.model.atividade;

import complementacao.enums.SubtipoRepresentacaoEstudantil;

/**
 * Representa uma atividade complementar do tipo representação estudantil.
 * Cada atividade desse tipo possui um subtipo específico definido por {@link SubtipoRepresentacaoEstudantil}.
 * Herda as regras de conversão de unidades para créditos da classe {@link Atividade}, com parâmetros fixos:
 * mínimo de 1 unidade, máximo de 2 créditos, com proporção de 1 unidade para 2 créditos.
 * 
 * @author Isadora Lucena
 */
public class AtividadeRepresentacaoEstudantil extends Atividade{
	private SubtipoRepresentacaoEstudantil subtipo;
	
	/**
	 * Cria uma nova atividade do tipo pesquisa ou extensão com base no subtipo fornecido.
	 * 
	 * @param id ID único da atividade.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas inicialmente.
	 * @param subtipo Nome do subtipo da atividade.
	 * @throws IllegalArgumentException se o subtipo informado não corresponder a um valor válido de {@link SubtipoRepresentacaoEstudantil}.
	 */
	public AtividadeRepresentacaoEstudantil(String id, int unidadeAcumulada, String subtipo) {
		super(id, unidadeAcumulada, 1, 2, 1, 2);
		this.subtipo = SubtipoRepresentacaoEstudantil.converterStringEmSubtipoPesquisaExtensao(subtipo);
	}
}
