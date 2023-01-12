
package se.iths.model;

public class Student {



    private int studentId;
    private String StudentName;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        StudentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
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
