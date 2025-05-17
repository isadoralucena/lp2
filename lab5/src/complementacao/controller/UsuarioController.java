package complementacao.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import complementacao.enums.TipoAtividade;
import complementacao.model.Usuario;
import complementacao.model.atividade.*;
import complementacao.util.Validador;
/**
 * Controlador responsável por gerenciar operações relacionadas aos usuários do sistema,
 * como criação de estudante, autenticação, criação e manipulação de atividades complementares
 * e geração de relatórios.
 * 
 * @author Isadora Lucena
 */
public class UsuarioController {
	private Map<String, Usuario> usuarios;

	/**
	 * Inicializa o controlador com um mapa vazio de usuários.
	 */
	public UsuarioController() {
		this.usuarios = new HashMap<String, Usuario>();
	}
	
	private void verificarUsuarioDuplicado(String cpf) {
		if (usuarios.containsKey(cpf)) {
            throw new IllegalArgumentException("Usuário com este CPF já existe.");
        }
	}
	
	private boolean usuarioExiste(String cpf) {
		return usuarios.containsKey(cpf);
	}
	
	/**
	 * Atualiza a bonificação de um usuário autenticado.
	 *
	 * @param cpf    CPF do usuário.
	 * @param senha  Senha do usuário.
	 * @param bonus  Valor da bonificação a ser adicionada.
	 * @throws SecurityException se o CPF e a senha não corresponderem a um usuário autenticado.
	 * @throws IllegalArgumentException se a bonificação for negativa.
	 */
	public void atualizarBonificacao(String cpf, String senha, int bonus) {
	    Validador.validarBonificacaoPositiva(bonus);
	    Usuario usuario = obterUsuarioAutenticado(cpf, senha);
	    usuario.atualizaBonificacao(bonus);
	}
	
