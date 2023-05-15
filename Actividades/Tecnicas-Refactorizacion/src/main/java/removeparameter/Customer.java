package removeparameter;

import java.util.Date;

// Antes
/*
public class Customer {
    String name;
    String phoneNumber;
    String address;
    String getContact(Date date) {
        return name + " - " + phoneNumber + " - " + address;
    }
}
 */

// Después

public class Customer {
    String name;
    String phoneNumber;
    String address;
    String getContact() {
        return name + " - " + phoneNumber + " - " + address;
    }
}



