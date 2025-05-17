package complementacao.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import complementacao.enums.TipoAtividade;
import complementacao.model.atividade.Atividade;
import complementacao.util.Validador;

/**
 * Representa um usuário do sistema. Cada usuário possui nome, CPF, matrícula e senha, 
 * sendo identificado unicamente pelo CPF. A autenticação é realizada com CPF e senha, garantindo o acesso seguro às funcionalidades.
 * 
 * O usuário pode adicionar atividades complementares divididas por tipo, e cada atividade contribui com créditos e bonificações. 
 * Esses créditos são acumulados até um valor máximo permitido pelo sistema, tanto de forma geral quanto por tipo de atividade. 
 * A cada nova atividade cadastrada, é gerado automaticamente um código único baseado no CPF do usuário e na ordem de cadastro.
 * 
 * O usuário também pode alterar descrições e links de comprovação das atividades já registradas. 
 * Além disso, é possível acompanhar a distribuição dos créditos por meio de um mapa de créditos e 
 * verificar se o usuário atingiu a carga horária complementar exigida.
 * 
 * O usuário mantém ainda um histórico próprio e cronológico de relatórios, que registram suas atividades complementares ao longo do tempo. 
 * Esse histórico é exclusivo de cada usuário e permite salvar, listar e excluir registros conforme necessário, 
 * oferecendo uma visão organizada da sua trajetória no sistema.
 * 
 * @author Isadora
 */
public class Usuario{
	private String nome;
	private String cpf;
	private Map<TipoAtividade, List<Atividade>> atividades;
	private Map<TipoAtividade, Integer> mapaCreditos;
	private String senha;
	private String matricula;
	private int bonificacao;
	private int creditosAcumulados;
	private static final int MAXIMO_CREDITOS = 22;
	private Map<LocalDate, String> historico; 
	
	/**
     * Constrói um novo usuário com os dados fornecidos.
     * 
     * @param nome      Nome completo do usuário.
     * @param cpf       CPF do usuário.
     * @param senha     Senha para autenticação.
     * @param matricula Matrícula acadêmica.
     * 
     * @throws IllegalArgumentException se o CPF for inválido, a senha for inválida 
     *                                  ou a matrícula for nula ou vazia.
     */
	public Usuario(String nome, String cpf, String senha, String matricula) {
		Validador.validarCPF(cpf);
        Validador.validarSenha(senha);
        Validador.validarString(matricula, "Matrícula não pode ser nula ou vazia.");
		this.nome = nome.trim();
		this.cpf = cpf.trim();
		this.senha = senha.trim();
		this.atividades = new HashMap<>();
		this.matricula = matricula.trim();
		this.historico = new HashMap<>();
		this.mapaCreditos = new HashMap<>();
	}
	
    /**
     * Atualiza a bonificação do usuário somando o bônus recebido.
     * 
     * @param bonus valor a ser somado à bonificação.
     */
	public void atualizaBonificacao(int bonus) {
		bonificacao += bonus;
	}
	
	/**
     * Autentica o usuário comparando CPF e senha.
     * 
     * @param cpf   CPF informado.
     * @param senha Senha informada.
     * @return {@code true} se os dados coincidirem, caso contrário {@code false}.
     */
	public boolean autenticar(String cpf, String senha) {
		return Objects.equals(this.cpf, cpf) && Objects.equals(senha, this.senha);
	}

	/**
	 * Altera a senha do usuário, caso a autenticação com o CPF e a senha antiga seja bem-sucedida.
	 * 
	 * @param cpf         CPF do usuário.
	 * @param senhaAntiga Senha atual do usuário.
	 * @param novaSenha   Nova senha que será definida.
	 * @return {@code true} se a senha foi alterada com sucesso; {@code false} caso a autenticação falhe.
	 * @throws IllegalArgumentException se a nova senha for inválida.
	 */
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		if (!autenticar(cpf, senhaAntiga)) return false;
		
