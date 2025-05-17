package complementacao.model.dica;

/**
 * Representa um elemento que compõe uma dica sobre atividades complementares.
 * Cada elemento pode ser visualizado de forma resumida ou detalhada, além de possuir
 * um valor numérico de bônus associado, limitado por {@code MAXIMO_VALOR_BONUS}.
 * 
 * Essa interface define os métodos necessários para qualquer tipo de elemento de dica.
 * 
 * O valor gerado por {@link #gerarValor()} deve respeitar o limite máximo definido
 * pela constante {@code MAXIMO_VALOR_BONUS}.
 * 
 * @author Isadora Lucena
 */
public interface ElementoDica {
	/**
	 * Valor máximo permitido para o bônus de um elemento de dica.
	 */
	public int MAXIMO_VALOR_BONUS = 50;

	/**
	 * Gera uma visualização resumida do elemento de dica.
	 * 
	 * @return String representando o conteúdo de forma resumida.
	 */
	public String visualizacaoResumida();

	/**
	 * Gera uma visualização detalhada do elemento de dica.
	 * 
	 * @return String representando o conteúdo de forma detalhada.
	 */
	public String visualizacaoDetalhada();

	/**
	 * Gera o valor de bônus associado ao elemento de dica.
	 * 
	 * @return Valor de bônus gerado.
	 */
	public int gerarValor();

	/**
	 * Retorna o valor atual de bônus do elemento de dica.
	 * 
	 * @return Valor de bônus.
	 */
	public int getValor();
}
