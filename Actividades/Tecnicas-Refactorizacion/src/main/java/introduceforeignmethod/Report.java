package introduceforeignmethod;

import java.util.Date;

// Antes
/*
public class Report {
    Date previousEnd = new Date(2023, 5, 13);

    void sendReport() {
        Date nextDay = new Date(previousEnd.getYear(), previousEnd.getMonth(), previousEnd.getDay());
        System.out.println(nextDay);
    }
}
 */

// Después
public class Report {
    Date previousEnd = new Date(2023, 5, 13);

    void sendReport() {
        Date nextDay = nextDay(previousEnd);
        System.out.println(nextDay);
    }

    static Date nextDay(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDay() + 1);
    }
}