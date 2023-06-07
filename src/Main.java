import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Persona p1 = new Persona("Maxi","Castillo",30,"Peralta Ramos","37388852","Empleado de Comercio");
        Persona p2 = new Persona("Victoria","Cabo",30,"Faro","37854698","Empleado de Comercio");
        Persona p3 = new Persona("Andrea","Barcala",33,"Constitucion","36288852","Empleado de Comercio");
        Persona p4 = new Persona("Emi","Sasso",28,"Constitucion","396548721","Empleado Metalurgico");
        Persona p5 = new Persona("Emanuel","Peral",30,"Parque Luro","38546321","Empleado de Comercio");
        Persona p6 = new Persona("Santiago","Mato",33,"Centro","35546321","Empleado informatico");


        Fila fila1 = new Fila();

        System.out.println("Probando la funcion de agregar a la lista comprobando que haya o no muestras disponibles:");
        fila1.agregarAFila(p1);
        fila1.agregarAFila(p2);
        fila1.agregarAFila(p3);
        fila1.agregarAFila(p4);
        fila1.agregarAFila(p5);
        fila1.agregarAFila(p6);
        System.out.println("Imprimimos la lista con los pacientes agregados: ");
        System.out.println(fila1.toString());

        fila1.testear();
        System.out.println("Probamos la funcion aislar arrojando el mensaje de la excepcion:");
        fila1.aislar(fila1.getPacientesAtendidos());
    }
}