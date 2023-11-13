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
    public String getTipo() {
        return "Programador";
    }

    public double getSueldo_extra_mensual() {
        return sueldo_extra_mensual;
    }

    public void setSueldo_extra_mensual(double sueldo_extra_mensual) {
        this.sueldo_extra_mensual = sueldo_extra_mensual;
    }

    public String getLenguaje_principal() {
        return lenguaje_principal;
    }

    public void setLenguaje_principal(String lenguaje_principal) {
        this.lenguaje_principal = lenguaje_principal;
    }

    @Override
    public String toString() {
        return "PROGRAMADOR: " + super.toString() + ", Suelda extra mensual: " + sueldo_extra_mensual + ", Lenguaje principal: " + lenguaje_principal + " PROGRAMADOR";
    }
}
