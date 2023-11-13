package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;
import java.io.Serializable;
import java.util.GregorianCalendar;

public class Empleado implements Serializable {
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

    public float getSueldo() {

        return sueldo;
    }

    public void setSueldo(float sueldo) throws SueldoSuperiorAMaximo {
        this.sueldo = sueldo;
        if (sueldo > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();
        else
            this.sueldo = sueldo;
    }

    public float getSueldo_max() {
        return sueldo_max;
    }

    public void setSueldo_max(float sueldo_max) throws SueldoSuperiorAMaximo {
            if (this.sueldo > sueldo_max)
                throw new SueldoSuperiorAMaximo();
            else
                this.sueldo_max = sueldo_max;
    }

    public String getTipo() {
        return "Empleado";
    }

    @Override
    public String toString() {
        return "Número de Empleado: " + num_empleado + ", Nombre: " + nombre + ", Sueldo: " + sueldo + ", Sueldo Máximo: " + sueldo_max;
    }
}
