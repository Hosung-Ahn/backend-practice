package string;

import java.util.Locale;

public class StringRunner {

    public static void main(String[] args) {
        String str = "This is a lot of text again";
        String newStr = str.replace(' ', '\n');
        System.out.println(newStr);
        System.out.println("str.toUpperCase() = " + str.toUpperCase());
        System.out.println("str = " + str);
    }

}
