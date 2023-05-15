package introduceparameterobject;

import java.util.Date;

// Antes
/*
public class Customer {
    void amountInvoiced(Date start, Date end) {

    }
    void amountReceived(Date start, Date end) {

    }
    void amountOverdue(Date start, Date end) {

    }
}
 */

// Después
public class Customer {
    void amountInvoiced(DateRange date) {

    }
    void amountReceived(DateRange date) {

    }
    void amountOverdue(DateRange date) {

    }
}

class DateRange {
    Date start;
    Date end;

    public DateRange(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
}
