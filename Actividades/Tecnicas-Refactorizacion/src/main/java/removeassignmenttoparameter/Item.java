package removeassignmenttoparameter;

// Antes
/*
public class Sale {
    int price;
    int discount(int inputVal, int quantity) {
        if(quantity > 50){
            inputVal -= 2;
        }
        return price + inputVal;
    }
}
*/

// Después
public class Item {
    int price;
    int discount(int inputVal, int quantity) {
        int result = inputVal;
        if(quantity > 50){
            result-= 2;
        }
        return price + result;
    }
}
