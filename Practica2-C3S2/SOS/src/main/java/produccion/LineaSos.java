package produccion;

import java.awt.Color;

/**
 * Representa una línea SOS en el tablero
 */
public class LineaSos {

  // Coordenadas de los extremos de la línea son (x1, y1), (x2, y2)
  private int x1;
  private int y1;
  private int x2;
  private int y2;
  Color colorLinea;

  public LineaSos(int x1, int y1, int x2, int y2, Color colorLinea) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.colorLinea = colorLinea;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }

  public Color getColorLinea() {
    return colorLinea;
  }
}
