package itx.examples.springboot.security.springsecurity.jwt.services;


import itx.examples.springboot.security.springsecurity.jwt.services.dto.ServerData;

/**
 * Service interface for retrieving secured data.  This interface defines a single method
 * for retrieving data, where access control is handled by the calling component.
 */
public interface DataService {

    /**
     * Retrieves secured data based on the provided source identifier.
     * @param source The identifier or name of the data source.
     * @return A ServerData object containing the retrieved data.
     */
    ServerData getSecuredData(String source);

}
