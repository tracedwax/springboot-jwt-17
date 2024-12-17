package itx.examples.springboot.security.springsecurity.jwt.services;

/**
 * Utility class for JSON Web Token (JWT) operations. This class provides helper methods
 * for creating authorization headers and extracting JWT tokens from authorization headers.
 */
public final class JWTUtils {

    /**
     * The prefix used for JWT authorization headers ("Bearer ").
     */
    public static final String BEARER_PREFIX = "Bearer ";

    private JWTUtils() {
        throw new UnsupportedOperationException("Do not instantiate utility class.");
    }

    /**
     * Creates a JWT authorization header string.
     * @param jwToken The JWT token string.
     * @return The authorization header string, including the "Bearer " prefix.
     */
    public static String createAuthorizationHeader(String jwToken) {
        return BEARER_PREFIX + jwToken;
    }

    /**
     * Extracts the JWT token from an authorization header string.  This method assumes the header
     * is in the format "Bearer <token>".
     * @param authorization The authorization header string.
     * @return The JWT token string.
     */
    public static String extractJwtToken(String authorization) {
        return authorization.substring(BEARER_PREFIX.length(), authorization.length()).trim();
    }

    /**
     * Removes the signature from a JWT token string.  This is useful for parsing the JWT header and body
     * without needing the verification key.
     * @param jwToken The JWT token string.
     * @return The JWT token string with the signature removed.
     */
    public static String removeSignature(String jwToken) {
        return jwToken.substring(0, (jwToken.lastIndexOf('.') + 1));
    }

}
