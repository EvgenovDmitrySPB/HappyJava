package net.proselyte.crud.repository.jdbc;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.controller.AccountController;
import net.proselyte.crud.controller.SkillController;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.DeveloperRepository;
import net.proselyte.crud.util.ConnectorMySQL;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JDBCDeveloperRepository implements DeveloperRepository {
    private Connection connection;

    @Override
    public void save(Developer developer) throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String getSql = "INSERT INTO developers VALUES(" + developer.getId().intValue() + ",'" + developer.getFirstName() +
                    "','" + developer.getLastName() + "','" + developer.getSpecialty() + "','" + developer.getAccount().getId().intValue() +
                    "','" + developer.getSkills() + "')";
            statement.executeUpdate(getSql);
            System.out.println("Operation save DEVELOPERS. Ok");
        }catch (SQLException e){
            System.out.println("Operation save DEVELOPERS. SQLException");
        }finally{
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public Developer getById(Long aLong) throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        Statement statement = null;
        int temp = 0;
        try {
            statement = connection.createStatement();

            String getSql = "SELECT id,firstName,lastName,specialty,account,skills FROM DEVELOPERS WHERE id=" + aLong.intValue();
            ResultSet result = statement.executeQuery(getSql);
            DeveloperBuilder developerBuilder = new DeveloperBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String firstName = result.getString(2);
                String lastName  = result.getString(3);
                String specialty = result.getString(4);
                int idAccount   = result.getInt(5);

                AccountController accountController = new AccountController();
                Account account = accountController.getAccountById((long)idAccount);

                String skillString = result.getString(6);
                byte[] buffer = skillString.getBytes();
                ByteArrayInputStream byteArray = new ByteArrayInputStream(buffer);
                int c;
                String tempId = "";
                ArrayList<Integer> list = new ArrayList<>();
                while((c = byteArray.read()) != -1){
                    if ((char)c == ' ' ){
                        list.add(Integer.parseInt(tempId));
                        tempId = "";
                    }else {
                        tempId = tempId + (char)c;
                    }
                }
                if (tempId != ""){
                    list.add(Integer.parseInt(tempId));
                }

                Set<Skill> skills = new HashSet<>();
                SkillController skillController = new SkillController();
                for (int x:list) {
                    Skill skill = skillController.getSkillById((long) x);
                    if (skill.getId() != null){
                        skills.add(skill);
                    }
                }

                developerBuilder.withId(id).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).
                        withAccount(account).withSkill(skills);
                Developer developer = developerBuilder.toDeveloper();
                return developer;
            }


        } catch (SQLException e) {
            System.out.println("Operation getAll ACCOUNTS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String getSql = "DELETE FROM developers WHERE id = " + aLong.intValue();
            statement.executeUpdate(getSql);
            System.out.println("Operation delete DEVELOPERS. Ok");
        }catch (SQLException e){
            System.out.println("Operation delete DEVELOPERS. SQLException");
        }finally{
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void createTable() throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String getSql = "CREATE TABLE DEVELOPERS (id int PRIMARY KEY UNIQUE, firstName VARCHAR(100), lastName VARCHAR(100), specialty VARCHAR(100), account LONG, skills VARCHAR(100))";
            statement.executeUpdate(getSql);
            System.out.println("TABLE DEVELOPERS has created");
        }catch (SQLException e){
            System.out.println("TABLE DEVELOPERS already exists");
        } finally{
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void getAll() throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        Statement statement = null;
        int temp = 0;
        try {
            statement = connection.createStatement();

            String getSql = "SELECT id,firstName,lastName,specialty,account,skills FROM DEVELOPERS";
            ResultSet result = statement.executeQuery(getSql);
            DeveloperBuilder developerBuilder = new DeveloperBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String firstName = result.getString(2);
                String lastName  = result.getString(3);
                String specialty = result.getString(4);
                int idAccount   = result.getInt(5);

                AccountController accountController = new AccountController();
                Account account = accountController.getAccountById((long)idAccount);

                String skillString = result.getString(6);
                byte[] buffer = skillString.getBytes();
                ByteArrayInputStream byteArray = new ByteArrayInputStream(buffer);
                int c;
                String tempId = "";
                ArrayList<Integer> list = new ArrayList<>();
                while((c = byteArray.read()) != -1){
                    if ((char)c == ' ' ){
                        list.add(Integer.parseInt(tempId));
                        tempId = "";
                    }else {
                        tempId = tempId + (char)c;
                    }
                }
                if (tempId != ""){
                    list.add(Integer.parseInt(tempId));
                }

                Set<Skill> skills = new HashSet<>();
                SkillController skillController = new SkillController();
                for (int x:list) {
                    Skill skill = skillController.getSkillById((long) x);
                    if (skill.getId() != null){
                        skills.add(skill);
                    }
                }

                developerBuilder.withId(id).withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).
                        withAccount(account).withSkill(skills);
                Developer developer = developerBuilder.toDeveloper();
                System.out.println(developer.toString());
                temp++;
            }
            if (temp ==0){
                System.out.println("0 element's in DEVELOPERS ");
            }

        } catch (SQLException e) {
            System.out.println("Operation getAll ACCOUNTS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
