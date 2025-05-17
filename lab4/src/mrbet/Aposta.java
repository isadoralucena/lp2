package mrbet;

/**
 * Representa uma aposta realizada por um time em um campeonato, contendo o time, o campeonato,
 * a colocação esperada e o valor apostado.
 * Garante que os valores essenciais (time, campeonato, colocação e valor) sejam válidos,
 * lançando exceções se algum parâmetro inválido for fornecido.
 * 
 * @author Isadora Lucena
 */

public class Aposta {
	private Time time;
	private Campeonato campeonato;
	private int colocacao;
	private double valor;
	
	/**
     * Constrói uma aposta para um time em um campeonato, com a colocação esperada e o valor da aposta.
     * A aposta é considerada válida se todos os parâmetros atenderem aos requisitos de validade.
     * 
     * @param time o time que fez a aposta (não pode ser nulo)
     * @param campeonato o campeonato onde a aposta é realizada (não pode ser nulo)
     * @param colocacao a colocação esperada para o time (deve ser maior que 0)
     * @param valor o valor da aposta (deve ser maior que 0)
     * @throws IllegalArgumentException se algum dos parâmetros for inválido (nulo ou valores fora do intervalo válido)
     */
	public Aposta(Time time, Campeonato campeonato, int colocacao, double valor) {
		if (time == null) {
	        throw new IllegalArgumentException("TIME NÃO PODE SER NULO!");
	    }
	    if (campeonato == null) {
	        throw new IllegalArgumentException("CAMPEONATO NÃO PODE SER NULO!");
	    }
	    if (colocacao < 1) {
	        throw new IllegalArgumentException("COLOCAÇÃO DEVE SER MAIOR QUE 0!");
	    }
	    if (valor <= 0) {
	        throw new IllegalArgumentException("VALOR DA APOSTA DEVE SER MAIOR QUE 0!");
	    }

		this.time = time;
		this.campeonato = campeonato;
		this.colocacao = colocacao;
		this.valor = valor;
	}
	
	/**
	 * Retorna o time que fez a aposta.
	 * 
	 * @return o time da aposta
	 */
	public Time getTime() {
		return time;
	}

	/**
     * Retorna o campeonato no qual a aposta foi realizada.
     * 
     * @return o campeonato da aposta
     */
	public Campeonato getCampeonato() {
		return campeonato;
	}

	/**
     * Retorna a colocação esperada para o time no campeonato.
     * 
     * @return a colocação esperada
     */
	public int getColocacao() {
		return colocacao;
	}

	/**
	 * Retorna o valor da aposta.
	 * 
	 * @return o valor da aposta
	 */
	public double getValor() {
		return valor;
	}

	/**
     * Retorna uma representação textual da aposta, exibindo informações sobre o time, campeonato,
     * número de times cadastrados no campeonato e o valor da aposta.
     * 
     * @return uma string representando a aposta no formato: 
     *         Nome do time, nome do campeonato, quantidade de times cadastrados e número total de participantes, 
     *         e valor da aposta formatado
     */
	@Override
	public String toString() {
		return time.toString() + "\n" + campeonato.getNome() + "\n" + 
				campeonato.getTimesCadastrados() + "/" + campeonato.getNumeroParticipantes() + "\nR$ " + 
				String.format("%.2f", valor);
	}
}
