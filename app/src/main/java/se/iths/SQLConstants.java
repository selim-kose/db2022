package se.iths;

public class SQLConstants {

    public static final String URL = "jdbc:mysql://localhost:3306/iths";
    public static final String USER = "iths";
    public static final String PASSWORD = "iths";


    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS User(Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(30) NOT NULL)";

}
