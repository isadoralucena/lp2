package br.edu.ufcg.computacao.p2lp2.coisa;

import java.util.Arrays;

/**
 * Representa um registro de resumos, permitindo armazenar, adicionar e consultar
 * resumos de temas específicos. Cada resumo é identificado pelo seu tema e possui conteúdo associado.
 *
 * O registro gerencia a capacidade de armazenamento de resumos e implementa lógica para substituir
 * resumos mais antigos quando a capacidade é excedida.
 *
 * @author Isadora Lucena
 */
public class RegistroResumos {
    private Resumo[] resumos;
    private int contador;

    /**
     * Constrói um registro de resumos com a capacidade máxima especificada.
     *
     * @param numeroDeResumos o número máximo de resumos que podem ser armazenados
     */
    public RegistroResumos(int numeroDeResumos) {
        resumos = new Resumo[numeroDeResumos];
        contador = 0;
    }

    /**
     * Adiciona um novo resumo ao registro, substituindo o mais antigo se o limite for atingido.
     *
     * @param tema o tema do resumo
     * @param resumo o conteúdo do resumo
     */
    public void adiciona(String tema, String resumo) {
    	if (!temResumo(tema)) {
    		resumos[contador % resumos.length] = new Resumo(tema, resumo);
            contador ++;
    	}
    }

    /**
     * Retorna todos os resumos cadastrados como um array de strings.
     *
     * @return um array de strings representando os resumos cadastrados
     */
    public String[] pegaResumos() {
    	int resumosCadastrados = conta();
    	String[] s = new String[resumosCadastrados];
    	
    	for (int i = 0; i < resumosCadastrados; i++) {
    		s[i] = resumos[i].toString();
    	}
    	
		return s;
    }

    /**
     * Gera uma representação textual dos resumos.
     *
     * @return uma string formatada representando o registro de resumos
     */
    public String imprimeResumos() {
        return toString();
    }

	/**
	 * Conta o número de resumos cadastrados atualmente no registro.
	 *
	 * @return o número de resumos cadastrados
	 */
    public int conta() {
        if(contador < resumos.length) {
        	return contador;
        }
        return resumos.length;
    }

    /**
     * Verifica se já existe um resumo registrado com o tema especificado.
     *
     * @param tema o tema a ser verificado
     * @return true se o tema já estiver registrado, false caso contrário
     */
    public boolean temResumo(String tema) {
    	for (int i = 0; i < conta(); i++) {
    		if(resumos[i].getTema().equals(tema)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Gera uma representação textual dos temas cadastrados e o total de resumos registrados.
     *
     * @return uma string formatada contendo os temas e a contagem de resumos
     */
    @Override
    public String toString() {
    	int resumosCadastrados = conta();
        String s = "- " + resumosCadastrados + " resumo(s) cadastrados(s)\n- ";
        
        for (int i = 0; i < resumosCadastrados; i++) {
            if (i == resumosCadastrados - 1) {
                s += resumos[i].getTema();
            } else {
                s += resumos[i].getTema() + " | ";
            }
        }
        return s;
    }
}
