package produccion;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import produccion.JuegoSimple.EstadoJuego;
import produccion.JuegoSimple.Turno;

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
  private JRadioButton btnORojo;
  private JRadioButton btnSRojo;
  private JRadioButton btnJuegoSimple;
  private JRadioButton btnJuegoGeneral;
  private JTextField txtTamanioTablero;

  /**
   * Crea la interfaz de acuerdo al tipo de juego
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
                juego.getTamanioTablero()* TAMANIO_CELDA + 1));
    panelInferior = new PanelInferior();

    panelIzquierdo = new PanelIzquierdo();
    panelIzquierdo.setPreferredSize(new Dimension(150, juego.getTamanioTablero() * TAMANIO_CELDA));

    panelDerecho = new PanelDerecho();
    panelDerecho.setPreferredSize(new Dimension(150, juego.getTamanioTablero() * TAMANIO_CELDA));

    panelSuperior = new PanelSuperior();
    panelSuperior.setPreferredSize(new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 300, 50));

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
          if (juego.getEstadoJuego() == JuegoSimple.EstadoJuego.JUGANDO) {
            filaSeleccionada = e.getY() / TAMANIO_CELDA;
            colSeleccionada = e.getX() / TAMANIO_CELDA;
            JuegoSimple.Celda celdaSeleccionada = null;
            if (juego.getTurno() == JuegoSimple.Turno.AZUL) {
              if (btnOAzul.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.O;
              } else if (btnSAzul.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.S;
              }
            } else if (juego.getTurno() == JuegoSimple.Turno.ROJO) {
              if (btnORojo.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.O;
              } else if (btnSRojo.isSelected()) {
                celdaSeleccionada = JuegoSimple.Celda.S;
              }
            }
            juego.realizarMovimiento(filaSeleccionada, colSeleccionada, celdaSeleccionada);
          }
          repaint();
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
     * @param g
     */
    public void dibujarSos(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(4));

      List<LineaSos> lineasSos = juego.getLineasSos();
      for(LineaSos ls : lineasSos) {
        g2d.setColor(ls.getColorLinea());
        int x1 = ls.getX1()*TAMANIO_CELDA + TAMANIO_CELDA/2;
        int x2 = ls.getX2()*TAMANIO_CELDA+ TAMANIO_CELDA/2;
        int y1 = ls.getY1()*TAMANIO_CELDA+ TAMANIO_CELDA/2;
        int y2 = ls.getY2()*TAMANIO_CELDA+ TAMANIO_CELDA/2;
        g2d.drawLine(x1, y1, x2, y2);
      }
    }

    /**
     * Dibuja las líneas del tablero
     * @param g
     */
    private void dibujarLineas(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);

      for (int fila = 0; fila <= juego.getTamanioTablero(); fila++) {
        g.drawLine(0, fila * TAMANIO_CELDA, juego.getTamanioTablero() * TAMANIO_CELDA, fila * TAMANIO_CELDA);
      }

      for (int col = 0; col <= juego.getTamanioTablero(); col++) {
        g.drawLine(col * TAMANIO_CELDA, 0, col * TAMANIO_CELDA, juego.getTamanioTablero() * TAMANIO_CELDA);
      }
    }

    /**
     * Dibuja las letras 'S' u 'O' en el tablero
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
          if (juego.getCelda(fila, col) == JuegoSimple.Celda.S) {
            g2d.drawString("S", x1, y1);
          } else if (juego.getCelda(fila, col) == JuegoSimple.Celda.O) {
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
            panelSuperior.setPreferredSize(new Dimension(juego.getTamanioTablero() * TAMANIO_CELDA + 301, 50));
            panelIzquierdo.setPreferredSize(new Dimension(150, juego.getTamanioTablero() * TAMANIO_CELDA + 1));
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

      JButton btnNuevoJuego = new JButton("Nuevo Juego");
      btnNuevoJuego.setFont(fuente);
      add(btnNuevoJuego, BorderLayout.EAST);

      // Resetea el juego al estado inicial
      btnNuevoJuego.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          juego.resetearJuego(8);
          txtTamanioTablero.setText(String.valueOf(juego.getTamanioTablero()));
          panelCentral.repaint();
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
      if(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL) {
        resultado = "        Ganador: Azul!!!";
      } else if(juego.getEstadoJuego() == EstadoJuego.GANO_ROJO) {
        resultado = "        Ganador: Rojo!!!";
      } else if(juego.getEstadoJuego() == EstadoJuego.EMPATE) {
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
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugAzul = new JLabel("Jugador Azul");
      lblJugAzul.setFont(fuente);
      add(lblJugAzul);

      btnSAzul = new JRadioButton("S", true);
      btnSAzul.setFont(fuente);
      btnSAzul.setBackground(Color.WHITE);
      btnSAzul.setBorder(BorderFactory.createEmptyBorder());

      btnOAzul = new JRadioButton("O", false);
      btnOAzul.setFont(fuente);
      btnOAzul.setBackground(Color.WHITE);
      btnOAzul.setBorder(BorderFactory.createEmptyBorder());

      ButtonGroup btnGrpLetraAzul = new ButtonGroup();
      btnGrpLetraAzul.add(btnSAzul);
      btnGrpLetraAzul.add(btnOAzul);

      Box boxLetraAzul = Box.createVerticalBox();
      boxLetraAzul.add(btnSAzul);
      boxLetraAzul.add(btnOAzul);
      add(boxLetraAzul);

    }
  }

  /**
   * Muestra la letra seleccionada por el juagdor rojo
   */
  private class PanelDerecho extends JPanel {

    PanelDerecho() {
      setBorder(new EmptyBorder(20, 20, 20, 20));
      setBackground(Color.WHITE);
      JLabel lblJugRojo = new JLabel("Jugador Rojo");
      lblJugRojo.setFont(fuente);
      add(lblJugRojo);

      btnSRojo = new JRadioButton("S", true);
      btnSRojo.setFont(fuente);
      btnSRojo.setBackground(Color.WHITE);
      btnSRojo.setBorder(BorderFactory.createEmptyBorder());

      btnORojo = new JRadioButton("O", false);
      btnORojo.setFont(fuente);
      btnORojo.setBackground(Color.WHITE);
      btnORojo.setBorder(BorderFactory.createEmptyBorder());

      ButtonGroup btnGrpLetraRojo = new ButtonGroup();
      btnGrpLetraRojo.add(btnSRojo);
      btnGrpLetraRojo.add(btnORojo);

      Box boxLetraRojo = Box.createVerticalBox();
      boxLetraRojo.add(btnSRojo);
      boxLetraRojo.add(btnORojo);
      add(boxLetraRojo);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SosGui(new JuegoSimple(8)));
  }
}
