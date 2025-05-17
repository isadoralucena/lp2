package complementacao.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe utilitária responsável pela validação de dados comuns,
 * como CPF, senha, texto, datas, importância e tempo.
 */
public class Validador {
	/**
     * Quantidade máxima de dígitos permitida para um CPF.
     */
	public static final int MAX_DIGITOS_CPF = 11;
	/**
     * Tamanho mínimo exigido para uma senha.
     */
    public static final int TAMANHO_MINIMO_SENHA = 8;
    /**
     * Tamanho máximo de um texto em uma dica.
     */
    public static final int VALOR_MAXIMO_DICA_TEXTO = 500;
    /**
     * Valor máximo permitido para a importância de uma dica.
     */
    public static final int VALOR_MAXIMO_IMPORTANCIA = 5;
    
    /**
     * Construtor privado para impedir instanciação da classe.
     */
    private Validador() {
        throw new UnsupportedOperationException("Esta classe fornece apenas métodos estáticos e não deve ser instanciada.");
    }
    
    /**
     * Valida se o CPF informado é composto por 11 dígitos numéricos.
     *
     * @param cpf CPF a ser validado.
     * @throws IllegalArgumentException se o CPF for nulo, vazio, tiver tamanho incorreto ou conter caracteres não numéricos.
     */
    public static void validarCPF(String cpf) {
        if (cpf == null || cpf.isBlank() ||cpf.length() != MAX_DIGITOS_CPF || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }
    
    /**
     * Valida se o ID informado é válido.
     * O ID não pode ser nulo, vazio ou composto apenas por espaços em branco.
     *
     * @param id o ID a ser validado.
     * @throws IllegalArgumentException se o ID for inválido.
     */
    public static void validaId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido.");
        }
    }
    
    /**
     * Valida se uma string não é nula nem vazia.
     *
     * @param texto Texto a ser validado.
     * @param mensagem Mensagem da exceção caso o texto seja inválido.
     * @throws IllegalArgumentException se o texto for nulo ou em branco.
     */
    public static void validarString(String texto, String mensagem) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }
    
    /**
     * Valida se uma senha não é nula, nem vazia, e possui o tamanho mínimo requerido.
     *
     * @param senha Senha a ser validada.
     * @throws IllegalArgumentException se a senha for inválida.
     */
    public static void validarSenha(String senha) {
        if (senha == null || senha.isBlank() || senha.length() < TAMANHO_MINIMO_SENHA) {
            throw new IllegalArgumentException("A senha deve ter pelo menos " 
                + TAMANHO_MINIMO_SENHA + " caracteres.");
        }
    }
    
    /**
     * Valida o texto de uma dica, verificando se é válido e se está dentro do tamanho permitido.
     *
     * @param texto Texto da dica.
     * @throws IllegalArgumentException se o texto for nulo, vazio ou ultrapassar o limite de caracteres.
     */
    public static void validarTextoDica(String texto) {
    	validarString(texto, "O texto não pode ser nulo ou vazio.");
        
        if (texto.length() > VALOR_MAXIMO_DICA_TEXTO) {
            throw new IllegalArgumentException("O texto deve ter no máximo " 
                + VALOR_MAXIMO_DICA_TEXTO + " caracteres.");
        }
    }
    
    /**
     * Valida se o tempo informado é positivo.
     *
     * @param tempo Tempo a ser validado.
     * @throws IllegalArgumentException se o tempo for negativo.
     */
    public static void validarTempoPositivo(int tempo) {
    	if (tempo <= 0) {
            throw new IllegalArgumentException("O tempo precisa ser um valor positivo.");
        }
    }
    
    /**
     * Valida se a bonificação informada é positiva.
     *
     * @param bonificacao Bonificação a ser validada.
     * @throws IllegalArgumentException se a bonificação for negativa.
     */
    public static void validarBonificacaoPositiva(int bonificacao) {
        if (bonificacao < 0) {
            throw new IllegalArgumentException("A bonificação precisa ser um valor positivo.");
        }
    }
    
    /**
     * Valida se a unidade acumulada informada é positiva.
     *
     * @param unidadeAcumulada Unidade acumulada a ser validada.
     * @throws IllegalArgumentException se a unidade acumulada for menor ou igual a zero.
     */
    public static void validarUnidadeAcumuladaPositiva(int unidadeAcumulada) {
        if (unidadeAcumulada <= 0) {
            throw new IllegalArgumentException("A unidade acumulada precisa ser um valor positivo.");
        }
    }
    
    /**
     * Valida a importância atribuída a uma dica, garantindo que esteja entre 1 e 5.
     *
     * @param importancia Valor da importância.
     * @throws IllegalArgumentException se a importância estiver fora do intervalo válido.
     */
    public static void validarImportanciaDica(int importancia) {
    	if (importancia <= 0 || importancia > VALOR_MAXIMO_IMPORTANCIA) {
            throw new IllegalArgumentException("Importância precisa estar entre 1 e 5.");
        }
    }
    
    /**
     * Valida uma data no formato "dd/MM/yyyy" e a converte para um {@link LocalDate}.
     *
     * @param data String representando a data.
     * @return Objeto {@link LocalDate} correspondente à data.
     * @throws IllegalArgumentException se o formato da data for inválido.
     */
    public static LocalDate validarEConverterData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida! Use o formato dd/MM/yyyy.");
        }
    }
}
