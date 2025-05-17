package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 * 
 * @author Isadora Lucena
 *
 */
public class FilmNow {
    
    private static final int TAMANHO_FILMES = 100;
    private static final int TAMANHO_HOTLIST = 10;
    private Filme[] filmes;
    private Filme[] hotlist; 

    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO_FILMES];
        this.hotlist = new Filme[TAMANHO_HOTLIST];
    }
    
    /**
     * Retorna uma lista de todos os filmes cadastrados.
     * 
     * @return Lista formatada de filmes cadastrados.
     */
    public String mostraFilmes() {
        String texto = "";
        
        for(int i=0; i < TAMANHO_FILMES; i++) {
            if (filmes[i] == null) continue;
            texto += (i+1) + " - " + filmes[i].getNome() + "\n";
        }
        
        return texto;
    }
    
    private boolean filmeJaCadastradoHotlist(int posicaoFilme) {
		for(int i=0; i < TAMANHO_HOTLIST; i++) {
			if (hotlist[i] == null) continue;
			if(filmes[posicaoFilme].equals(hotlist[i])) {
				return true;
			}
		}
		return false;
	}
    
    private boolean filmeJaAdicionado(String nome, String anoLancamento, String local) {
		Filme filmeTemporario = new Filme(nome, anoLancamento, local);
		
		for(int i=0; i < TAMANHO_FILMES; i++) {
			if (filmes[i] == null) continue;
			if(filmes[i].equals(filmeTemporario)) {
				return true;
			}
		}
		return false;
	}
    
    /**
     * Acessa os dados de um filme específico.
     * 
     * @param posicao Posição do filme no sistema.
     * @return Dados do filme. "POSIÇÃO INVÁLIDA" se a posição for inválida.
     */
    public String detalhaFilme(int posicao) {
        if(posicao <= 0 || posicao > TAMANHO_FILMES || filmes[posicao-1] == null) {
            return "POSIÇÃO INVÁLIDA";
        } else if(filmeJaCadastradoHotlist(posicao-1)) {
            return "🔥 " + filmes[posicao-1].toString();
        }
        
        return filmes[posicao-1].toString();
    }
    
    /**
     * Adiciona um filme em uma posição específica. Se já existir um filme na posição, sobrescreve o anterior.
     * 
     * @param posicao Posição do filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assistido.
     * @return Mensagem indicando se o filme foi adicionado com sucesso ou se houve um erro.
     */
    public String cadastraFilme(int posicao, String nome, String ano, String local) {
        if (posicao <= 0 || posicao > TAMANHO_FILMES) {
            return "POSIÇÃO INVÁLIDA";
        }
        
        if (filmeJaAdicionado(nome, ano, local)) {
            return "FILME JÁ ADICIONADO";
        }
        
        try {
            filmes[posicao - 1] = new Filme(nome, ano, local);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        
        return "FILME ADICIONADO";
    }
    
    /**
     * Adiciona um filme na hotlist em uma posição específica.
     * 
     * @param posicaoFilme Posição do filme na lista de filmes.
     * @param posicaoHot Posição desejada na hotlist.
     * @return Mensagem indicando se o filme foi adicionado à hotlist ou se houve um erro.
     */
    public String atribuirHot(int posicaoFilme, int posicaoHot) {
        if(filmeJaCadastradoHotlist(posicaoFilme-1)) {
            return "FILME JÁ ADICIONADO";
        }else if(posicaoHot <= 0 || posicaoHot > TAMANHO_HOTLIST || posicaoFilme <= 0 || posicaoFilme > TAMANHO_FILMES)  {
            return "POSIÇÃO INVÁLIDA"; 
        }
        hotlist[posicaoHot-1] = filmes[posicaoFilme-1];
        return "FILME CADASTRADO NA HOTLIST NA POSIÇÃO " +posicaoHot+ "!";
    }
    
    /**
     * Lista os filmes presentes na hotlist.
     * 
     * @return Lista formatada de filmes na hotlist.
     */
    public String listaHotlist() {
        String texto = "";
        
        for(int i=0; i < TAMANHO_HOTLIST; i++) {
            if (hotlist[i] == null) continue;
            texto += (i+1) + " - " + hotlist[i].getNome() + ",  " + hotlist[i].getAnoLancamento() + "\n";
        }
        
        return texto;
    }
    
    /**
     * Remove um filme da hotlist em uma posição específica.
     * 
     * @param posicao Posição do filme na hotlist.
     * @return Mensagem indicando se a remoção foi realizada com sucesso.
     */
    public String removerHot(int posicao) {
        hotlist[posicao-1] = null;
        return "FILME REMOVIDO NA HOTLIST";
    }
}