package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.JuegoSimple;

public class TestSeleccionaTamanio {

  private JuegoSimple juego;

  @BeforeEach
  public void setUp() {
    juego = new JuegoSimple(8);
  }

  // Criterio de aceptación 1.1
  @Test
  public void testSeleccionTamanioValido() {
    juego.setTamanioTablero(10);
    assertEquals(10, juego.getTamanioTablero());
  }

  // Criterio de aceptación 1.2
  @Test
  public void testSeleccionTamanioInvalido() {
    juego.setTamanioTablero(25);
    assertEquals(8, juego.getTamanioTablero());
  }
}
