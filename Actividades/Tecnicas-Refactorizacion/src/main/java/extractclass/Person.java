package extractclass;
// Antes
/*
public class Person {
    String name;
    String officeAreaCode;
    String officeNumber;
    String getTelephoneNumber() {
        return officeAreaCode + officeNumber;
    }
}
 */

// Después
public class Person {
    private String name;
    private TelephoneNumber telephoneNumber;

    public Person(String name, TelephoneNumber telephoneNumber) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber.getTelephoneNumber();
    }
}

class TelephoneNumber {
    private String officeAreaCode;
    private String officeNumber;

    TelephoneNumber(String officeAreaCode, String officeNumber) {
        this.officeNumber = officeNumber;
        this.officeAreaCode = officeAreaCode;
    }

    public String getTelephoneNumber() {
        return officeAreaCode + officeNumber;
    }
}


