package date;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateRunner {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println("nowTime = " + nowTime);

        System.out.println("nowTime.getHour() = " + nowTime.getHour());
        System.out.println("nowTime.getMinute() = " + nowTime.getMinute());
        System.out.println("nowTime.getSecond() = " + nowTime.getSecond());
        System.out.println("nowTime.getDayOfMonth() = " + nowTime.getDayOfMonth());
        System.out.println("nowTime.getDayOfWeek() = " + nowTime.getDayOfWeek());

        System.out.println("nowTime.getMonth() = " + nowTime.getMonth());
        System.out.println("nowTime.getMonthValue() = " + nowTime.getMonthValue());
    }
}
