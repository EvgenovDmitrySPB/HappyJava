package net.proselyte.crud.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorMySQL {
    private final Connection connection;
    private static final String PATH = "src\\main\\resources\\application.properties";

    public ConnectorMySQL() {
        this.connection = getMySqlConnection();
        //printConnectInfo();
    }

    public Connection getConnection(){
        if (connection != null){
            return connection;
        }else{
            return null;
        }
    }

    private Connection getMySqlConnection() {
        Connection connectMySql = null;
        try {
            FileInputStream fis = new FileInputStream(PATH);

            Properties prop = new Properties();
            prop.load(fis);

            StringBuilder url = new StringBuilder();
            url.    append("jdbc:mysql://").        //db type
                    append(prop.getProperty("url")).           //host name
                    append(":" + prop.getProperty("port") + "/").                //port
                    append(prop.getProperty("dbName") + "?").            //db name
                    append("user=" + prop.getProperty("user")+ "&").           //login
                    append("password=" + prop.getProperty("password"));      //password

            try {
                connectMySql = DriverManager.getConnection(url.toString());
               // System.out.println("Connected by mySQL ...");
            }catch(SQLException e){
                System.out.println("SQLException");
            }

        }catch(IOException e){
            System.out.println("IOException");
        }
        return connectMySql;
    }
    private void printConnectInfo() {
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