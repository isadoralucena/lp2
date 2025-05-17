package filmnow;

import java.util.Objects;

/**
 * Representa um filme com nome, ano de lançamento e local de exibição.
 * Garante que os atributos essenciais não sejam nulos ou vazios.
 * 
 * @author Isadora Lucena
 */
public class Filme {
	private String nome;
	private String anoLancamento;
	private String local;
	
	/**
     * Constrói um filme com nome, ano de lançamento e local de exibição.
     * 
     * @param nome o nome do filme (não pode ser nulo ou vazio)
     * @param anoLancamento o ano de lançamento do filme (não pode ser nulo ou vazio)
     * @param local o local de exibição do filme (não pode ser nulo ou vazio)
     * @throws IllegalArgumentException se algum dos parâmetros for inválido
     */
	public Filme(String nome, String anoLancamento, String local) {
		if (nome == null || nome.isBlank()) {
	        throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
	    }
	    if (anoLancamento == null || anoLancamento.isBlank()) {
	        throw new IllegalArgumentException("Ano de lançamento não pode ser nulo ou vazio");
	    }
	    if (local == null || local.isBlank()) {
	        throw new IllegalArgumentException("Local não pode ser nulo ou vazio");
	    }
	    this.nome = nome;
	    this.anoLancamento = anoLancamento;
	    this.local = local;
	}

	/**
     * Calcula o código hash do filme com base no nome e no ano de lançamento.
     * 
     * @return o código hash do filme
     */
	@Override
	public int hashCode() {
		return Objects.hash(anoLancamento, nome);
	}
	
	/**
     * Retorna o nome do filme.
     * 
     * @return o nome do filme
     */
	public String getNome() {
		return nome;
	}
	
	/**
     * Retorna o local de exibição do filme.
     * 
     * @return o local de exibição do filme
     */
	public String getLocal() {
		return local;
	}

	/**
     * Retorna o ano de lançamento do filme.
     * 
     * @return o ano de lançamento do filme
     */
	public String getAnoLancamento() {
		return anoLancamento;
	}
	
	/**
     * Compara este filme com outro objeto para verificar se são iguais.
     * Dois filmes são considerados iguais se tiverem o mesmo nome e o mesmo ano de lançamento.
     * 
     * @param obj o objeto a ser comparado
     * @return true se os filmes forem iguais, false caso contrário
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return anoLancamento == other.anoLancamento && Objects.equals(nome, other.nome);
	}

	/**
     * Retorna uma representação textual do filme no formato:
     * "Nome, Ano\nLocal", ou "Nome\nLocal" se o ano estiver em branco.
     * 
     * @return a representação textual do filme
     */
	@Override
	public String toString() {
		if (anoLancamento.isBlank()) {
			return nome + "\n" + local;
		}
		return nome + ", " + anoLancamento + "\n" + local;
	}
}