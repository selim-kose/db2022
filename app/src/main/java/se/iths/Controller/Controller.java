package se.iths.Controller;

import se.iths.model.Student;
import se.iths.repo.StudentRepo;

import java.sql.*;
import java.util.List;

public class Controller {


    public StudentRepo studentRepo = new StudentRepo();


    public Connection startConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iths", "iths","iths" );

        return con;
    }

    public void addStudent(String name) throws SQLException{
        PreparedStatement ps = startConnection().prepareStatement("INSERT INTO Student(Name) VALUES (?)");
        ps.setString(1, name);

        ps.executeUpdate();
    }


    public void addStudents(List<String> users) throws SQLException{
        PreparedStatement preparedStatement = startConnection().prepareStatement("INSERT INTO Student(Name) VALUES(?)");

        for(String i: users){
            preparedStatement.setString(1,i);
            preparedStatement.executeUpdate();
        }
    }

    public void removeStudentById(int id) throws SQLException{
        PreparedStatement ps = startConnection().prepareStatement("DELETE FROM Student WHERE Id = ?");
        ps.setInt(1,id);

        ps.executeUpdate();
    }


    public void updateStudent(int id, String name) throws SQLException{
        PreparedStatement ps = startConnection().prepareStatement("UPDATE Student SET Name = ? WHERE Id = ?");
        ps.setString(1,name);
        ps.setInt(2,id);

        ps.executeUpdate();
    }

    public Student getStudentById(int id) throws SQLException{
        Student student = null;
        PreparedStatement ps = startConnection().prepareStatement("SELECT * FROM Student WHERE Id = ?");
        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();


        while (rs.next()){
            int studentId = rs.getInt(1);
            String studentName = rs.getString(2);
            student = new Student(studentId,studentName);
        }
        return student;

    }

    public Student getStudentByName(String name) throws SQLException{
        Student student = null;
        PreparedStatement preparedStatement= startConnection().prepareStatement("SELECT * FROM Student WHERE = ?");
        ResultSet resultSet = preparedStatement.getResultSet();

        while(resultSet.next()){
            int studentId = resultSet.getInt(1);
            String studentName = resultSet.getString(2);
            student = new Student(studentId,studentName);
        }

        return student;
    }


    public void addStudentToList(int id) throws SQLException {
        studentRepo.addStudentToRepo(getStudentById(id));
    }
}
