package inlinemethod;


// Antes
/*
public class PizzaDelivery {
    int numberOfLateDeliveries;
    int getRating() {
        return moreThanFiveLateDeliveries() ? 2 : 1;
    }
    boolean moreThanFiveLateDeliveries() {
        return numberOfLateDeliveries > 5;
    }
}
 */

// Después
public class PizzaDelivery {
    int numberOfLateDeliveries;
    int getRating() {
        return numberOfLateDeliveries > 5 ? 2 : 1;
    }
}