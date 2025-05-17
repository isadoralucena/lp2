package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Representação da rotina de descanso de um aluno.
 * A classe permite acompanhar e calcular o status geral do descanso
 * de um aluno com base no número de horas de descanso e no número de semanas consideradas.
 * 
 * 
 * @author Isadora Lucena
 */
public class Descanso {
    private String statusGeral;
    private int horasDescanso;
    private int numeroSemanas;

    /**
     * Constrói um objeto da classe Descanso.
     * Inicializa o status geral como "cansado".
     */
    public Descanso() {
        this.statusGeral = "cansado";
    }

    /**
     * Define a quantidade de horas de descanso do aluno.
     * 
     * @param valor a quantidade de horas de descanso.
     */
    public void defineHorasDescanso(int valor) {
        this.horasDescanso = valor;
    }

    /**
     * Define o número de semanas consideradas para calcular o status geral do descanso.
     * Após definir o número de semanas, o status geral é recalculado.
     * 
     * @param valor o número de semanas.
     */
    public void defineNumeroSemanas(int valor) {
        this.numeroSemanas = valor;
        calculaStatusGeral();
    }

    /**
     * Retorna o status geral do descanso do aluno.
     * 
     * @return o status geral do descanso.
     */
    public String getStatusGeral() {
        return statusGeral;
    }

    private void calculaStatusGeral() {
        int calculo = horasDescanso / numeroSemanas;
        if (calculo >= 26) {
            statusGeral = "descansado";
        } else {
            statusGeral = "cansado";
        }
    }
}