package complementacao.model.dica;

import complementacao.util.Validador;

/**
 * Representa um elemento de dica textual, contendo um texto cujo valor de bônus é calculado
 * com base na quantidade de caracteres. O texto deve ter pelo menos 100 caracteres para gerar pontos.
 * A cada 10 caracteres é atribuído 1 ponto, limitado a {@link ElementoDica#MAXIMO_VALOR_BONUS}.
 * 
 * Implementa {@link ElementoDica}, oferecendo visualizações resumida e detalhada.
 * A visualização detalhada exibe também o tamanho do texto em caracteres.
 * 
 * @author Isadora Lucena
 */
public class ElementoTextoDica implements ElementoDica{
	private String texto;
	private static final int CARACTERES_REFERENCIA = 10;
	private static final int MIN_CARACTERES = 100;
	private int valor;
	
	/**
	 * Cria um novo {@code ElementoTextoDica} com base no texto fornecido.
	 *
	 * O valor do elemento é calculado automaticamente com base na quantidade de caracteres do texto.
	 * Caso ultrapasse o limite máximo de bonificação, o valor é ajustado para {@code MAXIMO_VALOR_BONUS}.
	 * 
	 * @param texto Conteúdo textual do elemento da dica.
	 * @throws IllegalArgumentException se o texto for nulo, vazio ou considerado inválido.
	 */
	public ElementoTextoDica(String texto) {
		Validador.validarTextoDica(texto);
		this.texto = texto.trim();
		this.valor = gerarValor();
		if (valor > MAXIMO_VALOR_BONUS) this.valor = 50;
	}
	
	/**
	 * Gera o valor de bônus com base no tamanho do texto.
	 * Se o texto tiver ao menos 100 caracteres, cada 10 caracteres geram 1 ponto.
	 * 
	 * @return o valor de bônus calculado
	 */
	public int gerarValor() {
		int tamanhoTexto = texto.length();
		if (MIN_CARACTERES <= tamanhoTexto) {
			return (int)tamanhoTexto/CARACTERES_REFERENCIA;
		}
		return 0;
	}
	
	/**
	 * Retorna o valor de bônus atribuído ao elemento textual.
	 * 
	 * @return o valor atual do elemento
	 */
	public int getValor(){
		return valor;
	}
	
	/**
	 * Retorna uma visualização resumida da dica textual.
	 * 
	 * @return uma string contendo apenas o texto da dica
	 */
	public String visualizacaoResumida() {
		return "Texto: " + texto;
	}

	/**
	 * Retorna uma visualização detalhada da dica textual.
	 * 
	 * @return uma string com o texto e o número de caracteres do conteúdo
	 */
	public String visualizacaoDetalhada() {
		int tamanhoTexto = texto.length();
		return visualizacaoResumida() + "\nTamanho de caracteres: " + tamanhoTexto;
	}
}