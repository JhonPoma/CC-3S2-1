package renamemethod;

// Antes
/*
public class Customer {
    private String firstName;
    private String secondName;
    public String getFirstName(){
        return firstName;
    }
    public String getsnm() {
        return secondName;
    }
}
 */

// Después
public class Customer {
    private String firstName;
    private String secondName;
    public String getFirstName(){
        return firstName;
    }
    public String getSecondName() {
        return secondName;
    }
}
