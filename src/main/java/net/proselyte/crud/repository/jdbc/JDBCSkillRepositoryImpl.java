package net.proselyte.crud.repository.jdbc;

import net.proselyte.crud.builders.SkillBuilder;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCSkillRepositoryImpl implements SkillRepository {
    private Connection connection;
    private Statement statement;

    public JDBCSkillRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Skill skill) {
        try (Statement statement = connection.createStatement()){
            String getSql = "INSERT INTO SKILLS(name) VALUES('" +  skill.getName() + "')";
            statement.executeUpdate(getSql);
            System.out.println("Operation save SKILLS. Ok");
        }catch (SQLException e){
            System.out.println("Operation save SKILLS. SQLException");
        }
    }

    @Override
    public void deleteById(Long aLong) {
        try (Statement statement = connection.createStatement()){
            String getSql = "DELETE FROM skills WHERE id = " + aLong.intValue();
            statement.executeUpdate(getSql);
            System.out.println("Operation delete SKILLS. Ok");
        }catch (SQLException e){
            System.out.println("Operation delete SKILLS. SQLException");
        }
    }

    @Override
    public Skill getById(Long aLong){
        try (Statement statement = connection.createStatement()){
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
        }
        return null;
    }

    @Override
    public List<Skill> getAll(){
        List<Skill> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            String getSql = "SELECT id,name FROM SKILLS";
            ResultSet result = statement.executeQuery(getSql);
            SkillBuilder skillBuilder = new SkillBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String name = result.getString(2);
                skillBuilder.withId(id).withName(name);
                Skill skill = skillBuilder.toSkill();
                list.add(skill);
            }
            if (list.size() ==0){
                System.out.println("0 element's in SKILLS");
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll ACCOUNTS . SQLException");
        }
        return list;
    }

    @Override
    public void update(Skill skill) {
        try (Statement statement = connection.createStatement()){
            String getSql = "UPDATE skills set name = '" + skill.getName() + "' WHERE id=" + skill.getId();
            statement.executeUpdate(getSql);
            System.out.println("Operation update SKILLS.");
        } catch (SQLException e) {
            System.out.println("Operation update SKILLS. SQLException");
        }
    }
}
