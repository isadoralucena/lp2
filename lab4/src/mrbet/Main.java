package mrbet;

import java.util.Scanner;

/**
 * Classe principal que serve como uma interface de usuário (UI) para o sistema MrBet.
 * 
 * A classe Main não contém lógica de negócio, mas atua como a interface que interage com o usuário.
 * Ela apresenta menus, captura as opções escolhidas e invoca métodos do sistema para realizar ações como 
 * adicionar times, campeonatos, fazer apostas etc.
 * 
 * @author Isadora Lucena
 */
public class Main {
	
	/**
     * Método principal que executa o loop do programa.
     * Ele exibe o menu principal e chama os métodos de acordo com a opção escolhida pelo usuário.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
	public static void main(String[]args) {
		MrBetSistema sistema = new MrBetSistema();
		Scanner sc = new Scanner(System.in);
		
		String escolha = "";
		while (true) {
			escolha = menuPrincipal(sc);
			exibirMenuPrincipal(escolha, sistema, sc);
		}
	}
	
	/**
     * Exibe o menu principal para o usuário escolher uma opção.
     * 
     * @param scanner Scanner para capturar a entrada do usuário.
     * @return A opção escolhida pelo usuário (uma string).
     */
	private static String menuPrincipal(Scanner scanner) {
		System.out.print(
				"MENU\n" + 
				"(M)Minha inclusão de times\n" + 
				"(R)Recuperar time\n" + 
				"(.)Adicionar campeonato\n" + 
				"(B)Bora incluir time em campeonato e verificar se time está em campeonato\n" +
				"(E)Exibir campeonatos que o time participa\n" +
				"(T)Tentar a sorte e status\n" +
				"(!)Já pode fechar o programa!\n" + 
				"\n" + 
				"Opção> ");
		return scanner.next().toUpperCase();
	}
	
	/**
     * Trata a opção escolhida pelo usuário e chama o método correspondente para cada ação.
     *
     * @param opcao A opção escolhida pelo usuário.
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
     */
	private static void exibirMenuPrincipal(String opcao, MrBetSistema sistema, Scanner scanner) {
		switch (opcao) {
		case "M":
			adicionarTime(sistema, scanner);
			break;
		case "R":
			recuperarTime(sistema, scanner);
			break;
		case ".":
			adicionarCampeonato(sistema, scanner);
			break;
		case "B":
			exibirSubmenuTimeCampeonato(sistema, scanner);
			break;
		case "E":
			exibirCampeonatosDoTime(sistema, scanner);
			break;
		case "T":
			exibirSubmenuAposta(sistema, scanner);
			break;
		case "!":
			encerrarPrograma();
		default:
			throw new IllegalArgumentException("ENTRADA INVÁLIDA!");
		}
	}

	/**
     * Permite ao usuário adicionar um novo time no sistema.
     *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
     */
	private static void adicionarTime(MrBetSistema sistema, Scanner scanner) {
		System.out.print("\nCódigo: ");
		String id = scanner.next();
		scanner.nextLine();
		System.out.print("\nNome: ");
		String nome = scanner.nextLine();
		System.out.print("\nMascote: ");
		String mascote = scanner.nextLine();
		System.out.println(sistema.adicionarTime(id, nome, mascote));
	}
	
	/**
     * Permite ao usuário recuperar um time existente no sistema pelo código.
     *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
     */
	private static void recuperarTime(MrBetSistema sistema, Scanner scanner) {
		System.out.print("\nCódigo: ");
		String id = scanner.next();
		System.out.println(sistema.recuperarTime(id));
	}
	
	/**
     * Permite ao usuário adicionar um novo campeonato no sistema.
     *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
     */
	private static void adicionarCampeonato(MrBetSistema sistema, Scanner scanner) {
		scanner.nextLine();
		System.out.print("\nCampeonato: ");
		String nome = scanner.nextLine();
		System.out.print("\nParticipantes: ");
		int numeroParticipantes = scanner.nextInt();
		System.out.println(sistema.adicionarCampeonato(nome, numeroParticipantes));
	}
	
