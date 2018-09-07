import net.proselyte.crud.model.Skill;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.view.AccountView;
import net.proselyte.crud.view.DeveloperView;
import net.proselyte.crud.view.SkillView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        //New comment
        //New comment 1 edit
        SkillView skillView = new SkillView();
        skillView.createTable();
        AccountView accountView = new AccountView();
        accountView.createTable();
        DeveloperView developerView = new DeveloperView();
        developerView.createTable();

        Scanner scannerSkill     = new Scanner(System.in);
        Scanner scannerAccount   = new Scanner(System.in);
        Scanner scannerDeveloper = new Scanner(System.in);

        Scanner scannerAll = new Scanner(System.in);

        boolean repeatAll = true;
        while(repeatAll){
            System.out.println("Enter number TABLE. 1 - Skill; 2 - Account; 3 - Developer ");
            int resultAll = scannerAll.nextInt();
            if (resultAll == 1) {
                // SKILLS
                System.out.println("Enter number operation. 1 - save Skill; 2 - get Skill by id; 3 - delete Account by id; 4 - GET all Skills ");
                int resultScanner = scannerSkill.nextInt();

                if (resultScanner == 1) {
                    skillView.saveSkill();
                } else if (resultScanner == 2) {
                    skillView.getSkillById();
                } else if (resultScanner == 3) {
                    skillView.deleteById();
                } else if (resultScanner == 4) {
                    skillView.getAllSkill();
                }
            }
            if (resultAll == 2) {
                //Accounts
                System.out.println("Enter number operation. 1 - save Account; 2 - get Account by id; 3 - delete Account by id; 4 - GET all Accounts ");
                int resultAccount = scannerAccount.nextInt();

                if (resultAccount == 1) {
                    accountView.saveAccount();
                } else if (resultAccount == 2) {
                    accountView.getSkillById();
                } else if (resultAccount == 3) {
                    accountView.deleteById();
                } else if (resultAccount == 4) {
                    accountView.getAllAccount();
                }
            }
            if (resultAll == 3) {
                //Developers
                System.out.println("Enter number operation. 1 - save Developer; 2 - get Developer by id; 3 - delete Developer by id; 4 - GET all Developer ");
                int resultDeveloper = scannerDeveloper.nextInt();

                if (resultDeveloper == 1) {
                    developerView.saveDeveloper();
                } else if (resultDeveloper == 2) {
                    developerView.getDeveloperById();
                } else if (resultDeveloper == 3) {
                    developerView.deleteById();
                } else if (resultDeveloper == 4) {
                    developerView.getAllDeveloper();
                }
                System.out.println("Do you want to repeat ? 1 - yes, 2- no");
                Scanner scanner3 = new Scanner(System.in);
                int repeatInt = scanner3.nextInt();
                if (repeatInt != 1) {
                    repeatAll = false;
                }
            }

         }
    }
}
