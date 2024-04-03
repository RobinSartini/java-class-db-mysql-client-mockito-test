package com.dev;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository{

    @Override
    public boolean addClient(Client client) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStudent'");
    }

    @Override
    public boolean deleteClient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudent'");
    }

    @Override
    public boolean updateClient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    @Override
    public List<Client> getAllClients() throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllClients'");
    }

    @Override
    public List<Client> getClientsContactDateBetween(Date start, Date end) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientsContactDateBetween'");
    }

    @Override
    public List<Client> getClientsByDailyAmount() throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientsByDailyAmount'");
    }
    
}
