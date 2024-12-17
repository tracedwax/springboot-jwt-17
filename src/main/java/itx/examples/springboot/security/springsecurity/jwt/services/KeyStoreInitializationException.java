package itx.examples.springboot.security.springsecurity.jwt.services;

/**
 * A custom exception class indicating that an error occurred during the initialization
 * of the keystore. This exception is thrown by the KeyStoreServiceImpl if there are
 * problems loading the keystore or accessing keys.
 */
public class KeyStoreInitializationException extends Exception {

    /**
     * Constructs a new KeyStoreInitializationException with the specified cause.
     * @param e The underlying Throwable that caused this exception.
     */
    public KeyStoreInitializationException(Throwable e) {
        super(e);
    }

}
