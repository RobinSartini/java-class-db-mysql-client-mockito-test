package com.dev;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
        System.out.println("Hello World!");
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
