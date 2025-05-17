package complementacao.facade;

import java.util.List;

import complementacao.controller.DicaController;
import complementacao.controller.UsuarioController;

public class Facade {
	private UsuarioController usuarioController;
	private DicaController dicaController;
	
	public Facade() {
		usuarioController = new UsuarioController();
		dicaController = new DicaController(usuarioController);
	}
	
	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		return usuarioController.criarEstudante(nome, cpf, senha, matricula);
	}
	public List<String> exibirEstudantes() {
		return usuarioController.exibirEstudantes();
	}
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		return usuarioController.alterarSenhaEstudante(cpf, senhaAntiga, novaSenha);
	}
	public int adicionarDica(String cpf, String senha, String tema) {
		return dicaController.adicionarDica(cpf, senha, tema);

	}
	public boolean adicionarElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		return dicaController.adicionarElementoTextoDica(cpf, senha, posicao, texto);
	}
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		return dicaController.adicionarElementoMultimidiaDica(cpf, senha, posicao, link, cabecalho, tempo);
	}
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia) {
		return dicaController.adicionarElementoReferenciaDica(cpf, senha, posicao, titulo, fonte, ano, conferida, importancia);
	}
	
	public List<String> listarDicas() {
		return dicaController.listarDicas();
	}
	public List<String> listarDicasDetalhes() {
		return dicaController.listarDicasDetalhes();
	}
	public String listarDica(int posicao) {
		return dicaController.listarDica(posicao);
	}
	public String listarDicaDetalhes(int posicao) {
		return dicaController.listarDicaDetalhes(posicao);
	}
	public List<String> listarUsuariosRankingDicas() {
		return usuarioController.listarUsuariosRankingDicas();
	}

	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		return usuarioController.alterarDescricaoAtividade(cpf, senha, codigoAtividade, descricao);
	}
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		return usuarioController.alterarComprovacaoAtividade(cpf, senha, codigoAtividade, linkComprovacao);
	}
	public String criarAtividadeMonitoriaEmEstudante(String cpf, String senha, int unidadeAcumulada, String disciplina) {
		return usuarioController.criarAtividadeMonitoria(cpf, senha, unidadeAcumulada, disciplina);
	}
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return usuarioController.criarAtividadePesquisaExtensao(cpf, senha, unidadeAcumulada, subtipo);
	}
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, int unidadeAcumulada, String nomeEmpresa) {
		return usuarioController.criarAtividadeEstagio(cpf, senha, unidadeAcumulada, nomeEmpresa);
	}
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return usuarioController.criarAtividadeRepresentacaoEstudantil(cpf, senha, unidadeAcumulada, subtipo);
	}
	public int creditosAtividade(String cpf, String senha, String tipo) {
		return usuarioController.creditosAtividade(cpf, senha, tipo);
	}
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		return usuarioController.gerarMapaCreditosAtividades(cpf, senha);
	}
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		return usuarioController.verificarMetaAlcancada(cpf, senha);
	}

	public String gerarRelatorioFinal(String cpf, String senha) {
		return usuarioController.gerarRelatorioFinal(cpf, senha);
	}
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
		return usuarioController.gerarRelatorioFinalPorAtividade(cpf, senha, tipoAtividade);
	}
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
		return usuarioController.gerarRelatorioParcial(cpf, senha, salvar);
	}
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
		return usuarioController.gerarRelatorioParcialPorAtividade(cpf, senha, salvar, tipoAtividade);
	}
	public String listarHistorico(String cpf, String senha) {
		return usuarioController.listarHistorico(cpf, senha);
	}
	public boolean excluirItemHistorico(String cpf, String senha, String data) {
		return usuarioController.excluirItemHistorico(cpf, senha, data);
	}
}