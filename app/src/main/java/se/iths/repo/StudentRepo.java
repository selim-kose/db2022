package se.iths.repo;

import se.iths.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
   private List<Student> students;


    public StudentRepo() {
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudentToRepo(Student student){
        students.add(student);
    }
}
