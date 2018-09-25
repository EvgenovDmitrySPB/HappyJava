package net.proselyte.crud.view;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.controller.AccountController;
import net.proselyte.crud.controller.DeveloperController;
import net.proselyte.crud.controller.SkillController;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeveloperView {
    private DeveloperController developerController;

    public DeveloperView(){
        developerController = new DeveloperController();
    }

    public void saveDeveloper(){
        //TODO: get data from console
        //TODO: build Skill instance from console data
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter first name");
        String firstName = scanner2.next();
        System.out.println("Please enter last name");
        String lastName = scanner2.next();
        System.out.println("Please enter specialty");
        String specialty = scanner2.next();

        boolean repeatAccount = true;
        AccountController accountController;
        Account account = null;

        while (repeatAccount){
            System.out.println("Please enter Account id");
            Long accountId = scanner2.nextLong();
            accountController = new AccountController();
            account = accountController.getAccountById(accountId);
            if (account.getId() == null){
                System.out.println("Account with id=" + accountId + " not exist!");
            }
            System.out.println("Do you want to enter other account ? 1 - yes, 2- no");
            Scanner scanner3 = new Scanner(System.in);
            if (scanner3.nextInt() != 1){
                repeatAccount = false;
            }
        }

        boolean repeatSkill = true;
        Set<Skill> skills = new HashSet<>();
        SkillController skillController = new SkillController();

        while (repeatSkill){
            System.out.println("Please enter Skill id");
            Long skillId = scanner2.nextLong();
            Skill skill = skillController.getSkillById((long) skillId.intValue());
            if (skill.getId() == null){
                System.out.println("Skill with id=" + skillId + " not exist!");
            }else{
                skills.add(skill);
            }
            System.out.println("Do you want to enter more/other  skill ? 1 - yes, 2- no");
            Scanner scanner4 = new Scanner(System.in);
            if (scanner4.nextInt() != 1){
                repeatSkill = false;
            }
        }

        DeveloperBuilder developerBuilder = new DeveloperBuilder();
        developerBuilder.withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).withAccount(account).withSkill(skills);
        developerController.saveDeveloper(developerBuilder.toDeveloper());
    }

    public void getDeveloperById() {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();

        Developer developer = developerController.getDeveloperById(id);
        if (developer.getId() != null){
            System.out.println(developer.toString());
        }else {
            System.out.println("Developer not found by id = " + id);
        }
    }

    public void deleteById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for delete:");
        Long id = scanner2.nextLong();

        developerController.deleteById(id);
    }

    public void getAllDeveloper(){
        developerController.getAll();
    }

    public void updateDeveloper() throws IOException {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for update:");
        Long id = scanner2.nextLong();
        System.out.println("Please enter new firstName:");
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String firstName = " ";
        if ((firstName = bufReader.readLine()) != null);

        DeveloperBuilder skillBuilder = new DeveloperBuilder();
        skillBuilder.withId(id).withFirstName(firstName);
        developerController.updateDeveloper(skillBuilder.toDeveloper());

    }
}
