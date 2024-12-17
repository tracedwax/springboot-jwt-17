package itx.examples.springboot.security.springsecurity.jwt.rest;

import itx.examples.springboot.security.springsecurity.jwt.services.DataService;
import itx.examples.springboot.security.springsecurity.jwt.services.dto.ServerData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collections;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DataRestControllerTest {

    @Mock
    private DataService dataService;

    @InjectMocks
    private DataRestController dataRestController;

    @Test
    void testGetDataForUsers() {
        Authentication authentication =  new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "testuser";
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "testuser";
            }
        };
        ServerData serverData = new ServerData("test", "test", 123);
        when(dataService.getSecuredData("Secured for USER/ADMIN testuser")).thenReturn(serverData);
        ResponseEntity<ServerData> response = dataRestController.getDataForUsers(authentication);
        assertEquals(response.getStatusCodeValue(), 200);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getData(), "test");
    }

    @Test
    void testGetDataForAdmins() {
        Authentication authentication =  new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "testadmin";
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "testadmin";
            }
        };
        ServerData serverData = new ServerData("test", "test", 123);
        when(dataService.getSecuredData("Secured for ADMIN testadmin")).thenReturn(serverData);
        ResponseEntity<ServerData> response = dataRestController.getDataForAdmins(authentication);
        assertEquals(response.getStatusCodeValue(), 200);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getData(), "test");
    }

}
