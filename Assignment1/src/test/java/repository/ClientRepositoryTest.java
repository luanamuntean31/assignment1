package repository;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryTest {

    private static ClientRepository clientRepository;


    @BeforeClass
    public static void setupClass() {
        clientRepository = new ClientRepositoryMySQL(
                new DBConnectionFactory().getConnectionWrapper(true).getConnection());
    }


    @Before
    public void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
    public void findAll() {
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 0);
    }

    @Test
    public void add() {
        assertTrue(clientRepository.add(
                new ClientBuilder()
                        .setName("Name")
                        .setIdCard((long) 25)
                        .setCNP((long) 12345678)
                        .setAddress("Address")
                        .build()
        ));
    }

    @Test
    public void findAllWhenDbNotEmpty() {
        Client client = new ClientBuilder()
                .setName("Name")
                .setIdCard((long) 25)
                .setCNP((long) 12345678)
                .setAddress("Address")
                .build();

        clientRepository.add(client);
        clientRepository.add(client);
        clientRepository.add(client);

        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 3);
    }


    @Test
    public void update()  {
        Client client = new ClientBuilder()
                .setName("Name")
                .setIdCard((long) 25)
                .setCNP((long) 12345678)
                .setAddress("Address")
                .build();
       clientRepository.add(client);

       long currId = clientRepository.viewClient("Name").getResult().getId();


        Client updateclient = new ClientBuilder()
                .setName("NewName")
                .setIdCard((long) 17)
                .setCNP((long) 987421)
                .setAddress("NewAddress")
                .build();

        clientRepository.update(updateclient, currId);

        assertTrue(clientRepository.viewClient("NewName").getResult() !=null );
        assertTrue(clientRepository.viewClient("Name").hasErrors());
        assertTrue(clientRepository.viewClient("NewName").getResult().getId() == currId );


    }




    @Test
    public void removeAll()  {
        Client client = new ClientBuilder()
                .setName("Name")
                .setIdCard((long) 25)
                .setCNP((long) 12345678)
                .setAddress("Address")
                .build();
        clientRepository.add(client);
        clientRepository.add(client);
        clientRepository.add(client);
        clientRepository.add(client);
        clientRepository.add(client);

        clientRepository.removeAll();

        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 0);
    }

}
