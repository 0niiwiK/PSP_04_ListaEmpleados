import Controller.Lista;
import Model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    }

    public static void probarDesordenados(){
        try {
            Lista l = new Lista();
            long startTime = System.nanoTime();
            for (int i = 0; i < 10; i++) {
                l.crearAleatorios();
            }
            l.imprimir();
            l.crearAleatoriosCienMil();
            long endTime = System.nanoTime();
            System.out.println("Tiempo creacion: " + (endTime - startTime) / 1000000 + " ms");
            List<Empleado> lista = new ArrayList<>();
            l.goFirst();
            startTime = System.nanoTime();
            while (!l.isLast()) {
                lista.add(l.getAct().getMain());
                l.goNext();
            }
            lista.add(l.getAct().getMain());
            endTime = System.nanoTime();
            System.out.println("Tiempo insercion coleccion: " + (endTime - startTime) / 1000000 + " ms");
            l.sort();
            startTime = System.nanoTime();
            lista.sort(Comparator.comparing(Empleado::getNum_empleado));
            endTime = System.nanoTime();
            System.out.println("Tiempo orden collec: " + (endTime - startTime) / 1000000 + " ms");
           // lista.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Otro");
        }
    }
}