package net.proselyte.crud.model;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String accountData;

    public Account(){

    }

    public Account(Long id, String accountData){
        this.id = id;
        this.accountData = accountData;
    }

    public String getAccountData() {
        return accountData;
    }

    public void setAccountData(String accountData) {
        this.accountData = accountData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountData='" + accountData + '\'' +
                '}';
    }
}
