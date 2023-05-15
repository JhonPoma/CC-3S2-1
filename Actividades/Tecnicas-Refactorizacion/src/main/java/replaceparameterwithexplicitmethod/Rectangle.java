package replaceparameterwithexplicitmethod;

// Antes
/*
public class Rectangle {
    int height;
    int width;
    void setValue(String name, int value) {
        if (name.equals("height")) {
            height = value;
            return;
        }
        if (name.equals("width")) {
            width = value;
            return;
        }
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setValue("height", 5);
        rectangle.setValue("width", 10);
    }
}
*/

// Después
public class Rectangle {
    int height;
    int width;

    void setHeight(int arg) {
        height = arg;
    }

    void setWidth(int arg) {
        width = arg;
    }
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(5);
        rectangle.setWidth(10);
    }
}

