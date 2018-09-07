package net.proselyte.crud.controller;

import net.proselyte.crud.model.Developer;
import net.proselyte.crud.repository.DeveloperRepository;
import net.proselyte.crud.repository.jdbc.JDBCDeveloperRepository;

import java.sql.SQLException;

public class DeveloperController {
    private DeveloperRepository developerRepository;

    public DeveloperController() throws SQLException {
        developerRepository = new JDBCDeveloperRepository();
    }

    public void createTable() throws SQLException {
        developerRepository.createTable();
    }

    public void saveDeveloper(Developer developer) throws SQLException {
        if(developer == null){
            throw new IllegalArgumentException();
        }
        developerRepository.save(developer);
    }

    public Developer getDeveloperById(Long id) throws SQLException {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        return developerRepository.getById(id);
    }

    public void deleteById(Long id) throws SQLException {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        developerRepository.deleteById(id);
    }

    public void getAll() throws SQLException {

        developerRepository.getAll();
    }
}
