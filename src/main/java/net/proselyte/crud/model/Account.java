package net.proselyte.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends ClassId {
//    @Id
//    @Column(name = "id",unique = true,nullable = false,length = 5)
//    private Long id;

    @Column(name="accountData")
    private String accountData;

    public Account(){

    }

    public Account(Long id, String accountData){
        super.id = id;
        this.accountData = accountData;
    }

    public String getAccountData() {
        return accountData;
    }

    public void setAccountData(String accountData) {
        this.accountData = accountData;
    }

    public Long getId() {
        return super.id;
    }

    public void setId(Long id) {
        super.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountData='" + accountData + '\'' +
                '}';
    }


}
