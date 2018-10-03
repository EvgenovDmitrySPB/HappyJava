package net.proselyte.crud.view;

import net.proselyte.crud.controller.AccountController;
import net.proselyte.crud.controller.DeveloperController;
import net.proselyte.crud.controller.SkillController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DeveloperView {
    private DeveloperController developerController;

    public DeveloperView(){
        developerController = new DeveloperController();
    }

    public void saveDeveloper(){
        //TODO: get data from console
        //TODO: build Skill instance from console data
        Map<String, Object> map = new HashMap<>();

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter first name");
        String firstName = scanner2.next();
        map.put("firstName",firstName);
        System.out.println("Please enter last name");
        String lastName = scanner2.next();
        map.put("lastName",lastName);
        System.out.println("Please enter specialty");
        String specialty = scanner2.next();
        map.put("specialty",specialty);

        boolean repeatAccount = true;
        AccountController accountController = new AccountController();
        SkillController skillController     = new SkillController();

        while (repeatAccount){
            System.out.println("Please enter Account id");
            Long accountId = scanner2.nextLong();
            boolean findedAccount = accountController.getAccountById(accountId);
            if (!findedAccount){
                System.out.println("Account with id=" + accountId + " not exist!");
            }else {
                map.put("accountId",accountId);
            }
            System.out.println("Do you want to enter other account ? 1 - yes, 2- no");
            Scanner scanner3 = new Scanner(System.in);
            if (scanner3.nextInt() != 1){
                repeatAccount = false;
            }
        }

        boolean repeatSkill = true;
        Set<Long> skillsId = new HashSet<>();

        while (repeatSkill){
            System.out.println("Please enter Skill id");
            Long skillId = scanner2.nextLong();
            boolean findedSkill = skillController.getSkillById((long) skillId.intValue());
            if (!findedSkill){
                System.out.println("Skill with id=" + skillId + " not exist!");
            }else{
                skillsId.add(skillId);
            }
            System.out.println("Do you want to enter more/other  skill ? 1 - yes, 2- no");
            Scanner scanner4 = new Scanner(System.in);
            if (scanner4.nextInt() != 1){
                repeatSkill = false;
            }
        }
        map.put("skillsId",skillsId);

        developerController.saveDeveloper(map);
    }

    public void getDeveloperById() {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();

        developerController.getDeveloperById(id);
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

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);

        developerController.updateDeveloper(map);

    }
}
