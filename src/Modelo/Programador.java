package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;

public class Programador extends Empleado {
    float sueldo_extra_mensual;
    String lenguaje_principal;

    public Programador(int num_empleado, String nombre, float sueldo, float sueldo_max, float sueldo_extra_mensual, String lenguaje_principal) throws SueldoSuperiorAMaximo {
        super(num_empleado, nombre, sueldo, sueldo_max);
        this.sueldo_extra_mensual = sueldo_extra_mensual;
        this.lenguaje_principal = lenguaje_principal;
    }
}
