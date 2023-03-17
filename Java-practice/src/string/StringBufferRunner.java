package string;

public class StringBufferRunner {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("hello");
        System.out.println("sb = " + sb);
        sb.append(123);
        System.out.println("sb = " + sb);
        sb.setCharAt(0, 'H');
        System.out.println("sb = " + sb);
    }
}
