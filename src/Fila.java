import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Fila implements Serializable {
    private LinkedList<Persona> pacientes;
    private Integer cantidadDeMuestras;

    private HashMap<Integer, InfoPaciente> pacientesAtendidos;



    public Fila(LinkedList<Persona> pacientes) {
        this.pacientes = pacientes;
        this.cantidadDeMuestras = 2;
        this.pacientesAtendidos = new HashMap<>();
    }

    public Fila(){
        pacientes = new LinkedList<>();
        this.cantidadDeMuestras =2;
        this.pacientesAtendidos = new HashMap<>();
    }

    public LinkedList<Persona> getPacientes() {
        return pacientes;
    }

    public void setPacientes(LinkedList<Persona> pacientes) {
        this.pacientes = pacientes;
    }

    public Integer getCantidadDeMuestras() {
        return cantidadDeMuestras;
    }

    public void setCantidadDeMuestras(Integer cantidadDeMuestras) {
        this.cantidadDeMuestras = cantidadDeMuestras;
    }

    public HashMap<Integer, InfoPaciente> getPacientesAtendidos() {
        return pacientesAtendidos;
    }

    public void setPacientesAtendidos(HashMap<Integer, InfoPaciente> pacientesAtendidos) {
        this.pacientesAtendidos = pacientesAtendidos;
    }

    @Override
    public String toString() {
        return "Fila{" +
                "pacientes=" + pacientes +
                ", cantidadDeMuestras=" + cantidadDeMuestras +
                ", pacientesAtendidos=" + pacientesAtendidos +
                '}';
    }

    public boolean existeDni(Persona p){
        for (Persona paciente : pacientes) {
            if(p.getDni().equals(paciente.getDni())){
                return true;
            }
        }
        return false;
    }

    public void agregarAFila(Persona paciente){
        Random rnd = new Random();
        if(!existeDni(paciente)){
        try{
                if(cantidadDeMuestras>=1){
                    paciente.setKit(rnd.nextInt());
                    pacientes.add(paciente);
                    cantidadDeMuestras--;
                }else{
                    throw new ExcepcionPropia();
                }
            }catch (ExcepcionPropia e) {
            System.out.println("Tenemos mas muestras disponibles?");
            Scanner scr = new Scanner(System.in);
            String respuesta = scr.next();
            if(respuesta.equals("si")){
                cantidadDeMuestras = 5;
                paciente.setKit(rnd.nextInt());
                pacientes.add(paciente);
                cantidadDeMuestras--;
            }else{
                System.out.println("No podemos seguir atendiendo ya que no hay mas muestras.");
                return;
            }
        }

        }else{
            System.out.println("El paciente ya se encuenta en la fila");
            return;

    }
}
/**Luego de ingresar a estas personas vamos a invocar un método llamado “testear” donde
 evaluaremos la temperatura de cada una de las personas. Con cada evaluación generamos
 una tabla donde la clave será el número de kit y el valor contendrá un registro que
 contendrá el DNI y la temperatura (generada de manera random entre 36 y 39 grados). */

/**Para finalizar nuestro trabajo generamos un JSON (que también debemos persistir en disco)
     donde tendremos un objeto con dos claves (“sanos” y “aislar”) que serán arreglos. En el
     primer arreglo guardaremos los datos de la persona y en el segundo, kit, temperatura y
     barrio. En la primera clave mostraremos la información de las personas que no superen los
     38 grados de temperatura tomada y en el segundo los casos sospechosos (opcional).*/

    public void testear(){
        Random rnd = new Random();
        List<Persona> sanos= new ArrayList<>();
        List<String> aislar = new ArrayList<>();
        for (Persona paciente : pacientes) {
            Persona p = paciente;
            InfoPaciente info = new InfoPaciente(paciente.getDni(), rnd.nextDouble(37,39));
            pacientesAtendidos.put(paciente.getKit(),info);
            if(info.getTemperatura()<38){
                sanos.add(paciente);
            }else{
                aislar.add("El kit del paciente: "+paciente.getKit()+ " pertenece al barrio:" + paciente.getBarrio() +" y su temperatura es: "+ info.getTemperatura());

            }

        }
        Gson gson= new GsonBuilder().setPrettyPrinting().create();
        String sanitos =gson.toJson(sanos);
        try (FileWriter writer = new FileWriter("sanos.json")){
            writer.write(sanitos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String dudosos = gson.toJson(aislar);
        try (FileWriter writer = new FileWriter("aislar.json")){
            writer.write(dudosos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    /**Una vez realizados todos los test, invocamos un método llamado “aislar” donde si la
     temperatura supera los 38 grados (inclusive) lanzaremos una excepción que contendrá el
     número de test y el barrio. Como tratamiento de ese error, esos datos se deben almacenar
     en un archivo binario de objetos llamado “urgente.dat” (opcional).
     */

    public String buscandoElBarrio(String dni){
        for(Persona p : pacientes){
            if(p.getDni().equals(dni)){
                return p.getBarrio();
            }
        }
        return null;
    }
    public void aislar(HashMap<Integer,InfoPaciente>pacientesTesteados)  {
        String dni= "";
        Gson gson= new GsonBuilder().setPrettyPrinting().create();
        String mensaje="";
        String mensajeParaArchivo= "";
        for(Map.Entry<Integer,InfoPaciente> paciente : pacientesTesteados.entrySet()){
        try{
            if(paciente.getValue().getTemperatura()>=38){
                 dni = paciente.getValue().getDni();
                throw new ExcepcionPropia("Excepcion proria: ");
            }
        }catch(ExcepcionPropia e){
            mensaje = "El paciente con dni: "+dni+" Pertenece al barrio: "+ buscandoElBarrio(dni);
            mensajeParaArchivo+= mensaje+ " " + "\n";
            try (FileWriter writer = new FileWriter("urgente.dat")){
                writer.write(mensajeParaArchivo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage()+ " " +mensaje);

        }
    }


}



}