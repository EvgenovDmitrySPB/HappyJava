package net.proselyte.crud.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectorMySQL {
    private static final String PATH = "src\\main\\resources\\liquibase\\liquibase.properties";
    private static ConnectorMySQL INSTANCE = null;
    private static Connection CONNECTION = getMySqlJDBCConnection();

    public static ConnectorMySQL getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ConnectorMySQL();
        }
        return INSTANCE;
    }

    //класс создан по паттерну Singleton и конструктор не нужен
//    public ConnectorMySQL() {
//        this.CONNECTION = getMySqlConnection();
//        //printConnectInfo();
//    }

    public Connection getConnection(){
        if (CONNECTION != null){
            return CONNECTION;
        }else{
            return null;
        }
    }

    private static Connection getMySqlJDBCConnection() {
        Connection connectMySql = null;
        try {
            FileInputStream fis = new FileInputStream(PATH);
            Properties prop = new Properties();
            prop.load(fis);

            try {
                connectMySql = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
                System.out.println("Connected to the mySQL on [JDBC] ...");
            }catch(SQLException e){
                System.out.println("SQLException");
            }

        }catch(IOException e){
            System.out.println("IOException");
        }
        return connectMySql;
    }
    private void printConnectInfo() {
        if (CONNECTION != null){
            try {
                System.out.println("*************  Connection has done. Properties *************");
                System.out.println("DB name: " + CONNECTION.getMetaData().getDatabaseProductName());
                System.out.println("DB version: " + CONNECTION.getMetaData().getDatabaseProductVersion());
                System.out.println("Driver: " + CONNECTION.getMetaData().getDriverName());
                System.out.println("Autocommit: " + CONNECTION.getAutoCommit());
                System.out.println("************************************************************");
            } catch (SQLException e) {
                System.out.println("SQLException");
            }
        }else{
            System.out.println("Not have information");
        }
    }


}