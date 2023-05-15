package decomposeconditional;

import java.util.Date;

// Antes
/*
public class Sale {
    private static final Date SUMMER_START = new Date(2022, 11, 21);
    private static final Date SUMMER_END = new Date(2023, 2, 20);
    private double charge;
    private double winterRate;
    private double winterServiceCharge;
    private double summerRate;
    private Date date;

    public void setCharge(int quantity) {
        if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
            charge = quantity * winterRate + winterServiceCharge;
        } else {
            charge = quantity * summerRate;
        }
    }
}
 */

// Después
public class Sale {
    private static final Date SUMMER_START = new Date(2022, 11, 21);
    private static final Date SUMMER_END = new Date(2023, 2, 20);
    private double charge;
    private double winterRate;
    private double winterServiceCharge;
    private double summerRate;
    private Date date;

    public void setCharge(int quantity) {
        if (isWinter(date)) {
            winterCharge(quantity);
        } else {
            summerCharge(quantity);
        }
    }
    public boolean isWinter(Date date){
        return date.before(SUMMER_START) || date.after(SUMMER_END);
    }
    public void winterCharge(int quantity) {
        charge = quantity * winterRate + winterServiceCharge;
    }
    public void summerCharge(int quantity){
        charge = quantity * summerRate;
    }
}
