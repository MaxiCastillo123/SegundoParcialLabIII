import java.io.Serializable;

public class Persona implements Comparable<Persona>, Serializable {

    ///Nombre y Apellido, edad, barrio, DNI y ocupación serán
    //los datos a pedir. T

    private String nombre;
    private String apellido;
    private int edad;
    private String barrio;
    private String dni;
    private String ocupacion;
    private Integer kit;

    public Persona(String nombre, String apellido, int edad, String barrio, String dni, String ocupacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.barrio = barrio;
        this.dni = dni;
        this.ocupacion = ocupacion;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Integer getKit() {
        return kit;
    }

    public void setKit(Integer kit) {
        this.kit = kit;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", barrio='" + barrio + '\'' +
                ", dni='" + dni + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", kit=" + kit +
                '}';
    }

    @Override
    public int compareTo(Persona p) {
        return this.dni.compareTo(p.getDni());
    }





}
