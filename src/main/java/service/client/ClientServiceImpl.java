package service.client;

import model.Client;
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
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client findById(Long idCard) throws EntityNotFoundException {
        return repository.findById(idCard);
    }

    @Override
    public boolean save(Client client) {
        return repository.save(client);
    }



}
