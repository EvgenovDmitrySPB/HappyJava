package net.proselyte.crud.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectorHibernateMySQL {
    private static final String PATH = "src\\main\\resources\\liquibase\\liquibase.properties";
    private static ConnectorHibernateMySQL INSTANCE = null;
    private static Configuration configuration = getMySqlHibernateConfiguration();
    private static SessionFactory sessionFactory = getMySqlHibernateConnection(configuration);

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
    public static Configuration getMySqlHibernateConfiguration() {
        configuration = new Configuration();


        return configuration;
    }


    private static SessionFactory getMySqlHibernateConnection(Configuration configuration) {
        return sessionFactory;
    }


}
