package preservewholeobject;

// Antes
/*
public class Plan {
    public static void main(String[] args) {
        Plan plan = new Plan();
        DaysTempRange daysTempRange = new DaysTempRange(5, 15);
        int low = daysTempRange.getLow();
        int high = daysTempRange.getHigh();
        boolean withinPlan = plan.withinRange(low, high);
    }

    boolean withinRange(int low, int high){
        return low > 3 && high < 17;
    }
}

class DaysTempRange{
    int low;
    int high;

    public DaysTempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }
}
 */

// Después
public class Plan {
    public static void main(String[] args) {
        Plan plan = new Plan();
        DaysTempRange daysTempRange = new DaysTempRange(5, 15);
        boolean withinPlan = plan.withinRange(daysTempRange);
    }

    boolean withinRange(DaysTempRange daysTempRange){
        return daysTempRange.getLow() > 3 && daysTempRange.getHigh() < 17;
    }
}

class DaysTempRange{
    int low;
    int high;

    public DaysTempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }
}
