package net.proselyte.crud.controller;

import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.repository.jdbc.JDBCSkillRepositoryImpl;

import java.sql.SQLException;

public class SkillController {

    private SkillRepository skillRepository;

    public SkillController() throws SQLException {
        skillRepository = new JDBCSkillRepositoryImpl();
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
