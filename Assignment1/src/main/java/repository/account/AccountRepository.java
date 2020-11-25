package repository.account;

import model.Account;
import model.Client;
import model.validation.Notification;

import java.util.List;

public interface AccountRepository {

    boolean add (Account account ,Long clientId);

    boolean update (Account account, Long nrAccount );

    Notification<Account> viewAccount (Long nrAccount);

    boolean delete ( Notification<Account> account,Long nrAccount );

    void removeAll();

   List<Account> findAll();



}
