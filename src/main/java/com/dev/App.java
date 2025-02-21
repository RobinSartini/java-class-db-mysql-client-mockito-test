package com.dev;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        // test all 6 methods from ClientRepositoryImpl
        try {
            ClientRepository clientRepository = new ClientRepositoryImpl();

            // Test addClient method
            Client newClient = new Client(new Random().nextInt(100000), "John Doe", new Date(), 3.5);
            clientRepository.addClient(newClient);

            Client anotherClient = new Client(new Random().nextInt(100000), "John Doe", new Date(), 3.5);
            clientRepository.addClient(anotherClient);

            Client thirdClient = new Client(new Random().nextInt(100000), "John Doe", new Date(), 3.5);
            clientRepository.addClient(thirdClient);


            // Test getAllClients method
            List<Client> clients = displayAndGetClients(clientRepository);
            System.out.println("Number of clients: " + clients.size());
            Client firstClient = clients.get(0);


            // Test updateClient method
            newClient.setName("Jane Doe");
            boolean isUpdated = clientRepository.updateClient(firstClient.getId(), newClient);
            System.out.println("Client updated: " + isUpdated);

            // Test deleteClient method
            boolean isDeleted = clientRepository.deleteClient(firstClient.getId());
            System.out.println("Client deleted: " + isDeleted);

            // Test getAllClients again to verify deletion
            clients = displayAndGetClients(clientRepository);
            System.out.println("Number of clients after deletion: " + clients.size());

            // Test getClientsBetweenDates method
            Date startDate = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30); // 30 days ago
            Date endDate = new Date();
            List<Client> clientsBetweenDates = clientRepository.getClientsBetweenDates(startDate, endDate);
            System.out.println("Number of clients between dates: " + clientsBetweenDates.size());

            // Test getClientsByAmount method
            double amount = 2.0;
            List<Client> clientsByAmount = clientRepository.getClientsByDailyAmount(amount);
            System.out.println("Number of clients by amount: " + clientsByAmount.size());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> displayAndGetClients(ClientRepository clientRepository)
            throws ClassNotFoundException, SQLException {
        List<Client> clients = clientRepository.getAllClients();
        for (Client client : clients) {
            System.out.println(client.getName());
        }
        return clients;
    }

    public static boolean addClient(Client client, ClientRepository clientRepository)
            throws ClassNotFoundException, SQLException {
        return clientRepository.addClient(client);
    }

    public static void simpleTest() {
        try {
            ClientRepository clientRepository = new ClientRepositoryImpl();
            Client client = new Client(new Random().nextInt(100000), "test", new Date(), 2.0);
            addClient(client, clientRepository);
            displayAndGetClients(clientRepository);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
