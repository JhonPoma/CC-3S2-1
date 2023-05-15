package consolidateconditionalexpression;

// Antes
/*
public class Employee {
    private int seniority;
    private int monthsDisabled;
    private boolean isPartTime;
    public double disabilityAmount() {
        if(seniority < 2) {
            return 0;
        }
        if(monthsDisabled > 12) {
            return 0;
        }
        if(isPartTime){
            return 0;
        }
        return seniority/monthsDisabled;
    }
}
 */

// Después
public class Employee {
    private int seniority;
    private int monthsDisabled;
    private boolean isPartTime;
    public double disabilityAmount() {
        if(isnotEligibleForDisability()){
            return 0;
        }
        return seniority/monthsDisabled;
    }
    private boolean isnotEligibleForDisability() {
        return  seniority < 2 || monthsDisabled > 12 || isPartTime;
    }
}