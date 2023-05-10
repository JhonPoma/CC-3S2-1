package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.Celda;
import produccion.EstadoJuego;
import produccion.JuegoGeneral;
import produccion.Turno;

public class TestJuegoGeneral {

  private JuegoGeneral juego;

  @BeforeEach
  public void setUp() {

    juego = new JuegoGeneral(3);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
  }

  // Criterio de aceptación 6.1
  @Test
  public void testMovimientoQueNoFormeSos() {
    juego.realizarMovimiento(0, 0, Celda.S);
    assertEquals(Celda.S, juego.getCelda(0, 0));
    assertTrue(juego.getTurno() == Turno.ROJO);
  }

  // Criterio de aceptación 6.2
  @Test
  public void testMovimientoFormaSos() {
    juego.realizarMovimiento(0, 0, Celda.S);
    juego.realizarMovimiento(0, 1, Celda.O);
    juego.realizarMovimiento(0, 2, Celda.S);
    assertEquals(Celda.S, juego.getCelda(0, 2));
    assertTrue(juego.getTurno() == Turno.AZUL);
  }

  // Criterio de aceptación 6.3
  @Test
  public void testMovimientoNoValido() {
    juego.realizarMovimiento(0, 0, Celda.S);
    juego.realizarMovimiento(0, 0, Celda.O);
    assertEquals(Celda.S, juego.getCelda(0, 0));
    assertTrue(juego.getTurno() == Turno.ROJO);
  }

  // Criterio de aceptación 7.1
  @Test
  public void testJuegoGeneralConGanadorAzul() {
    juego.realizarMovimiento(0, 0, Celda.S);
    juego.realizarMovimiento(0, 1, Celda.O);
    juego.realizarMovimiento(0, 2, Celda.S);
    juego.realizarMovimiento(1, 0, Celda.S);
    juego.realizarMovimiento(1, 1, Celda.S);
    juego.realizarMovimiento(1, 2, Celda.S);
    juego.realizarMovimiento(2, 0, Celda.S);
    juego.realizarMovimiento(2, 1, Celda.S);
    juego.realizarMovimiento(2, 2, Celda.S);
    assertTrue(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL);
  }

  @Test
  public void testJuegoGeneralConGanadorRojo() {
    juego.realizarMovimiento(0, 0, Celda.S);
    juego.realizarMovimiento(0, 1, Celda.O);
    juego.realizarMovimiento(1, 0, Celda.S);
    juego.realizarMovimiento(0, 2, Celda.S);
    juego.realizarMovimiento(1, 1, Celda.S);
    juego.realizarMovimiento(1, 2, Celda.S);
    juego.realizarMovimiento(2, 0, Celda.S);
    juego.realizarMovimiento(2, 1, Celda.S);
    juego.realizarMovimiento(2, 2, Celda.S);
    assertTrue(juego.getEstadoJuego() == EstadoJuego.GANO_ROJO);
  }

  // Criterio de aceptación 7.2
  @Test
  public void testJuegoGeneralConEmpate() {
    juego.realizarMovimiento(0, 0, Celda.S);
    juego.realizarMovimiento(0, 1, Celda.S);
    juego.realizarMovimiento(0, 2, Celda.S);
    juego.realizarMovimiento(1, 0, Celda.S);
    juego.realizarMovimiento(1, 1, Celda.S);
    juego.realizarMovimiento(1, 2, Celda.S);
    juego.realizarMovimiento(2, 0, Celda.S);
    juego.realizarMovimiento(2, 1, Celda.S);
    juego.realizarMovimiento(2, 2, Celda.S);
    assertTrue(juego.getEstadoJuego() == EstadoJuego.EMPATE);
  }
}
