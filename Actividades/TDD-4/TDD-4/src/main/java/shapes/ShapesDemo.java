package shapes;

public class ShapesDemo {
    public static void main(String[] args) {
        new ShapesDemo().run();
    }

    private void run() {
        Graphics console = new ConsoleGraphics();
        var shapes = new Shapes(console);

        shapes.add(new TextBox("Hola CC-3S2"));
        shapes.add(new Rectangle(32, 1));
        shapes.add(new RightArrow());
        shapes.add(new TextBox("Usando los principios de SOLID"));
        shapes.add(new TextBox("para crear un mini-framework"));
        shapes.add(new TextBox("de dibujos en ASCII"));
        shapes.add(new TextBox("en un rectangulo 3 x 5:"));
        shapes.add(new Rectangle(5, 3));

        shapes.draw();
    }
}


