package produccion;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Interfaz gráfica del juego SOS
 */
public class SosGui extends JFrame {

  private JuegoSimple juego;
  private PanelCentral panelCentral;
  private PanelSuperior panelSuperior;
  private PanelInferior panelInferior;
  private PanelIzquierdo panelIzquierdo;
  private PanelDerecho panelDerecho;
  private Container panelDeContenido;
  private Font fuente = new Font("SansSerif", Font.PLAIN, 16);
  private static final int TAMANIO_CELDA = 30;

  private int filaSeleccionada;
  private int colSeleccionada;

  private JRadioButton btnOAzul;
  private JRadioButton btnSAzul;
  private JRadioButton btnHumanoRojo;
  private JRadioButton btnHumanoAzul;
  private JRadioButton btnComputadoraRojo;
  private JRadioButton btnComputadoraAzul;
  private JRadioButton btnORojo;
  private JRadioButton btnSRojo;
  private JRadioButton btnJuegoSimple;
  private JRadioButton btnJuegoGeneral;
  private JTextField txtTamanioTablero;

  /**
   * Crea la interfaz de acuerdo al tipo de juego
   *
   * @param juego tipo de juego
   */
  public SosGui(JuegoSimple juego) {
    this.juego = juego;
    setPanelDeContenido();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setTitle("Juego SOS");
    setVisible(true);
  }

  public void setTipoJuego(JuegoSimple juego) {
    this.juego = juego;
  }

  public JuegoSimple getTipoJuego() {
    return this.juego;
  }

  /**
   * Panel que contiene toda la interfaz del juego
   */
  public void setPanelDeContenido() {
    panelCentral = new PanelCentral();
    panelCentral
        .setPreferredSize(new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 1,
            juego.getTamanioTablero() * TAMANIO_CELDA + 1));
    panelInferior = new PanelInferior();

    panelIzquierdo = new PanelIzquierdo();
    panelIzquierdo.setPreferredSize(new Dimension(180, juego.getTamanioTablero() * TAMANIO_CELDA));

    panelDerecho = new PanelDerecho();
    panelDerecho.setPreferredSize(new Dimension(180, juego.getTamanioTablero() * TAMANIO_CELDA));

