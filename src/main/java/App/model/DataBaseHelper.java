package App.model;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DataBaseHelper {
    public static String URL;
    public static String LOGIN;
    public static String PASSWORD;
    public final static String SQL_DROP_TABLE = "DROP TABLE IF EXISTS users";
    public final static String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users( id int AUTO_INCREMENT primary key, name varchar(256), age int, email varchar(256))";

    private final static String PATH = "mysql.properties";

    static {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(new File(PATH))){
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL = prop.getProperty("url");
        LOGIN = prop.getProperty("db_user");
        PASSWORD = prop.getProperty("db_password");
    }

    public  static  void init() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute(SQL_DROP_TABLE);
        statement.execute(SQL_CREATE_TABLE);
        }
}