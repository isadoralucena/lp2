package complementacao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import complementacao.enums.TipoAtividade;
import complementacao.model.dica.*;
import complementacao.util.Validador;

/**
 * Controlador responsável pelo gerenciamento de dicas criadas por usuários.
 * Permite adicionar dicas, adicionar elementos às dicas e listá-las.
 * 
 * @author Isadora Lucena
 */
public class DicaController {
	private List<Dica> dicas;
	private UsuarioController usuarioController;
	
	/**
	 * Constrói o controlador de dicas com referência ao controlador de usuários.
	 * 
	 * @param usuarioController Controlador de usuários associado.
	 */
	public DicaController(UsuarioController usuarioController) {
		dicas = new ArrayList<Dica>();
		this.usuarioController = usuarioController;
	}
	
	/**
	 * Adiciona uma nova dica associada a um usuário autenticado
	 * (a autenticação é realizada por meio do método {@code getNome}).
	 * 
	 * A dica será vinculada ao nome do autor obtido via CPF e senha, 
	 * e ao tema convertido para {@link TipoAtividade}.
	 * 
	 * @param cpf CPF do usuário.
	 * @param senha Senha do usuário.
	 * @param tema Tema da dica, representado como uma string. Será convertido para {@link TipoAtividade}.
	 * @return A posição (índice) da dica adicionada na lista.
	 * @throws SecurityException        se o CPF e a senha não corresponderem a um usuário autenticado.
	 * @throws IllegalArgumentException se o CPF ou a senha forem inválidos,
	 *         ou se o tema não corresponder a um valor válido de {@link TipoAtividade}.
	 */
	public int adicionarDica(String cpf, String senha, String tema) {
		String nomeAutor = usuarioController.getNome(cpf, senha);
		TipoAtividade atividade = TipoAtividade.converterStringEmAtividade(tema.trim());
		dicas.add(new Dica(nomeAutor, atividade));
		return dicas.size() - 1;
	}
	
	private boolean adicionarElementoDica(String cpf, String senha, int posicao, ElementoDica elemento) {
		validarPosicaoDica(posicao);

	    Dica dica = dicas.get(posicao);
	    dica.adicionarElementoDica(elemento);

	    usuarioController.atualizarBonificacao(cpf, senha, dica.getBonusUltimoElementoDica());
	    return true;
	}

	/**
	 * Adiciona um elemento de texto a uma dica já existente.
	 *
	 * O usuário precisa estar autenticado para realizar a operação. O texto é encapsulado
	 * em um {@link ElementoTextoDica}, que será associado à dica na posição informada.
	 * 
	 * @param cpf     CPF do usuário autenticado.
	 * @param senha   Senha do usuário.
	 * @param posicao Índice da dica na lista.
	 * @param texto   Conteúdo textual a ser adicionado como elemento da dica.
	 * 
	 * @return {@code true} se o elemento for adicionado com sucesso; {@code false} caso contrário.
	 * @throws SecurityException        se o CPF e a senha não corresponderem a um usuário autenticado.
	 * @throws IllegalArgumentException se os dados de autenticação forem inválidos,
	 *         a posição da dica não existir, ou o texto for inválido.
	 */
	public boolean adicionarElementoTextoDica(String cpf, String senha, int posicao, String texto) {
	    return adicionarElementoDica(cpf, senha, posicao, new ElementoTextoDica(texto));
	}

	/**
	 * Adiciona um elemento multimídia a uma dica existente.
	 * 
	 * O usuário precisa estar autenticado para realizar a operação. O conteúdo é encapsulado
	 * em um {@link ElementoMultimidiaDica}, que será associado à dica na posição informada.
	 * 
	 * @param cpf       CPF do usuário autenticado.
	 * @param senha     Senha do usuário.
	 * @param posicao   Índice da dica na lista.
	 * @param link      Link para o conteúdo multimídia.
	 * @param cabecalho Título ou descrição breve do material.
	 * @param tempo     Tempo estimado de visualização do conteúdo, em minutos.
	 * 
	 * @return {@code true} se o elemento for adicionado com sucesso; {@code false} caso contrário.
	 * @throws SecurityException        se o CPF e a senha não corresponderem a um usuário autenticado.
	 * @throws IllegalArgumentException se os dados de autenticação forem inválidos, a posição da dica for inexistente,
	 *         ou qualquer parâmetro do conteúdo for considerado inválido.
	 */
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
	    return adicionarElementoDica(cpf, senha, posicao, new ElementoMultimidiaDica(link, cabecalho, tempo));
	}

	/**
	 * Adiciona um elemento de referência a uma dica existente.
	 * 
	 * O usuário precisa estar autenticado para realizar a operação. O conteúdo é encapsulado
	 * em um {@link ElementoReferenciaDica}, que será associado à dica na posição informada.
	 * 
	 * @param cpf         CPF do usuário autenticado.
	 * @param senha       Senha do usuário.
	 * @param posicao     Índice da dica na lista.
	 * @param titulo      Título da referência.
	 * @param fonte       Fonte de onde a referência foi extraída.
	 * @param ano         Ano de publicação da referência.
	 * @param conferida   Indica se a referência foi conferida pelo autor da dica.
	 * @param importancia Grau de importância da referência, variando de 1 (menos importante) a 5 (mais importante).
	 * 
	 * @return {@code true} se o elemento for adicionado com sucesso; {@code false} caso contrário.
	 * @throws SecurityException        se o CPF e a senha não corresponderem a um usuário autenticado.
	 * @throws IllegalArgumentException se os dados de autenticação forem inválidos, a posição da dica for inexistente,
	 *         ou qualquer parâmetro do conteúdo for considerado inválido.
	 */
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia) {
	    return adicionarElementoDica(cpf, senha, posicao, new ElementoReferenciaDica(titulo, fonte, ano, conferida, importancia));
	}
	
	private void validarPosicaoDica(int posicao) {
	    if (posicao < 0 || posicao >= dicas.size()) {
	        throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
	    }
	}

	private List<String> listarTodasAsDicas(Function<Dica, String> visualizacao) {
	    return dicas.stream().map(visualizacao).collect(Collectors.toList());
	}

	private String listarUmaDica(int posicao, Function<Dica, String> visualizacao) {
	    validarPosicaoDica(posicao);
	    return visualizacao.apply(dicas.get(posicao));
	}
	
	/**
	 * Lista todas as dicas em formato resumido.
	 * 
	 * @return Lista de dicas resumidas.
	 */
	public List<String> listarDicas() {
	    return listarTodasAsDicas (Dica::visualizacaoResumida);
	}

	/**
	 * Lista todas as dicas com detalhes completos.
	 * 
	 * @return Lista de dicas detalhadas.
	 */
	public List<String> listarDicasDetalhes() {
	    return listarTodasAsDicas (Dica::visualizacaoDetalhada);
	}

	/**
	 * Lista uma dica específica em formato resumido.
	 * 
	 * @param posicao Índice da dica.
	 * @return Representação resumida da dica.
	 */
	public String listarDica(int posicao) {
	    return listarUmaDica(posicao, Dica::visualizacaoResumida);
	}

	/**
	 * Lista uma dica específica com todos os detalhes.
	 * 
	 * @param posicao Índice da dica.
	 * @return Representação detalhada da dica.
	 */
	public String listarDicaDetalhes(int posicao) {
	    return listarUmaDica(posicao, Dica::visualizacaoDetalhada);
	}
}
