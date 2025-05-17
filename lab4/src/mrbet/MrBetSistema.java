package mrbet;

import java.util.*;

/**
 * Esta classe representa o sistema do "MrBet", sendo responsável por gerenciar 
 * os times, campeonatos e apostas. Ela atua como um controller que centraliza
 * a lógica de negócio do sistema, manipulando a criação e as interações entre
 * as entidades do sistema.
 * 
* A classe oferece métodos para:
 * Adicionar, recuperar e listar times;
 * Adicionar e interagir com campeonatos;
 * Adicionar apostas aos campeonatos;
 * Verificar a participação de times em campeonatos;
 * Listar apostas registradas.
 * 
 * @author Isadora Lucena
 */
public class MrBetSistema {
	private HashMap<String, Time> times;
	private HashMap <String, Campeonato> campeonatos;
	private ArrayList <Aposta> apostas;
	
	/**
     * Construtor que inicializa o sistema com coleções vazias para times, 
     * campeonatos e apostas.
     */
	public MrBetSistema() {
		times = new HashMap<>();
		campeonatos = new HashMap<>();
		apostas = new ArrayList<>();
	}
	
	/**
	 * Método que valida se o time com o id fornecido não existe.
	 * 
	 * Caso o time não exista, retorna uma mensagem de erro. Caso contrário, retorna um Optional vazio.
	 * 
	 * @param idTime O ID do time a ser verificado.
	 * @return Um Optional que contém uma mensagem de erro se o time não existir, ou um Optional vazio se o time existir.
	 */
	private Optional<String> validarTimeNaoExistente(String idTime) {
	    if (!times.containsKey(idTime)) {
	    	return Optional.of("TIME NÃO EXISTE!");
	    }
	    return Optional.empty();
	}
	
	/**
	 * Método que valida se o campeonato com o nome fornecido não existe (ignorando maiúsculas e minúsculas).
	 * 
	 * Caso o campeonato não exista, retorna uma mensagem de erro. Caso contrário, retorna um Optional vazio.
	 * 
	 * @param nomeCampeonato O nome do campeonato a ser verificado.
	 * @return Um Optional que contém uma mensagem de erro se o campeonato não existir, ou um Optional vazio se o campeonato existir.
	 */
	private Optional<String> validarCampeonatoNaoExistente(String nomeCampeonato) {
	    if (!campeonatos.containsKey(nomeCampeonato.toLowerCase())) {
	    	return Optional.of("CAMPEONATO NÃO EXISTE!");
	    }
	    return Optional.empty();
	}
	
	/**
     * Adiciona um time ao sistema.
     * 
     * @param id o ID do time
     * @param nome o nome do time
     * @param mascote o mascote do time
     * @return uma mensagem indicando se o time foi adicionado ou se já existe no sistema
     */
	public String adicionarTime(String id, String nome, String mascote) {
		if (times.containsKey(id)) {
			return "TIME JÁ EXISTE!";
		}
		times.put(id, new Time(id, nome, mascote));
		return "TIME ADICIONADO!";
	}
	
	/**
     * Recupera a informação textual de um time baseado no seu ID.
     * 
     * @param id o ID do time a ser recuperado
     * @return a representação do time, ou uma mensagem de erro se o time não existir
     */
	public String recuperarTime(String id) {
		Optional<String> validacaoTime = validarTimeNaoExistente(id);
	    if (validacaoTime.isPresent()) return validacaoTime.get();
	    
		return times.get(id).toString();
	}
	
	/**
     * Adiciona um campeonato ao sistema.
     * 
     * @param nome o nome do campeonato
     * @param numeroParticipantes o número máximo de participantes no campeonato
     * @return uma mensagem indicando se o campeonato foi adicionado ou se já existe
     */
	public String adicionarCampeonato(String nome, int numeroParticipantes) {
		if (campeonatos.containsKey(nome.toLowerCase())) {
			return "CAMPEONATO JÁ EXISTE!";
		}
		campeonatos.put(nome.toLowerCase(), new Campeonato(nome, numeroParticipantes));
		return "CAMPEONATO ADICIONADO!";
	}
	
	/**
     * Adiciona um time existente a um campeonato existente.
     * 
     * @param nomeCampeonato o nome do campeonato
     * @param idTime o ID do time a ser adicionado ao campeonato
     * @return uma mensagem indicando o sucesso ou falha da operação
     */
	public String adicionarTimeEmCampeonato(String nomeCampeonato, String idTime) {	
		Optional<String> validacaoTime = validarTimeNaoExistente(idTime);
	    if (validacaoTime.isPresent()) return validacaoTime.get();
		
	    Optional<String> validacaoCampeonato = validarCampeonatoNaoExistente(nomeCampeonato);
	    if (validacaoCampeonato.isPresent()) return validacaoCampeonato.get();

		Time time = times.get(idTime);
		return campeonatos.get(nomeCampeonato.toLowerCase()).adicionarTime(time);   
	}
	
