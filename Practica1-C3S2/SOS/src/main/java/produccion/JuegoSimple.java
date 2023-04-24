package produccion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoSimple {

  protected static int totalFilas = 8;
  protected static int totalColumnas = 8;

  protected List<LineaSos> lineasSos;

  public enum Celda {
    VACIA, S, O
  }
  protected Celda[][] tablero;

  public enum Turno {
    ROJO, AZUL
  }

  protected Turno turno;

  public enum EstadoJuego {
    JUGANDO, EMPATE, GANO_ROJO, GANO_AZUL
  }

  Turno ganador;
  protected EstadoJuego estadoJuegoActual;

  public JuegoSimple(int tamanio) {
    this.lineasSos = new ArrayList<>();
    setTamanioTablero(tamanio);
    tablero = new Celda[totalFilas][totalColumnas];
    iniciarJuego(tamanio);
  }

  public List<LineaSos> getLineasSos() {
    return lineasSos;
  }


  public boolean setTamanioTablero(int tamanio) {
    if (tamanio > 2 && tamanio <= 20) {
      totalFilas = tamanio;
      totalColumnas = tamanio;
      return true;
    }
    return false;
  }

  public int getTamanioTablero() {
    return totalFilas;
  }

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

  public Turno getTurno() {
    return turno;
  }

  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas
        && tablero[fila][columna] == Celda.VACIA) {
      tablero[fila][columna] = valorCelda;
      actualizarEstadoJuego(fila, columna);
      if (getEstadoJuego() == EstadoJuego.JUGANDO) {
        turno = (turno == Turno.ROJO) ? Turno.AZUL : Turno.ROJO;
      }
    }
  }

  public void actualizarEstadoJuego(int fila, int columna) {
    if (hizoSos(fila, columna)) {
      ganador = getTurno();
      estadoJuegoActual = (turno == Turno.ROJO) ? EstadoJuego.GANO_ROJO : EstadoJuego.GANO_AZUL;
    } else if (esEmpate()) {
      estadoJuegoActual = EstadoJuego.EMPATE;
    }
  }

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

  public boolean hizoSos(int fila, int col) {
    if (getCelda(fila, col) == Celda.S) {
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col - 2, fila, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (col < getColumnasTotales() - 2 && getCelda(fila, col + 1) == Celda.O && getCelda(fila,
              col + 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col + 2, fila, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col, fila - 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila < getFilasTotales() - 2 && getCelda(fila + 1, col) == Celda.O && getCelda(fila + 2,
              col) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col, fila + 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila > 1 && col > 1 && getCelda(fila - 1, col - 1) == Celda.O
              && getCelda(fila - 2, col - 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col - 2, fila - 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila > 1 && col < getColumnasTotales() - 2 && getCelda(fila - 1, col + 1) == Celda.O
              && getCelda(fila - 2, col + 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col + 2, fila - 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila < getFilasTotales() - 2 && col > 1 && getCelda(fila + 1, col - 1) == Celda.O
              && getCelda(fila + 2, col - 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col - 2, fila + 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila < getFilasTotales() - 2 && col < getColumnasTotales() - 2 && getCelda(fila + 1,
              col + 1) == Celda.O && getCelda(fila + 2, col + 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col + 2, fila + 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
    }
    if (getCelda(fila, col) == Celda.O) {
      boolean bo = false;
      if (col > 0 && col < getColumnasTotales() - 1 && getCelda(fila, col - 1) == Celda.S
              && getCelda(fila, col + 1) == Celda.S) {
        lineasSos.add(new LineaSos(col - 1, fila, col + 1, fila, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && getCelda(fila - 1, col) == Celda.S
              && getCelda(fila + 1, col) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila -1, col, fila+1, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
              && getCelda(fila - 1, col - 1) == Celda.S
              && getCelda(fila + 1, col + 1) == Celda.S) {
        lineasSos.add(new LineaSos(col-1, fila -1, col+1, fila+1, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
              && getCelda(fila - 1, col + 1) == Celda.S
              && getCelda(fila + 1, col - 1) == Celda.S) {
        lineasSos.add(new LineaSos(col+1, fila -1, col-1, fila+1, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        return true;
      }
    }
    return false;
  }

  public EstadoJuego getEstadoJuego() {
    return estadoJuegoActual;
  }
}
