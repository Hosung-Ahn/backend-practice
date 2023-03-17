package student;

public class StudentRunner {

    public static void main(String[] args) {
        Student student = new Student("hosung", new int[] {10,20,20});
        System.out.println("student.getAverageMarks() = " + student.getAverageMarks());
        System.out.println("student.getMaxMarks() = " + student.getMaxMarks());
        System.out.println("student.getMinMarks() = " + student.getMinMarks());
        System.out.println("student.getNumberOfMarks() = " + student.getNumberOfMarks());
        System.out.println("student.getTotalSumOfMarks() = " + student.getTotalSumOfMarks());

    }
}
