package complementacao.model.dica;

import complementacao.util.Validador;

/**
* Representa um elemento de dica multimídia, com link, cabeçalho e duração em segundos.
* O valor de bônus é calculado com base no tempo (1 ponto a cada minuto), limitado a {@link ElementoDica#MAXIMO_VALOR_BONUS}.
* 
* Implementa {@link ElementoDica}, oferecendo visualizações resumida e detalhada.
* 
* @author Isadora Lucena
*/
public class ElementoMultimidiaDica implements ElementoDica{
	private String link;
	private int tamanhoMaterial;
	private static final int PONTOS_REFERENCIA = 5;
	private String cabecalho;
	private int valor;
	
	/**
	 * Constrói um novo {@code ElementoMultimidiaDica}.
	 * 
	 * @param link o link para o conteúdo multimídia
	 * @param cabecalho o título ou descrição curta do material
	 * @param tamanhoMaterial o tempo total do material em segundos
	 */
	public ElementoMultimidiaDica(String link, String cabecalho, int tamanhoMaterial) {
		Validador.validarString(link, "O link não pode ser nulo ou vazio.");
	    Validador.validarString(cabecalho, "O cabeçalho não pode ser nulo ou vazio.");
	    Validador.validarTempoPositivo(tamanhoMaterial);
		this.link = link.trim();
		this.cabecalho = cabecalho.trim();
	    this.tamanhoMaterial = tamanhoMaterial;
		this.valor = gerarValor();
		if (valor > MAXIMO_VALOR_BONUS) this.valor = 50;
	}

	/**
	 * Retorna uma visualização resumida do elemento multimídia,
	 * contendo o link e o cabeçalho.
	 */
	public String visualizacaoResumida() {
		return "Link: " + link + "\nCabeçalho: " + cabecalho;
	}
	
	/**
	 * Retorna o valor de bônus atribuído a este elemento.
	 */
	public int getValor(){
		return valor;
	}

	/**
	 * Retorna uma visualização detalhada do elemento multimídia,
	 * contendo também a duração em segundos.
	 */
	public String visualizacaoDetalhada() {
		return visualizacaoResumida() + "\nTempo em segundos: " + tamanhoMaterial;
	}

	/**
	 * Gera o valor de bônus com base na duração do material.
	 * São atribuídos {@value #PONTOS_REFERENCIA} pontos por minuto completo.
	 * 
	 * @return o valor de bônus gerado, limitado a {@link ElementoDica#MAXIMO_VALOR_BONUS}
	 */
	public int gerarValor() {
		int tamanhoMaterialMinutos = this.tamanhoMaterial / 60;
		return tamanhoMaterialMinutos * PONTOS_REFERENCIA;
	}
}
