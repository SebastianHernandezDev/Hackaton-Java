package HackatonJava;
import HackatonJava.Agenda.Agenda;
import HackatonJava.Contacto.Contacto;

import java.util.List;

public class AgendaController {
    private Agenda agenda;

    public AgendaController(int capacidad) {
        this.agenda = new Agenda(capacidad);
    }

    public boolean agregarContacto(String telefono, String nombre, String apellido) {
        Contacto nuevo = new Contacto(telefono, nombre, apellido);
        agenda.anadirContacto(nuevo);
        return false;
    }

    public boolean eliminarContacto(String nombre, String apellido) {
        return agenda.eliminarContactos(nombre, apellido);
    }

    public List<Contacto> buscarContactos(String nombre) {
        return agenda.buscarContacto(nombre);
    }

    public List<Contacto> listarContactos() {
        return agenda.listarcontactos();
    }

    public int espaciosLibres() {
        return agenda.espaciosLibres();
    }
}
