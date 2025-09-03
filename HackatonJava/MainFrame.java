package HackatonJava;

import HackatonJava.Contacto.Contacto;
import HackatonJava.AgendaController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private ListaContactosPanel listaPanel;
    private AgendaController controller;

    public MainFrame() {
        setTitle("Agenda de Contactos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Backend
        controller = new AgendaController(20);
        agregarContactosPrueba();

        // Tabla de contactos
        listaPanel = new ListaContactosPanel();
        add(listaPanel, BorderLayout.CENTER);

        // Panel inferior: botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));



        add(panelInferior, BorderLayout.SOUTH);

        listaPanel.getBtnAgregar().addActionListener(e -> agregarContacto());
        listaPanel.getBtnEliminar().addActionListener(e -> eliminarContacto());
        listaPanel.getBtnBuscar().addActionListener(e -> buscarContacto());
        listaPanel.getBtnModificar().addActionListener(e -> modificarContacto());
        listaPanel.getBtnMostrarTodos().addActionListener(e -> refrescarLista());

        refrescarLista();
    }

    private void agregarContactosPrueba() {
        controller.agregarContacto("3001234567", "María", "López");
        controller.agregarContacto("3109876543", "Camila", "Rayo");
        controller.agregarContacto("3155555555", "Alejandro", "García");
        controller.agregarContacto("1122334455", "sebas", "hernandez");
    }

    private void refrescarLista() {
        List<Contacto> lista = controller.listarContactos();
        listaPanel.actualizarLista(lista);
    }

    private void agregarContacto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String apellido = JOptionPane.showInputDialog(this, "Apellido:");
        String telefono = JOptionPane.showInputDialog(this, "Teléfono:");

        if (nombre != null && apellido != null && telefono != null) {
            try {
                boolean agregado = controller.agregarContacto(telefono, nombre, apellido);
                if (!agregado) {
                    JOptionPane.showMessageDialog(this, "Ya existe un contacto con ese nombre y apellido.", "Duplicado", JOptionPane.WARNING_MESSAGE);
                } else {
                    refrescarLista();
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarContacto() {
        Contacto seleccionado = listaPanel.getContactoSeleccionado();
        if (seleccionado != null) {
            controller.eliminarContacto(seleccionado.getNombre(), seleccionado.getApellido());
            refrescarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar.");
        }
    }

    private void buscarContacto() {
        String nombreBuscar = JOptionPane.showInputDialog(this, "Ingrese nombre a buscar:");
        if (nombreBuscar != null && !nombreBuscar.trim().isEmpty()) {
            List<Contacto> filtrados = controller.buscarContactos(nombreBuscar);
            listaPanel.actualizarLista(filtrados);
        }
    }

    private void modificarContacto() {
        Contacto seleccionado = listaPanel.getContactoSeleccionado();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para modificar.");
            return;
        }

        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", seleccionado.getNombre());
        String nuevoApellido = JOptionPane.showInputDialog(this, "Nuevo apellido:", seleccionado.getApellido());
        String nuevoTelefono = JOptionPane.showInputDialog(this, "Nuevo teléfono:", seleccionado.getTelefono());

        if (nuevoNombre != null && nuevoApellido != null && nuevoTelefono != null) {
            try {
                controller.eliminarContacto(seleccionado.getNombre(), seleccionado.getApellido());
                controller.agregarContacto(nuevoTelefono, nuevoNombre, nuevoApellido);
                refrescarLista();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al modificar el contacto: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
