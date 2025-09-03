package HackatonJava.Contacto;
import java.util.Objects;

public class Contacto {
    protected String telefono;
    protected String nombre;
    protected String apellido;


    public Contacto() {
    }

    public Contacto(String nombre, String apellido) {
        if (!elNombreEsValido(nombre)) {
            throw new IllegalArgumentException("El nombre solo debe contener letras.");
        }

        if (!elApellidoEsValido(apellido)) {
            throw new IllegalArgumentException("El apellido solo debe contener letras.");
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = "0000000000";
    }


    // validacion de telefono - nombre y apellido
    public boolean elTelefonoEsValido(String telefono) {
        return telefono.length() == 10 && telefono.matches("\\d+");

    }

    public boolean elNombreEsValido(String nombre) {
        return nombre != null && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public boolean elApellidoEsValido(String apellido) {
        return apellido != null && apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    //! Constructor con validaciones
    public Contacto(String telefono, String nombre, String apellido) {
        if (!elNombreEsValido(nombre)) {
            throw new IllegalArgumentException("El nombre solo debe contener letras y espacios.");
        }

        if (!elApellidoEsValido(apellido)) {
            throw new IllegalArgumentException("El apellido solo debe contener letras y espacios.");
        }

        if (!elTelefonoEsValido(telefono)) {
            throw new IllegalArgumentException("El teléfono debe contener solo números y tener exactamente 10 digitos.");
        }

        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    //! Getter para mandar nombre y apellido en un solo metodo separado por espacio
    public String getNombreApellido() {
        return nombre + " " + apellido;
    }

    //! Validación y comparación de objetos para evitar duplicados
    @Override
    public boolean equals(Object obj) {
        //! Si el objeto actual y el recibido son exactamente el mismo en memoria, son iguales
        if (this == obj) return true;

        //! Si el objeto recibido NO es de tipo Contacto, no se pueden comparar → retorna false
        if (!(obj instanceof Contacto)) return false;

        //! Convierte (castea) el objeto recibido a tipo Contacto para poder acceder a sus atributos
        Contacto otro = (Contacto) obj;

        //! Compara los nombres y apellidos ignorando mayúsculas/minúsculas
        return this.nombre.equalsIgnoreCase(otro.nombre) &&
                this.apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }
}

