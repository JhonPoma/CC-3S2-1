package hidedelegate;

// Antes
/*
public class Client {
    public static void main(String[] args) {
        Person person = new Person("Sales");
        Department department = person.getDepartment();
        String manager = department.getManager();
        System.out.println("Manager: " + manager);
    }
}
class Person{
    Department department;
    Person(String departmentName) {
        this.department = new Department(departmentName);
    }
    Department getDepartment() {
        return department;
    }
}
class Department{
    String name;
    Department(String name) {
        this.name = name;
    }
    String getManager() {
        if(name.equals("Sales")) {
            return "Carlos";
        } else {
            return "Juan";
        }
    }
}
 */

// Después
public class Client {
    public static void main(String[] args) {
        Person person = new Person("Sales");
        String manager = person.getManager();
        System.out.println("Manager: " + manager);
    }
}
class Person{
    Department department;
    Person(String departmentName) {
        this.department = new Department(departmentName);
    }
    String getManager() {
        return department.getManager();
    }
}
class Department{
    String name;
    Department(String name) {
        this.name = name;
    }
    String getManager() {
        if(name.equals("Sales")) {
            return "Carlos";
        } else {
            return "Juan";
        }
    }
}