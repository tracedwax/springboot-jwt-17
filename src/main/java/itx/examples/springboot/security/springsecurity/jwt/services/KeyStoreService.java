package itx.examples.springboot.security.springsecurity.jwt.services;

import itx.examples.springboot.security.springsecurity.jwt.services.dto.UserId;

import java.security.Key;
import java.util.Optional;

/**
 * Service interface for managing user keys within a KeyStore. This service provides methods
 * for retrieving the Certificate Authority (CA) key, creating user keys, retrieving user keys
 * from a cache, and removing user keys from the cache.  The underlying KeyStore implementation
 * is abstracted away by this interface.
 */
public interface KeyStoreService {

    /**
     * Retrieves the private key of the Certificate Authority (CA).
     * @return The CA's private key.
     */
    Key getCertificationAuthorityKey();

    /**
     * Creates a new key pair for the specified user and stores the private key in an internal cache.
     *  The public key is presumably managed elsewhere in the implementation.
     * @param userId The ID of the user for whom to create the key.
     * @return The user's private key.
     */
    Key createUserKey(UserId userId);

    /**
     * Retrieves the user's private key from the internal cache.
     * @param userId The ID of the user whose key is to be retrieved.
     * @return An Optional containing the user's private key if found in the cache; otherwise, Optional.empty().
     */
    Optional<Key> getUserKey(UserId userId);

    /**
     * Removes the user's private key from the internal cache.
     * @param userId The ID of the user whose key is to be removed.
     * @return True if the key was successfully removed; otherwise, false.
     */
    boolean removeUserKey(UserId userId);

}