		this.setSenha(novaSenha);
		return true;
	}
	
	/**
	 * Retorna o número máximo de créditos que um usuário pode acumular.
	 *
	 * @return o valor máximo de créditos permitido.
	 */
	public static int getMaximoCreditos() {
	    return MAXIMO_CREDITOS;
	}
	
	private void setSenha(String senha) {
		Validador.validarSenha(senha);
		this.senha = senha;
	}
	
	/**
	 * Retorna o nome do usuário.
	 *
	 * @return Nome do usuário.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o CPF do usuário.
	 *
	 * @return CPF do usuário.
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Retorna a matrícula do usuário.
	 *
	 * @return Matrícula do usuário.
	 */
	public String getMatricula() {
		return matricula;
	}
	
	/**
	 * Retorna a bonificação do usuário.
	 *
	 * @return Valor da bonificação.
	 */
	public int getBonificacao() {
		return bonificacao;
	}
	
	/**
	 * Retorna o total de créditos acumulados pelo usuário.
	 *
	 * @return Créditos acumulados.
	 */
	public int getCreditosAcumulados(){
		return creditosAcumulados;
	}
	
	private void atualizarMapaCreditos(TipoAtividade tipo, Atividade atividade) {
		mapaCreditos.merge(tipo, atividade.getCreditos(), Integer::sum);
	}

	/**
     * Gera um código único de atividade baseado no CPF e número de atividades do tipo.
     * 
     * @param tipo Tipo da atividade.
     * @return Código gerado no formato CPF_formatado + número sequencial.
     */
	public String gerarCodigoAtividade(TipoAtividade tipo) {
		int tamanho = atividades.getOrDefault(tipo, new ArrayList<>()).size() + 1;
		return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4")  + "_" + tamanho;
	}
	
	private void atualizarCreditosAcumulados(int creditos) {
		creditosAcumulados = Math.min(creditosAcumulados + creditos, MAXIMO_CREDITOS);
	}

	/**
	 * Adiciona uma atividade ao usuário, atualizando os créditos acumulados e
	 * os créditos por tipo de atividade.
	 *
	 * @param tipo      Tipo da atividade.
	 * @param atividade Atividade a ser registrada.
	 * @return Mensagem de confirmação do cadastro.
	 */
	public String adicionarAtividadePorTipo(TipoAtividade tipo, Atividade atividade) {
		atividades.computeIfAbsent(tipo, k -> new ArrayList<>()).add(atividade);
	    atualizarCreditosAcumulados(atividade.getCreditos());
	    atualizarMapaCreditos(tipo, atividade);
	    return "Atividade cadastrada com sucesso.";
	}
	
	/**
     * Gera uma visualização do total de créditos obtidos por tipo de atividade,
     * seguido dos créditos totais acumulados.
     * 
     * @return String formatada com a distribuição de créditos.
     */
	public String gerarMapaDeCreditos() {
		StringBuilder mapa = new StringBuilder();
	    for (TipoAtividade tipo : TipoAtividade.values()) {
	        int totalCreditos = mapaCreditos.getOrDefault(tipo, 0);
	        mapa.append(tipo.name()).append(": ").append(totalCreditos).append("\n");
	    }

	    mapa.append("Créditos Totais: ").append(creditosAcumulados).append("/").append(MAXIMO_CREDITOS);
	    return mapa.toString();
	}
	
    /**
     * Verifica se o usuário atingiu o máximo de créditos permitidos.
     * 
     * @return {@code true} se os créditos acumulados forem iguais ao máximo.
     */
	public boolean metaAlcancada() {
		return creditosAcumulados == MAXIMO_CREDITOS;
	}
	
	/**
     * Verifica se a meta de créditos foi alcançada para um tipo específico de atividade.
     * 
     * @param tipoAtividade Tipo da atividade.
     * @return {@code true} se os créditos atingirem o máximo permitido por esse tipo.
     */
	public boolean metaAlcancadaPorAtividade(TipoAtividade tipoAtividade) {
	    int creditosAtuais = mapaCreditos.getOrDefault(tipoAtividade, 0);
	    int creditoMaximo = getCreditoMaximoPorAtividade(tipoAtividade);
	    return creditosAtuais >= creditoMaximo && creditoMaximo > 0;
	}
	
	/**
     * Retorna o máximo de créditos permitidos para um tipo de atividade.
     * 
     * @param tipo Tipo da atividade.
     * @return Valor máximo de créditos, ou 0 se não houver atividades cadastradas.
     */
	public int getCreditoMaximoPorAtividade(TipoAtividade tipo) {
	    List<Atividade> atividadesTipo = atividades.get(tipo);
	    if (atividadesTipo == null || atividadesTipo.isEmpty()) return 0;
	    return atividadesTipo.get(0).getCreditoMaximo();
	}

    /**
     * Retorna os créditos acumulados para um tipo específico de atividade.
     * 
     * @param tipoAtividade Tipo da atividade.
     * @return Total de créditos acumulados desse tipo.
     */
	public int creditosAtividade(TipoAtividade tipoAtividade) {
		return mapaCreditos.getOrDefault(tipoAtividade, 0);
    }

	private Atividade buscarAtividadePorCodigo(String codigoAtividade) {
	    for (List<Atividade> lista : atividades.values()) {
	        for (Atividade atividade : lista) {
	            if (atividade.getId().equals(codigoAtividade)) {
	                return atividade;
	            }
	        }
	    }
	    return null;
	}

	/**
     * Altera a descrição de uma atividade já cadastrada.
     * 
     * @param codigoAtividade Código da atividade.
     * @param novaDescricao   Nova descrição a ser atribuída.
     * @return {@code true} se a alteração foi realizada.
     */
	public boolean alterarDescricaoAtividade(String codigoAtividade, String novaDescricao) {
	    Atividade atividade = buscarAtividadePorCodigo(codigoAtividade);
	    if (atividade != null) {
	        atividade.setDescricao(novaDescricao);
	        return true;
	    }
	    return false;
	}

	/**
     * Altera o link de comprovação/documentação de uma atividade.
     * 
     * @param codigoAtividade   Código da atividade.
     * @param linkComprovacao   Novo link de documentação.
     * @return {@code true} se a alteração foi realizada.
     */
	public boolean alterarComprovacaoAtividade(String codigoAtividade, String linkComprovacao) {
	    Atividade atividade = buscarAtividadePorCodigo(codigoAtividade);
	    if (atividade != null) {
	        atividade.setDocumentacaoComprobatoria(linkComprovacao);
	        return true;
	    }
	    return false;
	}
	
	/**
     * Lista o histórico de relatórios salvos pelo usuário.
     * 
     * @return String contendo os relatórios por data.
     */
	public String listarHistorico() {
		return historico.entrySet().stream()
                .map(entry -> entry.getKey() + "\n" + entry.getValue())
                .collect(Collectors.joining("\n\n"));

	}
	
    /**
     * Exclui um relatório salvo do histórico.
     * 
     * @param data Data do relatório a ser excluído.
     * @return {@code true} se a exclusão foi bem-sucedida.
     */
	public boolean excluirItemHistorico(LocalDate data) {
		if (historico.containsKey(data)) {
	        historico.remove(data);
	        return true;
	    }
	    return false;
    }

	/**
     * Salva um relatório no histórico associado a uma data.
     * 
     * @param data      Data de referência do relatório.
     * @param relatorio Conteúdo do relatório.
     * @return {@code true} se foi salvo com sucesso.
     */
	public boolean salvarRelatorio(LocalDate data, String relatorio) {
		historico.put(data, relatorio);	
		return true;
	}
	
	/**
	 * Gera o código hash com base no CPF do usuário.
	 *
	 * @return Valor hash do CPF.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	
	/**
	 * Compara este usuário com outro objeto para verificar igualdade.
	 * A comparação é feita com base no CPF.
	 *
	 * @param obj Objeto a ser comparado.
	 * @return {@code true} se os objetos forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}

	/**
	 * Retorna uma representação textual do usuário.
	 *
	 * @return String com nome, matrícula e bônus.
	 */
	@Override
	public String toString() {
		return "Nome: " + nome + ", matrícula: " + matricula + ", bônus: " + bonificacao;
	}
}