	/**
     * Verifica se um time está participando de um campeonato.
     * 
     * @param nomeCampeonato o nome do campeonato
     * @param idTime o ID do time a ser verificado
     * @return uma mensagem indicando a presença ou não do time no campeonato
     */
	public String verificarTimeNoCampeonato(String nomeCampeonato, String idTime) {
		Optional<String> validacaoTime = validarTimeNaoExistente(idTime);
	    if (validacaoTime.isPresent()) return validacaoTime.get();
		
	    Optional<String> validacaoCampeonato = validarCampeonatoNaoExistente(nomeCampeonato);
	    if (validacaoCampeonato.isPresent()) return validacaoCampeonato.get();
	    
		return campeonatos.get(nomeCampeonato.toLowerCase()).verificarTimeNoCampeonato(idTime);
	}
	
	/**
     * Lista todos os campeonatos em que um time está participando.
     * 
     * @param idTime o ID do time
     * @return uma string contendo todos os campeonatos em que o time participa ou uma mensagem indicando que o time não está participando de nenhum campeonato
     */
	public String campeonatosDoTime(String idTime) {
		Optional<String> validacaoTime = validarTimeNaoExistente(idTime);
	    if (validacaoTime.isPresent()) return validacaoTime.get();
		
		Time time = times.get(idTime);
	    List<String> campeonatosDoTimeFormatados = new ArrayList<>();

	    for (Campeonato campeonato : campeonatos.values()) {
	        if (campeonato.isTimeNoCampeonato(idTime)) {
	        	campeonatosDoTimeFormatados.add("* " + campeonato.getNome() + " - " + campeonato.getTimesCadastrados() + "/" + campeonato.getNumeroParticipantes());
	        }
	    }

	    if (campeonatosDoTimeFormatados.isEmpty()) {
	        return "O TIME NÃO PARTICIPA DE NENHUM CAMPEONATO!";
	    }

	    return "Campeonatos do " + time.getNome() + ":\n\n" + String.join("\n", campeonatosDoTimeFormatados);
	}
	
	/**
     * Adiciona uma aposta ao sistema.
     * 
     * @param idTime o ID do time em que a aposta é feita
     * @param nomeCampeonato o nome do campeonato onde a aposta será registrada
     * @param colocacao a colocação do time apostado
     * @param valor o valor da aposta
     * @return uma mensagem indicando o sucesso ou falha da operação
     */
	public String adicionarAposta(String idTime, String nomeCampeonato, int colocacao, double valor) {
		Optional<String> validacaoTime = validarTimeNaoExistente(idTime);
	    if (validacaoTime.isPresent()) return validacaoTime.get();
		
	    Optional<String> validacaoCampeonato = validarCampeonatoNaoExistente(nomeCampeonato);
	    if (validacaoCampeonato.isPresent()) return validacaoCampeonato.get();
		
		Time time = times.get(idTime);
		Campeonato campeonato = campeonatos.get(nomeCampeonato.toLowerCase());
		
		if(colocacao > campeonato.getNumeroParticipantes()) {
			return "APOSTA NÃO REGISTRADA!";
		}
		
		apostas.add(new Aposta(time, campeonato, colocacao, valor));
		return "APOSTA REGISTRADA!";
	}
	
	/**
     * Formata uma aposta para exibição.
     * 
     * @param aposta a aposta a ser formatada
     * @param index o índice da aposta na lista
     * @return a aposta formatada como uma string
     */
	private String formatarAposta(Aposta aposta, int index) {
	    return (index + 1) + ". " + aposta.toString();
	}
	
	/**
     * Lista todas as apostas registradas no sistema.
     * 
     * @return uma string contendo todos as apostas cadastradas no sistema ou uma mensagem indicando que nenhuma aposta foi feita
     */
	public String listarApostas() {
		if(apostas.size() == 0) {
			return "NENHUMA APOSTA CADASTRADA!";
		}
		
		List<String> apostasFormatadas = new ArrayList<>();
		
		for(int i = 0; i < apostas.size(); i++) {
			apostasFormatadas.add(formatarAposta(apostas.get(i), i));
		}
		
		return "Apostas:\n\n" + String.join("\n\n", apostasFormatadas);
	}
}