package parameterizemethod;

// Antes
/*
public class Employee {
    private double salary;
    public void fivePercentRaise() {
        salary = salary * 1.05;
    }

    public void tenPercentRaise() {
        salary = salary * 1.1;
    }
}
 */

// Después
public class Employee {
    private double salary;
    public void raise(double percentage) {
        salary = salary * ((100 + percentage)/100);
    }
}