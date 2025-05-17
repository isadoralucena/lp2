package br.edu.ufcg.computacao.p2lp2.coisa;

import java.util.Objects;

/**
 * Representa um resumo de tema com um conteúdo associado.
 * Cada resumo possui um tema único que o identifica.
 *
 *
 * @author Isadora Lucena
 */
public class Resumo {
	private String tema;
	private String conteudo;
	
	/**
     * Constrói um resumo com o tema e conteúdo especificados.
     *
     * @param tema o tema do resumo
     * @param conteudo o conteúdo do resumo
     */
	public Resumo(String tema, String conteudo) {
        this.tema = tema;
        this.conteudo = conteudo;
    }
	
	/**
     * Obtém o tema do resumo.
     *
     * @return o tema do resumo
     */
	public String getTema() {
        return tema;
    }
	
	/**
    * Calcula o código hash do resumo com base no tema.
    *
    * @return o código hash do tema
    */
	@Override
	public int hashCode() {
		return Objects.hash(tema);
	}
	
	/**
     * Verifica a igualdade entre resumos.
     * Dois resumos são iguais se tiverem o mesmo tema.
     *
     * @param obj o objeto a ser comparado
     * @return true se os temas forem iguais, false caso contrário
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resumo other = (Resumo) obj;
		return Objects.equals(tema, other.tema);
	}
	/**
     * Retorna uma representação textual do resumo no formato "tema: conteúdo".
     *
     * @return uma string representando o resumo
     */
	@Override
	public String toString() {
		return tema + ": " + conteudo;
	}
}
