package itx.examples.springboot.security.springsecurity.jwt.services;

import itx.examples.springboot.security.springsecurity.jwt.services.dto.UserId;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of the KeyStoreService interface. This service manages user keys using a Java KeyStore
 * and an in-memory cache.  It's responsible for loading the keystore, retrieving the CA key,
 * generating and caching user keys, and removing user keys from the cache.  Note that this
 * implementation uses an in-memory cache, which is not suitable for a production environment
 * where persistence is required.
 */
@Service
public class KeyStoreServiceImpl implements KeyStoreService {

    private final KeyStore keystore;
    private final Key caKey;
    private final Map<UserId, KeyPair> keyCache;
    private final KeyPairGenerator keyPairGenerator;

    /**
     * Constructs a new KeyStoreServiceImpl instance. This constructor attempts to load the keystore
     * from the "keystore.jks" file located in the classpath and initializes the CA key and key generator.
     * If any error occurs during initialization, a KeyStoreInitializationException is thrown.
     * @throws KeyStoreInitializationException If an error occurs during keystore initialization.
     */
    public KeyStoreServiceImpl() throws KeyStoreInitializationException {
        try {
            keystore = KeyStore.getInstance("JKS");
            InputStream is = KeyStoreServiceImpl.class.getResourceAsStream("/keystore.jks");
            keystore.load(is, "secret".toCharArray());
            caKey = keystore.getKey("organization", "secret".toCharArray());
            keyCache = new ConcurrentHashMap<>();
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = SecureRandom.getInstance("NativePRNG");
            keyPairGenerator.initialize(2048, secureRandom);
        } catch (Exception e) {
            throw new KeyStoreInitializationException(e);
        }
    }

    /**
     * Retrieves the Certificate Authority (CA) key.
     * @return The CA's private key.
     */
    @Override
    public Key getCertificationAuthorityKey() {
        return caKey;
    }

    /**
     * Creates a new key pair for the given user and caches the private key.
     * @param userId The ID of the user.
     * @return The user's private key.
     */
    @Override
    public Key createUserKey(UserId userId) {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //TODO: sign user's certificate using company CA.
        keyCache.put(userId, keyPair);
        return keyPair.getPrivate();
    }

    /**
     * Retrieves the user's private key from the cache.
     * @param userId The ID of the user.
     * @return An Optional containing the user's private key if found; otherwise, Optional.empty().
     */
    @Override
    public Optional<Key> getUserKey(UserId userId) {
        KeyPair pair = keyCache.get(userId);
        if (pair != null) {
            return Optional.of(keyCache.get(userId).getPrivate());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Removes the user's private key from the cache.
     * @param userId The ID of the user.
     * @return True if the key was removed, false otherwise.
     */
    @Override
    public boolean removeUserKey(UserId userId) {
        return keyCache.remove(userId) != null;
    }

}
