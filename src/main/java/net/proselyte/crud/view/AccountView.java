package net.proselyte.crud.view;

import net.proselyte.crud.controller.AccountController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
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
        System.out.println("Please enter name");
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        if ((name = bufReader.readLine()) != null);
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);

        accountController.saveAccount(map);
    }

    public void getSkillById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();

        accountController.getAccountById(id);
    }

    public void deleteById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for delete:");
        Long id = scanner2.nextLong();

        accountController.deleteById(id);
    }

    public void getAllAccount(){
        accountController.getAll();
    }

    public void updateAccount() throws IOException{
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for update:");
        Long id = scanner2.nextLong();
        System.out.println("Please enter new accountData:");
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String accountData = " ";
        if ((accountData = bufReader.readLine()) != null);

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("accountData", accountData);

        accountController.updateAccount(map);
    }
}
