package produccion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un juego simple SOS.
 */
public class JuegoSimple {
  private static int totalFilas = 8;
  private static int totalColumnas = 8;
  private List<LineaSos> lineasSos;
  private Celda[][] tablero;
  private Turno turno;
  private Turno ganador;
  private EstadoJuego estadoJuegoActual;

  public enum Celda {
    VACIA, S, O
  }

  public enum Turno {
    ROJO, AZUL
  }

  public enum EstadoJuego {
    JUGANDO, EMPATE, GANO_ROJO, GANO_AZUL
  }

  public JuegoSimple(int tamanio) {
    this.lineasSos = new ArrayList<>();
    setTamanioTablero(tamanio);
    tablero = new Celda[totalFilas][totalColumnas];
    iniciarJuego(tamanio);
  }

  public static int getTotalFilas() {
    return totalFilas;
  }

  public static int getTotalColumnas() {
    return totalColumnas;
  }

  public List<LineaSos> getLineasSos() {
    return lineasSos;
  }

  public boolean setTamanioTablero(int tamanio) {
    if (tamanio > 2 && tamanio <= 20) {
      tablero = new Celda[tamanio][tamanio];
      totalFilas = tamanio;
      totalColumnas = tamanio;
      return true;
    }
    return false;
  }

  public int getTamanioTablero() {
    return totalFilas;
  }

  /**
   * Inicializa un tablero del tamaño dado
   * @param tamanio Número de celdas por lado en el tablero
   */
  private void iniciarJuego(int tamanio) {
    setTamanioTablero(tamanio);
    for (int fila = 0; fila < totalFilas; ++fila) {
      for (int col = 0; col < totalColumnas; ++col) {
        tablero[fila][col] = Celda.VACIA;
      }
    }
    estadoJuegoActual = EstadoJuego.JUGANDO;
    turno = Turno.AZUL;
    lineasSos.clear();
  }

  public void setTurno(Turno turno) {
    this.turno = turno;
  }

  public Turno getTurno() {
    return turno;
  }

  public void resetearJuego(int tamanio) {
    iniciarJuego(tamanio);
  }

  public int getFilasTotales() {
    return totalFilas;
  }

  public int getColumnasTotales() {
    return totalColumnas;
  }

  public Celda getCelda(int fila, int columna) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas) {
      return tablero[fila][columna];
    } else {
      return null;
    }
  }

  public void setCelda(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas) {
      tablero[fila][columna] = valorCelda;
    }
  }

  /**
   * Llena la celda en la fila y columna seleccionada con el valor dado por valor Celda
   * @param fila fila de celda en la que se realiza el movimiento
   * @param columna columna de celda en la que se realiza el movimiento
   * @param valorCelda valor 'S' u 'O' que se pondrá en la celda
   */
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas
        && getCelda(fila, columna) == Celda.VACIA) {
      setCelda(fila, columna, valorCelda);
      actualizarEstadoJuego(fila, columna);
      if (getEstadoJuego() == EstadoJuego.JUGANDO) {
        setTurno((getTurno() == Turno.ROJO) ? Turno.AZUL : Turno.ROJO);
      }
    }
  }

  /**
   * Actualiza el estado de juego según haya ganado el azul, rojo o haya un empate
   * @param fila fila del movimiento actual
   * @param columna columna del movimiento actual
   */
  public void actualizarEstadoJuego(int fila, int columna) {
    if (hizoSos(fila, columna)) {
      ganador = getTurno();
      estadoJuegoActual = (turno == Turno.ROJO) ? EstadoJuego.GANO_ROJO : EstadoJuego.GANO_AZUL;
    } else if (esEmpate()) {
      estadoJuegoActual = EstadoJuego.EMPATE;
    }
  }

  /**
   * Este método se llama después de verificar que no hubo SOS
   * Verifica que no hay más celdas vacías y por lo tanto es empate
   * @return true cuando hay empate
   */
  public boolean esEmpate() {
    for (int fila = 0; fila < totalFilas; ++fila) {
      for (int col = 0; col < totalColumnas; ++col) {
        if (tablero[fila][col] == Celda.VACIA) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Verifica si el movimiento actual forma uno o más SOS, los añade a la lista "lineasSos"
   * @param fila fila del movimiento actual
   * @param col columna del movimiento actual
   * @return true si se ha formado uno o mas SOS, caso contrario retorna false
   */
  public boolean hizoSos(int fila, int col) {
    if (getCelda(fila, col) == Celda.S) {
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila);
        return true;
      }
      if (col < getColumnasTotales() - 2 && getCelda(fila, col + 1) == Celda.O && getCelda(fila,
              col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col+2, fila);
        return true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        aniadirLineaSos(col, fila, col, fila - 2);
        return true;
      }
      if (fila < getFilasTotales() - 2 && getCelda(fila + 1, col) == Celda.O && getCelda(fila + 2,
              col) == Celda.S) {
        aniadirLineaSos(col, fila, col, fila+2);
        return true;
      }
      if (fila > 1 && col > 1 && getCelda(fila - 1, col - 1) == Celda.O
              && getCelda(fila - 2, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila-2);
        return true;
      }
      if (fila > 1 && col < getColumnasTotales() - 2 && getCelda(fila - 1, col + 1) == Celda.O
              && getCelda(fila - 2, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col+2, fila-2);
        return true;
      }
      if (fila < getFilasTotales() - 2 && col > 1 && getCelda(fila + 1, col - 1) == Celda.O
              && getCelda(fila + 2, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila+2);
        return true;
      }
      if (fila < getFilasTotales() - 2 && col < getColumnasTotales() - 2 && getCelda(fila + 1,
              col + 1) == Celda.O && getCelda(fila + 2, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col+2, fila+2);
        return true;
      }
    }
    if (getCelda(fila, col) == Celda.O) {
      boolean bo = false;
      if (col > 0 && col < getColumnasTotales() - 1 && getCelda(fila, col - 1) == Celda.S
              && getCelda(fila, col + 1) == Celda.S) {
        aniadirLineaSos( col-1,  fila,  col+1,  fila);
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && getCelda(fila - 1, col) == Celda.S
              && getCelda(fila + 1, col) == Celda.S) {
        aniadirLineaSos( col,  fila-1,  col,  fila+1);
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
              && getCelda(fila - 1, col - 1) == Celda.S
              && getCelda(fila + 1, col + 1) == Celda.S) {
        aniadirLineaSos( col-1,  fila-1,  col+1,  fila+1);
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
              && getCelda(fila - 1, col + 1) == Celda.S
              && getCelda(fila + 1, col - 1) == Celda.S) {
        aniadirLineaSos( col+1,  fila-1,  col-1,  fila);
        return true;
      }
    }
    return false;
  }

  public void aniadirLineaSos(int col1, int fil1, int col2, int fil2){
    lineasSos.add(new LineaSos(col1, fil1, col2, fil2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
  }

  public EstadoJuego getEstadoJuego() {
    return estadoJuegoActual;
  }
  public void setEstadoJuego(EstadoJuego estadoJuegoActual) {
    this.estadoJuegoActual = estadoJuegoActual;
  }

}
