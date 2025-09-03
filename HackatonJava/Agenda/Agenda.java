package HackatonJava.Agenda;

import HackatonJava.Contacto.Contacto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agenda {
    protected ArrayList<Contacto> contactos;
    protected int Capacidad = 10;

    public Agenda() {
        this.contactos = new ArrayList<>(Capacidad);
    }

    public Agenda(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad > 0");
        }
        this.Capacidad = capacidad;
        this.contactos = new ArrayList<>(capacidad);
    }

    public boolean agendaLlena() {
        return contactos.size() >= Capacidad;
    }

    public int espaciosLibres() {
        return Capacidad - contactos.size();
    }

    public boolean existeContacto(Contacto contacto) {
        for (Contacto cont : contactos) {
            if (cont.getNombre().equalsIgnoreCase(contacto.getNombre()) &&
                    cont.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                return true;
            }
        }
        return false;
    }

    public void anadirContacto(Contacto contacto) {
        if (contacto.getNombre().isEmpty() || contacto.getApellido().isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido no pueden quedar vacíos.");
        }
        if (agendaLlena()) {
            throw new IllegalArgumentException("No tienes más espacio de almacenamiento en tu agenda");
        }
        if (contactos.contains(contacto)) {
            throw new IllegalArgumentException("Este contacto ya se encuentra en tu agenda");
        }
        contactos.add(contacto);
        System.out.println("Contacto añadido");
    }

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

    public List<Contacto> listarcontactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía");
        } else {
            contactos.sort(
                    Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                            .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
            );
        }
        return contactos;
    }
    public List<Contacto> getContactos() {
        return contactos;
    }

}
