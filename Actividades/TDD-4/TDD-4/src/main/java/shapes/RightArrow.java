package shapes;

public class RightArrow implements Shape {
    public void draw(Graphics g) {
        g.drawText("   \"" );
                g.drawText("-----");
        g.drawText("   /");
    }
}