	/**
     * Exibe um submenu para o usuário escolher entre incluir um time em um campeonato ou verificar se um time já está inscrito.
     *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
     */
	private static void exibirSubmenuTimeCampeonato(MrBetSistema sistema, Scanner scanner) {
	    System.out.print("\n(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
	    String escolha = scanner.next().toUpperCase();

	    switch (escolha) {
	        case "I":
	            adicionarTimeEmCampeonato(sistema, scanner);
	            break;
	        case "V":
	            verificarTimeEmCampeonato(sistema, scanner);
	            break;
	        default:
	        	throw new IllegalArgumentException("ENTRADA INVÁLIDA!");
	    }
	}
	
	/**
	 * Este método captura o código do time e o nome do campeonato, e então adiciona o time
	 * ao campeonato no sistema MrBet.
	 *
	 * @param sistema O objeto do sistema MrBet onde o time será adicionado.
	 * @param scanner O scanner para capturar a entrada do usuário.
	 */
	private static void adicionarTimeEmCampeonato(MrBetSistema sistema, Scanner scanner) {
		System.out.print("\nCódigo do time: ");
		String id = scanner.next();
		scanner.nextLine();
		System.out.print("\nNome do campeonato: ");
		String nome = scanner.nextLine();
		System.out.println(sistema.adicionarTimeEmCampeonato(nome, id));
	}
	
	/**
	 * Exibe uma mensagem final para o usuário e encerra o programa. 
	 * 
	 */
	private static void encerrarPrograma() {
		System.out.println("Por hoje é só, pessoal!");
        System.exit(0);
	}
	
	/**
	 * Captura o código do time e o nome do campeonato, e então verifica no
	 * sistema se o time está registrado no campeonato.
	 *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
	 */
	private static void verificarTimeEmCampeonato(MrBetSistema sistema, Scanner scanner) {
		System.out.print("\nCódigo do time: ");
		String id = scanner.next();
		scanner.nextLine();
		System.out.print("\nNome do campeonato: ");
		String nome = scanner.nextLine();
		System.out.println(sistema.verificarTimeNoCampeonato(nome, id));
	}
	
	/**
	 * Captura o código de um time e exibe todos os campeonatos nos quais o
	 * time está inscrito.
	 *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
	 */
	private static void exibirCampeonatosDoTime(MrBetSistema sistema, Scanner scanner) {
		System.out.print("\nCódigo do time: ");
		String id = scanner.next();
		System.out.println(sistema.campeonatosDoTime(id));
	}
	
	/**
	 * Exibe um submenu com duas opções: (A) Apostar ou (S) Status das Apostas. Dependendo
	 * da escolha do usuário, o programa chamará o método apropriado para adicionar uma aposta ou exibir
	 * o status das apostas. A ação escolhida é passada para o sistema MrBet para execução.
	 *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
	 */
	private static void exibirSubmenuAposta(MrBetSistema sistema, Scanner scanner) {
	    System.out.print("\n(A)Apostar ou (S)Status das Apostas? ");
	    String escolha = scanner.next().toUpperCase();

	    switch (escolha) {
	        case "A":
	            adicionarAposta(sistema, scanner);
	            break;
	        case "S":
	        	exibirstatusDasApostas(sistema, scanner);
	            break;
	        default:
	        	throw new IllegalArgumentException("ENTRADA INVÁLIDA!");
	    }
	}
	
	/**
	 * Captura o código do time, o nome do campeonato, a colocação e o valor da aposta,
	 * e então adiciona a aposta no sistema MrBet.
	 *
     * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
	 */
	private static void adicionarAposta(MrBetSistema sistema, Scanner scanner) {
		System.out.print("\nCódigo do time: ");
		String id = scanner.next();
		scanner.nextLine();
		System.out.print("\nNome do campeonato: ");
		String nome = scanner.nextLine();
		System.out.print("\nColocação: ");
		int colocacao = scanner.nextInt();
		System.out.print("Valor da aposta: ");
		double valor = scanner.nextDouble();
		System.out.println(sistema.adicionarAposta(id, nome, colocacao, valor));
	}
	
	/**
	 * Invoca um método do sistema MrBetSistema para listar todas as apostas feitas até o momento
	 * e exibe essas apostas para o usuário.
	 *
	 * @param sistema O objeto do sistema MrBet.
     * @param scanner O scanner para capturar a entrada do usuário.
	 */
	private static void exibirstatusDasApostas(MrBetSistema sistema, Scanner scanner) {
		System.out.println(sistema.listarApostas());
	}
}
