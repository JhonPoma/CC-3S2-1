package extractsubclass;

// Antes
/*
public class JobItem {
    private int unitPrice;
    private Employee employee;
    private boolean laborItem;

    private int totalPrice;

    public JobItem(int unitPrice, Employee employee, boolean isLaborItem, int totalPrice) {
        this.unitPrice = unitPrice;
        this.employee = employee;
        this.laborItem = isLaborItem;
        this.totalPrice = totalPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isLaborItem() {
        return laborItem;
    }

    public void setLaborItem(boolean laborItem) {
        this.laborItem = laborItem;
    }

    public int getTotalPrice() {
        if(isLaborItem()){
            totalPrice = unitPrice * 5;
        } else {
            totalPrice = unitPrice * 3;
        }
        return totalPrice;
    }

}
*/

//Después
public class JobItem {
    private int unitPrice;
    protected int totalPrice;

    public JobItem(int unitPrice, int totalPrice) {
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }


    public boolean isLaborItem() {
        return false;
    }

    public int getTotalPrice() {
        totalPrice = unitPrice * 3;
        return totalPrice;
    }

}

class LaborItem extends JobItem {
    private Employee employee;

    public LaborItem(int unitPrice, int totalPrice, Employee employee) {
        super(unitPrice, totalPrice);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isLaborItem() {
        return true;
    }

    public int getTotalPrice() {
        totalPrice = getUnitPrice() * 5;

        return totalPrice;
    }

}

class Employee {

}
