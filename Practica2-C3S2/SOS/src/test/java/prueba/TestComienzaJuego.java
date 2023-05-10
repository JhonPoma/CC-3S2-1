package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import produccion.Celda;
import produccion.EstadoJuego;
import produccion.JuegoGeneral;
import produccion.JuegoSimple;

public class TestComienzaJuego {

  JuegoSimple juego;

  @Test
  public void testComienzaJuegoSimpleConTamanioSeleccionado() {
    juego = new JuegoSimple(5);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
    juego.realizarMovimiento(0, 0, Celda.S);
    assertEquals(Celda.S, juego.getCelda(0, 0));
  }

  @Test
  public void testComienzaJuegoGeneralConTamanioSeleccionado() {
    juego = new JuegoGeneral(9);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
    juego.realizarMovimiento(2, 2, Celda.O);
    assertEquals(Celda.O, juego.getCelda(2, 2));
  }
}
