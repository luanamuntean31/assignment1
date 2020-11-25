package repository;

import database.DBConnectionFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountRepositoryTest {
    private static AccountRepository accountRepository;
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection= new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository=new ClientRepositoryMySQL(connection);
        accountRepository = new AccountRepositoryMySQL(connection);
    }


    @Before
    public void cleanUp() {
        accountRepository.removeAll();
    }

    @Test
    public void findAll() {
        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void add() {
        Client client = new ClientBuilder()
                .setName("Name")
                .setIdCard((long) 25)
                .setCNP((long) 12345678)
                .setAddress("Address")
                .build();

        clientRepository.add(client);

        assertTrue(accountRepository.add(
                new AccountBuilder()
                        .setAccountNumber((long) 19)
                        .setAccountType("typeAccount")
                        .setAmount((long) 3000)
                        .setCreationDate(new Date())
                        .build(),
                client.getId()));
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


       Account account = new AccountBuilder()
                .setAccountNumber((long) 19)
                .setAccountType("typeAccount")
                .setAmount((long) 3000)
                .setCreationDate(new Date())
                .build();


        accountRepository.add(account,client.getId());
        accountRepository.add(account,client.getId());
        accountRepository.add(account,client.getId());

        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 3);
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


        Account account = new AccountBuilder()

                .setAccountType("typeAccount")
                .setAmount((long) 3000)
                .setCreationDate(new Date())
                .build();

        accountRepository.add(account,client.getId());

        Account updateaccount = new AccountBuilder()

                .setAccountType("typeNewAccount")
                .setAmount((long) 7000)
                .setCreationDate(new Date())
                .build();

        accountRepository.update(updateaccount, account.getAccountNumber());

        //assertTrue(accountRepository.viewAccount(updateaccount.getAccountNumber()).getResult() !=null );





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

        Account account = new AccountBuilder()
                .setAccountNumber((long) 19)
                .setAccountType("typeAccount")
                .setAmount((long) 3000)
                .setCreationDate(new Date())
                .build();

        accountRepository.add(account,client.getId());
        accountRepository.add(account,client.getId());
        accountRepository.add(account,client.getId());
        accountRepository.add(account,client.getId());
        accountRepository.add(account,client.getId());

        accountRepository.removeAll();

        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 0);
    }
}
