package produccion;

/**
 * Representa un juego donde uno de los jugadores puede ser la computadora
 */
public interface AutoJuego {

  /**
   * @param jugador Color del jugador (Azul o Rojo)
   * @return el tipo de jugador (Computadora o Humano)
   */
  TipoJugador getTipoJugador(String jugador);

  /**
   * La computadora realiza un movimiento
   *
   * @param celda El valor de la celda (S u O)
   * @return true si hizo SOS o false si hizo un movimiento aleatorio
   */
  boolean realizarAutoMovimiento(Celda celda);

  /**
   * Realiza un movimiento aleatorio en cualquier celda vacía
   *
   * @param celda El valor de la celda (S u O)
   */
  void realizarMovimientoAleatorio(Celda celda);
}
