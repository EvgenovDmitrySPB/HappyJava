package net.proselyte.crud.controller;

import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.repository.hibernate.HibernateSkillRepository;
import net.proselyte.crud.repository.jdbc.JDBCSkillRepositoryImpl;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import java.sql.Connection;

public class SkillController {

    private SkillRepository skillRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public SkillController()  {
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                skillRepository = new JDBCSkillRepositoryImpl(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                skillRepository = new HibernateSkillRepository(sessionFactory);
            }
        }
    }

    public void saveSkill(Skill skill) {
        if(skill == null){
            throw new IllegalArgumentException();
        }
        skillRepository.save(skill);
    }

    public Skill getSkillById(Long id)  {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        return skillRepository.getById(id);
    }

    public void deleteById(Long id) {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        skillRepository.deleteById(id);
    }

    public void getAll() {
        skillRepository.getAll();
    }

}
