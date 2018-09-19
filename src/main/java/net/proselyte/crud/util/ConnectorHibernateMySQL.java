package net.proselyte.crud.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
        System.out.println("Connected to the mySQL on [Hibernate] ...");

        return sessionFactory;
    }

}
