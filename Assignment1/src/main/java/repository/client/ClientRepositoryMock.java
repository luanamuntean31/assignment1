package repository.client;


import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ClientRepositoryMock implements ClientRepository {

    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }


    @Override
    public Notification<Client> viewClient(String name) {
        return null;
    }

    @Override
    public boolean add(Client client) {
        return clients.add(client);

    }

    @Override
    public boolean update(Client client,Long currId) {
        return false;
    }

    @Override
    public void removeAll() {
        clients.clear();
    }

    public List<Client> findAll() {
        return clients;
    }







}

