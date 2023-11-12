package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;

public class Programador extends Empleado {
    double sueldo_extra_mensual;
    String lenguaje_principal;

    public Programador(int num_empleado, String nombre, float sueldo, float sueldo_max, double sueldo_extra_mensual, String lenguaje_principal) throws SueldoSuperiorAMaximo {
        super(num_empleado, nombre, sueldo, sueldo_max);
        this.sueldo_extra_mensual = sueldo_extra_mensual;
        this.lenguaje_principal = lenguaje_principal;
    }

    @Override
    public String toString() {
        return super.toString() + ", sueldo_extra_mensual: " + sueldo_extra_mensual + ", lenguaje_principal: " + lenguaje_principal + " PROGRAMADOR";
    }
}
