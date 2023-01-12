package se.iths;

import se.iths.Controller.StudentController;
import se.iths.model.Student;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class App {


    static List<String> names = Arrays.asList("Selim Köse", "Eveliina Homonnai", "Aslan Sütemen","Paolo Maldini");


    public static void main(String[] args) {

        StudentController crud = new StudentController();


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
            System.out.println("Fel vid körning av CRUD! " + e);
        }


    }

}
