package repository.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface  ClientRepository {


    boolean add (Client client );

    boolean update (Client client,Long currId );

    Notification<Client> viewClient (String name);

    void removeAll();

    List<Client> findAll();



}
