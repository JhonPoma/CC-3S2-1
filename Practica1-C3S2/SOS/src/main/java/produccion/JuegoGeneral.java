package produccion;

import java.awt.Color;

public class JuegoGeneral extends JuegoSimple {
  private int numeroSosAzul; // puntos del jugador azul
  private int numeroSosRojo; // puntos del jugador rojo

  public JuegoGeneral(int tamanio) {
    super(tamanio);
    this.numeroSosAzul = 0;
    this.numeroSosRojo = 0;
  }

  /**
   * Llena la celda en la fila y columna seleccionada con el valor dado por valor Celda
   * @param fila fila de celda en la que se realiza el movimiento
   * @param columna columna de celda en la que se realiza el movimiento
   * @param valorCelda valor 'S' u 'O' que se pondrá en la celda
   */
  @Override
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < getTotalFilas() && columna >= 0 && columna < getTotalColumnas()
        && getCelda(fila, columna) == Celda.VACIA) {
      setCelda(fila, columna, valorCelda);
      if (!hizoSos(fila, columna) && getEstadoJuego() == EstadoJuego.JUGANDO) {
        setTurno((getTurno() == Turno.ROJO) ? Turno.AZUL : Turno.ROJO);
      }
      actualizarEstadoJuego(fila, columna);
    }
  }

  /**
   * Actualiza el estado de juego según haya ganado el azul, rojo o haya un empate
   * @param fila fila del movimiento actual
   * @param columna columna del movimiento actual
   */
  @Override
  public void actualizarEstadoJuego(int fila, int columna) {
    boolean juegoTermino = true;
    for (int fil = 0; fil < getTotalFilas(); ++fil) {
      for (int col = 0; col < getTotalColumnas(); ++col) {
        if (getCelda(fil, col) == Celda.VACIA) {
          juegoTermino = false;
        }
      }
    }
    if (juegoTermino) {
      if (numeroSosAzul > numeroSosRojo) {
        setEstadoJuego(EstadoJuego.GANO_AZUL);
      } else if (numeroSosRojo > numeroSosAzul) {
        setEstadoJuego(EstadoJuego.GANO_ROJO);
      } else {
        setEstadoJuego(EstadoJuego.EMPATE);
      }
    }
  }

  @Override
  public boolean hizoSos(int fila, int col) {
    if (getCelda(fila, col) == Celda.S) {
      boolean realizoSos = false;
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila);
        actualizarPuntos();
        realizoSos = true;
      }
      if (col < getColumnasTotales() - 2 && getCelda(fila, col + 1) == Celda.O && getCelda(fila,
          col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col+2, fila);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        aniadirLineaSos(col, fila, col, fila - 2);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila < getFilasTotales() - 2 && getCelda(fila + 1, col) == Celda.O && getCelda(fila + 2,
          col) == Celda.S) {
        aniadirLineaSos(col, fila, col, fila+2);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila > 1 && col > 1 && getCelda(fila - 1, col - 1) == Celda.O
          && getCelda(fila - 2, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila-2);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila > 1 && col < getColumnasTotales() - 2 && getCelda(fila - 1, col + 1) == Celda.O
          && getCelda(fila - 2, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col+2, fila-2);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila < getFilasTotales() - 2 && col > 1 && getCelda(fila + 1, col - 1) == Celda.O
          && getCelda(fila + 2, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila+2);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila < getFilasTotales() - 2 && col < getColumnasTotales() - 2 && getCelda(fila + 1,
          col + 1) == Celda.O && getCelda(fila + 2, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col-2, fila+2);
        actualizarPuntos();
        realizoSos = true;
      }
        if (realizoSos) {
            return realizoSos;
        }
    }
    if (getCelda(fila, col) == Celda.O) {
      boolean realizoSos = false;
      if (col > 0 && col < getColumnasTotales() - 1 && getCelda(fila, col - 1) == Celda.S
          && getCelda(fila, col + 1) == Celda.S) {
        aniadirLineaSos(col-1, fila, col+1, fila);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && getCelda(fila - 1, col) == Celda.S
          && getCelda(fila + 1, col) == Celda.S) {
        aniadirLineaSos(col, fila-1, col, fila+1);
        actualizarPuntos();
        realizoSos = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col - 1) == Celda.S
          && getCelda(fila + 1, col + 1) == Celda.S) {
        aniadirLineaSos(col-1, fila-1, col+1, fila+1);
      actualizarPuntos();
        realizoSos = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col + 1) == Celda.S
          && getCelda(fila + 1, col - 1) == Celda.S) {
        aniadirLineaSos(col+1, fila-1, col-1, fila+1);
        actualizarPuntos();
        realizoSos = true;
      }
        if (realizoSos) {
            return realizoSos;
        }
    }
    return false;
  }

  /**
   * Incrementa un punto al jugador que hizo SOS
   */
  public void actualizarPuntos(){
    if (getTurno() == Turno.AZUL) {
      numeroSosAzul++;
    } else {
      numeroSosRojo++;
    }
  }

}
