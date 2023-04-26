package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.JuegoSimple;

public class TestJuegoSimple {

  private JuegoSimple juego;

  @BeforeEach
  public void setUp() {
    juego = new JuegoSimple(3);
  }

  // Criterio de aceptación 4.1
  @Test
  public void testMovimientoValidoJuegoSimple() {
    juego.realizarMovimiento(1, 1, JuegoSimple.Celda.S);
    assertEquals(JuegoSimple.Celda.S, juego.getCelda(1, 1));
    assertTrue(juego.getTurno() == JuegoSimple.Turno.ROJO);
  }

  // Criterio de aceptación 4.2
  @Test
  public void testMovimientoInvalidoJuegoSimple() {
    juego.realizarMovimiento(1, 1, JuegoSimple.Celda.S);
    juego.realizarMovimiento(1, 1, JuegoSimple.Celda.O);
    assertEquals(JuegoSimple.Celda.S, juego.getCelda(1, 1));
    assertTrue(juego.getTurno() == JuegoSimple.Turno.ROJO);
  }

  // Criterio de aceptación 5.1
  @Test
  public void testJuegoSimpleConGanadorAzul() {
    juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S);
    juego.realizarMovimiento(1, 1, JuegoSimple.Celda.O);
    juego.realizarMovimiento(2, 2, JuegoSimple.Celda.S);
    assertEquals(JuegoSimple.EstadoJuego.GANO_AZUL, juego.getEstadoJuego());
  }

  @Test
  public void testJuegoSimpleConGanadorRojo() {
    juego.realizarMovimiento(0, 2, JuegoSimple.Celda.S);
    juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S);
    juego.realizarMovimiento(1, 1, JuegoSimple.Celda.O);
    juego.realizarMovimiento(2, 2, JuegoSimple.Celda.S);
    assertEquals(JuegoSimple.EstadoJuego.GANO_ROJO, juego.getEstadoJuego());
  }

  // Criterio de aceptación 5.2
  @Test
  public void testJuegoSimpleConEmpate() {
    juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S);
    juego.realizarMovimiento(0, 1, JuegoSimple.Celda.S);
    juego.realizarMovimiento(0, 2, JuegoSimple.Celda.S);
    juego.realizarMovimiento(1, 0, JuegoSimple.Celda.S);
    juego.realizarMovimiento(1, 1, JuegoSimple.Celda.S);
    juego.realizarMovimiento(1, 2, JuegoSimple.Celda.S);
    juego.realizarMovimiento(2, 0, JuegoSimple.Celda.S);
    juego.realizarMovimiento(2, 1, JuegoSimple.Celda.S);
    juego.realizarMovimiento(2, 2, JuegoSimple.Celda.S);
    assertEquals(JuegoSimple.EstadoJuego.EMPATE, juego.getEstadoJuego());
  }

}
