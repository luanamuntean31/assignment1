package service.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;


public interface  ClientService {
    List<Client> findAll();

    Client findById(Long idCard) throws EntityNotFoundException;

    boolean save(Client client);



}
