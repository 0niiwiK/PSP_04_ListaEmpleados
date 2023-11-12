package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;

import java.util.GregorianCalendar;

public class Empleado {
    private static String SUELDO_EXCEPTION = "SUELDO_SUPERIOR_MAXIMO";
    int num_empleado;
    transient String nombre;
    float sueldo;
    float sueldo_max;
    GregorianCalendar fecha_alta;


    public Empleado(int num_empleado, String nombre, float sueldo, float sueldo_max) throws SueldoSuperiorAMaximo {
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.sueldo_max = sueldo_max;

        if (this.sueldo > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();
    }

    public int getNum_empleado() {
        return num_empleado;
    }

    // TODO

}
