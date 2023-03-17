package student;

import java.math.BigDecimal;
import java.util.Arrays;

public class Student {
    private String name;
    private int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public int getNumberOfMarks() {
        return marks.length;
    }

    public int getTotalSumOfMarks() {
        return Arrays.stream(marks).sum();
    }

    public int getMaxMarks() {
        return Arrays.stream(marks).max().getAsInt();
    }

    public int getMinMarks() {
        return Arrays.stream(marks).min().getAsInt();
    }

    public BigDecimal getAverageMarks() {
        int sum = getTotalSumOfMarks();
        int len = getNumberOfMarks();
        return new BigDecimal(sum).divide(new BigDecimal(len));
    }
}
