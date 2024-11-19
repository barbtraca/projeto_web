package exception;

public class EstudanteNaoEncontradoException extends RuntimeException {

    // Construtor padrão
    public EstudanteNaoEncontradoException(String message) {
        super(message);
    }
    
    // Construtor com mensagem e causa
    public EstudanteNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
