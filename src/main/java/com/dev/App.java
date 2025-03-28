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

        try {
            ClientRepository clientRepository = new ClientRepositoryImpl();

            // Test addClient method
            int random = new Random().nextInt(100000);
            Client newClient = new Client(random , "John Doe" + random, new Date(), 2.0);
            clientRepository.addClient(newClient);

            random = new Random().nextInt(100000);
            Client anotherClient = new Client(random, "John Doe" + random, new Date(), 3.5);
            clientRepository.addClient(anotherClient);

            random = new Random().nextInt(100000);
            Client thirdClient = new Client(random, "John Doe"+ random, new Date(), 2.0);
            clientRepository.addClient(thirdClient);

            // Test getAllClients method
            List<Client> clients = displayAndGetClients(clientRepository);
            System.out.println("Nombre de clients: " + clients.size());
            


            // Test updateClient method
            thirdClient = clients.get(2);
            thirdClient.setName("Jane Doe");
            thirdClient.setDailyBillAmount(15.0);
            boolean isUpdated = clientRepository.updateClient(thirdClient.getId(), thirdClient);
            System.out.println("Client mise à jour ? : " + isUpdated);

            // Test deleteClient method
            Client firstClient = clients.get(0);
            boolean isDeleted = clientRepository.deleteClient(firstClient.getId());
            System.out.println("Client supprimée ? : " + isDeleted);

            // Test getAllClients again to verify deletion
            clients = displayAndGetClients(clientRepository);

            // Test getClientsBetweenDates method
            System.out.println("Clients entre  2 dates: ");
            Date startDate = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30); // 30 days ago
            Date endDate = new Date();
            List<Client> clientsBetweenDates = clientRepository.getClientsBetweenDates(startDate, endDate);
            clientsBetweenDates.forEach(client -> System.out.println(client.toString()));

            // Test getClientsByAmount method
            System.out.println("Clients ayant le même montant de facturation: ");
            double amount = 2.0;
            List<Client> clientsByAmount = clientRepository.getClientsByDailyAmount(amount);
            clientsByAmount.forEach(client -> System.out.println(client.toString()));
           

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> displayAndGetClients(ClientRepository clientRepository)
            throws ClassNotFoundException, SQLException {
        List<Client> clients = clientRepository.getAllClients();
        for (Client client : clients) {
            System.out.println(client.toString());
        }
        return clients;
    }

    public static boolean addClient(Client client, ClientRepository clientRepository)
            throws ClassNotFoundException, SQLException {
        return clientRepository.addClient(client);
    }

}
