package wrapper;

public class WrapperRunner {
    public static void main(String[] args) {
        int i = 5;
        Integer integer = new Integer(10);
        i = integer;
        System.out.println("i = " + i);
        String s = Integer.toString(i);
        System.out.println("s = " + s);

        Integer two = 2;
        Integer three = Integer.valueOf(3);


    }



}
