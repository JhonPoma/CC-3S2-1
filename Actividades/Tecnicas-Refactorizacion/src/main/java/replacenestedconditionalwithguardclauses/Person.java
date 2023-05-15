package replacenestedconditionalwithguardclauses;

// Antes
/*
public class Person {
    boolean isDead;
    boolean isSeparated;
    boolean isRetired;
    public double getPayAmount() {
        double result;
        if (isDead) {
            result = deadAmount();
        } else {
            if (isSeparated) {
                result = separatedAmount();
            } else {
                if (isRetired) {
                    result = retiredAmount();
                } else {
                    result = normalPayAmount();
                }
            }
        }
        return result;
    }
    double deadAmount(){
        return 100;
    }
    double separatedAmount(){
        return 150;
    }
    double retiredAmount(){
        return 120;
    }
    double normalPayAmount() {
        return 300;
    }
}
 */

// Después
public class Person {
    boolean isDead;
    boolean isSeparated;
    boolean isRetired;

    public double getPayAmount() {
        double result;
        if (isDead) {
            return deadAmount();
        }
        if (isSeparated) {
            return separatedAmount();
        }
        if (isRetired) {
            return retiredAmount();
        }
        return normalPayAmount();
    }

    double deadAmount() {
        return 100;
    }

    double separatedAmount() {
        return 150;
    }

    double retiredAmount() {
        return 120;
    }

    double normalPayAmount() {
        return 300;
    }
}
