package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import produccion.AutoJuegoSimple;
import produccion.Celda;
import produccion.EstadoJuego;
import produccion.TipoJugador;

public class TestAutoJuegoSimple {

  private AutoJuegoSimple juego;

  // Criterio de aceptación 9.1
  @Test
  public void testMovimientoComputadoraAutoJuegoSimple() {
    juego = new AutoJuegoSimple(3, TipoJugador.HUMANO, TipoJugador.COMPUTADORA);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
    juego.realizarMovimiento(1, 1, Celda.S); // movimiento Humano

    assertEquals(7,
        juego.getNumeroCeldasVacias()); // Celdas vacías = 9 - 1(humano) - 1(computador) = 7
  }

  // Criterio de aceptación 10.1
  @Test
  public void testAutoJuegoSimpleCompletoHumanoAzulComputadoraRojo() {
    juego = new AutoJuegoSimple(3, TipoJugador.HUMANO, TipoJugador.COMPUTADORA);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);

    while (juego.getEstadoJuego() == EstadoJuego.JUGANDO) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (juego.getCelda(i, j) == Celda.VACIA) {
            juego.realizarMovimiento(i, j, Celda.S);
          }
        }
      }
    }
    assertTrue(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL ||
        juego.getEstadoJuego() == EstadoJuego.GANO_ROJO ||
        juego.getEstadoJuego() == EstadoJuego.EMPATE);
  }

  // Criterio de aceptación 12.1
  @Test
  public void testAutoJuegoSimpleCompletoComputadoraAzulComputadoraRoja() {
    juego = new AutoJuegoSimple(3, TipoJugador.COMPUTADORA, TipoJugador.COMPUTADORA);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
    juego.realizarMovimiento(0, 0, Celda.S); // Sirve para iniciar el juego, la computadora
    // ignora la fila y columna y realiza un movimiento aleatorio
    assertTrue(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL ||
        juego.getEstadoJuego() == EstadoJuego.GANO_ROJO ||
        juego.getEstadoJuego() == EstadoJuego.EMPATE);
  }
}
