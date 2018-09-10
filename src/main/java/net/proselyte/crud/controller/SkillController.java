package net.proselyte.crud.controller;

import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.repository.jdbc.JDBCSkillRepositoryImpl;
import net.proselyte.crud.util.ConnectorMySQL;

import java.sql.Connection;
import java.sql.SQLException;

public class SkillController {

    private SkillRepository skillRepository;
    private Connection connection;

    public SkillController() throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        if (this.connection == null){
            System.out.println("Warning! You don't have connection with MySQL");
            return;
        }else {
            skillRepository = new JDBCSkillRepositoryImpl(connection);
        }
    }

    public void createTable() throws SQLException {
        skillRepository.createTable();
    }

    public void saveSkill(Skill skill) throws SQLException {
        if(skill == null){
            throw new IllegalArgumentException();
        }
        skillRepository.save(skill);
    }

    public Skill getSkillById(Long id) throws SQLException {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        return skillRepository.getById(id);
    }

    public void deleteById(Long id) throws SQLException {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        skillRepository.deleteById(id);
    }

    public void getAll() throws SQLException {
        skillRepository.getAll();
    }

}
