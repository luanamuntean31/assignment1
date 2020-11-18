package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface  ClientRepository {

    List<Client> findAll();

    List<Client> add (Client client );

    Client findById(Long idCard) throws EntityNotFoundException;

    boolean save(Client client);

    void removeAll();

    //void update();
}
