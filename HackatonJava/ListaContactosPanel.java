package HackatonJava;

import HackatonJava.Contacto.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ListaContactosPanel extends JPanel {

    // Componentes de la tabla y botones
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    // Botones de acción
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnMostrarTodos;

    // Paleta de colores personalizada para la interfaz
    private final Color colorFondo = new Color(245, 240, 230);
    private final Color colorPanel = new Color(230, 220, 210);
    private final Color colorEncabezado = new Color(92, 64, 51);
    private final Color colorTexto = new Color(60, 40, 30);
    private final Color colorSeleccion = new Color(181, 136, 99);

    public ListaContactosPanel() {
        // Establece el layout principal
        setLayout(new BorderLayout());
        setBackground(colorFondo);

        // Configuración de la tabla y su modelo
        String[] columnas = {"Nombre", "Apellido", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaContactos = new JTable(modeloTabla);

        // Ajuste visual de la tabla
        tablaContactos.setRowHeight(28);
        tablaContactos.setFont(new Font("Serif", Font.PLAIN, 14));
        tablaContactos.setSelectionBackground(colorSeleccion);
        tablaContactos.setSelectionForeground(Color.WHITE);

        // Estilo del encabezado de la tabla
        JTableHeader header = tablaContactos.getTableHeader();
        header.setBackground(colorEncabezado);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Serif", Font.BOLD, 14));

        // Añade la tabla con scroll al panel principal
        JScrollPane scroll = new JScrollPane(tablaContactos);
        scroll.getViewport().setBackground(Color.WHITE);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior que contiene los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(colorPanel);

        // Se crean los botones con sus colores representativos
        btnAgregar = crearBoton("Agregar", new Color(60, 120, 60));
        btnEliminar = crearBoton("Eliminar", new Color(139, 0, 0));
        btnBuscar = crearBoton("Buscar", new Color(111, 78, 55));
        btnModificar = crearBoton("Modificar", new Color(70, 130, 180));
        btnMostrarTodos = crearBoton("Mostrar todos", new Color(210, 180, 140));

        // Se añaden los botones al panel
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnMostrarTodos);

        // Se agrega el panel de botones a la parte inferior
        add(panelBotones, BorderLayout.SOUTH);
    }

    // Metodo auxiliar para estilizar los botones visualmente
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

    // Actualiza la tabla de contactos con una nueva lista
    public void actualizarLista(List<Contacto> contactos) {
        modeloTabla.setRowCount(0); // Limpia la tabla
        for (Contacto c : contactos) {
            modeloTabla.addRow(new Object[]{
                    c.getNombre(), c.getApellido(), c.getTelefono()
            });
        }
    }

    // Devuelve el contacto actualmente seleccionado en la tabla
    public Contacto getContactoSeleccionado() {
        int fila = tablaContactos.getSelectedRow();
        if (fila == -1) return null; // No hay selección

        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        String apellido = (String) modeloTabla.getValueAt(fila, 1);
        String telefono = (String) modeloTabla.getValueAt(fila, 2);
        return new Contacto(telefono, nombre, apellido);
    }

    // Métodos para obtener los botones desde otras clases
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
