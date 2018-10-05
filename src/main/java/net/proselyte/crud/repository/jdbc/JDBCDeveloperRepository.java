package net.proselyte.crud.repository.jdbc;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.DeveloperRepository;
import net.proselyte.crud.repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCDeveloperRepository implements DeveloperRepository {
    private Connection connection;
    private Statement statement;
    private AccountRepository accountRepository ;
    private SkillRepository skillRepository;

    public JDBCDeveloperRepository(Connection connection){
        this.connection = connection;
        this.accountRepository = new JDBCAccountRepository(connection);
        this.skillRepository   = new JDBCSkillRepositoryImpl(connection);
    }

    @Override
    public void save(Developer developer) {
        String generatedColumns[] = { "developersid" };
        String getSql = "INSERT INTO developers(firstName, lastName, specialty, account) VALUES (?,?,?,?)";
        int idDeveloper = 0;
        try(PreparedStatement statement = connection.prepareStatement(getSql, Statement.RETURN_GENERATED_KEYS);){

            statement.setString(1,developer.getFirstName());
            statement.setString(2,developer.getLastName());
            statement.setString(3,developer.getSpecialty());
            statement.setLong(4,developer.getAccount().getId());

            int affectedRows = statement.executeUpdate();

            //получаем id созданной записи в БД
            if (affectedRows != 0){
                try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        idDeveloper = generatedKeys.getInt(1);
                    }
                }
            }

            System.out.println("Operation save DEVELOPERS. Ok");
            for (Skill idSkill:developer.getSkills()) {
                String getSqlSkill = "INSERT INTO developer_skills VALUES(" + idDeveloper + "," + idSkill.getId().intValue() + ")";
                statement.executeUpdate(getSqlSkill);
            }
        }catch (SQLException e){
            System.out.println("Operation save DEVELOPERS. SQLException");
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long aLong) {
        Developer developer = null;
        Account account = null;
        Skill skill     = null;

        try (Statement statement = connection.createStatement()){

            String getDev = "SELECT\n" +
                    "developers.id,\n" +
                    "developers.firstName,\n" +
                    "developers.lastName,\n" +
                    "developers.specialty,\n" +
                    "developers.account,\n" +
                    "developer_skills.idSkill,\n" +
                    "ISNULL(developer_skills.idSkill) AS flagSkillNull\n" +
                    "FROM DEVELOPERS\n" +
                    "LEFT JOIN developer_skills\n" +
                    "  ON id =developer_skills.idDeveloper\n" +
                    " WHERE ID = " + aLong.intValue()+
                    " ORDER BY developers.id,developer_skills.idSkill";

            ResultSet resultDev = statement.executeQuery(getDev);

            DeveloperBuilder developerBuilder = new DeveloperBuilder();

            Set<Skill> skills = new HashSet<>();
            long currentIdDev = 0;

            while (resultDev.next()) {
                long idDeveloper = resultDev.getLong(1);
                String firstName = resultDev.getString(2);
                String lastName  = resultDev.getString(3);
                String specialty = resultDev.getString(4);
                int idAccount    = resultDev.getInt(5);

                account = accountRepository.getById((long)idAccount);

                //в БД не работает правильно ISNULL
                //ISNULL(null) выдает 1, ISNULL('что-то') дает 0
                //ISNULL(value, 0) - не отрабатывает с 2-мя аргументами
                int flagSkillNull = resultDev.getInt(7);
                int idSkill = 0;
                if (flagSkillNull == 0) {
                    idSkill = resultDev.getInt(6);

                    skill   = skillRepository.getById((long) idSkill);
                    if (skill.getId() != null){
                        skills.add(skill);
                    }
                }

                if (currentIdDev != idDeveloper){
                    developerBuilder.withId(idDeveloper).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).
                            withAccount(account);
                    if (skills.size() > 0) {
                        developerBuilder.withSkill(skills);
                    }
                    developer = developerBuilder.toDeveloper();
                    skills.clear();
                    currentIdDev = idDeveloper;
                }
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll DEVELOPERS . SQLException");
        }
        return developer;
    }

    @Override
    public void deleteById(Long aLong) {
        try (Statement statement = connection.createStatement()){
            String getSql = "DELETE FROM developers WHERE id = " + aLong.intValue();
            statement.executeUpdate(getSql);
            System.out.println("Operation delete DEVELOPERS. Ok");
            String getSqlSkill = "DELETE FROM developer_skills WHERE idDeveloper = " + aLong.intValue();
            System.out.println("Operation delete developer_skills. Ok");
            statement.executeUpdate(getSqlSkill);
        }catch (SQLException e){
            System.out.println("Operation delete DEVELOPERS. SQLException");
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()){

            String getDev = "SELECT\n" +
                    "developers.id,\n" +
                    "developers.firstName,\n" +
                    "developers.lastName,\n" +
                    "developers.specialty,\n" +
                    "developers.account,\n" +
                    "developer_skills.idSkill,\n" +
                    "ISNULL(developer_skills.idSkill) AS flagSkillNull\n" +
                    "FROM DEVELOPERS\n" +
                    "LEFT JOIN developer_skills\n" +
                    " ON id =developer_skills.idDeveloper\n" +
                    "ORDER BY developers.id, developer_skills.idSkill";

            ResultSet resultDev = statement.executeQuery(getDev);

            DeveloperBuilder developerBuilder = new DeveloperBuilder();
            Developer developer = null;
            Account account     = null;
            Skill skill         = null;
            Set<Skill> skills = new HashSet<>();

            while (resultDev.next()) {
                long idDeveloper = resultDev.getLong(1);
                String firstName = resultDev.getString(2);
                String lastName  = resultDev.getString(3);
                String specialty = resultDev.getString(4);
                int idAccount    = resultDev.getInt(5);

                account = accountRepository.getById((long)idAccount);

                //в БД не работает правильно ISNULL
                //ISNULL(null) выдает 1, ISNULL('что-то') дает 0
                //ISNULL(value, 0) - не отрабатывает с 2-мя аргументами
                int flagSkillNull = resultDev.getInt(7);
                int idSkill = 0;
                if (flagSkillNull == 0) {
                    idSkill = resultDev.getInt(6);

                    skill   = skillRepository.getById((long) idSkill);
                    if (skill.getId() != null){
                        skills.add(skill);
                    }
                }

                    developerBuilder.withId(idDeveloper).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).
                    withAccount(account);
                    if (skills.size() > 0) {
                        developerBuilder.withSkill(skills);
                    }
                    developer = developerBuilder.toDeveloper();
                    if (!list.contains(developer)){
                        list.add(developer);
                    }
                    list.add(developer);
                    skills.clear();
                    currentIdDev = developer.getId();

            }
            if (list.size() ==0){
                System.out.println("0 element's in DEVELOPERS ");
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll DEVELOPERS . SQLException");
        }
        return list;
    }

    @Override
    public void update(Developer developer) {
        try (Statement statement = connection.createStatement()){
            String getSql = "UPDATE developers set firstName = '" + developer.getFirstName() + "' WHERE id=" + developer.getId();
            statement.executeUpdate(getSql);
            System.out.println("Operation update DEVELOPER.");
        } catch (SQLException e) {
            System.out.println("Operation update DEVELOPER. SQLException");
        }

    }
}
