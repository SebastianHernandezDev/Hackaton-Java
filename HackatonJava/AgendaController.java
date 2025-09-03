package HackatonJava;

import HackatonJava.Agenda.Agenda;
import HackatonJava.Contacto.Contacto;

import java.util.List;

public class AgendaController {
    private Agenda agenda;

    // Constructor que inicializa una agenda con la capacidad dada
    public AgendaController(int capacidad) {
        this.agenda = new Agenda(capacidad);
    }

    // Agrega un nuevo contacto a la agenda con los datos recibidos
    // Lanza excepción si el contacto ya existe o los datos no son válidos
    public boolean agregarContacto(String telefono, String nombre, String apellido) {
        Contacto nuevo = new Contacto(telefono, nombre, apellido);
        agenda.anadirContacto(nuevo);
        return false; // ← este return debería ser true si todo sale bien
    }

    // Elimina un contacto según su nombre y apellido
    // Retorna true si se eliminó, false si no se encontró
    public boolean eliminarContacto(String nombre, String apellido) {
        return agenda.eliminarContactos(nombre, apellido);
    }

    // Busca y retorna una lista de contactos que coincidan con el nombre dado
    public List<Contacto> buscarContactos(String nombre) {
        return agenda.buscarContacto(nombre);
    }

    // Retorna la lista completa de contactos ordenada alfabéticamente
    public List<Contacto> listarContactos() {
        return agenda.listarcontactos();
    }

    // Devuelve cuántos espacios libres quedan en la agenda
    public int espaciosLibres() {
        return agenda.espaciosLibres();
    }
}
