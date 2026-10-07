package excecoes;

public class AutenticacaoInvalidaException extends RuntimeException {
    public AutenticacaoInvalidaException(String message) {
        super(message);
    }
}
