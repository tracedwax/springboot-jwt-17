package itx.examples.springboot.security.springsecurity.jwt.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import itx.examples.springboot.security.springsecurity.jwt.services.dto.JWToken;
import itx.examples.springboot.security.springsecurity.jwt.services.dto.LoginRequest;
import itx.examples.springboot.security.springsecurity.jwt.services.dto.UserData;

import java.util.Optional;

/**
 * Service interface for managing user access and JWTs. This service provides methods for user login,
 * JWT validation, and session invalidation (logout).
 */
public interface UserAccessService {

    /**
     * Attempts to log in a user with the provided credentials.  If successful, a new JWT is generated
     * and returned along with user data.
     * @param loginRequest The login request containing user ID and password.
     * @return An Optional containing the UserData object (including the generated JWT) if login is successful;
     *         otherwise, Optional.empty().
     */
    Optional<UserData> login(LoginRequest loginRequest);

    /**
     * Validates a JWT token.  This method verifies the token's signature and checks its validity.
     * @param jwToken The JWT token to validate.
     * @return An Optional containing the Jws object with Claims if the token is valid; otherwise, Optional.empty().
     */
    Optional<Jws<Claims>> validate(JWToken jwToken);

    /**
     * Invalidates a user's session by removing their associated key from the KeyStore cache.  This
     * effectively renders any JWT associated with that session invalid, even if it hasn't expired.
     * @param jwToken The JWT token associated with the session to invalidate.  The token is used to identify
     *                the user whose session should be ended.
     * @return True if the logout was successful (key removed from cache), false otherwise.
     */
    boolean logout(JWToken jwToken);

}
