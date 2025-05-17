package complementacao.model.dica;

import complementacao.util.Validador;

/**
 * Representa um elemento de dica baseado em uma referência, contendo título, fonte, ano,
 * se foi conferida e um nível de importância. O valor de bônus é atribuído apenas se a referência for
 * conferida, limitado a {@link ElementoDica#MAXIMO_VALOR_BONUS}.
 * 
 * Implementa {@link ElementoDica}, oferecendo visualizações resumida e detalhada.
 * 
 * @author Isadora Lucena
 */
public class ElementoReferenciaDica implements ElementoDica{
	private int valor;
	private int importancia;
	private String titulo;
	private String fonte;
	private int ano;
	private boolean conferida;
	private static final int PONTOS_POR_REFERENCIA = 15;
	
	/**
	 * Cria um novo {@code ElementoReferenciaDica} com base nos dados fornecidos.
	 * 
	 * O valor do elemento é calculado automaticamente com base nos atributos informados.
	 * Caso o valor exceda o limite definido para bonificações, será ajustado para {@code MAXIMO_VALOR_BONUS}.
	 * 
	 * @param titulo      Título da referência.
	 * @param fonte       Fonte de onde a referência foi retirada.
	 * @param ano         Ano de publicação da referência.
	 * @param conferida   Define se a referência foi verificada pelo autor.
	 * @param importancia Nível de importância da referência, variando de 1 a 5.
	 * 
	 * @throws IllegalArgumentException se o título ou a fonte forem nulos ou vazios,
	 *         se o ano não for positivo ou se a importância estiver fora do intervalo permitido.
	 */
	public ElementoReferenciaDica(String titulo, String fonte, int ano, boolean conferida, int importancia) {
		Validador.validarString(titulo, "O título não pode ser nulo ou vazio.");
	    Validador.validarString(fonte, "A fonte não pode ser nula ou vazia.");
	    Validador.validarTempoPositivo(ano);
	    Validador.validarImportanciaDica(importancia);
		this.titulo = titulo.trim();
		this.fonte = fonte.trim();
		this.ano = ano;
		this.conferida = conferida;
		this.valor = gerarValor();
		if (valor > MAXIMO_VALOR_BONUS) this.valor = 50;
		this.importancia = importancia;
	}

	/**
     * Retorna uma visualização resumida da referência.
     * 
     * @return String com título, fonte e ano.
     */
	public String visualizacaoResumida() {
		return "Titulo: " + titulo + "\nFonte: " +fonte + "\nAno: "+ ano;
	}

	/**
     * Retorna uma visualização detalhada da referência,
     * incluindo se foi conferida e seu nível de importância.
     * 
     * @return String com todas as informações da referência.
     */
	public String visualizacaoDetalhada() {
		String conferidaTexto = "Não conferida";
		if (conferida) conferidaTexto = "Conferida";
			
		return visualizacaoResumida() + "\n" + conferidaTexto + "\nImportância: " +importancia;
	}
	
	/**
     * Retorna o valor atribuído à referência.
     * 
     * @return Valor de pontos do elemento.
     */
	public int getValor(){
		return valor;
	}

	/**
     * Gera o valor da referência com base na flag de conferência.
     * 
     * @return {@code PONTOS_POR_REFERENCIA} se conferida, caso contrário 0.
     */
	public int gerarValor() {
		if(conferida) return PONTOS_POR_REFERENCIA;
		return 0;
	}
}
