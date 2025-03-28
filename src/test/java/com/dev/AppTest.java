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

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
public class AppTest {

    @Mock
    private ClientRepositoryImpl clientRepository;


  


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

    @Test 
    public void testDeleteClient() {
        Client client = new Client(1,"test",new Date(), 2.0);
        try {
            when(clientRepository.deleteClient(client.getId())).thenReturn(true);
            boolean result = App.deleteClient(client.getId(), clientRepository);
            assertTrue(result);
            verify(clientRepository, times(1)).deleteClient(client.getId());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void testUpdateClient() {
        Client client = new Client(1,"test",new Date(), 2.0);
        try {
            when(clientRepository.updateClient(client.getId(), client)).thenReturn(true);
            boolean result = App.updateClient(client.getId(), client, clientRepository);
            assertTrue(result);
            verify(clientRepository, times(1)).updateClient(client.getId(), client);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void testGetClientsBetweenDates() {
        Client client = new Client(1,"test",new Date(), 2.0);
        Date startDate = new Date(System.currentTimeMillis() - 86400000); 
        Date endDate = new Date(); // Today
        try {
            when(clientRepository.getClientsBetweenDates(startDate, endDate))
                .thenReturn(Arrays.asList(client));
            List<Client> clients = App.getClientsBetweenDates(startDate, endDate, clientRepository);
            assertTrue(clients.size() == 1);
            assertTrue(clients.get(0).getName().equals("test"));
            verify(clientRepository, times(1)).getClientsBetweenDates(startDate, endDate);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void testGetClientsByDailyAmount() {
        Client client = new Client(1,"test",new Date(), 2.0);
        try {
            when(clientRepository.getClientsByDailyAmount(2.0)).thenReturn(Arrays.asList(client));
            List<Client> clients = App.getClientsByDailyAmount(2.0, clientRepository);
            assertTrue(clients.size() == 1);
            assertTrue(clients.get(0).getDailyBillAmount() == 2.0);
            verify(clientRepository, times(1)).getClientsByDailyAmount(2.0);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
