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

    @Override
    public void calcularSueldo() throws SueldoSuperiorAMaximo {
        if (this.sueldo+this.plus_anual > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();
        else
            this.sueldo = this.sueldo + this.plus_anual;
    }
    public Analista(int num_empleado, String nombre, float sueldo, String fecha_alta, float sueldo_max, double plus_anual, int anios_experiencia) throws SueldoSuperiorAMaximo {
        super(num_empleado, nombre, sueldo, fecha_alta, sueldo_max);
        this.plus_anual = plus_anual;
        this.anios_experiencia = anios_experiencia;
    } 

    public double getPlus_anual() {
        return plus_anual;
    }

    public void setPlus_anual(double plus_anual) {
        this.plus_anual = plus_anual;
    }

    public int getAnios_experiencia() {
        return anios_experiencia;
    }

    public void setAnios_experiencia(int anios_experiencia) {
        this.anios_experiencia = anios_experiencia;
    }

    @Override
    public String getTipo() {
        return "Analista";
    }

    @Override
    public String toString() {
        return "ANALISTA: "+super.toString()+", Plus Anual: "+plus_anual+", Años de Experiencia: "+anios_experiencia;
    }
}
