package net.proselyte.crud.builders;

import net.proselyte.crud.model.Account;

public class AccountBuilder {
    private Long id;
    private String accountData;

    public AccountBuilder(){

    }

    public AccountBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public AccountBuilder withAccount(String accountData){
        this.accountData = accountData;
        return this;
    }
    public Account toAccount(){
        return new Account(this.id, this.accountData);
    }

    public Account getAccount(){
        AccountBuilder accountBuilder =
        new AccountBuilder().withId(1L).withAccount("Test account");
        return accountBuilder.toAccount();
    }
}
