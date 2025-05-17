package complementacao.model.atividade;

import complementacao.util.Validador;

/**
 * Representa uma atividade complementar do tipo estágio.
 * Essa atividade está vinculada a uma empresa, cujo nome deve ser informado e validado no momento da criação.
 * Herda as regras de conversão de unidades para créditos da classe {@link Atividade}, com parâmetros específicos para estágio:
 * 300 unidades mínimas, até 18 créditos, com a proporção de 60 unidades para 1 crédito.
 * 
 * @author Isadora Lucena
 */
public class AtividadeEstagio extends Atividade{
	private String nomeEmpresa;
	
	/**
	 * Cria uma nova atividade de estágio com os parâmetros de conversão específicos.
	 * 
	 * @param id ID único da atividade.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas inicialmente.
	 * @param nomeEmpresa Nome da empresa onde o estágio foi realizado. Não pode ser nulo ou vazio.
	 * 
	 * @throws IllegalArgumentException se o nome da empresa for nulo ou vazio.
	 */
	public AtividadeEstagio(String id, int unidadeAcumulada, String nomeEmpresa) {
		super(id, unidadeAcumulada, 300, 18, 60, 1);
		Validador.validarString(nomeEmpresa, "Nome da empresa não pode ser nulo ou vazio");
		this.nomeEmpresa = nomeEmpresa.trim();
	}	
}
