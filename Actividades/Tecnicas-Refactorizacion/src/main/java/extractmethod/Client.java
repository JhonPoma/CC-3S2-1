package extractmethod;

// Antes
/*
public class Client {
    String name;
    double outstanding;
    void printBanner() {
        System.out.println("********Debts*********");
    }

    public double getOutstanding() {
        return outstanding;
    }

    void printOwing() {
        printBanner();

        // Print details.
        System.out.println("name: " + name);
        System.out.println("amount: " + getOutstanding());
    }
}
 */

// Después
public class Client {
    String name;
    double outstanding;
    void printBanner() {
        System.out.println("********Debts*********");
    }

    public double getOutstanding() {
        return outstanding;
    }

    void printOwing() {
        printBanner();
        printDetails(getOutstanding());
    }

    void printDetails(double outstanding) {
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }
}
