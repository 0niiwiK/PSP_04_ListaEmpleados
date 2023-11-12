package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;

public class Analista extends Empleado {
    float plus_anual;
    int anios_experiencia;

    public Analista(int num_empleado, String nombre, float sueldo, float sueldo_max, float plus_anual, int anios_experiencia) throws SueldoSuperiorAMaximo {
        super(num_empleado, nombre, sueldo, sueldo_max);
        this.plus_anual = plus_anual;
        this.anios_experiencia = anios_experiencia;
    }

    // TODO
}
