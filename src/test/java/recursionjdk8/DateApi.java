package recursionjdk8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @program: selfplay
 * @description: jdk8中的dataapi
 * @author: zx
 **/
public class DateApi {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2018, 8, 15);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int i = date.lengthOfMonth();
        System.out.println(year + "   " + month + "   " + dayOfMonth + "   " + dayOfWeek + "   " + i);

    }
}
