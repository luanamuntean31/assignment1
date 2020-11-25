package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean add(Client client) {
        return repository.add(client);
    }

    @Override
    public boolean update(Client client, Long currId) {
        return repository.update(client,currId);
    }

    @Override
    public Notification<Client> viewClient(String name) {
        return repository.viewClient(name);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }
}
