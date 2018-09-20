package net.proselyte.crud.view;

import net.proselyte.crud.builders.AccountBuilder;
import net.proselyte.crud.controller.AccountController;
import net.proselyte.crud.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountView {
    private AccountController accountController;

    public AccountView(){
        accountController = new AccountController();
    }

    public void saveAccount() throws IOException {
        //TODO: get data from console
        //TODO: build Skill instance from console data
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();
        System.out.println("Please enter name");
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String name = " ";
        if ((name = bufReader.readLine()) != null);

        AccountBuilder skillBuilder = new AccountBuilder();
        skillBuilder.withId(id).withAccount(name);
        accountController.saveAccount(skillBuilder.toAccount());
    }

    public void getSkillById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();

        Account skill = accountController.getAccountById(id);
        if (skill.getId() != null){
            System.out.println(skill.toString());
        }else {
            System.out.println("Account not found by id = " + id);
        }
    }

    public void deleteById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for delete:");
        Long id = scanner2.nextLong();

        accountController.getAccountById(id);
    }

    public void getAllAccount(){
        accountController.getAll();
    }
}
