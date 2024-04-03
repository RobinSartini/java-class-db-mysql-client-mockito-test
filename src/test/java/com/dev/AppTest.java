package com.dev;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Mock
    private ClientRepositoryImpl clientRepository;

    @InjectMocks
    App app;


    @Test
    public void testGetAllClients() {
        Client client = new Client(1,"test",new Date(), 2.0);
         try {
            when(clientRepository.getAllClients()).thenReturn(Arrays.asList(client));
            List<Client> clients = App.displayAndGetClients(clientRepository);
            assertTrue(clients.get(0).getDailyBillAmount() == 2.0);
            verify(clientRepository, times(1)).getAllClients();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddClient() {
        Client client = new Client(1,"test",new Date(), 2.0);
         try {
            when(clientRepository.addClient(client)).thenReturn(true);
            boolean result = App.addClient(client, clientRepository);
            assertTrue(result);
            verify(clientRepository, times(1)).addClient(client);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
