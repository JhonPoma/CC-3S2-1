package shapes;

import java.util.ArrayList;
import java.util.List;

public class Shapes {
    private final List<Shape> allShapes = new ArrayList<>();
    private final Graphics graphics;

    public Shapes(Graphics graphics) {
        this.graphics = graphics;
    }

    public void add(Shape s) {
        allShapes.add(s);
    }

    public void draw() {
        allShapes.forEach(shape -> shape.draw(graphics));
    }
}








    /*public void draw(Graphics g) {
        for (Shape s : allShapes) {
            switch (s.getType()) {
                case "textbox":
                    var t = (TextBox) s;
                    t.draw(g);
                    //g.drawText(t.getText());
                    break;
                case "rectangle":
                    var r = (Rectangle) s;
                    r.draw(g);
                     for (int row = 0;
                          row < r.getHeight();
                          row++) {
                         g.drawLine(0, r.getWidth());
                     }
            }
        }
    }*/

