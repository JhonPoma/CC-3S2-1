package movemethod;

// Antes
/*
public class Sale {
    double price;

    double totalPrice() {
        return price * 1.18;
    }
    void printPrice() {
        System.out.println("Price = " + totalPrice());
    }
}

class Sale {
    double price;
    int quantity;
    Sale item;

    double discount() {
        if (quantity < 100) {
            return item.totalPrice() * 0.1;
        } else if (quantity < 500) {
            return item.totalPrice() * 0.2;
        } else {
            return item.totalPrice() * 0.3;
        }
    }
}
 */

// Después
public class Item {
    double price;

    void printPrice() {
        System.out.println("Price = " + new Sale().totalPrice());
    }
}

class Sale {
    double price;
    int quantity;
    Item item;
    double totalPrice() {
        return price * 1.18;
    }

    double discount() {
        if (quantity < 100) {
            return totalPrice() * 0.1;
        } else if (quantity < 500) {
            return totalPrice() * 0.2;
        } else {
            return totalPrice() * 0.3;
        }
    }
}
