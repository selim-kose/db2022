package se.iths;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class CRUD {

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS User(Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(30) NOT NULL)";
    static List<String> names = Arrays.asList("Selim", "Eve", "Aslan");
    public static void main(String[] args) {

        CRUD crud = new CRUD();



        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "iths", "iths");
            con.createStatement().executeUpdate(CREATE_TABLE);


            crud.addUser("Mohammed");
            crud.addUsers(names);
            crud.updateUser(2, "Pelle");
            crud.removeUser("Selim");



        }catch (SQLException e){
            System.out.println("Fel! " + e);
        }


    }

    public static Statement statement() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "iths", "iths");
        Statement statement = con.createStatement();

        return statement;
    }

    public void addUser(String name) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "iths", "iths");
        PreparedStatement ps = con.prepareStatement("INSERT INTO User(Name) VALUES (?)");
        ps.setString(1, name);

        ps.executeUpdate();
    }


    public void addUsers(List<String> users) throws SQLException{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "iths", "iths");
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO User(Name) VALUES(?)");


        for(String i: users){
            preparedStatement.setString(1,i);
            preparedStatement.executeUpdate();
        }

       // statement().executeUpdate("INSERT INTO User(Name) VALUES('Selim')");

        //    for(String i: names) {

        //    }


    }

    public void removeUser(String name) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "iths", "iths");
        statement().executeUpdate("DELETE FROM User WHERE Name = ''");
        PreparedStatement ps = con.prepareStatement("DELETE FROM User WHERE Name = ?");
        ps.setString(1,name);

        ps.executeUpdate();
    }


    public void updateUser(int id, String name) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "iths", "iths");
        PreparedStatement ps = con.prepareStatement("UPDATE User SET Name = ? WHERE Id = ?");
        ps.setString(1,name);
        ps.setInt(2,id);

        ps.executeUpdate();
    }

}
