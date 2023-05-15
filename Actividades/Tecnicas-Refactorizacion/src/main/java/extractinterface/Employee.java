package extractinterface;

// Antes
/*
public class Employee {
    private double rate;
    private boolean specialSkill;
    private String name;
    private String department;
    public double getRate() {
        return rate;
    }
    public boolean hasSpecialSkill() {
        return specialSkill;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
}
*/
// Después
interface Billable {
    double getRate();

    boolean hasSpecialSkill();
}

public class Employee implements Billable {
    private double rate;
    private boolean specialSkill;
    private String name;
    private String department;

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public boolean hasSpecialSkill() {
        return specialSkill;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}


