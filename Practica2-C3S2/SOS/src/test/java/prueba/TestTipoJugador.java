package prueba;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import produccion.AutoJuegoGeneral;
import produccion.AutoJuegoSimple;
import produccion.JuegoGeneral;
import produccion.JuegoSimple;
import produccion.SosGui;
import produccion.TipoJugador;

public class TestTipoJugador {

  // Criterio de aceptación 8.1
  @Test
  public void testSeleccionJugadorAzulComputadoraRojoHumanoJuegoSimple() {
    SosGui juego = new SosGui(new JuegoSimple(8));
    juego.setTipoJuego(new AutoJuegoSimple(8, TipoJugador.COMPUTADORA, TipoJugador.HUMANO));

    assertTrue(
        ((AutoJuegoSimple) juego.getTipoJuego()).getTipoJugador("Azul") == TipoJugador.COMPUTADORA);
    assertTrue(
        ((AutoJuegoSimple) juego.getTipoJuego()).getTipoJugador("Rojo") == TipoJugador.HUMANO);
  }

  @Test
  public void testSeleccionJugadorAzulHumanoRojoComputadoraJuegoSimple() {
    SosGui juego = new SosGui(new JuegoSimple(8));
    juego.setTipoJuego(new AutoJuegoSimple(8, TipoJugador.HUMANO, TipoJugador.COMPUTADORA));

    assertTrue(
        ((AutoJuegoSimple) juego.getTipoJuego()).getTipoJugador("Azul") == TipoJugador.HUMANO);
    assertTrue(
        ((AutoJuegoSimple) juego.getTipoJuego()).getTipoJugador("Rojo") == TipoJugador.COMPUTADORA);
  }

  @Test
  public void testSeleccionJugadorAzulComputadoraRojoComputadoraJuegoSimple() {
    SosGui juego = new SosGui(new JuegoSimple(8));
    juego.setTipoJuego(new AutoJuegoSimple(8, TipoJugador.COMPUTADORA, TipoJugador.COMPUTADORA));

    assertTrue(
        ((AutoJuegoSimple) juego.getTipoJuego()).getTipoJugador("Azul") == TipoJugador.COMPUTADORA);
    assertTrue(
        ((AutoJuegoSimple) juego.getTipoJuego()).getTipoJugador("Rojo") == TipoJugador.COMPUTADORA);
  }

  // Criterio de aceptación 8.2
  @Test
  public void testSeleccionJugadorAzulComputadoraRojoHumanoJuegoGeneral() {
    SosGui juego = new SosGui(new JuegoGeneral(8));
    juego.setTipoJuego(new AutoJuegoGeneral(8, TipoJugador.COMPUTADORA, TipoJugador.HUMANO));

    assertTrue(((AutoJuegoGeneral) juego.getTipoJuego()).getTipoJugador("Azul")
        == TipoJugador.COMPUTADORA);
    assertTrue(
        ((AutoJuegoGeneral) juego.getTipoJuego()).getTipoJugador("Rojo") == TipoJugador.HUMANO);
  }

  @Test
  public void testSeleccionJugadorAzulHumanoRojoComputadoraJuegoGeneral() {
    SosGui juego = new SosGui(new JuegoGeneral(8));
    juego.setTipoJuego(new AutoJuegoGeneral(8, TipoJugador.HUMANO, TipoJugador.COMPUTADORA));

    assertTrue(
        ((AutoJuegoGeneral) juego.getTipoJuego()).getTipoJugador("Azul") == TipoJugador.HUMANO);
    assertTrue(((AutoJuegoGeneral) juego.getTipoJuego()).getTipoJugador("Rojo")
        == TipoJugador.COMPUTADORA);
  }

  @Test
  public void testSeleccionJugadorAzulComputadoraRojoComputadoraJuegoGeneral() {
    SosGui juego = new SosGui(new JuegoGeneral(8));
    juego.setTipoJuego(new AutoJuegoGeneral(8, TipoJugador.COMPUTADORA, TipoJugador.COMPUTADORA));

    assertTrue(((AutoJuegoGeneral) juego.getTipoJuego()).getTipoJugador("Azul")
        == TipoJugador.COMPUTADORA);
    assertTrue(((AutoJuegoGeneral) juego.getTipoJuego()).getTipoJugador("Rojo")
        == TipoJugador.COMPUTADORA);
  }
}
