package HackatonJava;

import HackatonJava.Contacto.Contacto;
import HackatonJava.AgendaController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private ListaContactosPanel listaPanel;       // Panel que contiene la tabla y los botones
    private AgendaController controller;          // Controlador que maneja la lógica de la agenda

    public MainFrame() {
        setTitle("Agenda de Contactos");          // Título de la ventana
        setSize(700, 500);                        // Tamaño inicial
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);              // Centrar la ventana
        setLayout(new BorderLayout());            // Layout principal

        // Inicializamos el controlador con una capacidad máxima
        controller = new AgendaController(20);
        agregarContactosPrueba();                 // Cargamos datos de prueba

        // Creamos el panel que muestra la tabla de contactos
        listaPanel = new ListaContactosPanel();
        add(listaPanel, BorderLayout.CENTER);     // Lo agregamos al centro del frame

        // Creamos un panel inferior (aunque visualmente ya está en el panel de ListaContactosPanel)
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        add(panelInferior, BorderLayout.SOUTH);   // No tiene botones, solo es decorativo aquí

        // Conectamos los botones del panel con sus acciones respectivas
        listaPanel.getBtnAgregar().addActionListener(e -> agregarContacto());
        listaPanel.getBtnEliminar().addActionListener(e -> eliminarContacto());
        listaPanel.getBtnBuscar().addActionListener(e -> buscarContacto());
        listaPanel.getBtnModificar().addActionListener(e -> modificarContacto());
        listaPanel.getBtnMostrarTodos().addActionListener(e -> refrescarLista());

        // Llenamos inicialmente la tabla
        refrescarLista();
    }

    // Carga algunos contactos por defecto al iniciar la aplicación
    private void agregarContactosPrueba() {
        controller.agregarContacto("3001234567", "María", "López");
        controller.agregarContacto("3109876543", "Camila", "Rayo");
        controller.agregarContacto("3155555555", "Alejandro", "García");
        controller.agregarContacto("1122334455", "sebas", "hernandez");
    }

    // Refresca el contenido de la tabla desde la agenda
    private void refrescarLista() {
        List<Contacto> lista = controller.listarContactos();
        listaPanel.actualizarLista(lista);
    }

    // Abre un formulario simple para agregar un nuevo contacto
    private void agregarContacto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String apellido = JOptionPane.showInputDialog(this, "Apellido:");
        String telefono = JOptionPane.showInputDialog(this, "Teléfono:");

        if (nombre != null && apellido != null && telefono != null) {
            try {
                boolean agregado = controller.agregarContacto(telefono, nombre, apellido);

                // Si no se pudo agregar, probablemente es un duplicado
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

    // Elimina el contacto seleccionado en la tabla
    private void eliminarContacto() {
        Contacto seleccionado = listaPanel.getContactoSeleccionado();
        if (seleccionado != null) {
            controller.eliminarContacto(seleccionado.getNombre(), seleccionado.getApellido());
            refrescarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar.");
        }
    }

    // Busca un contacto por nombre y filtra la tabla
    private void buscarContacto() {
        String nombreBuscar = JOptionPane.showInputDialog(this, "Ingrese nombre a buscar:");
        if (nombreBuscar != null && !nombreBuscar.trim().isEmpty()) {
            List<Contacto> filtrados = controller.buscarContactos(nombreBuscar);
            listaPanel.actualizarLista(filtrados);
        }
    }

    // Permite modificar los datos del contacto seleccionado
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
                // Se elimina el viejo contacto y se crea uno nuevo con los nuevos datos
                controller.eliminarContacto(seleccionado.getNombre(), seleccionado.getApellido());
                controller.agregarContacto(nuevoTelefono, nuevoNombre, nuevoApellido);
                refrescarLista();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al modificar el contacto: " + e.getMessage());
            }
        }
    }

    // Método main que lanza la interfaz gráfica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
