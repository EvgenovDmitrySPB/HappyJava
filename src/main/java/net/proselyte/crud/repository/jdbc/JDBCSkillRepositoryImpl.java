package net.proselyte.crud.repository.jdbc;

import net.proselyte.crud.builders.SkillBuilder;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.util.ConnectorMySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSkillRepositoryImpl implements SkillRepository {
    private Connection connection;
    private Statement statement;

    public JDBCSkillRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public void save(Skill skill) throws SQLException {
        try {
            statement = connection.createStatement();
            String getSql = "INSERT INTO SKILLS VALUES(" + skill.getId().intValue() + ",'" + skill.getName() + "')";
            statement.executeUpdate(getSql);
            System.out.println("Operation save SKILLS. Ok");
        }catch (SQLException e){
            System.out.println("Operation save SKILLS. SQLException");
        }finally{
            if (statement != null){
                statement.close();
            }
        }
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        try {
            statement = connection.createStatement();
            String getSql = "DELETE FROM skills WHERE id = " + aLong.intValue();
            statement.executeUpdate(getSql);
            System.out.println("Operation delete SKILLS. Ok");
        }catch (SQLException e){
            System.out.println("Operation delete SKILLS. SQLException");
        }finally{
            if (statement != null){
                statement.close();
            }
        }
    }

    @Override
    public Skill getById(Long aLong) throws SQLException {
        try {
            statement = connection.createStatement();
            String getSql = "SELECT id,name FROM SKILLS WHERE id=" + aLong.intValue();
            ResultSet result = statement.executeQuery(getSql);
            SkillBuilder skillBuilder = new SkillBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String name = result.getString(2);
                skillBuilder.withId(id).withName(name);
            }
            Skill skill = skillBuilder.toSkill();
            return skill;
        } catch (SQLException e) {
            System.out.println("Operation getById SKILLS. SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return null;
    }

    @Override
    public void getAll() throws SQLException {
        int temp =0;
        try {
            statement = connection.createStatement();
            String getSql = "SELECT id,name FROM SKILLS";
            ResultSet result = statement.executeQuery(getSql);
            SkillBuilder skillBuilder = new SkillBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String name = result.getString(2);
                skillBuilder.withId(id).withName(name);
                Skill skill = skillBuilder.toSkill();
                System.out.println(skill.toString());
            }
            if (temp ==0){
                System.out.println("0 element's in SKILLS");
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll ACCOUNTS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
