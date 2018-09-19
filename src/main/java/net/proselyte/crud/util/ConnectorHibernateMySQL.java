package net.proselyte.crud.util;

import net.proselyte.crud.model.Account;

import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectorHibernateMySQL {
    private static final String PATH = "src\\main\\resources\\hibernate\\hibernate.properties";
    private static ConnectorHibernateMySQL INSTANCE = null;
    private static Configuration configuration   = getMySqlHibernateConfiguration();
    private SessionFactory sessionFactory = createSessionFactory(configuration);

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
    private static Configuration getMySqlHibernateConfiguration() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(PATH);
            Properties prop = new Properties();
            prop.load(fis);

            configuration = new Configuration();
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(Skill.class);
            configuration.addAnnotatedClass(Developer.class);

            configuration.setProperty("hibernate.dialect", prop.getProperty("dialect"));
            configuration.setProperty("hibernate.connection.driver_class", prop.getProperty("driver_class"));
            configuration.setProperty("hibernate.connection.url", prop.getProperty("url"));
            configuration.setProperty("hibernate.connection.username", prop.getProperty("username"));
            configuration.setProperty("hibernate.connection.password", prop.getProperty("password"));
            configuration.setProperty("hibernate.show_sql", prop.getProperty("hibernate_show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("hibernate_hbm2ddl_auto"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }


    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
