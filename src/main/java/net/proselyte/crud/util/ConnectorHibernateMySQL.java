package net.proselyte.crud.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectorHibernateMySQL {
    private static ConnectorHibernateMySQL INSTANCE = null;
    private SessionFactory sessionFactory = createSessionFactory();

    public static ConnectorHibernateMySQL getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ConnectorHibernateMySQL();
        }
        return INSTANCE;
    }

    public SessionFactory getSessionFactory(){
        if (sessionFactory != null){
            return sessionFactory;
        }else{
            return null;
        }
    }

    //Конфигурирование hibernate для подключения к mySQL
    @SuppressWarnings("UnusedDeclaration")
    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        System.out.println("You have SessionFactory from [Hibernate] ...");

        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("*********** Connection info *******************");
            System.out.println("* DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("* DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("* Driver: " + connection.getMetaData().getDriverName());
            System.out.println("* Autocommit: " + connection.getAutoCommit());
            System.out.println("*********** Connection info *******************");
        }catch (SQLException e){
            System.out.println("sql exception");
        }

        return sessionFactory;
    }

    public void closeSessionFactory() {
        try {
            getSessionFactory().close();
            System.out.println("Session factory closed");
        }catch (HibernateException e){
            System.out.println("HibernateException for try to close session factory");
        }
    }

}
