package complementacao.model.dica;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import complementacao.enums.TipoAtividade;
import complementacao.util.Validador;

/**
 * Representa uma dica criada por um autor sobre uma determinada {@link TipoAtividade}.
 * Uma dica é composta por múltiplos {@link ElementoDica}, que podem ser visualizados
 * de forma resumida ou detalhada.
 * 
 * Cada dica possui um autor, um tema relacionado a um tipo de atividade complementar e uma lista de elementos associados.
 * 
 * @author Isadora Lucena
 */
public class Dica {
	private String nomeAutor;
	private TipoAtividade tema;
	private List<ElementoDica> elementosDica;
	
	/**
	 * Cria uma nova dica com o autor e o tema fornecidos.
	 * 
	 * @param nomeAutor Nome da pessoa que escreveu a dica. Não pode ser nulo ou vazio.
	 * @param tema Tema da dica, representado como {@link TipoAtividade}. Deve ser previamente validado.
	 * 
	 * @throws IllegalArgumentException se o nome do autor for nulo ou vazio.
	 */
	public Dica(String nomeAutor, TipoAtividade tema) {
		elementosDica = new ArrayList<>();
		Validador.validarString(nomeAutor, "Nome do autor não pode ser nulo ou vazio.");
		this.nomeAutor = nomeAutor.trim();
		this.tema = tema;
	}
	
	/**
	 * Retorna o valor (bônus) do último {@link ElementoDica} adicionado à dica.
	 * 
	 * @return Valor do último elemento, ou 0 se não houver elementos.
	 */
	public int getBonusUltimoElementoDica() {
		if (elementosDica.isEmpty()) return 0;
	    return elementosDica.get(elementosDica.size() - 1).getValor();
	}
	
	/**
	 * Adiciona um novo {@link ElementoDica} à dica.
	 * 
	 * @param elemento Elemento a ser adicionado à dica.
	 */
	public void adicionarElementoDica(ElementoDica elemento) {
		elementosDica.add(elemento);
	}
	
	/**
	 * Gera uma visualização resumida da dica, contendo o autor e os elementos em formato resumido.
	 * 
	 * @return String formatada com a visualização resumida da dica.
	 */
	public String visualizacaoResumida() {
		return formatarVisualizacao(ElementoDica::visualizacaoResumida);
	}

	/**
	 * Gera uma visualização detalhada da dica, contendo o autor e os elementos em formato detalhado.
	 * 
	 * @return String formatada com a visualização detalhada da dica.
	 */
	public String visualizacaoDetalhada() {
		return formatarVisualizacao(ElementoDica::visualizacaoDetalhada);
	}

	private String formatarVisualizacao(Function<ElementoDica, String> formatador) {
		return "Autor: " + nomeAutor + "\n" +
		       elementosDica.stream()
		                    .map(formatador)
		                    .collect(Collectors.joining("\n"));
	}
}
