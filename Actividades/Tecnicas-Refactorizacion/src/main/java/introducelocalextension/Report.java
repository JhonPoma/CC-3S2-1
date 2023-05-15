package introducelocalextension;

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
        Date nextDay = new MfDate(previousEnd).nextDay();
        System.out.println(nextDay);
    }
}

class MfDate extends Date {
    MfDate(Date date) {
        super(date.getYear(), date.getMonth(), date.getDay());
    }
    Date nextDay() {
        return new Date(this.getYear(), this.getMonth(), this.getDay() + 1);
    }
}
