package se.iths.model;

public class Student {



    private int studentId;
    private String StudentName;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        StudentName = studentName;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", StudentName='" + StudentName + '\'' +
                '}';
    }
}
