package se.iths.Controller;

import se.iths.model.Student;
import se.iths.persistance.StudentRepo;

import static se.iths.SQLConstants.*;

import java.sql.*;
import java.util.List;

public class StudentController {


    public StudentRepo studentRepo = new StudentRepo();


    public Connection startConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);


        return con;
    }

    public void addStudent(String name) {
        PreparedStatement ps = null;

        try {
            ps = startConnection().prepareStatement("INSERT INTO Student(Name) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("could not add Student" + e);
        } finally {
            try {
                ps.close();
            } catch (Exception ignore) {
            }
        }
    }


    public void addStudents(List<String> users){
        PreparedStatement ps = null;
        try {
            ps = startConnection().prepareStatement("INSERT INTO Student(Name) VALUES(?)");

            for (String i : users) {
                ps.setString(1, i);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Could not add list of students" + e);
        } finally {
            try {
                ps.close();
            } catch (Exception ignore) {
            }
        }


    }

    public void removeStudentById(int id) {
        PreparedStatement ps = null;
        try {
            ps = startConnection().prepareStatement("DELETE FROM Student WHERE Id = ?");
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println();
        } finally {
            try {
                ps.close();
            } catch (Exception ignore) {
            }
        }

    }


    public void updateStudent(int id, String name) {
        PreparedStatement ps = null;
        try {
            ps = startConnection().prepareStatement("UPDATE Student SET Name = ? WHERE Id = ?");
            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println();
        } finally {
            try {
                ps.close();
            } catch (Exception ignore) {
            }
        }
    }

    public Student getStudentById(int id){
        Student student = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = startConnection().prepareStatement("SELECT * FROM Student WHERE Id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt(1);
                String studentName = rs.getString(2);
                student = new Student(studentId, studentName);
            }
        } catch (SQLException e) {
            System.out.println();
        } finally {
            try {
                ps.close();
            } catch (Exception ignore) {
            }
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
        return student;

    }


    public Student getStudentByName(String name)  {
        Student student = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = startConnection().prepareStatement("SELECT * FROM Student WHERE = ?");
            rs = ps.getResultSet();

            while (rs.next()) {
                int studentId = rs.getInt(1);
                String studentName = rs.getString(2);
                student = new Student(studentId, studentName);
            }

        } catch (SQLException e) {
            System.out.println("could not get student by name" + e);
        }finally {
            try {
                ps.close();
            }catch (Exception ignore){}
            try {
                rs.close();
            }catch (Exception e){}
        }


        return student;
    }


    public void addStudentToList(int id) throws SQLException {
        studentRepo.addStudentToRepo(getStudentById(id));
    }
}
