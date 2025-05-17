package br.edu.ufcg.computacao.p2lp2.coisa;

import java.util.Arrays;

/**
 * Representação de uma disciplina que um aluno cursa.
 * A disciplina possui um nome, número de horas de estudo dedicadas,
 * notas relacionadas e uma média calculada com base nas notas.
 * 
 * Cada disciplina é composta por 4 notas e permite o controle
 * do progresso do aluno, como horas de estudo e aprovação.
 * 
 * @author Isadora Lucena
 */
public class Disciplina {
    private String nome;
    private int horasEstudo;
    private double[] notas;
    private double media;

    /**
     * Constrói um objeto Disciplina com o nome especificado.
     * Inicializa o array de notas com capacidade para 4 valores.
     * 
     * @param nomeDisciplina o nome da disciplina.
     */
    public Disciplina(String nomeDisciplina) {
        this.nome = nomeDisciplina;
        notas = new double[4];
    }

    /**
     * Adiciona horas de estudo à disciplina.
     * 
     * @param horas o número de horas dedicadas ao estudo.
     */
    public void cadastraHoras(int horas) {
        this.horasEstudo += horas;
    }

    /**
     * Cadastra ou atualiza uma das 4 notas da disciplina.
     * 
     * @param nota o índice da nota a ser atualizada (1 a 4).
     * @param valorNota o valor da nota a ser atribuído.
     */
    public void cadastraNota(int nota, double valorNota) {
        this.notas[nota - 1] = valorNota;
    }

    /**
     * Verifica se o aluno foi aprovado na disciplina com base na média das notas.
     * A média mínima para aprovação é 7.0.
     * 
     * @return true se a média for maior ou igual a 7.0, false caso contrário.
     */
    public boolean aprovado() {
        this.media = calculaMediaAritmetica();
        return media >= 7.0;
    }

    /**
     * Retorna uma representação em String da disciplina,
     * incluindo o nome, as horas de estudo, a média calculada e as notas.
     * 
     * @return a representação em String da disciplina.
     */
    @Override
    public String toString() {
        return nome + " " + horasEstudo + " " + media + " " + Arrays.toString(notas);
    }

    private double calculaMediaAritmetica() {
        double soma = 0;
        for (double n : notas) {
            soma += n;
        }
        return soma / 4;
    }
}
