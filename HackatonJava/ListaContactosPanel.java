package HackatonJava;

import HackatonJava.Contacto.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ListaContactosPanel extends JPanel {

    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnMostrarTodos;

    // Paleta de colores (café y beige)
    private final Color colorFondo = new Color(245, 240, 230);       // Beige claro
    private final Color colorPanel = new Color(230, 220, 210);       // Beige más oscuro
    private final Color colorEncabezado = new Color(92, 64, 51);     // Café oscuro
    private final Color colorTexto = new Color(60, 40, 30);          // Marrón suave
    private final Color colorSeleccion = new Color(181, 136, 99);    // Café con leche

    public ListaContactosPanel() {
        setLayout(new BorderLayout());
        setBackground(colorFondo);

        // Tabla
        String[] columnas = {"Nombre", "Apellido", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaContactos = new JTable(modeloTabla);

        tablaContactos.setRowHeight(28);
        tablaContactos.setFont(new Font("Serif", Font.PLAIN, 14));
        tablaContactos.setSelectionBackground(colorSeleccion);
        tablaContactos.setSelectionForeground(Color.WHITE);

        JTableHeader header = tablaContactos.getTableHeader();
        header.setBackground(colorEncabezado);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Serif", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tablaContactos);
        scroll.getViewport().setBackground(Color.WHITE);
        add(scroll, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(colorPanel);

        btnAgregar = crearBoton("Agregar", new Color(60, 120, 60));             // Verde
        btnEliminar = crearBoton("Eliminar", new Color(139, 0, 0));             // Rojo oscuro
        btnBuscar = crearBoton("Buscar", new Color(111, 78, 55));               // Marrón
        btnModificar = crearBoton("Modificar", new Color(70, 130, 180));        // Azul
        btnMostrarTodos = crearBoton("Mostrar todos", new Color(210, 180, 140));// Beige

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnMostrarTodos);

        add(panelBotones, BorderLayout.SOUTH);
    }

    // Método para crear botones estilizados
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Serif", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(150, 38));
        boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return boton;
    }

    // Actualizar tabla
    public void actualizarLista(List<Contacto> contactos) {
        modeloTabla.setRowCount(0);
        for (Contacto c : contactos) {
            modeloTabla.addRow(new Object[]{
                    c.getNombre(), c.getApellido(), c.getTelefono()
            });
        }
    }

    // Obtener contacto seleccionado
    public Contacto getContactoSeleccionado() {
        int fila = tablaContactos.getSelectedRow();
        if (fila == -1) return null;

        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        String apellido = (String) modeloTabla.getValueAt(fila, 1);
        String telefono = (String) modeloTabla.getValueAt(fila, 2);
        return new Contacto(telefono, nombre, apellido);
    }

    // Getters de los botones
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnMostrarTodos() {
        return btnMostrarTodos;
    }
}
