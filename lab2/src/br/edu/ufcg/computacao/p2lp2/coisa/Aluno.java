package br.edu.ufcg.computacao.p2lp2.coisa;

/**
 * Representação de um Aluno.
 * A classe mantém informações básicas sobre o aluno, como nome, ano de nascimento e CRA.
 * Permite definir e obter informações relacionadas ao aluno.
 * 
 * @author Isadora Lucena
 */
public class Aluno {
    private String nome;
    private int anoNascimento;
    private double cra;

    /**
     * Constrói um aluno a partir do nome e do ano de nascimento.
     * Inicialmente, o CRA é definido como 0.0.
     * 
     * @param nome o nome do aluno.
     * @param anoNascimento o ano de nascimento do aluno.
     */
    public Aluno(String nome, int anoNascimento) {
        this.nome = nome;
        this.cra = 0.0;
        this.anoNascimento = anoNascimento;
    }

    /**
     * Define o CRA (Coeficiente de Rendimento Acadêmico) do aluno.
     * 
     * @param cra o valor do CRA a ser definido.
     */
    public void setCra(double cra) {
        this.cra = cra;
    }

    /**
     * Retorna a idade do aluno com base no ano atual.
     * 
     * @return a idade do aluno.
     */
    public int getIdade() {
        return 2021 - anoNascimento;
    }

    /**
     * Retorna a representação textual do aluno.
     * A representação inclui apenas o tipo e o nome do aluno.
     * 
     * @return a representação em String do aluno.
     */
    @Override
    public String toString() {
        return "Aluno - " + this.nome;
    }
}
