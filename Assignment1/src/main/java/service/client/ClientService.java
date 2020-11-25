package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;


public interface  ClientService {

    boolean add (Client client );

    boolean update (Client client,Long currId );

    Notification<Client> viewClient (String name);

    List<Client> findAll();



}
