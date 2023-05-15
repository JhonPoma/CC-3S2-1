package replaceparameterwithmethodcall;

// Antes
/*
public class Sale {
    int quantity;
    int itemPrice;
    double getSeasonalDiscount() {
        return 0.1;
    }
    double getFees() {
        return 5.8;
    }
    double discountedPrice(int base, double discount, double fees) {
        return  base - discount + fees;
    }
    double calculateFinalPrice() {
        int basePrice = quantity * itemPrice;
        double seasonDiscount = this.getSeasonalDiscount();
        double fees = this.getFees();
        double finalPrice = discountedPrice(basePrice, seasonDiscount, fees);
        return finalPrice;
    }
}
 */

// Después
public class Sale {
    int quantity;
    int itemPrice;
    double getSeasonalDiscount() {
        return 0.1;
    }
    double getFees() {
        return 5.8;
    }
    double discountedPrice(int base) {
        return  base - getSeasonalDiscount() + getFees();
    }
    double calculateFinalPrice() {
        int basePrice = quantity * itemPrice;
        double finalPrice = discountedPrice(basePrice);
        return finalPrice;
    }
}
