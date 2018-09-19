package net.proselyte.crud.controller;

import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.repository.DeveloperRepository;
import net.proselyte.crud.repository.hibernate.HibernateDeveloperRepository;
import net.proselyte.crud.repository.jdbc.JDBCDeveloperRepository;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DeveloperController {
    private DeveloperRepository developerRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public DeveloperController() throws SQLException {
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                developerRepository = new JDBCDeveloperRepository(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                developerRepository = new HibernateDeveloperRepository(sessionFactory);
            }
        }
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
