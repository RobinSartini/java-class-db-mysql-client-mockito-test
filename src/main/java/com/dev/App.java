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
            Client client = new Client(new Random().nextInt(100000),"test",new Date(), 2.0);
            addClient(client, clientRepository);
            displayAndGetClients(clientRepository);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<Client> displayAndGetClients(ClientRepository clientRepository) throws ClassNotFoundException, SQLException {
        List<Client>  clients = clientRepository.getAllClients();
        for (Client client : clients) {
        System.out.println(client.getName());
       }
       return clients;
    }

    public static boolean addClient(Client client, ClientRepository clientRepository) throws ClassNotFoundException, SQLException {
       return clientRepository.addClient(client);
    }


}