    panelSuperior = new PanelSuperior();
    panelSuperior.setPreferredSize(
        new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 300, 50));

    panelDeContenido = getContentPane();
    panelDeContenido.setLayout(new BorderLayout());

    panelDeContenido.add(panelCentral, BorderLayout.CENTER);
    panelDeContenido.add(panelSuperior, BorderLayout.NORTH);
    panelDeContenido.add(panelIzquierdo, BorderLayout.WEST);
    panelDeContenido.add(panelDerecho, BorderLayout.EAST);
    panelDeContenido.add(panelInferior, BorderLayout.SOUTH);
  }

  /**
   * Panel que contiene al tablero
   */
  public class PanelCentral extends JPanel {

    public PanelCentral() {
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (juego.getEstadoJuego() == EstadoJuego.JUGANDO) {
            filaSeleccionada = e.getY() / TAMANIO_CELDA;
            colSeleccionada = e.getX() / TAMANIO_CELDA;
            Celda celdaSeleccionada = null;
            if (juego.getTurno() == Turno.AZUL) {
              if (btnOAzul.isSelected()) {
                celdaSeleccionada = Celda.O;
              } else if (btnSAzul.isSelected()) {
                celdaSeleccionada = Celda.S;
              }
            } else if (juego.getTurno() == Turno.ROJO) {
              if (btnORojo.isSelected()) {
                celdaSeleccionada = Celda.O;
              } else if (btnSRojo.isSelected()) {
                celdaSeleccionada = Celda.S;
              }
            }
            juego.realizarMovimiento(filaSeleccionada, colSeleccionada, celdaSeleccionada);
          }
          panelCentral.repaint();
        }
      });
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.WHITE);
      dibujarLineas(g);
      dibujarTablero(g);
      panelInferior.actualizarTurnoActual();
      panelInferior.actualizarResultado();
      dibujarSos(g);
    }

    /**
     * Dibuja todas las líneas SOS del color del jugador que las realizó
     *
     * @param g
     */
    public void dibujarSos(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(4));

      List<LineaSos> lineasSos = juego.getLineasSos();
      for (LineaSos ls : lineasSos) {
        g2d.setColor(ls.getColorLinea());
        int x1 = ls.getX1() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int x2 = ls.getX2() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int y1 = ls.getY1() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        int y2 = ls.getY2() * TAMANIO_CELDA + TAMANIO_CELDA / 2;
        g2d.drawLine(x1, y1, x2, y2);
      }
    }

    /**
     * Dibuja las líneas del tablero
     *
     * @param g
     */
    private void dibujarLineas(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);

      for (int fila = 0; fila <= juego.getTamanioTablero(); fila++) {
        g.drawLine(0, fila * TAMANIO_CELDA, juego.getTamanioTablero() * TAMANIO_CELDA,
            fila * TAMANIO_CELDA);
      }

      for (int col = 0; col <= juego.getTamanioTablero(); col++) {
        g.drawLine(col * TAMANIO_CELDA, 0, col * TAMANIO_CELDA,
            juego.getTamanioTablero() * TAMANIO_CELDA);
      }
    }

    /**
     * Dibuja las letras 'S' u 'O' en el tablero
     *
     * @param g
     */
    private void dibujarTablero(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("SansSerif", Font.PLAIN, 5 * TAMANIO_CELDA / 6));
      for (int fila = 0; fila < juego.getFilasTotales(); fila++) {
        for (int col = 0; col < juego.getColumnasTotales(); col++) {
          int x1 = col * TAMANIO_CELDA + 1 * TAMANIO_CELDA / 6;
          int y1 = fila * TAMANIO_CELDA + 5 * TAMANIO_CELDA / 6;
          if (juego.getCelda(fila, col) == Celda.S) {
            g2d.drawString("S", x1, y1);
          } else if (juego.getCelda(fila, col) == Celda.O) {
            g2d.drawString("O", x1, y1);
          }
        }
      }
    }
  }

  /**
   * Panel que contiene el tipo de juego y tamaño del tablero
   */
  public class PanelSuperior extends JPanel {

    public PanelSuperior() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setBackground(Color.WHITE);

      JPanel pnlTipoJuego = new JPanel();
      pnlTipoJuego.setBackground(Color.WHITE);

      // Elegir entre juego simple y general
      JLabel lblSos = new JLabel("SOS");
      lblSos.setFont(fuente);
      pnlTipoJuego.add(lblSos);

      btnJuegoSimple = new JRadioButton("Juego Simple", true);
      btnJuegoSimple.setBorder(BorderFactory.createEmptyBorder());
      btnJuegoSimple.setFont(fuente);
      btnJuegoSimple.setBackground(Color.WHITE);

      btnJuegoGeneral = new JRadioButton("Juego General", false);
      btnJuegoGeneral.setBorder(BorderFactory.createEmptyBorder());
      btnJuegoGeneral.setFont(fuente);
      btnJuegoGeneral.setBackground(Color.WHITE);

      // Realiza el cambio del tipo de juego a Juego Simple
      btnJuegoSimple.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (getTipoJuego().getClass() == JuegoGeneral.class) {
            setTipoJuego(new JuegoSimple(juego.getTamanioTablero()));
          }
        }
      });

      // Realiza el cambio del tipo de juego a Juego General
      btnJuegoGeneral.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (getTipoJuego().getClass() == JuegoSimple.class) {
            setTipoJuego(new JuegoGeneral(juego.getTamanioTablero()));
          } else if (getTipoJuego().getClass() == AutoJuegoSimple.class) {
            TipoJugador jugadorAzul = TipoJugador.HUMANO;
            TipoJugador jugadorRojo = TipoJugador.HUMANO;
            if (btnComputadoraAzul.isSelected()) {
              jugadorAzul = TipoJugador.COMPUTADORA;
            }
            if (btnComputadoraRojo.isSelected()) {
              jugadorRojo = TipoJugador.COMPUTADORA;
            }
            setTipoJuego(new AutoJuegoGeneral(juego.getTamanioTablero(), jugadorAzul, jugadorRojo));
          }
        }
      });

      pnlTipoJuego.add(btnJuegoSimple);
      pnlTipoJuego.add(btnJuegoGeneral);

      ButtonGroup btnGrpTipoJuego = new ButtonGroup();
      btnGrpTipoJuego.add(btnJuegoSimple);
      btnGrpTipoJuego.add(btnJuegoGeneral);

      add(pnlTipoJuego, BorderLayout.WEST);

      // Ingresa tamaño del tablero
      JPanel pnlTamanioTablero = new JPanel();
      pnlTamanioTablero.setBackground(Color.WHITE);
      JLabel lblTamanioTablero = new JLabel("Tamaño tablero");
      lblTamanioTablero.setFont(fuente);
      pnlTamanioTablero.add(lblTamanioTablero);
      txtTamanioTablero = new JTextField(3);
      txtTamanioTablero.setFont(fuente);
      txtTamanioTablero.setText(String.valueOf(juego.getTamanioTablero()));

      // Cambia el tamaño del tablero
      txtTamanioTablero.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int tamanio = Integer.parseInt(txtTamanioTablero.getText());
          if (juego.setTamanioTablero(tamanio)) {
            juego.setTamanioTablero(tamanio);

            if (getTipoJuego().getClass() == JuegoSimple.class) {
              setTipoJuego(new JuegoSimple(juego.getTamanioTablero()));
            }
            if (getTipoJuego().getClass() == JuegoGeneral.class) {
              setTipoJuego(new JuegoGeneral(juego.getTamanioTablero()));
            }

            panelDeContenido.repaint();
            panelSuperior.setPreferredSize(
                new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 301, 50));
            panelIzquierdo.setPreferredSize(
                new Dimension(150, juego.getTamanioTablero() * TAMANIO_CELDA + 1));
            SosGui.this.pack();
          } else {
            JOptionPane.showMessageDialog(SosGui.this
                , "El tamaño debe estar entre 3 y 20", "Error de tamaño",
                JOptionPane.ERROR_MESSAGE);
            txtTamanioTablero.setText(String.valueOf(juego.getTamanioTablero()));
          }
        }
      });
      txtTamanioTablero.setHorizontalAlignment(JTextField.CENTER);
      pnlTamanioTablero.add(txtTamanioTablero);
      add(pnlTamanioTablero, BorderLayout.EAST);
    }

  }

  /**
   * Panel que contiene el turno actual, muestra el resultado y contiene al botón NuevoJuego
   */
  public class PanelInferior extends JPanel {

    private JLabel lblTurno;
    private JLabel lblResultado;

    PanelInferior() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      setBackground(Color.WHITE);

      // Muestra el turno actual
      String turno = juego.getTurno() == Turno.AZUL ? "Azul" : "Rojo";
      lblTurno = new JLabel("Turno actual: " + turno);
      lblTurno.setFont(fuente);
      add(lblTurno, BorderLayout.WEST);

      //Muestra el resultado
      lblResultado = new JLabel("");
      lblResultado.setFont(fuente);
      add(lblResultado, BorderLayout.CENTER);

      JButton btnReset = new JButton("Reset");
      btnReset.setFont(fuente);
      add(btnReset, BorderLayout.EAST);

      // Resetea el juego al estado inicial
      btnReset.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          juego.resetearJuego(8);
          txtTamanioTablero.setText(String.valueOf(juego.getTamanioTablero()));
          panelCentral.repaint();
          btnSAzul.doClick();
          btnSRojo.doClick();
          btnJuegoSimple.doClick();
          btnHumanoAzul.doClick();
          btnHumanoRojo.doClick();
        }
      });
    }

    /**
     * Actualiza el turno en la interfaz
     */
    void actualizarTurnoActual() {
      String turno = juego.getTurno() == Turno.AZUL ? "Azul" : "Rojo";
      lblTurno.setText("Turno actual: " + turno);
    }

    /**
     * Actualiza el resultado en la interfaz
     */
    void actualizarResultado() {
      String resultado = "";
      if (juego.getEstadoJuego() == EstadoJuego.GANO_AZUL) {
        resultado = "        Ganador: Azul!!!";
      } else if (juego.getEstadoJuego() == EstadoJuego.GANO_ROJO) {
        resultado = "        Ganador: Rojo!!!";
      } else if (juego.getEstadoJuego() == EstadoJuego.EMPATE) {
        resultado = "        Empate";
      }
      lblResultado.setText(resultado);
    }
  }

  /**
   * Muestra la letra seleccionada por el juagdor azul
   */
  private class PanelIzquierdo extends JPanel {

    PanelIzquierdo() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugAzul = new JLabel("Jugador Azul");
      lblJugAzul.setFont(fuente);
      add(lblJugAzul);

      btnHumanoAzul = new JRadioButton("Humano", true);
      btnHumanoAzul.setFont(fuente);
      btnHumanoAzul.setBackground(Color.WHITE);
      btnHumanoAzul.setBorder(BorderFactory.createEmptyBorder());

      btnComputadoraAzul = new JRadioButton("Computadora", false);
      btnComputadoraAzul.setFont(fuente);
      btnComputadoraAzul.setBackground(Color.WHITE);
      btnComputadoraAzul.setBorder(BorderFactory.createEmptyBorder());

      /**
       * Cambia el tipo de juego de acuerdo a los valores seleccionados (humano o computador)
       */
      btnComputadoraAzul.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (btnComputadoraRojo.isSelected() && btnJuegoSimple.isSelected()) {
            setTipoJuego(new AutoJuegoSimple(juego.getTamanioTablero(), TipoJugador.COMPUTADORA,
                TipoJugador.COMPUTADORA));
          } else if (btnComputadoraRojo.isSelected() && btnJuegoGeneral.isSelected()) {
            setTipoJuego(new AutoJuegoGeneral(juego.getTamanioTablero(), TipoJugador.COMPUTADORA,
                TipoJugador.COMPUTADORA));
          } else if (btnHumanoRojo.isSelected() && btnJuegoSimple.isSelected()) {
            setTipoJuego(new AutoJuegoSimple(juego.getTamanioTablero(), TipoJugador.COMPUTADORA,
                TipoJugador.HUMANO));
          } else if (btnHumanoRojo.isSelected() && btnJuegoGeneral.isSelected()) {
            setTipoJuego(new AutoJuegoGeneral(juego.getTamanioTablero(), TipoJugador.COMPUTADORA,
                TipoJugador.HUMANO));
          }

          Celda celda;
          if (btnSAzul.isSelected()) {
            celda = Celda.S;
          } else {
            celda = Celda.O;
          }

          juego.realizarMovimiento(0, 0, celda);

          panelCentral.repaint();
        }
      });

      ButtonGroup btnGrpTipoJugadorAzul = new ButtonGroup();
      btnGrpTipoJugadorAzul.add(btnComputadoraAzul);
      btnGrpTipoJugadorAzul.add(btnHumanoAzul);

      btnSAzul = new JRadioButton("S", true);
      btnSAzul.setFont(fuente);
      btnSAzul.setBackground(Color.WHITE);
      btnSAzul.setBorder(BorderFactory.createEmptyBorder());
      btnSAzul.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          juego.setCeldaJugadorAzul(Celda.S);
        }
      });

      btnOAzul = new JRadioButton("O", false);
      btnOAzul.setFont(fuente);
      btnOAzul.setBackground(Color.WHITE);
      btnOAzul.setBorder(BorderFactory.createEmptyBorder());
      btnOAzul.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          juego.setCeldaJugadorAzul(Celda.O);
        }
      });

      ButtonGroup btnGrpLetraAzul = new ButtonGroup();
      btnGrpLetraAzul.add(btnSAzul);
      btnGrpLetraAzul.add(btnOAzul);

      JPanel panelLetras = new JPanel();
      panelLetras.setLayout(new FlowLayout(SwingConstants.CENTER));
      panelLetras.setBorder(new EmptyBorder(0, 20, 0, 20));
      panelLetras.setBackground(Color.WHITE);

      Box boxLetraAzul = Box.createVerticalBox();
      boxLetraAzul.add(btnSAzul);
      boxLetraAzul.add(btnOAzul);

      panelLetras.add(boxLetraAzul);

      add(btnHumanoAzul);
      add(panelLetras);
      add(btnComputadoraAzul);
    }
  }

  /**
   * Muestra la letra seleccionada por el juagdor rojo
   */
  private class PanelDerecho extends JPanel {

    PanelDerecho() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugRojo = new JLabel("Jugador Rojo");
      lblJugRojo.setFont(fuente);
      add(lblJugRojo);

      btnHumanoRojo = new JRadioButton("Humano", true);
      btnHumanoRojo.setFont(fuente);
      btnHumanoRojo.setBackground(Color.WHITE);
      btnHumanoRojo.setBorder(BorderFactory.createEmptyBorder());

      btnComputadoraRojo = new JRadioButton("Computadora", false);
      btnComputadoraRojo.setFont(fuente);
      btnComputadoraRojo.setBackground(Color.WHITE);
      btnComputadoraRojo.setBorder(BorderFactory.createEmptyBorder());

      /**
       * Cambia el tipo de juego de acuerdo a los valores seleccionados (humano o computador)
       */
      btnComputadoraRojo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (btnComputadoraAzul.isSelected() && btnJuegoSimple.isSelected()) {
            setTipoJuego(new AutoJuegoSimple(juego.getTamanioTablero(), TipoJugador.COMPUTADORA,
                TipoJugador.COMPUTADORA));
          } else if (btnComputadoraAzul.isSelected() && btnJuegoGeneral.isSelected()) {
            setTipoJuego(new AutoJuegoGeneral(juego.getTamanioTablero(), TipoJugador.COMPUTADORA,
                TipoJugador.COMPUTADORA));
          } else if (btnHumanoAzul.isSelected() && btnJuegoSimple.isSelected()) {
            setTipoJuego(new AutoJuegoSimple(juego.getTamanioTablero(), TipoJugador.HUMANO,
                TipoJugador.COMPUTADORA));
          } else if (btnHumanoAzul.isSelected() && btnJuegoGeneral.isSelected()) {
            setTipoJuego(new AutoJuegoGeneral(juego.getTamanioTablero(), TipoJugador.HUMANO,
                TipoJugador.COMPUTADORA));
          }

          Celda celda;
          if (btnSRojo.isSelected()) {
            celda = Celda.S;
          } else {
            celda = Celda.O;
          }

          juego.realizarMovimiento(0, 0, celda);

          panelCentral.repaint();
        }
      });

      ButtonGroup btnGrpTipoJugadorRojo = new ButtonGroup();
      btnGrpTipoJugadorRojo.add(btnComputadoraRojo);
      btnGrpTipoJugadorRojo.add(btnHumanoRojo);

      btnSRojo = new JRadioButton("S", true);
      btnSRojo.setFont(fuente);
      btnSRojo.setBackground(Color.WHITE);
      btnSRojo.setBorder(BorderFactory.createEmptyBorder());
      btnSRojo.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          juego.setCeldaJugadorRojo(Celda.S);
        }
      });

      btnORojo = new JRadioButton("O", false);
      btnORojo.setFont(fuente);
      btnORojo.setBackground(Color.WHITE);
      btnORojo.setBorder(BorderFactory.createEmptyBorder());
      btnORojo.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          juego.setCeldaJugadorRojo(Celda.O);
        }
      });

      ButtonGroup btnGrpLetraRojo = new ButtonGroup();
      btnGrpLetraRojo.add(btnSRojo);
      btnGrpLetraRojo.add(btnORojo);

      JPanel panelLetras = new JPanel();
      panelLetras.setLayout(new FlowLayout(SwingConstants.CENTER));
      panelLetras.setBorder(new EmptyBorder(0, 20, 0, 20));
      panelLetras.setBackground(Color.WHITE);

      Box boxLetraRojo = Box.createVerticalBox();
      boxLetraRojo.add(btnSRojo);
      boxLetraRojo.add(btnORojo);

      JButton btnIniciarJuego = new JButton("Iniciar Juego");
      btnIniciarJuego.setFont(fuente);

      /**
       * Inicia el juego
       */
      btnIniciarJuego.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          juego.setEstadoJuego(EstadoJuego.JUGANDO);
          if (btnComputadoraAzul.isSelected()) {
            Celda celda;
            if (btnSAzul.isSelected()) {
              celda = Celda.S;
            } else {
              celda = Celda.O;
            }
            juego.realizarMovimiento(0, 0, celda);
            panelCentral.repaint();
            System.out.println(juego.getClass().getName());
          }
        }
      });

      panelLetras.add(boxLetraRojo);

      add(btnHumanoRojo);
      add(panelLetras);
      add(btnComputadoraRojo);
      add(btnIniciarJuego);

    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SosGui(new JuegoSimple(8)));
  }
}
