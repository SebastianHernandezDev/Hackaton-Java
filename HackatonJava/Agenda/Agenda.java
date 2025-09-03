package HackatonJava.Agenda;

import HackatonJava.Contacto.Contacto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agenda {
    // Lista que almacena los contactos de la agenda
    protected ArrayList<Contacto> contactos;

    // Capacidad máxima de la agenda
    protected int Capacidad = 10;

    // Constructor por defecto con capacidad de 10
    public Agenda() {
        this.contactos = new ArrayList<>(Capacidad);
    }

    // Constructor con capacidad personalizada
    public Agenda(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad > 0");
        }
        this.Capacidad = capacidad;
        this.contactos = new ArrayList<>(capacidad);
    }

    // Verifica si la agenda está llena
    public boolean agendaLlena() {
        return contactos.size() >= Capacidad;
    }

    // Devuelve cuántos espacios libres quedan en la agenda
    public int espaciosLibres() {
        return Capacidad - contactos.size();
    }

    // Verifica si ya existe un contacto con mismo nombre y apellido (sin importar mayúsculas)
    public boolean existeContacto(Contacto contacto) {
        for (Contacto cont : contactos) {
            if (cont.getNombre().equalsIgnoreCase(contacto.getNombre()) &&
                    cont.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                return true;
            }
        }
        return false;
    }

    // Agrega un nuevo contacto a la agenda
    public void anadirContacto(Contacto contacto) {
        // Validaciones
        if (contacto.getNombre().isEmpty() || contacto.getApellido().isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido no pueden quedar vacíos.");
        }
        if (agendaLlena()) {
            throw new IllegalArgumentException("No tienes más espacio de almacenamiento en tu agenda");
        }
        if (contactos.contains(contacto)) { // Usa equals() de Contacto
            throw new IllegalArgumentException("Este contacto ya se encuentra en tu agenda");
        }

        contactos.add(contacto); // Agrega el contacto
        System.out.println("Contacto añadido");
    }

    // Elimina un contacto según su nombre y apellido (ignorando mayúsculas)
    public boolean eliminarContactos(String nombre, String apellido) {
        for (Contacto cont : contactos) {
            if (cont.getNombre().equalsIgnoreCase(nombre) &&
                    cont.getApellido().equalsIgnoreCase(apellido)) {
                contactos.remove(cont);
                System.out.println("Contacto eliminado exitosamente");
                return true;
            }
        }
        System.out.println("Este contacto no existe en tu agenda");
        return false;
    }

    // Busca contactos por nombre (ignora mayúsculas/minúsculas)
    public List<Contacto> buscarContacto(String nombre) {
        List<Contacto> encontrados = new ArrayList<>();

        if (nombre == null) {
            System.out.println("Parámetro inválido");
            return encontrados;
        }

        for (Contacto cont : contactos) {
            if (cont.getNombre() != null && cont.getNombre().equalsIgnoreCase(nombre)) {
                encontrados.add(cont);
            }
        }

        if (encontrados.isEmpty()) {
            System.out.println("Este contacto no existe en tu agenda");
        }

        return encontrados;
    }

    // Modifica el número de teléfono de un contacto existente
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (Contacto cont : contactos) {
            if (cont.getNombre().equalsIgnoreCase(nombre) &&
                    cont.getApellido().equalsIgnoreCase(apellido)) {
                cont.setTelefono(nuevoTelefono);
                System.out.println("Teléfono actualizado: " + cont.getTelefono());
                return;
            }
        }
        System.out.println("Contacto no encontrado");
    }

    // Devuelve todos los contactos ordenados por nombre y apellido
    public List<Contacto> listarcontactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía");
        } else {
            // Ordena por nombre y luego por apellido (ambos ignorando mayúsculas)
            contactos.sort(
                    Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                            .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
            );
        }
        return contactos;
    }

    // Getter de la lista de contactos
    public List<Contacto> getContactos() {
        return contactos;
    }
}
