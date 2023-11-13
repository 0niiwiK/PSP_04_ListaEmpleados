package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;

public class Analista extends Empleado {
    double plus_anual;
    int anios_experiencia;

    public Analista(int num_empleado, String nombre, float sueldo, float sueldo_max, double plus_anual, int anios_experiencia) throws SueldoSuperiorAMaximo {
        super(num_empleado, nombre, sueldo, sueldo_max);
        this.plus_anual = plus_anual;
        this.anios_experiencia = anios_experiencia;
    }

    public String getTipo() {
        return "Analista";
    }

    @Override
    public String toString() {
        return "ANALISTA: "+super.toString()+", Plus Anual: "+plus_anual+", Años de Experiencia: "+anios_experiencia;
    }
}
