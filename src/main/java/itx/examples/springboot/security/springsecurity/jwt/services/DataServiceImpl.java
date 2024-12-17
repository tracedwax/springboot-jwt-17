package itx.examples.springboot.security.springsecurity.jwt.services;

import itx.examples.springboot.security.springsecurity.jwt.services.dto.ServerData;
import org.springframework.stereotype.Service;

/**
 * A basic implementation of the DataService interface. This service provides a simple,
 * hardcoded implementation for demonstration or testing purposes.  In a production
 * environment, this would be replaced with a service that retrieves data from a
 * persistent store (e.g., a database).
 */
@Service
public class DataServiceImpl implements DataService {

    /**
     * Retrieves a ServerData object with hardcoded data. This method is intended for
     * demonstration or testing purposes only and should not be used in a production
     * environment.  The returned ServerData contains the input source, a fixed string
     * "Some Data", and the current system time.
     * @param source The source identifier (used only in the returned ServerData object).
     * @return A ServerData object with hardcoded data.
     */
    @Override
    public ServerData getSecuredData(String source) {
        return new ServerData(source, "Some Data", System.currentTimeMillis());
    }

}
