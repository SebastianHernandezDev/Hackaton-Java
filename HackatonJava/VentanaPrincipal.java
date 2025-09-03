package HackatonJava;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final Color colorFondo = new Color(245, 240, 230);
    private final Color colorPanel = new Color(230, 220, 210);
    private final Color colorEncabezado = new Color(92, 64, 51);

    public VentanaPrincipal() {
        setTitle("Agenda de Contactos");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);

        JLabel titulo = new JLabel("Bienvenido a tu Agenda de Contactos", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 20));
        titulo.setForeground(colorEncabezado);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // panel con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(colorPanel);

        JButton btnEntrar = crearBoton("Entrar", new Color(101, 67, 33));    // café intenso
        JButton btnSalir = crearBoton("Salir", new Color(153, 51, 51));     // café rojizo

        panelBotones.add(btnEntrar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        // eventos botones
        btnEntrar.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });


        btnSalir.addActionListener(e -> System.exit(0));
    }

    // botones
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Serif", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(120, 40));
        boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return boton;
    }

    // probar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
