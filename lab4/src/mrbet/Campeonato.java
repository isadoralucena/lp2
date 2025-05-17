package mrbet;

import java.util.HashMap;
import java.util.Objects;

/**
 * Representa um campeonato no qual times podem ser adicionados.
 * Cada campeonato possui um nome, um número de participantes e um conjunto de times registrados.
 * O campeonato permite verificar se um time está registrado e adicionar novos times, respeitando o número máximo de participantes nele.
 * 
 * @author Isadora Lucena
 */
public class Campeonato {
	private String nome;
	private int numeroParticipantes;
	private HashMap<String, Time> times;
	
	/**
	 * Constrói um campeonato com um nome e um número de participantes.
	 * 
	 * @param nome o nome do campeonato (não pode ser nulo ou vazio)
	 * @param numeroParticipantes o número máximo de participantes (deve ser maior que 0)
	 * @throws IllegalArgumentException se o nome for nulo ou vazio, ou se o número de participantes for menor ou igual a 0
	 */
	public Campeonato(String nome, int numeroParticipantes) {
		if (nome == null || nome.isBlank()) {
	        throw new IllegalArgumentException("NOME DO CAMPEONATO NÃO PODE SER NULO OU VAZIO!");
	    }

	    if (numeroParticipantes <= 0) {
	        throw new IllegalArgumentException("NÚMERO DE PARTICIPANTES DEVE SER MAIOR QUE 0!");
	    }

		this.nome = nome;
		this.numeroParticipantes = numeroParticipantes;
		times = new HashMap<>();	
	}
	
	/**
     * Calcula o código hash do campeonato com base no nome.
     * 
     * @return o código hash do campeonato
     */
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	
	/**
     * Compara este campeonato com outro objeto para verificar se são iguais.
     * Dois campeonatos são considerados iguais se tiverem o mesmo nome (ignorando maiúsculas e minúsculas).
     * 
     * @param obj o objeto a ser comparado
     * @return true se os campeonatos forem iguais, false caso contrário
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campeonato other = (Campeonato) obj;
		return nome.equalsIgnoreCase(other.nome);
	}
	
	/**
     * Verifica se um time com o ID especificado está registrado no campeonato.
     * 
     * @param idTime o ID do time a ser verificado
     * @return uma mensagem indicando se o time está ou não no campeonato
     */
	public String verificarTimeNoCampeonato(String idTime) {
		if (isTimeNoCampeonato(idTime)) {
			return "O TIME ESTÁ NO CAMPEONATO!";
		}
        return "O TIME NÃO ESTÁ NO CAMPEONATO!";
    }
	
	/**
     * Retorna o nome do campeonato.
     * 
     * @return o nome do campeonato
     */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o número máximo de participantes do campeonato.
	 * 
	 * @return o número de participantes
	 */
	public int getNumeroParticipantes() {
		return numeroParticipantes;
	}
	
	/**
     * Retorna o número de times registrados no campeonato.
     * 
     * @return o número de times cadastrados
     */
	public int getTimesCadastrados() {
		return times.size();
	}
	
	/**
     * Verifica se um time com o ID especificado está registrado no campeonato.
     * 
     * @param idTime o ID do time a ser verificado
     * @return true se o time estiver registrado, false caso contrário
     */
	public boolean isTimeNoCampeonato(String idTime) {
    	if (times.containsKey(idTime)) {
            return true;
        }
        return false;
    }
	
	/**
     * Adiciona um time ao campeonato, desde que não tenha atingido o número máximo de participantes.
     * Caso o time já esteja registrado ou o número máximo de participantes tenha sido atingido, uma mensagem de erro é retornada.
     * 
     * @param time o time a ser adicionado
     * @return uma mensagem informando se o time foi adicionado ou se ocorreu um erro
     */
	public String adicionarTime(Time time) {
		if (times.size() >= numeroParticipantes) {
			return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
		}
		
		if (isTimeNoCampeonato(time.getId())) {
            return "O TIME JÁ ESTÁ NO CAMPEONATO!";
        }
		
		times.put(time.getId(), time);
        return "TIME INCLUÍDO NO CAMPEONATO!";
	}
}
