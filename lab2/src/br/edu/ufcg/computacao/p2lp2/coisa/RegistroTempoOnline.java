package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Representação do registro de tempo online dedicado a uma disciplina.
 * A classe armazena o tempo investido em uma disciplina e verifica se a meta
 * de tempo online foi atingida.
 * 
 * @author Isadora Lucena
 */
public class RegistroTempoOnline {
    private int tempoInvestido;
    private int tempoEsperado;
    private String nomeDisciplina;

    /**
     * Constrói um registro de tempo online para a disciplina especificada.
     * O tempo esperado é definido como 120 minutos por padrão.
     * 
     * @param nomeDisciplina o nome da disciplina
     */
    public RegistroTempoOnline(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoEsperado = 120;  // Tempo esperado padrão de 120 minutos
        this.tempoInvestido = 0;
    }

    /**
     * Constrói um registro de tempo online para a disciplina especificada,
     * com um tempo esperado personalizado.
     * 
     * @param nomeDisciplina o nome da disciplina
     * @param tempoOnlineEsperado o tempo esperado em minutos
     */
    public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        this(nomeDisciplina);
        this.tempoEsperado = tempoOnlineEsperado;
    }

    /**
     * Adiciona um tempo investido à disciplina.
     * 
     * @param tempo o tempo em minutos a ser adicionado ao tempo investido
     */
    public void adicionaTempoOnline(int tempo) {
        this.tempoInvestido += tempo;
    }

    /**
     * Verifica se a meta de tempo online foi atingida.
     * A meta é atingida se o tempo investido for maior ou igual ao tempo esperado.
     * 
     * @return true se a meta de tempo online foi atingida, false caso contrário
     */
    public boolean atingiuMetaTempoOnline() {
        return tempoInvestido >= tempoEsperado;
    }

    /**
     * Retorna uma representação em string do registro de tempo online, incluindo
     * o nome da disciplina, o tempo investido e o tempo esperado.
     * 
     * @return uma string representando o registro de tempo online
     */
    @Override
    public String toString() {
        return nomeDisciplina + " " + tempoInvestido + "/" + tempoEsperado;
    }
}
