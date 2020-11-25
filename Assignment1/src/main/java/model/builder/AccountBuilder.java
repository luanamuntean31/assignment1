package model.builder;
import model.Account;

import java.util.Date;


public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setAccountNumber(Long accountNumber) {
        account.setAccountNumber(accountNumber);
        return this;
    }

    public AccountBuilder setAccountType(String accountType) {
        account.setAccountType(accountType);
        return this;
    }

    public AccountBuilder setAmount(Long amount) {
        account.setAmount(amount);
        return this;
    }

    public AccountBuilder setCreationDate(Date creationDate) {
        account.setCreationDate(creationDate);
        return this;
    }

    public Account build() {
        return account;
    }


}
