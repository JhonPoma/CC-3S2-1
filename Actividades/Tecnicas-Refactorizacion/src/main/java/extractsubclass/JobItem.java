package extractsubclass;

// Antes
/*
public class JobItem {
    double unitPrice;
    int quantity;

    double getTotalPrice() {
        return unitPrice * quantity;
    }

    double getUnitPrice() {
        return unitPrice;
    }

    Employee getEmployee() {
        return new Employee();
    }
}

class Employee { }
*/

// Después
public class JobItem {
    double unitPrice;
    int quantity;

    double getTotalPrice() {
        return unitPrice * quantity;
    }

    double getUnitPrice() {
        return unitPrice;
    }

    Employee getEmployee() {
        return new Employee();
    }
}

class LaborItem extends JobItem {
    double getUnitPrice() {
        return unitPrice;
    }

    Employee getEmployee() {
        return new Employee();
    }
}

class Employee {
}

