import Modelo.*;
import usarExcepciones.SueldoSuperiorAMaximo;

public class Main {
    public static void main(String[] args) {
        try {
            Programador p1 = new Programador(1,"Carlos",1200, 1500, 20, "Python");
            System.out.println("Empleado creado");
        } catch (SueldoSuperiorAMaximo e) {
            System.out.println("Empleado no creado");
        }
    }
}