package mrbet;

import java.util.Objects;

/**
 * Representa um time em um campeonato.
 * Cada time é identificado por um código único (ID), um nome e um mascote.
 * A classe fornece métodos para acessar esses atributos e para comparar se dois times são iguais com base no ID.
 * 
 * @author Isadora Lucena
 */
public class Time{
	private String id;
	private String nome;
	private String mascote;
	
	/**
     * Constrói um time com um ID único, nome e mascote.
     * 
     * @param id o código único do time (não pode ser nulo ou vazio)
     * @param nome o nome do time (não pode ser nulo ou vazio)
     * @param mascote o mascote do time (não pode ser nulo ou vazio)
     * @throws IllegalArgumentException se qualquer um dos parâmetros for nulo ou vazio
     */
	public Time(String id, String nome, String mascote) {
		if (id == null || id.isBlank()) {
	        throw new IllegalArgumentException("CÓDIGO DO TIME NÃO DEVE SER NULO OU VAZIO!");
	    }

	    if (nome == null || nome.isBlank()) {
	        throw new IllegalArgumentException("NOME DO TIME NÃO DEVE SER NULO OU VAZIO!");
	    }

	    if (mascote == null || mascote.isBlank()) {
	        throw new IllegalArgumentException("MASCOTE DO TIME NÃO DEVE SER NULO OU VAZIO");
	    }

		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
	}
	
	/**
     * Retorna o ID único do time.
     * 
     * @return o ID do time
     */
	public String getId() {
		return id;
	}


    /**
     * Retorna o nome do time.
     * 
     * @return o nome do time
     */
	public String getNome() {
		return nome;
	}

	/**
     * Retorna o mascote do time.
     * 
     * @return o mascote do time
     */
	public String getMascote() {
		return mascote;
	}

	/**
     * Calcula o código hash do time com base no seu ID.
     * 
     * @return o código hash do time
     */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
     * Compara este time com outro objeto para verificar se são iguais.
     * 
     * Dois times são considerados iguais se tiverem o mesmo ID.
     * 
     * @param obj o objeto a ser comparado com o time atual
     * @return true se os times forem iguais, ou seja, tiverem o mesmo ID; false caso contrário
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return Objects.equals(id, other.id);
	}
	
	/**
	 * Retorna uma representação textual do time.
	 * 
	 * A representação textual será no formato "[ID] Nome / Mascote", onde:
	 * ID é o código único do time.
	 * Nome é o nome do time.
	 * Mascote é o mascote do time.
	 * 
	 * @return a representação textual do time
	 */
	@Override
	public String toString() {
		return "[" +id+ "] " +nome+ " / " +mascote;
	}
}
