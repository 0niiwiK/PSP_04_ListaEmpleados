import Controlador.Lista;
import Modelo.*;
import usarExcepciones.SueldoSuperiorAMaximo;

public class Main {
    public static void main(String[] args) {
        try {
            Lista l = new Lista();
            l.add(new Empleado(1, "Pedro", 1000, 2000));
            l.add(new Empleado(2, "Juan", 2000, 3000));
            l.add(new Empleado(3, "Maria", 3000, 4000));
            l.add(new Analista(4, "Jose", 4000, 5000, 0.25, 2));
            l.imprimir();
            l.intercambiar(1,3);
            l.intercambiar(4,1);
            System.out.println("");
            l.imprimir();
            l.sort();
            System.out.println("");
            l.imprimir();
        } catch (Exception e) {
            System.out.println("Otro");
        }
    }
}