package consolidateduplicateconditionalfragments;

// Antes
/*
public class Sale {
    private double total;
    private double price;
    private double quantity;

    public boolean isSpecialDeal() {
        return quantity > 10;
    }

    public void send() {
        System.out.println("sending...");
    }

    public void sendPrice() {
        if (isSpecialDeal()) {
            total = price * 0.95;
            send();
        } else {
            total = price * 0.98;
            send();
        }
    }
}
 */

// Después
public class Item {
    private double total;
    private double price;
    private double quantity;

    public boolean isSpecialDeal() {
        return quantity > 10;
    }

    public void send() {
        System.out.println("sending...");
    }

    public void sendPrice() {
        if (isSpecialDeal()) {
            total = price * 0.95;
        } else {
            total = price * 0.98;
        }
        send();
    }
}