package se.iths;

import se.iths.Controller.Controller;
import se.iths.model.Student;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class App {

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS User(Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(30) NOT NULL)";
    static List<String> names = Arrays.asList("Selim Köse", "Eveliina Homonnai", "Aslan Sütemen","Paolo Maldini");


    public static void main(String[] args) {

        Controller crud = new Controller();


        try {
            crud.addStudents(names);
            crud.addStudent("Djingis Khan");
            crud.updateStudent(2, "Pelle Svanslös");
            crud.removeStudentById(1);
            crud.getStudentById(40);
            crud.addStudentToList(41);

            for(Student i: crud.studentRepo.getStudents()){
                System.out.println(i);
            }


        }catch (SQLException e){
            System.out.println("Fel! " + e);
        }


    }

}
