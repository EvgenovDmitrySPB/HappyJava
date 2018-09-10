package net.proselyte.crud.view;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.controller.AccountController;
import net.proselyte.crud.controller.DeveloperController;
import net.proselyte.crud.controller.SkillController;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeveloperView {
    private DeveloperController developerController;

    public DeveloperView() throws SQLException {
        developerController = new DeveloperController();
    }

    public void saveDeveloper() throws SQLException {
        //TODO: get data from console
        //TODO: build Skill instance from console data
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();
        System.out.println("Please enter first name");
        String firstName = scanner2.next();
        System.out.println("Please enter last name");
        String lastName = scanner2.next();
        System.out.println("Please enter specialty");
        String specialty = scanner2.next();
        System.out.println("Please enter Account id");
        Long accountId = scanner2.nextLong();

        boolean repeat = true;
        ArrayList<Integer> list = new ArrayList<>();
        while (repeat){
            System.out.println("Please enter Skill id");
            Long skillId = scanner2.nextLong();
            list.add(skillId.intValue());

            System.out.println("Do you want to enter more skill ? 1 - yes, 2- no");
            Scanner scanner3 = new Scanner(System.in);
            int repeatInt = scanner3.nextInt();
            if (repeatInt != 1){
                repeat = false;
            }
        }

        AccountController accountController = new AccountController();
        Account account = accountController.getAccountById(accountId);

        Set<Skill> skills = new HashSet<>();
        SkillController skillController = new SkillController();
        for (int x:list) {
            Skill skill = skillController.getSkillById((long) x);
            if (skill.getId() != null){
                skills.add(skill);
            }
        }

        DeveloperBuilder developerBuilder = new DeveloperBuilder();
        developerBuilder.withId(id).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).withAccount(account).withSkill(skills);
        developerController.saveDeveloper(developerBuilder.toDeveloper());
    }

    public void getDeveloperById() throws SQLException {
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

    public void deleteById() throws SQLException{
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for delete:");
        Long id = scanner2.nextLong();

        developerController.deleteById(id);
    }

    public void getAllDeveloper() throws SQLException {
        developerController.getAll();
    }
}
