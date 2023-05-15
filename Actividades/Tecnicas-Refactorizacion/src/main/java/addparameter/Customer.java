package addparameter;

// Antes
/*
public class Customer {
    String name;
    String phoneNumber;
    String address;
    String getContact() {
        return name + " - " + phoneNumber + " - " + address;
    }
}
 */

import java.util.Date;

// Después
public class Customer {
    String name;
    String phoneNumber;
    String address;
    String getContact(Date date) {
        return date + "\n" + name + " - " + phoneNumber + " - " + address;
    }
}

