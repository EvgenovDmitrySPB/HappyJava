package net.proselyte.crud.controller;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.DeveloperRepository;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.repository.hibernate.HibernateAccountRepository;
import net.proselyte.crud.repository.hibernate.HibernateDeveloperRepository;
import net.proselyte.crud.repository.hibernate.HibernateSkillRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCDeveloperRepository;
import net.proselyte.crud.repository.jdbc.JDBCSkillRepositoryImpl;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeveloperController {
    private AccountRepository accountRepository;
    private SkillRepository skillRepository;
    private DeveloperRepository developerRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public DeveloperController() {
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                developerRepository = new JDBCDeveloperRepository(connection);
                accountRepository = new JDBCAccountRepository(connection);
                skillRepository = new JDBCSkillRepositoryImpl(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                developerRepository = new HibernateDeveloperRepository(sessionFactory);
                accountRepository = new HibernateAccountRepository(sessionFactory);
                skillRepository = new HibernateSkillRepository(sessionFactory);
            }
        }
    }

    public void saveDeveloper(Map<String, Object> map){
        if(map.isEmpty()){
            throw new IllegalArgumentException();
        }
        String firstName   = (String)map.get("firstName");
        String lastName    = (String)map.get("lastName");
        String specialty   = (String)map.get("specialty");
        Long accountId     = (Long) map.get("accountId");
        Set<Long> skillsId = (Set<Long>)map.get("skillsId");

        Account account = accountRepository.getById(accountId);

        Set<Skill> setSkill = new HashSet<>();
        for (Long id:skillsId) {
            Skill skill = skillRepository.getById(id);
            setSkill.add(skill);
        }

        DeveloperBuilder developerBuilder = new DeveloperBuilder();
        developerBuilder.withFirstName(firstName).withLastName(lastName).withSpecialty(specialty).withAccount(account).withSkill(setSkill);

        developerRepository.save(developerBuilder.toDeveloper());
    }

    public boolean getDeveloperById(Long id){
        if(id == 0){
            throw new IllegalArgumentException();
        }
        Developer developer = developerRepository.getById(id);
        if (developer.getId() != null){
            System.out.println(developer.toString());
            return true;
        }else {
            System.out.println("Developer not found by id = " + id);
            return false;
        }
    }

    public void deleteById(Long id){
        if(id == 0){
            throw new IllegalArgumentException();
        }
        developerRepository.deleteById(id);
    }

    public void getAll() {

        developerRepository.getAll();
        List<Developer> list = developerRepository.getAll();
        for (Developer developer:list) {
            System.out.println(developer.toString());
        }
    }

    public void updateDeveloper(Map<String, Object> map) {
        if(map.isEmpty()){
            throw new IllegalArgumentException();
        }
        Long id     = (Long)map.get("id");
        String firstName = (String)map.get("firstName");

        DeveloperBuilder skillBuilder = new DeveloperBuilder();
        skillBuilder.withId(id).withFirstName(firstName);

        developerRepository.update(skillBuilder.toDeveloper());
    }
}