	/**
	 * Retorna o nome do usuário autenticado com o CPF e senha fornecidos.
	 *
	 * @param cpf   CPF do usuário.
	 * @param senha Senha do usuário.
	 * @return Nome do usuário.
	 * @throws SecurityException se a autenticação falhar.
	 */
	public String getNome(String cpf, String senha) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		return usuario.getNome();
	}
	
	/**
	 * Cria um novo estudante no sistema.
	 *
	 * @param nome      Nome do estudante
	 * @param cpf       CPF do estudante
	 * @param senha     Senha de acesso
	 * @param matricula Matrícula institucional
	 * @return true se o estudante for criado com sucesso
	 */
	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
        verificarUsuarioDuplicado(cpf);
        
        usuarios.put(cpf, new Usuario(nome, cpf, senha, matricula));
        return true;
	}
	
	private List<String> listarUsuariosOrdenados(Comparator<Usuario> criterio) {
	    return usuarios.values().stream()
	        .sorted(criterio)
	        .map(Usuario::toString)
	        .collect(Collectors.toList());
	}

	/**
	 * Retorna a lista de estudantes ordenados por nome.
	 *
	 * @return Lista de estudantes
	 */
	public List<String> exibirEstudantes() {
		return listarUsuariosOrdenados(
		        Comparator.comparing(u -> u.getNome().toLowerCase().trim())
		    );
	}

	/**
	 * Lista os usuários ordenados por bonificação (ranking).
	 *
	 * @return Lista ordenada de usuários por bonificação
	 */
	public List<String> listarUsuariosRankingDicas() {
	    return listarUsuariosOrdenados(Comparator.comparing(Usuario::getBonificacao).reversed());
	}
	
	/**
	 * Altera a senha de um estudante autenticado.
	 *
	 * @param cpf         CPF do estudante.
	 * @param senhaAntiga Senha atual do estudante.
	 * @param novaSenha   Nova senha que será definida.
	 * @return {@code true} se a senha for alterada com sucesso; {@code false} se a autenticação falhar.
	 * @throws IllegalArgumentException se o CPF ou a senha antiga estiverem incorretos,
	 *                                  ou se a nova senha for inválida.
	 */
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senhaAntiga);
		return usuario.alterarSenhaEstudante(cpf, senhaAntiga, novaSenha);
	}
	
	/**
	 * Verifica se o CPF e senha correspondem a um usuário válido.
	 *
	 * @param cpf   CPF do usuário
	 * @param senha Senha
	 * @return true se a autenticação for bem-sucedida
	 */
	public boolean autenticar(String cpf, String senha) {
		if (!usuarioExiste(cpf)) return false;
		Usuario usuario = usuarios.get(cpf);
		
		if (!usuario.autenticar(cpf, senha)) return false;
		return true;
	}
	
	private Usuario obterUsuarioAutenticado(String cpf, String senha) {
	    if (!autenticar(cpf, senha)) throw new SecurityException("Autenticação falhou.");
	    return usuarios.get(cpf);
	}
	
	private Atividade criarAtividadePorTipo(TipoAtividade tipo, String codigo, int unidade, String detalhe) {
	    return switch (tipo) {
	        case ESTAGIO -> new AtividadeEstagio(codigo, unidade, detalhe);
	        case PESQUISA_EXTENSAO -> new AtividadePesquisaExtensao(codigo, unidade, detalhe);
	        case REPRESENTACAO_ESTUDANTIL -> new AtividadeRepresentacaoEstudantil(codigo, unidade, detalhe);
	        case MONITORIA -> new AtividadeMonitoria(codigo, unidade, detalhe);
	    };
	}
	
	private String criarAtividade(String cpf, String senha, int unidadeAcumulada, TipoAtividade tipo, String dadoComplementar) {
	    Usuario usuario = obterUsuarioAutenticado(cpf, senha);
	    String codigo = usuario.gerarCodigoAtividade(tipo);

	    Atividade atividade = criarAtividadePorTipo(tipo, codigo, unidadeAcumulada, dadoComplementar);
	    return usuario.adicionarAtividadePorTipo(tipo, atividade);
	}
	
	/**
	 * Cria uma atividade do tipo Estágio para o aluno identificado pelo CPF e senha.
	 *
	 * @param cpf             CPF do aluno.
	 * @param senha           Senha do aluno.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas pela atividade.
	 * @param nomeEmpresa     Nome da empresa onde o estágio foi realizado.
	 * @return Uma mensagem de sucesso ou erro relacionada à criação da atividade.
	 */
	public String criarAtividadeEstagio(String cpf, String senha, int unidadeAcumulada, String nomeEmpresa) {
	    return criarAtividade(cpf, senha, unidadeAcumulada, TipoAtividade.ESTAGIO, nomeEmpresa);
	}

	/**
	 * Cria uma atividade do tipo Pesquisa ou Extensão para o aluno identificado pelo CPF e senha.
	 *
	 * @param cpf             CPF do aluno.
	 * @param senha           Senha do aluno.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas pela atividade.
	 * @param subtipo         Subtipo da atividade (por exemplo, "Pesquisa" ou "Extensão").
	 * @return Uma mensagem de sucesso ou erro relacionada à criação da atividade.
	 */
	public String criarAtividadePesquisaExtensao(String cpf, String senha, int unidadeAcumulada, String subtipo) {
	    return criarAtividade(cpf, senha, unidadeAcumulada, TipoAtividade.PESQUISA_EXTENSAO, subtipo);
	}

	/**
	 * Cria uma atividade do tipo Representação Estudantil para o aluno identificado pelo CPF e senha.
	 *
	 * @param cpf             CPF do aluno.
	 * @param senha           Senha do aluno.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas pela atividade.
	 * @param subtipo         Subtipo da atividade (por exemplo, "Representante de turma").
	 * @return Uma mensagem de sucesso ou erro relacionada à criação da atividade.
	 */
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
	    return criarAtividade(cpf, senha, unidadeAcumulada, TipoAtividade.REPRESENTACAO_ESTUDANTIL, subtipo);
	}

	/**
	 * Cria uma atividade do tipo Monitoria para o aluno identificado pelo CPF e senha.
	 *
	 * @param cpf             CPF do aluno.
	 * @param senha           Senha do aluno.
	 * @param unidadeAcumulada Quantidade de unidades acumuladas pela atividade.
	 * @param disciplina      Nome da disciplina na qual a monitoria foi realizada.
	 * @return Uma mensagem de sucesso ou erro relacionada à criação da atividade.
	 */
	public String criarAtividadeMonitoria(String cpf, String senha, int unidadeAcumulada, String disciplina) {
	    return criarAtividade(cpf, senha, unidadeAcumulada, TipoAtividade.MONITORIA, disciplina);
	}
	
	private String formatarRelatorio(Usuario usuario, boolean parcial, boolean porAtividade, TipoAtividade tipoAtividade) {
	    StringBuilder sb = new StringBuilder();

	    sb.append(String.format("%s, %s, %s.\n", 
	        usuario.getNome(), 
	        usuario.getCpf(), 
	        usuario.getMatricula()));

	    if (porAtividade && tipoAtividade != null) {
	        sb.append(formatarResumoAtividade(usuario, tipoAtividade));
	        if (!parcial) return sb.toString();
	    }

	    if (!porAtividade) {
	        for (TipoAtividade tipo : TipoAtividade.values()) {
	            sb.append(formatarResumoAtividade(usuario, tipo));
	        }

	        if (!parcial) {
	            sb.append("TOTAL: ").append(usuario.getCreditosAcumulados()).append("\n");
	        }
	    }

	    return sb.toString();
	}

	private String formatarResumoAtividade(Usuario usuario, TipoAtividade tipo) {
	    int creditos = usuario.creditosAtividade(tipo);
	    int maximo = usuario.getCreditoMaximoPorAtividade(tipo);
	    return String.format("%s: %d/%d\n", tipo, creditos, maximo);
	}
	
	/**
	 * Gera um mapa de créditos acumulados por tipo de atividade para o aluno autenticado.
	 *
	 * @param cpf   CPF do aluno.
	 * @param senha Senha do aluno.
	 * @return Mapa formatado com os créditos por tipo de atividade.
	 */
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		return usuario.gerarMapaDeCreditos();
	}
	
	/**
	 * Retorna a quantidade de créditos acumulados em uma atividade específica.
	 *
	 * @param cpf   CPF do aluno.
	 * @param senha Senha do aluno.
	 * @param tipo  Tipo da atividade (ex: "estagio", "monitoria").
	 * @return Créditos acumulados para o tipo de atividade informado.
	 */
	public int creditosAtividade(String cpf, String senha, String tipo) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		TipoAtividade atividade = TipoAtividade.converterStringEmAtividade(tipo);
		return usuario.creditosAtividade(atividade);
	}
	
	/**
	 * Verifica se o aluno já alcançou a meta total de créditos.
	 *
	 * @param cpf   CPF do aluno.
	 * @param senha Senha do aluno.
	 * @return true se a meta foi alcançada, false caso contrário.
	 */
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
        return usuario.metaAlcancada();
    }

	/**
	 * Altera a descrição de uma atividade existente do aluno.
	 *
	 * @param cpf             CPF do aluno.
	 * @param senha           Senha do aluno.
	 * @param codigoAtividade Código identificador da atividade.
	 * @param descricao       Nova descrição a ser definida.
	 * @return true se a alteração foi bem-sucedida, false caso contrário.
	 */
	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		return usuario.alterarDescricaoAtividade(codigoAtividade, descricao);
	}
	
	/**
	 * Altera o link de comprovação de uma atividade existente do aluno.
	 *
	 * @param cpf             CPF do aluno.
	 * @param senha           Senha do aluno.
	 * @param codigoAtividade Código identificador da atividade.
	 * @param linkComprovacao Novo link de comprovação.
	 * @return true se a alteração foi bem-sucedida, false caso contrário.
	 */
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		return usuario.alterarComprovacaoAtividade(codigoAtividade, linkComprovacao);
	}
	
	private String gerarRelatorio(String cpf, String senha, boolean parcial, boolean porAtividade, String tipo, boolean salvar) {
	    Usuario usuario = obterUsuarioAutenticado(cpf, senha);
	    TipoAtividade atividade = tipo == null ? null : TipoAtividade.converterStringEmAtividade(tipo);

	    if (!parcial) {
	        if (porAtividade && !usuario.metaAlcancadaPorAtividade(atividade)) 
	            throw new IllegalArgumentException("Meta de créditos da atividade não alcançada.");
	        if (!porAtividade && !usuario.metaAlcancada()) 
	            throw new IllegalArgumentException("Meta de créditos não alcançada.");
	    }

	    String relatorio = formatarRelatorio(usuario, parcial, porAtividade, atividade);
	    if (salvar) usuario.salvarRelatorio(LocalDate.now(), relatorio);
	    return relatorio;
	}
	
	/**
	 * Gera um relatório parcial geral (sem necessidade de meta alcançada).
	 *
	 * @param cpf    CPF do aluno.
	 * @param senha  Senha do aluno.
	 * @param salvar Indica se o relatório deve ser salvo no histórico.
	 * @return O relatório parcial gerado.
	 */
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
	    return gerarRelatorio(cpf, senha, true, false, null, salvar);
	}

	/**
	 * Gera um relatório final geral, desde que a meta total de créditos tenha sido alcançada.
	 *
	 * @param cpf   CPF do aluno.
	 * @param senha Senha do aluno.
	 * @return O relatório final gerado.
	 */
	public String gerarRelatorioFinal(String cpf, String senha) {
	    return gerarRelatorio(cpf, senha, false, false, null, false);
	}

	/**
	 * Gera um relatório parcial para um tipo específico de atividade.
	 *
	 * @param cpf           CPF do aluno.
	 * @param senha         Senha do aluno.
	 * @param salvar        Indica se o relatório deve ser salvo no histórico.
	 * @param tipoAtividade Tipo da atividade.
	 * @return O relatório parcial por atividade.
	 */
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
	    return gerarRelatorio(cpf, senha, true, true, tipoAtividade, salvar);
	}

	/**
	 * Gera um relatório final para um tipo específico de atividade, desde que a meta dessa atividade tenha sido alcançada.
	 *
	 * @param cpf           CPF do aluno.
	 * @param senha         Senha do aluno.
	 * @param tipoAtividade Tipo da atividade.
	 * @return O relatório final por atividade.
	 */
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
	    return gerarRelatorio(cpf, senha, false, true, tipoAtividade, false);
	}

	/**
	 * Lista o histórico de relatórios salvos pelo aluno.
	 *
	 * @param cpf   CPF do aluno.
	 * @param senha Senha do aluno.
	 * @return Uma lista formatada do histórico de relatórios.
	 */
	public String listarHistorico(String cpf, String senha) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		return usuario.listarHistorico();
	}
	
	/**
	 * Exclui um relatório do histórico com base na data fornecida.
	 *
	 * @param cpf   CPF do aluno.
	 * @param senha Senha do aluno.
	 * @param data  Data do relatório a ser excluído, no formato "dd/MM/yyyy".
	 * @return true se a exclusão foi bem-sucedida.
	 * @throws IllegalArgumentException se a data não for encontrada no histórico.
	 */
	public boolean excluirItemHistorico(String cpf, String senha, String data) {
		Usuario usuario = obterUsuarioAutenticado(cpf, senha);
		LocalDate dataFormatada = Validador.validarEConverterData(data);
		
		if (!usuario.excluirItemHistorico(dataFormatada)) throw new IllegalArgumentException("Data não encontrada no histórico.");
		return true;
	}
}
