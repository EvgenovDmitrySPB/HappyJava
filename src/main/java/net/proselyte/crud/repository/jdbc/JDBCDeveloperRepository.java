package net.proselyte.crud.repository.jdbc;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.controller.AccountController;
import net.proselyte.crud.controller.SkillController;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.DeveloperRepository;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JDBCDeveloperRepository implements DeveloperRepository {
    private Connection connection;
    private Statement statement;

    public JDBCDeveloperRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Developer developer) throws SQLException {
        try {
            statement = connection.createStatement();
            String getSql = "INSERT INTO developers VALUES(" + developer.getId().intValue() + ",'" + developer.getFirstName() +
                    "','" + developer.getLastName() + "','" + developer.getSpecialty() + "','" + developer.getAccount().getId().intValue() + "')";
            statement.executeUpdate(getSql);
            System.out.println("Operation save DEVELOPERS. Ok");
            for (Skill idSkill:developer.getSkills()) {
                String getSqlSkill = "INSERT INTO developer_skills VALUES(" + developer.getId().intValue() + "," + idSkill.getId().intValue() + ")";
                statement.executeUpdate(getSqlSkill);
            }
        }catch (SQLException e){
            System.out.println("Operation save DEVELOPERS. SQLException");
            e.printStackTrace();
        }finally{
            if (statement != null){
                statement.close();
            }
        }
    }

    @Override
    public Developer getById(Long aLong) throws SQLException {
        Developer developer = null;
        Account account = null;
        Skill skill = null;

        try {
            statement = connection.createStatement();

            String getDev = "SELECT\n" +
                    "developers.id,\n" +
                    "developers.firstName,\n" +
                    "developers.lastName,\n" +
                    "developers.specialty,\n" +
                    "developers.account,\n" +
                    "developer_skills.idSkill\n" +
                    "FROM DEVELOPERS\n" +
                    "LEFT JOIN developer_skills\n" +
                    "  ON id =developer_skills.idDeveloper\n" +
                    "WHERE developers.id =" + aLong.intValue() +
                    " ORDER BY developers.id,developer_skills.idSkill";

            ResultSet resultDev = statement.executeQuery(getDev);

            DeveloperBuilder developerBuilder = new DeveloperBuilder();
            AccountController accountController = new AccountController();

            Set<Skill> skills = new HashSet<>();
            SkillController skillController = new SkillController();
            long currentIdDev = 0;

            while (resultDev.next()) {
                long idDeveloper = resultDev.getLong(1);
                String firstName = resultDev.getString(2);
                String lastName  = resultDev.getString(3);
                String specialty = resultDev.getString(4);
                int idAccount    = resultDev.getInt(5);
                int idSkill = resultDev.getInt(6);

                account = accountController.getAccountById((long)idAccount);
                skill   = skillController.getSkillById((long) idSkill);
                if (skill.getId() != null){
                    skills.add(skill);
                }

                if (currentIdDev != idDeveloper){
                    developerBuilder.withId(idDeveloper).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).
                            withAccount(account).withSkill(skills);
                    developer = developerBuilder.toDeveloper();
                    skills.clear();
                    currentIdDev = idDeveloper;
                }
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll DEVELOPERS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return developer;
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        try {
            statement = connection.createStatement();
            String getSql = "DELETE FROM developers WHERE id = " + aLong.intValue();
            statement.executeUpdate(getSql);
            System.out.println("Operation delete DEVELOPERS. Ok");
            String getSqlSkill = "DELETE FROM developer_skills WHERE idDeveloper = " + aLong.intValue();
            System.out.println("Operation delete developer_skills. Ok");
            statement.executeUpdate(getSqlSkill);
        }catch (SQLException e){
            System.out.println("Operation delete DEVELOPERS. SQLException");
        }finally{
            if (statement != null){
                statement.close();
            }
        }
    }

    @Override
    public void getAll() throws SQLException {
        int temp = 0;
        try {
            statement = connection.createStatement();

            String getDev = "SELECT\n" +
                    "developers.id,\n" +
                    "developers.firstName,\n" +
                    "developers.lastName,\n" +
                    "developers.specialty,\n" +
                    "developers.account,\n" +
                    "developer_skills.idSkill\n" +
                    "FROM DEVELOPERS\n" +
                    "LEFT JOIN developer_skills\n" +
                    "  ON id =developer_skills.idDeveloper\n" +
                    "ORDER BY developers.id,developer_skills.idSkill";

            ResultSet resultDev = statement.executeQuery(getDev);

            DeveloperBuilder developerBuilder = new DeveloperBuilder();
            Developer developer = null;
            AccountController accountController = new AccountController();
            Account account = null;

            Set<Skill> skills = new HashSet<>();
            SkillController skillController = new SkillController();
            Skill skill = null;
            long currentIdDev = 0;

            while (resultDev.next()) {
                long idDeveloper = resultDev.getLong(1);
                String firstName = resultDev.getString(2);
                String lastName  = resultDev.getString(3);
                String specialty = resultDev.getString(4);
                int idAccount    = resultDev.getInt(5);
                int idSkill = resultDev.getInt(6);

                account = accountController.getAccountById((long)idAccount);
                skill   = skillController.getSkillById((long) idSkill);
                if (skill.getId() != null){
                    skills.add(skill);
                }

                if (currentIdDev != idDeveloper){
                    developerBuilder.withId(idDeveloper).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).
                            withAccount(account).withSkill(skills);
                    developer = developerBuilder.toDeveloper();
                    System.out.println(developer.toString());
                    skills.clear();
                    temp++;
                    currentIdDev = idDeveloper;
                }
            }
            if (temp ==0){
                System.out.println("0 element's in DEVELOPERS ");
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll DEVELOPERS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void update(Long aLong) throws SQLException {

    }
}
