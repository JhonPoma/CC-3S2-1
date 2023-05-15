package collapsehierarchy;

// Antes
/*
public class Employee {
    String name;
    int totalSales;
    String getName(){
        return name;
    }
    int getSales(){
        return totalSales;
    }
}

class Salesman extends Employee {

}
*/


// Después
public class Employee {
    String name;
    int totalSales;

    String getName() {
        return name;
    }

    int getSales() {
        return totalSales;
    }
}