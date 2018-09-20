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
        Connection connection = null;
        try {
            FileInputStream fis = new FileInputStream(PATH);
            Properties prop = new Properties();
            prop.load(fis);

            try {
                connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
                System.out.println("Connected to the mySQL on [JDBC] ...");
                printConnectInfo(connection);
            }catch(SQLException e){
                System.out.println("SQLException");
            }

        }catch(IOException e){
            System.out.println("IOException");
        }
        return connection;
    }
    private static void printConnectInfo(Connection connection) {
        if (connection != null){
            try {
                System.out.println("*************  Connection has done. Properties *************");
                System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
                System.out.println("Driver: " + connection.getMetaData().getDriverName());
                System.out.println("Autocommit: " + connection.getAutoCommit());
                System.out.println("************************************************************");
            } catch (SQLException e) {
                System.out.println("SQLException");
            }
        }else{
            System.out.println("Not have information");
        }
    }


}