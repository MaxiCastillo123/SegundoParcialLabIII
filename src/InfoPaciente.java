import java.io.Serializable;

public class InfoPaciente implements Serializable {
    private String dni;
    private Double temperatura;

    public InfoPaciente(String dni, Double temperatura) {
        this.dni = dni;
        this.temperatura = temperatura;
    }

    public InfoPaciente(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "InfoPaciente{" +
                "dni='" + dni + '\'' +
                ", temperatura=" + temperatura +
                '}';
    }
}
