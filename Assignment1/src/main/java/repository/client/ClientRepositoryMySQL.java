package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySQL implements ClientRepository{

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }



    @Override
    public boolean add(Client client) {

        try {
            PreparedStatement insertClientStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            insertClientStatement.setString(1, client.getName());
            insertClientStatement.setLong(2, client.getIdCard());
            insertClientStatement.setLong(3, client.getCNP());
            insertClientStatement.setString(4, client.getAddress());
            insertClientStatement.executeUpdate();

            ResultSet rs = insertClientStatement.getGeneratedKeys();
            rs.next();
            long clientId = rs.getLong(1);
            client.setId(clientId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    //al 2 lea param id ul clientului deja existent in bd
    //id va lua id ul existent in antet
    public boolean update(Client client,Long currId) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE client SET name = ?, idCard = ?, CNP=?, address=?\n" +
                            "WHERE idClient = ?");
            updateStatement.setString(1, client.getName());
            updateStatement.setLong(2, client.getIdCard());
            updateStatement.setLong(3, client.getCNP());
            updateStatement.setString(4, client.getAddress());
            updateStatement.setLong(5, currId);

            updateStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    @Override
    public Notification<Client> viewClient(String name) {
        Notification<Client> findByNameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM  client  WHERE name=\'"+name+"\'";
            ResultSet clientResultSet = statement.executeQuery(sql);

            if(clientResultSet.next())
            {
                Client client = new ClientBuilder()
                        .setId(clientResultSet.getLong("idClient"))
                        .setName(clientResultSet.getString("name"))
                        .setIdCard(clientResultSet.getLong("idCard"))
                        .setCNP(clientResultSet.getLong("CNP"))
                        .setAddress(clientResultSet.getString("address"))
                        .build();

                findByNameNotification.setResult(client);
                return findByNameNotification;

            }
            else {
                findByNameNotification.addError("Invalid name!");
                return findByNameNotification;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findByNameNotification;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            //verif incepand cu primul elem
            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }


    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("idClient"))
                .setName(rs.getString("name"))
                .setIdCard(rs.getLong("idCard"))
                .setCNP(rs.getLong("CNP"))
                .setAddress(rs.getString("address"))
                .build();
    }




}
