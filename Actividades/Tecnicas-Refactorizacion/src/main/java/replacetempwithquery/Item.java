package replacetempwithquery;

// Antes
/*
public class Sale {
    int quantity;
    double itemPrice;
    double calculateTotal() {
        double basePrice = quantity * itemPrice;
        if (basePrice > 1000) {
            return basePrice * 0.95;
        }
        else {
            return basePrice * 0.98;
        }
    }
}
 */

// Después
public class Item {
    int quantity;
    double itemPrice;
    double calculateTotal() {
        if (basePrice() > 1000) {
            return basePrice() * 0.95;
        }
        else {
            return basePrice() * 0.98;
        }
    }
    double basePrice() {
        return  quantity * itemPrice;
    }
}
