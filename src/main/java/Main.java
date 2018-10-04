import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import net.proselyte.crud.view.AccountView;
import net.proselyte.crud.view.DeveloperView;
import net.proselyte.crud.view.SkillView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scannerTypeConnect = new Scanner(System.in);
        Scanner scannerSkill       = new Scanner(System.in);
        Scanner scannerAccount     = new Scanner(System.in);
        Scanner scannerDeveloper   = new Scanner(System.in);
        Scanner scannerAll         = new Scanner(System.in);
        Scanner scannerRepeat      = new Scanner(System.in);

        //Выбор типа соединения с БД
        boolean repeatTypeConnect = true;
        while (repeatTypeConnect){
            scannerTypeConnect= new Scanner(System.in);
            System.out.println("Enter system for connecting: 1 - JDBC; 2 - Hibernate ");
            int resultJorH = scannerTypeConnect.nextInt();

            //создаем класс SelectConnection[Singleton], который хранит глобальную переменную о типе соединения с БД,
            // чтобы не прокидывать её по всем классам
            if (resultJorH == 1){
                SelectConnection.getInstance().setConnectType(ConnectType.JDBC);
                repeatTypeConnect = false;
            }else if (resultJorH == 2){
                SelectConnection.getInstance().setConnectType(ConnectType.HIBERNATE);
                repeatTypeConnect = false;
            }
        }

        SkillView skillView         = new SkillView();
        AccountView accountView     = new AccountView();
        DeveloperView developerView = new DeveloperView();

        scannerAll = new Scanner(System.in);

        boolean repeatAll = true;
        while(repeatAll){
            System.out.println("Enter number TABLE. 1 - Skill; 2 - Account; 3 - Developer ");
            int resultAll = scannerAll.nextInt();
            if (resultAll == 1) {
                // SKILLS
                System.out.println("Enter number operation. 1 - save Skill; 2 - get Skill by id; 3 - delete Account by id; 4 - GET all Skills; 5 - Update skill ");
                int resultScanner = scannerSkill.nextInt();

                if (resultScanner == 1) {
                    skillView.saveSkill();
                } else if (resultScanner == 2) {
                    skillView.getSkillById();
                } else if (resultScanner == 3) {
                    skillView.deleteById();
                } else if (resultScanner == 4) {
                    skillView.getAllSkill();
                }else if (resultScanner == 5) {
                    skillView.updateSkill();
                }
                System.out.println("Do you want to repeat ? 1 - yes, 2- no");
                scannerRepeat = new Scanner(System.in);
                if (scannerRepeat.nextInt() != 1) {
                    repeatAll = false;
                    if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
                        ConnectorMySQL.getInstance().closeConnection();
                    }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
                        ConnectorHibernateMySQL.getInstance().closeSessionFactory();
                    }
                }
            }
            if (resultAll == 2) {
                //Accounts
                System.out.println("Enter number operation. 1 - save Account; 2 - get Account by id; 3 - delete Account by id; 4 - GET all Accounts; 5 - Update Account ");
                int resultAccount = scannerAccount.nextInt();

                if (resultAccount == 1) {
                    accountView.saveAccount();
                } else if (resultAccount == 2) {
                    accountView.getSkillById();
                } else if (resultAccount == 3) {
                    accountView.deleteById();
                } else if (resultAccount == 4) {
                    accountView.getAllAccount();
                }else if (resultAccount == 5) {
                    accountView.updateAccount();
                }
                System.out.println("Do you want to repeat ? 1 - yes, 2- no");
                scannerRepeat = new Scanner(System.in);
                if (scannerRepeat.nextInt() != 1) {
                    repeatAll = false;
                    if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
                        ConnectorMySQL.getInstance().closeConnection();
                    }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
                        ConnectorHibernateMySQL.getInstance().closeSessionFactory();
                    }
                }
            }
            if (resultAll == 3) {
                //Developers
                System.out.println("Enter number operation. 1 - save Developer; 2 - get Developer by id; 3 - delete Developer by id; 4 - GET all Developer; 5 - Update Developer ");
                int resultDeveloper = scannerDeveloper.nextInt();

                if (resultDeveloper == 1) {
                    developerView.saveDeveloper();
                } else if (resultDeveloper == 2) {
                    developerView.getDeveloperById();
                } else if (resultDeveloper == 3) {
                    developerView.deleteById();
                } else if (resultDeveloper == 4) {
                    developerView.getAllDeveloper();
                }else if (resultDeveloper == 5) {
                    developerView.updateDeveloper();
                }
                System.out.println("Do you want to repeat ? 1 - yes, 2- no");
                scannerRepeat = new Scanner(System.in);
                if (scannerRepeat.nextInt() != 1) {
                    repeatAll = false;
                    if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
                        ConnectorMySQL.getInstance().closeConnection();
                    }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
                        ConnectorHibernateMySQL.getInstance().closeSessionFactory();
                    }
                }
            }

         }
    }
}
