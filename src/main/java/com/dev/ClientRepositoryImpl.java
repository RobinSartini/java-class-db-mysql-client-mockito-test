package com.dev;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository{

    @Override
    public boolean addClient(Client client) throws ClassNotFoundException, SQLException {
        ClientDBConfig dbConfig = new ClientDBConfig();
        Connection connection = dbConfig.getConnection();
        String insertionQuery = "INSERT INTO client (id, name, contactDate, dailyBillAmount) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
        preparedStatement.setInt(1, client.getId());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setDate(3, new java.sql.Date(client.getContactDate().getTime()));
        preparedStatement.setDouble(4, client.getDailyBillAmount());
        int rowsAffected = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return rowsAffected > 0;
    }

    @Override
    public List<Client> getAllClients() throws ClassNotFoundException, SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        ClientDBConfig dbConfig = new ClientDBConfig();
        Connection connection = dbConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from client");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date contactDate = resultSet.getDate("contactDate");
            double dailyBillAmount = resultSet.getDouble("dailyBillAmount");
            System.out.println("id: " + id);
            clients.add(new Client(id, name, contactDate, dailyBillAmount));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return clients;
    }

    @Override
    public boolean deleteClient(int id) {
        try {
            ClientDBConfig dbConfig = new ClientDBConfig();
            Connection connection = dbConfig.getConnection();
            String deletionQuery = "DELETE FROM client WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deletionQuery);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateClient(int id, Client client) {
        try {
            ClientDBConfig dbConfig = new ClientDBConfig();
            Connection connection = dbConfig.getConnection();
            String updateQuery = "UPDATE client SET name = ?, contactDate = ?, dailyBillAmount = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setDate(2, new java.sql.Date(client.getContactDate().getTime()));
            preparedStatement.setDouble(3, client.getDailyBillAmount());
            preparedStatement.setInt(4, id);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Client> getClientsContactDateBetween(java.util.Date start, java.util.Date end)
            throws ClassNotFoundException, SQLException {
        List<Client> clients = new ArrayList<>();
        ClientDBConfig dbConfig = new ClientDBConfig();
        Connection connection = dbConfig.getConnection();
        String query = "SELECT * FROM client WHERE contactDate BETWEEN ? AND ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, new java.sql.Date(start.getTime()));
        preparedStatement.setDate(2, new java.sql.Date(end.getTime()));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date contactDate = resultSet.getDate("contactDate");
            double dailyBillAmount = resultSet.getDouble("dailyBillAmount");
            clients.add(new Client(id, name, contactDate, dailyBillAmount));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return clients;
    }   

    @Override
    public List<Client> getClientsByDailyAmount(double amount) throws ClassNotFoundException, SQLException {
       
        List<Client> clients = new ArrayList<>();
        ClientDBConfig dbConfig = new ClientDBConfig();
        Connection connection = dbConfig.getConnection();
        String query = "SELECT * FROM client WHERE dailyBillAmount = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, amount);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date contactDate = resultSet.getDate("contactDate");
            double dailyBillAmount = resultSet.getDouble("dailyBillAmount");
            clients.add(new Client(id, name, contactDate, dailyBillAmount));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return clients;
    }
    
}
