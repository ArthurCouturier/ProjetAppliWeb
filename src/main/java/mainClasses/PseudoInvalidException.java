package mainClasses;

public class PseudoInvalidException extends Throwable {
    public PseudoInvalidException() {
        super("pseudo_deja_utilise");
    }
}
