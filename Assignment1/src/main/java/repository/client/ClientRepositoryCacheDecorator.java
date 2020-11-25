package repository.client;

import model.Client;
import model.validation.Notification;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator {
    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository, Cache<Client> cache) {
        super(clientRepository);
        this.cache = cache;
    }


    @Override
    public boolean add(Client client) {
        cache.invalidateCache();
        return decoratedRepository.add(client);
    }

    @Override
    public boolean update(Client client,Long currId)  {
        cache.invalidateCache();
        return decoratedRepository.update(client,currId);
    }


    @Override
    public Notification<Client> viewClient(String name) {
        cache.invalidateCache();
        return decoratedRepository.viewClient(name);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> clients = decoratedRepository.findAll();
        cache.save(clients);
        return clients;
    }

}
