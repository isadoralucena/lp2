package complementacao.model.atividade;

import complementacao.util.Validador;

/**
 * Representa uma atividade complementar do tipo monitoria.
 * Cada atividade de monitoria está vinculada a uma disciplina específica, cujo nome deve ser informado.
 * Herda as regras de conversão de unidades para créditos da classe {@link Atividade}, com parâmetros específicos:
 * mínimo de 1 unidade, máximo de 16 créditos, com a proporção de 1 unidade para 4 créditos.
 * 
 * @author Isadora Lucena
 */
public class AtividadeMonitoria extends Atividade{
	private String nomeDisciplina;
	
	/**
	 * Cria uma nova atividade de monitoria com os parâmetros de conversão específicos.
	 *
	 * @param id ID único da atividade.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas inicialmente.
	 * @param nomeDisciplina Nome da disciplina associada à monitoria. Não pode ser nulo ou vazio.
	 * 
	 * @throws IllegalArgumentException se o nome da disciplina for nulo ou vazio.
	 */
	public AtividadeMonitoria(String id, int unidadeAcumulada, String nomeDisciplina) {
		super(id, unidadeAcumulada, 1, 16, 1, 4);
		Validador.validarString(nomeDisciplina, "Nome da disciplina não pode ser nulo ou vazio");
		this.nomeDisciplina = nomeDisciplina.trim();
	}
	
}
